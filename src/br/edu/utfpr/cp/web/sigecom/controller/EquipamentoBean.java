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

import br.edu.utfpr.cp.web.sigecom.model.Equipamento;

@Named
@Stateful
@ViewScoped
public class EquipamentoBean {

	private Equipamento equipamentos;
	private List<Equipamento> filtroEquipamentos;
	private Collection<Equipamento> listaEquipamentos;

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		this.equipamentos = new Equipamento();
		Query query = em
				.createQuery("SELECT u FROM Equipamento u ORDER BY u.nome");
		this.listaEquipamentos = query.getResultList();
	}

	public Equipamento getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(Equipamento equipamentos) {
		this.equipamentos = equipamentos;
	}

	public List<Equipamento> getFiltroEquipamentos() {
		return filtroEquipamentos;
	}

	public void setFiltroEquipamentos(List<Equipamento> filtroEquipamentos) {
		this.filtroEquipamentos = filtroEquipamentos;
	}

	public Collection<Equipamento> getListaEquipamentos() {
		return listaEquipamentos;
	}

	public void setListaEquipamentos(Collection<Equipamento> listaEquipamentos) {
		this.listaEquipamentos = listaEquipamentos;
	}

	public void salvar(Equipamento equip) {

		try {
			this.em.persist(equip);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}

	}

	public void excluir(Equipamento equip) {

		try {
			Equipamento equipamento = this.em.find(Equipamento.class,
					equip.getNome());
			this.em.remove(equipamento);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {

		this.equipamentos = (Equipamento) event.getObject();

		FacesMessage msg = new FacesMessage("Equipamento Editado",
				equipamentos.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		// Faz alteração no equipamento da linha selecionada
		try {
			em.merge(equipamentos);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Equipamento Cancelado",
				((Equipamento) event.getObject()).getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Equipamento Removido",
				equipamentos.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
