package co.com.intergrupo.cxf;

import org.junit.Assert;
import org.junit.Test;

public class WebServiceImplClient {

  @Test
  public void test() {

    WebServiceImpl ss = new WebServiceImpl();
    InterfaceWebService port = ss.getWebServiceImplPort();

    Assert.assertTrue(port.saludo("nelson").contains("Hola"));
    Vehiculo v = port.consultarVehiculo("ABC123");
    Assert.assertEquals("88034100", v.getIdentificacion());
    Assert.assertEquals("nelson perez", v.getTitular());
    Assert.assertEquals("medellin", v.getLocalidad());

    Assert.assertTrue(port.vehiculoReportado("ABC123"));
    Assert.assertTrue(port.vehiculoAlDiaConImpuesto("ABC789"));
    Assert.assertTrue(port.registradoRunt("ABC123"));

  }

}
