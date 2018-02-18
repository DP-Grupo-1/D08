
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RendezvousRepository;
import domain.Administrator;
import domain.Comment;
import domain.Flag;
import domain.Question;
import domain.RSVP;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class RendezvousService {

	// Managed repository -----------------------------------------

	@Autowired
	private RendezvousRepository	rendezvousRepository;

	// Supporting services ------------------------------------------

	@Autowired
	private UserService				userService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private RSVPService				rsvpService;


	// Simple CRUD methods ------------------------------------------

	public Rendezvous create() {

		final Rendezvous result = new Rendezvous();

		final Collection<User> attendants = new ArrayList<User>();
		final Collection<Announcement> announcements = new ArrayList<Announcement>();
		final Collection<Question> questions = new ArrayList<Question>();
		final Collection<Rendezvous> rendezvouses = new ArrayList<Rendezvous>();
		final Collection<RSVP> rsvps = new ArrayList<RSVP>();
		final Collection<Comment> comments = new ArrayList<Comment>();

		result.setAttendants(attendants);
		result.setAnnouncements(announcements);
		result.setQuestions(questions);
		result.setRendezvouses(rendezvouses);
		result.setRSVP(rsvps);
		result.setComment(comments);
		result.setFinalMode(false);
		result.setAdultOnly(false);
		result.setFlag(Flag.ACTIVE);

		return result;
	}

	public Rendezvous save(final Rendezvous rendezvous) {

		Assert.notNull(rendezvous);
		Rendezvous result;

		final User user = this.userService.findByPrincipal();
		Assert.notNull(user);

		Assert.isTrue(rendezvous.getFinalMode() == false);
		Assert.isTrue(rendezvous.getFlag() != Flag.DELETED);

		if (rendezvous.getId() == 0) {
			result = this.rendezvousRepository.save(rendezvous);
			result.setCreator(user);
			this.rsvpService.create(result.getId());
			user.getRendezvouses().add(result);
		} else
			result = this.rendezvousRepository.save(rendezvous);

		return result;
	}

	public void deleteByUser(final Rendezvous rendezvous) {

		Assert.notNull(rendezvous);
		Assert.notNull(this.rendezvousRepository.findOne(rendezvous.getId()));

		final User user = this.userService.findByPrincipal();
		Assert.notNull(user);

		Assert.isTrue(rendezvous.getFinalMode() == false);
		Assert.isTrue(rendezvous.getFlag() != Flag.DELETED);
		rendezvous.setFlag(Flag.DELETED);
	}

	public void deleteByAdmin(final Rendezvous rendezvous) {

		Assert.notNull(rendezvous);
		Assert.notNull(this.rendezvousRepository.findOne(rendezvous.getId()));

		final Administrator admin = this.administratorService.findByPrincipal();
		Assert.notNull(admin);

		for (final Rendezvous r : this.rendezvousRepository.findAll())
			r.getRendezvouses().remove(rendezvous);
		for (final RSVP rs : rendezvous.getRsvps())
			this.rsvpService.delete(rs);
		rendezvous.getCreator().getRendezvouses().remove(rendezvous);
		this.rendezvousRepository.delete(rendezvous);
	}

	public Collection<Rendezvous> findAll() {
		final Collection<Rendezvous> result = this.rendezvousRepository.findAll();
		return result;
	}

	public Rendezvous findOne(final int rendezvousId) {
		final Rendezvous result = this.rendezvousRepository.findOne(rendezvousId);
		return result;
	}

	// Other business methods ----------------------------------

	public Collection<Rendezvous> findByUserId(final int userId) {
		Collection<Rendezvous> result;
		result = this.rendezvousRepository.findByUserId(userId);
		return result;
	}

	public Double[] avgStddevRendezvousPerUser() {
		final Double[] result = this.rendezvousRepository.avgStddevRendezvousPerUser();
		return result;
	}

	public Double ratioUserWithRendezvous() {
		final Double result = this.rendezvousRepository.ratioUserWithRendezvous();
		return result;
	}

	public Double[] avgStddevUsersPerRendezvous() {
		final Double[] result = this.rendezvousRepository.avgStddevUsersPerRendezvous();
		return result;
	}

	public Double[] avgStddevRSVPsPerUser() {
		final Double[] result = this.rendezvousRepository.avgStddevRSVPsPerUser();
		return result;
	}

	public Collection<Rendezvous> top10RendezvousesByRSVPs() {
		final Collection<Rendezvous> result = this.rendezvousRepository.top10RendezvousesByRSVPs();
		return result;
	}

}
