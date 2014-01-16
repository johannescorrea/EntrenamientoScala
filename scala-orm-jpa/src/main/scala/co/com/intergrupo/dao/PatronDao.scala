package co.com.intergrupo.dao

import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.Table

/*
trait TraitDao[T] {
  val entityClass: Class[T]
  val entityManager: EntityManager
  def getEntityManager(): EntityManager
  def getEntityClass(): Class[T]
  def setEntityManager(manager: EntityManager)
  def save(entity: T)
  def update(entity: T)
  def remove(entity: T)
  def findById(id: Any): T
}

class AbstratDao[T](entityClass: Class[T], entityManager: EntityManager) extends TraitDao[T] {

  def this(entityClass: Class[T]) = { this(entityClass, null) }

  def save(entity: T) = { entityManager.persist(entity) }

  def update(entity: T) = { entityManager.merge(entity) }

  def remove(entity: T) = { entityManager.remove(entity) }

  def findById(id: Any): T = { entityManager.find(entityClass, id) }

}

class PruebaDao(entityClass: Class[PruebaDto], entityManager: EntityManager) extends AbstratDao[PruebaDto](entityClass, entityManager) {

  def this() = { this(classOf[PruebaDto], null) }

  def this(entityManager: EntityManager) = { this(classOf[PruebaDto], entityManager) }

}

*/


