package pw.edu.pl.workscheduler.domain.repository;

import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;

public interface SchedulePort {

    ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO);

    ScheduleDTO updateSchedule(ScheduleDTO scheduleDTO);

    ScheduleDTO getSchedule(Long scheduleId);
}
