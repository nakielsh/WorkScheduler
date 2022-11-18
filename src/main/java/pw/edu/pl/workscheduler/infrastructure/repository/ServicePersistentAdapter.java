package pw.edu.pl.workscheduler.infrastructure.repository;

import lombok.AllArgsConstructor;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO;
import pw.edu.pl.workscheduler.domain.ports.EmployeeOutputPort;
import pw.edu.pl.workscheduler.domain.ports.ScheduleOutputPort;

import java.util.Optional;

@AllArgsConstructor
class ServicePersistentAdapter implements ScheduleOutputPort, EmployeeOutputPort {

    private final ScheduleRepository scheduleRepository;
    private final ShiftDaysRepository shiftDaysRepository;
    private final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;


    @Override
    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = scheduleRepository.save(EntityMapper.toEntity(scheduleDTO));

        return EntityMapper.toScheduleDTO(scheduleEntity);
    }

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
