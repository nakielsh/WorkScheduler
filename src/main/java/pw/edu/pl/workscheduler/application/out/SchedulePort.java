package pw.edu.pl.workscheduler.application.out;

import pw.edu.pl.workscheduler.domain.Schedule;

public interface SchedulePort {

    Schedule saveSchedule(Schedule schedule);

    Schedule getSchedule(Long scheduleId);
}
