package pw.edu.pl.workscheduler.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import pw.edu.pl.workscheduler.domain.commands.InitiateScheduleCommand;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.ports.EmployeeOutputPort;
import pw.edu.pl.workscheduler.domain.ports.ScheduleOutputPort;

@AllArgsConstructor
class ScheduleService {

    private final ScheduleOutputPort schedulePort;
    private final EmployeeOutputPort employeePort;

    ScheduleDTO initializeSchedule(InitiateScheduleCommand command) {

        Schedule schedule = new Schedule();
        schedule.setMonth(command.getMonth());
        schedule.setEmployeeList(getEmployeesFromId(command.getEmployeeIds()));
        schedule.generateShiftDays(
            command.getStartTime(), command.getEndTime(), command.getShiftTimes());

        return schedulePort.saveSchedule(ScheduleDtoMapper.toScheduleDTO(schedule));
    }

    ScheduleDTO getSchedule(Long scheduleId) {
        return schedulePort.getSchedule(scheduleId);
    }

    private List<Employee> getEmployeesFromId(List<Long> employeeIds) {
        return employeeIds.stream()
            .map(employeePort::getEmployeeById)
            .map(EmployeeDtoMapper::toEmployee)
            .toList();
    }

}
