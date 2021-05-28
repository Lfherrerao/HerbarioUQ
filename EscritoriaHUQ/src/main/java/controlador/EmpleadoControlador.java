/**
 * 
 */
package controlador;

import java.util.Optional;

import co.edu.analisis2.co.entidades.Empleado;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.EmpleadoObservable;
import utilidades.Utilidades;

/**
 * Controlador de la interfaz detalle empleado
 * 
 * @author
 * @version 1.0
 */
public class EmpleadoControlador {

	/**
	 * table donde se almacena la informacion de los clientes
	 */
	@FXML
	private TableView<EmpleadoObservable> tablaEmpleados;
	/**
	 * hace referencia a la columna con las cedulas de los empleados
	 */
	@FXML
	private TableColumn<EmpleadoObservable, String> cedulaColumna;
	/**
	 * hace referencia a la columna de los nombres de los empleados
	 */
	@FXML
	private TableColumn<EmpleadoObservable, String> nombreColumna;
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
	 * etiqueta de fecha
	 */

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
	 * Empleado Observable
	 */
	// private EmpleadoObservable empleadoObservable;

	/**
	 * Metodo constructor
	 */
	public EmpleadoControlador() {
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

		cedulaColumna.setCellValueFactory(empleadoCelda -> empleadoCelda.getValue().getCedula());
		nombreColumna.setCellValueFactory(empleadoCelda -> empleadoCelda.getValue().getNombre());

		mostrarDetalleEmpleado(null);
 
		tablaEmpleados.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleEmpleado(newValue));

	}

	/**
	 * permite obtener una instancia del escenario general
	 * 
	 * @param escenarioInicial
	 */
	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
		this.escenarioInicial = escenarioInicial;
		tablaEmpleados.setItems(escenarioInicial.getEmpleadosObservables());
	}

	/**
	 * Permite mostrar la informacion del empleado seleccionado
	 * 
	 * @param
	 */
	public void mostrarDetalleEmpleado(EmpleadoObservable empleado) {

		if (empleado != null) {

			txtCedula.setText(empleado.getCedula().getValue());
			txtNombre.setText(empleado.getNombre().getValue());
			txtApellido.setText(empleado.getApellido().getValue());
			txtEmail.setText(empleado.getCorreo().getValue());
			txtClave.setText(empleado.getContrasenia().getValue());
			txtTelefono.setText(empleado.getTelefono().getValue());

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
	 * permite mostrar la ventana de agregar empleado
	 */
	@FXML
	public void agregarEmpleado() {
		escenarioInicial.cargarEscenarioCrearEmpleado();
		tablaEmpleados.refresh();
	}

	/**
	 * permite eliminar un cliente seleccionado
	 */
	@FXML
	public void elimiarEmpleado() {

		Alert confirmarEliminar = Utilidades.mensajeConfirmar("Eliminar Empleado",
				"¿Realmente desea eliminar el empleado?");

		Optional<ButtonType> result = confirmarEliminar.showAndWait();

		if (result.get() == ButtonType.OK) {
			int indice = tablaEmpleados.getSelectionModel().getSelectedIndex();

			// System.out.println(tablaEmpleados.getItems().size());

			Empleado empleado = tablaEmpleados.getItems().get(indice).getEmpleado();

			if (escenarioInicial.eliminarEmpleado(empleado)) {
				tablaEmpleados.getItems().remove(indice);
				Utilidades.mostrarMensaje("Eliminar", "El cliente ha sido eliminado con éxito");
			} else {
				Utilidades.mostrarMensaje("Error", "El cliente no pudo ser eliminado");
			}

		}

	}

	/**
	 * permite mostrar la ventana de editar cliente
	 */
	@FXML
	public void editarEmpleado() {

		int indice = tablaEmpleados.getSelectionModel().getSelectedIndex();

		Empleado empleado = tablaEmpleados.getItems().get(indice).getEmpleado();

		escenarioInicial.cargarEscenarioEditarEmpleado(empleado);
		tablaEmpleados.refresh();
	}

}
