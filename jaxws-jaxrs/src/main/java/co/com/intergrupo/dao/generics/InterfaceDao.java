package co.com.intergrupo.dao.generics;

import java.util.List;

import javax.persistence.EntityManager;

public interface InterfaceDao<T> {

  EntityManager getEntityManager();

  Class<T> getEntityClass();

  void setEntityManager(EntityManager manager);

  void save(T entity);

  void update(T entity);

  void remove(T entity);

  T findById(Object id);

  List<T> findAll();

  long countAll();

  void removeAll();

}
