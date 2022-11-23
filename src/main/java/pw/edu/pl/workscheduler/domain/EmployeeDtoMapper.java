package pw.edu.pl.workscheduler.domain;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.domain.dto.ShiftDTO;
import pw.edu.pl.workscheduler.domain.dto.TimeFrameDTO;

@SuppressWarnings("java:S6204")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class EmployeeDtoMapper {

    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        if (employee == null) {
            return new EmployeeDTO(null, null, null, null);
        }
        return new EmployeeDTO(
            employee.getId(),
            employee.getName(),
            employee.getUnavailabilityList().stream()
                .map(EmployeeDtoMapper::toTimeFrameDTO)
                .collect(Collectors.toList()),
            getShifts(employee));
    }

    private static List<ShiftDTO> getShifts(Employee employee) {
        return employee.getShifts() == null
            ? null
            : employee.getShifts().stream()
                .map(EmployeeDtoMapper::toShiftDTO)
                .collect(Collectors.toList());
    }

    public static Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        if (employeeDTO != null) {
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
            if (employeeDTO.getShifts() != null) {
                employee.setShifts(
                    employeeDTO.getShifts().stream()
                        .map(EmployeeDtoMapper::toShift)
                        .collect(Collectors.toList()));
            }
        }

        return employee;
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
