
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RSVPRepository;
import domain.RSVP;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class RSVPService {

	//Managed Repository

	@Autowired
	private RSVPRepository		RSVPRepository;
	//Supporting services---------------------------------------------------------------
	@Autowired
	private UserService			userService;

	@Autowired
	private RendezvousService	rendezvousService;


	//Simple CRUD methods------------------------------------------------------------------

	public RSVP create(final int rendezvousId) {

		final RSVP res = new RSVP();
		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		final User user = this.userService.findByPrincipal();
		Assert.notNull(user);
		res.setUser(user);

		res.setRendezvous(rendezvous);

		return res;
	}

	public RSVP save(final RSVP rsvp) {
		Assert.notNull(rsvp);
		RSVP res;
		final RSVP aux = rsvp;
		final Rendezvous rendezvous = this.rendezvousService.findOne(aux.getRendezvous().getId());
		//Iniciaciones
		final User user = this.userService.findByPrincipal();
		Assert.notNull(user);
		if (this.userService.hasUserRSVP(rendezvous.getId()) == false) {
			aux.setRendezvous(rendezvous);
			final Collection<User> attendants = rendezvous.getAttendants();
			attendants.add(user);
			aux.setUser(user);
		}

		res = this.RSVPRepository.save(aux);

		return res;
	}

	public void delete(final RSVP rsvp) {

		Assert.notNull(rsvp);
		Assert.isTrue(this.RSVPRepository.exists(rsvp.getId()));

		final User user = rsvp.getUser();
		user.getRsvps().remove(rsvp);
		rsvp.getRendezvous().getAttendants().remove(user);
		this.RSVPRepository.delete(rsvp);
	}

	public Collection<RSVP> findAll() {
		final Collection<RSVP> res = this.RSVPRepository.findAll();
		return res;

	}
	public RSVP findOne(final int rspvId) {
		final RSVP res = this.RSVPRepository.findOne(rspvId);
		return res;
	}
	//Añadimos RSVP. 
	public void addRSVP(final int rendezvousId) {
		Assert.notNull(rendezvousId);
		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		//Buscamos el usuario logueado, y creamos un nuevo RSVP
		final User user = this.userService.findByPrincipal();
		final RSVP rsvp = this.create(rendezvousId);
		final RSVP save = this.save(rsvp);

		//Añadimos el usuario a la lista de atendientes, y el rsvp a la lista de rsvps del usuario en cuestión.
		Assert.notNull(user);
		rendezvous.getAttendants().add(user);
		user.getRsvps().add(save);

	}
}
