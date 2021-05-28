/**
 * 
 */
package util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Clase que contiene todas los metodos reusables
 * 
 * @author EinerZG
 * @version 1.0
 */
public class Util {

	/**
	 * permite mostrar un mensaje en la pagina web
	 * 
	 * @param titulo  titulo del menasje
	 * @param mensaje texto a destacar
	 */
	public static void mostrarMensaje(String titulo, String mensaje) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	/**
	 * permite obtener la infomación que se encuentra en el archivo de propiedades
	 * 
	 * @return resurce bunble con info de application.properties
	 */
	public static ResourceBundle getResourceBundle() {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		return bundle;
	}

}
