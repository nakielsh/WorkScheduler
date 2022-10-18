package pw.edu.pl.workscheduler.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.edu.pl.workscheduler.adapter.ShiftEntity;

public interface ShiftRepository extends JpaRepository<ShiftEntity, Long> {}
