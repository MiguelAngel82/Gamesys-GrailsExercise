package gamesys.grailsexercise

import grails.plugin.spock.IntegrationSpec

class EmployeeControllerSpec extends IntegrationSpec {

    /**
     * This test create a new employee
     */
    def "Create an employee with a start date less than one year and holiday allowance between 20 and 25 days"() {
        when:
            def startDate = new Date()-2
            def fullName = "NameTest"
            def holidayAllowance = 24
            def employee = new Employee(fullName: fullName, startDate: startDate, holidayAllowance: holidayAllowance)
            employee.save(flush: true)
        then:
            Employee.count() == 1
    }

    /**
     * This test create a new employee
     */
    def "Create an employee with a start date more than one year and holiday allowance less than 30 days"() {
        when:
            def startDate = new Date()-400
            def fullName = "NameTest"
            def holidayAllowance = 30
            def employee = new Employee(fullName: fullName, startDate: startDate, holidayAllowance: holidayAllowance)
            employee.save(flush: true)
        then:
            Employee.count() == 1
    }

    /**
     * This test doesn't create a new employee because the validation of start date fails
     */
    def "Create an employee with a start date int he future"() {
        when:
            def startDate = new Date()+100
            def fullName = "NameTest"
            def holidayAllowance = 20
            def employee = new Employee(fullName: fullName, startDate: startDate, holidayAllowance: holidayAllowance)
            employee.save(flush: true)
        then:
            Employee.count() == 0
    }

    /**
     * This test doesn't create a new employee because the validation of full name fails
     */
    def "Create two employees with the same full name"() {
        when:
            def startDate = new Date()-10
            def fullName = "NameTest"
            def holidayAllowance = 20
            def employee = new Employee(fullName: fullName, startDate: startDate, holidayAllowance: holidayAllowance)
            employee.save(flush: true)
            employee = new Employee(fullName: fullName, startDate: startDate, holidayAllowance: holidayAllowance)
            employee.save(flush: true)
        then:
            Employee.count() == 1
    }

    /**
     * This test doesn't create a new employee because the validation of holiday allowance fails
     */
    def "Create an employee with a start date less than one year and holiday allowance less than 20 days"() {
        when:
            def startDate = new Date()-2
            def fullName = "NameTest"
            def holidayAllowance = 10
            def employee = new Employee(fullName: fullName, startDate: startDate, holidayAllowance: holidayAllowance)
            employee.save(flush: true)
        then:
            Employee.count() == 0
    }

    /**
     * This test doesn't create a new employee because the validation of holiday allowance fails
     */
    def "Create an employee with a start date more than one year and holiday allowance more than 30 days"() {
        when:
            def startDate = new Date()-400
            def fullName = "NameTest"
            def holidayAllowance = 40
            def employee = new Employee(fullName: fullName, startDate: startDate, holidayAllowance: holidayAllowance)
            employee.save(flush: true)
        then:
            Employee.count() == 0
    }
}
