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
import pw.edu.pl.workscheduler.domain.dto.CompactScheduleDTO;
import pw.edu.pl.workscheduler.domain.dto.SuperCompactScheduleDTO;
import pw.edu.pl.workscheduler.infrastructure.controller.request.AddEmployeeToScheduleRequest;
import pw.edu.pl.workscheduler.infrastructure.controller.request.InitiateScheduleRequest;

@RestController
@AllArgsConstructor
class ScheduleController {

    // @TODO exchange existing employee in shift

    private final ScheduleFacade scheduleFacade;

    @PostMapping("/schedule/generate")
    CompactScheduleDTO generateSchedule(@RequestBody InitiateScheduleRequest request) {
        InitiateScheduleCommand command = toInitiateScheduleCommand(request);

        return CompactDtoMapper.toCompactScheduleDTO(scheduleFacade.generateSchedule(command));
    }

    @GetMapping("/schedule/{id}")
    CompactScheduleDTO getSchedule(@PathVariable(name = "id") Long id) {
        return CompactDtoMapper.toCompactScheduleDTO(scheduleFacade.getSchedule(id));
    }

    @GetMapping("/schedules")
    List<SuperCompactScheduleDTO> getAllSchedules() {
        return scheduleFacade.getAllSchedules().stream()
                .map(CompactDtoMapper::toSuperCompactScheduleDTO)
                .toList();
    }

    @PostMapping("/schedule/employee")
    CompactScheduleDTO addEmployeeToSchedule(@RequestBody AddEmployeeToScheduleRequest request) {
        AddEmployeeToScheduleCommand command = toAddEmployeeToScheduleCommand(request);

        return CompactDtoMapper.toCompactScheduleDTO(scheduleFacade.addEmployeeToSchedule(command));
    }
}
