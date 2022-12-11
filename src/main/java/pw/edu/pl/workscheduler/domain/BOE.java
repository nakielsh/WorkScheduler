package pw.edu.pl.workscheduler.domain;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class BOE {

    private final Schedule schedule;

    private int maxWorkingShifts;

    Schedule generateSchedule() {
        calculateMaxWorkingShifts();
        getLeastAvailableEmployeeOverall();
        Shift leastWantedShift = getLeastWantedShift();

        while (leastWantedShift != null) {
            Employee leastAvailableEmployee = getLeastAvailableEmployeeAtShift(leastWantedShift);
            addEmployeeToShift(leastAvailableEmployee, leastWantedShift.getId());

            leastWantedShift = getLeastWantedShift();
        }

        return schedule;
    }

    private void calculateMaxWorkingShifts() {
        AtomicInteger numberOfShifts = new AtomicInteger();
        schedule.getShiftDays()
                .forEach(
                        shiftDay -> {
                            if (!shiftDay.isWeekend()) {
                                numberOfShifts.addAndGet(shiftDay.getShiftsForADay().size());
                            }
                        });

        maxWorkingShifts = numberOfShifts.get() / schedule.getEmployeeList().size() + 1;

        setMaxWorkingShifts();
    }

    private Employee getLeastAvailableEmployeeAtShift(Shift shift) {
        return getEmployeesForShift(shift).stream()
                .map(Employee::getAvailableShiftsNumber)
                .min(Integer::compareTo)
                .flatMap(
                        min ->
                                getEmployeesForShift(shift).stream()
                                        .filter(
                                                employee ->
                                                        employee.getAvailableShiftsNumber() == min)
                                        .findFirst())
                .orElseThrow();
    }

    private void addEmployeeToShift(Employee employee, long id) {
        schedule.getShiftDays().stream()
                .flatMap(shiftDay -> shiftDay.getShiftsForADay().stream())
                .filter(shift -> shift.getId() == id)
                .findFirst()
                .ifPresent(
                        shift -> {
                            employee.addShift(shift);
                            shift.setEmployee(getEmployeeById(employee.getId()));
                        });
    }

    private Employee getEmployeeById(long id) {
        return schedule.getEmployeeList().stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    private List<Employee> getEmployeesForShift(Shift shift) {
        return schedule.getEmployeeList().stream()
                .filter(shift::canBeAssigned)
                .collect(Collectors.toList());
    }

    private Employee getLeastAvailableEmployeeOverall() {
        return schedule.getEmployeeList().stream()
                .map(employee -> employee.calculateAvailability(getUnassignedShifts()))
                .min(Integer::compareTo)
                .flatMap(
                        min ->
                                schedule.getEmployeeList().stream()
                                        .filter(
                                                employee ->
                                                        employee.getAvailableShiftsNumber() == min)
                                        .findFirst())
                .orElseThrow();
    }

    private Shift getLeastWantedShift() {
        return getUnassignedShifts().stream()
                .map(shift -> shift.calculatePossibleEmployees(schedule.getEmployeeList()))
                .min(Integer::compareTo)
                .flatMap(
                        min ->
                                getUnassignedShifts().stream()
                                        .filter(
                                                shift ->
                                                        shift.getPossibleEmployees() == min
                                                                || shift.getPossibleEmployees()
                                                                        != 0)
                                        .findFirst())
                .orElse(null);
    }

    private void setMaxWorkingShifts() {
        schedule.getEmployeeList().forEach(employee -> employee.setAllShiftsLeft(maxWorkingShifts));
    }

    private List<Shift> getUnassignedShifts() {
        return schedule.getShiftDays().stream()
                .filter(shiftDay -> !shiftDay.isWeekend())
                .flatMap(shiftDay -> shiftDay.getShiftsForADay().stream())
                .filter(shift -> !shift.isAssigned())
                .collect(Collectors.toList());
    }
}
