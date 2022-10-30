package pw.edu.pl.workscheduler.domain;

import pw.edu.pl.workscheduler.domain.commands.AddEmployeeToScheduleCommand;
import pw.edu.pl.workscheduler.domain.commands.InitiateScheduleCommand;
import pw.edu.pl.workscheduler.infrastructure.controller.AddEmployeeToScheduleRequest;
import pw.edu.pl.workscheduler.infrastructure.controller.InitiateScheduleRequest;

public class CommandMapper {

    public static AddEmployeeToScheduleCommand toAddEmployeeToScheduleCommand(
            AddEmployeeToScheduleRequest request) {
        return new AddEmployeeToScheduleCommand(
                request.getScheduleId(), request.getName(), request.getUnavailability());
    }

    public static InitiateScheduleCommand toInitiateScheduleCommand(
            InitiateScheduleRequest request) {
        return new InitiateScheduleCommand(
                request.getMonth(),
                request.getYear(),
                request.getStartTime(),
                request.getEndTime(),
                request.getShiftTimes());
    }
}
