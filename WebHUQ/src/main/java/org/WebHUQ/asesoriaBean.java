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
import co.edu.analisis2.co.entidades.Empleado;
import co.edu.analisis2.co.entidades.Persona;
import co.edu.analisis2.co.entidades.Recolector;
import co.edu.analisis2.grid.ejb.AdminEJB;
import co.edu.analisis2.grid.excepciones.ExcepcionesHerbario;
import util.Util;



/**
 * @author LENOVO
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "asesoriaBean")
@ApplicationScoped
public class asesoriaBean {

	/**
	 * EJB para realizar conexion con la capa de negocio
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * id de la asesoria
	 */
	private int radicado;

	/**
	 * pregunta
	 */
	private String pregunta;

	/**
	 * respuesta
	 */
	private String respuesta;

	/**
	 * recolector
	 */
	private Persona  recolector;

	
	private List<Empleado> empleados;

	/**
	 * Empleado al que se le pide la cita de asesoria
	 */
	private Empleado empleado;

	

	/**
	 * El inyectado desde el login Seguridad Bean
	 */
	@Inject
	@ManagedProperty(value = "#{seguridadBean}")
	private SeguridadBean seguridadBean;

	/**
	 * Lista de asesorias del cliente
	 */
	private List<Asesoria> asesorias;

	/**
	 * Asesoria de un cliente
	 */
	private Asesoria asesoria;

	@PostConstruct
	private void inicializar() {
		empleados = adminEJB.listarEmpleados();
	}

	/**
	 * Permite registrar una asesoria en la bd
	 * 
	 * @return ruta de index
	 */
	public String crearAsesoria() {


		try {
			Asesoria asesoria= new Asesoria();
			asesoria.setEmisor((Recolector)seguridadBean.getUsuario());
			asesoria.setReceptor(empleado);
			asesoria.setPregunta(pregunta);
			asesoria.setRespuesta("sin respuesta");
			
			adminEJB.pedirAsesoria(asesoria);
			
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			reiniciarVariables();
			return "/indexRecolector";
			
			
		} catch (ExcepcionesHerbario e) {
			Util.mostrarMensaje("No se pudo iniciar sesion verifique sus credenciales como recolector",
					"No se pudo iniciar sesion verifique sus credenciales");
			e.printStackTrace();
			
		}

		// No retorna cadena sino se realiza la asesoria
		return null;

	}

	public void reiniciarVariables() {
		
		empleado = null;
		
	}

	/**
	 * @return the radicado
	 */
	public int getRadicado() {
		return radicado;
	}

	/**
	 * @param radicado the radicado to set
	 */
	public void setRadicado(int radicado) {
		this.radicado = radicado;
	}

	/**
	 * @return the pregunta
	 */
	public String getPregunta() {
		return pregunta;
	}

	/**
	 * @param pregunta the pregunta to set
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	
	/**
	 * @return the recolector
	 */
	public Persona getRecolector() {
		return recolector;
	}

	/**
	 * @param recolector the recolector to set
	 */
	public void setRecolector(Persona recolector) {
		this.recolector = recolector;
	}

	/**
	 * @return the empleados
	 */
	public List<Empleado> getEmpleados() {
		return empleados;
	}

	/**
	 * @param empleados the empleados to set
	 */
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	/**
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return the seguridadBean
	 */
	public SeguridadBean getSeguridadBean() {
		return seguridadBean;
	}

	/**
	 * @param seguridadBean the seguridadBean to set
	 */
	public void setSeguridadBean(SeguridadBean seguridadBean) {
		this.seguridadBean = seguridadBean;
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
	 * @return the asesoria
	 */
	public Asesoria getAsesoria() {
		return asesoria;
	}

	/**
	 * @param asesoria the asesoria to set
	 */
	public void setAsesoria(Asesoria asesoria) {
		this.asesoria = asesoria;
	}
	

}