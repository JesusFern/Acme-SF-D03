
package acme.features.client.dashboards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.ClientDashboard;
import acme.roles.Client;

@Service
public class ClientDashboardShowService extends AbstractService<Client, ClientDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientDashboardRepository cdr;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}
	@Override
	public void load() {
		ClientDashboard dashboard;
		Integer totalNumberOfProgressLogsCompletenessBelow25;
		Integer totalNumberOfProgressLogsCompletenessBetween25to50;
		Integer totalNumberOfProgressLogsCompletenessBetween50to75;
		Integer totalNumberOfProgressLogsCompletenessAbove75;

		Double averageContractsBudget;
		Double deviationContractsBudget;
		Double minimumContractsBudget;
		Double maximumContractsBudget;
		int id;

		id = super.getRequest().getPrincipal().getActiveRoleId();

		totalNumberOfProgressLogsCompletenessBelow25 = this.cdr.totalNumberOfProgressLogsCompletenessBelow25(id);
		totalNumberOfProgressLogsCompletenessBetween25to50 = this.cdr.totalNumberOfProgressLogsCompletenessBetween25to50(id);
		totalNumberOfProgressLogsCompletenessBetween50to75 = this.cdr.totalNumberOfProgressLogsCompletenessBetween50to75(id);
		totalNumberOfProgressLogsCompletenessAbove75 = this.cdr.totalNumberOfProgressLogsCompletenessAbove75(id);
		averageContractsBudget = this.cdr.averageContractsBudget(id);
		deviationContractsBudget = this.cdr.deviationContractsBudget(id);
		minimumContractsBudget = this.cdr.minimumContractsBudget(id);
		maximumContractsBudget = this.cdr.maximumContractsBudget(id);

		dashboard = new ClientDashboard();
		dashboard.setTotalNumberOfProgressLogsCompletenessBelow25(totalNumberOfProgressLogsCompletenessBelow25);
		dashboard.setTotalNumberOfProgressLogsCompletenessBetween25to50(totalNumberOfProgressLogsCompletenessBetween25to50);
		dashboard.setTotalNumberOfProgressLogsCompletenessBetween50to75(totalNumberOfProgressLogsCompletenessBetween50to75);
		dashboard.setTotalNumberOfProgressLogsCompletenessAbove75(totalNumberOfProgressLogsCompletenessAbove75);
		dashboard.setAverageContractsBudget(averageContractsBudget);
		dashboard.setDeviationContractsBudget(deviationContractsBudget);
		dashboard.setMinimumContractsBudget(minimumContractsBudget);
		dashboard.setMaximumContractsBudget(maximumContractsBudget);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ClientDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalNumberOfProgressLogsCompletenessBelow25", //
			"totalNumberOfProgressLogsCompletenessBetween25to50", //
			"totalNumberOfProgressLogsCompletenessBetween50to75", // 
			"totalNumberOfProgressLogsCompletenessAbove75", //
			"averageContractsBudget", //
			"deviationContractsBudget", //
			"minimumContractsBudget", //
			"maximumContractsBudget");

		super.getResponse().addData(dataset);
	}

}
