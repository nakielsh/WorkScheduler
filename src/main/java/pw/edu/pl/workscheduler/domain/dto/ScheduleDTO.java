package pw.edu.pl.workscheduler.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.YearMonth;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class ScheduleDTO {

    private Long id;

    private YearMonth month;
    private List<ShiftDayDTO> shiftDays;
    private List<EmployeeDTO> employeeList;
}
