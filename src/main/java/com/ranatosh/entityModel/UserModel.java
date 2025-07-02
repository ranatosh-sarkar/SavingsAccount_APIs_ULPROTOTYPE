package com.ranatosh.entityModel;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserTable")
public class UserModel {
      
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
    
    @Column(name="socialid",           length = 200)
    private String socialid;

    @Column(name="applicationstatus",  length = 200)
    private String applicationstatus;

    @Column(name="balance",            length = 200)
    private String balance;

    @Column(name="kycstatus",          length = 200)
    private String kycstatus;

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

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

	public String getSocialid() {
		return socialid;
	}

	public void setSocialid(String socialid) {
		this.socialid = socialid;
	}

	public String getApplicationstatus() {
		return applicationstatus;
	}

	public void setApplicationstatus(String applicationstatus) {
		this.applicationstatus = applicationstatus;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getKycstatus() {
		return kycstatus;
	}

	public void setKycstatus(String kycstatus) {
		this.kycstatus = kycstatus;
	}

	@Override
	public String toString() {
		return "UserModel [contact=" + contact + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + ", socialid=" + socialid + ", applicationstatus="
				+ applicationstatus + ", balance=" + balance + ", kycstatus=" + kycstatus + "]";
	}


}