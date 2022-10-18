package pw.edu.pl.workscheduler.application.service;

import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.edu.pl.workscheduler.adapter.out.persistence.ServicePersistentAdapter;
import pw.edu.pl.workscheduler.domain.Schedule;

@Service
public class ScheduleApplicationService {

    ServicePersistentAdapter persistentAdapter;

    @Autowired
    public ScheduleApplicationService(ServicePersistentAdapter persistentAdapter) {
        this.persistentAdapter = persistentAdapter;
    }

    public Schedule initializeSchedule(
            YearMonth month, LocalTime startTime, LocalTime endTime, List<LocalTime> shifts) {
        Schedule schedule = new Schedule();
        schedule.setMonth(month);
        schedule.generateShiftDays(startTime, endTime, shifts);

        return persistentAdapter.saveSchedule(schedule);
    }
}
