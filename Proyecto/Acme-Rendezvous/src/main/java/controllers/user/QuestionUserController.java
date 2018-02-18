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

			result = this.createEditModelAndViewQuestion(question);

			result.addObject("requestURI", "question/user/create.do?rendezvousId=" + rendezvousId);

			return result;
		}
		
		//Edit----------------------------------------------------------------------
		@RequestMapping(value = "/editQuestion", method = RequestMethod.GET)
		public ModelAndView editQuestion(@RequestParam final int questionId) {
			final ModelAndView res;
			final Question question = this.questionService.findOne(questionId);
			res = this.createEditModelAndViewQuestion(question);
			return res;
		}

		@RequestMapping(value = "/editQuestion", method = RequestMethod.POST, params = "save")
		public ModelAndView saveQuestion(@Valid final Question question, final BindingResult binding) {

			ModelAndView result;

			if (binding.hasErrors())
				result = this.createEditModelAndViewQuestion(question);
			else

				try {
					
						this.questionService.saveQuestion(question);
						result = new ModelAndView("redirect:/welcome/index.do");
					
				}

				catch (final Throwable oops) {
					result = this.createEditModelAndViewQuestion(question, "question.comit.error");
				}

			return result;
		}

		protected ModelAndView createEditModelAndViewQuestion(final Question question) {
			ModelAndView result;

			result = this.createEditModelAndViewQuestion(question, null);
			return result;
		}

		protected ModelAndView createEditModelAndViewQuestion(final Question question, final String messageCode) {
			ModelAndView result;

			result = new ModelAndView("question/user/editQuestion");
			result.addObject("question", question);
			result.addObject("message", messageCode);
			return result;
		}

		
		@RequestMapping(value = "/answerQuestions", method = RequestMethod.GET)
		public ModelAndView answerQuestions(@RequestParam final int rendezvousId) {
			final ModelAndView res;
			final Collection<Question> questions = this.questionService.findAllByrendezvous(rendezvousId);
			res = this.createEditModelAndViewQuestions(questions);
			return res;
		}

		@RequestMapping(value = "/answerQuestions", method = RequestMethod.POST, params = "save")
		public ModelAndView saveQuestions(@Valid final Collection<Question> questions, final BindingResult binding) {

			ModelAndView result;

			if (binding.hasErrors())
				result = this.createEditModelAndViewQuestions(questions);
			else

				try {
					
						this.questionService.saveQuestions(questions);
						result = new ModelAndView("redirect:/welcome/index.do");
					
				}

				catch (final Throwable oops) {
					result = this.createEditModelAndViewQuestions(questions, "question.comit.error");
				}

			return result;
		}

		protected ModelAndView createEditModelAndViewQuestions(final Collection<Question> questions) {
			ModelAndView result;

			result = this.createEditModelAndViewQuestions(questions, null);
			return result;
		}

		protected ModelAndView createEditModelAndViewQuestions(final Collection<Question> questions, final String messageCode) {
			ModelAndView result;

			result = new ModelAndView("question/user/answerQuestions");
			result.addObject("questions", questions);
			result.addObject("message", messageCode);
			return result;
		}
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list(@RequestParam final int rendezvousId) {

			ModelAndView result;
			User user;
			user = this.userService.findByPrincipal();
			Collection<Question> questions;

			questions = questionService.findAllByPrincipalAndRendezvous(user.getId(), rendezvousId);

			result = new ModelAndView("question/list");
			result.addObject("questions", questions);
			result.addObject("requestURI", "question/user/list.do");

			return result;
		}
		
		@RequestMapping(value = "/answers", method = RequestMethod.GET)
		public ModelAndView answers(@RequestParam final int questionId) {

			ModelAndView result;
			Question question = this.questionService.findOne(questionId);

			result = new ModelAndView("question/answers");
			result.addObject("question", question);
			result.addObject("requestURI", "question/user/answers.do");

			return result;
		}

}
