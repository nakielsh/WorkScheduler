package pw.edu.pl.workscheduler.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.edu.pl.workscheduler.adapter.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {}
