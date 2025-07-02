package com.ranatosh.entityModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "RegisterTable")
public class RegisterModel {
      
	@Id
    @Column(name="contact", nullable=false, length=200)
    private long contact;
    
    @Column(name="firstName", nullable=false, length=200)
    private String firstName;
      
    @Column(name="lastName", nullable=false, length=200)
    private String lastName;
      
    @Column(name="email", nullable=false, length=200)
    private String email;
    
    @Column(name="password", nullable=false, length=200)
    private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "RegisterModel [contact=" + contact + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + "]";
	}
}