package controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.analisis2.co.entidades.Empleado;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.EmpleadoObservable;
import utilidades.Utilidades;

/**
 * Permite controlar la vista crear_editar empleado
 * 
 * @author leo
 * @version 1.0
 */
public class CrearEditarEmpleadoControlador {
 
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
	 * Lista para los telefonos
	 */
	String telefonos;

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
	private ObservableList<EmpleadoObservable> empleadosObservablesDetalleCliente;
	/**
	 * Indice de la posicion en la lsita de clientes observables del cliente a
	 * editar
	 */
	private int indiceListaEmpleadosObservables;

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
	public void cargarPersona(EmpleadoObservable empleado) {

		btnAceptar.setVisible(false);
		btnEditar.setVisible(true);
		cmpInfoEncabezado.setText("Por favor edite la información del empleado");
		cmpCedula.setDisable(true);
		cmpCorreo.setEditable(false);
		cmpNombre.requestFocus();

		cmpCedula.setText(empleado.getCedula().getValue());
		cmpNombre.setText(empleado.getNombre().getValue());
		cmpApellido.setText(empleado.getApellido().getValue());
		cmpCorreo.setText(empleado.getCorreo().getValue());
		cmpClave.setText(empleado.getContrasenia().getValue());
		cmpTelefono.setText(empleado.getTelefono().getValue());

		empleadosObservablesDetalleCliente = manejador.getEmpleadosObservables();

		for (EmpleadoObservable e : manejador.getEmpleadosObservables()) {
			if (e.getCedula().getValue() == empleado.getCedula().getValue()) {
				indiceListaEmpleadosObservables = empleadosObservablesDetalleCliente.indexOf(e);
			}
		}

		

	}

	/**
	 * permite registrar una persona en la base de datos
	 */
	@FXML
	public void registrarCliente() {

		if (validarFormulario()) {
			if (validarEmail()) {
				Empleado empleado = new Empleado();
				empleado.setCedula(cmpCedula.getText());
				empleado.setNombre(cmpNombre.getText());
				empleado.setApellido(cmpApellido.getText());
				empleado.setContrasenia(cmpClave.getText());
				empleado.setCorreo(cmpCorreo.getText());
				empleado.setTelefono(cmpTelefono.getText());

				if (manejador.registrarEmpleado(empleado)) {
					manejador.agregarEmpleadoALista(empleado);

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
	private void editarEmpleado() {

		if (validarFormulario()) {
			if (validarEmail()) {
				Empleado empleado = new Empleado();
				empleado.setCedula(cmpCedula.getText());
				empleado.setNombre(cmpNombre.getText());
				empleado.setApellido(cmpApellido.getText());
				empleado.setContrasenia(cmpClave.getText());
				empleado.setCorreo(cmpCorreo.getText());
				empleado.setTelefono(cmpTelefono.getText());

				if (manejador.editarEmpleado(empleado)) {
					Utilidades.mostrarMensaje("Edición", "Se editó el empleado con éxito!");
					empleadosObservablesDetalleCliente.set(indiceListaEmpleadosObservables,
							new EmpleadoObservable(empleado));

					escenarioEditar.close();
				} else {
					Utilidades.mostrarMensajeError("Edición", "Error en edición de empleado!");
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
