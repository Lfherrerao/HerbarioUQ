/**
 * 
 */
package co.analisis2.uniquindio.grid.pruebas;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.analisis2.co.entidades.Empleado;
import co.edu.analisis2.co.entidades.Envio;
import co.edu.analisis2.co.entidades.Persona;
import co.edu.analisis2.co.entidades.Planta;
import co.edu.analisis2.grid.ejb.AdminEJB;
import co.edu.analisis2.grid.excepciones.ElementoRepetidoException;
import co.edu.analisis2.grid.excepciones.ExcepcionesHerbario;


/**
 * @author LENOVO
 *
 */
@RunWith(Arquillian.class)
public class TestAdministradorEJB {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private AdminEJB adminEjb;

	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(AdminEJB.class)

				.addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	/**
	 * con este metodo se listan todas las plantas aceptadas;
	 */
	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarEmpleadoTestEJB() {

		Empleado empleado = new Empleado();
		List<Envio> envios = new ArrayList<Envio>();

		empleado.setNombre("leo");
		empleado.setApellido("herrera");
		empleado.setCedula("223456");
		empleado.setTelefono("3135608327");
		empleado.setCorreo("leo_9494@hotmail.com");
		empleado.setContrasenia("leon99");
		empleado.setEnvio(envios);
		try {
			Assert.assertNotNull(adminEjb.insertarEmpleado(empleado));
		} catch (ElementoRepetidoException e) {
			Assert.fail(e.getMessage());

		} catch (Exception e) {
			Assert.fail("error inesperado");
		}

	}

	/**
	 * Permite probar el metodo eliminar empleado de herbario.
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void eliminarEmpleadoTestEJB() {

		try {
			adminEjb.eliminarEmpleado("223456789");
		} catch (ExcepcionesHerbario e1) {
			Assert.fail(String.format("Error al borrar empleado: %s", e1.getMessage()));
		}

		Empleado empleado = entityManager.find(Empleado.class, "223456789");

		Assert.assertNull("Empleado es diferente de null", empleado);

	}
/**
 * esta prueba permite modificar un empleado de un herbarioEJB
 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void modificarEmpleadoTestEJB() {

		Empleado empleadoModificar = entityManager.find(Empleado.class, "223456789");
		if (empleadoModificar != null) {
			empleadoModificar.setCedula("1094940674");
		}
		try {
			adminEjb.modificarEmpleado(empleadoModificar);

		} catch (ExcepcionesHerbario e1) {
			Assert.fail(String.format("Error al modificarEmpleado: %s", e1.getMessage()));
		}

		Empleado empleadoModificado = entityManager.find(Empleado.class, "1094940674");

		Assert.assertNotEquals(empleadoModificado, empleadoModificar);

	}
	
	/**
	 * Permite probar el metodo listar empleados de herbarioEJB
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void listarEmpleadosTestEJB() {
		List<Empleado> lista = adminEjb.listarEmpleados();

		Assert.assertEquals("Error: La lista no tiene los empleados esperados ", 1, lista.size());
	}
	/**
	 * Permite probar el buscar empleado de HerbarioEJB
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void buscarEmpleadoTestEJB() {

		Empleado empleado=new Empleado();
		try {
		 empleado =adminEjb.buscarEmpleado("223456789");
		} catch (Exception e) {
			Assert.fail(String.format("Error al buscar empleado: %s", e.getMessage()));
		}
		Assert.assertNotNull(empleado);

	}

	/**
	 * Permite probar el metodo listar plantas por genero  herbarioEJB
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "planta.json", "genero.json" })
	public void listarPlantasPorGeneroTestEJB() {
		List<Planta> lista = null;
		try {
			lista =adminEjb.listarPlantaPorGenero(1);
		} catch (ExcepcionesHerbario e) {
			Assert.fail(String.format("Error al listar plantas por genero: %s", e.getMessage()));
		}

		Assert.assertEquals("Error: La lista no tiene la cantidad de  plantas esperados ", 3, lista.size());
	}
}