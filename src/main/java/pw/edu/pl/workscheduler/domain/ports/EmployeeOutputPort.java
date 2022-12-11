package pw.edu.pl.workscheduler.domain.ports;

import java.util.List;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;

public interface EmployeeOutputPort {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeByName(String name);

    EmployeeDTO getEmployeeById(Long id);

    List<EmployeeDTO> getAllEmployees();

}
