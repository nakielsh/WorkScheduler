package pw.edu.pl.workscheduler.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TimeFrame {

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
}
