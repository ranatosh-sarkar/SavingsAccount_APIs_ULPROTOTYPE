package com.ranatosh.serviceLogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ranatosh.dao.IApplicationDao;
import com.ranatosh.dao.IUserDao;
import com.ranatosh.entityModel.ApplicationModel;
import com.ranatosh.entityModel.UserModel;

@Service
public class ApplicationServiceLogic {

    @Autowired private IApplicationDao applicationDao;
    @Autowired private IUserDao        userDao;

    @Transactional
    public ApplicationModel saveApplication(ApplicationModel app) {

        UserModel user = verifyCredentialsOrThrow(app.getContact(), app.getPassword());

        app.setApplicationstatus("PENDING");
        user.setApplicationstatus("PENDING");
        user.setSocialid(app.getSocialid());
        userDao.save(user);                

        return applicationDao.save(app);   
    }

    @Transactional
    public List<ApplicationModel> saveApplications(List<ApplicationModel> apps) {

        for (ApplicationModel app : apps) {
            UserModel user = verifyCredentialsOrThrow(app.getContact(), app.getPassword());
            app.setApplicationstatus("PENDING");
            user.setApplicationstatus("PENDING");
            user.setSocialid(app.getSocialid());
            userDao.save(user);             
        }

        return applicationDao.saveAll(apps);
    }


    /** fetches the user if credentials match, otherwise throws 400-style exception */
    private UserModel verifyCredentialsOrThrow(long contact, String password) {
        return userDao.findByContactAndPassword(contact, password)
                      .orElseThrow(() ->
                          new IllegalArgumentException("Invalid contact / password"));
    }

    public List<ApplicationModel> getApplications()          { return applicationDao.findAll(); }
    public ApplicationModel getApplicationByContact(long c)  { return applicationDao.findById(c).orElse(null); }
}
