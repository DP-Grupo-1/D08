
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	//Atributos-------------------------------------------------------------------------
	private String	name;
	private String	surname;
	private Integer	postalAddress;
	private String	phoneNumber;
	private String	email;


	//Getters and setters---------------------------------------------------------------
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public Integer getPostalAddress() {
		return this.postalAddress;
	}

	public void setPostalAddress(final Integer postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

}
