package pw.edu.pl.workscheduler.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Employee {

    private Long id;
    private String name;

    private List<TimeFrame> unavailabilityList = new ArrayList<>();
}
