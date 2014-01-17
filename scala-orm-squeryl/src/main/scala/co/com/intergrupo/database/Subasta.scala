package co.com.intergrupo.database

import co.com.intergrupo.entities._
import org.squeryl._
import org.squeryl.PrimitiveTypeMode._

object Subasta extends Schema {

  val usuario = table[Usuario]("usuario")

  val persona = table[Persona]("persona")

  val tipoModeloVehiculo = table[ModeloVehiculo]("MODELO_VEHICULO")

  val subasta = table[Subasta]("SUBASTA")

  val historialSubasta = table[HistorialSubasta]("HISTORIAL_SUBASTA")
  
  val vendedorSubasta = oneToManyRelation(persona, subasta).via((p, s) => p.id === s.vendedor)

  val modeloVehiculo = oneToManyRelation(tipoModeloVehiculo, subasta).via((tmv, s) => tmv.id === s.modelo)

  val compradorSubasta = oneToManyRelation(persona, historialSubasta).via((p, hs) => p.id === hs.comprador)

  val subastaTransaccion = oneToManyRelation(subasta, historialSubasta).via((s, hs) => s.id === hs.subasta)

  on(usuario)(user => declare(
    user.id is (autoIncremented("usua_id")),
    user.email is (unique)))

  on(persona)(s => declare(
    s.id is (autoIncremented("pers_id")),
    s.email is (unique, dbType("varchar(60)")),
    columns(s.nombres, s.apellidos) are (indexed("nombre_completo"))))

  on(tipoModeloVehiculo)(s => declare(
    s.id is (autoIncremented("move_id"))))

  on(subasta)(s => declare(
    s.id is (autoIncremented("suba_id"))))

  on(historialSubasta)(s => declare(
    s.id is (autoIncremented("hisu_id"))))

}
