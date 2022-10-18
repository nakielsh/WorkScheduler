package pw.edu.pl.workscheduler.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.edu.pl.workscheduler.adapter.ScheduleEntity;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {}
