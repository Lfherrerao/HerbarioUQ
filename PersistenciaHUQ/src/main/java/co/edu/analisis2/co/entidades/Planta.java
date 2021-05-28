package co.edu.analisis2.co.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;




/**
 * Entity que reprecenta una planta de un herbario Planta
 *
 */
@NamedQueries({

	
		@NamedQuery(name = Planta.LISTAR_TODAS_LAS_PLANTAS, query = "select p from Planta p "),
		@NamedQuery(name = Planta.LISTAR_PLANTAS_POR_ESTADO, query = "select e.estado from Envio e where e.estado=:estado"),
		@NamedQuery(name = Planta.LISTAR_PLANTAS_POR_FAMILIA, query = "select p from Planta p where p.genero.familia.id =:idFamilia and p.genero.id=:idGenero "),
		@NamedQuery(name = Planta.LISTAR_PLANTAS_POR_GENERO, query = "select p from Planta p where p.genero.id =:idGenero "),
		@NamedQuery(name = Planta.INFORMACION_DETALLADA_POR_ID, query = "select p from Planta p where p.id =:id "),
		@NamedQuery(name = Planta.ESTADO_DE_PLANTAS_ENVIADAS_POR_RECOLECTOR, query = "select p from Planta p  where p.envio.persona.cedula=:cedula and p.envio.estado=:estado"),
		@NamedQuery(name = Planta.FAMILIA_DADO_UNA_PLANTA, query = "select p.genero.familia from Planta p  where p.id= :idPlanta"),
		@NamedQuery(name = Planta.LISTAR_POR_FECHA, query = "select p.envio.radicado, p.genero.nombre, p.genero.familia.nombre, p.envio.persona.cedula, p.envio.persona.correo from Planta p  where p.envio.fechaEnvio= :fecha"),
		@NamedQuery(name = Planta.LISTAR_POR_FECHA_DTO, query = "select new co.analisis2.uniquindio.grid.DTO.TurnosPorFechaDTO  "
		+ "(p.envio.radicado, p.genero.nombre, p.genero.familia.nombre, p.envio.persona.cedula, p.envio.persona.correo) from Planta p where p.envio.fechaEnvio= :fecha"),
		@NamedQuery(name = Planta.NOMBRE_FAMILIA_CON_MAS_ESPECIES_MAX, query = "select MAX(p.genero.familia.nombre) from Planta p "),
		@NamedQuery(name = Planta.OBTENER_FAMILIA_MAXIMAS_PLANTAS, query = "select p.genero.familia from Planta p where p.genero.familia.nombre=select MAX(p.genero.familia.nombre) from Planta p"),
		@NamedQuery(name = Planta.OBTENER_PLANTA_POR_NOMBRE, query = "select p from Planta p where p.nombre=:nombre")
})
@Entity

public class Planta implements Serializable {
	/**
	 * obtiene una planta por el nombre.
	 */
	public static final String OBTENER_PLANTA_POR_NOMBRE="Obtine una planta por nombre";
	
	/**
	 * consulta que permita determinar cual es la familia que más especies tiene registradas SOlo con max.
	 */
	public static final String OBTENER_FAMILIA_MAXIMAS_PLANTAS ="consulta que permita determinar cual es la familia que más especies tiene registradas solo con max.";
	/**
	 * consulta que permita determinar cual es la familia que más especies tiene registradas.
	 */
	public static final String NOMBRE_FAMILIA_CON_MAS_ESPECIES_MAX="consulta que permita determinar cual es la familia que más especies tiene registradas.";
	/**
	 * LISTAR por fecha
	 */
	public static final String LISTAR_POR_FECHA = "LISTAR por fecha";
	/**
	 * LISTAR por fecha
	 */
	public static final String LISTAR_POR_FECHA_DTO = "listar  por fecha con DTO";
	/**
	 * familia a la que pertenece la planta, dado el id de la planta.
	 */
	public static final String FAMILIA_DADO_UNA_PLANTA = "familia a la que pertenece la planta, dado el id de la planta.";

	/**
	 * lista de plantas enviadas aceptadas o rechazadas.
	 */
	public static final String ESTADO_DE_PLANTAS_ENVIADAS_POR_RECOLECTOR = "plantas enviadas aceptadas o rechazadas";

	/**
	 * ver informacion detallada de una planta por id.
	 */
	public static final String INFORMACION_DETALLADA_POR_ID = "informacion detallada";
	/**
	 * listar plantas por genero.
	 */
	public static final String LISTAR_PLANTAS_POR_GENERO = "listar olantas por genero";
	/**
	 * lista todos los generos de plantas vegetales.
	 */
	public static final String LISTAR_TODAS_LAS_PLANTAS = "listar todas las plantas";
	/**
	 * listar las plantas aceptadas
	 */
	public static final String LISTAR_PLANTAS_POR_ESTADO = "listar plantas aceptadas O rechazadas";

	/**
	 * listar plantas por familia.
	 */
	public static final String LISTAR_PLANTAS_POR_FAMILIA = "listar olantas por familia";
	/**
	 * codigo unico de la planta.
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	/**
	 * nombre de la planta
	 */
	@Column(length = 15)
	private String nombre;
	/**
	 * especie a la que pertenece la planta.
	 */
	@Column(length = 15)
	private String especie;
	/**
	 * color de la planta
	 */
	@Column(length = 15)
	private String color;
	/**
	 * imagen de la planta recolectada
	 */
	private byte imagen;

	
	/**
	 * genero a la que pertenece la planta.
	 */
	@ManyToOne
	private Genero genero;
	/**
	 * envio de la planta
	 */
	@OneToOne
	private Envio envio;
	private static final long serialVersionUID = 1L;

	public Planta() {
		super();
	}

	/**
	 * @return the envio
	 */
	public Envio getEnvio() {
		return envio;
	}

	/**
	 * @param envio the envio to set
	 */
	public void setEnvio(Envio envio) {
		this.envio = envio;
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
	 * @return the especie
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * @param especie the especie to set
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the imagen
	 */
	public byte getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(byte imagen) {
		this.imagen = imagen;
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
		Planta other = (Planta) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  nombre ;
	}
	
	

}
