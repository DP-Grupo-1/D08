
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.RSVPService;
import services.RendezvousService;
import services.UserService;
import domain.RSVP;
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

	@Autowired
	RSVPService			rsvpService;


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
			final Boolean hasUserRSVPd = this.userService.hasUserRSVPd(rendezvousId);
			result.addObject("hasUserRSVPd", hasUserRSVPd);
		} catch (final Throwable oops) {
		}

		rendezvous = this.rendezvousService.findOne(rendezvousId);

		result.addObject("rendezvous", rendezvous);
		result.addObject("requestURI", "rendezvous/display.do");

		return result;
	}

	//Attend ------------------------------------------------------------

	@RequestMapping(value = "/attend", method = RequestMethod.GET)
	public ModelAndView attend(@RequestParam final int rendezvousId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;

		try {
			final User user = this.userService.findByPrincipal();
			Assert.notNull(user);
			this.rsvpService.create(rendezvousId);

			redirectAttrs.addFlashAttribute("message", "rendezvous.commit.ok");
			redirectAttrs.addFlashAttribute("msgType", "success");
		} catch (final Throwable oops) {
			System.out.println(oops.getLocalizedMessage());
			System.out.println(oops.getMessage());
			redirectAttrs.addFlashAttribute("message", "rendezvous.commit.error");
			redirectAttrs.addFlashAttribute("msgType", "danger");
		}

		result = new ModelAndView("redirect:/rendezvous/display.do");

		return result;
	}

	@RequestMapping(value = "/noAttend", method = RequestMethod.GET)
	public ModelAndView noAttend(@RequestParam final int rsvpId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;

		try {
			final RSVP rsvp = this.rsvpService.findOne(rsvpId);
			final User user = this.userService.findByPrincipal();
			Assert.notNull(user);
			this.rsvpService.delete(rsvp);

			redirectAttrs.addFlashAttribute("message", "rendezvous.commit.ok");
			redirectAttrs.addFlashAttribute("msgType", "success");
		} catch (final Throwable oops) {

			redirectAttrs.addFlashAttribute("message", "rendezvous.commit.error");
			redirectAttrs.addFlashAttribute("msgType", "danger");
		}

		result = new ModelAndView("redirect:/rendezvous/display.do");

		return result;
	}

	//Creation ----------------------------------------------------------

	//Edition -----------------------------------------------------------

	//Ancillary Methods -------------------------------------------------

}
