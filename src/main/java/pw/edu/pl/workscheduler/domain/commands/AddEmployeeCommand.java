package pw.edu.pl.workscheduler.domain.commands;

import java.util.List;
import lombok.Value;
import pw.edu.pl.workscheduler.domain.dto.TimeFrameDTO;

@Value
public class AddEmployeeCommand {

    String name;
    List<TimeFrameDTO> timeframes;
}
