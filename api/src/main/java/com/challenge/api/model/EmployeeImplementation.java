package com.challenge.api.model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.time.Instant;
import java.util.UUID;

public  class EmployeeImplementation implements Employee{
    private UUID id ;
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;

    @Override
    public UUID getUuid()
    {
        if(id == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        else
        {
            return id;
        }        
    }

    /**
     * Set by either the Service or Data layer.
     * @param uuid required non-null
     */
    @Override
    public void setUuid(UUID uuid)
    {
        if(uuid == null)
        {
            this.id = UUID.randomUUID();
        }
        else{
            this.id = uuid;
        }
    }
    @Override
    public String getFirstName()
    {
        return firstName;
    }
    @Override
    public void setFirstName(String name)
    {
        this.firstName = name;
    }
    @Override
    public String getLastName()
    {
        return lastName;
    }
    @Override
    public void setLastName(String name)
    {
        this.lastName = name;
    }
    @Override
    public String getFullName()
    {
        return fullName;
    }
    @Override
    public void setFullName(String name)
    {
        this.fullName = name;
    }
    @Override
    public Integer getSalary()
    {
        return salary;
    }
    @Override
    public void setSalary(Integer salary) 
    {
        if (salary == null || salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative or null");
        }
        this.salary = salary;
    }
    @Override
    public Integer getAge()
    {
        return age;
    }
    @Override
    public void setAge(Integer age)
    {
        if(age < 18)
        {
            throw new IllegalArgumentException("Employee needs to be 18 or older");
        }
        this.age = age;
    }
    @Override
    public String getJobTitle()
    {
        return jobTitle;
    }
    @Override
    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }
    @Override
    public String getEmail()
    {
        return email;
    }
    @Override
    public void setEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (email == null || !email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format!");
        }
        
        this.email = email;
    }

    @Override
    public Instant getContractHireDate()
    {
        return contractHireDate;
    }
    @Override
    public void setContractHireDate(Instant date)
    {
        if(date == null)
        {
            throw new IllegalArgumentException("Hire date can't be null"); // an employee needs to be hired to be an employee;
        }
        this.contractHireDate = date;
    }

    /**
     * Nullable.
     * @return null, if Employee has not been terminated.
     */
    @Override
    public Instant getContractTerminationDate()
    {
        if(contractTerminationDate == null)
        {
            return null;
        }
        else{
            return contractTerminationDate;
        }
    }
    @Override
    public void setContractTerminationDate(Instant date)
    {   
        this.contractTerminationDate = date;
    }
}