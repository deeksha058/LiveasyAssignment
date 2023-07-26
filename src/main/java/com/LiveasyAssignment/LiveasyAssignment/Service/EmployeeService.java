package com.LiveasyAssignment.LiveasyAssignment.Service;

import com.LiveasyAssignment.LiveasyAssignment.Model.Employee;
import com.LiveasyAssignment.LiveasyAssignment.Repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    EmailService emailService;

    public List<Employee> data() {
        return repository.findAll();
    }

    public List<Employee> getAllData(int pageIndex, int pageSize, String field) {
        pageIndex = (pageIndex-1)*pageSize;
        List<Employee> loads = repository.loadsData(pageIndex ,pageSize ,field);
        return loads;
    }

    public Employee saveEmployeeData(Employee employee) {

        SimpleMailMessage messageToManager = new SimpleMailMessage();
        messageToManager.setTo("deekshajindal085@gmail.com");
        messageToManager.setSubject("New Employee Added!");
        messageToManager.setText(employee.getEmployeeName() + "will now work under you. Mobile number is " + employee.getPhoneNumber() + "and email is "+ employee.getEmail());
        emailService.sendEmail(messageToManager);
        Employee saveLoadData = repository.save(employee);
        return saveLoadData;
    }

    public Employee deleteEmployeeData(UUID id) {
        try{
            Employee employeeData = repository.getEmployeeById(id);
            repository.deleteById(id);
            return employeeData;

        }catch (Exception e){
            System.out.println("Employee not found");
        }
        return null;
    }

    public Employee updateEmployeeData(Employee employee, UUID id ) {

        try {

            Employee loadDataWithId = repository.getEmployeeById(id);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(employee);
            Employee employeeData = mapper.readValue(jsonString, Employee.class);
            employeeData.setId(id);
            Employee saveEmployeeData = repository.save(employeeData);
            return saveEmployeeData;

        } catch (Exception e) {
            System.out.println("Employee not found in database " + e);
        }
        return null;
    }

    public Employee updateOrAddEmployeeData(Employee employee, UUID id ) {

        try {
            Employee loadDataWithId = repository.getEmployeeById(id);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(employee);
            Employee employeeData = mapper.readValue(jsonString, Employee.class);
            employeeData.setId(id);
            Employee saveEmployeeData = repository.save(employeeData);
            return saveEmployeeData;

        } catch (Exception e) {
            System.out.println("Employee not found in database " + e);
        }
        return null;
    }

    public Employee getDataById(UUID id) {
        try {
            Employee employee = repository.getEmployeeById(id);
            return employee;
        }catch (Exception e) {
            System.out.println("Employee not found");
            return null;
        }
    }
}
