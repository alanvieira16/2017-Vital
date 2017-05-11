package br.ufes.dwws.vital.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.dwws.util.MenuItem;
import br.ufes.dwws.vital.login.LoginEvent;
import br.ufes.dwws.vital.login.SessionController;

@Named
@RequestScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MenuController.class.getCanonicalName());
	private List<MenuItem> menuItems;
	
	@Inject
	SessionController sessionController;

	public MenuController() {
		logger.log(Level.INFO, "menuController constructor's called");
		menuItems = new ArrayList<>();
	}
	
	@PostConstruct
	private void init()
	{
		if(sessionController.hasLoggedUser())
		{
			logger.log(Level.INFO, "User is logged in.");
			if(sessionController.getCurrentUser().getRole().equals("doctor")){
				menuItems.add(new MenuItem("Patients", "fa fa-user", "#"));
				menuItems.add(new MenuItem("My Appointments", "fa fa-calendar-check-o", "#"));
				menuItems.add(new MenuItem("Send Message", "fa fa-envelope-open", "#"));
			} else {
				menuItems.add(new MenuItem("My Appointments", "fa fa-calendar-check-o", "#"));
				menuItems.add(new MenuItem("My Treatments", "fa fa-thermometer", "#"));
			}
			
		}
		else
		{
			logger.log(Level.INFO, "User isn't logged in.");
		}
	}
	
	public void LoginEventHappens(@Observes LoginEvent event)
	{
		logger.log(Level.INFO, "loginEventHappens");
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

}
