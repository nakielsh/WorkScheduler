package pw.edu.pl.workscheduler.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TimeFrameDTO implements Serializable {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
