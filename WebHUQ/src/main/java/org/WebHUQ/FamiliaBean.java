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
import co.edu.analisis2.grid.ejb.AdminEJB;
import co.edu.analisis2.grid.excepciones.ExcepcionesHerbario;

/**
 * @author LENOVO
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "familiaBean")
@ApplicationScoped
public class FamiliaBean {


	/**
	 * nombre de la familia de las plantas
	 */
	private String nombre;
	/**
	 * descripcion de una familia e plantas.
	 */
	private String descripcion;

	private Familia familia;
	private List<Familia> familias;
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
		

	}


	/**
	 * metodo para registrar una familia en web
	 * 
	 * @return
	 * @throws ExcepcionesHerbario
	 */
	public String registrar() throws ExcepcionesHerbario {

		try {
			Familia familia = new Familia();
			familia.setNombre(nombre);
			familia.setDescripcion(descripcion);

			familia = adminEJB.crearFamilia(familia);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "/detalle_familia";

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
	 * @return the f
	 */
	public Familia getFamilia() {
		return familia;
	}

	/**
	 * @param f the f to set
	 */
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	/**
	 * @return the adminEJB
	 */
	public AdminEJB getAdminEJB() {
		return adminEJB;
	}

	/**
	 * @param adminEJB the adminEJB to set
	 */
	public void setAdminEJB(AdminEJB adminEJB) {
		this.adminEJB = adminEJB;
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

	
}
