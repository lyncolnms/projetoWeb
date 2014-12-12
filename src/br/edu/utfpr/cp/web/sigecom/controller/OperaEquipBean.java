package br.edu.utfpr.cp.web.sigecom.controller;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	public void salvar() {
				
	}
	
	public void remover() {
		
	}
	
}