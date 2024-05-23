package r1vs.holidays.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacationTypeDto {

    private Long id;
    private String description;
    private int maxDays;
}
