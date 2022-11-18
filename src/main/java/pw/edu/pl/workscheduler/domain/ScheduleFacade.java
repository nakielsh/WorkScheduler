package pw.edu.pl.workscheduler.domain;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pw.edu.pl.workscheduler.domain.commands.AddEmployeeToScheduleCommand;
import pw.edu.pl.workscheduler.domain.commands.InitiateScheduleCommand;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;

@AllArgsConstructor
public class ScheduleFacade {

    private final EmployeeService employeeService;
    private final ScheduleService scheduleService;

    @Transactional
    public ScheduleDTO addEmployeeToSchedule(AddEmployeeToScheduleCommand command){
        return employeeService.addEmployeeToSchedule(command);
    }

    public ScheduleDTO initializeSchedule(InitiateScheduleCommand command) {
        return scheduleService.initializeSchedule(command);
    }

    public ScheduleDTO getSchedule(Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

}
