package modelo;

import co.edu.analisis2.co.entidades.Empleado;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Permite transformar un empleado en formato observable
 * 
 * @author leo
 * @version 1.0
 */
public class EmpleadoObservable {

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
	private Empleado empleado;
	
	/**
	 * Metodo constructor 
	 */
	public EmpleadoObservable() {

	}

	/**
	 * constructor que genera con cliente observable con base a un cliente
	 * 
	 * @param cliente que se quiere volver observable
	 */
	public EmpleadoObservable(Empleado empleado) {		
		this.empleado = empleado; 
		this.cedula = new SimpleStringProperty(empleado.getCedula());
		this.nombre = new SimpleStringProperty(empleado.getNombre());
		this.apellido = new SimpleStringProperty(empleado.getApellido());
		this.correo = new SimpleStringProperty(empleado.getCorreo());
		this.contrasenia = new SimpleStringProperty(empleado.getContrasenia());
		this.telefono = new SimpleStringProperty(empleado.getTelefono());
		
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
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}


}
