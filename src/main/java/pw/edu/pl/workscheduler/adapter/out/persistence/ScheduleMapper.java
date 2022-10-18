package pw.edu.pl.workscheduler.adapter.out.persistence;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import pw.edu.pl.workscheduler.adapter.EmployeeEntity;
import pw.edu.pl.workscheduler.adapter.ScheduleEntity;
import pw.edu.pl.workscheduler.adapter.ShiftDayEntity;
import pw.edu.pl.workscheduler.adapter.ShiftEntity;
import pw.edu.pl.workscheduler.domain.Employee;
import pw.edu.pl.workscheduler.domain.Schedule;
import pw.edu.pl.workscheduler.domain.Shift;
import pw.edu.pl.workscheduler.domain.ShiftDay;

@Component
class ScheduleMapper {

    ScheduleEntity toEntity(Schedule schedule) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        if (schedule.getId() != null) {
            scheduleEntity.setId(scheduleEntity.getId());
        }
        scheduleEntity.setScheduleMonth(schedule.getMonth());
        scheduleEntity.setShiftDays(
                schedule.getShiftDays().stream().map(this::toEntity).collect(Collectors.toList()));
        scheduleEntity.setEmployeeList(
                schedule.getEmployeeList().stream()
                        .map(this::toEntity)
                        .collect(Collectors.toList()));

        return scheduleEntity;
    }

    ShiftDayEntity toEntity(ShiftDay shiftDay) {
        ShiftDayEntity shiftDayEntity = new ShiftDayEntity();
        shiftDayEntity.setShiftsForADay(
                shiftDay.getShiftsForADay().stream()
                        .map(this::toEntity)
                        .collect(Collectors.toList()));
        shiftDayEntity.setDate(shiftDay.getDate());

        return shiftDayEntity;
    }

    ShiftEntity toEntity(Shift shift) {
        ShiftEntity shiftEntity = new ShiftEntity();
        shiftEntity.setEmployee(toEntity(shift.getEmployee()));
        shiftEntity.setStartTime(shift.getStartTime());
        shiftEntity.setEndTime(shift.getEndTime());

        return shiftEntity;
    }

    EmployeeEntity toEntity(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        //        employeeEntity.setName(employee.getName());
        //        employeeEntity.setShifts();

        return employeeEntity;
    }

    Schedule toDomain(ScheduleEntity scheduleEntity) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleEntity.getId());
        schedule.setMonth(scheduleEntity.getScheduleMonth());
        schedule.setEmployeeList(
                scheduleEntity.getEmployeeList().stream()
                        .map(this::toDomain)
                        .collect(Collectors.toList()));
        schedule.setShiftDays(
                scheduleEntity.getShiftDays().stream()
                        .map(this::toDomain)
                        .collect(Collectors.toList()));

        return schedule;
    }

    ShiftDay toDomain(ShiftDayEntity shiftDayEntity) {
        ShiftDay shiftDay = new ShiftDay();
        shiftDay.setDate(shiftDayEntity.getDate());
        shiftDay.setShiftsForADay(
                shiftDayEntity.getShiftsForADay().stream()
                        .map(this::toDomain)
                        .collect(Collectors.toList()));

        return shiftDay;
    }

    Shift toDomain(ShiftEntity shiftEntity) {
        Shift shift = new Shift();
        shift.setStartTime(shiftEntity.getStartTime());
        shift.setEndTime(shiftEntity.getEndTime());
        shift.setEmployee(toDomain(shiftEntity.getEmployee()));

        return shift;
    }

    Employee toDomain(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();

        return employee;
    }
}
