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
import javax.persistence.criteria.CriteriaQuery;

import org.primefaces.event.RowEditEvent;

import br.edu.utfpr.cp.web.sigecom.model.Equipamento;
import br.edu.utfpr.cp.web.sigecom.model.Manutencao;

@Named
@Stateful
@ViewScoped
public class ManutencaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Manutencao manutencao;
	private List<Manutencao> filtroManutencao;
	private Collection<Manutencao> listaManutencao;
	private Equipamento equipamento;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostConstruct
	public void init() {
		this.manutencao = new Manutencao();

		CriteriaQuery cQ = em.getCriteriaBuilder().createQuery();
		cQ.select(cQ.from(Manutencao.class));

		listaManutencao = em.createQuery(cQ).getResultList();

	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Manutencao getManutencao() {
		return manutencao;
	}

	public void setManutencao(Manutencao manutencao) {
		this.manutencao = manutencao;
	}

	public List<Manutencao> getFiltroManutencao() {
		return filtroManutencao;
	}

	public void setFiltroManutencao(List<Manutencao> filtroManutencao) {
		this.filtroManutencao = filtroManutencao;
	}

	public Collection<Manutencao> getListaManutencao() {
		return listaManutencao;
	}

	public void setListaManutencao(Collection<Manutencao> listaManutencao) {
		this.listaManutencao = listaManutencao;
	}

	@SuppressWarnings({ "unchecked" })
	public Collection<Equipamento> listaEquipamento() {
		Query query = em.createQuery("SELECT eq FROM Equipamento eq");
		return query.getResultList();
	}

	public void salvar(Manutencao manu) {

		System.out.println("OI");

		try {
			this.em.persist(manu);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}

	}

	public void excluir(Manutencao manu) {

		try {
			Manutencao manutencao = this.em.find(Manutencao.class,
					manu.getIdManutencao());
			this.em.remove(manutencao);
			this.em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.init();
		}
	}

	public void onRowEdit(RowEditEvent event) {

		this.manutencao = (Manutencao) event.getObject();

		FacesMessage msg = new FacesMessage("Manutenção Editado",
				manutencao.getMotivo());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		// Faz alteração no equipamento da linha selecionada
		try {
			em.merge(manutencao);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Operação Cancelado",
				((Manutencao) event.getObject()).getMotivo());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void removeMessage() {
		FacesMessage msg = new FacesMessage("Manutenção Removido",
				manutencao.getMotivo());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
