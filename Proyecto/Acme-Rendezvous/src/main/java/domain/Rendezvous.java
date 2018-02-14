
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.User;

@Entity
@Access(AccessType.PROPERTY)
public class Rendezvous extends DomainEntity {

	//-------------------------------------Atributos--------------------------------------------

	private String						name, description;
	private Date						moment;
	private String						picture;
	private Double						locationLatitude, locationLongitude;
	private boolean						finalMode, adultOnly;

	//-------------------------------------Relaciones-------------------------------------------

	private Collection<Announcement>	announcements;
	private Collection<Question>		questions;								// No está usando nuestro dominio Question
	private Collection<Rendezvous>		rendezvouses;
	private Collection<RSVP>			rsvps;
	private Collection<Comment>			comments;
	private User						creator;
	private Collection<User>			attendants;


	//-----------------------------Getters y Setters Atributos----------------------------------

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Future
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public Double getLocationLatitude() {
		return this.locationLatitude;
	}

	public void setLocationLatitude(final Double locationLatitude) {
		this.locationLatitude = locationLatitude;
	}

	public Double getLocationLongitude() {
		return this.locationLongitude;
	}

	public void setLocationLongitude(final Double locationLongitude) {
		this.locationLongitude = locationLongitude;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public User getCreator() {
		return this.creator;
	}

	public void setCreator(final User creator) {
		this.creator = creator;
	}

	@Valid
	@NotNull
	@ManyToMany(mappedBy = "rendezvouses")
	public Collection<User> getAttendants() {
		return this.attendants;
	}

	public void setAttendants(final Collection<User> attendants) {
		this.attendants = attendants;
	}

	public boolean getFinalMode() {
		return this.finalMode;
	}

	public void setFinalMode(final boolean finalMode) {
		this.finalMode = finalMode;
	}

	public boolean getAdultOnly() {
		return this.adultOnly;
	}

	public void setAdultOnly(final boolean adultOnly) {
		this.adultOnly = adultOnly;
	}

	//-----------------------------Getters y Setters Relaciones---------------------------------

	@OneToMany
	@NotNull
	@Valid
	public Collection<Announcement> getAnnouncements() {
		return this.announcements;
	}

	public void setAnnouncements(final Collection<Announcement> announcements) {
		this.announcements = announcements;
	}

	@OneToMany
	@NotNull
	@Valid
	public Collection<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(final Collection<Question> questions) {
		this.questions = questions;
	}

	@OneToMany
	@NotNull
	@Valid
	public Collection<Rendezvous> getRendezvouses() {
		return this.rendezvouses;
	}

	public void setRendezvouses(final Collection<Rendezvous> rendezvouses) {
		this.rendezvouses = rendezvouses;
	}

	@OneToMany
	@NotNull
	@Valid
	public Collection<RSVP> getRsvps() {
		return this.rsvps;
	}

	public void setRSVP(final Collection<RSVP> rsvps) {
		this.rsvps = rsvps;
	}

	@OneToMany
	@NotNull
	@Valid
	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComment(final Collection<Comment> comments) {
		this.comments = comments;
	}
}
