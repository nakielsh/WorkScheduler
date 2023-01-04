package pw.edu.pl.workscheduler.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class EmployeeDTO {

    private final Long id;
    private final String name;

    private final List<TimeFrameDTO> unavailabilityList;

    @Setter private Integer numberOfShifts;
}
