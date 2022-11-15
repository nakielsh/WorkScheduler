package pw.edu.pl.workscheduler.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.edu.pl.workscheduler.infrastructure.repository.entities.ShiftEntity;

interface SpringDataShiftRepository extends JpaRepository<ShiftEntity, Long> {

}
