package co.com.intergrupo.entities

import java.sql.Timestamp
import org.squeryl.KeyedEntity
import org.squeryl.annotations.Column

class BaseEntity extends KeyedEntity[Long] {
  val id: Long = 0
  var lastModified = new Timestamp(System.currentTimeMillis)
}

class Persona(val cedula: String, @Column("nombre") val nombres: String, @Column("apellido") val apellidos: String, val usuario: String, val password: String, val email: Option[String], val telefono: String) extends BaseEntity {
  def this() = this("", "", "", "", "", Some(""), "")
}

class Usuario(var email: String, var password: String) extends BaseEntity {
  def this() = this("", "")
}
