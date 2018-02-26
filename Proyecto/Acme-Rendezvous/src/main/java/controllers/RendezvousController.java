
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
import services.QuestionService;
import services.RendezvousService;
import services.UserService;
import domain.Question;
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
<<<<<<< HEAD
	QuestionService			questionService;
=======
	QuestionService		questionService;
>>>>>>> 024f406c6d11d9fb930c95ecaa6b919457bfb467


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
			final UserAccount userAcc = LoginService.getPrincipal();
			final User u = this.userService.findByUserAccount(userAcc);

			Boolean hasUserRSVPd = false;

			if (u != null) {
				//Rendezvouses a los que el usuario va a asistir (RSVPs)
				final Collection<Rendezvous> rendezvouses = this.rendezvousService.findByUserId(u.getId());

				for (final Rendezvous r : rendezvouses)
					if (r.getId() == rendezvousId) {
						hasUserRSVPd = true;
						break;
					}

			}
			result.addObject("hasUserRSVPd", hasUserRSVPd);

		} catch (final Throwable oops) {
		}

		rendezvous = this.rendezvousService.findOne(rendezvousId);
		final Collection<Rendezvous> rendezvouses = this.rendezvousService.findAll();
<<<<<<< HEAD
		Collection<Question> questions = this.questionService.findAllByrendezvous(rendezvousId);
		Boolean noQuestions = questions.isEmpty();
=======
		final Collection<Question> questions = this.questionService.findAllByRendezvous(rendezvousId);
		final Boolean noQuestions = questions.isEmpty();
>>>>>>> 024f406c6d11d9fb930c95ecaa6b919457bfb467
		result.addObject("rendezvous", rendezvous);
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("noQuestions", noQuestions);
		result.addObject("requestURI", "rendezvous/display.do");

		return result;
	}

	//Display from Announcement ------------------------------------------------------------
	@RequestMapping(value = "/display2", method = RequestMethod.GET)
	public ModelAndView Display2(@RequestParam final Integer announcementId) {                                                        //Listeo de viajes
		ModelAndView result;

		result = new ModelAndView("rendezvous/display2");

		try {
			final Rendezvous rendezvous = this.rendezvousService.findByAnnouncementId(announcementId);
			result.addObject("rendezvous", rendezvous);

			final UserAccount userAcc = LoginService.getPrincipal();
			final User u = this.userService.findByUserAccount(userAcc);

			Boolean hasUserRSVPd = false;

			if (u != null) {
				//Rendezvouses a los que el usuario va a asistir (RSVPs)
				final Collection<Rendezvous> rendezvouses = this.rendezvousService.findByUserId(u.getId());

				for (final Rendezvous r : rendezvouses)
					if (r.getId() == rendezvous.getId()) {
						hasUserRSVPd = true;
						break;
					}

			}

			result.addObject("hasUserRSVPd", hasUserRSVPd);

		} catch (final Throwable oops) {
		}

		final Collection<Rendezvous> rendezvouses = this.rendezvousService.findAll();

		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/display2.do");

		return result;
	}

	//Creation ----------------------------------------------------------

	//Edition -----------------------------------------------------------

	//Ancillary Methods -------------------------------------------------

}
