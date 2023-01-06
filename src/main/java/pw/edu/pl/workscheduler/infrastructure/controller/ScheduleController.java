package pw.edu.pl.workscheduler.infrastructure.controller;

import static pw.edu.pl.workscheduler.domain.commands.CommandMapper.toAddEmployeeToScheduleCommand;
import static pw.edu.pl.workscheduler.domain.commands.CommandMapper.toInitiateScheduleCommand;

import java.util.List;
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

    // @TODO exchange existing employee in shift

    // @TODO get available employees for shift endpoint

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

    @GetMapping("/schedules")
    List<ScheduleDTO> getAllSchedules() {
        return scheduleFacade.getAllSchedules();
    }

    @PostMapping("/schedule/employee")
    ScheduleDTO addEmployeeToSchedule(@RequestBody AddEmployeeToScheduleRequest request) {
        AddEmployeeToScheduleCommand command = toAddEmployeeToScheduleCommand(request);

        return scheduleFacade.addEmployeeToSchedule(command);
    }
}
