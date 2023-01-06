package pw.edu.pl.workscheduler.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDate;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "workingDays")
@JsonInclude(Include.NON_NULL)
public class EmployeeDTO {

    private final Long id;
    private final String name;

    private final List<TimeFrameDTO> unavailabilityList;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonIgnore
    private List<LocalDate> workingDays;

    @Setter private Integer numberOfShifts;

    public EmployeeDTO(
            Long id,
            String name,
            List<TimeFrameDTO> unavailabilityList,
            List<LocalDate> workingDays) {
        this.id = id;
        this.name = name;
        this.unavailabilityList = unavailabilityList;
        this.workingDays = workingDays;
    }
}
