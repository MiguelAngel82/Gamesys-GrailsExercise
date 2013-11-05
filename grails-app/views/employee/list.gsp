
<%@ page import="gamesys.grailsexercise.Employee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'employee.label', default: 'Employee')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-employee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:ifAnyGranted roles="ROLE_HR">
				    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </sec:ifAnyGranted>
			</ul>
		</div>

		<div id="list-employee" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
            <g:formRemote name="myForm" update="updateList"
                          url="[controller: 'employee', action: 'list', params: [remote: true]]">
                <fieldset class="form">
                    <div class="fieldcontain">
                        <label for="startDateFrom">
                            <g:message code="employee.list.startDate.from" default="From" />
                        </label>
                        <input type="text" id="datepicker1" name="startDateFrom"/>
                        <script>
                            jQuery( "#datepicker1" ).datepicker();
                        </script>
                    </div>
                    <div class="fieldcontain">
                        <label for="startDateTo">
                            <g:message code="employee.list.startDate.to" default="To" />
                        </label>
                        <input type="text" id="datepicker2" name="startDateTo"/>
                        <script>
                            jQuery( "#datepicker2" ).datepicker();
                        </script>
                    </div>
                    <fieldset class="buttons">
                        <g:hiddenField name="max" value="${max}"/>
                        <g:hiddenField name="sort" value="${sort}"/>
                        <g:hiddenField name="order" value="${order}"/>
                        <g:submitButton class="search" name="search" value="Search" />
                    </fieldset>
                </fieldset>
            </g:formRemote>

            <div id="updateList">
                <g:render template="listTemplate"/>
            </div>
		</div>
	</body>
</html>
