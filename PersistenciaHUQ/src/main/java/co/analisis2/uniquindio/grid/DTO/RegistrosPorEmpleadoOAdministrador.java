/**
 * 
 */
package co.analisis2.uniquindio.grid.DTO;

/**
 * consulta dto que retorna la cedula de una persona 
 * @author LENOVO
 *
 */
public class RegistrosPorEmpleadoOAdministrador {

	/**
	 * cedula de la persona.
	 */
	private String cedula;
	/**
	 * cantidad de reguistros de las personas.
	 */
	private long CantidadRegistros;

	/**
	 * @param cedula
	 * @param cantidadRegistros
	 */
	public RegistrosPorEmpleadoOAdministrador( long cantidadRegistros,String cedula) {
		super();
		this.cedula = cedula;
		CantidadRegistros = cantidadRegistros;
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
	 * @return the cantidadRegistros
	 */
	public long getCantidadRegistros() {
		return CantidadRegistros;
	}

	/**
	 * @param cantidadRegistros the cantidadRegistros to set
	 */
	public void setCantidadRegistros(long cantidadRegistros) {
		CantidadRegistros = cantidadRegistros;
	}
	
	
}
