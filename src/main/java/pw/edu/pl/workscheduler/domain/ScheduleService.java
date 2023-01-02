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

    List<ScheduleDTO> getAllSchedules() {
        return schedulePort.getAllSchedules();
    }

    private List<Employee> getEmployeesFromId(List<Long> employeeIds) {
        return employeeIds.stream()
            .map(employeePort::getEmployeeById)
            .map(EmployeeDtoMapper::toEmployee)
            .toList();
    }

    ScheduleDTO generateSchedule(Long scheduleId) {
        Schedule schedule = ScheduleDtoMapper.toSchedule(schedulePort.getSchedule(scheduleId));
        Schedule generatedSchedule = new BOE(schedule).generateSchedule();

        return schedulePort.saveSchedule(ScheduleDtoMapper.toScheduleDTO(generatedSchedule));
    }

    ScheduleDTO addEmployeeToSchedule(Long scheduleId, Long employeeId) {
        Schedule schedule = ScheduleDtoMapper.toSchedule(schedulePort.getSchedule(scheduleId));
        Employee employee = EmployeeDtoMapper.toEmployee(employeePort.getEmployeeById(employeeId));
        schedule.addEmployee(employee);
        return schedulePort.saveSchedule(ScheduleDtoMapper.toScheduleDTO(schedule));
    }
}
