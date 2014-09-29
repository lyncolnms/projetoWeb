package br.edu.utfpr.cp.web.sigecom.controller;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	@PostConstruct
	public void init() {
		this.em.createQuery("FROM u ORDER BY u.login");

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

	public void salvar(Usuario usuario) {
		Usuario usr = this.em.find(Usuario.class, usuario.getId());
		this.em.persist(usr);
		this.em.flush();
		this.init();

	}

	public void remover(Usuario usuario) {
		Usuario usr = this.em.find(Usuario.class, usuario.getId());
		this.em.remove(usr);
		this.em.flush();

	}

}
