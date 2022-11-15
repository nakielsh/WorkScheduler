package pw.edu.pl.workscheduler.domain;

import java.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.edu.pl.workscheduler.domain.commands.InitiateScheduleCommand;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.repository.SchedulePort;

@Service
public class ScheduleApplicationService {

    SchedulePort schedulePort;

    @Autowired
    public ScheduleApplicationService(SchedulePort schedulePort) {
        this.schedulePort = schedulePort;
    }

    public ScheduleDTO initializeSchedule(InitiateScheduleCommand command) {

        Schedule schedule = new Schedule();
        schedule.setMonth(YearMonth.of(command.getYear(), command.getMonth()));
        schedule.generateShiftDays(
                command.getStartTime(), command.getEndTime(), command.getShiftTimes());

        return schedulePort.saveSchedule(DtoMapper.toScheduleDTO(schedule));
    }
}
