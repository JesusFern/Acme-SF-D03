
package acme.features.administrator.banner;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.banners.Banner;

@Service
public class AdministratorBannerCreateService extends AbstractService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBannerRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Banner object;

		object = new Banner();

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Banner object) {
		assert object != null;
		Date instantiationMoment;

		instantiationMoment = MomentHelper.getCurrentMoment();
		super.bind(object, "startDisplayPeriod", "endDisplayPeriod", "pictureLink", "slogan", "targetWebDocumentLink");
		object.setInstantiationMoment(instantiationMoment);
	}

	@Override
	public void validate(final Banner object) {
		assert object != null;
	}
	@Override
	public void perform(final Banner object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "startDisplayPeriod", "endDisplayPeriod", "pictureLink", "slogan", "targetWebDocumentLink");
		dataset.put("instantiationMoment", object.getInstantiationMoment());
		super.getResponse().addData(dataset);
	}
}
