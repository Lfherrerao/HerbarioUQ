package co.edu.analisis2.co.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * entidad que reprecenta un recolector que tiene la funcion de usuario.
 *
 */
@NamedQueries({
	//quitar la cedula, arreglo de objetos, castiars
	@NamedQuery(name =Recolector.CEDULAS_PERSONA_Y_SUS_ENVIOS, query="select r.cedula, envios from Recolector r  LEFT JOIN r.envio envios "),
	@NamedQuery(name =Recolector.LISTAR_TODOS_LOS_RECOLECTORES, query = "select r from Recolector r "),
	@NamedQuery(name =Recolector.LISTAR_ENVIOS_POR_CEDULA_DE_RECOLECTOR, query = "select envios from Recolector r INNER JOIN r.envio envios where r.cedula= :cedula"),
	@NamedQuery(name = Recolector.IS_EMPTY_PERSONAS_SIN_REGISTRO, query = "select r  from Recolector r where r.envio IS EMPTY"),
	
	})
@Entity
public class Recolector extends Persona implements Serializable {
	/**
	 * listar personas sin envio
	 */
	public static final String IS_EMPTY_PERSONAS_SIN_REGISTRO= "listr personas sin envios";
	
	/**
	 * listar las cedulas de las personas y sus envios asi no hayan hecho un envio.
	 */
	public static final String CEDULAS_PERSONA_Y_SUS_ENVIOS="listar cedulas de los recolectores y sus envios";
	/**
	 * listar envios dado la cedula de un recolector.
	 */
	public static final String LISTAR_ENVIOS_POR_CEDULA_DE_RECOLECTOR="listar envios por recolector";
/**
 * listar todos los recolectores
 */
	public static final String LISTAR_TODOS_LOS_RECOLECTORES="listar todos los recolectores";
	/**
	 * asesoria que realiza un recolector.
	 */
	@ManyToOne
	private Asesoria asesoria;

	

	private static final long serialVersionUID = 1L;

	public Recolector() {

		super();
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
