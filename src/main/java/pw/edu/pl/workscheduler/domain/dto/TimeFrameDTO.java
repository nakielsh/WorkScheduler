package pw.edu.pl.workscheduler.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class TimeFrameDTO implements Serializable {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
