package co.com.intergrupo.entities

import java.sql.Timestamp
import org.squeryl.KeyedEntity
import org.squeryl.annotations.Column
import co.com.intergrupo.entities._
import org.squeryl.dsl.ManyToOne
import co.com.intergrupo.database._
import org.squeryl.dsl.OneToMany
import scala.slick.direct.AnnotationMapper.table
import org.squeryl.Schema

class BaseEntity extends KeyedEntity[Long] {
  val id: Long = 0
  var lastModified = new Timestamp(System.currentTimeMillis)
}

object TipoDocumento extends Enumeration {

  type TipoDocumento = Value

  val CC = Value("Cedula de Ciudadania")

  val CE = Value("Cedula de Extranjero")

  val TI = Value("Tarjeta de Identidad")

}

class Persona(@Column("PERS_TIPO_DOCUMENTO") var tipoDocumento: String, @Column("PERS_CEDULA") val cedula: String, @Column("PERS_NOMBRE") val nombres: String, @Column("PERS_APELLIDO") val apellidos: String, @Column("PERS_USUARIO") val usuario: String, @Column("PERS_PASSWORD") val password: String, @Column("PERS_EMAIL") val email: String, @Column("PERS_TELEFONO") val telefono: String) extends KeyedEntity[Long] {
  @Column("PERS_ID")
  val id: Long = 0
  @Column("PERS_FECHA_CREACION")
  var lastModified = new Timestamp(System.currentTimeMillis)

  lazy val subastas: OneToMany[Subasta] = SchemaSubasta.vendedorSubasta.left(this)
  //lazy val ofertaSubastas: OneToMany[HistorialSubasta] = SchemaSubasta.compradorSubasta.left(this)

  def this() = this("", "", "", "", "", "", "", "")
}

class ModeloVehiculo(@Column("MOVE_MARCA") var marca: String, @Column("MOVE_MODELO") val modelo: String) extends KeyedEntity[Long] {
  @Column("MOVE_ID")
  val id: Long = 0
  @Column("MOVE_FECHA_CREACION")
  var lastModified = new Timestamp(System.currentTimeMillis)

  lazy val vehiculoSubastados: OneToMany[Subasta] = SchemaSubasta.modeloVehiculo.left(this)

  def this() = this("", "")
}

class Subasta(@Column("SUBA_VENDEDOR") var vendedor: Long, @Column("SUBA_MODELO") val modelo: Long, @Column("SUBA_ANHO") val anho: Int, @Column("SUBA_PLACA_VEHICULO") val placa: String, @Column("SUBA_PRECIO") val precio: Long, @Column("SUBA_INCREMENTO_MINIMO") val incremento: Long, @Column("SUBA_KILOMETRAJE") val kilometraje: Long, @Column("SUBA_TRANSMICION") val transmicion: String, @Column("SUBA_LOCALIDAD") val localidad: String) extends KeyedEntity[Long] {
  @Column("SUBA_ID")
  val id: Long = 0
  @Column("SUBA_FECHA_CREACION")
  var lastModified = new Timestamp(System.currentTimeMillis)

 // lazy val historial: OneToMany[HistorialSubasta] = SchemaSubasta.subastaTransaccion.left(this)

  lazy val personaVendedor: ManyToOne[Persona] = SchemaSubasta.vendedorSubasta.right(this)

}

class HistorialSubasta(@Column("HISU_SUBASTA") var subastaId: Long, @Column("HISU_COMPRADOR") val comprador: Long, @Column("HISU_OFERTA") val oferta: Long, @Column("HISU_TRANSACCION") val transaccion: Char) extends KeyedEntity[Long] {
  @Column("HISU_ID")
  val id: Long = 0
  @Column("HISU_HORA_OFERTA")
  var lastModified = new Timestamp(System.currentTimeMillis)

  //lazy val subastaOfertada: ManyToOne[Subasta] = SchemaSubasta.subastaTransaccion.right(this)

 // lazy val personaComprador: ManyToOne[Persona] = SchemaSubasta.compradorSubasta.right(this)
}

class Config(val name: String, val value: String) extends BaseEntity {
  def this() = this("", "")
}

class Usuario(val email: String, val password: String) extends BaseEntity {
  def this() = this("", "")
}
