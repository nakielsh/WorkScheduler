package pw.edu.pl.workscheduler.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TimeFrame implements Serializable {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
}
