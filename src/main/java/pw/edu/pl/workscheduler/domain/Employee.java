package pw.edu.pl.workscheduler.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Employee {

    private Long id;
    private String name;

    private Set<TimeFrame> unavailabilityList = new HashSet<>();

    private List<Shift> shifts;

    public void addToUnavailabilityList(Set<TimeFrame> timeFrames) {
        //            if (!unavailabilityList.contains(timeframe)) {
        //            }
        unavailabilityList.addAll(timeFrames);
    }
}
