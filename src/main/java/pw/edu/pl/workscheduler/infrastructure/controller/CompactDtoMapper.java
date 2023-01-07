package pw.edu.pl.workscheduler.infrastructure.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pw.edu.pl.workscheduler.domain.dto.CompactScheduleDTO;
import pw.edu.pl.workscheduler.domain.dto.CompactShiftDTO;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.dto.ShiftDTO;
import pw.edu.pl.workscheduler.domain.dto.ShiftDayDTO;
import pw.edu.pl.workscheduler.domain.dto.SuperCompactScheduleDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class CompactDtoMapper {

    static SuperCompactScheduleDTO toSuperCompactScheduleDTO(ScheduleDTO scheduleDTO) {
        return new SuperCompactScheduleDTO(
                scheduleDTO.getId(),
                scheduleDTO.getScheduleName(),
                scheduleDTO.getManagerName(),
                scheduleDTO.getMonth());
    }

    static CompactScheduleDTO toCompactScheduleDTO(ScheduleDTO scheduleDTO) {
        if (scheduleDTO == null) {
            return null;
        }
        CompactScheduleDTO compactSchedule =
                new CompactScheduleDTO(
                        scheduleDTO.getId(),
                        scheduleDTO.getScheduleName(),
                        scheduleDTO.getManagerName(),
                        scheduleDTO.getMonth(),
                        scheduleDTO.getShiftDays().stream()
                                .map(ShiftDayDTO::getShiftsForADay)
                                .flatMap(List::stream)
                                .map(CompactDtoMapper::toCompactShiftDTO)
                                .collect(Collectors.toList()),
                        scheduleDTO.getEmployeeList());

        compactSchedule.setEmptyShifts(
                scheduleDTO.getEmptyShifts().stream()
                        .map(CompactDtoMapper::toCompactShiftDTO)
                        .collect(Collectors.toList()));

        return compactSchedule;
    }

    static CompactShiftDTO toCompactShiftDTO(ShiftDTO shiftDTO) {
        if (shiftDTO == null) {
            return null;
        }
        CompactShiftDTO compactShift =
                new CompactShiftDTO(
                        shiftDTO.getId(), shiftDTO.getStartTime(), shiftDTO.getEndTime());

        if (shiftDTO.getEmployee() != null) {
            compactShift.setEmployeeId(shiftDTO.getEmployee().getId());
            compactShift.setEmployeeName(shiftDTO.getEmployee().getName());
        }

        return compactShift;
    }
}
