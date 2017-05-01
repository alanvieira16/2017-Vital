package br.ufes.dwws.vital.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.util.MenuItem;
import br.ufes.dwws.vital.domain.User;
import br.ufes.dwws.vital.login.SessionController;

@Named
@RequestScoped
public class HomeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SessionController sessionController;

	private Boolean hasLoggedUser;

	public Boolean getHasLoggedUser() {
		return hasLoggedUser;
	}

	public void setHasLoggedUser(Boolean hasLoggedUser) {
		this.hasLoggedUser = hasLoggedUser;
	}

	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	private List<MenuItem> menuItems;

	public HomeController() {
		menuItems = new ArrayList<>();
		menuItems.add(new MenuItem("Item 1", "fa fa-envelope-open", "#"));
		menuItems.add(new MenuItem("Item N", "fa fa-fire", "#"));
		menuItems.add(new MenuItem("Item O", "fa fa-heart", "#"));
		menuItems.add(new MenuItem("Item P", "fa fa-user", "#"));

	}

	@PostConstruct
	void init() {

		hasLoggedUser = sessionController.hasLoggedUser();

		if (hasLoggedUser) {
			User user = sessionController.getCurrentUser();
			mensagem = "tá logado\n" + user + "\n\\o/";
		} else {
			mensagem = "Não tem usuario logado :(";
		}
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public String logout() {
		getRequest().getSession().invalidate();
		return "logout";
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

}
