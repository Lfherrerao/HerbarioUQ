package controlador;

import java.util.Optional;

import co.edu.analisis2.co.entidades.Recolector;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.RecolectorObservable;
import utilidades.Utilidades;

public class RecolectorControlador {

	/**
	 * table donde se almacena la informacion de los clientes
	 */
	@FXML
	private TableView<RecolectorObservable> tablaRecolector;
	/**
	 * hace referencia a la columna con las cedulas
	 */
	@FXML
	private TableColumn<RecolectorObservable, String> cedulaColumna;
	/**
	 * hace referencia a la columna de los nombres de los clientes
	 */
	@FXML
	private TableColumn<RecolectorObservable, String> nombreColumna;
	/**
	 * etiqueta de cedula
	 */
	@FXML
	private Label txtCedula;
	/**
	 * etiqueta de nombre
	 */
	@FXML
	private Label txtNombre;
	/**
	 * etiqueta de apellido
	 */
	@FXML
	private Label txtApellido;
	/**
	 * etiqueta de email
	 */
	@FXML
	private Label txtEmail;
	/**
	 * etiqueta de clave
	 */
	@FXML
	private Label txtClave;

	/**
	 * Etiqueta de telefono
	 */
	@FXML
	private Label txtTelefono;

	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;

	

	/**
	 * Metodo constructor
	 */
	public RecolectorControlador() {

	}

	/**
	 * permite carga la informacion en las tables y escuchar las operaciones que se
	 * realizan con la tabla
	 */
	@FXML
	private void initialize() {

		cedulaColumna.setCellValueFactory(recolectorCelda -> recolectorCelda.getValue().getCedula());
		nombreColumna.setCellValueFactory(recolectorCelda -> recolectorCelda.getValue().getNombre());

		mostrarDetalleRecolector(null);

		tablaRecolector.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleRecolector(newValue));

	}

	@FXML
	private void inicio() {

		escenarioInicial.cargarEscenaInicio(escenarioInicial);
	}

	/**
	 * permite obtener una instancia del escenario general
	 * 
	 * @param escenarioInicial
	 */
	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
		this.escenarioInicial = escenarioInicial;
		tablaRecolector.setItems(escenarioInicial.getRecolectoresObservables());
	}

	/**
	 * permite mostrar la informacion del cliente seleccionado
	 * 
	 * @param cliente cliente al que se le desea mostrar el detalle
	 */
	public void mostrarDetalleRecolector(RecolectorObservable recolector) {

		if (recolector != null) {

			txtCedula.setText(recolector.getCedula().getValue());
			txtNombre.setText(recolector.getNombre().getValue());
			txtApellido.setText(recolector.getApellido().getValue());
			txtEmail.setText(recolector.getCorreo().getValue());
			txtClave.setText(recolector.getContrasenia().getValue());
			txtTelefono.setText(recolector.getTelefono().getValue());

		} else {
			txtCedula.setText("");
			txtNombre.setText("");
			txtApellido.setText("");
			txtEmail.setText("");
			txtClave.setText("");
			txtTelefono.setText("");

		}

	}

	/**
	 * permite mostrar la ventana de agregar recolector
	 */
	@FXML
	public void agregarRecolector() {
		escenarioInicial.cargarEscenarioCrearRecolector();
		tablaRecolector.refresh();
	}

	/**
	 * permite eliminar un recolector seleccionado
	 */
	@FXML
	public void elimiarRecolector() {

		Alert confirmarEliminar = Utilidades.mensajeConfirmar("Eliminar Recolector",
				"¿Realmente desea eliminar el Recolector?");

		Optional<ButtonType> result = confirmarEliminar.showAndWait();

		if (result.get() == ButtonType.OK) {
			int indice = tablaRecolector.getSelectionModel().getSelectedIndex();

			Recolector recolector = tablaRecolector.getItems().get(indice).getRecolector();

			if (escenarioInicial.eliminarRecolector(recolector)) {
				tablaRecolector.getItems().remove(indice);
				Utilidades.mostrarMensaje("Eliminar", "El cliente ha sido eliminado con exito");
			} else {
				Utilidades.mostrarMensaje("Error", "El cliente no pudo ser eliminado");
			}
		}

	}

	/**
	 * permite mostrar la ventana de editar cliente
	 */
	@FXML
	public void editarRecolector() {
		int indice = tablaRecolector.getSelectionModel().getSelectedIndex();
		Recolector recolector = tablaRecolector.getItems().get(indice).getRecolector();
		
		escenarioInicial.cargarEscenarioEditarRecolector(recolector);
		
		
		tablaRecolector.refresh();
	}

}
