package pw.edu.pl.workscheduler.infrastructure.controller;

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

import static pw.edu.pl.workscheduler.domain.commands.CommandMapper.toAddEmployeeToScheduleCommand;
import static pw.edu.pl.workscheduler.domain.commands.CommandMapper.toInitiateScheduleCommand;

@RestController
@AllArgsConstructor
class ScheduleController {

    private final ScheduleFacade scheduleFacade;

    @PostMapping
    ScheduleDTO initiateSchedule(@RequestBody InitiateScheduleRequest request) {
        InitiateScheduleCommand command = toInitiateScheduleCommand(request);

        return scheduleFacade.initializeSchedule(command);
    }

    @GetMapping("/{id}")
    ScheduleDTO getSchedule(@PathVariable(name = "id") Long id) {
        return scheduleFacade.getSchedule(id);
    }

    @PostMapping("/schedule/employee")
    ScheduleDTO addEmployeeToSchedule(@RequestBody AddEmployeeToScheduleRequest request) {
        AddEmployeeToScheduleCommand command = toAddEmployeeToScheduleCommand(request);

        return scheduleFacade.addEmployeeToSchedule(command);
    }
}
