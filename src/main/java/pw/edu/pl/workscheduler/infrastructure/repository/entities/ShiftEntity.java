package pw.edu.pl.workscheduler.infrastructure.repository.entities;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "shifts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private EmployeeEntity employee;
}
