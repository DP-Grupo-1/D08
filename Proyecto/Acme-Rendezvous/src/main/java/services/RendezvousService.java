
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RendezvousRepository;
import domain.Administrator;
import domain.Comment;
import domain.Flag;
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
		final User user = this.userService.findByPrincipal();
		final Collection<User> attendants = new ArrayList<User>();
		final Collection<Rendezvous> rendezvouses = new ArrayList<Rendezvous>();
		final Collection<Comment> comments = new ArrayList<Comment>();

		result.setAttendants(attendants);
		result.setRendezvouses(rendezvouses);
		result.setComments(comments);
		result.setFinalMode(false);
		result.setAdultOnly(false);
		result.setCreator(user);
		result.setFlag(Flag.ACTIVE);

		return result;
	}

	public Rendezvous save(final Rendezvous rendezvous) {

		Assert.notNull(rendezvous);
		Rendezvous result;

		final User user = this.userService.findByPrincipal();
		Assert.notNull(user);

		//		Assert.isTrue(rendezvous.getFinalMode() == false);
		Assert.isTrue(rendezvous.getFlag() != Flag.DELETED);

		if (rendezvous.getId() == 0) {

			result = this.rendezvousRepository.save(rendezvous);

			//			result.setCreator(user);
			this.rsvpService.create(result.getId());
			result.getAttendants().add(user);

			this.findByCreatorId(user.getId()).add(result);
		} else

			result = this.rendezvousRepository.save(rendezvous);

		return result;
	}

	public void deleteByUser(final Rendezvous rendezvous) {

		Assert.notNull(rendezvous);
		Assert.notNull(this.findOne(rendezvous.getId()));

		final User user = this.userService.findByPrincipal();
		Assert.notNull(user);

		Assert.isTrue(rendezvous.getFinalMode() == false);
		Assert.isTrue(rendezvous.getFlag() != Flag.DELETED);
		rendezvous.setFlag(Flag.DELETED);
	}

	public void deleteByAdmin(final Rendezvous rendezvous) {

		Assert.notNull(rendezvous);
		Assert.notNull(this.findOne(rendezvous.getId()));

		final Administrator admin = this.administratorService.findByPrincipal();
		Assert.notNull(admin);

		final Collection<Rendezvous> rendezvouses = this.findRendezvousParents(rendezvous.getId());
		for (final Rendezvous r : rendezvouses)
			r.getRendezvouses().remove(rendezvous);

		final Collection<RSVP> rsvps = this.findRSVPs(rendezvous.getId());
		for (final RSVP rsvp : rsvps)
			this.rsvpService.delete(rsvp);

		this.rendezvousRepository.delete(rendezvous);
	}

	public Collection<Rendezvous> findAll() {
		final Collection<Rendezvous> result = this.rendezvousRepository.findAll();

		for (final Rendezvous r : result)
			if (r.getMoment().before(new Date()) && r.getFlag() == Flag.ACTIVE)
				r.setFlag(Flag.PASSED);

		return result;
	}

	public Rendezvous findOne(final int rendezvousId) {
		final Rendezvous result = this.rendezvousRepository.findOne(rendezvousId);

		if (result.getMoment().before(new Date()) && result.getFlag() == Flag.ACTIVE)
			result.setFlag(Flag.PASSED);

		return result;
	}

	// Other business methods ----------------------------------

	public Collection<Rendezvous> findByUserId(final int userId) {
		Collection<Rendezvous> result;
		result = this.rendezvousRepository.findByUserId(userId);

		for (final Rendezvous r : result)
			if (r.getMoment().before(new Date()) && r.getFlag() == Flag.ACTIVE)
				r.setFlag(Flag.PASSED);

		return result;
	}
	public Collection<Rendezvous> findByCreatorId(final int userId) {
		final Collection<Rendezvous> res = this.rendezvousRepository.findByCreatorId(userId);
		for (final Rendezvous r : res)
			if (r.getMoment().before(new Date()) && r.getFlag() == Flag.ACTIVE)
				r.setFlag(Flag.PASSED);

		return res;
	}

	//	public Double[] avgStddevRendezvousPerUser() {
	//		final Double[] result = this.rendezvousRepository.avgStddevRendezvousPerUser();
	//		return result;
	//	}
	//
	//	public Double ratioUserWithRendezvous() {
	//		final Double result = this.rendezvousRepository.ratioUserWithRendezvous();
	//		return result;
	//	}

	public Double[] avgStddevUsersPerRendezvous() {
		final Double[] result = this.rendezvousRepository.avgStddevUsersPerRendezvous();
		return result;
	}

	public Double[] avgStddevRSVPsPerUser() {
		final Double[] result = this.rendezvousRepository.avgStddevRSVPsPerUser();
		return result;
	}

	//	public Collection<Rendezvous> top10RendezvousesByRSVPs() {
	//		final Collection<Rendezvous> result;
	//		result = this.rendezvousRepository.top10RendezvousesByRSVPs();
	//		return result;
	//	}

	//	private Collection<Rendezvous> above75AverageOfAnnouncementsPerRendezvous() {
	//		final Collection<Rendezvous> result;
	//		result = this.rendezvousRepository.above75AverageOfAnnouncementsPerRendezvous();
	//		return result;
	//	}

	//	private Collection<Rendezvous> LinkedGreaterAveragePlus10() {
	//		final Collection<Rendezvous> result; 
	//		result = this.rendezvousRepository.linkedGreaterAveragePlus10();
	//		return result;
	//	}

	private Collection<Rendezvous> findRendezvousParents(final int id) {
		final Collection<Rendezvous> result;
		result = this.rendezvousRepository.findRendezvousParents(id);
		return result;
	}

	private Collection<RSVP> findRSVPs(final int id) {
		final Collection<RSVP> result = this.rendezvousRepository.findRSVPs(id);
		return result;
	}

	//COMMENT

	public Collection<Comment> findByRendezvous(final Integer rendezvousId) {
		final Collection<Comment> comments = new ArrayList<Comment>();
		final Rendezvous rendezvous = this.rendezvousRepository.findOne(rendezvousId);
		Assert.notNull(rendezvous);
		comments.addAll(rendezvous.getComments());
		return comments;
	}

}
