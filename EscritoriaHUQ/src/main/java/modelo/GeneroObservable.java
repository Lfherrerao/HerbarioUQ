/**
 * 
 */
package modelo;

import co.edu.analisis2.co.entidades.Familia;
import co.edu.analisis2.co.entidades.Genero;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author LENOVO
 *
 */
public class GeneroObservable {
	/**
	 * identificacion de un genero
	 */
	private IntegerProperty id;
	/**
	 * nombre de un genero observable
	 */
	private StringProperty nombre;
	/**
	 * descripcion de un genero observable
	 */
	private StringProperty descripcion;
	/**
	 * familia asociada a el genero.
	 */
	private Familia familia;
	/**
	 * genero asociado
	 */
	private Genero genero;

	/**
	 * 
	 */
	public GeneroObservable() {
		super();
	}

	/**
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param familia
	 * @param genero
	 */
	public GeneroObservable(Genero genero) {
		
		this.genero=genero;
		this.familia=genero.getFamilia();
		
	 this.id = new SimpleIntegerProperty(genero.getId());
		this.nombre = new SimpleStringProperty(genero.getNombre());
		this.descripcion = new SimpleStringProperty(genero.getDescripcion());
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

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

}
