
package acme.features.sponsor.sponsorship;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.sponsorships.Sponsorship;
import acme.entities.sponsorships.Type;
import acme.roles.Sponsor;

@Service
public class SponsorSponsorshipCreateService extends AbstractService<Sponsor, Sponsorship> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorSponsorshipRepository ssr;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Sponsorship object;
		Sponsor sponsor;
		Date moment;

		sponsor = this.ssr.findOneSponsorById(super.getRequest().getPrincipal().getActiveRoleId());
		moment = MomentHelper.getCurrentMoment();

		object = new Sponsorship();
		object.setCode("");
		object.setMoment(moment);
		object.setType(Type.Financial);
		object.setEmail("");
		object.setLink("");
		object.setSponsor(sponsor);
		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Sponsorship object) {
		assert object != null;

		int projectId;
		Project project;

		projectId = super.getRequest().getData("project", int.class);
		project = this.ssr.findOneProjectById(projectId);

		super.bind(object, "code", "moment", "startSponsor", "endSponsor", "amount", "type", "email", "link");
		object.setProject(project);
	}

	@Override
	public void validate(final Sponsorship object) {
		assert object != null;
	}

	@Override
	public void perform(final Sponsorship object) {
		assert object != null;

		this.ssr.save(object);
	}

	@Override
	public void unbind(final Sponsorship object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "moment", "startSponsor", "endSponsor", "amount", "type", "email", "link");

		super.getResponse().addData(dataset);
	}
}
