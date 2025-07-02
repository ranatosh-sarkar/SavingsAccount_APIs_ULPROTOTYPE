package com.ranatosh.entityModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ApplicationTable")
public class ApplicationModel {

    @Id
    @Column(name = "contact", nullable = false)
    private long contact;

    @Column(name = "accounttype", nullable = false)
    private String accounttype;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "socialid", nullable = false)
    private String socialid;

    @Column(name = "applicationstatus", length = 200)
    private String applicationstatus;
    
    @jakarta.persistence.Transient
    private String password;

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
