package pw.edu.pl.workscheduler.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pw.edu.pl.workscheduler.domain.ports.EmployeeOutputPort;
import pw.edu.pl.workscheduler.domain.ports.ScheduleOutputPort;

@Configuration
class ScheduleConfiguration {

    @Bean
    ScheduleFacade scheduleFacade(EmployeeService employeeService, ScheduleService scheduleService) {
        return new ScheduleFacade(employeeService, scheduleService);
    }

    @Bean
    EmployeeService employeeService(EmployeeOutputPort employeeOutputPort, ScheduleOutputPort scheduleOutputPort) {
        return new EmployeeService(scheduleOutputPort, employeeOutputPort);
    }

    @Bean
    ScheduleService scheduleService(ScheduleOutputPort scheduleOutputPort) {
        return new ScheduleService(scheduleOutputPort);
    }
}
