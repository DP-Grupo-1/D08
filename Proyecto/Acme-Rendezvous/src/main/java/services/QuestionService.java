package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
	private QuestionRepository			questionRepository;

	@Autowired
	private RendezvousService		rendezvousService;
	@Autowired
	private UserService		userService;

	@Autowired
	private AdministratorService		administratorService;


	// Constructors -----------------------------------------------------------

	public QuestionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------


	public Question create(final int rendezvousId){
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

	public Question findOne(final int questionId){
		final Question question = this.questionRepository.findOne(questionId);
		Assert.isTrue(question.getId()!=0);
		return question;
	}

	public Question save(final Question question){

		final Question saved = this.questionRepository.save(question);
		return saved;
	}

	public void deleteByAdmin(final Question question) {

		Assert.notNull(question);

		final Administrator administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		this.questionRepository.delete(question);
	}

	public Collection<Question> findAllByPrincipalAndRendezvous(final int principalId, final int rendezvousId){
		return this.questionRepository.findAllByPrincipalAndRendezvous(principalId, rendezvousId);
	}

	public Collection<Question> findAllByrendezvous(final int rendezvousId) {
		return this.questionRepository.findAllByRendezvous(rendezvousId);
	}


}
