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
	<acme:input-textbox code="sponsor.sponsorship.form.label.code" path="code" readonly="true" />
	<acme:input-textbox code="sponsor.sponsorship.form.label.moment" path="moment" readonly="true" />
	<acme:input-textarea code="sponsor.sponsorship.form.label.startSponsor" path="startSponsor" readonly="true" />
	<acme:input-textarea code="sponsor.sponsorship.form.label.endSponsor" path="endSponsor" readonly="true" />
	<acme:input-textarea code="sponsor.sponsorship.form.label.amount" path="amount" readonly="true" />
	<acme:input-textarea code="sponsor.sponsorship.form.label.type" path="type" readonly="true" />
	<acme:input-textarea code="sponsor.sponsorship.form.label.email" path="email" readonly="true" />
	<acme:input-textarea code="sponsor.sponsorship.form.label.link" path="link" readonly="true" />
</acme:form>