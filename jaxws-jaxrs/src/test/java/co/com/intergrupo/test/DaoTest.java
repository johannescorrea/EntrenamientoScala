package co.com.intergrupo.test;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.com.intergrupo.dao.generics.GenericDao;
import co.com.intergrupo.dao.generics.InterfaceDao;
import co.com.intergrupo.entities.ModeloVehiculo;

public class DaoTest {

  private EntityManager em;
  private ModeloVehiculo prueba;

  @Before
  public void createDataSource() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    em = emf.createEntityManager();
    prueba = new ModeloVehiculo();
    prueba.setMarca("hyundai");
    prueba.setModelo("i25");
    prueba.setFechaCreacion(new Date());
  }

  public static void main(String[] arg) {
    DaoTest d = new DaoTest();
    d.createDataSource();
    d.run();
    d.after();
  }

  @Test
  public void run() {
    insert();
    consultar();
    update();
    consultar();
    // remove();
  }

  public void consultar() {
    Query query = em.createQuery("SELECT mv FROM ModeloVehiculo mv");
    @SuppressWarnings("unchecked")
    List<ModeloVehiculo> list = (List<ModeloVehiculo>) query.getResultList();
    System.out.println("Consultando Modelo Vehiculo...");
    for (ModeloVehiculo prueba : list) {
      System.out.println(prueba.getId() + "  : " + prueba.getMarca() + " " + prueba.getModelo());
    }
    Assert.assertTrue(list.size() > 0);
  }

  public void insert() {
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    InterfaceDao<ModeloVehiculo> dao = new GenericDao<ModeloVehiculo>(ModeloVehiculo.class, em);
    dao.save(prueba);
    Assert.assertTrue(prueba.getId() != 0);
    tx.commit();
  }

  public void update() {
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    InterfaceDao<ModeloVehiculo> dao = new GenericDao<ModeloVehiculo>(ModeloVehiculo.class, em);
    prueba.setModelo("i35");
    dao.update(prueba);
    Assert.assertEquals(prueba.getModelo(), dao.findById(prueba.getId()).getModelo());
    tx.commit();
  }

  public void remove() {
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    InterfaceDao<ModeloVehiculo> dao = new GenericDao<ModeloVehiculo>(ModeloVehiculo.class, em);
    dao.remove(prueba);
    Assert.assertNull(dao.findById(prueba.getId()));
    tx.commit();
  }

  @After
  public void after() {
    em.close();
  }

}
