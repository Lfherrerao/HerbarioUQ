/**
 * 
 */
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

import co.edu.analisis2.co.entidades.Genero;
import co.edu.analisis2.grid.ejb.AdminEJB;

/**
 * @author LENOVO
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "generoConverter")
@ApplicationScoped
public class GeneroConverter implements Converter<Genero> {
	
	@EJB
	private AdminEJB adminEJB;

	@Override
	public Genero getAsObject(FacesContext context, UIComponent component, String nombre) {
		Genero genero = null;
		if (nombre != null && !nombre.trim().equals("")) {
			try {
				genero = adminEJB.buscarGeneroPorNombre(nombre);
				return genero;
			} catch (Exception e) {
				throw new ConverterException(new FacesMessage(component.getClientId() +

						":ID no valido"));
			}
		}
		return genero;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Genero value) {
		if (value != null)
			return  ((Genero) value).getNombre();
		return "";
	}

}
