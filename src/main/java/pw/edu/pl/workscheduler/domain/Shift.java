package pw.edu.pl.workscheduler.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
class Shift {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Employee employee = null;

    private int possibleEmployees = 0;

    public Shift(long id, LocalDateTime startTime, LocalDateTime endTime, Employee employee) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
    }

    public Shift(long id, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Shift(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    boolean canBeAssigned(Employee employee) {
        for (TimeFrame timeFrame : employee.getUnavailabilityList()) {
            if (timeFrame.getStartTime().isBefore(startTime)
                    && timeFrame.getEndTime().isAfter(startTime)) {
                return false;
            }
            if (timeFrame.getStartTime().isBefore(endTime)
                    && timeFrame.getEndTime().isAfter(endTime)) {
                return false;
            }
            if (timeFrame.getStartTime().isAfter(startTime)
                    && timeFrame.getEndTime().isBefore(endTime)) {
                return false;
            }
            if (timeFrame.getStartTime().equals(startTime)
                    || timeFrame.getEndTime().equals(endTime)) {
                return false;
            }
        }
        return true;
    }

    int calculatePossibleEmployees(List<Employee> employees) {
        int tempPossibleEmployees = 0;
        for (Employee empl : employees) {
            if (canBeAssigned(empl)) {
                tempPossibleEmployees++;
            }
        }
        possibleEmployees = tempPossibleEmployees;
        return possibleEmployees;
    }

    boolean isAssigned() {
        return employee != null;
    }
}
