package co.com.intergrupo.test

import org.scalatest.{ FunSuite, BeforeAndAfter }
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec

import co.com.intergrupo.database._
import co.com.intergrupo.entities._
import org.squeryl.PrimitiveTypeMode.transaction

@RunWith(classOf[JUnitRunner])
class DatabaseFunSuiteTest extends FunSuite with BeforeAndAfter with TraitConnection {

  before {
    connect
    transaction {
      SchemaSubasta.drop
      SchemaSubasta.create
    }
  }

  test("insertando configuracion inicial configurando el mail.smtp.host ") {
    transaction {
      val param1: Config = new Config("mail.smtp.host", "smtp.gmail.com")
      assert(param1.id == 0)
      SchemaSubasta.config.insert(param1)
      assert(param1.id > 0)
    }
  }

  after {
   //close
  }

}