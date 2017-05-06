package br.ufes.dwws.vital.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import br.ufes.dwws.util.MenuItem;
import br.ufes.dwws.vital.login.LoginEvent;

@Named
@SessionScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MenuController.class.getCanonicalName());
	private List<MenuItem> menuItems;

	public MenuController() {
		
		logger.log(Level.INFO, "menuController constructor's called");
		
		menuItems = new ArrayList<>();
		menuItems.add(new MenuItem("Item 1", "fa fa-envelope-open", "#"));
		menuItems.add(new MenuItem("Item N", "fa fa-fire", "#"));
		menuItems.add(new MenuItem("Item O", "fa fa-heart", "#"));
		menuItems.add(new MenuItem("Item P", "fa fa-user", "#"));
	}
	
	public void LoginEventHappens(@Observes LoginEvent event)
	{
		logger.log(Level.INFO, "loginEventHappens");
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

}
