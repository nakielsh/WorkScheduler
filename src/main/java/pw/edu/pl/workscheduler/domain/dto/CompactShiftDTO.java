package pw.edu.pl.workscheduler.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class CompactShiftDTO {

    private final Long id;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    @Setter private Long employeeId;
    @Setter private String employeeName;
}
