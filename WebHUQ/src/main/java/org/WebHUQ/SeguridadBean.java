/**
 * 
 */
package org.WebHUQ;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import co.edu.analisis2.co.entidades.Administrador;
import co.edu.analisis2.co.entidades.Empleado;
import co.edu.analisis2.co.entidades.Persona;
import co.edu.analisis2.co.entidades.Recolector;
import co.edu.analisis2.grid.ejb.AdminEJB;
import co.edu.analisis2.grid.excepciones.ExcepcionesHerbario;
import util.EnviarEmail;
import util.Util;

/**
 * @author LENOVO
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "seguridadBean")
@SessionScoped
public class SeguridadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Persona usuario;

	/**
	 * determina si la persona inicio seson o no
	 */
	private boolean autenticado;

	@EJB
	private AdminEJB adminEJB;

	/**
	 * permite inicializar el usuario.
	 */
	@PostConstruct
	private void init() {

		usuario = new Persona();
		autenticado = false;
	}

	/**
	 * permite iniciar sesion
	 * 
	 * @throws ExcepcionesHerbario
	 */
	public void iniciarSesionComoRecolector() {

		Persona p;
		try {

			p = adminEJB.buscarPersonaPorCorreo(usuario.getCorreo());
			if (p != null && p.getClass() == Recolector.class) {
				Persona r = new Recolector();

				r = adminEJB.inicioSesion(usuario.getCorreo(), usuario.getContrasenia());
				usuario = r;
				autenticado = true;
			}
		} catch (ExcepcionesHerbario e) {
			Util.mostrarMensaje("No se pudo iniciar sesion verifique sus credenciales como recolector",
					"No se pudo iniciar sesion verifique sus credenciales");
			e.printStackTrace();
		}

	}
	/**
	 * permite iniciar sesion
	 * 
	 * @throws ExcepcionesHerbario
	 */
	public void iniciarSesionComoEmpleado() {

		Persona p;
		try {

			p = adminEJB.buscarPersonaPorCorreo(usuario.getCorreo());
			if (p != null && p.getClass() == Empleado.class ) {
				Persona r = new Empleado();

				r = adminEJB.inicioSesion(usuario.getCorreo(), usuario.getContrasenia());
				usuario = r;
				autenticado = true;
			}
		} catch (ExcepcionesHerbario e) {
			Util.mostrarMensaje("No se pudo iniciar sesion verifique sus credenciales como Administrador",
					"No se pudo iniciar sesion verifique sus credenciales");
			e.printStackTrace();
		}

	}
	/**
	 * envia un emaila con contrasenia.
	 * @return
	 */
	public String olvideContrasenia() {
		EnviarEmail enviarEmail = new EnviarEmail();

		Persona usuarioEmail =adminEJB.buscarPersonaPorCorreo(usuario.getCorreo());

		if (usuarioEmail != null) {

			if (usuarioEmail.getCorreo() != null ) {

				if (enviarEmail.enviarEmailolvideContrasenia(usuarioEmail)) {
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito",
							"Envío de email exitoso. Te enviamos la contraseña al email registrado");
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				} else {
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"No pudimos enviar el email de recordar contraseña");
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				}
			} else {
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						"Parece que no tenemos tu cuenta de correo registrada");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}

		} else {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Parece que en nuestro sistema no están los datos ingresado");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return null;
	}
	
	public String navegacion(int i) {
		switch (i) {
		case 1:
			return "/modificar_recolector?faces-redirect=true";
		case 2:
			return "/registrar_planta?faces-redirect=true";
		case 3:
			return "/detalle_familia?faces-redirect=true";
		case 4:
			return "/listar_plantas?faces-redirect=true";
		case 5:
			return "/pedir_asesoria?faces-redirect=true";
		case 6:
			return "/gestionar_recolecto?faces-redirect=true";
		case 7:
			return "/listar_recolector?faces-redirect=true";
		case 8:
			return "/empleado_registra_recolector?faces-redirect=true";
		case 9:
			return "/listar_plantas?faces-redirect=true";
		case 10:
			return "/listar_familias2?faces-redirect=true";
		case 11:
			return "/registrar_planta2?faces-redirect=true";
		default:
			return null;
		}
	}

	/**
	 * permite cerrar la sesion del administrador
	 * 
	 * @return pagina inicial
	 */
	public String cerrarSesion() {
		if (autenticado) {
			autenticado = false;
			usuario = new Persona();
			return "/inicio?faces-redirect=true";
		}
		return null;
	}
	/**
	 * @return the usuario
	 */
	public Persona getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the autenticado
	 */
	public boolean isAutenticado() {
		return autenticado;
	}

	/**
	 * @param autenticado the autenticado to set
	 */
	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

}
