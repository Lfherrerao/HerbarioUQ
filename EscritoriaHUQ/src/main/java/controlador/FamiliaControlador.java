package controlador;

import java.util.Optional;

import co.edu.analisis2.co.entidades.Familia;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.FamiliObservable;
import utilidades.Utilidades;

public class FamiliaControlador {

	/**
	 * table donde se almacena la informacion de las familias
	 */
	@FXML
	private TableView<FamiliObservable> tablaFamilia;
	/**
	 * hace referencia a la columna con el id de las familias
	 */
	@FXML
	private TableColumn<FamiliObservable, Integer> idColumna;
	/**
	 * hace referencia a la columna de los nombres de las familias
	 */
	@FXML
	private TableColumn<FamiliObservable, String> nombreColumna;
	/**
	 * etiqueta de id
	 */
	@FXML
	private Label txtId;
	/**
	 * etiqueta de nombre
	 */
	@FXML
	private Label txtNombre;

	@FXML
	private Label txtDescripcion;

	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;

	/**
	 * Metodo constructor
	 */
	public FamiliaControlador() {
	}

	@FXML
	private void inicio() {

		escenarioInicial.cargarEscenaInicio(escenarioInicial);
	}

	/**
	 * permite carga la informacion en las tables y ver las operaciones que se
	 * realizan con la tabla
	 */
	@FXML
	private void initialize() {

		nombreColumna.setCellValueFactory(familiaCelda -> familiaCelda.getValue().getNombre());
		idColumna.setCellValueFactory(familiaCelda -> familiaCelda.getValue().getId().asObject());
		mostrarDetalleFamilia(null);

		tablaFamilia.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleFamilia(newValue));

	}

	/**
	 * Permite mostrar la informacion del empleado seleccionado
	 * 
	 * @param
	 */
	public void mostrarDetalleFamilia(FamiliObservable familia) {

		if (familia != null) {

			txtId.setText(familia.getId().getValue().toString());
			txtNombre.setText(familia.getNombre().getValue());
			txtDescripcion.setText(familia.getDescripcion().getValue());

		} else {
			txtId.setText("");
			txtNombre.setText("");
			txtDescripcion.setText("");

		}

	}
	
	/**
	 * permite mostrar la ventana de agregar una familia
	 */
	@FXML
	public void agregarFamilia() {
		escenarioInicial.cargarEscenarioCrearFamilia();
		tablaFamilia.refresh();
	}

	/**
	 * permite eliminar un cliente seleccionado
	 */
	@FXML
	public void elimiarFamilia() {

		Alert confirmarEliminar = Utilidades.mensajeConfirmar("Eliminar familia",
				"¿Realmente desea eliminar la Familia?");

		Optional<ButtonType> result = confirmarEliminar.showAndWait();

		if (result.get() == ButtonType.OK) {
			
			int indice = tablaFamilia.getSelectionModel().getSelectedIndex();


			Familia familia = tablaFamilia.getItems().get(indice).getFamilia();

			if (escenarioInicial.eliminarFamilia(familia)) {
				tablaFamilia.getItems().remove(indice);
				Utilidades.mostrarMensaje("Eliminar", "La familia ha sido eliminada con éxito");
			} else {
				Utilidades.mostrarMensaje("Error", "la familia no pudo ser eliminada ");
			}

		}

	}
	
	/**
	 * permite mostrar la ventana de editar familia
	 */
	@FXML
	public void editarFamilia() {

		int indice = tablaFamilia.getSelectionModel().getSelectedIndex();

		Familia familia = tablaFamilia.getItems().get(indice).getFamilia();

		escenarioInicial.cargarEscenarioEditarFamilia(familia);
		tablaFamilia.refresh();
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
		tablaFamilia.setItems(escenarioInicial.getFamiliasObservables());
	}

}
