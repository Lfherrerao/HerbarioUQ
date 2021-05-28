package co.edu.analisis2.grid.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.analisis2.co.entidades.Administrador;

/**
 * se encarga la preconfiguracion de la capa de negocio. Session Bean
 * implementation class SetupEJB
 */
@Singleton
@LocalBean
@Startup
public class SetupEJB {
	@PersistenceContext
	private EntityManager entityMananger;

	/**
	 * Default constructor.
	 */
	public SetupEJB() {

	}

	/**
	 * carga la preconfiguracion
	 */
	@PostConstruct
	private void init() {

		TypedQuery<Long> query = entityMananger.createNamedQuery(Administrador.CONTAR_ADMINS, Long.class);
		long contarAdmin = query.getSingleResult();

		if (contarAdmin == 0) {

			Administrador administrador = new Administrador();

			administrador.setNombre("admin");
			administrador.setApellido("admin");
			administrador.setCedula("admin");
			administrador.setTelefono("admin");
			administrador.setCorreo("admin@admin");
			administrador.setContrasenia("admin");

			entityMananger.persist(administrador);
		}
	}

}
