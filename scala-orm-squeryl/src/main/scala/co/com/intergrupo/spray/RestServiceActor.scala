package co.com.intergrupo.spray

import akka.actor.Actor
import akka.event.slf4j.SLF4JLogging
import spray.http.MediaTypes
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply
import spray.routing.HttpService
import spray.http.StatusCode
import spray.http.StatusCodes
import spray.routing.RequestContext
import spray.httpx.Json4sSupport
import co.com.intergrupo.entities.Usuario
import scala.concurrent.{ ExecutionContext, Future }
import spray.httpx.marshalling.Marshaller
import scala.concurrent.ExecutionContext.Implicits.global
import spray.json.{ JsValue, JsString, JsonFormat, DefaultJsonProtocol }
import java.util.{ GregorianCalendar, Date }
import javax.xml.bind.DatatypeConverter
import spray.httpx.SprayJsonSupport
import scala.concurrent.ExecutionContext
import org.json4s.Formats
import org.json4s.DefaultFormats
import org.json4s.JsonAST.JObject
import scala.concurrent.ExecutionContext.Implicits.global
import org.slf4j.LoggerFactory
import spray.http.HttpHeaders.RawHeader

class RestServiceActor extends Actor with RestService {

  implicit def json4sFormats: Formats = DefaultFormats

  implicit def actorRefFactory = context //Optional implicit

  def receive = runRoute(restRoute)
}

trait RestService extends HttpService with Json4sSupport with SLF4JLogging {

  val logger = LoggerFactory.getLogger(getClass)
  implicit val executionContext = actorRefFactory.dispatcher

  def cross(origin: String) = respondWithHeaders(
    RawHeader("Access-Control-Allow-Origin", origin),
    RawHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"),
    RawHeader("Access-Control-Allow-Headers", "X-Requested-With, Cache-Control, Pragma, Origin, Authorization, Content-Type"),
    RawHeader("Access-Control-Max-Age", "86400"),
    RawHeader("Content-Type", "application/json"))

  def crossDomain = cross("*")

  val restRoute = path("deleteUsuario" / LongNumber) {
    Id =>
      get {
        complete {
          var usuario = Usuario("nefeper" + Id, "*****")
          usuario
        }
      }
  } ~
    path("getUsuario" / Segment) { Id =>
      respondWithMediaType(MediaTypes.`application/json`) {
        get {
          crossDomain {
            complete {
              var usuario = Usuario("nefeper" + Id, "*****")
              usuario
            }
          }
        } ~ post {
          complete {
            var usuario = Usuario("nefeper" + Id, "*****")
            usuario
          }
        }
      }
    } ~
    path("saveUsuario") {
      post {
        entity(as[JObject]) { usuarioObject =>
          complete {
            logger.info("addCustomer 1")
            usuarioObject.extract[Usuario]
          }
        }
      }
    } ~
    path("addUsuario") {
      post {
        respondWithMediaType(MediaTypes.`application/json`) {
          crossDomain {
            entity(as[Usuario]) {
              item =>
                complete {
                  item
                }
            }
          }
        }
      }
    }

}