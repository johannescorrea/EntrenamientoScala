package co.com.intergrupo.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.com.intergrupo.dao.PruebaDao;
import co.com.intergrupo.dao.generics.InterfaceDao;
import co.com.intergrupo.dto.PruebaDto;

public class DaoTest {

  private EntityManager em;
  private PruebaDto prueba;

  @Before
  public void createDataSource() {
    em = Persistence.createEntityManagerFactory("jpa").createEntityManager();
    prueba = new PruebaDto();
    prueba.setNombre("nelson");
  }

  @Test
  public void run() {
    insert();
    update();
    remove();
  }

  public void insert() {
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    InterfaceDao<PruebaDto> dao = new PruebaDao(em);
    dao.save(prueba);
    Assert.assertTrue(prueba.getId() != 0);
    tx.commit();
  }

  public void update() {
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    InterfaceDao<PruebaDto> dao = new PruebaDao(em);
    prueba.setNombre("nelson modificado");
    dao.update(prueba);
    Assert.assertEquals(prueba.getNombre(), dao.findById(prueba.getId()).getNombre());
    tx.commit();
  }

  public void remove() {
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    InterfaceDao<PruebaDto> dao = new PruebaDao(em);
    dao.remove(prueba);
    Assert.assertNull(dao.findById(prueba.getId()));
    tx.commit();
  }

  @After
  public void after() {
    em.close();
  }

}
