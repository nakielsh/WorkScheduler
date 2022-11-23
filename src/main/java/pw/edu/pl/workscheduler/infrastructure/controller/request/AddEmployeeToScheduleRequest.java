package pw.edu.pl.workscheduler.infrastructure.controller.request;

import java.util.List;
import lombok.Getter;
import pw.edu.pl.workscheduler.domain.dto.TimeFrameDTO;

@Getter
public class AddEmployeeToScheduleRequest {

    private Long scheduleId;
    private String name;
    private List<TimeFrameDTO> timeframes;
}
