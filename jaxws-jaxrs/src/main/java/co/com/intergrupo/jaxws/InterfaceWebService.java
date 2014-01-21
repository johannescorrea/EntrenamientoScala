package co.com.intergrupo.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import co.com.intergrupo.dto.Vehiculo;

@WebService
public interface InterfaceWebService {

  @WebMethod(operationName = "saludo")
  public String saludo(@WebParam(name = "nombre") String nombre);

  @WebMethod(operationName = "consultarVehiculo", action = "urn:consultarVehiculo")
  @WebResult(name = "vehiculo")
  public Vehiculo consultarVehiculo(@WebParam(name = "placa") String placa);

  @WebMethod(operationName = "vehiculoAlDiaConImpuesto", action = "urn:vehiculoAlDiaConImpuesto")
  public boolean vehiculoAlDiaConImpuesto(@WebParam(name = "placa") String placa);

  @WebMethod(operationName = "registradoRunt", action = "urn:registradoRunt")
  public boolean registradoRunt(@WebParam(name = "placa") String placa);

  @WebMethod(operationName = "vehiculoReportado", action = "urn:vehiculoReportado")
  public boolean vehiculoReportado(@WebParam(name = "placa") String placa);

}
