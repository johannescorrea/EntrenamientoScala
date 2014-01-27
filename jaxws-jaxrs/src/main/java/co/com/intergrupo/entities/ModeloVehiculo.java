package co.com.intergrupo.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the MODELO_VEHICULO database table.
 * 
 */
@Entity
@Table(name = "MODELO_VEHICULO")
@NamedQuery(name = "ModeloVehiculo.findAll", query = "SELECT m FROM ModeloVehiculo m")
public class ModeloVehiculo implements Serializable {

  private static final long serialVersionUID = 1L;
  private long id;
  private String marca;
  private String modelo;
  private Date fechaCreacion;

  public ModeloVehiculo() {
  }

  @Id
  @SequenceGenerator(name = "MODELO_VEHICULO_ID_GENERATOR", sequenceName = "MOVE_ID", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODELO_VEHICULO_ID_GENERATOR")
  @Column(name = "MOVE_ID")
  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(name = "MOVE_MARCA")
  public String getMarca() {
    return this.marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  @Column(name = "MOVE_MODELO")
  public String getModelo() {
    return this.modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "MOVE_FECHA_CREACION")
  public Date getFechaCreacion() {
    return this.fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

}