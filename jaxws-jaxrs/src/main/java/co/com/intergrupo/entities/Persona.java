package co.com.intergrupo.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The persistent class for the PERSONA database table.
 * 
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "PERS_EMAIL_UK", columnNames = { "PERS_EMAIL" }), @UniqueConstraint(name = "PERS_DOCUMENTO_UK", columnNames = { "PERS_TIPO_DOCUMENTO", "PERS_CEDULA" }) })
@NamedQueries({ @NamedQuery(name = "Persona.findCedula", query = "SELECT p FROM Persona p WHERE p.cedula = :cedula "), @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p") })
@NamedStoredProcedureQuery(name = "actualizarNombreCompleto", procedureName = "actualizar_nombre_apellido", parameters = { @StoredProcedureParameter(mode = ParameterMode.IN, name = "cedula", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre", type = String.class), @StoredProcedureParameter(mode = ParameterMode.IN, name = "apellido", type = String.class) })
public class Persona implements Serializable {

  private static final long serialVersionUID = 1L;
  private long id;
  private String apellido;
  private String cedula;
  private String email;
  private Timestamp fechaCreacion;
  private String nombre;
  private String password;
  private String telefono;
  private String tipoDocumento;
  private String usuario;
  private List<HistorialSubasta> ofertas;
  private List<Subasta> subastas;

  public Persona() {
  }

  @Id
  @SequenceGenerator(name = "PERSONA_ID_GENERATOR", sequenceName = "PERS_ID", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONA_ID_GENERATOR")
  @Column(name = "PERS_ID")
  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(name = "PERS_APELLIDO")
  public String getApellido() {
    return this.apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  @Column(name = "PERS_CEDULA")
  public String getCedula() {
    return this.cedula;
  }

  public void setCedula(String cedula) {
    this.cedula = cedula;
  }

  @Column(name = "PERS_EMAIL")
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "PERS_FECHA_CREACION")
  public Timestamp getFechaCreacion() {
    return this.fechaCreacion;
  }

  public void setFechaCreacion(Timestamp fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  @Column(name = "PERS_NOMBRE")
  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Column(name = "PERS_PASSWORD")
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "PERS_TELEFONO")
  public String getTelefono() {
    return this.telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  @Column(name = "PERS_TIPO_DOCUMENTO")
  public String getTipoDocumento() {
    return this.tipoDocumento;
  }

  public void setTipoDocumento(String tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  @Column(name = "PERS_USUARIO")
  public String getUsuario() {
    return this.usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  // bi-directional many-to-one association to HistorialSubasta
  @OneToMany(mappedBy = "comprador")
  public List<HistorialSubasta> getOfertas() {
    return this.ofertas;
  }

  public void setOfertas(List<HistorialSubasta> ofertas) {
    this.ofertas = ofertas;
  }

  public HistorialSubasta addOferta(HistorialSubasta oferta) {
    getOfertas().add(oferta);
    oferta.setComprador(this);
    return oferta;
  }

  public HistorialSubasta removeOferta(HistorialSubasta oferta) {
    getOfertas().remove(oferta);
    oferta.setComprador(null);

    return oferta;
  }

  // bi-directional many-to-one association to Subasta
  @OneToMany(mappedBy = "vendedor")
  public List<Subasta> getSubastas() {
    return this.subastas;
  }

  public void setSubastas(List<Subasta> subastas) {
    this.subastas = subastas;
  }

  public Subasta addSubasta(Subasta subasta) {
    getSubastas().add(subasta);
    subasta.setVendedor(this);

    return subasta;
  }

  public Subasta removeSubasta(Subasta subasta) {
    getSubastas().remove(subasta);
    subasta.setVendedor(null);

    return subasta;
  }

}