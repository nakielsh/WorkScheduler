package pw.edu.pl.workscheduler.domain.database;

import java.util.HashMap;
import java.util.Map;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.ports.ScheduleOutputPort;

public class InMemoryScheduleRepo implements ScheduleOutputPort {

    private final Map<Long, ScheduleDTO> schedules = new HashMap<>();

    @Override
    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO) {
        ScheduleDTO scheduleToSave =
                new ScheduleDTO(
                        schedules.size() + 1L,
                        scheduleDTO.getMonth(),
                        scheduleDTO.getShiftDays(),
                        scheduleDTO.getEmployeeList());
        schedules.put(scheduleToSave.getId(), scheduleToSave);
        return scheduleToSave;
    }

    @Override
    public ScheduleDTO updateSchedule(ScheduleDTO scheduleDTO) {
        schedules.put(scheduleDTO.getId(), scheduleDTO);
        return scheduleDTO;
    }

    @Override
    public ScheduleDTO getSchedule(Long scheduleId) {
        return schedules.get(scheduleId);
    }
}
