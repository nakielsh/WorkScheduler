package pw.edu.pl.workscheduler.infrastructure.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import java.util.List;
import lombok.Getter;

@Getter
public class InitiateScheduleRequest {

    private int month;
    private int year;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @JsonFormat(pattern = "HH:mm")
    private List<LocalTime> shiftTimes;
}
