package r1vs.holidays.service;

import java.util.List;

import r1vs.holidays.dto.VacationDto;

public interface VacationService {
    VacationDto createVacation(VacationDto vacationDto);
    VacationDto getVacationById(Long vacationId);
    List<VacationDto> getAllVacations();
    List<VacationDto> getVacationsByEmployeeId(Long employeeId);
    VacationDto updateVacation(Long vacationId, VacationDto updatedVacationDto);
    void deleteVacation(Long vacationId);
}
