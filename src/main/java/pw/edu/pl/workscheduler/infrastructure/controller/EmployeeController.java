package pw.edu.pl.workscheduler.infrastructure.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pw.edu.pl.workscheduler.domain.ScheduleFacade;
import pw.edu.pl.workscheduler.domain.commands.CommandMapper;
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO;
import pw.edu.pl.workscheduler.infrastructure.controller.request.AddEmployeesRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final ScheduleFacade scheduleFacade;

    @GetMapping
    List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Long shiftId) {
        return shiftId == null
                ? scheduleFacade.getAllEmployees()
                : scheduleFacade.getAvailableEmployees(shiftId);
    }

    @PostMapping
    List<EmployeeDTO> addEmployees(@RequestBody AddEmployeesRequest request) {
        return request.getEmployees().stream()
                .map(CommandMapper::toAddEmployeeCommand)
                .map(scheduleFacade::addEmployee)
                .toList();
    }
}
