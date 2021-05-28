package co.edu.analisis2.grid.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.edu.analisis2.co.entidades.Administrador;
import co.edu.analisis2.co.entidades.Empleado;
import co.edu.analisis2.co.entidades.Familia;
import co.edu.analisis2.co.entidades.Genero;
import co.edu.analisis2.co.entidades.Persona;
import co.edu.analisis2.co.entidades.Planta;
import co.edu.analisis2.co.entidades.Recolector;
import co.edu.analisis2.grid.excepciones.ElementoRepetidoException;
import co.edu.analisis2.grid.excepciones.ExcepcionesHerbario;

@Remote
public interface AdminEJBRemote {

	String JNDI = "java:global/EarHUQ/NegocioHUQ/AdminEJB!co.edu.analisis2.grid.ejb.AdminEJBRemote";

	/**
	 * permite agregar un empleado
	 * 
	 * @param empleado
	 * @return
	 * @throws ElementoRepetidoException
	 */
	Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoException;

	/**
	 * permite eliminar un empleado
	 * 
	 * @param cedula
	 * @return
	 * @throws ExcepcionesHerbario
	 */
	boolean eliminarEmpleado(String cedula) throws ExcepcionesHerbario;

	/**
	 * permite buscar un empleado.
	 * 
	 * @param cedula
	 * @return
	 * @throws ExcepcionesHerbario
	 */
	public Empleado buscarEmpleado(String cedula) throws ExcepcionesHerbario;

	/**
	 * lista todos los empleados.
	 * 
	 * @return
	 */
	public List<Empleado> listarEmpleados();

	/**
	 * permite modificar a un empleado.
	 * 
	 * @param empleado
	 * @return
	 * @throws ExcepcionesHerbario
	 */
	public Empleado modificarEmpleado(Empleado empleado) throws ExcepcionesHerbario;

	/**
	 * permite listar todas las plantas de un genero.
	 * 
	 * @param idGenero
	 * @return
	 * @throws ExcepcionesHerbario
	 */
	public List<Planta> listarPlantaPorGenero(int idGenero) throws ExcepcionesHerbario;

	/**
	 * 
	 * @param cedula
	 * @return
	 * @throws ExcepcionesHerbario
	 */
	Administrador buscarAdministradorPorId(String cedula) throws ExcepcionesHerbario;

	/**
	 * 
	 * @param cedula
	 * @param contrasenia
	 * @return
	 * @throws ExcepcionesHerbario
	 */
	boolean login(String cedula, String contrasenia) throws ExcepcionesHerbario;

	/**
	 * lista los recolectores
	 * 
	 * @return
	 */
	public List<Recolector> listarRecolector();

	/**
	 * agregar recolector.
	 * 
	 * @param recolector
	 * @return
	 * @throws ElementoRepetidoException
	 */

	public Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoException;

	/**
	 * elimina los recolectores
	 * 
	 * @param cedula
	 * @return
	 * @throws ExcepcionesHerbario
	 */

	/**
	 * elimina un recolector
	 * 
	 * @param cedula
	 * @return
	 * @throws ExcepcionesHerbario
	 */
	boolean eliminarRecolector(String cedula) throws ExcepcionesHerbario;

	/**
	 * modifica un recolector
	 * 
	 * @param recolector
	 * @return
	 * @throws ExcepcionesHerbario
	 */

	Persona modificarPersona(Persona recolector) throws ExcepcionesHerbario;

	/**
	 * busca un recolector
	 * 
	 * @param cedula
	 * @return
	 * @throws ExcepcionesHerbario
	 */

	Recolector buscarRecolector(String cedula) throws ExcepcionesHerbario;

	/**
	 * crear familia
	 * 
	 * @param familia
	 * @return
	 * @throws ElementoRepetidoException
	 * @throws ExcepcionesHerbario 
	 */
	Familia crearFamilia(Familia familia) throws  ExcepcionesHerbario;

	/**
	 * buscar familia
	 * 
	 * @param id
	 * @return
	 * @throws ExcepcionesHerbario
	 */
	Familia buscarFamilia(String id) throws ExcepcionesHerbario;

	/**
	 * modificar familia.
	 * 
	 * @param familia
	 * @return
	 * @throws ExcepcionesHerbario
	 */
	Familia modificarFamilia(Familia familia) throws ExcepcionesHerbario;

	/**
	 * listar familia
	 * 
	 * @return
	 */
	
	public List<Familia> listarFamilia();

	/**
	 * eliminar familia
	 * 
	 * @param id
	 * @return
	 * @throws ExcepcionesHerbario
	 */
	boolean eliminarFamilia(int id) throws ExcepcionesHerbario;
/**
 * para generar el id automatico.
 * @return
 * @throws ExcepcionesHerbario
 */
	 public int consecutivoFamilia() throws ExcepcionesHerbario;
//------------------gestionar genero remote---------------------------
	 
	 
	 /**
	  * insertar genero
	  * @param genero
	  * @return
	  * @throws ElementoRepetidoException
	  */
	 Genero insertarGenero(Genero genero) throws ElementoRepetidoException;
	 
	 /**
	  * buscar genero remote
	  * @param id
	  * @return
	  * @throws ExcepcionesHerbario
	  */
	 Genero buscarGenero(int id) throws ExcepcionesHerbario;
	 
	 
	 /**
	  * modificar genero remote
	  * @param genero
	  * @return
	  * @throws ExcepcionesHerbario
	  */
	 Genero modificarGenero(Genero genero) throws ExcepcionesHerbario;
	 
	 
	 /**
	  * listar genero remote
	  * @return
	  */
	 List<Genero> listarGenero();
	 
	 
	 /**
	  * eliminar genero remote.
	  * @param id
	  * @return
	  * @throws ExcepcionesHerbario
	  */
	 boolean eliminarGenero(int id) throws ExcepcionesHerbario;
	 
	 
	 /**
	  * consecutivo genero remote
	  * @return
	  * @throws ExcepcionesHerbario
	  */
	 int consecutivoGenero() throws ExcepcionesHerbario;
}
