package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.CommentService;
import services.QuestionService;
import services.RendezvousService;
import services.UserService;

import controllers.AbstractController;
import domain.Comment;
import domain.Question;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/question/user")
public class QuestionUserController extends AbstractController{
	
	@Autowired
	private QuestionService			questionService;
	
	@Autowired
	private RendezvousService			rendezvousService;
	
	@Autowired
	private UserService			userService;

	//Creation--------------------------
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam final Integer rendezvousId) {
			ModelAndView result;
			Question question;
			question = this.questionService.create(rendezvousId);

			result = this.createEditModelAndView(question);

			result.addObject("requestURI", "question/user/create.do?rendezvousId=" + rendezvousId);

			return result;
		}
		
		//Edit----------------------------------------------------------------------
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam final int questionId) {
			final ModelAndView res;
			final Question question = this.questionService.findOne(questionId);
			res = this.createEditModelAndView(question);
			return res;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final Question question, final BindingResult binding) {

			ModelAndView result;

			if (binding.hasErrors())
				result = this.createEditModelAndView(question);
			else

				try {
					
						this.questionService.saveQuestions(question);
						result = new ModelAndView("redirect:/question/user/list.do");
					
				}

				catch (final Throwable oops) {
					result = this.createEditModelAndView(question, "question.comit.error");
				}

			return result;
		}

		protected ModelAndView createEditModelAndView(final Question question) {
			ModelAndView result;

			result = this.createEditModelAndView(question, null);
			return result;
		}

		protected ModelAndView createEditModelAndView(final Question question, final String messageCode) {
			ModelAndView result;

			result = new ModelAndView("question/user/edit");
			result.addObject("question", question);
			result.addObject("message", messageCode);
			return result;
		}


}
