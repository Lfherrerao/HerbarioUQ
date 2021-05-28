/**
 * 
 */
package modelo;

import co.edu.analisis2.co.entidades.Familia;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author LENOVO
 *
 */
public class FamiliObservable {

	
	/**
	 * identificacion de una familia
	 */
	private IntegerProperty id;
	/**
	 * nombre de una familia observable
	 */
	private StringProperty nombre;
	/**
	 * descripcion de una familia observable
	 */
	private StringProperty descripcion;
	/**
	 * familia asociada
	 */
	private Familia familia;

	public FamiliObservable(Familia familia) {
		
		this.familia = familia;
		this.id = new SimpleIntegerProperty(familia.getId());
		this.nombre = new SimpleStringProperty(familia.getNombre());
		this.descripcion = new SimpleStringProperty(familia.getDescripcion());

	}

	public FamiliObservable() {
		
	}
	
	


	/**
	 * @return the id
	 */
	public IntegerProperty getId() {
		return id;
		
	}

	/**
	 * @param id the id to set
	 */
	public void setId(IntegerProperty id) {
		this.id = id;
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
	 * @return the descripcion
	 */
	public StringProperty getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(StringProperty descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the familia
	 */
	public Familia getFamilia() {
		return familia;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

}
