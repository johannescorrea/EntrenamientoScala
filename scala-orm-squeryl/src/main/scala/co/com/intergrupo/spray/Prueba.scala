package co.com.intergrupo.spray

import spray.util._
import spray.http._
import spray.httpx.marshalling._
import spray.httpx.unmarshalling._
import spray.http.ContentType.apply
import spray.httpx.RequestBuilding.Get

case class Person(name: String, firstName: String, age: Int)

object Person {
  implicit val PersonMarshaller =
    Marshaller.of[Person](MediaTypes.`application/json`) { (value, contentType, ctx) =>
      val Person(name, first, age) = value
      val string = "Person: %s, %s, %s".format(name, first, age)
      ctx.marshalTo(HttpEntity(contentType, string))
    }

  def main(args: Array[String]) {
    // println(marshal(Person("Bob", "Parr", 32)) == Right(HttpEntity(MediaTypes.`application/json`, "Person: Bob, Parr, 32")))

    import spray.httpx.RequestBuilding._
    import spray.http._
    import HttpMethods._
    import HttpHeaders._
    import ContentTypes._

    // simple GET requests
   // Get() == HttpRequest(method = GET)
    //Get("/getUsuario/123") == HttpRequest(method = GET, uri = "/getUsuario/123")
    println("hola")
    println(Get("/getUsuario/123"))
/*
    // as second argument you can specify an object that is
    // to be marshalled using the in-scope marshaller for the type
    Put("/abc", "foobar") == HttpRequest(method = PUT, uri = "/abc", entity = "foobar")

    implicit val intMarshaller = Marshaller.of[Int](`application/json`) {
      (value, ct, ctx) => ctx.marshalTo(HttpEntity(ct, s"{ value: $value }"))
    }
    Post("/int", 42) == HttpRequest(method = POST, uri = "/int",
      entity = HttpEntity(`application/json`, "{ value: 42 }"))

    // add one or more headers by chaining in the `addHeader` modifier
    Patch("/abc", "content") ~> addHeader("X-Yeah", "Naah") == HttpRequest(
      method = PATCH,
      uri = "/abc",
      entity = "content",
      headers = List(RawHeader("X-Yeah", "Naah")))
*/
  }
}



