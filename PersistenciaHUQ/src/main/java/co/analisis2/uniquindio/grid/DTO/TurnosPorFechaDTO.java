/**

 * 
 */
package co.analisis2.uniquindio.grid.DTO;

/**
 * consulta dto que permite generar algunos datos requeridos en un query.s
 * @author LENOVO
 *
 */
public class TurnosPorFechaDTO {
	/**
	 */
	private int radicadoEnvio;
	/**
	 * nombre del genero dto
	 */
	private String nombreGenero;
	/**
	 *nombre de la persona consultada dto
	 */
	private String nombreFamilia;
	/**
	 * cedula de la persona consultada dto
	 */
	private String cedulaPersona;
	/**
	 * correo de la persona consultada dto
	 */
	private String correoPersona;
	
	
	public TurnosPorFechaDTO(int radicadoEnvio, String nombreGenero, String nombreFamilia, String cedulaPersona,
			String correoPersona) {
		super();
		this.radicadoEnvio = radicadoEnvio;
		this.nombreGenero = nombreGenero;
		this.nombreFamilia = nombreFamilia;
		this.cedulaPersona = cedulaPersona;
		this.correoPersona = correoPersona;
	}


	/**
	 * @return the radicadoEnvio
	 */
	public int getRadicadoEnvio() {
		return radicadoEnvio;
	}


	/**
	 * @param radicadoEnvio the radicadoEnvio to set
	 */
	public void setRadicadoEnvio(int radicadoEnvio) {
		this.radicadoEnvio = radicadoEnvio;
	}


	/**
	 * @return the nombreGenero
	 */
	public String getNombreGenero() {
		return nombreGenero;
	}


	/**
	 * @param nombreGenero the nombreGenero to set
	 */
	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}


	/**
	 * @return the nombreFamilia
	 */
	public String getNombreFamilia() {
		return nombreFamilia;
	}


	/**
	 * @param nombreFamilia the nombreFamilia to set
	 */
	public void setNombreFamilia(String nombreFamilia) {
		this.nombreFamilia = nombreFamilia;
	}


	/**
	 * @return the cedulaPersona
	 */
	public String getCedulaPersona() {
		return cedulaPersona;
	}


	/**
	 * @param cedulaPersona the cedulaPersona to set
	 */
	public void setCedulaPersona(String cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
	}


	/**
	 * @return the correoPersona
	 */
	public String getCorreoPersona() {
		return correoPersona;
	}


	/**
	 * @param correoPersona the correoPersona to set
	 */
	public void setCorreoPersona(String correoPersona) {
		this.correoPersona = correoPersona;
	}
	
	

}
