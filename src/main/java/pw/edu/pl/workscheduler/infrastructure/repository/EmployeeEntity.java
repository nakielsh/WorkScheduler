package pw.edu.pl.workscheduler.infrastructure.repository;

import java.util.Collection;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pw.edu.pl.workscheduler.domain.dto.TimeFrameDTO;

@Table(name = "employee")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection private Collection<TimeFrameDTO> unavailabilityList;

    @ManyToMany(mappedBy = "employeeList")
    private Collection<ScheduleEntity> schedules;

}
