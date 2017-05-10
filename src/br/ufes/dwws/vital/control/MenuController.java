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
			logger.log(Level.INFO, "has logged user");
			
			menuItems.add(new MenuItem("Informations", "fa fa-envelope-open", "#"));
			menuItems.add(new MenuItem("Appointments", "fa fa-heart", "#"));
			menuItems.add(new MenuItem("Patients", "fa fa-user", "#"));
			
		}
		else
		{
			logger.log(Level.INFO, "dont have logged user");
			
			menuItems.add(new MenuItem("Home", "fa fa-envelope-open", "#"));
			menuItems.add(new MenuItem("News", "fa fa-fire", "#"));
			menuItems.add(new MenuItem("Solutions", "fa fa-heart", "#"));
			menuItems.add(new MenuItem("Contact", "fa fa-user", "#"));
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
