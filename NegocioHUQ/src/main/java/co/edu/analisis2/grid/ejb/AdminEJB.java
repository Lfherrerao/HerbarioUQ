package co.edu.analisis2.grid.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import co.edu.analisis2.co.entidades.Administrador;
import co.edu.analisis2.co.entidades.Asesoria;
import co.edu.analisis2.co.entidades.Empleado;
import co.edu.analisis2.co.entidades.Envio;
import co.edu.analisis2.co.entidades.Familia;
import co.edu.analisis2.co.entidades.Genero;
import co.edu.analisis2.co.entidades.Persona;
import co.edu.analisis2.co.entidades.Planta;
import co.edu.analisis2.co.entidades.Recolector;
import co.edu.analisis2.grid.excepciones.ElementoRepetidoException;
import co.edu.analisis2.grid.excepciones.ExcepcionesHerbario;

/**
 * se encarga de manejara las operaciones realizadas por el operador Session
 * Bean implementation class AdminEJB
 */
@Stateless
@LocalBean
public class AdminEJB implements AdminEJBRemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AdminEJB() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.analisis2.grid.ejb.AdminEJBRemote#insertarEmpleado(co.edu.analisis2.co
	 * .entidades.Empleado)
	 */
	public Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoException {
		if (entityManager.find(Empleado.class, empleado.getCedula()) != null) {
			throw new ElementoRepetidoException("el empleado con esa cedula ya se encuntra registrado");
		} else if (buscarPorCorreo(empleado.getCorreo())) {
			throw new ElementoRepetidoException("el empleado con ese correo ya se encuntra registrado");
		}
		try {
			entityManager.persist(empleado);
			return empleado;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	/**
	 * 
	 * @param correo
	 * @return
	 * 
	 */
	public boolean buscarPorCorreo(String correo) {

		TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.BUSCAR_EMPLEADO_POR_EMAIL, Empleado.class);
		query.setParameter("correo", correo);
		return query.getResultList().size() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.analisis2.grid.ejb.AdminEJBRemote#eliminarEmpleado(java.lang.String)
	 */
	public boolean eliminarEmpleado(String cedula) throws ExcepcionesHerbario {
		Empleado empleadoEliminar = entityManager.find(Empleado.class, cedula);

		if (empleadoEliminar != null) {
			try {
				entityManager.remove(empleadoEliminar);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}

		} else {
			throw new ExcepcionesHerbario(
					"el empleaso con la cedula  <<" + cedula + ">>no se encuentra registrado en bases de datos.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.analisis2.grid.ejb.AdminEJBRemote#buscarEmpleado(java.lang.String)
	 */
	public Empleado buscarEmpleado(String cedula) throws ExcepcionesHerbario {
		Empleado empleadoBuscar = entityManager.find(Empleado.class, cedula);

		if (empleadoBuscar != null) {
			return empleadoBuscar;
		}
		throw new ExcepcionesHerbario(
				"el empleado con cedula <<" + cedula + ">>  no se encuentra registrado en base de datos.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.analisis2.grid.ejb.AdminEJBRemote#listarEmpleados()
	 */
	public List<Empleado> listarEmpleados() {
		TypedQuery<Empleado> empleados = entityManager.createNamedQuery(Empleado.LISTAR_TODOS_LOS_EMPLEADOS,
				Empleado.class);

		return empleados.getResultList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.analisis2.grid.ejb.AdminEJBRemote#modificarEmpleado(co.edu.analisis2.
	 * co.entidades.Empleado)
	 */

	public Empleado modificarEmpleado(Empleado empleado) throws ExcepcionesHerbario {

		if (empleado != null) {
			try {
				entityManager.merge(empleado);
				return empleado;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			throw new ExcepcionesHerbario("El empleado a modificar no se encuentra en base de datos");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.analisis2.grid.ejb.AdminEJBRemote#listarPlantaPorGenero(int)
	 */
	public List<Planta> listarPlantaPorGenero(int idGenero) throws ExcepcionesHerbario {

		if (entityManager.find(Genero.class, idGenero) != null) {
			try {
				TypedQuery<Planta> planta = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_GENERO,
						Planta.class);
				planta.setParameter("idGenero", idGenero);
				return planta.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			throw new ExcepcionesHerbario("no exite un genero con id <" + idGenero + "> en bases de datos.");

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.analisis2.grid.ejb.AdminEJBRemote#listarAdministradorPorId(java.lang.
	 * String)
	 */
	public Administrador buscarAdministradorPorId(String cedula) throws ExcepcionesHerbario {
		try {
			TypedQuery<Administrador> query = entityManager.createNamedQuery(Administrador.ADMIN_POR_ID,
					Administrador.class);
			query.setParameter("cedula", cedula);
			return query.getSingleResult();
		} catch (NoResultException e) {
			throw new ExcepcionesHerbario("No se encontró el Administrador");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.analisis2.grid.ejb.AdminEJBRemote#login(java.lang.String,
	 * java.lang.String)
	 */
	public boolean login(String cedula, String contrasenia) throws ExcepcionesHerbario {
		Administrador administrador = buscarAdministradorPorId(cedula);

		if (administrador != null) {
			if (administrador.getCedula().equals(cedula) && administrador.getContrasenia().equals(contrasenia)) {
				return true;
			}
		}
		throw new ExcepcionesHerbario("Los datos no son correctos");

	}
	
	/**
	 * inicio de sesion con correo y contrasenia para todo tipo de persona.
	 * @param correo
	 * @param contrasenia
	 * @return
	 * @throws ExcepcionesHerbario
	 */
	public Persona inicioSesion(String correo, String contrasenia) throws ExcepcionesHerbario {
		Persona persona = buscarPersonaPorCorreo(correo);

		if (persona != null) {
			if (persona.getCorreo().equals(correo) && persona.getContrasenia().equals(contrasenia)) {
				return persona;
			}
		}
		throw new ExcepcionesHerbario("Los datos no son correctos");

	}
	/**
	 * busca una persona por su cedula.
	 */
	public Persona buscarPersonaPorCorreo(String correo)  {
		
			TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.BUSCAR_PERSONA_POR_CORREO,
					Persona.class);
			query.setParameter("correo", correo);
			return query.getSingleResult();
		
	}

	// ---------------------------------------RECOLECTOR--------------------
	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.analisis2.grid.ejb.AdminEJBRemote#listarRecolector()
	 */
	public List<Recolector> listarRecolector() {
		TypedQuery<Recolector> recolector = entityManager.createNamedQuery(Recolector.LISTAR_TODOS_LOS_RECOLECTORES,
				Recolector.class);

		return recolector.getResultList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.analisis2.grid.ejb.AdminEJBRemote#insertarRecolector(co.edu.analisis2.
	 * co.entidades.Recolector)
	 */
	public Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoException {
		if (entityManager.find(Recolector.class, recolector.getCedula()) != null) {
			throw new ElementoRepetidoException("el recolector con esa cedula ya se encuntra registrado");
		} else if (buscarPorCorreo(recolector.getCorreo())) {
			throw new ElementoRepetidoException("el empleado con ese correo ya se encuntra registrado");
		}
		try {
			entityManager.persist(recolector);
			return recolector;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.analisis2.grid.ejb.AdminEJBRemote#eliminarRecolector(java.lang.String)
	 */
	public boolean eliminarRecolector(String cedula) throws ExcepcionesHerbario {
		Recolector recolectorEliminar = entityManager.find(Recolector.class, cedula);

		if (recolectorEliminar != null) {
			try {
				entityManager.remove(recolectorEliminar);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}

		} else {
			throw new ExcepcionesHerbario(
					"el recolector con la cedula  <<" + cedula + ">>no se encuentra registrado en bases de datos.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.analisis2.grid.ejb.AdminEJBRemote#modificarRecolector(co.edu.analisis2
	 * .co.entidades.Recolector)
	 */
	public Persona modificarRecolector(Persona recolector) throws ExcepcionesHerbario {

		if (recolector != null) {
			try {
				entityManager.merge(recolector);
				return recolector;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			throw new ExcepcionesHerbario("El recolector a modificar no se encuentra en base de datos");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.analisis2.grid.ejb.AdminEJBRemote#buscarRecolector(java.lang.String)
	 */
	public Recolector buscarRecolector(String cedula) throws ExcepcionesHerbario {
		Recolector recolectorBuscar = entityManager.find(Recolector.class, cedula);

		if (recolectorBuscar != null) {
			return recolectorBuscar;
		}
		throw new ExcepcionesHerbario(
				"el recolector con cedula <<" + cedula + ">>  no se encuentra registrado en base de datos.");

	}

	// ----------------------GESTIONAR FAMILIA--------------------
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.analisis2.grid.ejb.AdminEJBRemote#crearFamilia(co.edu.analisis2.co.
	 * entidades.Familia)
	 */
	public Familia crearFamilia(Familia familia) throws ExcepcionesHerbario {
		if (buscarFamiliaPorNombre(familia.getNombre()) != null) {
			throw new ExcepcionesHerbario("la familia con este nombre  ya se encuntra registrado");
		} else
			try {
				entityManager.persist(familia);
				return familia;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}

	}

	public Familia buscarFamiliaPorNombre(String nombre) throws ExcepcionesHerbario {
		try {
			TypedQuery<Familia> query = entityManager.createNamedQuery(Familia.OBTENER_FAMILIA_POR_NOMBRE,
					Familia.class);
			query.setParameter("nombre", nombre);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.analisis2.grid.ejb.AdminEJBRemote#buscarFamilia(java.lang.String)
	 */
	public Familia buscarFamilia(String id) throws ExcepcionesHerbario {
		Familia familiaBuscar = entityManager.find(Familia.class, id);

		if (familiaBuscar != null) {
			return familiaBuscar;
		}
		throw new ExcepcionesHerbario("la familia con id <<" + id + ">>  no se encuentra registrada en base de datos.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.analisis2.grid.ejb.AdminEJBRemote#modificarFamilia(co.edu.analisis2.co
	 * .entidades.Familia)
	 */
	public Familia modificarFamilia(Familia familia) throws ExcepcionesHerbario {

		if (familia != null) {
			try {
				entityManager.merge(familia);
				return familia;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			throw new ExcepcionesHerbario("la familia a modificar no se encuentra en base de datos");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.analisis2.grid.ejb.AdminEJBRemote#listarFamilia()
	 */
	public List<Familia> listarFamilia() {
		TypedQuery<Familia> familia = entityManager.createNamedQuery(Familia.LISTAR_TODAS_LAS_FAMILIAS, Familia.class);

		return familia.getResultList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.analisis2.grid.ejb.AdminEJBRemote#eliminarFamilia(java.lang.String)
	 */
	public boolean eliminarFamilia(int id) throws ExcepcionesHerbario {
		Familia familiaEliminar = entityManager.find(Familia.class, id);

		if (familiaEliminar != null) {
			try {
				entityManager.remove(familiaEliminar);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}

		} else {
			throw new ExcepcionesHerbario(
					"la familia con id  <<" + id + ">>no se encuentra registrado en bases de datos.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.analisis2.grid.ejb.AdminEJBRemote#consecutivoFamilia()
	 */
	public int consecutivoFamilia() throws ExcepcionesHerbario {
		int consecutivo;
		try {
			Query query = entityManager.createNamedQuery(Familia.OBTENER_CONSECUTIVO_FAMILIA);
			consecutivo = (int) query.getSingleResult() + 1;
			return consecutivo;
		} catch (Exception e) {

			throw new ExcepcionesHerbario("No se puede generar el id la familia");
		}
	}
	// ----------------------GESTIONAR FAMILIA--------------------

	/**
	 * agregar el genero en la base de datos por medio de web.
	 * @param genero
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Genero insertarGenero(Genero genero) throws ElementoRepetidoException {
		if (buscarGeneroPorNombre(genero.getNombre()) != null) {
			throw new ElementoRepetidoException("el genero con este nombre  ya se encuntra registrado");
		} else
			try {
				entityManager.persist(genero);
				return genero;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}

	}
/**
 * buscar genero por nombre para poder registrar un genero
 * @param nombre
 * @return
 * @throws ElementoRepetidoException
 */
	public Genero buscarGeneroPorNombre(String nombre) throws ElementoRepetidoException {
		try {
			TypedQuery<Genero> query = entityManager.createNamedQuery(Genero.OBTENER_GENERO_POR_NOMBRE,
					Genero.class);
			query.setParameter("nombre", nombre);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;

		}
	}

	public Genero buscarGenero(int id) throws ExcepcionesHerbario {
		Genero generoBuscar = entityManager.find(Genero.class, id);

		if (generoBuscar != null) {
			return generoBuscar;
		}
		throw new ExcepcionesHerbario("el genero  con id <<" + id + ">>  no se encuentra registrada en base de datos.");
	}

	public Genero modificarGenero(Genero genero) throws ExcepcionesHerbario {

		if (genero != null) {
			try {
				entityManager.merge(genero);
				return genero;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			throw new ExcepcionesHerbario("el genero a modificar no se encuentra en base de datos");
		}

	}
/**
 * lista los generos en la base de datos para mostrarlo por web.
 */
	public List<Genero> listarGenero() {
		TypedQuery<Genero> genero = entityManager.createNamedQuery(Genero.LISTAR_TODOS_LOS_GENEROS, Genero.class);

		return genero.getResultList();

	}

	public boolean eliminarGenero(int id) throws ExcepcionesHerbario {
		Genero generoEliminar = entityManager.find(Genero.class, id);

		if (generoEliminar != null) {
			try {
				entityManager.remove(generoEliminar);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}

		} else {
			throw new ExcepcionesHerbario(
					"el genero con id  <<" + id + ">>no se encuentra registrado en bases de datos.");
		}
	}

	public int consecutivoGenero() throws ExcepcionesHerbario {
		int consecutivo;
		try {
			Query query = entityManager.createNamedQuery(Genero.OBTENER_CONSECUTIVO_GENERO);
			consecutivo = (int) query.getSingleResult() + 1;
			return consecutivo;
		} catch (Exception e) {

			throw new ExcepcionesHerbario("No se puede generar el id del genero");
		}
	}

	// ----------------------GESTIONAR PLANTA--------------------	
	public Planta agregarPlanta(Planta planta) throws ExcepcionesHerbario {
		if (buscarPlantaPorNombre(planta.getNombre()) != null) {
			throw new ExcepcionesHerbario("la planta con este nombre  ya se encuntra registrado");
		} else
			try {
				entityManager.persist(planta);
				return planta;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}

	}
	/**
	 * busca una planta por nobre para compararla con la planta que se va a registrar
	 * @param nombre
	 * @return
	 * @throws ExcepcionesHerbario
	 */
	public Planta buscarPlantaPorNombre(String nombre) throws ExcepcionesHerbario {
		try {
			TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.OBTENER_PLANTA_POR_NOMBRE,
					Planta.class);
			query.setParameter("nombre", nombre);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;

		}
	}
	/**
	 * lista las plantas en la base de datos para mostrarlo por web.
	 */
		public List<Planta> listarPlantas() {
			TypedQuery<Planta> planta = entityManager.createNamedQuery(Planta.LISTAR_TODAS_LAS_PLANTAS, Planta.class);

			return planta.getResultList();

		}
		/**
		 * realiza un envio
		 * @param envio
		 * @return
		 * @throws ExcepcionesHerbario
		 */
		public Envio realizarEnvio(Envio envio) throws ExcepcionesHerbario {
			if (buscarEnvioPorId(envio.getRadicado()) != null) {
				throw new ExcepcionesHerbario("ya se encuentra un envio con este radicado");
			} else
				try {
					entityManager.persist(envio);
					return envio;

				} catch (Exception e) {
					e.printStackTrace();
					return null;

				}

		}
		/**
		 * busca un envio por id
		 * @param id
		 * @return
		 * @throws ExcepcionesHerbario
		 */
		public Envio buscarEnvioPorId(int id) throws ExcepcionesHerbario {
			try {
				TypedQuery<Envio> query = entityManager.createNamedQuery(Envio.OBTENER_ENVIO_POR_RADICADO,
						Envio.class);
				query.setParameter("radicado", id);

				return query.getSingleResult();
			} catch (NoResultException e) {
				return null;

			}
		}

		@Override
		public Persona modificarPersona(Persona recolector) throws ExcepcionesHerbario {
			// TODO Auto-generated method stub
			return null;
		}
		
		public Asesoria pedirAsesoria(Asesoria asesoria) throws ExcepcionesHerbario {
			if (entityManager.find(Asesoria.class, asesoria.getRadicado()) != null) {
				throw new ExcepcionesHerbario("ya se encuentra un envio con este radicado");
			} else
				try {
					entityManager.persist(asesoria);
					return asesoria;

				} catch (Exception e) {
					e.printStackTrace();
					return null;

				}

		}
}
