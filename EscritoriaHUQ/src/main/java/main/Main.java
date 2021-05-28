package main;


import controlador.ManejadorEscenarios;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Clase encargada de iniciar la ejecucion de la aplicacion y cargar la ventana principal
 * @author 
 * 
 * @version 1.0
 */
public class Main extends Application {

	/**
	 * Se lanza la aplicaicion
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Se carga y edita el escenario inicial de la app
	 * 1094940674
	 * @param primaryStage escenario de a la aplicacion
	 * @throws Exception en caso de no poder cargar el escenario
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		new ManejadorEscenarios(primaryStage);
	}
}
