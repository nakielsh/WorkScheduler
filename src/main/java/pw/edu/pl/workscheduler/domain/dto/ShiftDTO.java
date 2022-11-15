package pw.edu.pl.workscheduler.domain.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class ShiftDTO {

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private EmployeeDTO employee;
}
