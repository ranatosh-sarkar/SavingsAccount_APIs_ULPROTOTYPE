package com.ranatosh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ranatosh.entityModel.KYCMasterTableModel;

public interface IKYCDao extends JpaRepository<KYCMasterTableModel, Long> { 
	
	
}