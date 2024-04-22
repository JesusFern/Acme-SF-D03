
package acme.features.developer.trainingSessions;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.trainingModule.TrainingSession;
import acme.roles.Developer;

@Controller
public class DeveloperTrainingSessionController extends AbstractController<Developer, TrainingSession> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingSessionListService						listService;

	@Autowired
	private DeveloperTrainingSessionListInTrainingModulesService	listInTrainingModuleService;


	@PostConstruct
	public void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addCustomCommand("list-in-training-module", "list", this.listInTrainingModuleService);
	}

}
