package pw.edu.pl.workscheduler.domain.commands;

import pw.edu.pl.workscheduler.infrastructure.controller.request.AddEmployeeToScheduleRequest;
import pw.edu.pl.workscheduler.infrastructure.controller.request.InitiateScheduleRequest;

public class CommandMapper {

    public static AddEmployeeToScheduleCommand toAddEmployeeToScheduleCommand(
            AddEmployeeToScheduleRequest request) {
        return new AddEmployeeToScheduleCommand(
                request.getScheduleId(), request.getName(), request.getTimeframes());
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
