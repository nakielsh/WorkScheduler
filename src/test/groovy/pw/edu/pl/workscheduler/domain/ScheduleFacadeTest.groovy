package pw.edu.pl.workscheduler.domain

import pw.edu.pl.workscheduler.domain.commands.AddEmployeeCommand
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

    def "should successfully add employee with unavailability"() {
        given:
        EmployeeDTO employeeDTO = scheduleFacade.addEmployee(addEmployeeCommandWithUnavailability())

        expect:
        isEmployeeDTOEqual(employeeDTO, employeeDTOWithUnavailability())
    }

    def "should successfully add employee without unavailability"() {
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

    def "should successfully generate schedule for different months"(int month, int daysNumber) {
        given:
        ScheduleDTO scheduleDTO = scheduleFacade.generateSchedule(initiateScheduleCommand(month, List.of(1L, 2L)))

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

    def "should successfully generate and retrieve schedule"() {
        given:
        ScheduleDTO scheduleDTO = scheduleFacade.generateSchedule(initiateScheduleCommand(1, List.of(1L, 2L)))

        when:
        ScheduleDTO retrievedScheduleDTO = scheduleFacade.getSchedule(scheduleDTO.id)

        then:
        isScheduleDTOEqual(scheduleDTO, retrievedScheduleDTO)
    }

    def "should generate schedule with empty days"() {
        given:
        addEmployees(addEmployeeCommandList())

        when:
        ScheduleDTO generatedScheduleDTO = scheduleFacade.generateSchedule(initiateScheduleCommand(1, List.of(3L, 4L)))

        then:
        generatedScheduleDTO.month == YearMonth.of(2022, 1) &&
                generatedScheduleDTO.shiftDays.size() == 31 &&
                generatedScheduleDTO.employeeList.size() == 2

        and:
        isEmployeeDTOEqual(generatedScheduleDTO.employeeList.get(0), employee1()) &&
                isEmployeeDTOEqual(generatedScheduleDTO.employeeList.get(1), employee2())
    }

    def "should retrieve all schedules"() {
        given:
        def schedule1 = scheduleFacade.generateSchedule(initiateScheduleCommand(1, List.of(1L, 2L)))
        def schedule2 = scheduleFacade.generateSchedule(initiateScheduleCommand(2, List.of(1L)))

        when:
        List<ScheduleDTO> scheduleDTOList = scheduleFacade.getAllSchedules()

        then:
        scheduleDTOList.size() == 2
        isScheduleDTOEqual(scheduleDTOList.get(0), schedule1)
        isScheduleDTOEqual(scheduleDTOList.get(1), schedule2)
    }

    def "should add employee to existing schedule"() {
        given:
        def schedule = scheduleFacade.generateSchedule(initiateScheduleCommand(1, List.of(1L, 2L)))

        when:
        def updatedSchedule = scheduleFacade.addEmployeeToSchedule(addEmployeeToScheduleCommand(schedule.id))

        then:
        updatedSchedule.employeeList.size() == 3
        isEmployeeDTOEqual(updatedSchedule.employeeList.get(0), employee1())
        isEmployeeDTOEqual(updatedSchedule.employeeList.get(1), employee2())
        isEmployeeDTOEqual(updatedSchedule.employeeList.get(2), employeeDTOWithoutUnavailability())
    }

    private void addEmployees(List<AddEmployeeCommand> addEmployeeCommandList) {
        addEmployeeCommandList.each {
            scheduleFacade.addEmployee(it)
        }
    }

    private ScheduleFacade scheduleFacade() {
        return new ScheduleFacade(
                new EmployeeService(scheduleOutputPort, employeeOutputPort),
                new ScheduleService(scheduleOutputPort, employeeOutputPort)
        )
    }

}
