package r1vs.holidays.service;

import java.util.List;

import r1vs.holidays.dto.VacationTypeDto;

public interface VacationTypeService {
    VacationTypeDto createVacationType(VacationTypeDto vacationTypeDto);
    VacationTypeDto getVacationTypeById(Long vacationTypeId);
    List<VacationTypeDto> getAllVacationTypes();
    VacationTypeDto updateVacationType(Long vacationTypeId, VacationTypeDto updatedVacationTypeDto);
    void deleteVacationType(Long vacationTypeId);
}
