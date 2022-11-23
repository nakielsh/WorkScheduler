package pw.edu.pl.workscheduler.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

interface ShiftDaysRepository extends JpaRepository<ShiftDayEntity, Long> {}
