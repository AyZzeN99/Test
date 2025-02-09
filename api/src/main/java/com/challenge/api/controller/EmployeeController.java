package com.challenge.api.controller;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImplementation;
import com.challenge.api.service.EmployeeService;  
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;  

    // Constructor-based injection for EmployeeService
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Get all employees
     * @return List of all Employees.
     */
    // using the service layer to handle bussiness logic
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();  
    }

    /**
     * Get employee by UUID
     * @param uuid Employee UUID
     * @return Employee with the given UUID
     */
    @GetMapping("/{uuid}")
    public Employee getEmployeeByUuid(@PathVariable UUID uuid) {
        return employeeService.getEmployeeByUuid(uuid);  
    }

    /**
     * Create a new employee
     * @param requestBody Employee data
     * @return Newly created Employee
     */
    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeImplementation requestBody) {
        return employeeService.createEmployee(requestBody);  
    }
}
