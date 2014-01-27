package co.com.intergrupo.dao.generics;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GenericDao<T> implements InterfaceDao<T> {

  private Class<T> entityClass;
  private EntityManager entityManager;

  public GenericDao(Class<T> cls) {
    setEntityClass(cls);
  }

  public GenericDao(Class<T> cls, EntityManager entityManager) {
    setEntityClass(cls);
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

  public EntityManager getEntityManager() {
    return entityManager;
  }

  private void setEntityClass(Class<T> cls) {
    if (cls == null) {
      throw new IllegalArgumentException("Argument cannot be null.");
    }
    if (cls.getAnnotation(Entity.class) == null) {
      throw new IllegalArgumentException(String.format("%s is not annotated as an entity", cls.getName()));
    }
    this.entityClass = (Class<T>) cls;
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

  @SuppressWarnings("unchecked")
  public List<T> findAll() {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery(entityClass);
    Root<T> root = cq.from(entityClass);
    cq.select(root);
    Query q = entityManager.createQuery(cq);
    return (List<T>) q.getResultList();
  }

  public long countAll() {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> cq = cb.createQuery(Long.class);
    cq.select(cb.count(cq.from(entityClass)));
    return entityManager.createQuery(cq).getSingleResult().intValue();
  }

  public void removeAll() {
    String jpql = String.format("delete from %s", entityClass.getName());
    Query query = entityManager.createQuery(jpql);
    query.executeUpdate();
  }

}
