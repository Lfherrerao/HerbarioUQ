package co.edu.analisis2.co.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Entity que reprecenta el genero de una planta.
 *
 */
@NamedQueries({ @NamedQuery(name = Genero.LISTAR_TODOS_LOS_GENEROS, query = "select g from Genero g "),
		@NamedQuery(name = Genero.LISTAR_PLANTAS_DADO_UN_GENERO, query = "select plantas from Genero g, IN(g.plantas) plantas where g.id= :idGenero"),
		@NamedQuery(name = Genero.OBTENER_CONSECUTIVO_GENERO, query = "select MAX(g.id) from Genero g"),
		@NamedQuery(name = Genero.OBTENER_GENERO_POR_NOMBRE, query = "select g from Genero g where g.nombre=:nombre")

})
@Entity

public class Genero implements Serializable {
	/**
	 * busca y retorna un genero espesificado que se encuentre en base de datos
	 */
	public static final String OBTENER_GENERO_POR_NOMBRE = "obtener genero por nombre";

	/**
	 * obtener consecutivo
	 */
	public static final String OBTENER_CONSECUTIVO_GENERO = "obtener consecutivo genero";
	/**
	 * listar todas las especies dado el id de un genero.
	 */
	public static final String LISTAR_PLANTAS_DADO_UN_GENERO = "Listar genero dado el id de un genero";
	/**
	 * listar todos los generos
	 */
	public static final String LISTAR_TODOS_LOS_GENEROS = "listar todos los generos";
	/**
	 * codigo unico de el genero.
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	/**
	 * descripcion del genero.
	 */
	@Column(length = 50)
	private String descripcion;
	/**
	 * familia a la que pertenece el genero.
	 */
	@ManyToOne
	private Familia familia;
	/**
	 * lista de plantas de una genero
	 * 
	 */
	@OneToMany(mappedBy = "genero")
	List<Planta> plantas;
	/**
	 * nombre del genero.
	 */
	@Column(length = 30)
	private String nombre;

	private static final long serialVersionUID = 1L;

	public Genero() {
		super();
	}

	/**
	 * @param id
	 * @param descripcion
	 * @param nombre
	 */
	public Genero(int id, String descripcion, String nombre) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.nombre = nombre;
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
	 * @return the plantas
	 */
	public List<Planta> getPlantas() {
		return plantas;
	}

	/**
	 * @param plantas the plantas to set
	 */
	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		Genero other = (Genero) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombre;
	}

}
