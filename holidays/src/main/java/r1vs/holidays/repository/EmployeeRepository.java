package r1vs.holidays.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import r1vs.holidays.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByRun(String run);
    Optional<Employee> findByEmail(String email);
    
}
