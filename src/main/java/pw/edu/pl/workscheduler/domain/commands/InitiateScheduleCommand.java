package pw.edu.pl.workscheduler.domain.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import java.util.List;
import lombok.Value;

@Value
public class InitiateScheduleCommand {

    int month;
    int year;

    @JsonFormat(pattern = "HH:mm")
    LocalTime startTime;

    @JsonFormat(pattern = "HH:mm")
    LocalTime endTime;

    @JsonFormat(pattern = "HH:mm")
    List<LocalTime> shiftTimes;
}
