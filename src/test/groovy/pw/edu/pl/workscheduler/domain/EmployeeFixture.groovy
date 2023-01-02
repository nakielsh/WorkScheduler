package pw.edu.pl.workscheduler.domain

import pw.edu.pl.workscheduler.domain.commands.AddEmployeeCommand
import pw.edu.pl.workscheduler.domain.commands.AddEmployeeToScheduleCommand
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
                "Test Employee available",
                List.of())
    }

    List<EmployeeDTO> employeesForScheduleWithEmptyDays() {
        List.of(
                new EmployeeDTO(
                        1L,
                        "Unavailable employee",
                        List.of(
                                new TimeFrameDTO(LocalDateTime.of(2022, 1, 1, 8, 0),
                                        LocalDateTime.of(2022, 2, 1, 23, 0)))),
                new EmployeeDTO(
                        2L,
                        "1/2 available employee",
                        List.of(new TimeFrameDTO(LocalDateTime.of(2022, 1, 1, 8, 0),
                                LocalDateTime.of(2022, 1, 15, 16, 0))))
        )
    }

    List<EmployeeDTO> employeesForScheduleWithAllDays() {
        List.of(
                new EmployeeDTO(
                        1L,
                        "test1",
                        List.of()),
                new EmployeeDTO(
                        2L,
                        "test2",
                        List.of()
                ))
    }

    List<AddEmployeeCommand> addEmployeeCommandList() {
        List.of(new AddEmployeeCommand(
                "Unavailable employee",
                List.of(
                        new TimeFrameDTO(LocalDateTime.of(2022, 1, 1, 8, 0),
                                LocalDateTime.of(2022, 2, 1, 23, 0)
                        ))),
                new AddEmployeeCommand(
                        "1/2 available employee",
                        List.of(new TimeFrameDTO(LocalDateTime.of(2022, 1, 1, 8, 0),
                                LocalDateTime.of(2022, 1, 15, 16, 0))))
        )
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
                "Test Employee available",
                List.of())
    }

    AddEmployeeToScheduleCommand addEmployeeToScheduleCommand(long id) {
        new AddEmployeeToScheduleCommand(
                id,
                addEmployeeCommandWithoutUnavailability().name,
                addEmployeeCommandWithoutUnavailability().unavailability)
    }

    boolean isEmployeeDTOEqual(EmployeeDTO employeeDTO1, EmployeeDTO employeeDTO2) {
        return employeeDTO1.name == employeeDTO2.name &&
                employeeDTO1.unavailabilityList == employeeDTO2.unavailabilityList
    }

    AddEmployeeCommand toAddEmployeeCommand(EmployeeDTO employeeDTO) {
        new AddEmployeeCommand(employeeDTO.name, employeeDTO.unavailabilityList)
    }
}