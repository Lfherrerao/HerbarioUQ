package co.edu.analisis2.co.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;



/**
 * entidad que reprecenta las familias de plantas.
 */
@NamedQueries({
	
	@NamedQuery(name = Familia.LISTAR_TODAS_LAS_FAMILIAS, query = "select f from Familia f "),
	@NamedQuery(name = Familia.COUNT_FAMILIA, query = "select COUNT(f)  from Familia f  "),
	@NamedQuery(name = Familia.OBTENER_FAMILIA_CON_MAS_PLANTAS, query = "select f from Familia f where f.nombre=:nombreFamilia"),
	 @NamedQuery(name = Familia.OBTENER_CONSECUTIVO_FAMILIA, query = "select MAX(f.id) from Familia f"),
	 @NamedQuery(name = Familia.OBTENER_FAMILIA_POR_NOMBRE, query = "select f from Familia f where f.nombre=:nombre")

	
	})
@Entity
public class Familia implements Serializable {
	
	public static final String OBTENER_FAMILIA_POR_NOMBRE="obtener familia por nmbre";
	/**
	 * obtener consecutivo
	 */
	public static final String OBTENER_CONSECUTIVO_FAMILIA="obtener consecutivo";
	
	/**
	 * 
	 */
	public static final String OBTENER_FAMILIA_CON_MAS_PLANTAS="obtener familia con mas plantas";
	
	/**
	 * listar todas las familias
	 */
	public static final String LISTAR_TODAS_LAS_FAMILIAS = "listar todas las familias";
	/**
	 * CUANTA cuantas familias hay.
	 */
	public static final String COUNT_FAMILIA = "cuantas familias hay en la base de datos";
	/**
	 * codigo unico de la familia de la planta.
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	/**
	 * nombre de una familia de generos de plantas.
	 */
	@Column(length = 20)
	private String nombre;
	/**
	 * descripcion de la familia de la planta.
	 */
	@Column(length = 50)
	private String descripcion;

	/**
	 * lista de generos de de la familia.
	 */
	@OneToMany(mappedBy = "familia")
	List<Genero> generos;
	private static final long serialVersionUID = 1L;

	public Familia() {
		super();
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the generos
	 */
	public List<Genero> getGeneros() {
		return generos;
	}

	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
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
		result = prime * result + id;
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
		Familia other = (Familia) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  nombre;
	}

	
	

}
