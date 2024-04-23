
package acme.features.manager.projectUserStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.ProjectUserStory;
import acme.roles.Manager;

@Service
public class ManagerProjectUserStoryListMineService extends AbstractService<Manager, ProjectUserStory> {

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
		Collection<ProjectUserStory> objects;
		Principal principal;

		principal = super.getRequest().getPrincipal();
		objects = this.repository.findManyProjectUserStoriesByManagerId(principal.getActiveRoleId());

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final ProjectUserStory object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "");
		dataset.put("project", object.getProject().getCode());
		dataset.put("userStory", object.getUserStory().getTitle());

		super.getResponse().addData(dataset);
	}

}
