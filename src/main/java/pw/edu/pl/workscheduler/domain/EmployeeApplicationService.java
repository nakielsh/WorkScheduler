package pw.edu.pl.workscheduler.domain;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.edu.pl.workscheduler.domain.commands.AddEmployeeToScheduleCommand;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.repository.EmployeePort;
import pw.edu.pl.workscheduler.domain.repository.SchedulePort;

@Service
public class EmployeeApplicationService {

    SchedulePort schedulePort;
    EmployeePort employeePort;

    @Autowired
    public EmployeeApplicationService(SchedulePort schedulePort, EmployeePort employeePort) {
        this.schedulePort = schedulePort;
        this.employeePort = employeePort;
    }

    public ScheduleDTO addEmployeeToSchedule(AddEmployeeToScheduleCommand command) {
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
                command.getUnavailability().stream().map(DtoMapper::toTimeFrame).collect(
                    Collectors.toSet()));
            employeeDTO = DtoMapper.toEmployeeDTO(employee);
        }
        return employeeDTO;
    }

    private Schedule getSchedule(Long scheduleId) {
        ScheduleDTO scheduleDTO = schedulePort.getSchedule(scheduleId);
        return DtoMapper.toSchedule(scheduleDTO);
    }
}
