package co.com.intergrupo.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vehiculo", propOrder = { "localidad", "identificacion", "titular", "impuesto", "runt", "reportado" })
@XmlRootElement(name = "vehiculo", namespace = "http://intergrupo.com.co/vehiculos")
public class Vehiculo {

  @XmlElement
  private String localidad;
  @XmlElement
  private String identificacion;
  @XmlElement
  private String titular;
  @XmlElement
  private boolean impuesto;
  @XmlElement
  private boolean runt;
  @XmlElement
  private boolean reportado;
  @XmlAttribute(required = true)
  @XmlSchemaType(name = "NCName")
  private String placa;

  public Vehiculo() {
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public String getLocalidad() {
    return localidad;
  }

  public void setLocalidad(String localidad) {
    this.localidad = localidad;
  }

  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
  }

  public String getTitular() {
    return titular;
  }

  public void setTitular(String titular) {
    this.titular = titular;
  }

  public boolean isImpuesto() {
    return impuesto;
  }

  public void setImpuesto(boolean impuesto) {
    this.impuesto = impuesto;
  }

  public boolean isRunt() {
    return runt;
  }

  public void setRunt(boolean runt) {
    this.runt = runt;
  }

  public boolean isReportado() {
    return reportado;
  }

  public void setReportado(boolean reportado) {
    this.reportado = reportado;
  }

}
