import gamesys.grailsexercise.Employee
import gamesys.grailsexercise.Role
import gamesys.grailsexercise.User
import gamesys.grailsexercise.UserRole

class BootStrap {

    def init = { servletContext ->
        //The role HR
        def hrRole = new Role(authority: 'ROLE_HR').save(flush: true)
        //The role for standard user
        def standardRole = new Role(authority: 'ROLE_STANDARD').save(flush: true)
        //A user with role HR
        def hrUser = new User(username: 'hrUser', password: 'gamesys')
        hrUser.save(flush: true)
        UserRole.create hrUser, hrRole, true
        //A user with role standard
        def standardUser = new User(username: 'standardUser', password: 'gamesys')
        standardUser.save(flush: true)
        UserRole.create standardUser, standardRole, true

        //Check if the users have been created
        assert User.count() == 2
        assert Role.count() == 2
        assert UserRole.count() == 2

        //Create dummy employees
        def startDate = new Date()-2
        def fullName = "Paul"
        def holidayAllowance = 24
        def employee = new Employee(fullName: fullName, startDate: startDate, holidayAllowance: holidayAllowance)
        employee.save(flush: true)

        startDate = new Date()-50
        fullName = "Neil"
        holidayAllowance = 22
        employee = new Employee(fullName: fullName, startDate: startDate, holidayAllowance: holidayAllowance)
        employee.save(flush: true)

        startDate = new Date()-400
        fullName = "Miguel"
        holidayAllowance = 30
        employee = new Employee(fullName: fullName, startDate: startDate, holidayAllowance: holidayAllowance)
        employee.save(flush: true)
    }
    def destroy = {
    }
}
