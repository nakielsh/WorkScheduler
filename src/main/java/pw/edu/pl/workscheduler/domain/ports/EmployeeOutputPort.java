package pw.edu.pl.workscheduler.domain.ports;

import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;

public interface EmployeeOutputPort {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeByName(String name);
}
