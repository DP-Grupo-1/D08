
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
<<<<<<< HEAD
	private Collection<Reply>	replies;
	private Collection<Comment>	comments;

	private Collection<RSVP>	rsvps;
=======

	private Collection<Reply>	replies;
	private Collection<Comment>	comments;
>>>>>>> 6ca36bcb6c39306a54b3f9239de03094014a376f

	private Collection<RSVP>	rsvps;

<<<<<<< HEAD
=======

>>>>>>> 6ca36bcb6c39306a54b3f9239de03094014a376f
	@OneToMany
	public Collection<Reply> getReplies() {
		return this.replies;
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
