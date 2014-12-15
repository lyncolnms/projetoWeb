package br.edu.utfpr.cp.web.sigecom.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Stateful;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.edu.utfpr.cp.web.sigecom.model.Equipamento;
import br.edu.utfpr.cp.web.sigecom.model.Operadores;

@Named
@Stateful
@ViewScoped
public class OperaEquipBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	private Operadores operador;
	private Equipamento equipamento;

	public Operadores getOperador() {
		return operador;
	}

	public void setOperador(Operadores operador) {
		this.operador = operador;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	@SuppressWarnings({ "unchecked" })
	public Collection<Equipamento> listaEquipamento() {
		Query query = em.createQuery("SELECT eq FROM Equipamento eq");
		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked" })
	public Collection<Operadores> listaOperador() {
		Query query = em.createQuery("SELECT op FROM Operadores op");
		return query.getResultList();
	}

	public void salvar(Operadores opera) {
		System.out.println("OI");
		try {
			em.persist(opera.getId());
		//	em.persist(equip);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void remover() {

	}

}