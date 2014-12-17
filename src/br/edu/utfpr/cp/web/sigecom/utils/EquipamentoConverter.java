package br.edu.utfpr.cp.web.sigecom.utils;

import javax.ejb.Stateful;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.edu.utfpr.cp.web.sigecom.model.Equipamento;

@Named
@Stateful
@FacesConverter(forClass = Equipamento.class)
public class EquipamentoConverter implements Converter {

	@PersistenceContext
	private EntityManager em;

	private Long id;

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		Equipamento equipamento = (Equipamento) object;
		if (equipamento == null || equipamento.getNome() == null)
			return null;
		return String.valueOf(equipamento.getNome());
	}

	// @Override
	// public Object getAsObject(FacesContext context, UIComponent component,
	// String string) {
	// if (string != null && !string.isEmpty()) {
	// Equipamento equipamento = em.find(Equipamento.class, string);
	// System.out.println(">>>Object<<<");
	// return equipamento;
	// }
	//
	// return null;
	// }

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		try {
			if (value != null && !value.isEmpty()) {

				System.out.println(">>>Chegou<<<<");
				System.out.println(Equipamento.class);
				System.out.println(id);

				return em.find(Equipamento.class, value);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// @Override
	// public String getAsString(FacesContext facesContext,
	// UIComponent uiComponent, Object value) {
	// if (value instanceof Equipamento) {
	// Equipamento entity = (Equipamento) value;
	// if (entity != null && entity instanceof Equipamento
	// && entity.getId() != null) {
	// uiComponent.getAttributes().put(entity.getId().toString(),
	// entity);
	// return entity.getId().toString();
	// }
	// }
	// return "";
	// }

	// @Override
	// public Object getAsObject(FacesContext facesContext,
	// UIComponent uiComponent, String value) {
	// if (value != null && !value.isEmpty()) {
	// return (Equipamento) uiComponent.getAttributes().get(value);
	// }
	// return null;
	// }

}
