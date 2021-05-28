/**
 * 
 */
package org.WebHUQ;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.analisis2.co.entidades.Asesoria;
import co.edu.analisis2.co.entidades.Persona;
import co.edu.analisis2.co.entidades.Recolector;
import co.edu.analisis2.grid.ejb.AdminEJB;
import co.edu.analisis2.grid.excepciones.ElementoRepetidoException;
import co.edu.analisis2.grid.excepciones.ExcepcionesHerbario;
import util.Util;

/**
 * @author LENOVO
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "recolectorBean")
@ApplicationScoped
public class RecolectorBean {

	/**
	 * cedula de el recolecrot
	 */
	private String cedula;
	/**
	 * apellido del recolector
	 */
	private String apellido;
	/**
	 * contrasenia del recolector
	 */
	private String contrasenia;
	/**
	 * correo del recolector
	 */
	private String correo;
	/**
	 * nombre del recolector
	 */
	private String nombre;
	/**
	 * telefono del recolector
	 */
	private String telefono;
	

	private Recolector recolector;
	/**
	 * lista de todas las asesorias asosiadas.
	 */
	private List<Asesoria> asesorias;
	private List<Recolector>recolectores;

	/**
	 * conexion con la capa de ejb
	 */
	@EJB
	private AdminEJB adminEJB;
	
	/**
	 * El inyectado desde el login Seguridad Bean
	 */
	@Inject
	@ManagedProperty(value = "#{seguridadBean}")
	private SeguridadBean seguridadBean;
	
	/**
	 * El inyectado desde el login Seguridad Bean
	 */

	/**
	 * inicializa la info basica del bean.
	 */
	@PostConstruct
	private void init() {

		recolectores = adminEJB.listarRecolector();
		 
		// asesorias = adminEJB.listarAsesorias();

	}

	/**
	 * metodo para registrar un genero por medio de web.
	 * 
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public String registrar() {
		Recolector recolector = new Recolector();
		recolector.setApellido(apellido);
		recolector.setCedula(cedula);
		recolector.setContrasenia(contrasenia);
		recolector.setCorreo(correo);
		recolector.setNombre(nombre);
		recolector.setTelefono(telefono);

		try {
			adminEJB.insertarRecolector(recolector);
			Util.mostrarMensaje("registro exitoso", "ahora igrese con correo y contraseña.");
			return "/indexRecolector.xhtml";
		} catch (ElementoRepetidoException e) {

			e.printStackTrace();
		}
		return "";

	}
	/**
	 * metodo para registrar un genero por medio de web.
	 * 
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public String registrar2() {
		Recolector recolector = new Recolector();
		recolector.setApellido(apellido);
		recolector.setCedula(cedula);
		recolector.setContrasenia(contrasenia);
		recolector.setCorreo(correo);
		recolector.setNombre(nombre);
		recolector.setTelefono(telefono);

		try {
			adminEJB.insertarRecolector(recolector);
			Util.mostrarMensaje("registro exitoso", "ahora igrese con correo y contraseña.");
			return "/indexAdministrador.xhtml";
		} catch (ElementoRepetidoException e) {

			e.printStackTrace();
		}
		return "";

	}
	/**
	 * Metodo que permite reiniciar las variables del bean
	 */
	public void cargar() {
		cedula = "";
		nombre = recolector.getNombre();
		apellido = "";
		correo = "";
		telefono = "";
		contrasenia = "";
	}


	/**
	 * Metodo que permite modificar la informacion de un recolector
	 * 
	 * @return true si se modifico o false si no
	 */
	public String modificarRecolector() {

		
			Persona  p=(Recolector)seguridadBean.getUsuario();
			
			try {	if (p!= null) {
			

				
				p.setApellido(apellido);
				p.setCedula(seguridadBean.getUsuario().getCedula());
				p.setContrasenia(contrasenia);
				p.setCorreo(correo);
				p.setNombre(nombre);
				p.setTelefono(telefono);

				adminEJB.modificarRecolector(p);

				
				
			}

			
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificó el recolector",
					"Modificacion exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			reiniciarVariables();
			return "";

		} catch (ExcepcionesHerbario e) {
			e.printStackTrace();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "";
		}
	}

	/**
	 * Metodo que permite reiniciar los valores de las variables
	 */
	public void reiniciarVariables() {
		cedula = "";
		nombre = "";
		apellido = "";
		correo = "";
		telefono = "";
		contrasenia = "";

	}
	
	/**
	 * metodo que me permite eliminar un recolector
	 * 
	 * @param cedula
	 * @return
	 */
	public void eliminarRecolector() {

		try {
			adminEJB.eliminarRecolector(recolector.getCedula());

			recolectores = adminEJB.listarRecolector();
			Util.mostrarMensaje("Eliminación exitosa!!!", "Eliminación exitosa!!!");
		} catch (ExcepcionesHerbario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia the contrasenia to set
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the asesorias
	 */
	public List<Asesoria> getAsesorias() {
		return asesorias;
	}

	/**
	 * @param asesorias the asesorias to set
	 */
	public void setAsesorias(List<Asesoria> asesorias) {
		this.asesorias = asesorias;
	}

	/**
	 * @return the recolector
	 */
	public Recolector getRecolector() {
		return recolector;
	}

	/**
	 * @param recolector the recolector to set
	 */
	public void setRecolector(Recolector recolector) {
		this.recolector = recolector;
	}

	/**
	 * @return the recolectores
	 */
	public List<Recolector> getRecolectores() {
		return recolectores;
	}

	/**
	 * @param recolectores the recolectores to set
	 */
	public void setRecolectores(List<Recolector> recolectores) {
		this.recolectores = recolectores;
	}

}
