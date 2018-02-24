package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;
import domain.User;
import repositories.QuestionRepository;

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
		
		
		public Question create(int rendezvousId){
			Question result;
			result = new Question();
			Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
			Collection<Answer> answers = new ArrayList<Answer>();
			User principal = this.userService.findByPrincipal();
			result.setAnswers(answers);
			result.setCreator(principal);
			result.setRendezvous(rendezvous);
			
			return result;
		}
		
		public Question findOne(int questionId){
			Question question = this.questionRepository.findOne(questionId);
			return question;
		}
		
		public Question save(Question question){
			
			Question saved = this.questionRepository.save(question);
			return saved;
		}
		
		public void deleteByAdmin(final Question question) {
			
			Assert.notNull(question);
			final Administrator administrator = this.administratorService.findByPrincipal();
			Assert.notNull(administrator);
			this.questionRepository.delete(question);
		}
		
		public Collection<Question> findAllByPrincipalAndRendezvous(int principalId, int rendezvousId){
			return this.questionRepository.findAllByPrincipalAndRendezvous(principalId, rendezvousId);
		}

		public Collection<Question> findAllByrendezvous(int rendezvousId) {
			return this.questionRepository.findAllByRendezvous(rendezvousId);
		}
		
		
}
