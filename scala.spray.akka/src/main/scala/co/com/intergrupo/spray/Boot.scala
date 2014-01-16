package co.com.intergrupo.spray

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.io.IO
import spray.can.Http

object Boot extends App with Config {
  
  // comentario d

  implicit val system = ActorSystem("scala-spray-akka-angular")

  val service = system.actorOf(Props[RestServiceActor], "RestServiceActor")

  IO(Http) ! Http.Bind(service, interface = serviceHost, port = servicePort)
}