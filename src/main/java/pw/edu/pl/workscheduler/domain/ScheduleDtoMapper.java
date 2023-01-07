package pw.edu.pl.workscheduler.domain;

import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.dto.ShiftDayDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ScheduleDtoMapper {

    public static ScheduleDTO toScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO =
                new ScheduleDTO(
                        schedule.getId(),
                        schedule.getScheduleName(),
                        schedule.getManagerName(),
                        schedule.getMonth(),
                        schedule.getShiftDays().stream()
                                .map(ScheduleDtoMapper::toShiftDayDTO)
                                .collect(Collectors.toList()),
                        schedule.getEmployeeList().stream()
                                .map(
                                        employee ->
                                                EmployeeDtoMapper.toEmployeeDTO(employee, schedule))
                                .collect(Collectors.toList()));

        scheduleDTO.setEmptyShifts(
                schedule.getEmptyShifts().stream()
                        .map(EmployeeDtoMapper::toShiftDTO)
                        .collect(Collectors.toList()));

        return scheduleDTO;
    }

    public static Schedule toSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setScheduleName(scheduleDTO.getScheduleName());
        schedule.setManagerName(scheduleDTO.getManagerName());
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
                shiftDay.getId(),
                shiftDay.getDate(),
                shiftDay.getShiftsForADay().stream()
                        .map(EmployeeDtoMapper::toShiftDTO)
                        .collect(Collectors.toList()),
                shiftDay.getWorkStartTime(),
                shiftDay.getWorkEndTime());
    }

    public static ShiftDay toShiftDay(ShiftDayDTO shiftDayDTO) {
        return new ShiftDay(
                shiftDayDTO.getId(),
                shiftDayDTO.getDate(),
                shiftDayDTO.getShiftsForADay().stream()
                        .map(EmployeeDtoMapper::toShift)
                        .collect(Collectors.toList()));
    }
}
