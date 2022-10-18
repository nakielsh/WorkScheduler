package pw.edu.pl.workscheduler.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.edu.pl.workscheduler.adapter.ShiftDayEntity;

public interface ShiftDaysRepository extends JpaRepository<ShiftDayEntity, Long> {}
