package co.com.intergrupo.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "prueba", schema = "nefeper")
public class PruebaDto implements Serializable {

  @Id
  @GeneratedValue(generator = "UserId", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "UserId", sequenceName = "SQ_USUA_ID", allocationSize = 1)
  private int id;
  private String nombre;
  private static final long serialVersionUID = -3070651125431179487L;

  public PruebaDto() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

}