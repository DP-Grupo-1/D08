package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Question;
import repositories.QuestionRepository;

@Service
@Transactional
public class QuestionService {

	// Managed repository -----------------------------------------------------

		@Autowired
		private QuestionRepository			QuestionRepository;
		
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
		}
		
		public Question findOne(int questionId){
			Question question = this.QuestionRepository.findOne(questionId);
			return question;
		}
		
		public Question saveQuestion(Question question){
			Question saved = this.QuestionRepository.save(question);
			return saved;
		}
		
		public Question saveAnswer(String answer, Question question){
			Collection<String> answers = question.getAnswers();
			answers.add(answer);
			question.setAnswers(answers);
			Question saved = this.QuestionRepository.save(question);
			return saved;
		}
		
		public Collection<Question> findAllByPrincipalAndRendezvous(int principalId, int rendezvousId){
			return this.QuestionRepository.findAllByPrincipalAndRendezvous(principalId, rendezvousId);
		}
		
		
}
