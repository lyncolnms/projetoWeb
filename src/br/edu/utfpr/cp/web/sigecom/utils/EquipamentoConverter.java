package br.edu.utfpr.cp.web.sigecom.utils;

import javax.ejb.Stateful;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.utfpr.cp.web.sigecom.model.Equipamento;

@Named
@Stateful
@FacesConverter(forClass = Equipamento.class)
public class EquipamentoConverter implements Converter {

	@PersistenceContext
	private EntityManager em;

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		Equipamento equipamento = (Equipamento) object;
		if (equipamento == null || equipamento.getNome() == null)
			return null;
		return String.valueOf(equipamento.getNome());
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Equipamento equipamento = em.find(Equipamento.class, string);
		return equipamento;
	}

}
