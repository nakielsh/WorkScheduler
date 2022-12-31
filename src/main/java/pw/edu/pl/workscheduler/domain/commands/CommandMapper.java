package pw.edu.pl.workscheduler.domain.commands;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pw.edu.pl.workscheduler.infrastructure.controller.request.AddEmployeeRequest;
import pw.edu.pl.workscheduler.infrastructure.controller.request.AddEmployeeToScheduleRequest;
import pw.edu.pl.workscheduler.infrastructure.controller.request.InitiateScheduleRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandMapper {

    public static AddEmployeeToScheduleCommand toAddEmployeeToScheduleCommand(
        AddEmployeeToScheduleRequest request) {
        return new AddEmployeeToScheduleCommand(
            request.getScheduleId(), request.getName(), request.getTimeframes());
    }

    public static AddEmployeeCommand toAddEmployeeCommand(AddEmployeeRequest request) {
        return new AddEmployeeCommand(request.getName(), request.getUnavailability());
    }

    public static InitiateScheduleCommand toInitiateScheduleCommand(
        InitiateScheduleRequest request) {
        return new InitiateScheduleCommand(
            request.getMonth(),
            request.getStartTime(),
            request.getEndTime(),
            request.getShiftTimes(),
            request.getEmployeeIds());
    }
}
