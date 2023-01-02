package pw.edu.pl.workscheduler.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pw.edu.pl.workscheduler.domain.commands.AddEmployeeCommand;
import pw.edu.pl.workscheduler.domain.commands.AddEmployeeToScheduleCommand;
import pw.edu.pl.workscheduler.domain.commands.CommandMapper;
import pw.edu.pl.workscheduler.domain.commands.InitiateScheduleCommand;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;

@AllArgsConstructor
public class ScheduleFacade {

    private final EmployeeService employeeService;
    private final ScheduleService scheduleService;

    @Transactional
    public ScheduleDTO addEmployeeToSchedule(AddEmployeeToScheduleCommand command){
        EmployeeDTO employeeToAdd =
                employeeService.addEmployee(CommandMapper.toAddEmployeeCommand(command));
        return scheduleService.addEmployeeToSchedule(
                command.getScheduleId(), employeeToAdd.getId());
    }

    public ScheduleDTO generateSchedule(InitiateScheduleCommand command) {
        ScheduleDTO initializedSchedule = scheduleService.initializeSchedule(command);
        return scheduleService.generateSchedule(initializedSchedule.getId());
    }

    public ScheduleDTO getSchedule(Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    public EmployeeDTO addEmployee(AddEmployeeCommand command) {
        return employeeService.addEmployee(command);
    }

}
