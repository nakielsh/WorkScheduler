package pw.edu.pl.workscheduler.infrastructure.repository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.repository.EmployeePort;
import pw.edu.pl.workscheduler.domain.repository.SchedulePort;
import pw.edu.pl.workscheduler.infrastructure.repository.entities.EmployeeEntity;
import pw.edu.pl.workscheduler.infrastructure.repository.entities.EntityMapper;
import pw.edu.pl.workscheduler.infrastructure.repository.entities.ScheduleEntity;

@Component
public class ServicePersistentAdapter implements SchedulePort, EmployeePort {

    SpringDataScheduleRepository scheduleRepository;
    SpringDataShiftDaysRepository springDataShiftDaysRepository;
    SpringDataShiftRepository springDataShiftRepository;
    SpringDataEmployeeRepository employeeRepository;

    @Autowired
    public ServicePersistentAdapter(
            SpringDataScheduleRepository scheduleRepository,
            SpringDataShiftDaysRepository springDataShiftDaysRepository,
            SpringDataShiftRepository springDataShiftRepository,
            SpringDataEmployeeRepository employeeRepository) {
        this.scheduleRepository = scheduleRepository;
        this.springDataShiftDaysRepository = springDataShiftDaysRepository;
        this.springDataShiftRepository = springDataShiftRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = scheduleRepository.save(EntityMapper.toEntity(scheduleDTO));

        return EntityMapper.toScheduleDTO(scheduleEntity);
    }

    @Transactional
    @Override
    public ScheduleDTO updateSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity =
                scheduleRepository.findById(scheduleDTO.getId()).orElseThrow();
        scheduleEntity.setShiftDays(
                scheduleDTO.getShiftDays().stream().map(EntityMapper::toEntity).toList());
        scheduleEntity.setEmployeeList(
                scheduleDTO.getEmployeeList().stream().map(EntityMapper::toEntity).toList());

        return EntityMapper.toScheduleDTO(scheduleEntity);
    }

    @Override
    public ScheduleDTO getSchedule(Long scheduleId) {
        return EntityMapper.toScheduleDTO(
                scheduleRepository.findById(scheduleId).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = employeeRepository.save(EntityMapper.toEntity(employeeDTO));

        return EntityMapper.toEmployeeDTO(employeeEntity);
    }

    @Override
    public EmployeeDTO getEmployeeByName(String name) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findFirstByName(name);
        return employeeEntity.map(EntityMapper::toEmployeeDTO).orElse(null);
    }
}
