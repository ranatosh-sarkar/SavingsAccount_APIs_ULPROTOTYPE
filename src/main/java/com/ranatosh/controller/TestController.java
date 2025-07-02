package com.ranatosh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ranatosh.entityModel.TestModel;
import com.ranatosh.serviceLogic.TestServiceLogic;

@RestController
public class TestController {
  
    @Autowired
    private TestServiceLogic service;
  
    @PostMapping("/testAddEmployee")
    public TestModel addEmployee(@RequestBody TestModel employee) {
        return service.saveEmployee(employee);
    }
  
    @PostMapping("/testAddEmployees")
    public List<TestModel> addEmployees(@RequestBody List<TestModel> employees) {
        return service.saveEmployees(employees);
    }
  
    @GetMapping("/testEmployees")
    public List<TestModel> findAllEmployees() {
        return service.getEmployees();
    }  
      
    @GetMapping("/testEmployee/{firstName}") 
    public TestModel findEmployeeByFirstName(@PathVariable String firstName) { return
    service.getEmployeeByFirstName(firstName); }
       
  
    @PutMapping("/testUpdate")
    public TestModel updateEmployee(@RequestBody TestModel employee) {
        return service.updateEmployee(employee);
    }
    
 // GET by ID
    @GetMapping("/testEmployeeByEmpId/{empId}")
    public TestModel findEmployeeByEmpId(@PathVariable int empId) {
        return service.getEmployeeById(empId);
    }

    // DELETE
    @DeleteMapping("/testDelete/{empId}")
    public String deleteEmployee(@PathVariable int empId) {
        return service.deleteEmployee(empId);
    }
}