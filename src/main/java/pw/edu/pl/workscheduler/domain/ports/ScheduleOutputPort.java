package pw.edu.pl.workscheduler.domain.ports;

import java.util.List;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;

public interface ScheduleOutputPort {

    ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO);

    ScheduleDTO updateSchedule(ScheduleDTO scheduleDTO);

    ScheduleDTO getSchedule(Long scheduleId);

    List<ScheduleDTO> getAllSchedules();
}
