package co.com.intergrupo.entities

import java.sql.Timestamp

import org.squeryl.KeyedEntity
import org.squeryl.annotations.Column
import org.squeryl.dsl.ManyToOne

import co.com.intergrupo.database.SchemaSubasta


//class HistorialSubasta(val subasta:Subasta, val comprador:Persona, @Column("HISU_OFERTA") val oferta: Long, @Column("HISU_TRANSACCION") val transaccion: String) extends KeyedEntity[Long] {
class HistorialSubasta(@Column("HISU_OFERTA") val oferta: Long, @Column("HISU_TRANSACCION") val transaccion: String) extends KeyedEntity[Long] {
  
//  if(comprador != null && comprador.id == 0) {
//    SchemaSubasta.persona.insert(comprador)
//  }
  
  @Column("HISU_ID")
  val id: Long = 0
  @Column("HISU_HORA_OFERTA")
  var lastModified = new Timestamp(System.currentTimeMillis)
  lazy val subastaOfertada: ManyToOne[Subasta] = SchemaSubasta.subastaTransaccion.right(this)
  lazy val personaComprador: ManyToOne[Persona] = SchemaSubasta.compradorSubasta.right(this)
  
//  if(subasta!=null) subastaOfertada.assign(subasta)
//  if(comprador!=null) personaComprador.assign(comprador)
}