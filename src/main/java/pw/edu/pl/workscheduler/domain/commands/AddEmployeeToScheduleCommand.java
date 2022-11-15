package pw.edu.pl.workscheduler.domain.commands;

import java.util.Set;
import lombok.Value;
import pw.edu.pl.workscheduler.domain.dto.TimeFrameDTO;

@Value
public class AddEmployeeToScheduleCommand {

    Long scheduleId;
    String name;
    Set<TimeFrameDTO> unavailability;
}
