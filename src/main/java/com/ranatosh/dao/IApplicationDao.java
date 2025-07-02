package com.ranatosh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ranatosh.entityModel.ApplicationModel;

public interface IApplicationDao extends JpaRepository<ApplicationModel,Long> {
	 
}
