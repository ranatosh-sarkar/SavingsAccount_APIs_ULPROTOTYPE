package com.ranatosh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ranatosh.entityModel.TestModel;

public interface ITestDao extends JpaRepository<TestModel,Integer> {
	 
    TestModel findByFirstName(String name);
 
}