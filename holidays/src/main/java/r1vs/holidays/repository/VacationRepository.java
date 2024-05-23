package r1vs.holidays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import r1vs.holidays.entity.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

    List<Vacation> findByEmployeeId(Long employeeId);
}
