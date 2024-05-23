package r1vs.holidays.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import r1vs.holidays.entity.VacationType;

public interface VacationTypeRepository extends JpaRepository<VacationType, Long>{

}
