
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Answer extends DomainEntity {

	


	// Attributes -------------------------------------------------------------

	private String	answer;


	@NotBlank
	public String getAnswer() {
		return this.answer;
	}
	public void setAnswer(final String answer) {
		this.answer = answer;
	}


	// Relationships ----------------------------------------------------------

	private User	answerer;


	@Valid
	@NotNull
	@OneToOne(optional = false)
	public User getAnswerer() {
		return this.answerer;
	}
	public void setAnswerer(final User answerer) {
		this.answerer = answerer;
	}

}
