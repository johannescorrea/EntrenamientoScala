package co.com.intergrupo.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the SUBASTA database table.
 * 
 */
@Entity
@NamedQuery(name = "Subasta.findAll", query = "SELECT s FROM Subasta s")
public class Subasta implements Serializable {

  private static final long serialVersionUID = 1L;
  private long id;
  private BigDecimal anho;
  private Date fechaCreacion;
  private BigDecimal incrementoMinimo;
  private BigDecimal kilometraje;
  private String localidad;
  private String placaVehiculo;
  private BigDecimal precio;
  private String transmicion;
  private List<HistorialSubasta> historialSubastas;
  private ModeloVehiculo modeloVehiculo;
  private Persona vendedor;

  public Subasta() {
  }

  @Id
  @SequenceGenerator(name = "SUBASTA_ID_GENERATOR", sequenceName = "SUBA_ID", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBASTA_ID_GENERATOR")
  @Column(name = "SUBA_ID")
  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(name = "SUBA_ANHO")
  public BigDecimal getAnho() {
    return this.anho;
  }

  public void setAnho(BigDecimal anho) {
    this.anho = anho;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "SUBA_FECHA_CREACION")
  public Date getFechaCreacion() {
    return this.fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  @Column(name = "SUBA_INCREMENTO_MINIMO")
  public BigDecimal getIncrementoMinimo() {
    return this.incrementoMinimo;
  }

  public void setIncrementoMinimo(BigDecimal incrementoMinimo) {
    this.incrementoMinimo = incrementoMinimo;
  }

  @Column(name = "SUBA_KILOMETRAJE")
  public BigDecimal getKilometraje() {
    return this.kilometraje;
  }

  public void setKilometraje(BigDecimal kilometraje) {
    this.kilometraje = kilometraje;
  }

  @Column(name = "SUBA_LOCALIDAD")
  public String getLocalidad() {
    return this.localidad;
  }

  public void setLocalidad(String localidad) {
    this.localidad = localidad;
  }

  @Column(name = "SUBA_PLACA_VEHICULO")
  public String getPlacaVehiculo() {
    return this.placaVehiculo;
  }

  public void setPlacaVehiculo(String placaVehiculo) {
    this.placaVehiculo = placaVehiculo;
  }

  @Column(name = "SUBA_PRECIO")
  public BigDecimal getPrecio() {
    return this.precio;
  }

  public void setPrecio(BigDecimal precio) {
    this.precio = precio;
  }

  @Column(name = "SUBA_TRANSMICION")
  public String getTransmicion() {
    return this.transmicion;
  }

  public void setTransmicion(String transmicion) {
    this.transmicion = transmicion;
  }

  // bi-directional many-to-one association to HistorialSubasta
  @OneToMany(mappedBy = "subasta")
  public List<HistorialSubasta> getHistorialSubastas() {
    return this.historialSubastas;
  }

  public void setHistorialSubastas(List<HistorialSubasta> historialSubastas) {
    this.historialSubastas = historialSubastas;
  }

  public HistorialSubasta addHistorialSubasta(HistorialSubasta historialSubasta) {
    getHistorialSubastas().add(historialSubasta);
    historialSubasta.setSubasta(this);

    return historialSubasta;
  }

  public HistorialSubasta removeHistorialSubasta(HistorialSubasta historialSubasta) {
    getHistorialSubastas().remove(historialSubasta);
    historialSubasta.setSubasta(null);

    return historialSubasta;
  }

  // uni-directional many-to-one association to ModeloVehiculo
  @ManyToOne
  @JoinColumn(name = "SUBA_MODELO")
  public ModeloVehiculo getModeloVehiculo() {
    return this.modeloVehiculo;
  }

  public void setModeloVehiculo(ModeloVehiculo modeloVehiculo) {
    this.modeloVehiculo = modeloVehiculo;
  }

  // bi-directional many-to-one association to Persona
  @ManyToOne
  @JoinColumn(name = "SUBA_VENDEDOR")
  public Persona getVendedor() {
    return this.vendedor;
  }

  public void setVendedor(Persona vendedor) {
    this.vendedor = vendedor;
  }

}