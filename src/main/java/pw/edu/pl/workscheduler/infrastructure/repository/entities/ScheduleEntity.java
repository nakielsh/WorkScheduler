package pw.edu.pl.workscheduler.infrastructure.repository.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.YearMonth;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "schedules")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy")
    private YearMonth scheduleMonth;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<ShiftDayEntity> shiftDays;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "EMPLOYEES_FOR_SCHEDULE",
            joinColumns = @JoinColumn(name = "SCHEDULE_ID"),
            inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
    private Collection<EmployeeEntity> employeeList;
}
