package com.challenge.api.service;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImplementation;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.Optional;

@Service // this class is a service, which allows it to be injected into the controller.
public class EmployeeService {

    //mock database to hold Employee objects 
    private final List<Employee> mockDatabase = new ArrayList<>();  

    // Generates a unique UUID for each new employee.
    public UUID generateUniqueUuid() {

        UUID newUuid;

        // Ensure the UUID generated is unique by checking if it already exists in the mock database.
        do {
            newUuid = UUID.randomUUID();  // Generates a new random UUID.
        } while (uuidExists(newUuid)); // Keep generating a new UUID until a unique one is found.

        return newUuid;  // Return the unique UUID.
    }

    // Helper method to check if the UUID already exists in the mock database.
    private boolean uuidExists(UUID uuid) {
        return mockDatabase.stream().anyMatch(emp -> emp.getUuid().equals(uuid)); // Stream through the list to check for matching UUID.
    }

    // Creates a new Employee and adds it to the mock database.
    public Employee createEmployee(EmployeeImplementation employee) {
        employee.setUuid(generateUniqueUuid()); // Generate a unique UUID and set it on the Employee object.
        mockDatabase.add(employee);  // Add the newly created employee to the mock database.
        return employee;  // Return the created Employee.
    }

    // Returns the list of all employees in the mock database.
    public List<Employee> getAllEmployees() {
        return mockDatabase;  // Simply return the mock database list.
    }

    // Retrieves an Employee by its UUID. If not found, throws a ResponseStatusException with 404 status.
    public Employee getEmployeeByUuid(UUID uuid) {
        return mockDatabase.stream()
                .filter(emp -> emp.getUuid().equals(uuid))  // Filter for the employee with the given UUID.
                .findFirst()  // Return the first match (if any).
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));  // Throw 404 if not found.
    }
}
