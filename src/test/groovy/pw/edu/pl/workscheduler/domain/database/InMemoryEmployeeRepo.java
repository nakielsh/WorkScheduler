package pw.edu.pl.workscheduler.domain.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.domain.ports.EmployeeOutputPort;

public class InMemoryEmployeeRepo implements EmployeeOutputPort {

    private final Map<Long, EmployeeDTO> employees;

    public InMemoryEmployeeRepo(List<EmployeeDTO> employees) {
        this.employees = new HashMap<>();
        for (EmployeeDTO employee : employees) {
            this.employees.put(employee.getId(), employee);
        }
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        EmployeeDTO employeeToSave =
                new EmployeeDTO(
                        employees.size() + 1L,
                        employeeDTO.getName(),
                        employeeDTO.getUnavailabilityList(),
                        employeeDTO.getShifts());
        employees.put(employeeToSave.getId(), employeeToSave);
        return employeeToSave;
    }

    @Override
    public EmployeeDTO getEmployeeByName(String name) {
        return employees.values().stream()
                .filter(employee -> employee.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return employees.get(id);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }
}
