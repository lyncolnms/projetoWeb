package br.edu.utfpr.cp.web.sigecom.controller;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.event.RowEditEvent;

import br.edu.utfpr.cp.web.sigecom.model.Usuario;

@Named
@Stateful
@ViewScoped
public class UsuarioBean {

	private Usuario usuario;
	private List<Usuario> filtroUsuarios;
	private Collection<Usuario> listaUsuarios;

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
		Query query = em
				.createQuery("SELECT u FROM Usuario u ORDER BY u.login");
		this.listaUsuarios = query.getResultList();

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getFiltroUsuarios() {
		return filtroUsuarios;
	}

	public void setFiltroUsuarios(List<Usuario> filtroUsuarios) {
		this.filtroUsuarios = filtroUsuarios;
	}

	public Collection<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuario(Collection<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public void salvar(Usuario usr) {

		try {
			this.em.persist(usr);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}

	}

	public void excluir(Usuario usr) {

		try {
			Usuario usuario = this.em.find(Usuario.class, usr.getLogin());
			this.em.remove(usuario);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {

		this.usuario = (Usuario) event.getObject();

		FacesMessage msg = new FacesMessage("Usuario Editado",
				usuario.getLogin());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		// Faz alteração no usuário da linha selecionada
		try {
			em.merge(usuario);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Usuario Cancelado",
				((Usuario) event.getObject()).getLogin());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Usuário Removido",
				usuario.getLogin());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
