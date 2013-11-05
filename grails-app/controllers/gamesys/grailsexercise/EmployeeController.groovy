package gamesys.grailsexercise

import org.springframework.dao.DataIntegrityViolationException
import grails.plugin.springsecurity.annotation.Secured

class EmployeeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def employeeService

    @Secured(['ROLE_HR','ROLE_STANDARD'])
    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(['ROLE_HR','ROLE_STANDARD'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def results
        if (params.remote){
            params.startDateFrom = params.date('startDateFrom')
            params.startDateTo = params.date('startDateTo')
            results = employeeService.listByStartDateBetween(params)
            render(template: 'listTemplate',model: [employeeInstanceList: results.employeeInstanceList, employeeInstanceTotal: results.employeeInstanceTotal,
                    max: max , sort: params.sort, order: params.order])
        }
        else{
            results = employeeService.list(params)
            [employeeInstanceList: results.employeeInstanceList, employeeInstanceTotal: results.employeeInstanceTotal,
                    max: max , sort: params.sort, order: params.order]
        }
    }

    @Secured(['ROLE_HR'])
    def create() {
        [employeeInstance: new Employee(params)]
    }

    @Secured(['ROLE_HR'])
    def save() {
        params.fullName = params.fullName.toUpperCase()
        params.startDate = params.date('startDate')
        def employeeInstance = new Employee(params)
        if (!employeeInstance.save(flush: true)) {
            render(view: "create", model: [employeeInstance: employeeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'employee.label', default: 'Employee'), employeeInstance.id])
        redirect(action: "show", id: employeeInstance.id)
    }

    @Secured(['ROLE_HR','ROLE_STANDARD'])
    def show(Long id) {
        def employeeInstance = Employee.get(id)
        if (!employeeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'employee.label', default: 'Employee'), id])
            redirect(action: "list")
            return
        }

        [employeeInstance: employeeInstance]
    }

    @Secured(['ROLE_HR'])
    def edit(Long id) {
        def employeeInstance = Employee.get(id)
        if (!employeeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'employee.label', default: 'Employee'), id])
            redirect(action: "list")
            return
        }

        [employeeInstance: employeeInstance]
    }

    @Secured(['ROLE_HR'])
    def update(Long id, Long version) {
        def employeeInstance = Employee.get(id)
        if (!employeeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'employee.label', default: 'Employee'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (employeeInstance.version > version) {
                employeeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'employee.label', default: 'Employee')] as Object[],
                        "Another user has updated this Employee while you were editing")
                render(view: "edit", model: [employeeInstance: employeeInstance])
                return
            }
        }
        params.fullName = params.fullName.toUpperCase()
        params.startDate = params.date('startDate')
        employeeInstance.properties = params

        if (!employeeInstance.save(flush: true)) {
            render(view: "edit", model: [employeeInstance: employeeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'employee.label', default: 'Employee'), employeeInstance.id])
        redirect(action: "show", id: employeeInstance.id)
    }

    @Secured(['ROLE_HR'])
    def delete(Long id) {
        def employeeInstance = Employee.get(id)
        if (!employeeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'employee.label', default: 'Employee'), id])
            redirect(action: "list")
            return
        }

        try {
            employeeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'employee.label', default: 'Employee'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'employee.label', default: 'Employee'), id])
            redirect(action: "show", id: id)
        }
    }
}
