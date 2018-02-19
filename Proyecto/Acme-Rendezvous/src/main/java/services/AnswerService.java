
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AnswerRepository;
import domain.Answer;
import domain.Question;
import domain.User;

@Service
@Transactional
public class AnswerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AnswerRepository	answerRepository;

	@Autowired
	private QuestionService		questionService;
	@Autowired
	private UserService			userService;


	// Constructors -----------------------------------------------------------

	public AnswerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Answer create(final int questionId) {
		final Answer result;
		result = new Answer();
		return result;
	}

	public Question findOne(final int questionId) {
		final Question question = this.questionRepository.findOne(questionId);
		return question;
	}

	public Answer save(final Answer answer, final Question question) {
		final User principal = this.userService.findByPrincipal();
		answer.setAnswerer(principal);
		final Answer saved = this.answerRepository.save(answer);
		final Collection<Answer> answers = question.getAnswers();
		answers.add(answer);
		question.setAnswers(answers);
		this.questionService.saveQuestion(question);
		return saved;
	}

	public Question saveQuestion(final Question question) {

		final Question saved = this.questionRepository.save(question);
		return saved;
	}

	public Collection<Question> findAllByPrincipalAndRendezvous(final int principalId, final int rendezvousId) {
		return this.questionRepository.findAllByPrincipalAndRendezvous(principalId, rendezvousId);
	}

	public Collection<Question> findAllByrendezvous(final int rendezvousId) {
		return this.questionRepository.findAllByRendezvous(rendezvousId);
	}

}
