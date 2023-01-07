package pw.edu.pl.workscheduler.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Schedule {

    private Long id;
    private String scheduleName;
    private String managerName;

    private YearMonth month;
    private List<ShiftDay> shiftDays = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();

    void generateShiftDays(LocalTime startTime, LocalTime endTime, List<LocalTime> shiftTimes) {
        for (int i = 0; i < month.lengthOfMonth(); i++) {
            shiftDays.add(
                    new ShiftDay(
                            LocalDate.of(month.getYear(), month.getMonth(), i + 1),
                            startTime,
                            endTime,
                            shiftTimes));
        }
    }

    void addEmployee(Employee employee) {
        if (isEmployeeInTheList(employee)) {
            employeeList.stream()
                    .filter(empl -> Objects.equals(empl.getName(), employee.getName()))
                    .findFirst()
                    .ifPresent(
                            empl -> empl.addToUnavailabilityList(employee.getUnavailabilityList()));
        } else {
            employeeList.add(employee);
        }
    }

    int countWorkingShiftsForEmployee(Employee employee) {
        int workingShifts = 0;
        for (ShiftDay shiftDay : shiftDays) {
            for (Shift shift : shiftDay.getShiftsForADay()) {
                if (shift.getEmployee() != null
                        && shift.getEmployee().getId().equals(employee.getId())) {
                    workingShifts++;
                }
            }
        }

        return workingShifts;
    }

    List<Shift> getEmptyShifts() {
        List<Shift> emptyShifts = new ArrayList<>();

        for (ShiftDay shiftDay : shiftDays) {
            if (!shiftDay.isWeekend()) {
                for (Shift shift : shiftDay.getShiftsForADay()) {
                    if (shift.getEmployee() == null) {
                        emptyShifts.add(shift);
                    }
                }
            }
        }
        return emptyShifts;
    }

    private boolean isEmployeeInTheList(Employee employee) {
        return employeeList.stream()
                .anyMatch(empl -> Objects.equals(empl.getName(), employee.getName()));
    }

    List<Employee> getEmployeesAvailableForShift(Shift shift) {
        return employeeList.stream().filter(shift::canBeAssigned).collect(Collectors.toList());
    }
}
