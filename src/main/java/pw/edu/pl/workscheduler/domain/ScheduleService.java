package pw.edu.pl.workscheduler.domain;

import lombok.AllArgsConstructor;
import pw.edu.pl.workscheduler.domain.commands.InitiateScheduleCommand;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.ports.ScheduleOutputPort;

import java.time.YearMonth;

@AllArgsConstructor
class ScheduleService {

    private final ScheduleOutputPort schedulePort;

    ScheduleDTO initializeSchedule(InitiateScheduleCommand command) {

        Schedule schedule = new Schedule();
        schedule.setMonth(YearMonth.of(command.getYear(), command.getMonth()));
        schedule.generateShiftDays(
                command.getStartTime(), command.getEndTime(), command.getShiftTimes());

        return schedulePort.saveSchedule(DtoMapper.toScheduleDTO(schedule));
    }

    ScheduleDTO getSchedule(Long scheduleId) {
        return schedulePort.getSchedule(scheduleId);
    }

}
