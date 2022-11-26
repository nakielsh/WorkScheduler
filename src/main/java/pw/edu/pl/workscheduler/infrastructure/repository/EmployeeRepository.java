package pw.edu.pl.workscheduler.infrastructure.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findFirstByName(String name);
}
