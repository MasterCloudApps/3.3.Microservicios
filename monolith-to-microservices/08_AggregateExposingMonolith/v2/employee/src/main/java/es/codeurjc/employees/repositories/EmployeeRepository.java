package es.codeurjc.employees.repositories;

import java.util.Optional;

import es.codeurjc.employees.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findById(Integer id);

}
