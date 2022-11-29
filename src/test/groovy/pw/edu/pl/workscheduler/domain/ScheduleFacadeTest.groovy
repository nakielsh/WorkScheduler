package pw.edu.pl.workscheduler.domain

import pw.edu.pl.workscheduler.domain.database.EmployeeProvider
import pw.edu.pl.workscheduler.domain.database.InMemoryEmployeeRepo
import pw.edu.pl.workscheduler.domain.database.InMemoryScheduleRepo
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO
import pw.edu.pl.workscheduler.domain.ports.EmployeeOutputPort
import pw.edu.pl.workscheduler.domain.ports.ScheduleOutputPort
import spock.lang.Specification
import spock.lang.Subject

import java.time.YearMonth

class ScheduleFacadeTest extends Specification implements EmployeeFixture, ScheduleFixture, EmployeeProvider {

    private EmployeeOutputPort employeeOutputPort = new InMemoryEmployeeRepo(employeeDTOList())
    private ScheduleOutputPort scheduleOutputPort = new InMemoryScheduleRepo()

    @Subject
    private ScheduleFacade scheduleFacade = scheduleFacade()

    def "should successfully  add employee with unavailability"() {
        given:
        EmployeeDTO employeeDTO = scheduleFacade.addEmployee(addEmployeeCommandWithUnavailability())

        expect:
        isEmployeeDTOEqual(employeeDTO, employeeDTOWithUnavailability())
    }

    def "should successfully  add employee without unavailability"() {
        given:
        EmployeeDTO employeeDTO = scheduleFacade.addEmployee(addEmployeeCommandWithoutUnavailability())

        expect:
        isEmployeeDTOEqual(employeeDTO, employeeDTOWithoutUnavailability())
    }

    def "should successfully get all employees"() {
        given:
        List<EmployeeDTO> employeeDTOList = scheduleFacade.getAllEmployees()

        expect:
        employeeDTOList.size() == 2
        isEmployeeDTOEqual(employeeDTOList.get(0), employee1())
        isEmployeeDTOEqual(employeeDTOList.get(1), employee2())
    }

    def "should successfully initiate schedule"(int month, int daysNumber) {
        given:
        ScheduleDTO scheduleDTO = scheduleFacade.initializeSchedule(initiateScheduleCommand(month))

        expect:
        scheduleDTO.month == YearMonth.of(2022, month) &&
                scheduleDTO.shiftDays.size() == daysNumber &&
                scheduleDTO.employeeList.size() == 2

        and:
        isEmployeeDTOEqual(scheduleDTO.employeeList.get(0), employee1()) &&
                isEmployeeDTOEqual(scheduleDTO.employeeList.get(1), employee2())

        where:
        month | daysNumber
        1     | 31
        2     | 28
        4     | 30
    }

    def "should successfully initiate and retrieve schedule"() {
        given:
        ScheduleDTO scheduleDTO = scheduleFacade.initializeSchedule(initiateScheduleCommand(1))

        when:
        ScheduleDTO retrievedScheduleDTO = scheduleFacade.getSchedule(1L)

        then:
        isScheduleDTOEqual(scheduleDTO, retrievedScheduleDTO)
    }

    private ScheduleFacade scheduleFacade() {
        return new ScheduleFacade(
                new EmployeeService(scheduleOutputPort, employeeOutputPort),
                new ScheduleService(scheduleOutputPort, employeeOutputPort)
        )
    }

}