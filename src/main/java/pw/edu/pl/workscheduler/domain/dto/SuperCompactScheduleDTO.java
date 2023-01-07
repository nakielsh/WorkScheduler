package pw.edu.pl.workscheduler.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.YearMonth;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class SuperCompactScheduleDTO {

    private final Long id;
    private final String scheduleName;
    private final String managerName;
    private final YearMonth month;
}
