package co.analisis2.uniquindio.grid.pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

import co.analisis2.uniquindio.grid.DTO.RegistrosPorEmpleadoOAdministrador;
import co.analisis2.uniquindio.grid.DTO.TurnosPorFechaDTO;
import co.edu.analisis2.co.entidades.Envio;
import co.edu.analisis2.co.entidades.Familia;
import co.edu.analisis2.co.entidades.Genero;
import co.edu.analisis2.co.entidades.Persona;
import co.edu.analisis2.co.entidades.Planta;
import co.edu.analisis2.co.entidades.Recolector;
import co.edu.analisis2.co.enumeraciones.Estado;

@RunWith(Arquillian.class)
public class TestJPQLseguimiento {

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

	public void test() {
	}

	// ------------PUNTO 4 GUIA 9-----------------------------------------

	/**
	 * con este metodo se listan todas las plantas o especies vegetales;
	 */
	@Test
	@UsingDataSet({ "planta.json", "genero.json", "familia.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void familiaPorIdPlanta() {

		TypedQuery<Familia> query = entityManager.createNamedQuery(Planta.FAMILIA_DADO_UNA_PLANTA, Familia.class);

		query.setParameter("idPlanta", 2);

		Familia familia = query.getSingleResult();
		Assert.assertEquals("arganes", familia.getNombre());

	}

//------------PUNTO 5 GUIA 9------------------------------------------------------------

	/**
	 * con este metodo se listan todas las plantas o especies vegetales;
	 */
	@Test
	@UsingDataSet({ "planta.json", "genero.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarPlantasPorGenero() {

		TypedQuery<Planta> query = entityManager.createNamedQuery(Genero.LISTAR_PLANTAS_DADO_UN_GENERO, Planta.class);

		query.setParameter("idGenero", 1);

		List<Planta> plantas = query.getResultList();
		Assert.assertEquals(3, plantas.size());

	}

	// ------------PUNTO 6 GUIA 9------------------------------------------

	/**
	 * con este metodo se listan todos los envios daado la cedula de un recolector
	 */
	@Test
	@UsingDataSet({ "envio.json", "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarEnviosPorCedula() {

		TypedQuery<Envio> query = entityManager.createNamedQuery(Recolector.LISTAR_ENVIOS_POR_CEDULA_DE_RECOLECTOR,
				Envio.class);

		query.setParameter("cedula", "323456789");

		List<Envio> envios = query.getResultList();
		Assert.assertEquals(3, envios.size());

	}

// ------------PUNTO 7 GUIA 9------------------------------------------

	/**
	 * con este metodo se listan todas las personas con o sin registro
	 */
	@Test
	@UsingDataSet({ "envio.json", "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarPersoansConEnvios() {

		TypedQuery<Object> query = entityManager.createNamedQuery(Recolector.CEDULAS_PERSONA_Y_SUS_ENVIOS,
				Object.class);

		List<Object> personas = query.getResultList();

		Object object[] = (Object[]) personas.get(personas.size() - 1);

		System.out.println(object + "/ln");

		Assert.assertEquals("323456789", object[0]);
		Assert.assertEquals(3, ((Envio) object[1]).getRadicado());
	}
// ------------PUNTO 8 GUIA 9------------------------------------------

	/**
	 * con este metodo obtenemos los recolectores que han hecho envio sin repetirlos
	 */
	@Test
	@UsingDataSet({ "envio.json", "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarClientesConEnvioSiRepetir() {

		TypedQuery<Recolector> query = entityManager.createNamedQuery(Envio.RECOLECTORES_QUE_HAN_HECHO_ENVIO,
				Recolector.class);

		List<Recolector> personas = query.getResultList();

		Assert.assertEquals(1, personas.size());

	}

	// ---------------------------guia 9 punto 9--------------------

	/**
	 * Metodo que se encarga de generarla prueba para listar diferentes tipos de
	 * datos de un envio
	 * 
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "envio.json", "persona.json", "familia.json", "genero.json", "planta.json" })
	public void listarPorFecha() throws java.text.ParseException {

		Date fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse("2019-09-19 00:00:00.0");
		TypedQuery<Object> query = entityManager.createNamedQuery(Planta.LISTAR_POR_FECHA, Object.class);
		query.setParameter("fecha", fecha);
		List<Object> prestamo = query.getResultList();

		Object object[];
		object = (Object[]) prestamo.get(prestamo.size() - 1);

		Assert.assertEquals(3, object[0]);
		Assert.assertEquals("plante", object[1]);
		Assert.assertEquals("arganes", object[2]);
		Assert.assertEquals("323456789", object[3]);
		Assert.assertEquals("hhernandez@mail.com", object[4]);
	}

// ---------------------------guia 9 punto 10--------------------
	/**
	 * con este metodo obtenemos los recolectores que han hecho envio sin repetirlos
	 */
	@Test
	@UsingDataSet({ "envio.json", "persona.json", "familia.json", "genero.json", "planta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarPorFechaDTO() {

		try {
			Date fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse("2019-09-19 00:00:00.0");

			TypedQuery<TurnosPorFechaDTO> query = entityManager.createNamedQuery(Planta.LISTAR_POR_FECHA_DTO,
					TurnosPorFechaDTO.class);
			query.setParameter("fecha", fecha);
			List<TurnosPorFechaDTO> turnosPorFecha = query.getResultList();

			Assert.assertEquals(2, turnosPorFecha.size());
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}

	// ---------------------------guia 10 punto 1--------------------
	/**
	 * Metodo que se encarga de contar el numero de familias que se han registrado a
	 * traves de la anotacion COUNT
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familia.json" })
	public void contarFamilias() {

		TypedQuery<Integer> query = entityManager.createNamedQuery(Familia.COUNT_FAMILIA, Integer.class);
		Object cont = query.getSingleResult();
		long dato = 1;

		Assert.assertEquals(dato, cont);

	}
	// ---------------------------------guia 10 punto 2-----------------------

	/**
	 * este metodo cuenta el numero de personas con envios aceptados por dia a
	 * traves de la anotacion GROUP BY Y COUNT
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "envio.json" })
	public void groupByEnvio() {

		try {
			Date fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse("2019-09-19 00:00:00.0");

			TypedQuery<Long> query = entityManager.createNamedQuery(Envio.AGROUP_BY_ENVIOS_ACEPTADOS_POR_DIA,
					Long.class);

			query.setParameter("estado", Estado.ACEPTADO);
			query.setParameter("fecha", fecha);

			List<Long> cont = query.getResultList();
			Long dato = (long) 1;
			Assert.assertEquals(dato, cont.get(cont.size() - 1));
		} catch (ParseException e) {

			e.printStackTrace();
		}

	}
	// ----------------------------guia 10 punto 4---------------

	/**
	 * consulta que determina cuantos registros ha hecho un empleado y regresa un
	 * DTO con el numero de registros y la cedula del empleado
	 */
	@Test
	@UsingDataSet({ "envio.json", "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void RegistrosPorEmpleado() {

		TypedQuery<RegistrosPorEmpleadoOAdministrador> query = entityManager
				.createNamedQuery(Envio.NUMERO_ENVIOS_EMPLEADO, RegistrosPorEmpleadoOAdministrador.class);

		query.setParameter("cedula", "323456789");

		RegistrosPorEmpleadoOAdministrador registrosPorEmpleado = query.getSingleResult();
		long comparar = 3;
		Assert.assertEquals(comparar, registrosPorEmpleado.getCantidadRegistros());

	}

//------------------guia 10 punto 5----------------------

	/**
	 * consulta que permita determinar cual es la familia que más especies tiene
	 * registradas con max y otra consulta.
	 * 
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "planta.json", "genero.json", "familia.json" })
	public void obtenerFamiliaConMasEspecies() {
		String nombrePlanta = "null";
		try {
			Query query = entityManager.createNamedQuery(Planta.NOMBRE_FAMILIA_CON_MAS_ESPECIES_MAX);

			nombrePlanta = (String) query.getSingleResult();
			Assert.assertEquals("No corresponde al nombre de la familia", nombrePlanta, "arganes");
			System.out.println(nombrePlanta);

			// Consultar la familia donde sea la que mas plantas tiene.

		} catch (NoResultException e) {
			Assert.fail(String.format("Error encontrando la familiacom mas plantas  %s", e.getMessage()));
		}

		TypedQuery<Familia> queryFamilia = entityManager.createNamedQuery(Familia.OBTENER_FAMILIA_CON_MAS_PLANTAS,
				Familia.class);
		queryFamilia.setParameter("nombreFamilia", nombrePlanta);
		List<Familia> familia = queryFamilia.getResultList();

		Assert.assertEquals("Error:", 1, familia.size());

	}

	// ------------------guia 10 punto 6----------------------
	/**
	 ** consulta que permita determinar cual es la familia que más especies tiene
	 * registradas solo con max.
	 */

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "planta.json", "genero.json", "familia.json" })
	public void obtenerFamiliaConMasEspeciesSoloMax() {

		TypedQuery<Familia> queryPrestamo = entityManager.createNamedQuery(Planta.OBTENER_FAMILIA_MAXIMAS_PLANTAS,
				Familia.class);

		List<Familia> listaFamilia = queryPrestamo.getResultList();

		Assert.assertEquals("Error: Numero de prestamos con valor maximo", 3, listaFamilia.size());

		/*
		 * for (Prestamo valorP : listaPrestamos) {
		 * System.out.println(String.format("Id:%s Valor prestamo:%.0f", valorP.getId(),
		 * valorP.getValorPrestamo())); }
		 */
	}
}

/*
 * La mejor manera de hacer la consulta de el prestamos o prestamos con el monto
 * maximo es con el metodo anterior(obtenerTodosPrestamosMaximosTest) ya que
 * alli en una sola consulta se obtienen los resultados, lo que genera que se
 * consuman menos recursos y memoria en el sistema. Mientras que en el metodo
 * (obtenerMaximoPrestamoTest) se necesita primero consultar el valor maximo y
 * despues enviarlo como parametro a otra consulta que devuelve el listado, en
 * el metodo anterior el listado se obtiene en una sola consulta.
 */
