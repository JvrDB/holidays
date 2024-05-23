package r1vs.holidays.mapper;

import r1vs.holidays.dto.VacationDto;
import r1vs.holidays.entity.Vacation;

public class VacationMapper {

    public static VacationDto mapToVacationDto(Vacation vacation){
        return new VacationDto(
            vacation.getId(),
            vacation.getStartDate(),
            vacation.getEndDate(),
            EmployeeMapper.mapToEmployeeDto(vacation.getEmployee()),
            VacationTypeMapper.mapToVacationTypeDto(vacation.getVacationType())
        );
    }

    public static Vacation mapToVacation(VacationDto vacationDto){
        return new Vacation(
            vacationDto.getId(),
            vacationDto.getStartDate(),
            vacationDto.getEndDate(),
            EmployeeMapper.mapToEmployee(vacationDto.getEmployee()),
            VacationTypeMapper.mapToVacationType(vacationDto.getVacationType())
        );
    }
}
