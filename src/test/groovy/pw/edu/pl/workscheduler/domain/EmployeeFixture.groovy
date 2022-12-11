package pw.edu.pl.workscheduler.domain

import pw.edu.pl.workscheduler.domain.commands.AddEmployeeCommand
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO
import pw.edu.pl.workscheduler.domain.dto.TimeFrameDTO

import java.time.LocalDateTime

trait EmployeeFixture {

    AddEmployeeCommand addEmployeeCommandWithUnavailability() {
        new AddEmployeeCommand(
                "Test Employee",
                List.of(
                        new TimeFrameDTO(LocalDateTime.of(2022, 1, 1, 16, 0),
                                LocalDateTime.of(2022, 1, 1, 23, 0)
                        ),
                        new TimeFrameDTO(LocalDateTime.of(2022, 1, 2, 8, 0),
                                LocalDateTime.of(2022, 1, 2, 16, 0))))
    }

    AddEmployeeCommand addEmployeeCommandWithoutUnavailability() {
        new AddEmployeeCommand(
                "Test Employee 2",
                List.of())
    }

    EmployeeDTO employeeDTOWithUnavailability() {
        new EmployeeDTO(
                3L,
                "Test Employee",
                List.of(
                        new TimeFrameDTO(LocalDateTime.of(2022, 1, 1, 16, 0),
                                LocalDateTime.of(2022, 1, 1, 23, 0)
                        ),
                        new TimeFrameDTO(LocalDateTime.of(2022, 1, 2, 8, 0),
                                LocalDateTime.of(2022, 1, 2, 16, 0))))
    }

    EmployeeDTO employeeDTOWithoutUnavailability() {
        new EmployeeDTO(
                4L,
                "Test Employee 2",
                List.of())
    }

    boolean isEmployeeDTOEqual(EmployeeDTO employeeDTO1, EmployeeDTO employeeDTO2) {
        return employeeDTO1.name == employeeDTO2.name &&
                employeeDTO1.unavailabilityList == employeeDTO2.unavailabilityList
    }

}