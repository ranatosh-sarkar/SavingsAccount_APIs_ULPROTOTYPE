package com.ranatosh.serviceLogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranatosh.dao.ITestDao;
import com.ranatosh.entityModel.TestModel;

@Service
public class TestServiceLogic {
    @Autowired
    private ITestDao iTestDao;
  
    public TestModel saveEmployee(TestModel testModel) {
        return iTestDao.save(testModel);
    }
  
    public List<TestModel> saveEmployees(List<TestModel> testModelEmployees) {
        return iTestDao.saveAll(testModelEmployees);
    }
  
    public List<TestModel> getEmployees() {
        return iTestDao.findAll();
    }
  
    public TestModel getEmployeeById(int empId) {
        return iTestDao.findById(empId).orElse(null);
    }
      
      public TestModel getEmployeeByFirstName(String name) { return
      iTestDao.findByFirstName(name); }
       
  
    public String deleteEmployee(int id) {
        iTestDao.deleteById(id);
        return "Employee removed !! " + id;
    }
  
    public TestModel updateEmployee(TestModel employee) {
        TestModel existingEmployee = iTestDao.findById(employee.getEmpId()).orElse(null);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        return iTestDao.save(existingEmployee);
    }
}
