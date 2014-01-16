package co.com.intergrupo.dao.generics;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

public class AbstratDao<T> implements InterfaceDao<T> {

  private final Class<T> entityClass;

  private EntityManager entityManager;

  public AbstratDao(Class<T> cls) {
    if (cls == null) {
      throw new IllegalArgumentException("Argument cannot be null. It must be a POJO Class annotated as an entity.");
    }
    if (cls.getAnnotation(Entity.class) == null) {
      throw new IllegalArgumentException(String.format("%s is not annotated as an entity", cls.getName()));
    }
    this.entityClass = (Class<T>) cls;
  }

  public EntityManager getEntityManager() {
    return entityManager;
  }

  public AbstratDao(Class<T> cls, EntityManager entityManager) {
    this(cls);
    setEntityManager(entityManager);
  }

  public void setEntityManager(EntityManager entityManager) {
    if (entityManager == null) {
      throw new IllegalArgumentException("Argument cannot be null");
    }
    if (!entityManager.isOpen()) {
      throw new IllegalStateException("Entity manager is closed: " + entityManager);
    }
    this.entityManager = entityManager;
  }

  public Class<T> getEntityClass() {
    return entityClass;
  }

  public void save(T entity) {
    getEntityManager().persist(entity);
  }
  
  public void update(T entity) {
    getEntityManager().merge(entity);
  }

  public void remove(T entity) {
    getEntityManager().remove(entity);
  }

  public T findById(Object id) {
    return getEntityManager().find(entityClass, id);
  }

  public List<T> queryByExample(T example) {
    throw new UnsupportedOperationException(" No soportada ");
  }

}
