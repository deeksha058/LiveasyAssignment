package com.LiveasyAssignment.LiveasyAssignment.Repository;

import com.LiveasyAssignment.LiveasyAssignment.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query(value = "SELECT * FROM employees ORDER BY :field limit :limit_value offset :offset_value" ,nativeQuery = true)
    public List<Employee> loadsData(Integer offset_value , Integer  limit_value, String field);

    @Query(value = "SELECT * FROM employees WHERE employee_id = :employeeId" ,nativeQuery = true)
    public Employee getEmployeeById(UUID employeeId);

    void deleteById(UUID id);


}