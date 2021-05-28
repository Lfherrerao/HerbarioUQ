package co.edu.analisis2.co.entidades;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entidad que reprecenta a un empleado de un herbario.
 *
 */
@NamedQueries({ @NamedQuery(name = Empleado.LISTAR_TODOS_LOS_EMPLEADOS, query = "select e from Empleado e "),
	 @NamedQuery(name = Empleado.BUSCAR_EMPLEADO_POR_EMAIL, query = "select e from Empleado e where e.correo= :correo ")	
})
@Entity

public class Empleado extends Persona implements Serializable {

	/**
	 * listar todos los empleados.
	 */
	public static final String LISTAR_TODOS_LOS_EMPLEADOS ="listar todos los empleados";
	/**
	 * buscar empleado por email.
	 */
	public static final String BUSCAR_EMPLEADO_POR_EMAIL ="busvar empleado por email";
	private static final long serialVersionUID = 1L;

	public Empleado() {
		super();
	}

	
   
}
