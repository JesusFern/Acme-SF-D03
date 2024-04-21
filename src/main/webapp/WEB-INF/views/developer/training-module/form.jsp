<%--
- list.jsp
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
	<acme:input-textbox code="developer.training-module.form.label.code" path="code" readonly="true" />
	<acme:input-textarea code="developer.training-module.form.label.creationMoment" path="creationMoment" readonly="true" />
	<acme:input-textarea code="developer.training-module.form.label.details" path="details" readonly="true" />
	<acme:input-textarea code="developer.training-module.form.label.difficulty" path="difficulty" readonly="true" />
	<acme:input-textarea code="developer.training-module.form.label.startMoment" path="startMoment" readonly="true" />
	<acme:input-textarea code="developer.training-module.form.label.endMoment" path="endMoment" readonly="true" />
	<acme:input-textarea code="developer.training-module.form.label.link" path="link" readonly="true" />
	<acme:input-textarea code="developer.training-module.form.label.time" path="time" readonly="true" />
</acme:form>