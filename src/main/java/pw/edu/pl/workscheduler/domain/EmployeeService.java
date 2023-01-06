package pw.edu.pl.workscheduler.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import pw.edu.pl.workscheduler.domain.commands.AddEmployeeCommand;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.domain.ports.EmployeeOutputPort;
import pw.edu.pl.workscheduler.domain.ports.ScheduleOutputPort;

@AllArgsConstructor
class EmployeeService {

    private final ScheduleOutputPort schedulePort;
    private final EmployeeOutputPort employeePort;

    List<EmployeeDTO> getAllEmployees() {
        return employeePort.getAllEmployees();
    }

    EmployeeDTO addEmployee(AddEmployeeCommand command) {
        return employeePort.saveEmployee(getEmployeeDTO(command));
    }

    List<EmployeeDTO> getAvailableEmployeesForShift(Long shiftId) {
        Schedule schedule =
                ScheduleDtoMapper.toSchedule(schedulePort.getScheduleByShiftId(shiftId));
        Shift shift =
                schedule.getShiftDays().stream()
                        .map(ShiftDay::getShiftsForADay)
                        .flatMap(List::stream)
                        .filter(s -> s.getId().equals(shiftId))
                        .findFirst()
                        .orElseThrow();

        return schedule.getEmployeesAvailableForShift(shift).stream()
                .map(EmployeeDtoMapper::toEmployeeDTO)
                .toList();
    }

    private EmployeeDTO getEmployeeDTO(AddEmployeeCommand command) {
        return new EmployeeDTO(null, command.getName(), command.getUnavailability());
    }
}
