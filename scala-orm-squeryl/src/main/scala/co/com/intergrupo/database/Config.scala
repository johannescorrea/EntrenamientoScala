package co.com.intergrupo.database

import com.typesafe.config.ConfigFactory
import util.Try

trait Config {
  val config = ConfigFactory.load()
  lazy val driver = config.getString("db.driver")
  lazy val url = Try(config.getString("db.url")).toOption.orNull
  lazy val userName = Try(config.getString("db.userName")).toOption.orNull
  lazy val password = Try(config.getString("db.password")).toOption.orNull
}
