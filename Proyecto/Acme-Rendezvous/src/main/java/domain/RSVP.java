package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class RSVP extends DomainEntity {
	
	//Relaciones
	
	private User user;
	private Rendezvous rendezvous;	
	
	//GetterSetters Relaciones
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user=user;
	}
	
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Rendezvous getRendezvous() {
		return this.rendezvous;
	}
	
	public void setRendevouz(Rendezvous rendezvous) {
		this.rendezvous=rendezvous;
	}
}
