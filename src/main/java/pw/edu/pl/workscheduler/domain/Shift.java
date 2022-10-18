package pw.edu.pl.workscheduler.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class Shift {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Employee employee;

    public Shift(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
