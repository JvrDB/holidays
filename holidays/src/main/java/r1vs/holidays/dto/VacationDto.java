package r1vs.holidays.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacationDto {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private EmployeeDto employee;
    private VacationTypeDto vacationType;
}
