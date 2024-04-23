
package acme.features.manager.projectUserStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectUserStory;
import acme.entities.projects.UserStory;
import acme.roles.Manager;

@Service
public class ManagerProjectUserStoryCreateService extends AbstractService<Manager, ProjectUserStory> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectUserStoryRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		ProjectUserStory object;

		object = new ProjectUserStory();
		object.setProject(null);
		object.setUserStory(null);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final ProjectUserStory object) {
		assert object != null;
		int projectId, userStoryId;
		Project project;
		UserStory userStory;

		super.bind(object, "id");

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectById(projectId);
		object.setProject(project);

		userStoryId = super.getRequest().getData("userStory", int.class);
		userStory = this.repository.findOneUserStoryById(userStoryId);
		object.setUserStory(userStory);

	}

	@Override
	public void validate(final ProjectUserStory object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors())
			super.state(object.getProject().getManager().equals(object.getUserStory().getManager()), "project", "manager.project-user-story.form.error.not-same-manager");
	}

	@Override
	public void perform(final ProjectUserStory object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final ProjectUserStory object) {
		assert object != null;

		Collection<Project> projects;
		Collection<UserStory> userStories;

		SelectChoices choicesP;
		SelectChoices choicesU;
		Dataset dataset = new Dataset();

		Principal principal;

		principal = super.getRequest().getPrincipal();

		projects = this.repository.findManyProjectsByManagerId(principal.getActiveRoleId());
		choicesP = SelectChoices.from(projects, "code", object.getProject());

		userStories = this.repository.findManyUserStoriesByManagerId(principal.getActiveRoleId());
		choicesU = SelectChoices.from(userStories, "title", object.getUserStory());

		dataset.put("projects", choicesP);
		dataset.put("userStories", choicesU);

		super.getResponse().addData(dataset);
	}

}
