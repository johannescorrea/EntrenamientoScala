package co.com.intergrupo.dao

import scala.reflect.BeanProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.persistence.GenerationType

@Entity
@Table(name = "prueba", schema = "nefeper")
class PruebaDto(@BeanProperty nombre: String) {
  
  @Id
  @GeneratedValue(generator = "UserId", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "UserId", sequenceName = "SQ_USUA_ID", allocationSize = 1)
  var id: Int = _

  def this() = this(null)

  override def toString = id + " = " + nombre
}