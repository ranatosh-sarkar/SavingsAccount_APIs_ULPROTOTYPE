package com.ranatosh.serviceLogic;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ranatosh.dao.IRegisterDao;
import com.ranatosh.dao.IUserDao;
import com.ranatosh.entityModel.RegisterModel;
import com.ranatosh.entityModel.UserModel; 


@Service
public class RegisterServiceLogic {
    @Autowired
    private IRegisterDao iRegisterDao;
    
    @Autowired 
    private IUserDao     userDao;
  
    @Transactional
    public RegisterModel saveRegister(RegisterModel reg) {
    // public RegisterModel saveRegister(RegisterModel reg) {
        RegisterModel saved = iRegisterDao.save(reg);

        UserModel user = new UserModel();
        user.setContact(saved.getContact());
        user.setFirstName(saved.getFirstName());
        user.setLastName(saved.getLastName());
        user.setEmail(saved.getEmail());
        user.setPassword(saved.getPassword());

        userDao.save(user);

        return saved;
    }
  

    @Transactional                   
    public List<RegisterModel> saveRegisters(List<RegisterModel> regs) {

        List<RegisterModel> savedRegs = iRegisterDao.saveAll(regs);

        List<UserModel> users = savedRegs.stream()
            .map(r -> {
                UserModel u = new UserModel();
                u.setContact(r.getContact());
                u.setFirstName(r.getFirstName());
                u.setLastName(r.getLastName());
                u.setEmail(r.getEmail());
                u.setPassword(r.getPassword());
                return u;
            })
            .collect(Collectors.toList());

        userDao.saveAll(users);

        return savedRegs;
    }
  
    public List<RegisterModel> getRegisters() {
        return iRegisterDao.findAll();
    }
  
    public RegisterModel getRegisterByContact(long contact) {
        return iRegisterDao.findById(contact).orElse(null);
    }
}

