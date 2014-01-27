package co.com.intergrupo.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * The persistent class for the HISTORIAL_SUBASTA database table.
 * 
 */
@Entity
@Table(name = "HISTORIAL_SUBASTA")
@NamedQuery(name = "HistorialSubasta.findAll", query = "SELECT h FROM HistorialSubasta h")
public class HistorialSubasta implements Serializable {

  private static final long serialVersionUID = 1L;
  private long id;
  private Timestamp horaOferta;
  private BigDecimal Oferta;
  private String transaccion;
  private Persona comprador;
  private Subasta subasta;

  public HistorialSubasta() {
  }

  @Id
  @SequenceGenerator(name = "HISTORIAL_SUBASTA_ID_GENERATOR", sequenceName = "HISU_ID", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HISTORIAL_SUBASTA_ID_GENERATOR")
  @Column(name = "HISU_ID")
  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(name = "HISU_HORA_OFERTA")
  public Timestamp getHoraOferta() {
    return this.horaOferta;
  }

  public void setHoraOferta(Timestamp horaOferta) {
    this.horaOferta = horaOferta;
  }

  @Column(name = "HISU_OFERTA")
  public BigDecimal getOferta() {
    return this.Oferta;
  }

  public void setOferta(BigDecimal Oferta) {
    this.Oferta = Oferta;
  }

  @Column(name = "HISU_TRANSACCION")
  public String getTransaccion() {
    return this.transaccion;
  }

  public void setTransaccion(String transaccion) {
    this.transaccion = transaccion;
  }

  // bi-directional many-to-one association to Persona
  @ManyToOne
  @JoinColumn(name = "HISU_COMPRADOR", foreignKey = @ForeignKey(name = "compardor"))
  public Persona getComprador() {
    return this.comprador;
  }

  public void setComprador(Persona comprador) {
    this.comprador = comprador;
  }

  // bi-directional many-to-one association to Subasta
  @ManyToOne
  @JoinColumn(name = "HISU_SUBASTA", foreignKey = @ForeignKey(name = "subasta_detalle"))
  public Subasta getSubasta() {
    return this.subasta;
  }

  public void setSubasta(Subasta subasta) {
    this.subasta = subasta;
  }

}