package co.com.intergrupo.test

import org.scalatest.{ FunSpec, BeforeAndAfter }
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import co.com.intergrupo.database._
import co.com.intergrupo.entities._
import org.squeryl.PrimitiveTypeMode.transaction
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
import org.scalatest.BeforeAndAfterAll

@RunWith(classOf[JUnitRunner])
class DatabaseFunSpecTest extends FunSpec with BeforeAndAfterAll with TraitConnection {

  var id: Long = 0

  override def beforeAll() {

    connect
    transaction {
      SchemaSubasta.drop
      SchemaSubasta.create
    }

  }

  describe("insertando configuracion inicial ") {
    ignore("configurando el mail.smtp.host") {
      transaction {
        val param1: Config = new Config("mail.smtp.host", "smtp.gmail.com")
        assert(param1.id == 0)
        SchemaSubasta.config.insert(param1)
        assert(param1.id > 0)
      }
    }

    ignore("configurando el mail.smtp.port") {
      transaction {
        val param1: Config = new Config("mail.smtp.port", "587")
        assert(param1.id == 0)
        SchemaSubasta.config.insert(param1)
        assert(param1.id > 0)
      }
    }

    ignore("configurando el mail.smtp.user") {
      transaction {
        val param1: Config = new Config("mail.smtp.user", "nperez@domain.com")
        assert(param1.id == 0)
        SchemaSubasta.config.insert(param1)
        assert(param1.id > 0)
      }
    }

    it("deberia crear una subasta") {
      transaction {
        val vendedor: Persona = new Persona("CC", "71781586", "Johannes", "Correa", "jcorrea", "clave", "jscorrea@intergrupo.com", "5986858")
        val modelo = new ModeloVehiculo("MAZDA", "323HS")

        SchemaSubasta.tipoModeloVehiculo.insert(modelo)
        SchemaSubasta.persona.insert(vendedor)

        //SchemaSubasta.tipoModeloVehiculo.insert(modelo)
        val year = 1997
        val placa = "LAN195"
        val precio = 5100000L
        val fechaV = 
        //assert(vendedor.id > 0)
        println(vendedor)

        val subasta = new Subasta(placa, year, precio, 20000, 170000, "MECANICA", "MEDELLIN", 5)

        subasta.personaVendedor.assign(vendedor)
        subasta.modelo.assign(modelo)
        SchemaSubasta.subasta.insert(subasta)
        assert(subasta.id > 0, "Assert1")
        println(vendedor)

        id = subasta.id
        val subasta2: Subasta = SchemaSubasta.subasta.where(sub => sub.id === id).single

        assert(subasta2.modelo.toList.size > 0, "modelo no es mayor que 0")
        println("Imprimiendo modelo asociado a la subasta")
        subasta2.modelo.toList.foreach(p => println(p.marca))
        assert(subasta2.personaVendedor.toList.size > 0, "vendedor no es mayor que 0")

        println("Imprimiendo datos de tablas")
        from(SchemaSubasta.persona)(s => select(s)).toList.foreach(p => println(p.id))
        from(SchemaSubasta.tipoModeloVehiculo)(s => select(s)).toList.foreach(p => println(p.marca))

      }
    }

    it("deberia registrar una oferta") {
      transaction {
        val comprador: Persona = new Persona("CC", "71781587", "Johannes2", "Correa", "jcorrea", "clave", "nefeper@intergrupo.com", "5986858")
        SchemaSubasta.persona.insert(comprador)

        var subasta2: Subasta = SchemaSubasta.subasta.where(sub => sub.id === id).single
        println(subasta2.placa)

        val oferta = new HistorialSubasta(5400000, "cualquier cosa")
        oferta.personaComprador.assign(comprador)
        subasta2.historial.associate(oferta)
        //oferta.subastaOfertada.assign(subasta2)
        
        //SchemaSubasta.historialSubasta.insert(oferta)
        
        
        //SchemaSubasta.subasta.update(subasta2)

        assert(oferta.id > 0, "id oferta no es mayor que cero")

        println("Imprimiendo ofertas")
        SchemaSubasta.historialSubasta.foreach(h => println(h.transaccion + ", " + h.subastaOfertada.foreach(s => print(s.placa+"-"+s.id))))
        
        
        //val subasta3: Subasta = SchemaSubasta.subasta.where(sub => sub.id === id).single
        val ofertas = subasta2.historial.toList
        assert(ofertas.size > 0, "Lista de ofertas no es mayor que cero")
        
        println("Imprimiendo compradores")
        ofertas.foreach(of => println(of.transaccion))
      }
    }
    
//    it("la subasta debería tener ofertas") {
//      transaction {
//        val subasta2: Subasta = SchemaSubasta.subasta.where(sub => sub.id === id).single
//        
//        subasta2.
//      }
//    }

  }


}