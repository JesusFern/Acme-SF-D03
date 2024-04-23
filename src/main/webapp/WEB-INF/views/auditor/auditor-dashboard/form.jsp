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

<h2>
	<acme:message code="sponsor.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">

	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.total-invoices-with-tax-less-or-equals-21"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfStaticCodeAudit}"/>
		</td>
	</tr>	


	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.total-sponsorships-link"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfDynamicCodeAudit}"/>
		</td>
	</tr>	

	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.average-sponsorships-amount"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfAuditRecords}"/>
		</td>
	</tr>	

	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.deviation-sponsorships-amount"/>
		</th>
		<td>
			<acme:print value="${deviationNumberOfAuditRecords}"/>
		</td>
	</tr>	

	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.minimum-sponsorships-amount"/>
		</th>
		<td>
			<acme:print value="${minimumNumberOfAuditRecords}"/>
		</td>
	</tr>	

	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.maximum-sponsorships-amount"/>
		</th>
		<td>
			<acme:print value="${maximumNumberOfAuditRecords}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.average-invoices-quantity"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfPeriod}"/>
		</td>
	</tr>	

	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.deviation-invoices-quantity"/>
		</th>
		<td>
			<acme:print value="${deviationNumberOfPeriod}"/>
		</td>
	</tr>	

	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.minimum-invoices-quantity"/>
		</th>
		<td>
			<acme:print value="${minimumNumberOfPeriod}"/>
		</td>
	</tr>	

	<tr>
		<th scope="row">
			<acme:message code="sponsor.dashboard.form.label.maximum-invoices-quantity"/>
		</th>
		<td>
			<acme:print value="${maximumNumberOfPeriod}"/>
		</td>
	</tr>	
</table>


<acme:return/>