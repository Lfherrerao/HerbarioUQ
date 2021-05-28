/**
 * 
 */
package controlador;


import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;


/**
 * Permite controlar la vista inicio
 * @author 
 */
public class InicioControlador {

	/**
	 * Barra menu
	 */
	@FXML
	private MenuBar menuBar;
	
	/**
	 * Escenario inicial
	 */
	private ManejadorEscenarios escenarioInicial;
	
	/** 
	 * Constructor
	 */
	public InicioControlador() { 
		
	}
	
	@FXML
	private void initialize() {

	}


	/**
	 * Permite mostrar la escena de detalle empleado
	 */
	@FXML
	public void cargarGestionarEmpleado() {
		escenarioInicial.cargarEscenaDetalleEmpleado();
	}
	/**
	 * Permite mostrar la escena de detalle empleado
	 */
	@FXML
	public void cargarGestionarRecolector() {
		escenarioInicial.cargarEscenaDetalleRecolector();
	}
	/**
	 * Permite mostrar la escena de detalle genero
	 */
	@FXML
	public void cargarGestionarGenero() {
		escenarioInicial.cargarEscenaDetallGenero();
	}
	/**
	 * Permite mostrar la escena de detalle familia
	 */
	@FXML
	public void cargarGestionarFamilia() {
		escenarioInicial.cargarEscenaDetalleFamilia();
	}
	/**
	 * Permite mostrar la escena de detalle empleado
	 */
	@FXML
	public void cargarEscenarioCrearEmpleado() {
		escenarioInicial.cargarEscenarioCrearEmpleado();
	}
	
	@FXML
	public void cerrarAplicacion() {
		escenarioInicial.getEscenario().close();
	} 
	
	
	

	/**
	 * @return the escenarioInicial
	 */
	public ManejadorEscenarios getEscenarioInicial() {
		return escenarioInicial;
	}

	/**
	 * @param escenarioInicial the escenarioInicial to set
	 */
	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
		this.escenarioInicial = escenarioInicial;
	}

}
