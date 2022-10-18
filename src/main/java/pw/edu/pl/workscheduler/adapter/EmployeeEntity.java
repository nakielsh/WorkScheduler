package pw.edu.pl.workscheduler.adapter;

import java.util.Collection;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pw.edu.pl.workscheduler.domain.TimeFrame;

@Table(name = "employees")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ElementCollection private Collection<TimeFrame> unavailabilityList;

    @ManyToMany(mappedBy = "employeeList")
    private Collection<ScheduleEntity> schedules;

    @OneToMany(mappedBy = "employee")
    private Collection<ShiftEntity> shifts;
}
