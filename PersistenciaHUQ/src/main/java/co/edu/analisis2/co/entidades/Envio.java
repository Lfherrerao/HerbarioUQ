package co.edu.analisis2.co.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.edu.analisis2.co.enumeraciones.Estado;

/**
 * Entidad que reprecenta el envio de una planta a un herbario.
 * 
 *
 */
@NamedQueries({

		@NamedQuery(name = Envio.LISTAR_TODOS_LOS_ENVIOS, query = "select e from Envio e "),
		@NamedQuery(name = Envio.RECOLECTORES_QUE_HAN_HECHO_ENVIO, query = "select DISTINCT envio.persona from Envio envio "),
		@NamedQuery(name = Envio.AGROUP_BY_ENVIOS_ACEPTADOS_POR_DIA, query = "select COUNT(e.persona) from Envio e  where e.estado= :estado and e.fechaEnvio= :fecha GROUP BY e.persona "),
		@NamedQuery(name = Envio.NUMERO_ENVIOS_EMPLEADO, query = "select new co.analisis2.uniquindio.grid.DTO.RegistrosPorEmpleadoOAdministrador  (COUNT(e), e.persona.cedula ) from Envio e where e.persona.cedula= :cedula"),
		@NamedQuery(name = Envio.OBTENER_ENVIO_POR_RADICADO, query = "select e from Envio e where e.radicado= :radicado ")

})
@Entity

public class Envio implements Serializable {
	public static final String  OBTENER_ENVIO_POR_RADICADO= "optener envio por radicado";

	/**
	 * consulta que determina cuantos envios ha realizado un empleado.
	 */
	public static final String NUMERO_ENVIOS_EMPLEADO = "numero de envios de un empleao";
	/**
	 * este metodo cuenta el numero de personas con envios aceptados por dia
	 */
	public static final String AGROUP_BY_ENVIOS_ACEPTADOS_POR_DIA = "cuenta el numero de persona con envios aceptados por dias";
	/**
	 * lista de recolectores que han hecho envios sin repetir.
	 */
	public static final String RECOLECTORES_QUE_HAN_HECHO_ENVIO = "RECOLECTORES que han hecho envio";

	/**
	 * listar todos los envios.
	 */
	public static final String LISTAR_TODOS_LOS_ENVIOS = "listar todos los envios";
	/**
	 * radicado unico del envio.
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int radicado;
	/**
	 * persona que realiza el envio.
	 */
	@ManyToOne
	Persona persona;
	/**
	 * fecha en la que se realiza el envio.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	Date fechaEnvio;

	/**
	 * estado en el que se encuentra el envio.
	 */
	@Column(length = 15)
	@Enumerated(EnumType.STRING)
	Estado estado;

	/**
	 * justificacion del estado del envio.
	 */
	@Column(length = 100)
	String justificacion;

	/**
	 * planta que se envia al herbario.
	 */
	@OneToOne(mappedBy = "envio")
	private Planta planta;
	private static final long serialVersionUID = 1L;

	public Envio() {
		super();
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
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * @return the fechaEnvio
	 */
	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	/**
	 * @param fechaEnvio the fechaEnvio to set
	 */
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the justificacion
	 */
	public String getJustificacion() {
		return justificacion;
	}

	/**
	 * @param justificacion the justificacion to set
	 */
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + radicado;
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
		Envio other = (Envio) obj;
		if (radicado != other.radicado)
			return false;
		return true;
	}

}
