
package acme.features.manager.dashboard;

import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.forms.ManagerDashboard;
import acme.roles.Manager;

@Controller
public class ManagerDashboardController extends AbstractController<Manager, ManagerDashboard> {

}
