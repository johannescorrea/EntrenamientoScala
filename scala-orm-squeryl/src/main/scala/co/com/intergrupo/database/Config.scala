package co.com.intergrupo.database

import com.typesafe.config.ConfigFactory
import util.Try
import com.mchange.v2.c3p0.ComboPooledDataSource
import org.squeryl.adapters.{ H2Adapter, OracleAdapter }
import org.squeryl.Session
import org.squeryl.SessionFactory
import org.slf4j.LoggerFactory

trait TraitConnection {
  val logger = LoggerFactory.getLogger(getClass)
  val config = ConfigFactory.load()
  lazy val driver = config.getString("db.driver")
  lazy val url = Try(config.getString("db.url")).toOption.orNull
  lazy val userName = Try(config.getString("db.userName")).toOption.orNull
  lazy val password = Try(config.getString("db.password")).toOption.orNull
  var ds = new ComboPooledDataSource

  def connect() {
    ds.setDriverClass(driver)
    ds.setJdbcUrl(url)
    ds.setUser(userName)
    ds.setPassword(password)
    ds.setMinPoolSize(1)
    ds.setAcquireIncrement(1)
    ds.setMaxPoolSize(50)
    logger.info("creando connection pool")
    SessionFactory.concreteFactory = Some(driver) match {
      case Some("org.h2.Driver") => Some(() => Session.create(ds.getConnection, new H2Adapter))
      case Some("oracle.jdbc.driver.OracleDriver") => Some(() => Session.create(ds.getConnection, new OracleAdapter))
      case _ => sys.error("Soporta solo para los drivers:  org.h2.Driver o oracle.jdbc.driver.OracleDriver")
    }
  }

  def close() {
    logger.info("close connection pool")
    ds.close()
  }
}