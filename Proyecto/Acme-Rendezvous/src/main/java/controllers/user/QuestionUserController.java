package controllers.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnswerService;
import services.QuestionService;
import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;
import domain.User;
import forms.AnswerQuestions;

@Controller
@RequestMapping("/question/user")
public class QuestionUserController extends AbstractController {

	@Autowired
	private QuestionService		questionService;

	@Autowired
	private AnswerService		answerService;

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private UserService			userService;


	//Creation--------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer rendezvousId) {
		ModelAndView result;
		Question question;
		question = this.questionService.create(rendezvousId);

		result = this.createEditModelAndViewQuestion(question);
		result.addObject("requestURI", "question/user/edit.do?rendezvousId=" + rendezvousId);

		return result;
	}

	//Edit----------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editQuestion(@RequestParam final int questionId) {
		final ModelAndView res;
		final Question question = this.questionService.findOne(questionId);
		final User principal = this.userService.findByPrincipal();
		Assert.isTrue(question.getRendezvous().getCreator().equals(principal));
		res = this.createEditModelAndViewQuestion(question);
		return res;
	}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView saveQuestion(@Valid final Question question, final BindingResult binding) {

			ModelAndView result;

			if (binding.hasErrors())
				result = this.createEditModelAndViewQuestion(question);
			else

				try {
					
						this.questionService.save(question);
						result = new ModelAndView("redirect:/welcome/index.do");
					
				}

				catch (final Throwable oops) {
					result = this.createEditModelAndViewQuestion(question, "question.comit.error");
				}

			return result;
		}
		
		//Edit----------------------------------------------------------------------
		@RequestMapping(value = "/answerQuestions", method = RequestMethod.GET)
		public ModelAndView answerQuestions(@RequestParam final int rendezvousId) {

			ModelAndView result;
			Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
			Collection<Question> questions = questionService.findAllByRendezvous(rendezvousId);
			User principal = this.userService.findByPrincipal();
			Assert.isTrue(!(rendezvous.getCreator().equals(principal)));
			List<Answer> answers = new ArrayList<Answer>();
			for(int i=0;i<questions.size();i++){
				Answer ans = new Answer();
				ans.setAnswerer(principal);
				answers.add(ans);
			}
			AnswerQuestions answerQuestions = new AnswerQuestions();
			answerQuestions.setQuestions(questions);
			answerQuestions.setAnswers(answers);

			result = this.createEditModelAndViewAnswer(answerQuestions);
			result.addObject("answerQuestions", answerQuestions);
			result.addObject("requestURI", "question/user/answerQuestions.do?rendezvousId=" + rendezvousId);

		return result;
	}

		@RequestMapping(value = "/answerQuestions", method = RequestMethod.POST, params = "save")
		public ModelAndView answerQuestions(@Valid AnswerQuestions answerQuestions, BindingResult binding) {

			ModelAndView result;

			if (binding.hasErrors())
				result = this.createEditModelAndViewAnswer(answerQuestions);
			else

				try {
						for(Answer s : answerQuestions.getAnswers()){
							Assert.notNull(s.getWritten(), "You must answer every question");
						}
						this.answerService.saveAll(answerQuestions.getAnswers(), answerQuestions.getQuestions());
						result = new ModelAndView("redirect:/welcome/index.do");
					
				}

				catch (final Throwable oops) {
					result = this.createEditModelAndViewAnswer(answerQuestions, "question.comit.error");
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

			result = new ModelAndView("question/edit");
			result.addObject("question", question);
			result.addObject("message", messageCode);
			return result;
		}
		
		protected ModelAndView createEditModelAndViewAnswer(final AnswerQuestions answerQuestions) {
			ModelAndView result;

			result = this.createEditModelAndViewAnswer(answerQuestions, null);
			return result;
		}

		protected ModelAndView createEditModelAndViewAnswer(final AnswerQuestions answerQuestions, final String messageCode) {
			ModelAndView result;

			result = new ModelAndView("question/user/answerQuestions");
			result.addObject("answerQuestions", answerQuestions);
			result.addObject("message", messageCode);
			return result;
		}

	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int rendezvousId) {

		ModelAndView result;
		User user;
		user = this.userService.findByPrincipal();
		Collection<Question> questions;

		questions = this.questionService.findAllByPrincipalAndRendezvous(user.getId(), rendezvousId);
		final User principal = this.userService.findByPrincipal();
		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		Assert.isTrue(rendezvous.getCreator().equals(principal));

		result = new ModelAndView("question/list");
		result.addObject("questions", questions);
		result.addObject("requestURI", "question/user/list.do");

		return result;
	}

	@RequestMapping(value = "/answers", method = RequestMethod.GET)
	public ModelAndView answers(@RequestParam final int questionId) {

		ModelAndView result;
		final Question question = this.questionService.findOne(questionId);
		final User principal = this.userService.findByPrincipal();
		Assert.isTrue(question.getRendezvous().getCreator().equals(principal));

		result = new ModelAndView("question/answers");
		result.addObject("answers", question.getAnswers());
		result.addObject("requestURI", "question/user/answers.do");

		return result;
	}

}
