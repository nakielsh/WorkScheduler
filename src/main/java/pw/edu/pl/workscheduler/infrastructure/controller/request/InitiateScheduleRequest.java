package pw.edu.pl.workscheduler.infrastructure.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
public class InitiateScheduleRequest {

    /*
    prolly good to use java date
     */

    private int month;
    private int year;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @JsonFormat(pattern = "HH:mm")
    private List<LocalTime> shiftTimes;
}
