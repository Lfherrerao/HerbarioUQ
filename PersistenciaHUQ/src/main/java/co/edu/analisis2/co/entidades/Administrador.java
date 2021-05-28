package co.edu.analisis2.co.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * entidad que reprecenta un administrador de un herbario.
 *
 */
@NamedQueries({
		@NamedQuery(name = Administrador.LISTAR_TODOS_LOS_ADMINISTRADORES, query = "select a from Administrador a "),
		@NamedQuery(name = Administrador.ADMIN_POR_ID, query = "select a from Administrador a where a.cedula= :cedula "),
		@NamedQuery(name = Administrador.CONTAR_ADMINS, query = "select COUNT (a) from Administrador a")
		})
@Entity

public class Administrador extends Persona implements Serializable {
	/**
	 * listar eadministrador por id.
	 */
public static final String CONTAR_ADMINS="conar Administrador por id";
	/**
	 * listar eadministrador por id.
	 */
public static final String ADMIN_POR_ID="Administrador por id";
	/**
	 * listar administrador
	 */
	public static final String LISTAR_TODOS_LOS_ADMINISTRADORES = "listar administradores";

	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}

}
