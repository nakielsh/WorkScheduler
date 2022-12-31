package pw.edu.pl.workscheduler.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@RequiredArgsConstructor
public class ShiftDayDTO {

    private Long id;

    private final LocalDate date;
    private final List<ShiftDTO> shiftsForADay;

    @JsonIgnore private LocalTime workStartTime;

    @JsonIgnore private LocalTime workEndTime;

    public ShiftDayDTO(Long id, LocalDate date, List<ShiftDTO> shiftsForADay) {
        this.id = id;
        this.date = date;
        this.shiftsForADay = shiftsForADay;
    }
}
