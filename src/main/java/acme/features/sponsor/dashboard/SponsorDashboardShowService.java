
package acme.features.sponsor.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.SponsorDashboard;
import acme.roles.Sponsor;

@Service
public class SponsorDashboardShowService extends AbstractService<Sponsor, SponsorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorDashboardRepository sdr;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		SponsorDashboard dashboard;
		Integer totalNumberOfInvoicesWithTaxLessOrEquals21;
		Integer totalNumberOfSponsorshipsLink;
		Double averageSponsorshipsAmount;
		Double deviationSponsorshipsAmount;
		Double minimumSponsorshipsAmount;
		Double maximumSponsorshipsAmount;
		Double averageInvoicesQuantity;
		Double deviationInvoicesQuantity;
		Double minimumInvoicesQuantity;
		Double maximumInvoicesQuantity;
		int id;

		id = super.getRequest().getPrincipal().getActiveRoleId();

		totalNumberOfInvoicesWithTaxLessOrEquals21 = this.sdr.totalNumberOfInvoicesWithTaxLessOrEquals21(id);
		totalNumberOfSponsorshipsLink = this.sdr.totalNumberOfSponsorshipsLink(id);
		averageSponsorshipsAmount = this.sdr.averageSponsorshipsAmount(id);
		deviationSponsorshipsAmount = this.sdr.deviationSponsorshipsAmount(id);
		minimumSponsorshipsAmount = this.sdr.minimumSponsorshipsAmount(id);
		maximumSponsorshipsAmount = this.sdr.maximumSponsorshipsAmount(id);
		averageInvoicesQuantity = this.sdr.averageInvoicesQuantity(id);
		deviationInvoicesQuantity = this.sdr.deviationInvoicesQuantity(id);
		minimumInvoicesQuantity = this.sdr.minimumInvoicesQuantity(id);
		maximumInvoicesQuantity = this.sdr.maximumInvoicesQuantity(id);

		dashboard = new SponsorDashboard();
		dashboard.setTotalNumberOfInvoicesWithTaxLessOrEquals21(totalNumberOfInvoicesWithTaxLessOrEquals21);
		dashboard.setTotalNumberOfSponsorshipsLink(totalNumberOfSponsorshipsLink);
		dashboard.setAverageSponsorshipsAmount(averageSponsorshipsAmount);
		dashboard.setDeviationSponsorshipsAmount(deviationSponsorshipsAmount);
		dashboard.setMinimumSponsorshipsAmount(minimumSponsorshipsAmount);
		dashboard.setMaximumSponsorshipsAmount(maximumSponsorshipsAmount);
		dashboard.setAverageInvoicesQuantity(averageInvoicesQuantity);
		dashboard.setDeviationInvoicesQuantity(deviationInvoicesQuantity);
		dashboard.setMinimumInvoicesQuantity(minimumInvoicesQuantity);
		dashboard.setMaximumInvoicesQuantity(maximumInvoicesQuantity);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final SponsorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalNumberOfInvoicesWithTaxLessOrEquals21", //
			"totalNumberOfSponsorshipsLink", //
			"averageSponsorshipsAmount", // 
			"deviationSponsorshipsAmount", //
			"minimumSponsorshipsAmount", //
			"maximumSponsorshipsAmount", //
			"averageInvoicesQuantity", // 
			"deviationInvoicesQuantity", //
			"minimumInvoicesQuantity", //
			"maximumInvoicesQuantity");

		super.getResponse().addData(dataset);
	}
}
