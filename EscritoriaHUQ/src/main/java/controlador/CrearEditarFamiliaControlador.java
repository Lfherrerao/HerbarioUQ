/**
 * 
 */
package controlador;

import co.edu.analisis2.co.entidades.Familia;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.FamiliObservable;
import utilidades.Utilidades;

/**
 * @author LENOVO
 *
 */
public class CrearEditarFamiliaControlador {

	/**
	 * campo para el nombre
	 */
	@FXML
	private TextField cmpNombre;
	/**
	 * campo para el id
	 */
	@FXML
	private TextField cmpId;
	/**
	 * campo para la descripcion
	 */
	@FXML
	private TextField cmpDescripcion;

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
	private ObservableList<FamiliObservable> familiasObservablesDetalleCliente;
	/**
	 * Indice de la posicion en la lsita de clientes observables del cliente a
	 * editar
	 */
	private int indiceListaFamiliasObservables;

	/**
	 * Caractere para la validacion
	 */
	private static char caracter;

	/**
	 * 
	 */
	@FXML
	private void initialize() {
		cmpId.requestFocus();

		btnEditar.setVisible(false);
	}
	/**
	 * permite cargar la informacion de un familia para realizar una edicion
	 * 
	 * @param familia a editar
	 */ 
	public void cargarFamilia(FamiliObservable familia) {

		btnAceptar.setVisible(false);
		btnEditar.setVisible(true);
		
		cmpId.setDisable(true);
		cmpNombre.requestFocus();

		cmpId.setText(String.valueOf(familia.getId().getValue()));
		cmpNombre.setText(familia.getNombre().getValue());
		cmpDescripcion.setText(familia.getDescripcion().getValue());
		

		familiasObservablesDetalleCliente = manejador.getFamiliasObservables();

		for (FamiliObservable e : manejador.getFamiliasObservables()) {
			if (e.getId().getValue() == familia.getId().getValue()) {
				indiceListaFamiliasObservables = familiasObservablesDetalleCliente.indexOf(e);
			}
		}

		

	}


	/**
	 * permite registrar una familia en la base de datos
	 */
	@FXML
	public void registrarFamilia() {

		if (validarFormulario()) {

			Familia familia = new Familia();
			familia.setId(manejador.consecutivoFamilia());
			familia.setNombre(cmpNombre.getText());
			familia.setDescripcion(cmpDescripcion.getText());

			if (manejador.registrarFamilia(familia)) {
				manejador.agregarFamiliaALista(familia);

				Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
				escenarioEditar.close();
			} else {
				Utilidades.mostrarMensaje("Registro", "Error en registro!!");
			}

		} else

		{
			Utilidades.mostrarMensajeError("Datos incompletos",
					"Debes ingresar todos los datos. Algunos estan vacíos!");
		}

	}

	/**
	 * Permite editar la informacion de un empleado
	 */
	@FXML
	private void editarFamilia() {

		if (validarFormulario()) {

			Familia familia = new Familia();
			familia.setId(Integer.parseUnsignedInt(cmpId.getText()));
			familia.setNombre(cmpNombre.getText());
			familia.setDescripcion(cmpDescripcion.getText());

			if (manejador.editarFamilia(familia)) {
				Utilidades.mostrarMensaje("Edición", "Se editó el empleado con éxito!");
				familiasObservablesDetalleCliente.set(indiceListaFamiliasObservables, new FamiliObservable(familia));

				escenarioEditar.close();
			} else {
				Utilidades.mostrarMensajeError("Edición", "Error en edición de empleado!");
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
	 * Valida si los campos son diferente de vacio
	 * 
	 * @return true si todos contienen informacion false si algunos estan vacios
	 */
	public boolean validarFormulario() {
		if (!cmpId.getText().equals("") && (!cmpNombre.getText().isEmpty() || cmpNombre.getText().startsWith(" "))
				&& (!cmpDescripcion.getText().isEmpty() || cmpDescripcion.getText().startsWith(" "))) {

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
		this.cmpId.requestFocus();
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
