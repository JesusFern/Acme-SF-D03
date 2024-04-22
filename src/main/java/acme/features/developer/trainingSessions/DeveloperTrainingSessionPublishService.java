
package acme.features.developer.trainingSessions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.trainingModule.TrainingSession;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionPublishService extends AbstractService<Developer, TrainingSession> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected DeveloperTrainingSessionRepository repository;

	// AbstractService<Authenticated, TrainingModule> ---------------------------


	@Override
	public void authorise() {
		//		final Principal principal = super.getRequest().getPrincipal();
		//		final int userAccountId = principal.getAccountId();
		//
		//		int id = super.getRequest().getData("id", int.class);
		//		TrainingSession trainingSession = this.repository.findOneTrainingSessionById(id);
		//
		//		final boolean authorise = trainingSession != null && trainingSession.isDraftMode() && trainingSession.getTrainingModule().getDeveloper().getUserAccount().getId() == userAccountId;
		//
		//		super.getResponse().setAuthorised(authorise);
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		TrainingSession trainingSession = this.repository.findOneTrainingSessionById(id);

		super.getBuffer().addData(trainingSession);
	}

	@Override
	public void bind(final TrainingSession object) {
		assert object != null;

		super.bind(object, "code", "timeBeforePeriod", "timeAfterPeriod", "location", "instructor", "email", "link");
	}

	@Override
	public void validate(final TrainingSession object) {
		assert object != null;

		//		final String PERIOD_START = "startPeriod";
		//		final String PERIOD_END = "endPeriod";
		//		final String CREATION_MOMENT = "creationMoment";
		//
		//		if (!super.getBuffer().getErrors().hasErrors(PERIOD_START) && !super.getBuffer().getErrors().hasErrors(PERIOD_END)) {
		//			final boolean startBeforeEnd = MomentHelper.isAfter(object.getEndPeriod(), object.getStartPeriod());
		//			super.state(startBeforeEnd, PERIOD_END, "developer.trainingSession.form.error.end-before-start");
		//
		//			if (startBeforeEnd) {
		//				final boolean startOneWeekBeforeEndMinimum = MomentHelper.isLongEnough(object.getStartPeriod(), object.getEndPeriod(), 7, ChronoUnit.DAYS);
		//
		//				super.state(startOneWeekBeforeEndMinimum, PERIOD_END, "developer.trainingSession.form.error.small-display-period");
		//			}
		//		}
		//
		//		if (!super.getBuffer().getErrors().hasErrors(CREATION_MOMENT) && !super.getBuffer().getErrors().hasErrors(PERIOD_START)) {
		//			final boolean startBeforeCreation = MomentHelper.isAfter(object.getStartPeriod(), object.getTrainingModule().getCreationMoment());
		//			super.state(startBeforeCreation, PERIOD_START, "developer.trainingSession.form.error.start-before-creation");
		//		}
		//
		//		if (!super.getBuffer().getErrors().hasErrors(CREATION_MOMENT) && !super.getBuffer().getErrors().hasErrors(PERIOD_END)) {
		//			final boolean endBeforeCreation = MomentHelper.isAfter(object.getEndPeriod(), object.getTrainingModule().getCreationMoment());
		//			super.state(endBeforeCreation, PERIOD_END, "developer.trainingSession.form.error.end-before-creation");
		//		}
		//
		//		if (!super.getBuffer().getErrors().hasErrors("code")) {
		//			final int trainingSessionId = super.getRequest().getData("id", int.class);
		//			final boolean duplicatedCode = this.repository.findAllTrainingSessions().stream().filter(e -> e.getId() != trainingSessionId).anyMatch(e -> e.getCode().equals(object.getCode()));
		//
		//			super.state(!duplicatedCode, "code", "developer.trainingSession.form.error.duplicated-code");
		//		}
	}

	@Override
	public void perform(final TrainingSession object) {
		assert object != null;

		object.setDraftMode(false);

		this.repository.save(object);
	}

	@Override
	public void unbind(final TrainingSession object) {
		assert object != null;

		Dataset dataset = super.unbind(object, "code", "timeBeforePeriod", "timeAfterPeriod", "location", "instructor", "email", "link", "draftMode");

		super.getResponse().addData(dataset);
	}

}
