package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity{
	
	// Constructors -----------------------------------------------------------

		public Question() {
			super();
		}


		// Attributes -------------------------------------------------------------

		private String	question;
		private Collection<String>	answers;


		@NotBlank
		public String getQuestion() {
			return this.question;
		}
		public void setQuestion(final String question) {
			this.question = question;
		}
		
		@NotNull
		public Collection<String> getAnswers() {
			return this.answers;
		}
		public void setAnswers(final Collection<String> answers) {
			this.answers = answers;
		}

		

		// Relationships ----------------------------------------------------------

		private User				creator;
		private Collection<User>	answerers;
		private Rendezvous 			rendezvous;

		@Valid
		@NotNull
		@ManyToOne(optional = false)
		public User getCreator() {
			return this.creator;
		}
		public void setCreator(final User creator) {
			this.creator = creator;
		}

		@Valid
		@ManyToMany(cascade = CascadeType.ALL)
		@NotNull
		public Collection<User> getAnswerers() {
			return this.answerers;
		}
		public void setAnswerers(final Collection<User> answerers) {
			this.answerers = answerers;
		}
		
		@Valid
		@ManyToOne(optional = false)
		@NotNull
		public Rendezvous getRendezvous() {
			return rendezvous;
		}
		public void setRendezvous(Rendezvous rendezvous) {
			this.rendezvous = rendezvous;
		}


}
