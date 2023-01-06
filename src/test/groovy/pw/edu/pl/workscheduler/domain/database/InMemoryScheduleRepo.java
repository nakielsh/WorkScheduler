package pw.edu.pl.workscheduler.domain.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.dto.ShiftDayDTO;
import pw.edu.pl.workscheduler.domain.ports.ScheduleOutputPort;

public class InMemoryScheduleRepo implements ScheduleOutputPort {

    private final Map<Long, ScheduleDTO> schedules = new HashMap<>();

    @Override
    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO) {
        setIds(scheduleDTO);
        ScheduleDTO scheduleToSave;

        if (scheduleDTO.getId() != null) {
            scheduleToSave = scheduleDTO;
        } else {
            scheduleToSave =
                    new ScheduleDTO(
                            schedules.size() + 1L,
                            scheduleDTO.getMonth(),
                            scheduleDTO.getShiftDays(),
                            scheduleDTO.getEmployeeList());
        }
        schedules.put(scheduleToSave.getId(), scheduleToSave);
        return scheduleToSave;
    }

    private void setIds(ScheduleDTO scheduleDTO) {
        for (int i = 0; i < scheduleDTO.getShiftDays().size(); i++) {
            scheduleDTO.getShiftDays().get(i).setId((long) i);
            for (int j = 0; j < scheduleDTO.getShiftDays().get(i).getShiftsForADay().size(); j++) {
                scheduleDTO
                        .getShiftDays()
                        .get(i)
                        .getShiftsForADay()
                        .get(j)
                        .setId(
                                (long) scheduleDTO.getShiftDays().get(0).getShiftsForADay().size()
                                                * i
                                        + j);
            }
        }
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

    @Override
    public List<ScheduleDTO> getAllSchedules() {
        return schedules.values().stream().toList();
    }

    @Override
    public ScheduleDTO getScheduleByShiftId(Long shiftId) {
        for (ScheduleDTO scheduleDTO : schedules.values()) {
            for (ShiftDayDTO shiftDayDTO : scheduleDTO.getShiftDays()) {
                for (int i = 0; i < shiftDayDTO.getShiftsForADay().size(); i++) {
                    if (shiftDayDTO.getShiftsForADay().get(i).getId().equals(shiftId)) {
                        return scheduleDTO;
                    }
                }
            }
        }
        return null;
    }
}
