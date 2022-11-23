package pw.edu.pl.workscheduler.domain;

import lombok.AllArgsConstructor;
import pw.edu.pl.workscheduler.domain.commands.InitiateScheduleCommand;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.ports.ScheduleOutputPort;

@AllArgsConstructor
class ScheduleService {

    private final ScheduleOutputPort schedulePort;

    ScheduleDTO initializeSchedule(InitiateScheduleCommand command) {

        Schedule schedule = new Schedule();
        schedule.setMonth(command.getMonth());
        schedule.generateShiftDays(
            command.getStartTime(), command.getEndTime(), command.getShiftTimes());

        return schedulePort.saveSchedule(ScheduleDtoMapper.toScheduleDTO(schedule));
    }

    ScheduleDTO getSchedule(Long scheduleId) {
        return schedulePort.getSchedule(scheduleId);
    }

}
