package controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.analisis2.co.entidades.Recolector;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.RecolectorObservable;

import utilidades.Utilidades;

public class CrearEditarRecolectorControlador {

	/**
	 * campo para la cedula
	 */
	@FXML
	private TextField cmpCedula;
	/**
	 * campo para el nombre
	 */
	@FXML
	private TextField cmpNombre;
	/**
	 * campo para el apellido
	 */
	@FXML
	private TextField cmpApellido;
	/**
	 * campo para el email
	 */
	@FXML
	private TextField cmpCorreo;
	/**
	 * campo para la calve
	 */
	@FXML
	private TextField cmpClave;

	/**
	 * Campo para el telefono
	 */
	@FXML
	private TextField cmpTelefono;

	/**
	 * Campo para informacion
	 */
	@FXML
	private Label cmpInfoEncabezado;

	/**
	 * Boton Aceptar de la ventana edicion empleado y crear empleado
	 */
	@FXML
	private Button btnAceptar;

	/**
	 * Boton Aceptar de la ventana edicion cliente
	 */
	@FXML
	private Button btnEditar;

	@FXML
	private Button btnCancelar;

	/**
	 * representa el escenario en donde se agrega la vista
	 */
	private Stage escenarioEditar;

	/**
	 * instancia del manejador de los escenario
	 */
	private ManejadorEscenarios manejador;

	/**
	 * para almacenar clientes observables que se recibe desde detalle cliente
	 */
	private ObservableList<RecolectorObservable> recolectoresObservablesDetalleRecolector;
	/**
	 * Indice de la posicion en la lsita de clientes observables del cliente a
	 * editar
	 */
	private int indiceListaRecolectoresObservables;

	/**
	 * Caractere para la validacion
	 */
	private static char caracter;

	/**
	 * 
	 */
	@FXML
	private void initialize() {
		cmpCedula.requestFocus();

		btnEditar.setVisible(false);
	}

	/**
	 * permite cargar la informacion de un persona para realizar una edicion
	 * 
	 * @param cliente cliente a editar
	 */
	public void cargarPersona(RecolectorObservable recolector) {

		btnAceptar.setVisible(false);
		btnEditar.setVisible(true);
		cmpCedula.setDisable(true);
		cmpCorreo.setEditable(false);
		cmpNombre.requestFocus();

		cmpCedula.setText(recolector.getCedula().getValue());
		cmpNombre.setText(recolector.getNombre().getValue());
		cmpApellido.setText(recolector.getApellido().getValue());
		cmpCorreo.setText(recolector.getCorreo().getValue());
		cmpClave.setText(recolector.getContrasenia().getValue());

		cmpTelefono.setText(recolector.getTelefono().getValue());

		recolectoresObservablesDetalleRecolector = manejador.getRecolectoresObservables();

		for (RecolectorObservable c : manejador.getRecolectoresObservables()) {
			if (c.getCedula().getValue() == recolector.getCedula().getValue()) {
				indiceListaRecolectoresObservables = recolectoresObservablesDetalleRecolector.indexOf(c);
			}
		}
	}

	/**
	 * @return the recolectoresObservablesDetalleRecolector
	 */
	public ObservableList<RecolectorObservable> getRecolectoresObservablesDetalleRecolector() {
		return recolectoresObservablesDetalleRecolector;
	}

	/**
	 * @param recolectoresObservablesDetalleRecolector the recolectoresObservablesDetalleRecolector to set
	 */
	public void setRecolectoresObservablesDetalleRecolector(
			ObservableList<RecolectorObservable> recolectoresObservablesDetalleRecolector) {
		this.recolectoresObservablesDetalleRecolector = recolectoresObservablesDetalleRecolector;
	}

	/**
	 * @return the escenarioEditar
	 */
	public Stage getEscenarioEditar() {
		return escenarioEditar;
	}

	/**
	 * permite registrar una persona en la base de datos
	 */
	@FXML
	public void registrarRecolector() {

		if (validarFormulario()) {
			if (validarEmail()) {
				Recolector recolector = new Recolector();
				recolector.setCedula(cmpCedula.getText());
				recolector.setNombre(cmpNombre.getText());
				recolector.setApellido(cmpApellido.getText());
				recolector.setContrasenia(cmpClave.getText());
				recolector.setCorreo(cmpCorreo.getText());
				recolector.setTelefono(cmpTelefono.getText());

				if (manejador.agregarRecolector(recolector)) {
					manejador.agregarRecolectorALista(recolector);

					Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
					escenarioEditar.close();
				} else {
					Utilidades.mostrarMensaje("Registro", "Error en registro!!");
				}
			} else {
				Utilidades.mostrarMensajeError("Email inválido", "El email no es válido");
			}

		} else {
			Utilidades.mostrarMensajeError("Datos incompletos",
					"Debes ingresar todos los datos. Algunos estan vacíos!");
		}

	}

	/**
	 * Permite editar la informacion de un empleado
	 */
	@FXML
	private void editarRecolector() {

		btnAceptar.setVisible(false);
		btnEditar.setVisible(true);
		if (validarFormulario()) {
			if (validarEmail()) {
				Recolector recolector = new Recolector();
				recolector.setCedula(cmpCedula.getText());
				recolector.setNombre(cmpNombre.getText());
				recolector.setApellido(cmpApellido.getText());
				recolector.setContrasenia(cmpClave.getText());
				recolector.setCorreo(cmpCorreo.getText());
				recolector.setTelefono(cmpTelefono.getText());

				if (manejador.editarRecolector(recolector)) {
					Utilidades.mostrarMensaje("Edición", "Se editó el recolector con éxito!");

					recolectoresObservablesDetalleRecolector.set(indiceListaRecolectoresObservables,
							new RecolectorObservable(recolector));

					escenarioEditar.close();
				} else {
					Utilidades.mostrarMensajeError("Edición", "Error en edición de recolector!");
				}
			} else {
				Utilidades.mostrarMensajeError("Email inválido", "El email no es válido");
			}
		} else {
			Utilidades.mostrarMensajeError("Datos incompletos",
					"Debes ingresar todos los datos. Algunos estan vacíos!");
		}

	}

	/**
	 * permite cerrar la ventana de editar y crear
	 */
	@FXML
	private void cancelar() {
		escenarioEditar.close();
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
	 * Permite validar que el texto ingresado solo sean letras
	 */
	@FXML
	public void validarSoloLetrasConEspacio(KeyEvent ke) {
		caracter = ke.getCharacter().charAt(0);
		if (!Character.isAlphabetic(caracter) && !Character.isWhitespace(caracter)) {
			ke.consume();
		}
	}

	/**
	 * Permite validar el email
	 * 
	 * @return true si el email es valido false si no
	 */
	@FXML
	public boolean validarEmail() {
		Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
		Matcher m = p.matcher(cmpCorreo.getText());

		if (m.find() && m.group().equals(cmpCorreo.getText())) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Permite validar que el texto ingresado solo sean letras, numeros, # o -
	 */
	@FXML
	public void validarDireccion(KeyEvent ke) {
		caracter = ke.getCharacter().charAt(0);
		if (!Character.isAlphabetic(caracter) && !Character.isDigit(caracter) && !Character.isWhitespace(caracter)
				&& !(caracter == '#') && !(caracter == '-')) {
			ke.consume();
		}
	}

	/**
	 * Valida si los campos son diferente de vacio
	 * 
	 * @return true si todos contienen informacion false si algunos estan vacios
	 */
	public boolean validarFormulario() {
		if (!cmpCedula.getText().equals("") && (!cmpNombre.getText().isEmpty() || cmpNombre.getText().startsWith(" "))
				&& (!cmpApellido.getText().isEmpty() || cmpApellido.getText().startsWith(" "))
				&& !cmpCorreo.getText().isEmpty() && !cmpClave.getText().isEmpty()
				&& !cmpTelefono.getText().isEmpty()) {

			return true;

		} else {
			return false;
		}
	}

	/**
	 * permite cargar el manejador de escenarios
	 * 
	 * @param manejador
	 */
	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
		this.cmpCedula.requestFocus();
	}

	/**
	 * permite modificar el escenario
	 * 
	 * @param escenarioEditar
	 */
	public void setEscenarioEditar(Stage escenarioEditar) {

		this.escenarioEditar = escenarioEditar;
	}

}
