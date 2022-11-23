package pw.edu.pl.workscheduler.domain;

import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.dto.ShiftDayDTO;

@SuppressWarnings("java:S6204")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ScheduleDtoMapper {

    public static ScheduleDTO toScheduleDTO(Schedule schedule) {
        return new ScheduleDTO(
            schedule.getId(),
            schedule.getMonth(),
            schedule.getShiftDays().stream()
                .map(ScheduleDtoMapper::toShiftDayDTO)
                .collect(Collectors.toList()),
            schedule.getEmployeeList().stream()
                .map(EmployeeDtoMapper::toEmployeeDTO)
                .collect(Collectors.toList()));
    }

    public static Schedule toSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setMonth(scheduleDTO.getMonth());
        schedule.setEmployeeList(
            scheduleDTO.getEmployeeList().stream()
                .map(EmployeeDtoMapper::toEmployee)
                .collect(Collectors.toList()));
        schedule.setShiftDays(
            scheduleDTO.getShiftDays().stream()
                .map(ScheduleDtoMapper::toShiftDay)
                .collect(Collectors.toList()));

        return schedule;
    }

    public static ShiftDayDTO toShiftDayDTO(ShiftDay shiftDay) {
        return new ShiftDayDTO(
            shiftDay.getDate(),
            shiftDay.getShiftsForADay().stream()
                .map(EmployeeDtoMapper::toShiftDTO)
                .collect(Collectors.toList()),
            shiftDay.getWorkStartTime(),
            shiftDay.getWorkEndTime());
    }

    public static ShiftDay toShiftDay(ShiftDayDTO shiftDayDTO) {
        return new ShiftDay(
            shiftDayDTO.getDate(),
            shiftDayDTO.getShiftsForADay().stream()
                .map(EmployeeDtoMapper::toShift)
                .collect(Collectors.toList()));
    }
}
