package co.com.intergrupo.console

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.adapters.DerbyAdapter
import org.squeryl.adapters.H2Adapter
import org.squeryl.adapters.MySQLAdapter
import org.squeryl.internals.DatabaseAdapter
import co.com.intergrupo.database._
import co.com.intergrupo.entities._
import org.squeryl.SessionFactory
import org.squeryl.Session
import org.squeryl.adapters.OracleAdapter

object App extends Config {

  def getSession(adapter: DatabaseAdapter) = Session.create(java.sql.DriverManager.getConnection(url, userName, password), adapter)

  def main(args: Array[String]): Unit = {

    Class.forName(driver)

    SessionFactory.concreteFactory = Some(driver) match {
      case Some("org.h2.Driver") => Some(() => getSession(new H2Adapter))
      case Some("oracle.jdbc.driver.OracleDriver") => Some(() => getSession(new OracleAdapter))
      case _ => sys.error("Soporta solo para los drivers:  org.h2.Driver o oracle.jdbc.driver.OracleDriver")
    }

    transaction {
      Subasta.drop
      Subasta.create
    }

    transaction {
      val user1: Usuario = new Usuario("nfp@domain.com", "***nfp")
      Subasta.usuario.insert(user1)
      user1.password = "#####nfp"
      Subasta.usuario.update(user1)
    }

    transaction {
      val user2: Usuario = new Usuario("nefeper@domain.com", "password")
      Subasta.usuario.insert(user2)
    }

    transaction {
      val user3: Usuario = new Usuario("nperez@domain.com", "password")
      Subasta.usuario.insert(user3)
    }

    inTransaction {
      println()
      println("Consultando ")
      val a = from(Subasta.usuario)(a => where(a.email like "%.%") select (a)).foreach(u => println(u.id + "-" + u.email));
    }

    transaction {
      val query: Usuario = Subasta.usuario.where(user => user.id === 3L).single
      println()
      println("Buscando unico resultado para eliminarlo")
      println(query.id + " -- " + query.email)
      Subasta.usuario.deleteWhere(s => s.id === query.id)
    }

    inTransaction {
      println()
      println("Consultando ")
      val a = from(Subasta.usuario)(a => where(a.email like "%.%") select (a)).foreach(u => println(u.id + "-" + u.email));
    }

  }

}
