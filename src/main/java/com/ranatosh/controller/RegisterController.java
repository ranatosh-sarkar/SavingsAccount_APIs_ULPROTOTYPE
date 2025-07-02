package com.ranatosh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ranatosh.entityModel.RegisterModel;
import com.ranatosh.serviceLogic.RegisterServiceLogic;

@RestController
public class RegisterController {
  
    @Autowired
    private RegisterServiceLogic regService;
  
    @PostMapping("/addRegister")
    public RegisterModel addRegister(@RequestBody RegisterModel register) {
        return regService.saveRegister(register);
    }
  
    @PostMapping("/addRegisters")
    public List<RegisterModel> addRegisters(@RequestBody List<RegisterModel> registers) {
        return regService.saveRegisters(registers);
    }
  
    @GetMapping("/registers")
    public List<RegisterModel> findAllRegisters() {
        return regService.getRegisters();
    }  
   
    @GetMapping("/registerByContact/{contact}")
    public RegisterModel findRegisterByContact(@PathVariable long contact) {
        return regService.getRegisterByContact(contact);
    }
}