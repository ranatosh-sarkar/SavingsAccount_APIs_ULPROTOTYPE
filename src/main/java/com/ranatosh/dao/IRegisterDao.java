package com.ranatosh.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ranatosh.entityModel.RegisterModel;


public interface IRegisterDao extends JpaRepository<RegisterModel,Long> {
	 
}
