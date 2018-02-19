
package controllers;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvousService;
<<<<<<< HEAD
import services.UserService;
=======
import domain.Flag;
>>>>>>> ab26a804d38129dc072e0417cf133055b5138530
import domain.Rendezvous;

@Controller
@RequestMapping("/rendezvous")
public class RendezvousController extends AbstractController {

	//Services ----------------------------------------------------------
	@Autowired
	RendezvousService	rendezvousService;

	@Autowired
	UserService			userService;


	//Constructors ------------------------------------------------------

	//Listing -----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Rendezvous> rendezvouses;

		rendezvouses = this.rendezvousService.findAll();

		for (final Rendezvous r : rendezvouses)
			if (r.getMoment().before(new Date()) && r.getFlag() == Flag.ACTIVE)
				r.setFlag(Flag.PASSED);

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/list.do");

		return result;
	}

	//Display ------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView Display(@RequestParam final Integer rendezvousId) {                                                        //Listeo de viajes
		ModelAndView result;
		Rendezvous rendezvous;
		result = new ModelAndView("rendezvous/display");
		try {
			final Boolean hasUserRSVPd = this.userService.hasUserRSVPd(rendezvousId);
			result.addObject("hasUserRSVPd", hasUserRSVPd);
		} catch (final Throwable oops) {
		}

		rendezvous = this.rendezvousService.findOne(rendezvousId);
		if (rendezvous.getMoment().before(new Date()) && rendezvous.getFlag() == Flag.ACTIVE)
			rendezvous.setFlag(Flag.PASSED);

		result.addObject("rendezvous", rendezvous);
		result.addObject("requestURI", "rendezvous/display.do");

		return result;
	}

	//Creation ----------------------------------------------------------

	//Edition -----------------------------------------------------------

	//Ancillary Methods -------------------------------------------------

}
