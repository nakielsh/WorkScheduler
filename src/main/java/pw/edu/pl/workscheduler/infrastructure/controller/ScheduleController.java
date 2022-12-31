package pw.edu.pl.workscheduler.infrastructure.controller;

import static pw.edu.pl.workscheduler.domain.commands.CommandMapper.toAddEmployeeToScheduleCommand;
import static pw.edu.pl.workscheduler.domain.commands.CommandMapper.toInitiateScheduleCommand;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pw.edu.pl.workscheduler.domain.ScheduleFacade;
import pw.edu.pl.workscheduler.domain.commands.AddEmployeeToScheduleCommand;
import pw.edu.pl.workscheduler.domain.commands.InitiateScheduleCommand;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.infrastructure.controller.request.AddEmployeeToScheduleRequest;
import pw.edu.pl.workscheduler.infrastructure.controller.request.InitiateScheduleRequest;

@RestController
@AllArgsConstructor
class ScheduleController {

    // @TODO add empty days to scheduleDTO
    // @TODO add summary of number of workingSchedules in EmployeeDTO

    // @TODO add new employee to generated schedule
    // @TODO exchange existing employee in shift

    // @TODO get all shifts endpoint

    private final ScheduleFacade scheduleFacade;

    @PostMapping("/schedule/generate")
    ScheduleDTO generateSchedule(@RequestBody InitiateScheduleRequest request) {
        InitiateScheduleCommand command = toInitiateScheduleCommand(request);

        return scheduleFacade.generateSchedule(command);
    }

    @GetMapping("/schedule/{id}")
    ScheduleDTO getSchedule(@PathVariable(name = "id") Long id) {
        return scheduleFacade.getSchedule(id);
    }

    @PostMapping("/schedule/employee")
    ScheduleDTO addEmployeeToSchedule(@RequestBody AddEmployeeToScheduleRequest request) {
        AddEmployeeToScheduleCommand command = toAddEmployeeToScheduleCommand(request);

        return scheduleFacade.addEmployeeToSchedule(command);
    }
}
