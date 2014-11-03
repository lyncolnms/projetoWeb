package br.edu.utfpr.cp.web.sigecom.controller;

import java.io.Serializable;
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

import br.edu.utfpr.cp.web.sigecom.model.Operadores;

@Named
@Stateful
@ViewScoped
public class OperadorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Operadores operador;
	private Collection<Operadores> listaOperadores;
	private List<Operadores> filtroOperadores;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		this.operador = new Operadores();

		Query query = this.em.createQuery(
				"SELECT op FROM Operadores op ORDER BY op.nome",
				Operadores.class);
		listaOperadores = query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Operadores getOperador() {
		return operador;
	}

	public void setOperador(Operadores operador) {
		this.operador = operador;
	}

	public Collection<Operadores> getListaOperadores() {
		this.init();
		return listaOperadores;
	}

	public void setListaOperadores(Collection<Operadores> listaOperadores) {
		this.listaOperadores = listaOperadores;
	}

	public List<Operadores> getFiltroOperadores() {
		return filtroOperadores;
	}

	public void setFiltroOperadores(List<Operadores> filtroOperadores) {
		this.filtroOperadores = filtroOperadores;
	}

	public void salvar(Operadores operador) {
		try {
			this.em.persist(operador);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void excluir(Operadores operador) {
		try {
			Operadores op = this.em.find(Operadores.class, operador.getNome());
			this.em.remove(op);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {

		this.operador = (Operadores) event.getObject();

		FacesMessage msg = new FacesMessage("Operador Editado",
				operador.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		// Faz alteração no operador da linha selecionada
		try {
			em.merge(operador);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Operador Cancelado",
				((Operadores) event.getObject()).getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Operador Removido",
				operador.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
