package pw.edu.pl.workscheduler.domain;

import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.domain.dto.ShiftDTO;
import pw.edu.pl.workscheduler.domain.dto.TimeFrameDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class EmployeeDtoMapper {

    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getUnavailabilityList().stream()
                        .map(EmployeeDtoMapper::toTimeFrameDTO)
                        .collect(Collectors.toList()));
    }

    public static EmployeeDTO toEmployeeDTO(Employee employee, Schedule schedule) {
        if (employee == null) {
            return null;
        }
        EmployeeDTO employeeDTO =
                new EmployeeDTO(
                        employee.getId(),
                        employee.getName(),
                        employee.getUnavailabilityList().stream()
                                .map(EmployeeDtoMapper::toTimeFrameDTO)
                                .collect(Collectors.toList()));
        int numberOfWorkingShifts = schedule.countWorkingShiftsForEmployee(employee);
        employeeDTO.setNumberOfShifts(numberOfWorkingShifts);
        return employeeDTO;
    }

    public static Employee toEmployee(EmployeeDTO employeeDTO) {

        if (employeeDTO == null) {
            return null;
        }
        Employee employee = new Employee();
        if (employeeDTO.getId() != null) {
            employee.setId(employeeDTO.getId());
        }
        employee.setName(employeeDTO.getName());
        if (employeeDTO.getUnavailabilityList() != null) {
            employee.setUnavailabilityList(
                    employeeDTO.getUnavailabilityList().stream()
                            .map(EmployeeDtoMapper::toTimeFrame)
                            .collect(Collectors.toList()));
        }

        return employee;
    }

    public static ShiftDTO toShiftDTO(Shift shift) {
        return new ShiftDTO(
                shift.getId(),
                shift.getStartTime(),
                shift.getEndTime(),
                toEmployeeDTO(shift.getEmployee()));
    }

    public static Shift toShift(ShiftDTO shiftDTO) {
        if (shiftDTO.getEmployee() == null) {
            return new Shift(shiftDTO.getId(), shiftDTO.getStartTime(), shiftDTO.getEndTime());
        } else {
            return new Shift(
                    shiftDTO.getId(),
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
