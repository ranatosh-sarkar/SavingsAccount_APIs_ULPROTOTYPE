package com.ranatosh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ranatosh.entityModel.KYCMasterTableModel;
import com.ranatosh.entityModel.UserModel;
import com.ranatosh.serviceLogic.KYCServiceLogic;

@RestController
public class KYCController {

    @Autowired
    private KYCServiceLogic kycService;
    
    @PostMapping("/kycDataCapture")
    public KYCMasterTableModel captureKYC(@RequestBody KYCMasterTableModel payload) {
        return kycService.captureKYCData(payload);
    }

    /** 
     * Client sends *all* KYC fields in the body.
     * We reuse KYCMasterTableModel as the payload type.
     */
    @PostMapping("/kycVerification")
    public UserModel verifyKYC(@RequestBody KYCMasterTableModel payload) {
        return kycService.verifyAndActivate(payload);
    }
}
