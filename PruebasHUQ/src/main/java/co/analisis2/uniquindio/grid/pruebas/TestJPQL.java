package co.analisis2.uniquindio.grid.pruebas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
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

/**
 * clase encargada de hacer las pruebas de los listamientos de JPQL O Queris.
 * 
 * @author LENOVO
 *
 */

@RunWith(Arquillian.class)
public class TestJPQL {

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

//---------------------------QUERYS PARA PLANTA--------------------------------------------------

	@Test
	/**
	 * con este metodo se listan todas las plantas o especies vegetales;
	 */

	@UsingDataSet({ "planta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarTodasLasPlantas() {

		TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_TODAS_LAS_PLANTAS, Planta.class);
		List<Planta> plantas = query.getResultList();
		Assert.assertEquals(3, plantas.size());

	}

	/**
	 * con este metodo se listan todas las plantas aceptadas;
	 */
	@Test
	@UsingDataSet({ "planta.json", "envio.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarTodasLasPlantasAceptadas() {

		try {

			TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_ESTADO, Planta.class);
			query.setParameter("estado", Estado.ACEPTADO);

			List<Planta> plantas = query.getResultList();
			Assert.assertEquals(1, plantas.size());
		} catch (NoResultException e) {
			Assert.fail("no se encuentra ninguna planta aceptada");
		}

	}

	/**
	 * con este metodo se listan todas las plantas rechazadas;
	 */
	@Test
	@UsingDataSet({ "planta.json", "envio.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarTodasLasPlantasRechazadas() {

		try {

			TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_ESTADO, Planta.class);
			query.setParameter("estado", Estado.RECHAZADO);

			List<Planta> plantas = query.getResultList();
			Assert.assertEquals(1, plantas.size());
		} catch (NoResultException e) {
			Assert.fail("no se encuentra ninguna planta RECHAZADA");
		}

	}

	/**
	 * con este metodo se listan todas las plantas familia;
	 */
	@Test
	@UsingDataSet({ "planta.json", "familia.json", "genero.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarPlantasPorFamilia() {

		try {

			TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_FAMILIA, Planta.class);

			query.setParameter("idGenero", 1);
			query.setParameter("idFamilia", 1);

			List<Planta> plantasPorFamilia = query.getResultList();
			Assert.assertEquals(3, plantasPorFamilia.size());

		} catch (NoResultException e) {
			Assert.fail("no se encuentra ninguna planta por familia");
		}

	}

	/**
	 * con este metodo se listan todas las plantas por genero;
	 */
	@Test
	@UsingDataSet({ "planta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarPlantasPorGenero() {

		try {

			TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_GENERO, Planta.class);

			query.setParameter("idGenero", 1);

			List<Planta> plantasPorFamilia = query.getResultList();
			Assert.assertEquals(3, plantasPorFamilia.size());

		} catch (NoResultException e) {
			Assert.fail("no se encuentra ninguna planta por genero");
		}

	}

	/**
	 * con este metodo se puede ver la informacion especifica de una planta
	 */
	@Test
	@UsingDataSet({ "planta.json", "familia.json", "genero.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void mostrarInformacionDetalladaDePlanta() {

		try {

			TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.INFORMACION_DETALLADA_POR_ID,
					Planta.class);

			query.setParameter("id", 2);

			List<Planta> plantasPorFamilia = query.getResultList();
			Assert.assertEquals(1, plantasPorFamilia.size());

		} catch (NoResultException e) {
			Assert.fail("el id no pertenece a ninguna planta registrada");
		}

	}

	/**
	 * con este metodo se puede ver las plantas enviadas por un recolector y han
	 * sido aceptadas.
	 */
	@Test
	@UsingDataSet({ "planta.json", "envio.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void mostrarPlantasAceptadasRecolector() {

		try {

			TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.ESTADO_DE_PLANTAS_ENVIADAS_POR_RECOLECTOR,
					Planta.class);
			
			query.setParameter("estado", Estado.ACEPTADO);
			query.setParameter("cedula", "323456789");

			List<Planta> plantasAceptadasDeUnRecolector = query.getResultList();
			Assert.assertEquals(1, plantasAceptadasDeUnRecolector.size());

		} catch (NoResultException e) {
			Assert.fail("el recolector no tiene plantas aceptadas");
		}

	}

	/**
	 * con este metodo se puede ver las plantas enviadas por un recolector y han
	 * sido rechazadas.
	 */
	@Test
	@UsingDataSet({ "planta.json", "envio.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void mostrarPlantasRechazadasRecolector() {

		try {

			TypedQuery<Planta> query = entityManager
					.createNamedQuery(Planta.ESTADO_DE_PLANTAS_ENVIADAS_POR_RECOLECTOR, Planta.class);

			
			query.setParameter("estado", Estado.RECHAZADO);
			query.setParameter("cedula", "323456789");

			List<Planta> plantasAceptadasDeUnRecolector = query.getResultList();
			Assert.assertEquals(1, plantasAceptadasDeUnRecolector.size());

		} catch (NoResultException e) {
			Assert.fail("el recolector no tiene plantas rechazadas");
		}

	}

//------------------------------------------------------------------------
//------------------------QUERYS ADMINISTRADOR------------------------------
	@Test
	/**
	 * con este metodo se listan todos los administradores;
	 */

	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarTodosLosAdministradores() {

		TypedQuery<Administrador> query = entityManager.createNamedQuery(Administrador.LISTAR_TODOS_LOS_ADMINISTRADORES,
				Administrador.class);
		List<Administrador> administradores = query.getResultList();
		Assert.assertEquals(1, administradores.size());

	}

	// ------------------------------------------------------------------------
	// ------------------------QUERYS EMPLEADO------------------------------
	@Test
	/**
	 * con este metodo se listan todos los empleados;
	 */

	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarTodosLosEmpleados() {

		TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.LISTAR_TODOS_LOS_EMPLEADOS,
				Empleado.class);
		List<Empleado> empleados = query.getResultList();
		Assert.assertEquals(1, empleados.size());

	}

	// ------------------------------------------------------------------------
	// ------------------------QUERYS RECOLECTORES------------------------------
	@Test
	/**
	 * con este metodo se listan todos los recolectores;
	 */

	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarTodosLosRecolectores() {

		TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.LISTAR_TODOS_LOS_RECOLECTORES,
				Recolector.class);
		List<Recolector> recolectores = query.getResultList();
		Assert.assertEquals(1, recolectores.size());

	}

	// ------------------------------------------------------------------------
	// ------------------------QUERYS PRA ASESORIA------------------------------
	@Test
	/**
	 * con este metodo se listan todas las asesorias;
	 */

	@UsingDataSet({ "asesoria.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarTodosLasAsesorias() {

		TypedQuery<Asesoria> query = entityManager.createNamedQuery(Asesoria.LISTAR_TODAS_LAS_ASESORIAS,
				Asesoria.class);
		List<Asesoria> asesoria = query.getResultList();
		Assert.assertEquals(1, asesoria.size());

	}

	// ------------------------------------------------------------------------
	// ------------------------QUERYS PRA ENVIO------------------------------
	@Test
	/**
	 * con este metodo se listan todas los envios;
	 */

	@UsingDataSet({ "envio.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarTodosLosEnvios() {

		TypedQuery<Envio> query = entityManager.createNamedQuery(Envio.LISTAR_TODOS_LOS_ENVIOS, Envio.class);
		List<Envio> envio = query.getResultList();
		Assert.assertEquals(3, envio.size());

	}

	// ------------------------------------------------------------------------
	// ------------------------QUERYS PRA FAMILIA------------------------------
	@Test
	/**
	 * con este metodo se listan todas las familias;
	 */

	@UsingDataSet({ "familia.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarTodasLasFamilias() {

		TypedQuery<Familia> query = entityManager.createNamedQuery(Familia.LISTAR_TODAS_LAS_FAMILIAS, Familia.class);
		List<Familia> familias = query.getResultList();
		Assert.assertEquals(1, familias.size());
	}

	// -----------------------------------------------------------------------
	// ------------------------QUERYS PRA GENEROS------------------------------
	@Test
	/**
	 * con este metodo se listan todos los generos;
	 */

	@UsingDataSet({ "genero.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarTodasLasGeneros() {

		TypedQuery<Genero> query = entityManager.createNamedQuery(Genero.LISTAR_TODOS_LOS_GENEROS, Genero.class);
		List<Genero> generos = query.getResultList();
		Assert.assertEquals(1, generos.size());

	}
}
