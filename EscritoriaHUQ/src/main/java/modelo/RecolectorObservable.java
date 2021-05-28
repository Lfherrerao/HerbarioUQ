package modelo;

import co.edu.analisis2.co.entidades.Recolector;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class RecolectorObservable {

	/**
	 * cedula observable de un empleado
	 */
	private StringProperty cedula;
	/**
	 * nombre observable de un empleado
	 */
	private StringProperty nombre;
	/**
	 * apellido observable de un empleado
	 */
	private StringProperty apellido;
	/**
	 * telefono observable de un empleado
	 */
	private StringProperty telefono;
	/**
	 * correo observable de un empleado
	 */
	private StringProperty correo;
	/**
	 * clave observable de un empleado
	 */
	private StringProperty contrasenia;

	/**
	 * Empleado asociado
	 */
	private Recolector recolector;

	/**
	 * Metodo constructor
	 */
	public RecolectorObservable() {

	}

	/**
	 * constructor que genera con cliente observable con base a un cliente
	 * 
	 * @param cliente que se quiere volver observable
	 */
	public RecolectorObservable(Recolector recolector) {
		this.recolector = recolector;
		this.cedula = new SimpleStringProperty(recolector.getCedula());
		this.nombre = new SimpleStringProperty(recolector.getNombre());
		this.apellido = new SimpleStringProperty(recolector.getApellido());
		this.correo = new SimpleStringProperty(recolector.getCorreo());
		this.contrasenia = new SimpleStringProperty(recolector.getContrasenia());
		this.telefono = new SimpleStringProperty(recolector.getTelefono());

	}
	public RecolectorObservable(String cedula, String nombre) {

		this.cedula = new SimpleStringProperty(cedula);
		this.nombre = new SimpleStringProperty(nombre);

		this.apellido = new SimpleStringProperty("Algo");
		this.correo = new SimpleStringProperty("algo@mail.com");
		this.contrasenia = new SimpleStringProperty("12345");
		

	}
	
	


	/**
	 * @return the cedula
	 */
	public StringProperty getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(StringProperty cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the nombre
	 */
	public StringProperty getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public StringProperty getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(StringProperty apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the telefono
	 */
	public StringProperty getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(StringProperty telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the correo
	 */
	public StringProperty getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(StringProperty correo) {
		this.correo = correo;
	}

	/**
	 * @return the contrasenia
	 */
	public StringProperty getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia the contrasenia to set
	 */
	public void setContrasenia(StringProperty contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * @return the empleado
	 */
	public Recolector getRecolector() {
		return recolector;
	}

	/**
	 * @param recolector the recolector to set
	 */
	public void setEmpleado(Recolector recolector) {
		this.recolector = recolector;
	}

}
