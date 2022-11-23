package pw.edu.pl.workscheduler.infrastructure.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SchedulePersistenceConfiguration {

    @Bean
    ServicePersistentAdapter servicePersistentAdapter(
            ScheduleRepository scheduleRepository,
            ShiftDaysRepository shiftDaysRepository,
            ShiftRepository shiftRepository,
            EmployeeRepository employeeRepository) {
        return new ServicePersistentAdapter(scheduleRepository, shiftDaysRepository, shiftRepository, employeeRepository);
    }
}
