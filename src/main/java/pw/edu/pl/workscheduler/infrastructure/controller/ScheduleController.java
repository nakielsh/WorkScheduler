package pw.edu.pl.workscheduler.infrastructure.controller;

import static pw.edu.pl.workscheduler.domain.CommandMapper.toAddEmployeeToScheduleCommand;
import static pw.edu.pl.workscheduler.domain.CommandMapper.toInitiateScheduleCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pw.edu.pl.workscheduler.domain.EmployeeApplicationService;
import pw.edu.pl.workscheduler.domain.ScheduleApplicationService;
import pw.edu.pl.workscheduler.domain.commands.AddEmployeeToScheduleCommand;
import pw.edu.pl.workscheduler.domain.commands.InitiateScheduleCommand;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.infrastructure.repository.ServicePersistentAdapter;

@RestController
class ScheduleController {

    ScheduleApplicationService scheduleApplicationService;
    EmployeeApplicationService employeeApplicationService;
    ServicePersistentAdapter servicePersistentAdapter;

    @Autowired
    public ScheduleController(
            ScheduleApplicationService scheduleApplicationService,
            EmployeeApplicationService employeeApplicationService,
            ServicePersistentAdapter servicePersistentAdapter) {
        this.scheduleApplicationService = scheduleApplicationService;
        this.employeeApplicationService = employeeApplicationService;
        this.servicePersistentAdapter = servicePersistentAdapter;
    }

    @PostMapping()
    public @ResponseBody ScheduleDTO initiateSchedule(
            @RequestBody InitiateScheduleRequest request) {
        InitiateScheduleCommand command = toInitiateScheduleCommand(request);

        return scheduleApplicationService.initializeSchedule(command);
    }

    @GetMapping("/{scheduleId}")
    public @ResponseBody ScheduleDTO getSchedule(
            @PathVariable(name = "scheduleId") Long scheduleId) {
        return servicePersistentAdapter.getSchedule(scheduleId);
    }

    @PostMapping("/schedule/employee")
    public @ResponseBody ScheduleDTO addEmployeeToSchedule(
            @RequestBody AddEmployeeToScheduleRequest request) {
        AddEmployeeToScheduleCommand command = toAddEmployeeToScheduleCommand(request);

        return employeeApplicationService.addEmployeeToSchedule(command);
    }
}
