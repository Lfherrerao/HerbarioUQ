package util;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

import co.edu.analisis2.co.entidades.Recolector;
import co.edu.analisis2.grid.ejb.AdminEJB;

@FacesConfig(version = Version.JSF_2_3)
@Named(value = "recolectorConverter")
@ApplicationScoped
class RecolectorConverter implements Converter<Recolector> {
	
	
	
	
	
	/**
	 * referencia a la capa de negocio
	 */
	@EJB
	private AdminEJB adminEJB;

	@Override
	public Recolector getAsObject(FacesContext context, UIComponent component, String value) {
		Recolector recolector = null;
		if (value != null && !value.trim().equals("")) {
			try {
				recolector = adminEJB.buscarRecolector(value);
			} catch (Exception e) {
				throw new ConverterException(new FacesMessage(component.getClientId() + ":ID no valido"));
			}
		}
		return recolector;
	}


	@Override
	public String getAsString(FacesContext context, UIComponent component, Recolector value) {
		return value != null ? String.format("%s", value.getCedula()) : "";
	}

}
