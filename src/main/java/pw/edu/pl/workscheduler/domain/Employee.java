package pw.edu.pl.workscheduler.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
class Employee {

    private Long id;
    private String name;

    private List<TimeFrame> unavailabilityList = new ArrayList<>();
    private List<Shift> shifts = new ArrayList<>();
    private List<LocalDate> workingDays = new ArrayList<>();

    // used to algorithm
    private int availableShiftsNumber = 0;
    private int allShiftsLeft = 0;

    void addToUnavailabilityList(List<TimeFrame> timeFrames) {
        for (TimeFrame timeframe : timeFrames) {
            if (!unavailabilityList.contains(timeframe)) {
                unavailabilityList.add(timeframe);
            }
        }
    }

    void addShift(Shift shift) {
        shifts.add(shift);
        workingDays.add(shift.getStartTime().toLocalDate());
        allShiftsLeft--;
    }

    void calculateAvailability(List<Shift> shifts) {
        int availability = 0;
        for (Shift shift : shifts) {
            if (shift.canBeAssigned(this)) {
                availability++;
            }
        }
        availableShiftsNumber = availability;
        log.info(
                "Employee {} : \navailable shifts = {} \nall shifts left = {}",
                name,
                availableShiftsNumber,
                allShiftsLeft);
    }
}
