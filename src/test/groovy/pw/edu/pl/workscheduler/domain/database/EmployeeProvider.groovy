package pw.edu.pl.workscheduler.domain.database

import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO
import pw.edu.pl.workscheduler.domain.dto.TimeFrameDTO

import java.time.LocalDateTime

trait EmployeeProvider {

    List<EmployeeDTO> employeeDTOList() {
        List.of(employee1(), employee2())
    }

    EmployeeDTO employee1() {
        new EmployeeDTO(
                1L,
                "Test Employee",
                List.of(
                        new TimeFrameDTO(LocalDateTime.of(2022, 1, 1, 16, 0),
                                LocalDateTime.of(2022, 1, 1, 23, 0)
                        ),
                        new TimeFrameDTO(LocalDateTime.of(2022, 1, 2, 8, 0),
                                LocalDateTime.of(2022, 1, 2, 16, 0))))
    }

    EmployeeDTO employee2() {
        new EmployeeDTO(
                2L,
                "Test Employee 2",
                List.of(new TimeFrameDTO(LocalDateTime.of(2022, 1, 4, 8, 0),
                        LocalDateTime.of(2022, 1, 4, 23, 0))))
    }


}