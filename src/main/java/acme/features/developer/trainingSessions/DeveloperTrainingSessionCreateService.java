
package acme.features.developer.trainingSessions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.trainingModule.TrainingModule;
import acme.entities.trainingModule.TrainingSession;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionCreateService extends AbstractService<Developer, TrainingSession> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingSessionRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		TrainingSession object;
		TrainingModule trainingModule;
		int trainingModuleId;

		trainingModuleId = super.getRequest().getData("trainingModuleId", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(trainingModuleId);

		object = new TrainingSession();
		object.setTrainingModule(trainingModule);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final TrainingSession object) {
		assert object != null;

		super.bind(object, "code", "timeBeforePeriod", "timeAfterPeriod", "location", "instructor", "email", "link");
	}

	@Override
	public void validate(final TrainingSession object) {
		assert object != null;
	}

	@Override
	public void perform(final TrainingSession object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final TrainingSession object) {
		assert object != null;
		Dataset dataset;

		dataset = super.unbind(object, "code", "timeBeforePeriod", "timeAfterPeriod", "location", "instructor", "email", "link");
		dataset.put("trainingModuleId", super.getRequest().getData("trainingModuleId", int.class));

		super.getResponse().addData(dataset);

	}

}
