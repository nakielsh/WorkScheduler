package pw.edu.pl.workscheduler.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
class Employee {

    private Long id;
    private String name;

    private List<TimeFrame> unavailabilityList = new ArrayList<>();

    private List<Shift> shifts;

    void addToUnavailabilityList(List<TimeFrame> timeFrames) {
        for (TimeFrame timeframe : timeFrames) {
            if (unavailabilityList.contains(timeframe)) {
                unavailabilityList.add(timeframe);
            }
        }
    }
}
