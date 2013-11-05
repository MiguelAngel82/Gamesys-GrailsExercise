<%@ page import="gamesys.grailsexercise.Employee" %>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'fullName', 'error')} required">
	<label for="fullName">
		<g:message code="employee.fullName.label" default="Full Name" />
        <span class="required-indicator">*</span>
	</label>
	<g:textField name="fullName" value="${employeeInstance?.fullName}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="employee.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDate" precision="day"  value="${employeeInstance?.startDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'holidayAllowance', 'error')} required">
	<label for="holidayAllowance">
		<g:message code="employee.holidayAllowance.label" default="Holiday Allowance" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="holidayAllowance" type="number" max="30" value="${employeeInstance.holidayAllowance}" required=""/>
</div>

