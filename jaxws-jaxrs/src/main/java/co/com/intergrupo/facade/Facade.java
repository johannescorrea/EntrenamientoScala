package co.com.intergrupo.facade;

import java.io.File;
import java.net.URISyntaxException;

import org.dom4j.Document;
import org.dom4j.Element;

import co.com.intergrupo.dto.Vehiculo;
import co.com.intergrupo.utils.Dom4J;

public class Facade {

  private static Facade instance;
  private Element elementRoot;

  static {
    instance = new Facade();
  }

  private Facade() {
    try {
      File xml = new File(getClass().getClassLoader().getResource("vehiculos.xml").toURI());
      Dom4J dom4J = new Dom4J();
      Document document = dom4J.getDocument(xml);
      elementRoot = document.getRootElement();
    } catch (URISyntaxException e) {

    }
  }

  public static Facade getInstance() {
    synchronized (Facade.class) {
      return instance;
    }
  }

  public Vehiculo consultarVehiculo(String placa) {
    Vehiculo vehiculo = null;
    if (elementRoot != null) {
      Element element = (Element) elementRoot.selectSingleNode("/vehiculos/vehiculo[@placa='" + placa + "']");
      if (element != null) {
        vehiculo = new Vehiculo();
        vehiculo.setPlaca(element.attributeValue("placa"));
        vehiculo.setLocalidad(element.elementText("localidad"));
        vehiculo.setIdentificacion(element.elementText("identificacion"));
        vehiculo.setTitular(element.elementText("titular"));
        try {
          vehiculo.setImpuesto(Boolean.valueOf(element.elementTextTrim("impuestos")));
          vehiculo.setRunt(Boolean.valueOf(element.elementTextTrim("runt")));
          vehiculo.setReportado(Boolean.valueOf(element.elementTextTrim("reportado")));
        } catch (Exception e) {
        }
      }
    }
    return vehiculo;
  }

  public boolean vehiculoReportado(String placa) {
    boolean rta = true;
    if (elementRoot != null) {
      Element element = (Element) elementRoot.selectSingleNode("/vehiculos/vehiculo[@placa='" + placa + "']/reportado");
      rta = Boolean.valueOf(element.getTextTrim());
    }
    return rta;
  }

  public boolean registradoRunt(String placa) {
    boolean rta = true;
    if (elementRoot != null) {
      Element element = (Element) elementRoot.selectSingleNode("/vehiculos/vehiculo[@placa='" + placa + "']/runt");
      rta = Boolean.valueOf(element.getTextTrim());
    }
    return rta;
  }

  public boolean vehiculoAlDiaConImpuesto(String placa) {
    boolean rta = true;
    if (elementRoot != null) {
      Element element = (Element) elementRoot.selectSingleNode("/vehiculos/vehiculo[@placa='" + placa + "']/impuestos");
      rta = Boolean.valueOf(element.getTextTrim());
    }
    return rta;
  }

}
