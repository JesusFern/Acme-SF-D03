
package acme.features.administrator.banner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Administrator;
import acme.entities.banners.Banner;

@Controller
public class AdministratorBannerController extends AbstractController<Administrator, Banner> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBannerListService	listService;

	@Autowired
	private AdministratorBannerShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}

}
