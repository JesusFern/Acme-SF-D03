
package acme.features.client.contract;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.contracts.Contract;
import acme.roles.Client;

@Controller
public class ClientContractController extends AbstractController<Client, Contract> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private ClientContractListMineService	listMineService;

	@Autowired
	private ClientContractShowService	listShowService;

	@Autowired
	private ClientContractCreateService		createService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCustomCommand("list-mine", "list", this.listMineService);
		super.addBasicCommand("show", this.listShowService);
		super.addBasicCommand("create", this.createService);
	}

}
