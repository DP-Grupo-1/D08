
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	//Relationships-----------------------------------------------------------
	private Collection<Question>	questions;
	private Collection<Reply>					replies;
	private Collection<Comment>		comments;
	private Collection<Rendezvous>	rendezvouses;
	private Collection<RSVP>		rsvps;


	@OneToMany
	public Collection<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(final Collection<Question> questions) {
		this.questions = questions;
	}

	@OneToMany
	public Collection<Reply> getReplies() {
		return this.replies;
	}

	public void setReplies(final Collection<Reply> replies) {
		this.replies = replies;
	}

	@OneToMany
	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}
	@OneToMany
	public Collection<Rendezvous> getRendezvouses() {
		return this.rendezvouses;
	}

	public void setRendezvouses(final Collection<Rendezvous> rendezvouses) {
		this.rendezvouses = rendezvouses;
	}
	@OneToMany(mappedBy = "user")
	public Collection<RSVP> getRsvps() {
		return this.rsvps;
	}

	public void setRsvps(final Collection<RSVP> rsvps) {
		this.rsvps = rsvps;
	}

}
