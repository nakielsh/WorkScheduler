package pw.edu.pl.workscheduler.domain.dto;

import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {

    //    public ScheduleEntity toEntity(Schedule schedule) {
    //        ScheduleEntity scheduleEntity = new ScheduleEntity();
    //        if (schedule.getId() != null) {
    //            scheduleEntity.setId(scheduleEntity.getId());
    //        }
    //        scheduleEntity.setScheduleMonth(schedule.getMonth());
    //        scheduleEntity.setShiftDays(
    //
    // schedule.getShiftDays().stream().map(this::toEntity).collect(Collectors.toList()));
    //        scheduleEntity.setEmployeeList(
    //                schedule.getEmployeeList().stream()
    //                        .map(this::toEntity)
    //                        .collect(Collectors.toList()));
    //
    //        return scheduleEntity;
    //    }

    //    public ShiftDayEntity toEntity(ShiftDay shiftDay) {
    //        ShiftDayEntity shiftDayEntity = new ShiftDayEntity();
    //        shiftDayEntity.setShiftsForADay(
    //                shiftDay.getShiftsForADay().stream()
    //                        .map(this::toEntity)
    //                        .collect(Collectors.toList()));
    //        shiftDayEntity.setDate(shiftDay.getDate());
    //
    //        return shiftDayEntity;
    //    }

    //    public ShiftEntity toEntity(Shift shift) {
    //        ShiftEntity shiftEntity = new ShiftEntity();
    //        if (shift.getEmployee() != null) {
    //            shiftEntity.setEmployee(toEntity(shift.getEmployee()));
    //        }
    //        shiftEntity.setStartTime(shift.getStartTime());
    //        shiftEntity.setEndTime(shift.getEndTime());
    //
    //        return shiftEntity;
    //    }

    //    public EmployeeEntity toEntity(Employee employee) {
    //        EmployeeEntity employeeEntity = new EmployeeEntity();
    //        if (employee != null) {
    //            employeeEntity.setName(employee.getName());
    //            employeeEntity.setUnavailabilityList(
    //                    employee.getUnavailabilityList().stream()
    //                            .map(DtoMapper::toTimeFrameDTO)
    //                            .collect(Collectors.toList()));
    //        }
    //
    //        return employeeEntity;
    //    }

    //    public Schedule toDomain(ScheduleEntity scheduleEntity) {
    //        Schedule schedule = new Schedule();
    //        schedule.setId(scheduleEntity.getId());
    //        schedule.setMonth(scheduleEntity.getScheduleMonth());
    //        schedule.setEmployeeList(
    //                scheduleEntity.getEmployeeList().stream()
    //                        .map(this::toDomain)
    //                        .collect(Collectors.toList()));
    //        schedule.setShiftDays(
    //                scheduleEntity.getShiftDays().stream()
    //                        .map(this::toDomain)
    //                        .collect(Collectors.toList()));
    //
    //        return schedule;
    //    }

    //    public ShiftDay toDomain(ShiftDayEntity shiftDayEntity) {
    //        ShiftDay shiftDay = new ShiftDay();
    //        shiftDay.setDate(shiftDayEntity.getDate());
    //        shiftDay.setShiftsForADay(
    //                shiftDayEntity.getShiftsForADay().stream()
    //                        .map(this::toDomain)
    //                        .collect(Collectors.toList()));
    //
    //        return shiftDay;
    //    }

    //    public Shift toDomain(ShiftEntity shiftEntity) {
    //        Shift shift = new Shift();
    //        shift.setStartTime(shiftEntity.getStartTime());
    //        shift.setEndTime(shiftEntity.getEndTime());
    //        shift.setEmployee(toDomain(shiftEntity.getEmployee()));
    //
    //        return shift;
    //    }
    //
    //    public Employee toDomain(EmployeeEntity employeeEntity) {
    //        Employee employee = new Employee();
    //        if (employeeEntity != null) {
    //            employee.setId(employeeEntity.getId());
    //            employee.setName(employeeEntity.getName());
    //            employee.setUnavailabilityList(
    //                    employeeEntity.getUnavailabilityList().stream()
    //                            .map(DtoMapper::toTimeFrame)
    //                            .toList());
    //            if (employeeEntity.getShifts() != null) {
    //                employee.setShifts(
    //                        employeeEntity.getShifts().stream()
    //                                .map(this::toDomain)
    //                                .collect(Collectors.toList()));
    //            }
    //        }
    //
    //        return employee;
    //    }
}
