package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


		// Constructors -----------------------------------------------------------

		public QuestionService() {
			super();
		}

		// Simple CRUD methods ----------------------------------------------------
		
		
		public Question create(int rendezvousId){
			Question result;
			result = new Question();
			Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
			Collection<User> answerers = new ArrayList<User>();
			Collection<String> answers = new ArrayList<String>();
			User principal = this.userService.findByPrincipal();
			result.setAnswerers(answerers);
			result.setAnswers(answers);
			result.setCreator(principal);
			result.setRendezvous(rendezvous);
			
			return result;
		}
		
		public Question findOne(int questionId){
			Question question = this.questionRepository.findOne(questionId);
			return question;
		}
		
		public Collection<Question> saveQuestions(Collection<Question> questions){
			Collection<Question> saved = new ArrayList<Question>();
			for(Question question:questions){
				Question savedQuestion = this.questionRepository.save(question);
				saved.add(savedQuestion);
			}
			
			return saved;
		}
		
		public Question saveQuestion(Question question){
			
			Question saved = this.questionRepository.save(question);
			return saved;
		}
		
		public Collection<Question> findAllByPrincipalAndRendezvous(int principalId, int rendezvousId){
			return this.questionRepository.findAllByPrincipalAndRendezvous(principalId, rendezvousId);
		}

		public Collection<Question> findAllByrendezvous(int rendezvousId) {
			return this.questionRepository.findAllByRendezvous(rendezvousId);
		}
		
		
}
