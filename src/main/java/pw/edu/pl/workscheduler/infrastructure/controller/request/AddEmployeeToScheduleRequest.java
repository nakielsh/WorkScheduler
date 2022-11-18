package pw.edu.pl.workscheduler.infrastructure.controller.request;

import lombok.Getter;
import pw.edu.pl.workscheduler.domain.dto.TimeFrameDTO;

import java.util.List;

@Getter
public class AddEmployeeToScheduleRequest {

    private Long scheduleId;
    private String name;
    private List<TimeFrameDTO> timeframes; // list - plural
}
