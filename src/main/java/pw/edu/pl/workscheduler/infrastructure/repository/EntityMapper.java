package pw.edu.pl.workscheduler.infrastructure.repository;

import java.util.List;
import java.util.stream.Collectors;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.dto.ShiftDTO;
import pw.edu.pl.workscheduler.domain.dto.ShiftDayDTO;
import pw.edu.pl.workscheduler.domain.dto.TimeFrameDTO;

class EntityMapper {

    public static ScheduleEntity toEntity(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        if (scheduleDTO.getId() != null) {
            scheduleEntity.setId(scheduleEntity.getId());
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
        shiftDayEntity.setShiftsForADay(
            shiftDayDTO.getShiftsForADay().stream()
                .map(EntityMapper::toEntity)
                .collect(Collectors.toList()));
        shiftDayEntity.setDate(shiftDayDTO.getDate());

        return shiftDayEntity;
    }

    public static ShiftEntity toEntity(ShiftDTO shiftDTO) {
        ShiftEntity shiftEntity = new ShiftEntity();
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
            shiftDayEntity.getDate(),
            shiftDayEntity.getShiftsForADay().stream()
                .map(EntityMapper::toShiftDTO)
                .collect(Collectors.toList()));
    }

    public static ShiftDTO toShiftDTO(ShiftEntity shiftEntity) {

        return new ShiftDTO(
            shiftEntity.getStartTime(),
            shiftEntity.getEndTime(),
            //                toEmployeeDTO(shiftEntity.getEmployee()));
            null);
    }

    public static EmployeeDTO toEmployeeDTO(EmployeeEntity employeeEntity) {
        //        Employee employee = new Employee();
        //        if (employeeEntity != null) {
        //            employee.setId(employeeEntity.getId());
        //            employee.setName(employeeEntity.getName());
        //            employee.setUnavailabilityList(
        //                    employeeEntity.getUnavailabilityList().stream().toList());
        //            if (employeeEntity.getShifts() != null) {
        //                employee.setShifts(
        //                        employeeEntity.getShifts().stream()
        //                                .map(this::toDomain)
        //                                .collect(Collectors.toList()));
        //            }
        //        }

        if (employeeEntity == null) {
            return null;
        }
        return new EmployeeDTO(
            employeeEntity.getId(),
            employeeEntity.getName(),
            getUnavailabilityList(employeeEntity),
            getShifts(employeeEntity));
    }

    private static List<TimeFrameDTO> getUnavailabilityList(EmployeeEntity employeeEntity) {
        return employeeEntity.getUnavailabilityList() == null
            ? null
            : employeeEntity.getUnavailabilityList().stream().toList();
    }

    private static List<ShiftDTO> getShifts(EmployeeEntity employeeEntity) {
        return employeeEntity.getShifts() == null
            ? null
            : employeeEntity.getShifts().stream().map(EntityMapper::toShiftDTO).toList();
    }
}
