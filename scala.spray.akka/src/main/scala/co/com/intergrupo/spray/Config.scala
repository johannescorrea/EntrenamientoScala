package co.com.intergrupo.spray

import com.typesafe.config.ConfigFactory
import util.Try

trait Config {
  val config = ConfigFactory.load()
  lazy val serviceHost = Try(config.getString("service.host")).getOrElse("localhost")
  lazy val servicePort = Try(config.getInt("service.port")).getOrElse(8090)
  lazy val dbHost = Try(config.getString("db.host")).getOrElse("localhost")
  lazy val dbPort = Try(config.getInt("db.port")).getOrElse(1521)
  lazy val dbName = Try(config.getString("db.name")).getOrElse("xe")
  lazy val dbUser = Try(config.getString("db.user")).toOption.orNull
  lazy val dbPassword = Try(config.getString("db.password")).toOption.orNull
}
