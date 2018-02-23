
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.RendezvousService;
import services.UserService;
import domain.Rendezvous;
import domain.User;

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
			UserAccount userAcc = LoginService.getPrincipal();
			User u = this.userService.findByUserAccount(userAcc);

			Boolean hasUserRSVPd = false;

			if(u != null){
				//Rendezvouses a los que el usuario va a asistir (RSVPs)
				Collection<Rendezvous> rendezvouses = this.rendezvousService.findByUserId(u.getId());

				for(Rendezvous r: rendezvouses){
					if(r.getId() == rendezvousId){
						hasUserRSVPd = true;
						break;
					}
				}

			}
			result.addObject("hasUserRSVPd", hasUserRSVPd);

		} catch (final Throwable oops) {
		}

		rendezvous = this.rendezvousService.findOne(rendezvousId);
		final Collection<Rendezvous> rendezvouses = this.rendezvousService.findAll();

		result.addObject("rendezvous", rendezvous);
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/display.do");

		return result;
	}

	//Creation ----------------------------------------------------------

	//Edition -----------------------------------------------------------

	//Ancillary Methods -------------------------------------------------

}
