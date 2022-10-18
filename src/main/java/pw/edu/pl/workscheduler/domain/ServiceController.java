package pw.edu.pl.workscheduler.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/newschedule")
public class ServiceController {

    private final ScheduleService scheduleService;

    @Autowired
    public ServiceController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //    @PostMapping()
    //    public @ResponseBody Schedule test(@RequestBody InitiateScheduleRequest request) {
    //        return scheduleService.initializeSchedule(
    //                YearMonth.of(request.getYear(), request.getMonth()), request.getShiftTimes());
    //    }

    @GetMapping()
    public ResponseEntity<String> test1() {
        return ResponseEntity.ok("dupa");
    }
}
