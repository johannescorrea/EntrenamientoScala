package co.com.intergrupo.database

import org.squeryl.PrimitiveTypeMode.long2ScalarLong
import org.squeryl.PrimitiveTypeMode.oneToManyRelation
import org.squeryl.PrimitiveTypeMode.string2ScalarString
import org.squeryl.Schema

import co.com.intergrupo.entities._

object SchemaSubasta extends Schema {

  val usuario = table[Usuario]("Usuario")

  val config = table[Config]("Config")

  val persona = table[Persona]("persona")

  val tipoModeloVehiculo = table[ModeloVehiculo]("MODELO_VEHICULO")

  val subasta = table[Subasta]("SUBASTA")

  val historialSubasta = table[HistorialSubasta]("HISTORIAL_SUBASTA")

  val vendedorSubasta = oneToManyRelation(persona, subasta).via((p, s) => p.id === s.id)

  val modeloVehiculo = oneToManyRelation(tipoModeloVehiculo, subasta).via((tmv, s) => tmv.id === s.id)

  val compradorSubasta = oneToManyRelation(persona, historialSubasta).via((p, hs) => p.id === hs.id)

  val subastaTransaccion = oneToManyRelation(subasta, historialSubasta).via((s, hs) => s.id === hs.id)

  on(config)(s => declare(
    s.id is (autoIncremented("config_id"))))

  on(usuario)(u => declare(
    u.id is (autoIncremented("usua_id")),
    u.email is (dbType("varchar(60)"))))

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
