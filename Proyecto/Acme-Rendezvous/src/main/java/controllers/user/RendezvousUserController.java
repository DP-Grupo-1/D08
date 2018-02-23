
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.RSVPService;
import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;
import domain.RSVP;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/rendezvous/user")
public class RendezvousUserController extends AbstractController {

	// Services ---------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private UserService			userService;

	@Autowired
	private RSVPService			rsvpService;


	//Listing ----------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Rendezvous> rendezvouses = new ArrayList<Rendezvous>();
		final User logged = this.userService.findByPrincipal();
		//	if(logged.id)
		rendezvouses = this.rendezvousService.findByCreatorId(logged.getId());

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/user/list.do");

		return result;
	}

	//Create----------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result = null;

		try {
			final User user = this.userService.findByPrincipal();
			Assert.notNull(user);
			final Rendezvous rendezvous = this.rendezvousService.create();

			result = this.createEditModelAndView(rendezvous);
			result.addObject("rendezvous", rendezvous);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/");
			System.out.println(oops);
		}

		return result;
	}

	//Edition----------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int rendezvousId) {

		ModelAndView result;
		Rendezvous rendezvous;

		rendezvous = this.rendezvousService.findOne(rendezvousId);
		Assert.notNull(rendezvous);
		result = this.createEditModelAndView(rendezvous);
		result.addObject("rendezvous", rendezvous);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Rendezvous rendezvous, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			result = this.createEditModelAndView(rendezvous);
		} else
			try {
				final Rendezvous saved = this.rendezvousService.save(rendezvous);
				result = new ModelAndView("redirect:../display.do?rendezvousId=" + saved.getId());
			} catch (final Throwable error) {
				System.out.println(error);
				result = this.createEditModelAndView(rendezvous, "rendezvous.comit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Rendezvous rendezvous, final BindingResult binding) {

		ModelAndView result;

		try {
			this.rendezvousService.deleteByUser(rendezvous);
			result = new ModelAndView("redirect:../display.do?rendezvousId=" + rendezvous.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(rendezvous, "rendezvous.comit.error");
		}
		return result;
	}

	//Attend ------------------------------------------------------------

	@RequestMapping(value = "/attend", method = RequestMethod.GET)
	public ModelAndView attend(@RequestParam final int rendezvousId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;

		try {
			final User user = this.userService.findByPrincipal();
			
			Assert.notNull(user);
			RSVP rsvp = this.rsvpService.create(rendezvousId);
			this.rsvpService.save(rsvp);
			
			redirectAttrs.addFlashAttribute("message", "rendezvous.commit.ok");
			redirectAttrs.addFlashAttribute("msgType", "success");
		} catch (final Throwable oops) {
			System.out.println(oops.getLocalizedMessage());
			System.out.println(oops.getMessage());
			redirectAttrs.addFlashAttribute("message", "rendezvous.commit.error");
			redirectAttrs.addFlashAttribute("msgType", "danger");
		}

		result = new ModelAndView("redirect:/rendezvous/list.do");

		return result;
	}

	@RequestMapping(value = "/noAttend", method = RequestMethod.GET)
	public ModelAndView noAttend(@RequestParam final int rendezvousId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		final User user = this.userService.findByPrincipal();
		RSVP rsvpValido = null;
		Assert.notNull(user);
		Collection<RSVP> rsvps = this.rendezvousService.findRSVPs(rendezvousId);
		for(RSVP rsvp: rsvps){
			if(user.getRsvps().contains(rsvp)){
				rsvpValido = rsvp;
				break;
			}
		}
		
		try {
			
			
			this.rsvpService.delete(rsvpValido);

			redirectAttrs.addFlashAttribute("message", "rendezvous.commit.ok");
			redirectAttrs.addFlashAttribute("msgType", "success");
		} catch (final Throwable oops) {

			redirectAttrs.addFlashAttribute("message", "rendezvous.commit.error");
			redirectAttrs.addFlashAttribute("msgType", "danger");
		}

		result = new ModelAndView("redirect:/rendezvous/list.do");
		
		return result;
	}

	// Link ------------------------------------------------------

	@RequestMapping(value = "/deleteLink", method = RequestMethod.GET)
	public ModelAndView link(@RequestParam final int rendezvousId, final RedirectAttributes redirectAttrs) {
		final ModelAndView result;

		try {
			final User user = this.userService.findByPrincipal();
			Assert.notNull(user);

			final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);

			final Collection<Rendezvous> rendezvouses = this.rendezvousService.findAll();

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

	// Ancillary methods -----------------------------------------
	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous) {
		ModelAndView result;
		result = this.createEditModelAndView(rendezvous, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous, final String message) {
		ModelAndView result;

		result = new ModelAndView("rendezvous/user/edit");
		result.addObject("rendezvous", rendezvous);
		result.addObject("message", message);
		return result;
	}
}
