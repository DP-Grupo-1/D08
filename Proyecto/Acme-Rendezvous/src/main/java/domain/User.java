
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
	private Collection<Reply>					replies;
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
<<<<<<< HEAD
	public Collection<Reply> getReplies() {
		return this.replies;
=======
	@Valid
	public Reply getReply() {
		return this.reply;
>>>>>>> c9627f0e25e9d8a8a905503162178b0a9e6e284d
	}

	public void setReplies(final Collection<Reply> replies) {
		this.replies = replies;
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
