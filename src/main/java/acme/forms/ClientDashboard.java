
package acme.forms;

import acme.client.data.AbstractForm;

public class ClientDashboard extends AbstractForm {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	Integer						totalNumberOfProgressLogsBelow25;
	Integer						totalNumberOfProgressLogsBetween25to50;
	Integer						totalNumberOfProgressLogsBetween50to75;
	Integer						totalNumberOfProgressLogsAbove75;

	Double						averageContractsBudget;
	Double						deviationProjectsCost;
	Double						minimumContractsBudget;
	Double						maximumContractsBudget;

}
