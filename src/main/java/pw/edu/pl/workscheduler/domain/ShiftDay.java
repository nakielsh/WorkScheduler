package pw.edu.pl.workscheduler.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@Setter
public class ShiftDay implements Serializable {

    private LocalDate date;
    private List<Shift> shiftsForADay = new ArrayList<>();

    @JsonIgnore private LocalTime workStartTime = LocalTime.of(0, 0);
    @JsonIgnore private LocalTime workEndTime = LocalTime.of(23, 59);

    public ShiftDay(
            LocalDate date, LocalTime startTime, LocalTime endTime, List<LocalTime> shifts) {
        this.date = date;
        this.workStartTime = startTime;
        this.workEndTime = endTime;
        generateShifts(shifts);
    }

    public ShiftDay(LocalDate date, List<LocalTime> shifts) {
        this.date = date;
        generateShifts(shifts);
    }

    private void generateShifts(List<LocalTime> shifts) {
        LocalDateTime startTime;
        LocalDateTime endTime;
        for (int i = 0; i <= shifts.size(); i++) {

            if (i == 0) {
                startTime = LocalDateTime.of(date, workStartTime);
                endTime = LocalDateTime.of(date, shifts.get(i));
            } else if (i == shifts.size()) {
                startTime = LocalDateTime.of(date, shifts.get(i - 1));
                endTime = LocalDateTime.of(date, workEndTime);
            } else {
                startTime = LocalDateTime.of(date, shifts.get(i - 1));
                endTime = LocalDateTime.of(date, shifts.get(i));
            }

            shiftsForADay.add(new Shift(startTime, endTime));
        }
    }
}
