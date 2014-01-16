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
import net.liftweb.json.Formats
import net.liftweb.json.DefaultFormats

object RestServiceActor {
  // case class Register(user: User)

  case object Registered

  case object NotRegistered
}

class RestServiceActor extends Actor with RestService {

  implicit def actorRefFactory = context //Optional implicit

  def receive = runRoute(restRoute)
}

trait RestService extends HttpService with SLF4JLogging {

  implicit val executionContext = actorRefFactory.dispatcher

  val restRoute =
    respondWithMediaType(MediaTypes.`text/html`) {
      path("") {
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
        }
    } ~
      pathPrefix("Angular") {
        {
          getFromDirectory("./src/webapp/")
        }
      }

}