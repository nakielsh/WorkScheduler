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
public class CompactScheduleDTO {

    private final Long id;

    private final String scheduleName;
    private final String managerName;

    private final YearMonth month;
    private final List<CompactShiftDTO> shifts;
    private final List<EmployeeDTO> employeeList;

    @Setter private List<CompactShiftDTO> emptyShifts;
}
