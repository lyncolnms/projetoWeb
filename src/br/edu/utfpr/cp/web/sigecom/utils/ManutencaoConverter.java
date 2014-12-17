package br.edu.utfpr.cp.web.sigecom.utils;

import javax.ejb.Stateful;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.utfpr.cp.web.sigecom.model.Manutencao;

@Named
@Stateful
@FacesConverter(forClass = Manutencao.class)
public class ManutencaoConverter implements Converter {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		Manutencao manutencao = (Manutencao) object;
		if (manutencao == null || manutencao.getEquipamento().getId() == null)
			return null;
		return String.valueOf(manutencao.getEquipamento().getId());
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		if (string == null || string.isEmpty())
			return null;
		Manutencao manutencao = em.find(Manutencao.class, string);
		return manutencao;
	}

}
