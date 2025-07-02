package com.ranatosh.entityModel;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "transaction_table")
public class TransactionsModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long   contact;
    private String txntype;     // DEPOSIT | WITHDRAW | TRANSFER_IN | TRANSFER_OUT
    private double amount;
    private double balanceAfter;
    private LocalDateTime txnTime = LocalDateTime.now();

    @Transient              // supplied only for auth – not persisted
    private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getTxntype() {
		return txntype;
	}

	public void setTxntype(String txntype) {
		this.txntype = txntype;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalanceAfter() {
		return balanceAfter;
	}

	public void setBalanceAfter(double balanceAfter) {
		this.balanceAfter = balanceAfter;
	}

	public LocalDateTime getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(LocalDateTime txnTime) {
		this.txnTime = txnTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}

