package pw.edu.pl.workscheduler.domain;

import lombok.AllArgsConstructor;
import pw.edu.pl.workscheduler.domain.commands.AddEmployeeToScheduleCommand;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.ports.EmployeeOutputPort;
import pw.edu.pl.workscheduler.domain.ports.ScheduleOutputPort;

@AllArgsConstructor
class EmployeeService {

    private final ScheduleOutputPort schedulePort;
    private final EmployeeOutputPort employeePort;

    //when there is no Employee entity in scope, you can just name vars 'employee'
    ScheduleDTO addEmployeeToSchedule(AddEmployeeToScheduleCommand command) {
        EmployeeDTO employeeDTO = getEmployeeDTO(command);

        Schedule schedule = getSchedule(command.getScheduleId());
        schedule.addEmployee(DtoMapper.toEmployee(employeeDTO));

        return schedulePort.updateSchedule(DtoMapper.toScheduleDTO(schedule));
    }

    private EmployeeDTO getEmployeeDTO(AddEmployeeToScheduleCommand command) {
        EmployeeDTO employeeDTO = employeePort.getEmployeeByName(command.getName());
        if (employeeDTO == null) {
            employeeDTO =
                    new EmployeeDTO(null, command.getName(), command.getUnavailability(), null);
        } else {
            Employee employee = DtoMapper.toEmployee(employeeDTO);
            employee.addToUnavailabilityList(
                    command.getUnavailability().stream().map(DtoMapper::toTimeFrame).toList());
            employeeDTO = DtoMapper.toEmployeeDTO(employee);
        }
        return employeeDTO;
    }

    private Schedule getSchedule(Long scheduleId) {
        ScheduleDTO scheduleDTO = schedulePort.getSchedule(scheduleId);
        return DtoMapper.toSchedule(scheduleDTO);
    }
}
