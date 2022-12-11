package pw.edu.pl.workscheduler.infrastructure.repository;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.dto.ShiftDTO;
import pw.edu.pl.workscheduler.domain.dto.ShiftDayDTO;
import pw.edu.pl.workscheduler.domain.dto.TimeFrameDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class EntityMapper {

    public static ScheduleEntity toEntity(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        if (scheduleDTO.getId() != null) {
            scheduleEntity.setId(scheduleDTO.getId());
        }
        scheduleEntity.setScheduleMonth(scheduleDTO.getMonth());
        scheduleEntity.setShiftDays(
                scheduleDTO.getShiftDays().stream()
                        .map(EntityMapper::toEntity)
                        .collect(Collectors.toList()));
        scheduleEntity.setEmployeeList(
                scheduleDTO.getEmployeeList().stream()
                        .map(EntityMapper::toEntity)
                        .collect(Collectors.toList()));

        return scheduleEntity;
    }

    public static ShiftDayEntity toEntity(ShiftDayDTO shiftDayDTO) {
        ShiftDayEntity shiftDayEntity = new ShiftDayEntity();
        if (shiftDayDTO.getId() != null) {
            shiftDayEntity.setId(shiftDayDTO.getId());
        }
        shiftDayEntity.setShiftsForADay(
                shiftDayDTO.getShiftsForADay().stream()
                        .map(EntityMapper::toEntity)
                        .collect(Collectors.toList()));
        shiftDayEntity.setDate(shiftDayDTO.getDate());

        return shiftDayEntity;
    }

    public static ShiftEntity toEntity(ShiftDTO shiftDTO) {
        ShiftEntity shiftEntity = new ShiftEntity();
        if (shiftDTO.getId() != null) {
            shiftEntity.setId(shiftDTO.getId());
        }
        if (shiftDTO.getEmployee() != null) {
            shiftEntity.setEmployee(toEntity(shiftDTO.getEmployee()));
        }
        shiftEntity.setStartTime(shiftDTO.getStartTime());
        shiftEntity.setEndTime(shiftDTO.getEndTime());

        return shiftEntity;
    }

    public static EmployeeEntity toEntity(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        if (employeeDTO != null) {
            if (employeeDTO.getId() != null) {
                employeeEntity.setId(employeeDTO.getId());
            }
            employeeEntity.setName(employeeDTO.getName());
            employeeEntity.setUnavailabilityList(employeeDTO.getUnavailabilityList());
        }

        return employeeEntity;
    }

    public static ScheduleDTO toScheduleDTO(ScheduleEntity scheduleEntity) {
        return new ScheduleDTO(
                scheduleEntity.getId(),
                scheduleEntity.getScheduleMonth(),
                scheduleEntity.getShiftDays().stream()
                        .map(EntityMapper::toShiftDayDTO)
                        .collect(Collectors.toList()),
                scheduleEntity.getEmployeeList().stream()
                        .map(EntityMapper::toEmployeeDTO)
                        .collect(Collectors.toList()));
    }

    public static ShiftDayDTO toShiftDayDTO(ShiftDayEntity shiftDayEntity) {

        return new ShiftDayDTO(
                shiftDayEntity.getId(),
                shiftDayEntity.getDate(),
                shiftDayEntity.getShiftsForADay().stream()
                        .map(EntityMapper::toShiftDTO)
                        .collect(Collectors.toList()));
    }

    public static ShiftDTO toShiftDTO(ShiftEntity shiftEntity) {

        return new ShiftDTO(
                shiftEntity.getId(),
                shiftEntity.getStartTime(),
                shiftEntity.getEndTime(),
                toEmployeeDTO(shiftEntity.getEmployee()));
    }

    public static EmployeeDTO toEmployeeDTO(EmployeeEntity employeeEntity) {

        if (employeeEntity == null) {
            return null;
        }
        return new EmployeeDTO(
                employeeEntity.getId(),
                employeeEntity.getName(),
                getUnavailabilityList(employeeEntity));
    }

    private static List<TimeFrameDTO> getUnavailabilityList(EmployeeEntity employeeEntity) {
        return employeeEntity.getUnavailabilityList() == null
                ? null
                : employeeEntity.getUnavailabilityList().stream().toList();
    }
}
