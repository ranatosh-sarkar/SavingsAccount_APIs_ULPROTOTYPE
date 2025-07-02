package com.ranatosh.entityModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "KYCMasterTable")
public class KYCMasterTableModel {
      
	@Id
    @Column(name="contact", nullable=false, length=200)
    private long contact;
    
    @Column(name="password", nullable=false, length=200)
    private String password;
    
    @Column(name = "socialid", nullable = false)
    private String socialid;
    
    @Column(name = "annualincome", nullable = false)
    private String annualincome;
    
    @Column(name = "taxid", nullable = false)
    private String taxid;
    
    @Column(name = "drivinglicence", nullable = false)
    private String drivinglicence;
    
    @Column(name = "passportnumber", nullable = false)
    private String passportnumber;
    
    @Column(name = "occupation", nullable = false)
    private String occupation;
    
    @Column(name = "ongoingloans", nullable = false)
    private String ongoingloans;

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
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

	public String getAnnualincome() {
		return annualincome;
	}

	public void setAnnualincome(String annualincome) {
		this.annualincome = annualincome;
	}

	public String getTaxid() {
		return taxid;
	}

	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}

	public String getDrivinglicence() {
		return drivinglicence;
	}

	public void setDrivinglicence(String drivinglicence) {
		this.drivinglicence = drivinglicence;
	}

	public String getPassportnumber() {
		return passportnumber;
	}

	public void setPassportnumber(String passportnumber) {
		this.passportnumber = passportnumber;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getOngoingloans() {
		return ongoingloans;
	}

	public void setOngoingloans(String ongoingloans) {
		this.ongoingloans = ongoingloans;
	}
}