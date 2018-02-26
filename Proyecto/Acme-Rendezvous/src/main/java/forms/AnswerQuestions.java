package forms;

import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import domain.Question;

public class AnswerQuestions {

	public AnswerQuestions() {
		super();
	}


	private Collection<Question>	questions;
	private Collection<String>	answers;
	
	@ElementCollection
	public Collection<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Collection<Question> questions) {
		this.questions = questions;
	}
	@NotEmpty
	@ElementCollection
	public Collection<String> getAnswers() {
		return answers;
	}
	public void setAnswers(Collection<String> answers) {
		this.answers = answers;
	}

}
