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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import co.edu.analisis2.co.entidades.Familia;
import co.edu.analisis2.co.entidades.Genero;
import co.edu.analisis2.grid.ejb.AdminEJB;
import co.edu.analisis2.grid.excepciones.ElementoRepetidoException;

/**
 * @author LENOVO
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "generoBean")
@ApplicationScoped
public class GeneroBean {

	/**
	 * nombre del genero de las plantas
	 */
	private String nombre;
	/**
	 * descripcion del genero de las plantas
	 */
	private String descripcion;
	/**
	 * familia asociada a el genero.
	 */
	private Familia familia;
	/**
	 * lista de familias disponibles
	 */
	private List<Familia> familias;
	private List<Genero> generos;
	/**
	 * genero completo.
	 */
	private Genero g = new Genero();

	/**
	 * referencia a la capa de negocio
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * inicializa la info basica del bean.
	 */
	@PostConstruct
	private void init() {

		familias = adminEJB.listarFamilia();
		generos = adminEJB.listarGenero();

	}

	/**
	 * metodo para registrar un genero por medio de web.
	 * 
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public String registrar() throws ElementoRepetidoException {

		try {
			Genero genero = new Genero();
			genero.setNombre(nombre);
			genero.setDescripcion(descripcion);
			genero.setFamilia(familia);
			g = adminEJB.insertarGenero(genero);
			generos = adminEJB.listarGenero();
			familias = adminEJB.listarFamilia();

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "/detalle_genero";

		} catch (ElementoRepetidoException e) {
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the familia
	 */
	public Familia getFamilia() {
		return familia;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	/**
	 * @return the g
	 */
	public Genero getG() {
		return g;
	}

	/**
	 * @param g the g to set
	 */
	public void setG(Genero g) {
		this.g = g;
	}

	/**
	 * @return the familias
	 */
	public List<Familia> getFamilias() {
		return familias;
	}

	/**
	 * @param familias the familias to set
	 */
	public void setFamilias(List<Familia> familias) {
		this.familias = familias;
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

}
