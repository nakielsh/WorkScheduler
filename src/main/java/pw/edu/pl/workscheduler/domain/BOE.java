package pw.edu.pl.workscheduler.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class BOE {

    private final Schedule schedule;

    private int maxWorkingShifts;

    Schedule generateSchedule() {
        calculateMaxWorkingShifts();
        calculateAvailabilityOfEmployees();
        Shift leastWantedShift = getLeastWantedShift();

        while (leastWantedShift != null) {
            Employee leastAvailableEmployee = getLeastAvailableEmployeeAtShift(leastWantedShift);
            if (leastAvailableEmployee != null) {
                addEmployeeToShift(leastAvailableEmployee, leastWantedShift.getId());
            }

            leastWantedShift = getLeastWantedShift();
            calculateAvailabilityOfEmployees();
        }

        prettyPrintSchedule();
        prettyPrintSchedule2();
        printEmployeesReport();
        printEmptyDays();

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
        int maxNoOfShiftsLeft = 0;
        try {
            maxNoOfShiftsLeft = getMaxValueOfAllShiftsLeft(shift);
        } catch (IllegalStateException e) {
            return null;
        }

        return streamEmployeesWithShiftsLeftEqualTo(maxNoOfShiftsLeft, shift)
                .filter(employee -> employee.getAvailableShiftsNumber() > 0)
                .min(Comparator.comparing(Employee::getAvailableShiftsNumber))
                .orElseThrow();
    }

    private int getMaxValueOfAllShiftsLeft(Shift shift) {
        return schedule.getEmployeesAvailableForShift(shift).stream()
                .map(Employee::getAllShiftsLeft)
                .max(Integer::compareTo)
                .orElseThrow(() -> new IllegalStateException("No employees for shift"));
    }

    private Stream<Employee> streamEmployeesWithShiftsLeftEqualTo(int shiftsLeft, Shift shift) {
        return schedule.getEmployeesAvailableForShift(shift).stream()
                .filter(employee -> employee.getAllShiftsLeft() == shiftsLeft);
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

    private Shift getLeastWantedShift() {
        return getUnassignedShifts().stream()
                .map(
                        shift ->
                                shift.calculatePossibleEmployees(
                                        schedule.getEmployeesAvailableForShift(shift)))
                .filter(value -> value > 0)
                .min(Integer::compareTo)
                .flatMap(
                        min ->
                                getUnassignedShifts().stream()
                                        .filter(
                                                shift ->
                                                        shift.getPossibleEmployees() == min
                                                                && shift.getPossibleEmployees()
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

    private void calculateAvailabilityOfEmployees() {
        List<Shift> unassignedShifts = getUnassignedShifts();
        schedule.getEmployeeList()
                .forEach(employee -> employee.calculateAvailability(unassignedShifts));
    }

    private void prettyPrintSchedule() {

        for (int i = 0; i < 6; i++) {
            System.out.println();
        }

        System.out.print(" Employee  | ");

        for (int i = 0; schedule.getShiftDays().size() > i; i++) {
            System.out.print(schedule.getShiftDays().get(i).getDate() + " | ");
        }

        for (Employee employee : schedule.getEmployeeList()) {
            String name = String.format("%10.10s", employee.getName());
            System.out.println();
            for (int i = 0; schedule.getShiftDays().size() >= i; i++) {
                System.out.print("---------- | ");
            }
            System.out.println();
            System.out.print(name + " | ");

            for (ShiftDay shiftDay : schedule.getShiftDays()) {
                if (shiftDay.isWeekend()) {
                    System.out.print("     -     | ");
                } else {
                    String s = "";

                    for (Shift shift : shiftDay.getShiftsForADay()) {
                        if (shift.getEmployee() != null
                                && Objects.equals(shift.getEmployee().getId(), employee.getId())) {
                            s = s.concat("x");
                        }
                    }

                    System.out.print(String.format("%10.10s", s) + " | ");
                }
            }
        }
    }

    private void prettyPrintSchedule2() {

        for (int i = 0; i < 6; i++) {
            System.out.println();
        }

        System.out.print("   Shift   | ");

        for (int i = 0; schedule.getShiftDays().size() > i; i++) {
            System.out.print(schedule.getShiftDays().get(i).getDate() + " | ");
        }

        for (Shift shift : schedule.getShiftDays().get(0).getShiftsForADay()) {
            String name =
                    String.format(
                            "%10.10s",
                            shift.getStartTime().getHour()
                                    + ":"
                                    + shift.getStartTime().getMinute()
                                    + "-"
                                    + shift.getEndTime().getHour()
                                    + ":"
                                    + shift.getEndTime().getMinute());
            System.out.println();
            for (int i = 0; schedule.getShiftDays().size() >= i; i++) {
                System.out.print("---------- | ");
            }
            System.out.println();
            System.out.print(name + " | ");

            for (ShiftDay shiftDay : schedule.getShiftDays()) {
                if (shiftDay.isWeekend()) {
                    System.out.print("     -     | ");
                } else {

                    Shift thisShift =
                            shiftDay.getShiftsForADay().stream()
                                    .filter(
                                            shift1 ->
                                                    shift1.getStartTime()
                                                            .toLocalTime()
                                                            .equals(
                                                                    shift.getStartTime()
                                                                            .toLocalTime()))
                                    .findFirst()
                                    .orElseThrow();

                    if (thisShift.getEmployee() != null) {
                        System.out.print(
                                String.format("%10.10s", thisShift.getEmployee().getName())
                                        + " | ");
                    } else {
                        System.out.print("           | ");
                    }
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            System.out.println();
        }
    }

    private void printEmployeesReport() {
        for (Employee employee : schedule.getEmployeeList()) {
            System.out.println(
                    employee.getName()
                            + " has "
                            + employee.getShifts().size()
                            + " shifts assigned.");
        }
    }

    private void printEmptyDays() {
        System.out.println("Empty shifts:");
        for (ShiftDay shiftDay : schedule.getShiftDays()) {
            if (shiftDay.isWeekend()) {
                continue;
            }
            if (!shiftDay.getShiftsForADay().stream().allMatch(Shift::isAssigned)) {
                shiftDay.getShiftsForADay().stream()
                        .filter(shift -> !shift.isAssigned())
                        .forEach(
                                shift ->
                                        System.out.println(
                                                shift.getStartTime() + " - " + shift.getEndTime()));
            }
        }
    }
}
