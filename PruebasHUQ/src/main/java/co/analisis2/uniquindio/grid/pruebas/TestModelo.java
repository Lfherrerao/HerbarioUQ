package co.analisis2.uniquindio.grid.pruebas;

import java.util.Date;

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
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.analisis2.co.entidades.Administrador;
import co.edu.analisis2.co.entidades.Asesoria;
import co.edu.analisis2.co.entidades.Empleado;
import co.edu.analisis2.co.entidades.Envio;
import co.edu.analisis2.co.entidades.Familia;
import co.edu.analisis2.co.entidades.Genero;
import co.edu.analisis2.co.entidades.Persona;
import co.edu.analisis2.co.entidades.Planta;
import co.edu.analisis2.co.entidades.Recolector;
import co.edu.analisis2.co.enumeraciones.Estado;

/*
 * Clase de pruebas dedicada para la pruebas de las entidades
 * 
 * @author Leo
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class TestModelo {
 
	/**
	 * instancia para realizar las transaciones con las entidades
	 */
	@PersistenceContext
	private EntityManager entityManager;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	// ------------- PRUEBAS PARA GESTIONAR ADMINISTRADOR-----------------
	/**
	 * metodo de prueba para crear un administrador.
	 */
	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void agregarAdministradorTest() {

		Administrador administrador = new Administrador();

		administrador.setNombre("leo");
		administrador.setApellido("herrera");
		administrador.setCedula("1094940674");
		administrador.setTelefono("3135608327");
		administrador.setCorreo("leo_9494@hotmail.com");
		administrador.setContrasenia("leon99");

		entityManager.persist(administrador);

		Administrador adm2 = entityManager.find(Administrador.class, administrador.getCedula());
		Assert.assertNotNull(adm2);

	}

	/**
	 * metodo de prueba para buscar un administrador.
	 */
	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void buscarAdministradorTest() {

		Administrador administrador = entityManager.find(Administrador.class, "123456789");

		Assert.assertNotNull(administrador);
	}

	/**
	 * Metodo que se encarga de generar la prueba para actualizar los datos de un
	 * administrador.
	 */
	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void actualizarAdministradorTest() {

		Administrador admiNuevo = entityManager.find(Administrador.class, "123456789");

		admiNuevo.setNombre("leo");
		admiNuevo.setApellido("sanchez");

		entityManager.merge(admiNuevo);

		Assert.assertEquals("leo", admiNuevo.getNombre());

	}

	/**
	 * Metodo que se encarga de realiza la prueba de eliminar un cliente
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void eliminarAdministradorTest() {

		Administrador administrador = entityManager.find(Administrador.class, "123456789");
		entityManager.remove(administrador);

		Assert.assertNull("123456789", entityManager.find(Administrador.class, "123456789"));

	}

	// --------------------------------------------------------------------------------------------------------------------------------------
	// ------------- PRUEBAS PARA GESTIONAR EMPLEADO-----------------
	/**
	 * METODO encargado de hacer pruebas de agregar empleado.
	 */
	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void agregarEmpleadoTest() {

		Empleado empleado = new Empleado();

		empleado.setNombre("Maria Martinez");
		empleado.setApellido("keil");
		empleado.setCedula("223456789");
		empleado.setContrasenia("12345");
		empleado.setCorreo("mmartinez@mail.com");
		empleado.setTelefono("12");

		entityManager.persist(empleado);
		
		Empleado empleado2 = entityManager.find(Empleado.class, empleado.getCedula());
		Assert.assertNotNull(empleado2);

	}

	/**
	 * metodo de prueba para buscar un empleado.
	 */

	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void buscarEmmpleadoTest() {

		Empleado empleado = entityManager.find(Empleado.class, "223456789");

		Assert.assertNotNull(empleado);
	}

	/**
	 * Metodo que se encarga de generar la prueba para actualizar los datos de un
	 * empleado.
	 */
	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void actualizarEmpleadoTest() {

		Empleado empleado = entityManager.find(Empleado.class, "223456789");

		
		empleado.setNombre("herrera");

		entityManager.merge(empleado);

		Assert.assertEquals("herrera", empleado.getNombre());

	}

	/**
	 * Metodo que se encarga de realiza la prueba de eliminar un empleado.
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void eliminarEmpleadoTest() {

		Empleado empleado = entityManager.find(Empleado.class, "223456789");
		entityManager.remove(empleado);

		Assert.assertNull("223456789", entityManager.find(Administrador.class, "223456789"));
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------
	// ------------- PRUEBAS PARA GESTIONAR RECOLECTOR-----------------

	/**
	 * METODO encargado de hacer pruebas de agregar recolector.
	 */
	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void agregarRecolectorTest() {

		Recolector recolector = new Recolector();

		recolector.setNombre("tomas");
		recolector.setApellido("la");
		recolector.setCedula("111");
		recolector.setContrasenia("12345");
		recolector.setCorreo("mmart@mail.com");
		recolector.setTelefono("12");

		entityManager.persist(recolector);

		Recolector rec2 = entityManager.find(Recolector.class, recolector.getCedula());
		Assert.assertNotNull(rec2);

	}

	/**
	 * metodo encargado de las pruebas de buscar un recolector.
	 */

	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void buscarRecolectorTest() {

		Recolector recolector = entityManager.find(Recolector.class, "323456789");
		Assert.assertNotNull(recolector);

	}

	/**
	 * Metodo que se encarga de generar la prueba para actualizar los datos de un
	 * recolector.
	 */
	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void actualizarRecolectorTest() {

		Recolector recolector = entityManager.find(Recolector.class, "323456789");

		recolector.setNombre("eso");

		entityManager.merge(recolector);

		Assert.assertEquals(recolector.getNombre(), "eso");

	}

	/**
	 * Metodo que se encarga de realiza la prueba de eliminar un recolector.
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void eliminarRecolectorTest() {

		Recolector recolector = entityManager.find(Recolector.class, "323456789");

		entityManager.remove(recolector);

		Assert.assertNull("323456789", entityManager.find(Recolector.class, "323456789"));
	}

	// ----------------------------------------------------------------------------------------------------------------------------
	// ------------- PRUEBAS PARA GESTIONAR ASESORIA(NO TRIVIAL)------------------

	/**
	 * Metodo que se encarga de generar la prueba para crear una consulta
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "asesoria.json" })
	public void crearAsesoriaTest() {

		Asesoria crearAsesoria = new Asesoria();
		crearAsesoria.setRadicado(2);
		crearAsesoria.setPregunta("Compra de cartera");
		crearAsesoria.setRespuesta("RESPUESTA");
		crearAsesoria.setEmisor(entityManager.find(Recolector.class, "323456789"));
		crearAsesoria.setReceptor(entityManager.find(Empleado.class, "223456789"));

		entityManager.persist(crearAsesoria);

		Assert.assertNotNull(crearAsesoria);

	}

	/**
	 * Metodo que se encarga de generar la prueba para buscar una ASESORIA.
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "asesoria.json" })
	public void buscarAsesoriaTest() {

		Asesoria asesoriaBusca = entityManager.find(Asesoria.class, 1);
		Assert.assertEquals(1, asesoriaBusca.getRadicado());

	}

	/**
	 * Metodo que se encarga de generar la prueba para actualizar una consulta
	 * 
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "asesoria.json" })
	public void actualizarAsesoriaTest() {

		Asesoria actuaAsesoria = entityManager.find(Asesoria.class, 1);
		actuaAsesoria.setPregunta("cancelar prestamo");

		entityManager.merge(actuaAsesoria);
		Assert.assertEquals("cancelar prestamo", actuaAsesoria.getPregunta());
	}

	/**
	 * Metodo que se encarga de generar la prueba de eliminar un asesoria
	 * 
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "asesoria.json" })
	public void eliminarAsesoriaTest() {

		entityManager.remove(entityManager.find(Asesoria.class, 1));
		Assert.assertNull(entityManager.find(Administrador.class, "1"));

	}

	// ----------------------------------------------------------------------------------------------------------------------------
	// ------------- PRUEBAS PARA GESTIONAR GENEROS------------------

	/**
	 * Metodo que se encarga de generar la prueba para crear un genero
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "genero.json" ,"familia.json"})
	public void crearGeneroTest() {

		Genero genero = new Genero();
		genero.setId(2);
		genero.setDescripcion("huele mal");
		genero.setFamilia(entityManager.find(Familia.class, 1));
		genero.setNombre("blablabla");

		entityManager.persist(genero);

		Assert.assertNotNull(genero);

	}

	/**
	 * Metodo que se encarga de generar la prueba para buscar un genero
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "genero.json" })
	public void buscarGeneroTest() {

		Genero genero = entityManager.find(Genero.class, 1);
		Assert.assertEquals(1, genero.getId());

	}

	/**
	 * Metodo que se encarga de generar la prueba para actualizar un genero
	 * 
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "genero.json" })
	public void actualizarGeneroTest() {

		Genero genero = entityManager.find(Genero.class, 1);
		genero.setDescripcion("es gruesa");

		entityManager.merge(genero);
		Assert.assertEquals("es gruesa", genero.getDescripcion());
	}

	/**
	 * Metodo que se encarga de generar la prueba de eliminar un genero
	 * 
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "genero.json" })
	public void eliminarGeneroTest() {

		entityManager.remove(entityManager.find(Genero.class, 1));
		Assert.assertNull(entityManager.find(Genero.class, 1));

	}

	// ----------------------------------------------------------------------------------------------------------------------------
	// ------------- PRUEBAS PARA GESTIONAR FAMILIAS------------------
	/**
	 * Metodo que se encarga de generar la prueba para crear una familia
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familia.json" })
	public void crearFamiliaTest() {

		Familia familia = new Familia();
		familia.setId(2);
		familia.setDescripcion("huele mal");
		entityManager.persist(familia);

		Assert.assertNotNull(familia);

	}

	/**
	 * Metodo que se encarga de generar la prueba para buscar una familia
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familia.json" })
	public void buscarFamiliaTest() {

		Familia familia = entityManager.find(Familia.class, 1);
		Assert.assertEquals(1, familia.getId());

	}

	/**
	 * Metodo que se encarga de generar la prueba para actualizar una familia
	 * 
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familia.json" })
	public void actualizarFamiliaTest() {

		Familia familia = entityManager.find(Familia.class, 1);
		familia.setDescripcion("fea");

		entityManager.merge(familia);
		Assert.assertEquals("fea", familia.getDescripcion());
	}

	/**
	 * Metodo que se encarga de generar la prueba de eliminar una familia
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familia.json" })
	public void eliminarFamiliaTest() {

		entityManager.remove(entityManager.find(Familia.class, 1));
		Assert.assertNull(entityManager.find(Familia.class, 1));

	}
// -----------------------------------------------------------------------------------------------
// ------------- PRUEBAS PARA GESTIONAR PLANTAS------------------

	/**
	 * Metodo que se encarga de generar la prueba para crear una planta.
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "planta.json", "familia.json", "genero.json", "persona.json","envio.json" })
	public void crearPlantaTest() {

		Planta planta = new Planta();

		planta.setColor("rojo");
		planta.setEspecie("especie");
		planta.setGenero(entityManager.find(Genero.class, 1));
		planta.setId(2);
		planta.setNombre("romelia");
	
		planta.setEnvio(entityManager.find(Envio.class, 1));

		entityManager.persist(planta);

		Assert.assertNotNull(planta);

	}

	/**
	 * Metodo que se encarga de generar la prueba para buscar una planta
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "planta.json", "familia.json", "genero.json", "persona.json","envio.json" })
	public void buscarPlantaTest() {

		Planta planta = entityManager.find(Planta.class, 2);
		Assert.assertEquals(2, planta.getId());

	}

	/*******
	 * Metodo que se encarga de generar la prueba para actualizar una planta
	 * 
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "planta.json", "familia.json", "genero.json", "persona.json","envio.json" })
	public void actualizarPlantaTest() {

		Planta planta = entityManager.find(Planta.class, 2);
		planta.setColor("amarillo");

		entityManager.merge(planta);
		Assert.assertEquals("amarillo", planta.getColor());
	}

	/**
	 * Metodo que se encarga de generar la prueba de eliminar una planta
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "planta.json", "familia.json", "genero.json", "persona.json","envio.json" })
	public void eliminarPlantaTest() {

		entityManager.remove(entityManager.find(Planta.class, 2));
		Assert.assertNull(entityManager.find(Planta.class, 2));

	}

	// -----------------------------------------------------------------------------------------------
	// ------------- PRUEBAS PARA GESTIONAR UN ENVIO------------------
	/**
	 * Metodo que se encarga de generar la prueba para crear un envio
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)

	public void crearEnvioTest() {

		Envio envio = new Envio();

		envio.setEstado(Estado.ACEPTADO);
		envio.setFechaEnvio(new Date());
		envio.setJustificacion("el envio se encuentra en estudio");
		envio.setPersona(entityManager.find(Recolector.class, "323456789"));
		envio.setPlanta(entityManager.find(Planta.class, 2));
		envio.setRadicado(2);

		entityManager.persist(envio);

		Assert.assertNotNull(envio);

	}

	/**
	 * Metodo que se encarga de generar la prueba para buscar un ENVIO
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "envio.json" })
	public void buscarEnvioTest() {

		Envio envio = entityManager.find(Envio.class, 1);
		Assert.assertEquals(1, envio.getRadicado());

	}

	/**
	 * Metodo que se encarga de generar la prueba para actualizar un envio
	 * 
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "envio.json","planta.json" })
	public void actualizarEnvioTest() {

		Envio envio = entityManager.find(Envio.class, 1);
		envio.setEstado(Estado.ACEPTADO);

		entityManager.merge(envio);
		Assert.assertEquals(Estado.ACEPTADO, envio.getEstado());
	}

	/**
	 * Metodo que se encarga de generar la prueba de eliminar un envio
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "envio.json","planta.json" })
	public void eliminarEnvioTest() {

		entityManager.remove(entityManager.find(Envio.class,1));
		Assert.assertNull(entityManager.find(Envio.class, 1));

	}
}