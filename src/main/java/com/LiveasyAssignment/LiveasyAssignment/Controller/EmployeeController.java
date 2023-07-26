package com.LiveasyAssignment.LiveasyAssignment.Controller;

import com.LiveasyAssignment.LiveasyAssignment.Model.Employee;
import com.LiveasyAssignment.LiveasyAssignment.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<UUID>  addEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.saveEmployeeData(employee);
        return new ResponseEntity<>(newEmployee.getId(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> retrieveAllEmployeeData(){
        return new ResponseEntity<>(employeeService.data(), HttpStatus.OK);
    }

    @GetMapping("/{employeeID}")
    public ResponseEntity<Employee> retrieveEmployeeById(@PathVariable("employeeID") UUID employeeID){

        Employee dataById = employeeService.getDataById(employeeID);
        return new ResponseEntity<>(dataById, HttpStatus.OK);
    }

    @PutMapping("/{employeeID}")
    public ResponseEntity<String> updateOrAddEmployee(@RequestBody Employee employee, @PathVariable("employeeID") UUID id ) {
        Employee newEmployeeData = employeeService.updateOrAddEmployeeData(employee, id);
        if (newEmployeeData == null){
            return  new ResponseEntity<>("Data with  Id " + id +  " is not Present in Database", HttpStatus.OK);
        }
        return new ResponseEntity<>("Data Updated Successfully!", HttpStatus.OK);
    }

    @PatchMapping("/{employeeID}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable("employeeID") UUID id ) {
        Employee newEmployeeData = employeeService.updateEmployeeData(employee, id);
        if (newEmployeeData == null){
            return  new ResponseEntity<>("Data with  Id " + id +  " is not Present in Database", HttpStatus.OK);
        }
        return new ResponseEntity<>("Data Updated Successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{employeeID}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeID") UUID id) {
        Employee deleteEmployeeData = employeeService.deleteEmployeeData(id);
        if(deleteEmployeeData == null){
            return  new ResponseEntity<>("Data with this Id " + id  + " is not Present in Database", HttpStatus.OK);
        }
        return new ResponseEntity<>("Data Deleted Successfully!", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> loadsData(@RequestParam int pageIndex, @RequestParam int pageSize, @RequestParam String field){
        return new ResponseEntity<>(employeeService.getAllData(pageIndex, pageSize ,field) , HttpStatus.OK);
    }

}
