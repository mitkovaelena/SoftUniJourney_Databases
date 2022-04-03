package app.repositories;

import app.models.Address;
import app.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Set<Employee> findByBirthdayBeforeOrderBySalaryDesc(LocalDate date);
}
