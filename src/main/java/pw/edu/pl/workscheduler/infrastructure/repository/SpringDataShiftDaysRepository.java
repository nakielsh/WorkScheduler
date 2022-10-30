package pw.edu.pl.workscheduler.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.edu.pl.workscheduler.infrastructure.repository.entities.ShiftDayEntity;

public interface SpringDataShiftDaysRepository extends JpaRepository<ShiftDayEntity, Long> {}
