package controlador;

import java.io.IOException;

import co.edu.analisis2.co.entidades.Empleado;
import co.edu.analisis2.co.entidades.Familia;
import co.edu.analisis2.co.entidades.Genero;
import co.edu.analisis2.co.entidades.Recolector;
import co.edu.analisis2.grid.excepciones.ExcepcionesHerbario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;
import modelo.AdministradorDelegado;
import modelo.EmpleadoObservable;
import modelo.FamiliObservable;
import modelo.GeneroObservable;
import modelo.RecolectorObservable;
import utilidades.Utilidades;

/**
 * Permite manejar los escenarios que tiene la aplicacion
 * 
 * @author
 * @version 1.0
 */
public class ManejadorEscenarios {

	/**
	 * contenedor prinpipal de la aplicacion
	 */
	private Stage escenario;
	/**
	 * tipo de panel inicial
	 */
	private BorderPane bordePanel;

	/**
	 * Lista de empleados observables
	 */
	private ObservableList<EmpleadoObservable> empleadosObservables;
	/**
	 * Lista de empleados observables
	 */
	private ObservableList<RecolectorObservable> recolectoresObservables;
	/**
	 * Lista de generos observables
	 */
	private ObservableList<GeneroObservable> generosObservables;
	/**
	 * lista de familias observables
	 */
	private ObservableList<FamiliObservable> familiasObservables;

	/**
	 * conexion con capa de negocio
	 */
	private AdministradorDelegado adminDelegado;

	/**
	 * recibe el escenario principla de la aplicacion
	 * 
	 * @param escenario inicial
	 */
	public ManejadorEscenarios(Stage escenario) {

		this.escenario = escenario;

		adminDelegado = AdministradorDelegado.administradordelegado;
		empleadosObservables = FXCollections.observableArrayList();

		try {
			// se inicializa el escenario
			escenario.setTitle("HERBARIO UNIVERSIDAD DEL QUINDIO");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./../vista/login.fxml"));

			bordePanel = (BorderPane) loader.load();
			// se carga la escena
			Scene scene = new Scene(bordePanel);
			escenario.centerOnScreen();
			escenario.setScene(scene);
			escenario.show();

			// se carga el controlador del inicio
			LoginControlador loginControlador = loader.getController();
			loginControlador.setEscenarioInicial(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Permite cargar la escena de inicio
	 * 
	 * @param manejador manejador de escenarios
	 */
	public void cargarEscenaLogin(ManejadorEscenarios manejador) {

		try {
			// se inicializa el escenario
			escenario.setTitle("Herbario universiad del quindio");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/login.fxml"));

			bordePanel = (BorderPane) loader.load();
			// se carga la escena
			Scene scene = new Scene(bordePanel);
			escenario.centerOnScreen();
			escenario.setScene(scene);
			escenario.show();

			// se carga el controlador del inicio
			LoginControlador loginControlador = loader.getController();
			loginControlador.setEscenarioInicial(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite cargar la escena de inicio
	 * 
	 * @param manejador manejador de escenarios
	 */
	public void cargarEscenaInicio(ManejadorEscenarios manejador) {

		try {
			// se inicializa el escenario
			escenario.setTitle("herbaeioHUQ");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./../vista/inicio.fxml"));

			bordePanel = (BorderPane) loader.load();

			// se carga la escena
			Scene scene = new Scene(bordePanel);
			escenario.setScene(scene);
			escenario.centerOnScreen();
			escenario.show();

			// se carga el controlador del inicio
			InicioControlador inicioControlador = loader.getController();
			inicioControlador.setEscenarioInicial(manejador);

			// cargarEscenaDetalleCliente();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga la escena de detalle empleado en el centro del escenario.
	 */
	public void cargarEscenaDetalleEmpleado() {

		try {

			empleadosObservables = adminDelegado.listarEmpleadosObservables();

			FXMLLoader loader3 = new FXMLLoader();
			loader3.setLocation(Main.class.getResource("./../vista/detalle_empleado.fxml"));
			AnchorPane panelAncho = (AnchorPane) loader3.load();
			bordePanel.setCenter(panelAncho);

			EmpleadoControlador controlador = loader3.getController();
			controlador.setEscenarioInicial(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void cargarEscenaDetalleFamilia() {
		try {

			familiasObservables = adminDelegado.listarFamiliaObservables();
			FXMLLoader loader3 = new FXMLLoader();
			loader3.setLocation(Main.class.getResource("./../vista/detalle_familia.fxml"));

			AnchorPane panelAncho = (AnchorPane) loader3.load();
			bordePanel.setCenter(panelAncho);

			FamiliaControlador controlador = loader3.getController();
			controlador.setEscenarioInicial(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga la escena de detalle empleado en el centro del escenario.
	 */
	public void cargarEscenaDetalleRecolector() {

		try {

			recolectoresObservables = adminDelegado.listarRecolectoresObservables();

			FXMLLoader loader2 = new FXMLLoader();

			loader2.setLocation(Main.class.getResource("./../vista/detalle_recolector.fxml"));

			AnchorPane panelAncho = (AnchorPane) loader2.load();
			bordePanel.setCenter(panelAncho);
			RecolectorControlador controlador = loader2.getController();
			controlador.setEscenarioInicial(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Muestra el escenario para crear un empleado nuevo
	 */
	public void cargarEscenarioCrearEmpleado() {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./../vista/crear_editar_empleado.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Registrar Empleado");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			CrearEditarEmpleadoControlador empleadoControlador = loader.getController();
			empleadoControlador.setEscenarioEditar(escenarioCrear);
			empleadoControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Carga la escena de editar empleado
	 * 
	 * @param empleado empleado a editar
	 */

	public void cargarEscenarioEditarEmpleado(Empleado empleado) {

		try {

			// Se crea un empleado observable
			EmpleadoObservable empleadoObservableEditar = new EmpleadoObservable(empleado);

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./../vista/crear_editar_empleado.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioEditar = new Stage();
			escenarioEditar.setTitle("Editar Empleado");
			Scene scene = new Scene(page);
			escenarioEditar.setScene(scene);

			// se carga el controlador
			CrearEditarEmpleadoControlador empleadoControlador = loader.getController();
			empleadoControlador.setEscenarioEditar(escenarioEditar);
			empleadoControlador.setManejador(this);
			empleadoControlador.cargarPersona(empleadoObservableEditar);

			// se crea el escenario
			escenarioEditar.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Carga la escena de editar familia
	 * 
	 * @param empleado empleado a editar
	 */

	public void cargarEscenarioEditarFamilia(Familia familia) {

		try {

			// Se crea un empleado observable
			FamiliObservable familiaObservableEditar = new FamiliObservable(familia);

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./../vista/crear_editar_familia.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioEditar = new Stage();
			escenarioEditar.setTitle("Editar Familia");
			Scene scene = new Scene(page);
			escenarioEditar.setScene(scene);

			// se carga el controlador
			CrearEditarFamiliaControlador familiaControlador = loader.getController();
			familiaControlador.setEscenarioEditar(escenarioEditar);
			familiaControlador.setManejador(this);
			familiaControlador.cargarFamilia(familiaObservableEditar);

			// se crea el escenario
			escenarioEditar.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * permite agregar una empleado a la lista obsevable
	 * 
	 * @param empleado
	 */
	public void agregarEmpleadoALista(Empleado empleado) {
		empleadosObservables.add(new EmpleadoObservable(empleado));
	}

	/**
	 * permite agregar una familia a una lista observable
	 * 
	 * @param familia
	 */
	public void agregarFamiliaALista(Familia familia) {
		familiasObservables.add(new FamiliObservable(familia));
	}

	/**
	 * deveulve una instancia del escenario
	 * 
	 * @return escenario
	 */
	public Stage getEscenario() {
		return escenario;
	}

	/**
	 * @return the recolectoresObservables
	 */
	public ObservableList<RecolectorObservable> getRecolectoresObservables() {
		return recolectoresObservables;
	}

	/**
	 * @return the generosObservables
	 */
	public ObservableList<GeneroObservable> getGenerosObservables() {
		return generosObservables;
	}

	/**
	 * @param generosObservables the generosObservables to set
	 */
	public void setGenerosObservables(ObservableList<GeneroObservable> generosObservables) {
		this.generosObservables = generosObservables;
	}

	/**
	 * @return the familiasObservables
	 */
	public ObservableList<FamiliObservable> getFamiliasObservables() {
		return familiasObservables;
	}

	/**
	 * @param familiasObservables the familiasObservables to set
	 */
	public void setFamiliasObservables(ObservableList<FamiliObservable> familiasObservables) {
		this.familiasObservables = familiasObservables;
	}

	/**
	 * @return the empleadosObservables
	 */
	public ObservableList<EmpleadoObservable> getEmpleadosObservables() {
		return empleadosObservables;
	}

	/**
	 * @param empleadosObservables the empleadosObservables to set
	 */
	public void setEmpleadosObservables(ObservableList<EmpleadoObservable> empleadosObservables) {
		this.empleadosObservables = empleadosObservables;
	}

	/**
	 * Permite registrar un empleado en la base de datos
	 * 
	 * @param empleado a registrar
	 * @return true si quedo registrado false si no se registro
	 */
	public boolean registrarEmpleado(Empleado empleado) {
		try {
			return adminDelegado.agregarEmpleado(empleado) != null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * permite registrar una familia en la base de datos
	 * 
	 * @param familia
	 * @return
	 */
	public boolean registrarFamilia(Familia familia) {
		try {
			return adminDelegado.agregarFamilia(familia) != null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Permite editar un empleado en la base de datos
	 * 
	 * @param empleado a editar
	 * @return true si se edito o false si no
	 */
	public boolean editarEmpleado(Empleado empleado) {
		try {
			return adminDelegado.modificarEmpleado(empleado) != null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error por: " + e.getMessage());
		}
		return false;
	}

	/**
	 * perite editar una familia en la base de datos
	 * 
	 * @param familia
	 * @return
	 */
	public boolean editarFamilia(Familia familia) {
		try {
			return adminDelegado.modificarFamilia(familia) != null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error por: " + e.getMessage());
		}
		return false;
	}

	/**
	 * Permite eliminar un empleado
	 * 
	 * @param empleado empleado a eliminar
	 * @return true si se elimino false si no
	 */
	public boolean eliminarEmpleado(Empleado empleado) {
		try {
			return adminDelegado.eliminarEmpleado(empleado.getCedula());
		} catch (ExcepcionesHerbario e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean eliminarFamilia(Familia familia) {
		try {
			return adminDelegado.eliminarFamilia(familia.getId());
		} catch (ExcepcionesHerbario e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Permite validar un adminstrador en el sistema
	 * 
	 * @param cedula      cedula del administrador
	 * @param contrasenia contrasenia del administrador
	 * @return true si es valido o false si no
	 * @throws ExcepcionesFenix
	 * @see proyectofenix.escritorio.modelo.BancoDelegado#login(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean login(String cedula, String contrasenia) throws ExcepcionesHerbario {
		try {
			return adminDelegado.login(cedula, contrasenia);
		} catch (ExcepcionesHerbario e) {
			Utilidades.mostrarMensajeError("Login", "Error: " + e.getMessage());
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * permite agregar unarecolector a la lista obsevable
	 * 
	 * @param recolector
	 */
	public void agregarRecolectorALista(Recolector recolector) {
		recolectoresObservables.add(new RecolectorObservable(recolector));
	}

	/**
	 * Muestra el escenario para crear un empleado nuevo
	 */
	public void cargarEscenarioCrearRecolector() {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./../vista/editar_recolector.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Registrar recolector");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			CrearEditarRecolectorControlador recolectorControlador = loader.getController();
			recolectorControlador.setEscenarioEditar(escenarioCrear);
			recolectorControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * metodo encargado de cargar la escena para crear una familia
	 */
	public void cargarEscenarioCrearFamilia() {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./../vista/crear_editar_familia.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Registrar familia");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			CrearEditarFamiliaControlador recolectorControlador = loader.getController();
			recolectorControlador.setEscenarioEditar(escenarioCrear);
			recolectorControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * se encarga de eliminar un recolector
	 * 
	 * @param recolector
	 * @return
	 */
	public boolean eliminarRecolector(Recolector recolector) {
		try {
			return adminDelegado.eliminarRecolector(recolector.getCedula());
		} catch (ExcepcionesHerbario e) {
			e.printStackTrace();
		}
		return false;
	}
/**
 * agregar recolector
 * @param recolector
 * @return
 */
	public boolean agregarRecolector(Recolector recolector) {
		try {
			return adminDelegado.agregarRecolector(recolector) != null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
/**
 * editar recolector manejador
 * @param recolector
 * @return
 */
	public boolean editarRecolector(Recolector recolector) {
		try {
			return adminDelegado.modificarRecolecto(recolector) != null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error por: " + e.getMessage());
		}
		return false;
	}
/**
 * crea la ascena para editar familia
 * @param recolector
 */
	public void cargarEscenarioEditarRecolector(Recolector recolector) {

		try {

			// Se crea un empleado observable
			RecolectorObservable empleadoObservableEditar = new RecolectorObservable(recolector);

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./../vista/editar_recolector.fxml"));

			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioEditar = new Stage();
			escenarioEditar.setTitle("Recolector");
			Scene scene = new Scene(page);
			escenarioEditar.setScene(scene);

			// se carga el controlador
			CrearEditarRecolectorControlador recolectorControlador = loader.getController();
			recolectorControlador.setEscenarioEditar(escenarioEditar);
			recolectorControlador.setManejador(this);
			recolectorControlador.cargarPersona(empleadoObservableEditar);

			// se crea el escenario
			escenarioEditar.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * consecutivo para id de familia.
	 * 
	 * @return
	 */
	public int consecutivoFamilia() {
		try {
			return adminDelegado.consecutivoFamilia();
		} catch (ExcepcionesHerbario e) {
			Utilidades.mostrarMensajeError("Registro familia", "Error en registro: " + e.getMessage());
			return -1;
		}

	}
	
//------------------------GENERO--------------------	
	
	/**
	 * consecutivo para id de GENERO.
	 * 
	 * @return
	 */
	public int consecutivoGenero() {
		try {
			return adminDelegado.consecutivoGenero();
		} catch (ExcepcionesHerbario e) {
			Utilidades.mostrarMensajeError("Registro genero", "Error en registro: " + e.getMessage());
			return -1;
		}

	}
	/**
	 * permite agregar un genero a la lista obsevable
	 * 
	 * @param recolector
	 */
	public void agregarGeneroALista(Genero genero) {
		generosObservables.add(new GeneroObservable(genero));
	}
	
	/**
	 * se encarga de eliminar un genero
	 * 
	 * @param genero
	 * @return
	 */
	public boolean eliminarGenero(Genero genero) {
		try {
			return adminDelegado.eliminarGenero(genero.getId());
		} catch (ExcepcionesHerbario e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * editar genero manejador
	 * @param recolector
	 * @return
	 */
	public boolean editarGenero(Genero genero) {
		try {
			return adminDelegado.modificarGenero(genero) != null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error por: " + e.getMessage());
		}
		return false;
	}
	/**
	 * metodo encargado de cargar la escena para crear una familia
	 */
	public void cargarEscenarioCrearGenero() {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./../vista/crear_editar_genero.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Registrar genero");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			CrearEditarGeneroControlador generoControlador = loader.getController();
			generoControlador.setEscenarioEditar(escenarioCrear);
			generoControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * permite registrar una familia en la base de datos
	 * 
	 * @param familia
	 * @return
	 */
	public boolean registrarGenero(Genero genero) {
		try {
			return adminDelegado.agregarGenero(genero) != null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Carga la escena de detalle empleado en el centro del escenario.
	 */
	public void cargarEscenaDetallGenero() {

		try {

			generosObservables = adminDelegado.listarGenerosObservables();

			FXMLLoader loader2 = new FXMLLoader();

			loader2.setLocation(Main.class.getResource("./../vista/detalle_genero.fxml"));

			AnchorPane panelAncho = (AnchorPane) loader2.load();
			bordePanel.setCenter(panelAncho);
			GeneroControlador controlador = loader2.getController();
			controlador.setEscenarioInicial(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Carga la escena de editar genero
	 * 
	 * @param genero a editar
	 */

	public void cargarEscenarioEditarGenero(Genero genero) {

		try {

			// Se crea un empleado observable
			GeneroObservable generoObservableEditar = new GeneroObservable(genero);

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./../vista/crear_editar_genero.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioEditar = new Stage();
			escenarioEditar.setTitle("Editar genero");
			Scene scene = new Scene(page);
			escenarioEditar.setScene(scene);

			// se carga el controlador
			CrearEditarGeneroControlador generoControlador = loader.getController();
			generoControlador.setEscenarioEditar(escenarioEditar);
			generoControlador.setManejador(this);
			generoControlador.cargarGenero(generoObservableEditar);

			// se crea el escenario
			escenarioEditar.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
