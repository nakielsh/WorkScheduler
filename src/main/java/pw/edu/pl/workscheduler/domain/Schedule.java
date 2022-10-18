package pw.edu.pl.workscheduler.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@JsonInclude()
@Getter
@Setter
public class Schedule {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy")
    private YearMonth month;
    //    private final AppUser owner = new AppUser();
    @JsonProperty private List<ShiftDay> shiftDays = new ArrayList<>();
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
}
