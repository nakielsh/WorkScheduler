package pw.edu.pl.workscheduler.infrastructure.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pw.edu.pl.workscheduler.infrastructure.repository.entities.EmployeeEntity;

interface SpringDataEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findFirstByName(String name);
}
