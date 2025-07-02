package com.ranatosh.serviceLogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;   // ← use Spring’s annotation

import com.ranatosh.dao.ITransactionsDao;
import com.ranatosh.dao.IUserDao;
import com.ranatosh.entityModel.TransactionsModel;
import com.ranatosh.entityModel.UserModel;

@Service
public class TransactionsServiceLogic {

    @Autowired private IUserDao         iUserDao;
    @Autowired private ITransactionsDao txnDao;

    /* ------------------------- deposit ------------------------- */
    @Transactional
    public TransactionsModel deposit(TransactionsModel dto) {

        UserModel user = auth(dto.getContact(), dto.getPassword(), false);

        double newBalance = strToDouble(user.getBalance()) + dto.getAmount();
        user.setBalance(doubleToStr(newBalance));
        iUserDao.save(user);

        return txnDao.save( map(dto, "DEPOSIT", newBalance) );
    }

    /* ------------------------- withdraw ------------------------ */
    @Transactional
    public TransactionsModel withdraw(TransactionsModel dto) {

        UserModel user = auth(dto.getContact(), dto.getPassword(), true);

        double bal = strToDouble(user.getBalance());
        if (bal < dto.getAmount()) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        double newBalance = bal - dto.getAmount();
        user.setBalance(doubleToStr(newBalance));
        iUserDao.save(user);

        return txnDao.save( map(dto, "WITHDRAW", newBalance) );
    }

    /* ----------------------- fund-transfer --------------------- */
    @Transactional
    public TransactionsModel transferFund(TransactionsModel dto, long toContact) {

        // debit side
        UserModel from = auth(dto.getContact(), dto.getPassword(), true);
        if (strToDouble(from.getBalance()) < dto.getAmount())
            throw new IllegalArgumentException("Insufficient funds");

        // credit side
        UserModel to = iUserDao.findById(toContact)
                              .orElseThrow(() -> new IllegalArgumentException("Payee not found"));

        double fromNewBal = strToDouble(from.getBalance()) - dto.getAmount();
        double toNewBal   = strToDouble(to.getBalance())   + dto.getAmount();

        from.setBalance(doubleToStr(fromNewBal));
        to.setBalance(doubleToStr(toNewBal));
        iUserDao.save(from);
        iUserDao.save(to);

        /* two rows in transaction_table */
        txnDao.save( map(dto, "TRANSFER_OUT", fromNewBal) );   // payer

        TransactionsModel inTx = map(dto, "TRANSFER_IN", toNewBal);
        inTx.setContact(toContact);
        return txnDao.save(inTx);                              // payee
    }

    /* ---------------------- statement -------------------------- */
    @Transactional(readOnly = true)
    public List<TransactionsModel> statement(long contact, String password) {

        auth(contact, password, true);     // just validate credentials + KYC
        return txnDao.findByContactOrderByTxnTimeDesc(contact);
    }

    /* ------------------ helpers ------------------ */

    private UserModel auth(long contact, String pwd, boolean kycNeeded) {
        UserModel u = iUserDao.findByContactAndPassword(contact, pwd)
                             .orElseThrow(() -> new IllegalArgumentException("Bad credentials"));

        if (kycNeeded && !"ACTIVE".equalsIgnoreCase(u.getKycstatus()))
            throw new IllegalStateException("KYC not complete");

        return u;
    }

    private TransactionsModel map(TransactionsModel src, String type, double balAfter) {
        TransactionsModel t = new TransactionsModel();
        t.setContact(src.getContact());
        t.setTxntype(type);
        t.setAmount(src.getAmount());
        t.setBalanceAfter(balAfter);
        return t;
    }

    /* ---------- String ↔ double utilities ---------- */
    private double strToDouble(String v) {
        return (v == null || v.isBlank()) ? 0.0 : Double.parseDouble(v);
    }
    private String doubleToStr(double v) { return Double.toString(v); }
}
