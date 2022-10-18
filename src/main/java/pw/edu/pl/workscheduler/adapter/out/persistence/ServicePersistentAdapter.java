package pw.edu.pl.workscheduler.adapter.out.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pw.edu.pl.workscheduler.adapter.ScheduleEntity;
import pw.edu.pl.workscheduler.application.out.SchedulePort;
import pw.edu.pl.workscheduler.domain.Schedule;

@Component
public class ServicePersistentAdapter implements SchedulePort {

    ScheduleRepository scheduleRepository;
    ShiftDaysRepository shiftDaysRepository;
    ShiftRepository shiftRepository;
    EmployeeRepository employeeRepository;

    ScheduleMapper scheduleMapper;

    @Autowired
    public ServicePersistentAdapter(
            ScheduleRepository scheduleRepository,
            ShiftDaysRepository shiftDaysRepository,
            ScheduleMapper scheduleMapper,
            ShiftRepository shiftRepository,
            EmployeeRepository employeeRepository) {
        this.scheduleRepository = scheduleRepository;
        this.shiftDaysRepository = shiftDaysRepository;
        this.shiftRepository = shiftRepository;
        this.employeeRepository = employeeRepository;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        ScheduleEntity scheduleEntity = scheduleRepository.save(scheduleMapper.toEntity(schedule));

        return scheduleMapper.toDomain(scheduleEntity);
    }

    @Override
    public Schedule getSchedule(Long scheduleId) {
        return scheduleMapper.toDomain(
                scheduleRepository.findById(scheduleId).orElseThrow(IllegalArgumentException::new));
    }
}
