package pw.edu.pl.workscheduler.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ShiftDayDTO {

    private final Long id;

    private final LocalDate date;
    private final List<ShiftDTO> shiftsForADay;

    @JsonIgnore private LocalTime workStartTime;

    @JsonIgnore private LocalTime workEndTime;
}
