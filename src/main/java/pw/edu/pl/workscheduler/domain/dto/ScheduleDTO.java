package pw.edu.pl.workscheduler.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.YearMonth;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class ScheduleDTO {

    private final Long id;

    private final YearMonth month;
    private final List<ShiftDayDTO> shiftDays;
    private final List<EmployeeDTO> employeeList;

    @Setter private List<Long> emptyShifts;
}
