package forms;

import java.util.Collection;

import domain.Question;

public class AnswerQuestions {

	public AnswerQuestions() {
		super();
	}


	private Collection<Question>	questions;
	private Collection<String>	answers;
	
	public Collection<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Collection<Question> questions) {
		this.questions = questions;
	}
	public Collection<String> getAnswers() {
		return answers;
	}
	public void setAnswers(Collection<String> answers) {
		this.answers = answers;
	}

}
