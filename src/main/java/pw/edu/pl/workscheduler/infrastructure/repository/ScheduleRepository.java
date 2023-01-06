package pw.edu.pl.workscheduler.infrastructure.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    Optional<ScheduleEntity> findByShiftDays_ShiftsForADay_Id(Long id);
}
