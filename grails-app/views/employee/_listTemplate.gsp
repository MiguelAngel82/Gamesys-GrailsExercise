<table>
    <thead>
    <tr>

        <g:sortableColumn property="fullName" title="${message(code: 'employee.fullName.label', default: 'Full Name')}" />

        <g:sortableColumn property="startDate" title="${message(code: 'employee.startDate.label', default: 'Start Date')}" />

        <g:sortableColumn property="holidayAllowance" title="${message(code: 'employee.holidayAllowance.label', default: 'Holiday Allowance')}" />

    </tr>
    </thead>
    <tbody>
    <g:each in="${employeeInstanceList}" status="i" var="employeeInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td><g:link action="show" id="${employeeInstance.id}">${fieldValue(bean: employeeInstance, field: "fullName")}</g:link></td>

            <td><g:formatDate date="${employeeInstance.startDate}" format="MM/dd/yyyy"/></td>

            <td>${fieldValue(bean: employeeInstance, field: "holidayAllowance")}</td>

        </tr>
    </g:each>
    </tbody>
</table>
<div class="pagination">
    <g:paginate total="${employeeInstanceTotal}" />
</div>