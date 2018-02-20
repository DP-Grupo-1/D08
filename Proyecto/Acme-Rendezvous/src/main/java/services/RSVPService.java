package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.RSVP;
import domain.Rendezvous;
import domain.User;

import repositories.RSVPRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class RSVPService{

	
	//Managed Repository
	
	@Autowired
	private RSVPRepository RSVPRepository;
	//Supporting services---------------------------------------------------------------
	@Autowired
	private UserService userService;
	
	@Autowired
	private RendezvousService rendezvousService;
	

	
	
	//Simple CRUD methods------------------------------------------------------------------
	
	public RSVP create(final int rendezvousId) {
		
		final RSVP res = new RSVP();
		Rendezvous rendezvous = rendezvousService.findOne(rendezvousId);
		User user = this.userService.findByPrincipal();
		res.setUser(user);

		res.setRendezvous(rendezvous);

		
		return res;
	}
	
	public RSVP save(RSVP rsvp) {
		Assert.notNull(rsvp);
		RSVP res;
		final RSVP aux = rsvp;
		
		//Iniciaciones
		User user = userService.findByPrincipal();
		Assert.notNull(user);
		aux.setUser(user);
		Rendezvous rendezvous = rendezvousService.findOne(aux.getRendezvous().getId());
		aux.setRendezvous(rendezvous);
		
		res= this.RSVPRepository.save(aux);
		
		return res;
	}

	public void delete(RSVP rsvp) {

		Assert.notNull(rsvp);
		Assert.isTrue(this.RSVPRepository.exists(rsvp.getId()));
		
		// Authority
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().contains(Authority.USER));

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
}
