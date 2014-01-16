package co.com.intergrupo.database

import co.com.intergrupo.entities._
import org.squeryl._
import org.squeryl.PrimitiveTypeMode._

object Subasta extends Schema {

  val usuario = table[Usuario]("usuario")

  val persona = table[Persona]("persona")

  on(usuario)(user => declare(
    user.id is (autoIncremented("usua_id")),
    user.email is (unique)))

  on(persona)(s => declare(
    s.id is (autoIncremented("pers_id")),
    s.email is (unique, dbType("varchar(60)")),
    columns(s.nombres, s.apellidos) are (indexed("nombre_completo"))))

}
