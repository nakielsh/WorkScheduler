package pw.edu.pl.workscheduler.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
class TimeFrame {

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
}
