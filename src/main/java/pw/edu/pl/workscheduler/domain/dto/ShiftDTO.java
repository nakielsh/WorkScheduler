package pw.edu.pl.workscheduler.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShiftDTO {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private EmployeeDTO employee;
}
