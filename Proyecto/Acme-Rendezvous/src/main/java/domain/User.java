
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	//Relationships-----------------------------------------------------------
	private Collection<Question>	questions;
	private Reply					reply;
	private Collection<Comment>		comments;
	private Collection<Rendezvous>	rendezvouses;
	private Collection<RSVP>		rsvps;


	@Valid
	@OneToMany
	@NotNull
	public Collection<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(final Collection<Question> questions) {
		this.questions = questions;
	}

	@OneToMany
	@Valid
	public Reply getReply() {
		return this.reply;
	}

	public void setReply(final Reply reply) {
		this.reply = reply;
	}

	@OneToMany
	@Valid
	@NotNull
	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}
	@OneToMany
	@Valid
	@NotNull
	public Collection<Rendezvous> getRendezvouses() {
		return this.rendezvouses;
	}

	public void setRendezvouses(final Collection<Rendezvous> rendezvouses) {
		this.rendezvouses = rendezvouses;
	}
	@Valid
	@OneToMany(mappedBy = "user")
	@NotNull
	public Collection<RSVP> getRsvps() {
		return this.rsvps;
	}

	public void setRsvps(final Collection<RSVP> rsvps) {
		this.rsvps = rsvps;
	}

}
