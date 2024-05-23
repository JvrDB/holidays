package r1vs.holidays.mapper;

import r1vs.holidays.dto.VacationTypeDto;
import r1vs.holidays.entity.VacationType;

public class VacationTypeMapper {

    public static VacationTypeDto mapToVacationTypeDto(VacationType vacationType){
        return new VacationTypeDto(
            vacationType.getId(),
            vacationType.getDescription(),
            vacationType.getMaxDays()
        );
    }

    public static VacationType mapToVacationType(VacationTypeDto vacationTypeDto){
        return new VacationType(
            vacationTypeDto.getId(),
            vacationTypeDto.getDescription(),
            vacationTypeDto.getMaxDays()
        );
    }
}
