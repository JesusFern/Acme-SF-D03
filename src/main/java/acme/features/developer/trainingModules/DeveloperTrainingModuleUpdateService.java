
package acme.features.developer.trainingModules;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.trainingModule.DifficultyLevel;
import acme.entities.trainingModule.TrainingModule;
import acme.roles.Developer;

@Service
public class DeveloperTrainingModuleUpdateService extends AbstractService<Developer, TrainingModule> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingModuleRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		TrainingModule object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneTrainingModuleById(id);

		super.getBuffer().addData(object);
	}

	//DUDA: puedo actualizar el draftMode, pero eso no significa que lo estaría publicando si lo paso a false?
	@Override
	public void bind(final TrainingModule object) {
		assert object != null;

		int projectId;
		Project project;
		Date updateMoment;

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectById(projectId);
		updateMoment = MomentHelper.deltaFromCurrentMoment(1, ChronoUnit.MINUTES);

		super.bind(object, "code", "creationMoment", "details", "difficultyLevel", "updateMoment", "link", "time", "project");
		object.setUpdateMoment(updateMoment);
		object.setProject(project);
	}

	//Hacer validaciones
	@Override
	public void validate(final TrainingModule object) {
		assert object != null;
	}

	@Override
	public void perform(final TrainingModule object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		Collection<Project> projects;
		SelectChoices levelChoices;
		SelectChoices projectChoices;
		Dataset dataset;

		projects = this.repository.findAllProjects();
		projectChoices = SelectChoices.from(projects, "code", object.getProject());
		levelChoices = SelectChoices.from(DifficultyLevel.class, object.getDifficultyLevel());

		dataset = super.unbind(object, "code", "creationMoment", "details", "difficultyLevel", "updateMoment", "link", "time", "draftMode", "project");
		dataset.put("project", projectChoices.getSelected().getKey());
		dataset.put("projects", projectChoices);
		dataset.put("difficultyLevels", levelChoices);

		super.getResponse().addData(dataset);
	}

}
