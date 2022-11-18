package pw.edu.pl.workscheduler.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findFirstByName(String name);
}
