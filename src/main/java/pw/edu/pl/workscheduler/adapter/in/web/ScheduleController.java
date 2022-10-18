package pw.edu.pl.workscheduler.adapter.in.web;

import java.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pw.edu.pl.workscheduler.adapter.out.persistence.ServicePersistentAdapter;
import pw.edu.pl.workscheduler.application.service.ScheduleApplicationService;
import pw.edu.pl.workscheduler.domain.Schedule;

@RestController
public class ScheduleController {

    ScheduleApplicationService scheduleApplicationService;
    ServicePersistentAdapter servicePersistentAdapter;

    @Autowired
    public ScheduleController(
            ScheduleApplicationService scheduleApplicationService,
            ServicePersistentAdapter servicePersistentAdapter) {
        this.scheduleApplicationService = scheduleApplicationService;
        this.servicePersistentAdapter = servicePersistentAdapter;
    }

    @PostMapping()
    public @ResponseBody Schedule initiateSchedule(@RequestBody InitiateScheduleRequest request) {

        return scheduleApplicationService.initializeSchedule(
                YearMonth.of(request.getYear(), request.getMonth()),
                request.getStartTime(),
                request.getEndTime(),
                request.getShiftTimes());
    }

    @GetMapping("/{scheduleId}")
    public @ResponseBody Schedule getSchedule(@PathVariable(name = "scheduleId") Long scheduleId) {
        return servicePersistentAdapter.getSchedule(scheduleId);
    }
}
