
package acme.features.sponsor.invoice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Invoice;
import acme.entities.sponsorships.Sponsorship;
import acme.roles.Sponsor;

@Service
public class SponsorInvoiceCreateService extends AbstractService<Sponsor, Invoice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorInvoiceRepository sir;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Sponsorship sponsorship;

		masterId = super.getRequest().getData("masterId", int.class);
		sponsorship = this.sir.findOneSponsorshipById(masterId);
		status = sponsorship != null && sponsorship.isDraftMode() && super.getRequest().getPrincipal().hasRole(sponsorship.getSponsor());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Invoice object;
		int masterId;
		Sponsorship sponsorship;
		Date moment;

		masterId = super.getRequest().getData("masterId", int.class);
		sponsorship = this.sir.findOneSponsorshipById(masterId);
		moment = MomentHelper.getCurrentMoment();

		object = new Invoice();
		object.setCode("");
		object.setRegistrationTime(moment);
		object.setTax(0.00);
		object.setSponsorship(sponsorship);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Invoice object) {
		assert object != null;

		super.bind(object, "code", "registrationTime", "startDate", "endDate", "quantity", "tax", "link");
	}

	@Override
	public void validate(final Invoice object) {
		assert object != null;
	}

	@Override
	public void perform(final Invoice object) {
		assert object != null;

		this.sir.save(object);
	}

	@Override
	public void unbind(final Invoice object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "registrationTime", "startDate", "endDate", "quantity", "tax", "link");
		dataset.put("masterId", super.getRequest().getData("masterId", int.class));
		dataset.put("draftMode", object.getSponsorship().isDraftMode());

		super.getResponse().addData(dataset);
	}
}