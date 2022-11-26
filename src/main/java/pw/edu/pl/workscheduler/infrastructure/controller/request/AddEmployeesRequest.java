package pw.edu.pl.workscheduler.infrastructure.controller.request;

import java.util.List;
import lombok.Getter;

@Getter
public class AddEmployeesRequest {

    List<AddEmployeeRequest> employees;
}
