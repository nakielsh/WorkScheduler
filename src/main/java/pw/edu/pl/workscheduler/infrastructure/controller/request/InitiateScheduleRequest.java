package pw.edu.pl.workscheduler.infrastructure.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import lombok.Getter;

@Getter
public class InitiateScheduleRequest {

    @JsonFormat(pattern = "yyyy-MM")
    private YearMonth month;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @JsonFormat(pattern = "HH:mm")
    private List<LocalTime> shiftTimes;

    private List<Long> employeeIds;
}
