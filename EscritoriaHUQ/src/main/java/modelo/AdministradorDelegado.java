package modelo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.edu.analisis2.co.entidades.Empleado;
import co.edu.analisis2.co.entidades.Familia;
import co.edu.analisis2.co.entidades.Genero;
import co.edu.analisis2.co.entidades.Recolector;
import co.edu.analisis2.grid.ejb.AdminEJBRemote;
import co.edu.analisis2.grid.excepciones.ElementoRepetidoException;
import co.edu.analisis2.grid.excepciones.ExcepcionesHerbario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministradorDelegado {
	/**
	 * Instancia que representa el EJB remoto de Banco
	 */
	private AdminEJBRemote adminEJB;
	/**
	 * permite manejar una unica instancia para le manejo de delegados
	 */
	public static AdministradorDelegado administradordelegado = instancia();

	/**
	 * constructor para conectar con la capa de negocio
	 */
	private AdministradorDelegado() {
		try {
			adminEJB = (AdminEJBRemote) new InitialContext().lookup(AdminEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static AdministradorDelegado instancia() {

		if (administradordelegado == null) {
			administradordelegado = new AdministradorDelegado();
			return administradordelegado;
		}
		return administradordelegado;
	}

	/**
	 * Permite agregar un empleado al herbario.
	 * 
	 * @param empleado
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Empleado agregarEmpleado(Empleado empleado) throws ElementoRepetidoException {
		return adminEJB.insertarEmpleado(empleado);
	}

	/**
	 * Permite validar un adminstrador en el sistema
	 * 
	 * @param cedula      cedula del administrador
	 * @param contrasenia contrasenia del administrador
	 * @return true si es valido o false si no
	 * @throws ExcepcionesFenix
	 * @see proyectofenix.negocio.BancoEJBRemote#login(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean login(String cedula, String contrasenia) throws ExcepcionesHerbario {
		return adminEJB.login(cedula, contrasenia);
	}

	/**
	 * Genera una lista de empleados observables
	 * 
	 * @return todos los empleados obsevables
	 */
	public ObservableList<EmpleadoObservable> listarEmpleadosObservables() {
		List<Empleado> empleados = adminEJB.listarEmpleados();

		ObservableList<EmpleadoObservable> empleadosObservables = FXCollections.observableArrayList();
		for (Empleado e : empleados) {

			empleadosObservables.add(new EmpleadoObservable(e));
		}
		return empleadosObservables;
	}

	/**
	 * Permite modificar un empleado
	 * 
	 * @param empleado empleado a modificar
	 * @return empleado modificado
	 * @throws ExcepcionesFenix
	 * @see proyectofenix.negocio.BancoEJBRemote#modificarEmpleado(proyectofenix.entidades.Empleado)
	 */
	public Empleado modificarEmpleado(Empleado empleado) throws ExcepcionesHerbario {
		return adminEJB.modificarEmpleado(empleado);
	}

	/**
	 * Permite eliminar un cliente
	 * 
	 * @param cedula numero de documento del cliente a eliminar
	 * @return devuelve true si fue eliminado o false si no
	 * @throws ExcepcionesHerbario Se ejecuta si el empleado a eliminar es null
	 * @see proyectofenix.negocio.BancoEJBRemote#eliminarEmpleado(java.lang.String)
	 */
	public boolean eliminarEmpleado(String cedula) throws ExcepcionesHerbario {
		return adminEJB.eliminarEmpleado(cedula);
	}

//----------------------GESTIONAR RECOLECTOR DELEGADO-------------------	
	public Recolector agregarRecolector(Recolector recolector) throws ElementoRepetidoException {
		return adminEJB.insertarRecolector(recolector);
	}

	public boolean eliminarRecolector(String cedula) throws ExcepcionesHerbario {
		return adminEJB.eliminarRecolector(cedula);
	}

	public ObservableList<RecolectorObservable> listarRecolectoresObservables() {
		List<Recolector> recolector = adminEJB.listarRecolector();

		ObservableList<RecolectorObservable> recolectoresObservables = FXCollections.observableArrayList();
		for (Recolector e : recolector) {

			recolectoresObservables.add(new RecolectorObservable(e));
		}
		return recolectoresObservables;
	}

	public Recolector modificarRecolecto(Recolector recolector) throws ExcepcionesHerbario {
		return adminEJB.modificarRecolector(recolector);
	}

	// ----------------------GESTIONAR FAMILIA DELEGADO-------------------
	public Familia agregarFamilia(Familia familia) throws  ExcepcionesHerbario {
		return adminEJB.crearFamilia(familia);
	}

	/**
	 * generra una lista de familias observables
	 * 
	 * @return
	 */
	public ObservableList<FamiliObservable> listarFamiliaObservables() {

		List<Familia> familia = adminEJB.listarFamilia();

		ObservableList<FamiliObservable> familiasObservables = FXCollections.observableArrayList();
		for (Familia e : familia) {
			familiasObservables.add(new FamiliObservable(e));
		}
		return familiasObservables;
	}

	public Familia modificarFamilia(Familia familia) throws ExcepcionesHerbario {
		return adminEJB.modificarFamilia(familia);
	}

	public int consecutivoFamilia() throws ExcepcionesHerbario {
		return adminEJB.consecutivoFamilia();
	}

	public boolean eliminarFamilia(int id) throws ExcepcionesHerbario {
		return adminEJB.eliminarFamilia(id);
	}
	// ----------------------GESTIONAR GENERO DELEGADO-------------------
	
	public ObservableList<GeneroObservable> listarGenerosObservables() {

		List<Genero> genero = adminEJB.listarGenero();

		ObservableList<GeneroObservable> generosObservables = FXCollections.observableArrayList();
		for (Genero e : genero) {
			generosObservables.add(new GeneroObservable(e));
		}
		return generosObservables;
	}

	public Genero agregarGenero(Genero genero) throws ElementoRepetidoException {
		return adminEJB.insertarGenero(genero);
	}
	public Genero modificarGenero(Genero genero) throws ExcepcionesHerbario {
		return adminEJB.modificarGenero(genero);
	}

	public int consecutivoGenero() throws ExcepcionesHerbario {
		return adminEJB.consecutivoGenero();
	}

	public boolean eliminarGenero(int id) throws ExcepcionesHerbario {
		return adminEJB.eliminarGenero(id);
	}

	
}
