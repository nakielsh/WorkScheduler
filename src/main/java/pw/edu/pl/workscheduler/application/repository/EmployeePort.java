package pw.edu.pl.workscheduler.application.repository;

import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;

public interface EmployeePort {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeByName(String name);
}
