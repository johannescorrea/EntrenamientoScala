package co.com.intergrupo.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.com.intergrupo.dto.Libro;
import co.com.intergrupo.dto.Vehiculo;
import co.com.intergrupo.facade.Facade;

@Path("/json")
public class RestServiceJson {

  @GET
  @Path("saludo/{param}")
  public Response saludo(@PathParam("param") String nombre) {
    return Response.status(200).entity("Hola " + nombre + ", como estas?").build();
  }

  @GET
  @Path("consultarVehiculo/{param}")
  @Produces(MediaType.APPLICATION_JSON)
  public Vehiculo consultarVehiculo(@PathParam("param") String placa) {
    return Facade.getInstance().consultarVehiculo(placa);
  }

  @GET
  @Path("vehiculoReportado/{param}")
  @Produces(MediaType.APPLICATION_JSON)
  public boolean vehiculoReportado(@PathParam("param") String placa) {
    return Facade.getInstance().vehiculoReportado(placa);
  }

  @GET
  @Path("registradoRunt/{param}")
  @Produces(MediaType.APPLICATION_JSON)
  public boolean registradoRunt(@PathParam("param") String placa) {
    return Facade.getInstance().registradoRunt(placa);
  }

  @GET
  @Path("vehiculoAlDiaConImpuesto/{param}")
  @Produces(MediaType.APPLICATION_JSON)
  public boolean vehiculoAlDiaConImpuesto(@PathParam("param") String placa) {
    return Facade.getInstance().vehiculoAlDiaConImpuesto(placa);
  }

  @POST
  @Path("/add")
  public Response add(@FormParam("nombre") String nombre, @FormParam("edad") int edad) {
    return Response.status(200).entity("add nombre : " + nombre + ", edad : " + edad).build();
  }

  @GET
  @Path("/get")
  @Produces(MediaType.APPLICATION_JSON)
  public Libro getLibro() {
    Libro libro = new Libro();
    libro.setId(1);
    libro.setTitulo("El Juego de Ender");
    libro.setAutor("Orson Scott Card");
    libro.setEditorial("Ediciones B / Zeta");
    libro
        .setDescripcion("La Tierra está amenazada por una especie extraterrestre de insectos que pretende destruir la humanidad. Para vencerlos se precisa la intervención de un genio militar, por lo cual se permite el nacimiento de Ender, tercer hijo de una pareja en un mundo que limita a dos el número de descendientes. Ender se entrenará en una estación espacial, superará a sus rivales y se convertirá en la persona capaz de dirigir las flotas terrestres contra los insectos de otros mundos.");
    libro.setImg("img/img1.jpg");
    return libro;
  }

  @POST
  @Path("/post")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response crearLibro(Libro libro) {
    String result = "Libro saved : " + libro;
    return Response.status(201).entity(result).build();
  }

}