package br.ufes.dwws.vital.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.dwws.util.MenuItem;

@Named
@SessionScoped
public class HomeController implements Serializable{
	
	private List<MenuItem> menuItems;
	private String name = "consegu";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HomeController(){
		menuItems = new ArrayList<>();
		menuItems.add(new MenuItem("Item M", "fa fa-envelope-open", "#"));
		menuItems.add(new MenuItem("Item N", "fa fa-fire", "#"));
		menuItems.add(new MenuItem("Item O", "fa fa-heart", "#"));
		menuItems.add(new MenuItem("Item P", "fa fa-user", "#"));
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}
	
}
