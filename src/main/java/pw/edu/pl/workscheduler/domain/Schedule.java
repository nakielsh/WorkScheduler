package pw.edu.pl.workscheduler.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Schedule {

    private Long id;

    private YearMonth month;
    //    private final AppUser owner = new AppUser();
    private List<ShiftDay> shiftDays = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();

    public void generateShiftDays(
        LocalTime startTime, LocalTime endTime, List<LocalTime> shiftTimes) {
        for (int i = 0; i < month.lengthOfMonth(); i++) {
            shiftDays.add(
                new ShiftDay(
                    LocalDate.of(month.getYear(), month.getMonth(), i + 1),
                    startTime,
                    endTime,
                    shiftTimes));
        }
    }

    public void addEmployee(Employee employee) {
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

    private boolean isEmployeeInTheList(Employee employee) {
        return employeeList.stream()
                .anyMatch(empl -> Objects.equals(empl.getName(), employee.getName()));
    }
}
