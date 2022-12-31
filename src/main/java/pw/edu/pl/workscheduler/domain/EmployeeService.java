package pw.edu.pl.workscheduler.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import pw.edu.pl.workscheduler.domain.commands.AddEmployeeCommand;
import pw.edu.pl.workscheduler.domain.commands.AddEmployeeToScheduleCommand;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
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

    ScheduleDTO addEmployeeToSchedule(AddEmployeeToScheduleCommand command) {
        EmployeeDTO employeeDTO = getEmployeeDTO(command);

        Schedule schedule = getSchedule(command.getScheduleId());
        schedule.addEmployee(EmployeeDtoMapper.toEmployee(employeeDTO));

        return schedulePort.updateSchedule(ScheduleDtoMapper.toScheduleDTO(schedule));
    }

    private EmployeeDTO getEmployeeDTO(AddEmployeeCommand command) {
        return new EmployeeDTO(null, command.getName(), command.getUnavailability());
    }

    private EmployeeDTO getEmployeeDTO(AddEmployeeToScheduleCommand command) {
        EmployeeDTO employeeDTO = employeePort.getEmployeeByName(command.getName());
        if (employeeDTO == null) {
            employeeDTO = new EmployeeDTO(null, command.getName(), command.getUnavailability());
        } else {
            Employee employee = EmployeeDtoMapper.toEmployee(employeeDTO);
            employee.addToUnavailabilityList(
                command.getUnavailability().stream().map(EmployeeDtoMapper::toTimeFrame).toList());
            employeeDTO = EmployeeDtoMapper.toEmployeeDTO(employee);
        }
        return employeeDTO;
    }

    private Schedule getSchedule(Long scheduleId) {
        ScheduleDTO scheduleDTO = schedulePort.getSchedule(scheduleId);
        return ScheduleDtoMapper.toSchedule(scheduleDTO);
    }
}
