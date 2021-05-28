package co.edu.analisis2.co.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Entity que reprecenta una persona.
 *
 */
@NamedQueries({
	
	@NamedQuery(name = Persona.BUSCAR_PERSONA_POR_CORREO, query = "select p from Persona p where p.correo= :correo")
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {
	


	public static final String BUSCAR_PERSONA_POR_CORREO ="buscar persona por correo";
	/**
	 * cedula de la persona y llave primaria de la entidad
	 */
	@Column(length = 15)
	@Id
	private String cedula;
	/**
	 * nombre de la persona
	 */
	@Column(length = 50)
	private String nombre;
	/**
	 * apellido de la persona
	 */
	@Column(length = 50)
	private String apellido;
	/**
	 * telefono de la persona
	 */
	@Column(length = 15)
	private String telefono;
	/**
	 * correo de la persona
	 */
	@Column(length = 20, nullable = false, unique = true)
	private String correo;
	/**
	 * contrasenia del correo de la perona.
	 */
	@Column(length = 20, nullable = false)
	private String contrasenia;
	
	/**
	 * lista de envios de un recolector.
	 */
	@OneToMany(mappedBy = "persona")
	private List<Envio> envio;



	private static final long serialVersionUID = 1L;

	public Persona() {
		super();
	}
	/**
	 * @return the envio
	 */
	public List<Envio> getEnvio() {
		return envio;
	}

	/**
	 * @param envio the envio to set
	 */
	public void setEnvio(List<Envio> envio) {
		this.envio = envio;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombre;
	}

}
