package pw.edu.pl.workscheduler.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;

@Table(name = "shift_days")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class ShiftDayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<ShiftEntity> shiftsForADay;
}
