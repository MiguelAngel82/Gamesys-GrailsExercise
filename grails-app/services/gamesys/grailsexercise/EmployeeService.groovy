package gamesys.grailsexercise

class EmployeeService {

    /**
     * This method allows to search employees by startDate between a startDate from and a startDate to. Also, allows to
     * sort and pagination the results. If the params of sorting don't exists, it removes from params.
     * @param params
     * @return
     */
    def listByStartDateBetween(params) {
        def results = [:]

        if (!params.sort) params.remove('sort')
        if (!params.order) params.remove('order')
        results.employeeInstanceList = Employee.createCriteria().list(params){
            between('startDate',params.startDateFrom,params.startDateTo)
        }
        results.employeeInstanceTotal = results.employeeInstanceList.totalCount

        return results
    }

    /**
     * This method allows to search employees sorting and pagination the results.
     * @param params
     * @return
     */
    def list(params){
        def results = [:]

        results.employeeInstanceList = Employee.list(params)
        results.employeeInstanceTotal = Employee.count()

        return results
    }
}
