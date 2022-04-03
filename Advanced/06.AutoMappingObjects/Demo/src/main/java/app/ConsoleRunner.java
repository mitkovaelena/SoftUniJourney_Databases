package app;

import app.dtos.EmployeeDto;
import app.dtos.ManagerDto;
import app.models.Address;
import app.models.Employee;
import app.repositories.AddressRepository;
import app.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private EmployeeRepository employeeRepository;
    private AddressRepository addressRepository;

    @Autowired
    public ConsoleRunner(EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }


    @Override
    public void run(String... strings) throws Exception {
        //ex1();
        ex2();

    }

    private void ex2() {
        ModelMapper modelMapper = new ModelMapper();
        Set<Employee> employees = this.employeeRepository.findByBirthdayBeforeOrderBySalaryDesc(LocalDate.parse("1990-01-01"));
        employees.forEach(e -> System.out.println(modelMapper.map(e, EmployeeDto.class).toString()));
    }

    private void ex1() {
        Address address = new Address();
        address.setCity("Sofia");
        address.setCountry("Bulgaria");
        Employee one = this.employeeRepository.findOne(1L);
        Employee two = this.employeeRepository.findOne(2L);

        two.setManager(one);
        employeeRepository.save(two);

        addressRepository.save(address);

        Set<Employee> employees = one.getEmployees();
        ModelMapper modelMapper = new ModelMapper();
        EmployeeDto employeeDto = modelMapper.map(two, EmployeeDto.class);
        ManagerDto managerDto = modelMapper.map(one, ManagerDto.class);

        System.out.println(employeeDto.getFirstName());
        System.out.println(managerDto.getFirstName());

        for (EmployeeDto e : managerDto.getEmployees()) {
            System.out.println(e.getFirstName());
        }
    }
}
