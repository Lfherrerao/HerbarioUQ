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

import co.edu.analisis2.co.entidades.Envio;
import co.edu.analisis2.co.entidades.Genero;
import co.edu.analisis2.co.entidades.Planta;
import co.edu.analisis2.co.enumeraciones.Estado;
import co.edu.analisis2.grid.ejb.AdminEJB;
import co.edu.analisis2.grid.excepciones.ExcepcionesHerbario;

/**
 * @author LENOVO
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "plantaBean")
@ApplicationScoped
public class PlantaBean {

	/**
	 * nombre de la planta
	 */
	private String nombre;
	/**
	 * color de la planta
	 */
	private String color;

	private Planta planta;

	/**
	 * lista de generos registrados en base de datos.
	 */
	private List<Genero> generos;
	/**
	 * lista de plantas registradas
	 */
	private List<Planta> plantas;

	/**
	 * generos asociados a la planta
	 */
	private Genero genero;
	@EJB
	private AdminEJB adminEJB;

	/**
	 * inicializa la info basica del bean.
	 */
	@PostConstruct
	private void init() {
		generos = adminEJB.listarGenero();
		plantas = adminEJB.listarPlantas();
	}

	/**
	 * El inyectado desde el login Seguridad Bean
	 */
	@Inject
	@ManagedProperty(value = "#{seguridadBean}")
	private SeguridadBean seguridadBean;

	public String registrarPlanta() throws ExcepcionesHerbario {

		try {

			Planta planta = new Planta();
			planta.setNombre(nombre);
			planta.setColor(color);
			planta.setGenero(genero);

			adminEJB.agregarPlanta(planta);

			generos = adminEJB.listarGenero();
			plantas = adminEJB.listarPlantas();

			Envio envio = new Envio();
			envio.setPlanta(planta);
			envio.setEstado(Estado.EN_ESTUDIO);
			envio.setPersona(seguridadBean.getUsuario());
			envio.setJustificacion("esperando cambios por administrador");
			adminEJB.realizarEnvio(envio);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			return "/indexRecolector";

		} catch (ExcepcionesHerbario e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "";
		}
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
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	/**
	 * @return the generos
	 */
	public List<Genero> getGeneros() {
		return generos;
	}

	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;

	}

	/**
	 * @return the plantas
	 */
	public List<Planta> getPlantas() {
		return plantas;
	}

	/**
	 * @param plantas the plantas to set
	 */
	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}

	/**
	 * @return the planta
	 */
	public Planta getPlanta() {
		return planta;
	}

	/**
	 * @param planta the planta to set
	 */
	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

}
