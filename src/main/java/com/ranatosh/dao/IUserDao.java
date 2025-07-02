package com.ranatosh.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ranatosh.entityModel.UserModel;

public interface IUserDao extends JpaRepository<UserModel, Long> { 
	
	Optional<UserModel> findByContactAndPassword(long contact, String password);
}