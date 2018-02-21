package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.RSVP;
import domain.Rendezvous;
import domain.User;

import services.RSVPService;
import services.RendezvousService;
import services.UserService;

@Controller
@RequestMapping("/RSVP/user")
public class RSVPUserController {
	
	@Autowired
	private RendezvousService	rendezvousService;
	@Autowired
	private RSVPService	rsvpService;
	@Autowired
	private UserService	userService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int rendezvousId) {
		ModelAndView result;
		final User user = this.userService.findByPrincipal();
		Assert.notNull(user);
		final RSVP rsvp = this.rsvpService.create(rendezvousId);
		result = this.createEditModelAndView(rsvp);
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final RSVP rsvp, final BindingResult binding) {
//		ModelAndView res;
//		Assert.notNull(rsvpId);
//		RSVP rsvp = this.rsvpService.findOne(rsvpId);
//		this.rsvpService.delete(rsvp);
//		res = new ModelAndView("redirect:list.do");
//		return res;
		

		ModelAndView result = null;

		try {

			this.rsvpService.delete(rsvp);
			result = new ModelAndView("redirect:../list.do");

		}

		catch (final Throwable oops) {
			if (rsvp.getId()==0)
				result = this.createEditModelAndView(rsvp, "category.comit.error2");
			
		}
		return result;

	}
	private ModelAndView createEditModelAndView(final RSVP rsvp) {
		final ModelAndView res = this.createEditModelAndView(rsvp, null);
		return res;
	}
	
	@RequestMapping(value = "/addRSVP", method = RequestMethod.GET)
	public ModelAndView addRSVP(@RequestParam final int rendezvousId) {
		ModelAndView res;
		Assert.notNull(rendezvousId);
		this.rsvpService.addRSVP(rendezvousId);
		res = new ModelAndView("redirect:list.do");

		return res;
	}
	private ModelAndView createEditModelAndView(final RSVP rsvp, final String message) {
		final ModelAndView res;

		res = new ModelAndView("rsvp/user/edit");
		res.addObject("RSVP", rsvp);
		res.addObject("message", message);

		return res;
	}
}
