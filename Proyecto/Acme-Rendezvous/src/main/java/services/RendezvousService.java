
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RendezvousRepository;
import security.LoginService;
import domain.Manager;
import domain.Rendezvous;
import domain.Tag;
import domain.Trip;

@Service
@Transactional
public class RendezvousService {

	// Managed repository -----------------------------------------

	@Autowired
	private RendezvousRepository	rendezvousRepository;

	// Supporting services ------------------------------------------

	@Autowired
	private UserRepository			userService;

	@Autowired
	private AdministratorRepository	administratorService;

	@Autowired
	private RSVPRepository			rsvpService;


	// Simple CRUD methods ------------------------------------------

	public Rendezvous create() {

		final Rendezvous result = new Rendezvous();

		final User user = this.userService.findByPrincipal();
		Assert.notNull(user);

		final Collection<User> attendants = new ArrayList<String>();
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

		return result;
	}

	public Rendezvous save(final Rendezvous rendezvous) {

		Assert.notNull(rendezvous);
		final Rendezvous result;

		final User user = this.userService.findByPrincipal();
		Assert.notNull(user);

		final Date moment = new Date(System.currentTimeMillis() - 1000);
		Assert.isTrue(rendezvous.getMoment().after(moment));

		Assert.isTrue(rendezvous.getFinalMode() == false);

		if (rendezvous.getId() == 0) {
			result.setCreator(user);
			result.getAttendants().add(user);
			final RSVP rs = this.rsvpService.create(result);
			result.getRsvps().add(rs);
			result = this.rendezvousRepository.save(rendezvous);
			user.getRendezvouses().add(result);
		} else
			result = this.rendezvousRepository.save(rendezvous);

		return result;
	}

	public void delete(final Rendezvous rendezvous) {
		
		Assert.notNull(rendezvous);
		Assert.notNull(this.rendezvousRepository.findOne(rendezvous.getId()));
		
		Assert.isTrue(rendezvous.getFinalMode() == false);
		
		if(user==null){
			final Collection<User> users = this.userService.findAll();
			for(final User u : users){
				u.getRendezvouses().remove(rendezvous);
				final Collection<RSVP> rsvps = u.getRsvps();
				for(final RSVP rs : rsvps)
					rs.getRendezvous()
			}
			this.rendezvousRepository.delete(rendezvous);
		}else{
			// La idea es que se marque la reunión como "eliminada" y que no se pueda actualizar
		}
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
