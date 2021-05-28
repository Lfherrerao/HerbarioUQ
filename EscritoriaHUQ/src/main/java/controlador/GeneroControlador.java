/**
 * 
 */
package controlador;

import java.util.Optional;

import co.edu.analisis2.co.entidades.Familia;
import co.edu.analisis2.co.entidades.Genero;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.GeneroObservable;
import utilidades.Utilidades;

/**
 * @author LENOVO
 *
 */
public class GeneroControlador {
	/**
	 * table donde se almacena la informacion de las familias
	 */
	@FXML
	private TableView<GeneroObservable> tablaGenero;
	/**
	 * hace referencia a la columna con el id de los generos
	 */
	@FXML
	private TableColumn<GeneroObservable, Integer> idColumna;
	/**
	 * hace referencia a la columna de los nombres de los Generos
	 */
	@FXML
	private TableColumn<GeneroObservable, String> nombreColumna;
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
	
	@FXML
	private Label txtFamilia;

	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;

	/**
	 * Metodo constructor
	 */
	public GeneroControlador() {
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

		nombreColumna.setCellValueFactory(generoCelda -> generoCelda.getValue().getNombre());
		idColumna.setCellValueFactory(generoCeldaId -> generoCeldaId.getValue().getId().asObject());
		mostrarDetalleGenero(null); 

		tablaGenero.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleGenero(newValue));

	}

	/**
	 * Permite mostrar la informacion del empleado seleccionado
	 * 
	 * @param
	 */
	public void mostrarDetalleGenero(GeneroObservable genero) {

		if (genero!= null) {

			txtId.setText(genero.getId().getValue().toString());
			txtNombre.setText(genero.getNombre().getValue());
			txtDescripcion.setText(genero.getDescripcion().getValue());
			

		} else {
			txtId.setText("");
			txtNombre.setText("");
			txtDescripcion.setText("");
			txtFamilia.setText("");

		}

	}
	
	/**
	 * permite mostrar la ventana de agregar un genero
	 */
	@FXML
	public void agregarGenero() {
		escenarioInicial.cargarEscenarioCrearGenero();
		tablaGenero.refresh();
	}

	/**
	 * permite eliminar un genero seleccionado
	 */
	@FXML
	public void elimiarGenero() {

		Alert confirmarEliminar = Utilidades.mensajeConfirmar("Eliminar genero",
				"¿Realmente desea eliminar el genero?");

		Optional<ButtonType> result = confirmarEliminar.showAndWait();

		if (result.get() == ButtonType.OK) {
			int indice = tablaGenero.getSelectionModel().getSelectedIndex();


			Genero genero = tablaGenero.getItems().get(indice).getGenero();

			if (escenarioInicial.eliminarGenero(genero)) {
				tablaGenero.getItems().remove(indice);
				Utilidades.mostrarMensaje("Eliminar", "el genero ha sido eliminada con éxito");
			} else {
				Utilidades.mostrarMensaje("Error", "El genero no pudo ser eliminada ");
			}

		}

	}
	
	/**
	 * permite mostrar la ventana de editar un genero
	 */
	@FXML
	public void editarGenero() {

		int indice = tablaGenero.getSelectionModel().getSelectedIndex();

		Genero genero = tablaGenero.getItems().get(indice).getGenero();
		escenarioInicial.cargarEscenarioEditarGenero(genero);
		tablaGenero.refresh();
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
		tablaGenero.setItems(escenarioInicial.getGenerosObservables());
	}

}
