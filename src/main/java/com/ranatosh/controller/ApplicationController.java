package com.ranatosh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ranatosh.entityModel.ApplicationModel;
import com.ranatosh.serviceLogic.ApplicationServiceLogic;

@RestController
public class ApplicationController {

	 @Autowired
	    private ApplicationServiceLogic applicationService;
	  
	    @PostMapping("/addApplication")
	    public ApplicationModel addRegister(@RequestBody ApplicationModel application) {
	        return applicationService.saveApplication(application);
	    }
	  
	    @PostMapping("/addApplications")
	    public List<ApplicationModel> addRegisters(@RequestBody List<ApplicationModel> applications) {
	        return applicationService.saveApplications(applications);
	    }
	  
	    @GetMapping("/applications")
	    public List<ApplicationModel> findAllRegisters() {
	        return applicationService.getApplications();
	    }  
	   
	    @GetMapping("/applicationByContact/{contact}")
	    public ApplicationModel findApplicationByContact(@PathVariable long contact) {
	        return applicationService.getApplicationByContact(contact);
	    }
}
