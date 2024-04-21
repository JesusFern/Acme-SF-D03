<%--
- form.jsp
-
- Copyright (C) 2012-2024 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textarea code="client.progress-log.form.label.recordId" path="recordId"/>
	<acme:input-textarea code="client.progress-log.form.label.percentageCompleteness" path="percentageCompleteness"/>
	<acme:input-textarea code="client.progress-log.form.label.comment" path="comment"/>
	<acme:input-textarea code="client.progress-log.form.label.registrationMoment" path="registrationMoment"/>
	<acme:input-select code="client.progress-log.form.label.contract" path="contract" choices="${contracts}" />
	<acme:input-money code="client.progress-log.form.label.responsiblePerson" path="responsiblePerson"/>


	<jstl:choose>
    <jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
            <acme:submit code="client.progress-log.form.button.delete" action="/client/progress-log/delete"/>
            <acme:submit code="client.progress-log.form.button.update" action="/client/progress-log/update"/>
        </jstl:when>
    <jstl:when test="${_command == 'create'}">
            <acme:submit code="client.progress-log.form.button.create" action="/client/progress-log/create?masterId=${id}"/>
        </jstl:when>

    </jstl:choose>
</acme:form>