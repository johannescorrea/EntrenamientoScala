package co.com.intergrupo.console

import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.Persistence
import javax.persistence.Table
import co.com.intergrupo.dao.PruebaDto

object App {

  def main(args: Array[String]){
    var em: EntityManager = Persistence.createEntityManagerFactory("jpa").createEntityManager
    var prueba: PruebaDto = new PruebaDto("nelson este funciono")
    val tx: EntityTransaction = em.getTransaction
    tx.begin()
    //val dao: TraitDao[PruebaDto] = new PruebaDao(em)
    //dao.save(prueba)
    em.persist(prueba)
    tx.commit
    em.close
  }
}