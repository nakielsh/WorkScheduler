package pw.edu.pl.workscheduler.infrastructure.repository;

import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "shift_day")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class ShiftDayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<ShiftEntity> shiftsForADay;
}
