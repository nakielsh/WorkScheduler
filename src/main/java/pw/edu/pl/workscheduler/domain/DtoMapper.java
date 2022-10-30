package pw.edu.pl.workscheduler.domain;

import java.util.List;
import java.util.stream.Collectors;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.dto.ShiftDTO;
import pw.edu.pl.workscheduler.domain.dto.ShiftDayDTO;
import pw.edu.pl.workscheduler.domain.dto.TimeFrameDTO;

public class DtoMapper {

    public static ScheduleDTO toScheduleDTO(Schedule schedule) {
        return new ScheduleDTO(
                schedule.getId(),
                schedule.getMonth(),
                schedule.getShiftDays().stream()
                        .map(DtoMapper::toShiftDayDTO)
                        .collect(Collectors.toList()),
                schedule.getEmployeeList().stream()
                        .map(DtoMapper::toEmployeeDTO)
                        .collect(Collectors.toList()));
    }

    public static Schedule toSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setMonth(scheduleDTO.getMonth());
        schedule.setEmployeeList(
                scheduleDTO.getEmployeeList().stream()
                        .map(DtoMapper::toEmployee)
                        .collect(Collectors.toList()));
        schedule.setShiftDays(
                scheduleDTO.getShiftDays().stream()
                        .map(DtoMapper::toShiftDay)
                        .collect(Collectors.toList()));

        return schedule;
    }

    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        if (employee == null) {
            return new EmployeeDTO(null, null, null, null);
        }
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getUnavailabilityList().stream()
                        .map(DtoMapper::toTimeFrameDTO)
                        .collect(Collectors.toList()),
                getShifts(employee));
    }

    private static List<ShiftDTO> getShifts(Employee employee) {
        return employee.getShifts() == null
                ? null
                : employee.getShifts().stream()
                        .map(DtoMapper::toShiftDTO)
                        .collect(Collectors.toList());
    }

    public static Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        if (employeeDTO != null) {
            if (employeeDTO.getId() != null) {
                employee.setId(employeeDTO.getId());
            }
            employee.setName(employeeDTO.getName());
            employee.setUnavailabilityList(
                    employeeDTO.getUnavailabilityList().stream()
                            .map(DtoMapper::toTimeFrame)
                            .collect(Collectors.toList()));
            if (employeeDTO.getShifts() != null) {
                employee.setShifts(
                        employeeDTO.getShifts().stream()
                                .map(DtoMapper::toShift)
                                .collect(Collectors.toList()));
            }
        }

        return employee;
    }

    public static ShiftDayDTO toShiftDayDTO(ShiftDay shiftDay) {
        return new ShiftDayDTO(
                shiftDay.getDate(),
                shiftDay.getShiftsForADay().stream()
                        .map(DtoMapper::toShiftDTO)
                        .collect(Collectors.toList()),
                shiftDay.getWorkStartTime(),
                shiftDay.getWorkEndTime());
    }

    public static ShiftDay toShiftDay(ShiftDayDTO shiftDayDTO) {
        return new ShiftDay(
                shiftDayDTO.getDate(),
                shiftDayDTO.getShiftsForADay().stream()
                        .map(DtoMapper::toShift)
                        .collect(Collectors.toList()));
    }

    public static ShiftDTO toShiftDTO(Shift shift) {
        return new ShiftDTO(
                shift.getStartTime(), shift.getEndTime(), toEmployeeDTO(shift.getEmployee()));
    }

    public static Shift toShift(ShiftDTO shiftDTO) {
        if (shiftDTO.getEmployee() == null) {
            return new Shift(shiftDTO.getStartTime(), shiftDTO.getEndTime());
        } else {
            return new Shift(
                    shiftDTO.getStartTime(),
                    shiftDTO.getEndTime(),
                    toEmployee(shiftDTO.getEmployee()));
        }
    }

    public static TimeFrame toTimeFrame(TimeFrameDTO timeFrameDTO) {
        return new TimeFrame(timeFrameDTO.getStartTime(), timeFrameDTO.getEndTime());
    }

    public static TimeFrameDTO toTimeFrameDTO(TimeFrame timeFrame) {
        return new TimeFrameDTO(timeFrame.getStartTime(), timeFrame.getEndTime());
    }
}
