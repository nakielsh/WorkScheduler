package pw.edu.pl.workscheduler.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ShiftDTO {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private EmployeeDTO employee;
}
