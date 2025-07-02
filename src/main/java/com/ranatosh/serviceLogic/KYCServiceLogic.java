package com.ranatosh.serviceLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranatosh.dao.IKYCDao;
import com.ranatosh.dao.IUserDao;
import com.ranatosh.entityModel.KYCMasterTableModel;
import com.ranatosh.entityModel.UserModel;

import jakarta.transaction.Transactional;

@Service
public class KYCServiceLogic {

    @Autowired private IKYCDao  kycDao;
    @Autowired private IUserDao userDao;

    @Transactional
    public UserModel verifyAndActivate(KYCMasterTableModel req) {

        KYCMasterTableModel master = kycDao.findById(req.getContact())
                                           .orElseThrow(() ->
                                               new IllegalArgumentException("Contact not found in KYC master"));

        if (!same(master.getPassword(),       req.getPassword())       ||
            !same(master.getSocialid(),       req.getSocialid())       ||
            !same(master.getAnnualincome(),   req.getAnnualincome())   ||
            !same(master.getTaxid(),          req.getTaxid())          ||
            !same(master.getDrivinglicence(), req.getDrivinglicence()) ||
            !same(master.getPassportnumber(), req.getPassportnumber()) ||
            !same(master.getOccupation(),     req.getOccupation())     ||
            !same(master.getOngoingloans(),   req.getOngoingloans())) {

            throw new IllegalArgumentException("KYC details do not match master record");
        }

        UserModel user = userDao.findById(req.getContact())
                                .orElseThrow(() ->
                                    new IllegalArgumentException("User not registered"));

        user.setApplicationstatus("ACTIVE");
        user.setKycstatus("ACTIVE");
        userDao.save(user);

        return user;
    }


    private boolean same(String a, String b) {
        return a == null ? b == null : a.equals(b);
    }
}
