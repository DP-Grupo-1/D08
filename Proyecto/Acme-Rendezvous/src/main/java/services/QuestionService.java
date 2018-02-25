
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.QuestionRepository;
import domain.Administrator;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class QuestionService {

	// Managed repository -----------------------------------------------------

	@Autowired
<<<<<<< HEAD
	private QuestionRepository		questionRepository;
=======
	private QuestionRepository			questionRepository;
>>>>>>> c25f182381200f18a6cd17075b670a7471dba377

	@Autowired
	private RendezvousService		rendezvousService;
	@Autowired
<<<<<<< HEAD
	private UserService				userService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private Validator				validator;
=======
	private UserService		userService;

	@Autowired
	private AdministratorService		administratorService;
>>>>>>> c25f182381200f18a6cd17075b670a7471dba377


	// Constructors -----------------------------------------------------------

	public QuestionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

<<<<<<< HEAD
	public Question create(final int rendezvousId) {
=======

	public Question create(final int rendezvousId){
>>>>>>> c25f182381200f18a6cd17075b670a7471dba377
		Question result;
		result = new Question();
		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		final Collection<Answer> answers = new ArrayList<Answer>();
		final User principal = this.userService.findByPrincipal();
		Assert.isTrue(principal.equals(rendezvous.getCreator()));
		result.setAnswers(answers);
		result.setCreator(principal);
		result.setRendezvous(rendezvous);

		return result;
	}

<<<<<<< HEAD
	public Question findOne(final int questionId) {
		final Question question = this.questionRepository.findOne(questionId);
		Assert.isTrue(question.getId() != 0);
		return question;
	}

	public Question save(final Question question) {
=======
	public Question findOne(final int questionId){
		final Question question = this.questionRepository.findOne(questionId);
		Assert.isTrue(question.getId()!=0);
		return question;
	}

	public Question save(final Question question){
>>>>>>> c25f182381200f18a6cd17075b670a7471dba377

		final Question saved = this.questionRepository.save(question);
		return saved;
	}

	public void deleteByAdmin(final Question question) {

		Assert.notNull(question);
<<<<<<< HEAD
		final Administrator administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);
		this.questionRepository.delete(question);
	}

	public Collection<Question> findAllByPrincipalAndRendezvous(final int principalId, final int rendezvousId) {
=======

		final Administrator administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		this.questionRepository.delete(question);
	}

	public Collection<Question> findAllByPrincipalAndRendezvous(final int principalId, final int rendezvousId){
>>>>>>> c25f182381200f18a6cd17075b670a7471dba377
		return this.questionRepository.findAllByPrincipalAndRendezvous(principalId, rendezvousId);
	}

	public Collection<Question> findAllByrendezvous(final int rendezvousId) {
		return this.questionRepository.findAllByRendezvous(rendezvousId);
	}
<<<<<<< HEAD
	//Prune domain object------------------------------------------------------------
	public Question reconstruct(final Question question, final BindingResult binding) {
		Question res;
		if (question.getId() == 0)
			res = question;
		else {
			res = this.questionRepository.findOne(question.getId());
			res.setQuestionToAnswer(question.getQuestionToAnswer());
			this.validator.validate(res, binding);
		}
		return res;
	}
=======

>>>>>>> c25f182381200f18a6cd17075b670a7471dba377

}
