package pw.edu.pl.workscheduler.domain;

import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
class TimeFrame {

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
}
