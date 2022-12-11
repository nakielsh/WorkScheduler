package pw.edu.pl.workscheduler.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EmployeeDTO {

    private Long id;
    private String name;

    private List<TimeFrameDTO> unavailabilityList;
}
