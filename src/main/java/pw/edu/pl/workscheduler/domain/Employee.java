package pw.edu.pl.workscheduler.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Employee {

    private Long id;
    private String name;

    private List<TimeFrame> unavailabilityList = new ArrayList<>();

    private List<Shift> shifts = new ArrayList<>();

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
    }

    int calculateAvailability(List<Shift> shifts) {
        for (Shift shift : shifts) {
            if (shift.canBeAssigned(this)) {
                availableShiftsNumber++;
            }
        }
        return availableShiftsNumber;
    }
}
