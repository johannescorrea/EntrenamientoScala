package co.com.intergrupo.test.domain

import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfter
import org.scalatest.FunSpec
import org.squeryl.PrimitiveTypeMode.transaction
import co.com.intergrupo.database.SchemaSubasta
import co.com.intergrupo.database.TraitConnection
import co.com.intergrupo.domain.AutoNoValidoException
import co.com.intergrupo.entities.Subasta
import org.scalatest.junit.JUnitRunner
import org.scalatest.BeforeAndAfterAll
import co.com.intergrupo.entities.Persona
import co.com.intergrupo.entities.ModeloVehiculo
import co.com.intergrupo.entities.ModeloVehiculo
import co.com.intergrupo.database.SubastaRepository
import org.scalatest.mock.MockitoSugar
import org.scalatest.mock.MockitoSugar._
import co.com.intergrupo.cxf.WebServiceImpl
import org.mockito.Mockito._
import co.com.intergrupo.cxf.InterfaceWebService
import co.com.intergrupo.entities.SubastaFactory
import co.com.intergrupo.entities.HistorialSubasta
import co.com.intergrupo.domain.SubastaException
import java.util.Calendar
import java.util.Date
import java.sql.Timestamp

@RunWith(classOf[JUnitRunner])
class SubastaSpec extends FunSpec with BeforeAndAfter with BeforeAndAfterAll with TraitConnection with MockitoSugar {

  val vendedor: Persona = new Persona("CC", "71781586", "Johannes", "Correa", "jcorrea", "clave", "jscorrea@intergrupo.com", "5986858")
  val modelo: ModeloVehiculo = new ModeloVehiculo("MAZDA", "323HS")
  val comprador: Persona = new Persona("CC", "71781587", "Johannes2", "Correa", "jcorrea", "clave", "nefeper@intergrupo.com", "5986858")

  var wsCliente: WebServiceImpl = _
  var ws: InterfaceWebService = _

  override def beforeAll = {
    connect
    transaction {
      SchemaSubasta.drop
      SchemaSubasta.create
      SchemaSubasta.persona.insert(vendedor)
      SchemaSubasta.persona.insert(comprador)

    }
  }

  before {
    wsCliente = mock[WebServiceImpl]
    ws = mock[InterfaceWebService]
    when(wsCliente.getWebServiceImplPort()).thenReturn(ws)
  }

  describe("Una Subasta") {
    var idSubasta: Long = 0
    describe("Cuando es creada") {
      val year = 1997
      val placa = "LAN195"
      val precio = 5100000L

      it("Debe lanzar AutoNoValidoException si no tiene registro en Runt") {

        when(ws.registradoRunt(placa)).thenReturn(false)
        when(ws.vehiculoAlDiaConImpuesto(placa)).thenReturn(true)
        when(ws.vehiculoReportado(placa)).thenReturn(false)

        intercept[AutoNoValidoException] {
          SubastaFactory(wsCliente).crearSubasta(placa, year, precio, 20000, 175000, "MEC", "MEDELLIN", 5)
        }
      }

      it("Debe lanzar AutoNoValidoException si no tiene impuestos al dia") {
        when(ws.registradoRunt(placa)).thenReturn(true)
        when(ws.vehiculoAlDiaConImpuesto(placa)).thenReturn(false)
        when(ws.vehiculoReportado(placa)).thenReturn(false)

        intercept[AutoNoValidoException] {
          SubastaFactory(wsCliente).crearSubasta(placa, year, precio, 20000, 175000, "MEC", "MEDELLIN", 5)
        }
      }

      it("Debe lanzar AutoNoValidoException si tiene denuncias judiciales") {
        when(ws.registradoRunt(placa)).thenReturn(true)
        when(ws.vehiculoAlDiaConImpuesto(placa)).thenReturn(true)
        when(ws.vehiculoReportado(placa)).thenReturn(true)

        intercept[AutoNoValidoException] {
          SubastaFactory(wsCliente).crearSubasta(placa, year, precio, 20000, 175000, "MEC", "MEDELLIN", 5)
        }
      }

      it("Debe crear una subasta nueva") {
        when(ws.registradoRunt(placa)).thenReturn(true)
        when(ws.vehiculoAlDiaConImpuesto(placa)).thenReturn(true)
        when(ws.vehiculoReportado(placa)).thenReturn(false)

        val subasta = SubastaFactory(wsCliente).crearSubasta(placa, year, precio, 100000, 175000, "MEC", "MEDELLIN", 5)
        assert(subasta.id == 0, "la subasta creada tiene id !=0")
        assert(!subasta.isPersisted)

        val subasta2 = SubastaRepository.registrarSubasta(subasta, vendedor, modelo)
        assert(subasta2.id > 0, "la subasta no fue registrada")
        idSubasta = subasta2.id
        assert(subasta2.isPersisted, "la subasta no está persistida")
      }
    }

    describe("En la realizacion de ofertas") {

      it("Debe adicionar ofertas que superen el incremento minimo") {
        transaction {
          println("subasta a consultar: " + idSubasta)
          val subasta = SubastaRepository.buscarSubasta(idSubasta)
          assert(subasta.historial.isEmpty, "el historial no está vacio")
          val oferta = new HistorialSubasta(5400000, "cualquier cosa")

          subasta.adicionarOferta(oferta, comprador)
          assert(oferta.id > 0, "El id de la oferta no se actualizo")
          assert(!subasta.historial.isEmpty, "El historial está vacio")
        }
      }

      it("Debe lanzar excepcion si la oferta no supera el incremento") {
        transaction {
          println("subasta a consultar: " + idSubasta)
          val subasta = SubastaRepository.buscarSubasta(idSubasta)
          assert(!subasta.historial.isEmpty, "el historial esta vacio")
          val oferta = new HistorialSubasta(subasta.historial.toList.sortBy(of => of.oferta ).head.oferta+subasta.incremento/2, "cualquier cosa")
          intercept[SubastaException] {
            subasta.adicionarOferta(oferta, comprador)
          }
        }
      }

      it("No debe permitir ofertas nuevas si la subasta ha expirado") {
        transaction {
          println("subasta a consultar: " + idSubasta)
          val subasta = SubastaRepository.buscarSubasta(idSubasta)
          assert(!subasta.historial.isEmpty, "el historial esta vacio")
          subasta.fechaCreacion = calcularFechaExpirada(subasta.fechaCreacion, subasta.diasVigencia)
          val oferta = new HistorialSubasta(subasta.historial.toList.sortBy(of => of.oferta ).head.oferta+subasta.incremento*2, "cualquier cosa")
          intercept[SubastaException] {
            subasta.adicionarOferta(oferta, comprador)
          }
        }
      }

      it("Debe notificar al usuario de la oferta anterior") {

      }
    }
    def calcularFechaExpirada(fecha:Date, vigencia:Int) = {
      val date = Calendar.getInstance()
      date.setTime(fecha)
      date.add(Calendar.DATE, -vigencia-1)
      new Timestamp(date.getTime().getTime())
    }
    

  }
}