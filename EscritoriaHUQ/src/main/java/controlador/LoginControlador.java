/**
 * 
 */
package controlador;

import co.edu.analisis2.grid.excepciones.ExcepcionesHerbario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import utilidades.Utilidades;

/**
 * Permite controlar la vista login
 * 
 * @author
 */
public class LoginControlador {

	/**
	 * campo para el usuario
	 */
	@FXML
	private TextField cmpUsuario;

	/**
	 * campo para la contrasenia
	 */
	@FXML
	private PasswordField cmpContrasenia;

	/**
	 * Boton Aceptar de la ventana edicion empleado y crear empleado
	 */
	@FXML
	private Button btnAceptar;

	/**
	 * Escenario inicial
	 */
	private ManejadorEscenarios escenarioInicial;

	/**
	 * cedula administrador
	 */
	private String cedula;

	/**
	 * Contrasenia administrador
	 */
	private String contrasenia;

	/**
	 * Caracter a validar
	 */
	private char caracter;

	/**
	 * Constructor
	 */
	public LoginControlador() {

	}

	@FXML
	private void initialize() {
		cmpUsuario.requestFocus();
	}

	/**
	 * Permite validar el ingreso de un administrador
	 */
	public void ingresar() {
		cedula = cmpUsuario.getText();
		contrasenia = cmpContrasenia.getText();

		try {

			if (escenarioInicial.login(cedula, contrasenia)) {
				escenarioInicial.cargarEscenaInicio(escenarioInicial);
			}
		} catch (ExcepcionesHerbario e) {
			Utilidades.mostrarMensajeError("Login", "Error: " + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * Permite cerrar la aplicacación
	 */
	@FXML
	public void cerrarAplicacion() {
		escenarioInicial.getEscenario().close();
	}

	/**
	 * Permite validar que el texto ingresado solo sean numeros
	 */
	@FXML
	public void validarSoloNumeros(KeyEvent ke) {
		caracter = ke.getCharacter().charAt(0);
		if (!Character.isDigit(caracter)) {
			ke.consume();
		}

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
		cmpUsuario.requestFocus();
	}

}
