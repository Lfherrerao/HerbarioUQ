package co.edu.analisis2.co.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entidad que reprecenta una asesoria sobre el herbario.
 *
 */
@NamedQueries({ @NamedQuery(name = Asesoria.LISTAR_TODAS_LAS_ASESORIAS, query = "select a from Asesoria a ") })
@Entity

public class Asesoria implements Serializable {
	/**
	 * listar todas las asesorias
	 */
public static final String LISTAR_TODAS_LAS_ASESORIAS="listar todas las asesorias";
	/**
	 * radicado de la aseesoria.
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id

	private int radicado;
	/**
	 * pregunta de la asesoria.
	 */
	@Column(length = 100)
	private String pregunta;
	/**
	 * respuesta de la asesoria.
	 */
	@Column(length = 100)
	private String respuesta;
	/**
	 * persona que realiza la pregunta.
	 */
	Recolector emisor;

	/**
	 * empleado que responde la consulta.
	 */
	private Empleado receptor;

	/**
	 * @return the receptor
	 */
	@ManyToOne
	public Empleado getReceptor() {
		return receptor;
	}

	/**
	 * @param receptor the receptor to set
	 */
	public void setReceptor(Empleado receptor) {
		this.receptor = receptor;
	}

	private static final long serialVersionUID = 1L;

	public Asesoria() {
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
	 * @return the emisor
	 */
	public Recolector getEmisor() {
		return emisor;
	}

	/**
	 * @param emisor the emisor to set
	 */
	public void setEmisor(Recolector emisor) {
		this.emisor = emisor;
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
		Asesoria other = (Asesoria) obj;
		if (radicado != other.radicado)
			return false;
		return true;
	}

}
