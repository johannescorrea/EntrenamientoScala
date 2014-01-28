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

class RestServiceActor extends Actor with RestService {

  implicit def json4sFormats: Formats = DefaultFormats

  implicit def actorRefFactory = context //Optional implicit

  def receive = runRoute(restRoute)
}

trait RestService extends HttpService with Json4sSupport with SLF4JLogging {

  val logger = LoggerFactory.getLogger(getClass)
  implicit val executionContext = actorRefFactory.dispatcher

  val restRoute = path("") {
    get {
      complete {
        <html>
          <body>
            <h1>Este es una prueba uno <i>scala-spray-akka</i>!</h1>
          </body>
        </html>
      }
    }
  } ~
    path("vista" / Segment) { id =>
      get {
        complete(s"parametro ${id}")
      } ~
        post {
          complete(s"parametro ${id}")
        }
    } ~
    path("Id" / LongNumber) {
      Id =>
        get {
          complete {
            <html>
              <body>
                <h1>Este es una prueba dois id: { Id } <i>scala-spray-akka</i>!</h1>
              </body>
            </html>
          }
        }
    } ~
    path("getUsuario" / Segment) { Id =>
      get {
        complete {
          var usuario = Usuario("nefeper", "*****")
          usuario
        }
      } ~ options {
        complete {
          var usuario = Usuario("nefeper", "*****")
          usuario
        }
      }
    } ~
    path("addCustomer") {
      post {
        entity(as[JObject]) { usuarioObject =>
          respondWithMediaType(MediaTypes.`application/json`) {
            complete {
              //logger.info("addCustomer 1")
              //val usuario = usuarioObject.extract[Usuario]
              //logger.info("addCustomer 2")
              //insert customer information into a DB and return back customer obj
              //usuario
              usuarioObject.extract[Usuario]
            }
          }
        }
      }
    } ~
    path("enviar") {
      post {
        entity(as[Usuario]) {
          item =>
            respondWithMediaType(MediaTypes.`application/json`) {
              complete {
                item
              }
            }
        }
      }
    } 

}