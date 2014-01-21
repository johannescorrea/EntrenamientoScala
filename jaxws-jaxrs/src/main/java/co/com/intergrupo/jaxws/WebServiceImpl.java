package co.com.intergrupo.jaxws;

import javax.jws.WebService;

import co.com.intergrupo.dto.Vehiculo;
import co.com.intergrupo.facade.Facade;

@WebService(endpointInterface = "co.com.intergrupo.jaxws.InterfaceWebService", serviceName = "WebServiceImpl")
public class WebServiceImpl implements InterfaceWebService {

  public String saludo(String nombre) {
    return "Hola " + nombre + ", como estas ?";
  }

  public Vehiculo consultarVehiculo(String placa) {
    return Facade.getInstance().consultarVehiculo(placa);
  }

  public boolean vehiculoReportado(String placa) {
    return Facade.getInstance().vehiculoReportado(placa);
  }

  public boolean registradoRunt(String placa) {
    return Facade.getInstance().registradoRunt(placa);
  }

  public boolean vehiculoAlDiaConImpuesto(String placa) {
    return Facade.getInstance().vehiculoAlDiaConImpuesto(placa);
  }

}
