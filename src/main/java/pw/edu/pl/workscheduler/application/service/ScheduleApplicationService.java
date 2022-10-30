package pw.edu.pl.workscheduler.application.service;

import java.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.edu.pl.workscheduler.application.repository.SchedulePort;
import pw.edu.pl.workscheduler.domain.DtoMapper;
import pw.edu.pl.workscheduler.domain.Schedule;
import pw.edu.pl.workscheduler.domain.commands.InitiateScheduleCommand;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;

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
