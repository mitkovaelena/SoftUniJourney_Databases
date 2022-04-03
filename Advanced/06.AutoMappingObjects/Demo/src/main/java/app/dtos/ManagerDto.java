package app.dtos;

import app.models.Employee;

import java.math.BigDecimal;
import java.util.Set;

public class ManagerDto {
    private String firstName;

    private String lastName;

    private Set<EmployeeDto> employees;

    private int employeesCount;

    public ManagerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDto> employees) {
        this.employees = employees;
        this.employeesCount = employees.size();
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

}
