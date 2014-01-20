package co.com.intergrupo.test

import org.scalatest.{ FunSpec, BeforeAndAfter }
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import co.com.intergrupo.entities._
import org.squeryl.PrimitiveTypeMode.transaction
import co.com.intergrupo.cxf._

@RunWith(classOf[JUnitRunner])
class WebServiceFunSpecTest extends FunSpec {
  var ss: WebServiceImpl = new WebServiceImpl()
  var port: InterfaceWebService = ss.getWebServiceImplPort()

  describe("invocando web services") {
    it("consultando los datos del vehiculo con placas ABC123, que deben pertenecer a nelson perez cc 88034100 ") {
      var v: Vehiculo = port.consultarVehiculo("ABC123");
      assert(v.getIdentificacion() == "88034100")
      assert(v.getTitular() == "nelson perez")
      assert(v.getLocalidad() == "medellin")
    }

    it("consultando si el vehiculo con placas ABC123 esta reportado") {
      assert(port.vehiculoReportado("ABC123"));
    }

    it("consultando si el vehiculo con placas ABC456 esta registrado runt") {
      assert(port.registradoRunt("ABC456"));
    }

    it("consultando si el vehiculo con placas ABC789 esta al dia con los impuesto") {
      assert(port.vehiculoAlDiaConImpuesto("ABC789"));
    }
  }

}