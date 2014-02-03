package co.com.intergrupo.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.com.intergrupo.entities.Persona;

public class ProcedureDaoTest {

  private EntityManager em;

  @Before
  public void createDataSource() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    em = emf.createEntityManager();
  }

  @Test
  public void obtenerNombreCompleto() {
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("obtener_NombreCompleto");
    storedProcedure.registerStoredProcedureParameter("cedula", String.class, ParameterMode.IN);
    storedProcedure.registerStoredProcedureParameter("nombre", String.class, ParameterMode.OUT);
    storedProcedure.setParameter("cedula", "88034010");
    storedProcedure.execute();
    String nombre = (String) storedProcedure.getOutputParameterValue("nombre");
    Assert.assertEquals(nombre, "nelson perez");
    System.out.println("nombre completo es: " + nombre);
    tx.commit();
  }

  @Test
  public void actualizarNombreCompleto() {
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    String cedulaJuan = "88034011";
    Query query = em.createNamedQuery("Persona.findCedula");
    query.setParameter("cedula", cedulaJuan);
    Persona persona = (Persona) query.getSingleResult();
    Assert.assertNotNull(persona.getNombre());
    Assert.assertNotNull(persona.getApellido());

    StoredProcedureQuery storeProcedure = em.createNamedStoredProcedureQuery("actualizarNombreCompleto");
    storeProcedure.setParameter("cedula", cedulaJuan);
    storeProcedure.setParameter("nombre", "juanito.");
    storeProcedure.setParameter("apellido", "perez.");
    storeProcedure.execute();

    query = em.createQuery("SELECT p FROM Persona p WHERE p.cedula = :cedula ");
    query.setParameter("cedula", cedulaJuan);
    persona = (Persona) query.getSingleResult();
    Assert.assertEquals(persona.getNombre(), "juanito.");
    Assert.assertEquals(persona.getApellido(), "perez.");
    tx.commit();
  }

  @After
  public void close() {
    em.close();
  }
}
