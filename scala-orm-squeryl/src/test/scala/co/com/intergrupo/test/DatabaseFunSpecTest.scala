package co.com.intergrupo.test

import org.scalatest.{ FunSpec, BeforeAndAfter }
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import co.com.intergrupo.database._
import co.com.intergrupo.entities._
import org.squeryl.PrimitiveTypeMode.transaction

@RunWith(classOf[JUnitRunner])
class DatabaseFunSpecTest extends FunSpec with BeforeAndAfter with TraitConnection {

  before {
    connect
    transaction {
      SchemaSubasta.drop
      SchemaSubasta.create
    }
  }

  describe("insertando configuracion inicial ") {
    it("configurando el mail.smtp.host") {
      transaction {
        val param1: Config = new Config("mail.smtp.host", "smtp.gmail.com")
        assert(param1.id == 0)
        SchemaSubasta.config.insert(param1)
        assert(param1.id > 0)
      }
    }
  }

  //describe("Falta implementar") {
   // it("prueba pendiente")(pending)
  // }

  after {
    close
  }

}