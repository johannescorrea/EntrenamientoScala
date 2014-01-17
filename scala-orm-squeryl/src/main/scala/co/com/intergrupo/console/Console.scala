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

object App extends TraitConnection {

  def main(args: Array[String]): Unit = {
    
    connect
    
    transaction {
      SchemaSubasta.drop
      SchemaSubasta.create
    }

    transaction {
      val user1: Usuario = new Usuario("nfp@domain.com", "***nfp")
      SchemaSubasta.usuario.insert(user1)
      SchemaSubasta.usuario.update(user1)
    }

    transaction {
      val user2: Usuario = new Usuario("nefeper@domain.com", "password")
      SchemaSubasta.usuario.insert(user2)
    }

    transaction {
      val user3: Usuario = new Usuario("nperez@domain.com", "password")
      SchemaSubasta.usuario.insert(user3)
    }

    inTransaction {
      println()
      println("Consultando ")
      val a = from(SchemaSubasta.usuario)(a => where(a.email like "%.%") select (a)).foreach(u => println(u.id + "-" + u.email));
    }

    transaction {
      val query: Usuario = SchemaSubasta.usuario.where(user => user.id === 3L).single
      println()
      println("Buscando unico resultado para eliminarlo")
      println(query.id + " -- " + query.email)
      SchemaSubasta.usuario.deleteWhere(s => s.id === query.id)
    }

    inTransaction {
      println()
      println("Consultando ")
      val a = from(SchemaSubasta.usuario)(a => where(a.email like "%.%") select (a)).foreach(u => println(u.id + "-" + u.email));
    }
    
    close

  }

}
