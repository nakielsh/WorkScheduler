package pw.edu.pl.workscheduler.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import pw.edu.pl.workscheduler.domain.database.EmployeeProvider
import pw.edu.pl.workscheduler.domain.dto.EmployeeDTO
import pw.edu.pl.workscheduler.domain.dto.ScheduleDTO
import spock.lang.Specification

import java.time.YearMonth

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ScheduleFacadeIntegrationTestSpec extends Specification implements EmployeeFixture, ScheduleFixture, EmployeeProvider {

    @Autowired
    private ScheduleFacade scheduleFacade

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
        addEmployees1(List.of(employee1(), employee2()))

        when:
        def employeeDTOList = scheduleFacade.getAllEmployees()

        then:
        employeeDTOList.contains(employee1())
        employeeDTOList.contains(employee2())
    }

    def "should successfully generate schedule for different months"(int month, int daysNumber) {
        given:
        addEmployees1(List.of(employee1(), employee2()))

        when:
        ScheduleDTO scheduleDTO = scheduleFacade.generateSchedule(initiateScheduleCommand(month, List.of(1L, 2L)))

        then:
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
        addEmployees1(List.of(employee1(), employee2()))

        and:
        def scheduleDTO = scheduleFacade.generateSchedule(initiateScheduleCommand(1, List.of(1L, 2L)))

        when:
        def retrievedScheduleDTO = scheduleFacade.getSchedule(scheduleDTO.id)

        then:
        isScheduleDTOEqual(scheduleDTO, retrievedScheduleDTO)
    }

    def "should generate schedule with all days taken"() {
        given:
        addEmployees1(employeesForScheduleWithAllDays())

        when:
        ScheduleDTO generatedScheduleDTO = scheduleFacade.generateSchedule(initiateScheduleCommand(1, List.of(1L, 1L)))

        then:
        generatedScheduleDTO.month == YearMonth.of(2022, 1) &&
                generatedScheduleDTO.shiftDays.size() == 31 &&
                generatedScheduleDTO.employeeList.size() == 2

        and:
        allShiftsAreTaken(generatedScheduleDTO)

    }

    def "should generate schedule with empty days"() {
        given:
        addEmployees1(employeesForScheduleWithEmptyDays())

        when:
        ScheduleDTO generatedScheduleDTO = scheduleFacade.generateSchedule(initiateScheduleCommand(1, List.of(1L, 1L)))

        then:
        generatedScheduleDTO.month == YearMonth.of(2022, 1) &&
                generatedScheduleDTO.shiftDays.size() == 31 &&
                generatedScheduleDTO.employeeList.size() == 2

        and:
        numberOfShiftsTaken(generatedScheduleDTO) == 11

    }

    def "should retrieve all schedules"() {
        given:
        addEmployees1(employeeDTOList())
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
        addEmployees1(employeeDTOList())
        def schedule = scheduleFacade.generateSchedule(initiateScheduleCommand(1, List.of(1L, 2L)))

        when:
        def updatedSchedule = scheduleFacade.addEmployeeToSchedule(addEmployeeToScheduleCommand(schedule.id))

        then:
        updatedSchedule.employeeList.size() == 3
        isEmployeeDTOEqual(updatedSchedule.employeeList.get(0), employee1())
        isEmployeeDTOEqual(updatedSchedule.employeeList.get(1), employee2())
        isEmployeeDTOEqual(updatedSchedule.employeeList.get(2), employeeDTOWithoutUnavailability())
    }

    def allShiftsAreTaken(ScheduleDTO scheduleDTO) {
        boolean allShiftsAreTaken = true
        scheduleDTO.shiftDays.each { shiftDay ->
            {
                if (shiftDay.getDate().getDayOfWeek().getValue() < 6)
                    shiftDay.getShiftsForADay().each { shift ->
                        if (shift.employee == null)
                            allShiftsAreTaken = false
                    }

            }
        }
    }

    def numberOfShiftsTaken(ScheduleDTO scheduleDTO) {
        int numberOfShiftsTaken = 0
        scheduleDTO.shiftDays.each { shiftDay ->
            {
                if (shiftDay.getDate().getDayOfWeek().getValue() < 6)
                    shiftDay.getShiftsForADay().each { shift ->
                        if (shift.employee != null)
                            numberOfShiftsTaken++
                    }

            }
        }
        return numberOfShiftsTaken
    }

    def addEmployees1(List<EmployeeDTO> employeeDTOList) {
        employeeDTOList.each {
            scheduleFacade.addEmployee(toAddEmployeeCommand(it))
        }
    }
}
