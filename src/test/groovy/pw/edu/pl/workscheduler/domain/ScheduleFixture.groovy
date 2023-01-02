package pw.edu.pl.workscheduler.domain

import pw.edu.pl.workscheduler.domain.commands.InitiateScheduleCommand
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO
import pw.edu.pl.workscheduler.domain.dto.ShiftDTO
import pw.edu.pl.workscheduler.domain.dto.ShiftDayDTO

import java.time.LocalTime
import java.time.YearMonth

trait ScheduleFixture {

    InitiateScheduleCommand initiateScheduleCommand(int month, List<Long> employeeIds) {
        new InitiateScheduleCommand(
                YearMonth.of(2022, month),
                LocalTime.of(8, 0),
                LocalTime.of(23, 0),
                List.of(LocalTime.of(15, 0), LocalTime.of(19, 0)),
                List.of(1L, 2L)
        )
    }

    boolean isScheduleDTOEqual(ScheduleDTO scheduleDTO1, ScheduleDTO scheduleDTO2) {
        scheduleDTO1.shiftDays.sort(Comparator.comparing(ShiftDayDTO::getId))
        scheduleDTO2.shiftDays.sort(Comparator.comparing(ShiftDayDTO::getId))

        scheduleDTO1.shiftDays.each { day -> day.getShiftsForADay().sort(Comparator.comparing(ShiftDTO::getId)) }
        scheduleDTO2.shiftDays.each { day -> day.getShiftsForADay().sort(Comparator.comparing(ShiftDTO::getId)) }
        return scheduleDTO1.id == scheduleDTO2.id &&
                scheduleDTO1.month == scheduleDTO2.month &&
                scheduleDTO1.shiftDays == scheduleDTO2.shiftDays &&
                scheduleDTO1.employeeList == scheduleDTO2.employeeList
    }

}