package co.com.intergrupo.entities


import java.sql.Timestamp
import org.squeryl.KeyedEntity
import org.squeryl.annotations.Column
import org.squeryl.dsl.ManyToOne
import org.squeryl.dsl.OneToMany
import org.squeryl.PrimitiveTypeMode.transaction
import co.com.intergrupo.database.SchemaSubasta
import co.com.intergrupo.domain.AutoNoValidoException
import co.com.intergrupo.cxf._
import co.com.intergrupo.domain.SubastaException
import co.com.intergrupo.database.SubastaRepository
import java.util.Date
import java.util.Calendar

case class Subasta(@Column("SUBA_PLACA_VEHICULO") val placa: String, @Column("SUBA_ANHO") val anho: Int, @Column("SUBA_PRECIO") val precio: Long, @Column("SUBA_INCREMENTO_MINIMO") val incremento: Long, @Column("SUBA_KILOMETRAJE") val kilometraje: Long, @Column("SUBA_TRANSMICION") val transmicion: String, @Column("SUBA_LOCALIDAD") val localidad: String, @Column("SUBA_DIASVIGENCIA")val diasVigencia: Int) extends KeyedEntity[Long] {
  
  @Column("SUBA_ID")
  val id: Long = 0
  @Column("SUBA_FECHA_CREACION")
  var fechaCreacion = new Timestamp(System.currentTimeMillis)
  
  lazy val modelo: ManyToOne[ModeloVehiculo] = SchemaSubasta.modeloVehiculo.right(this)
  lazy val historial: OneToMany[HistorialSubasta] = SchemaSubasta.subastaTransaccion.left(this)
  lazy val personaVendedor: ManyToOne[Persona] = SchemaSubasta.vendedorSubasta.right(this)
  
  @throws[SubastaException]
  def adicionarOferta(oferta:HistorialSubasta, comprador:Persona) = {
    val ultimoValor: Long = if(historial.isEmpty) precio else historial.head.oferta
    if(oferta.oferta-ultimoValor < this.incremento) 
      throw new SubastaException("Oferta no supera el incremento minimo")
    
    if(!estaVigente) 
      throw new SubastaException("Subasta expirada")
      
    SubastaRepository.adicionarOferta(this, oferta, comprador)
  }
  
  private def calcularFechaLimite: Date = {
    var cal = Calendar.getInstance()
    cal.setTime(fechaCreacion)
    cal.add(Calendar.DATE, this.diasVigencia)
    cal.getTime()
  }
  
  def estaVigente: Boolean = {
    calcularFechaLimite.after(new Date())
  }

  
}

class SubastaFactory(val wsCliente:WebServiceImpl) {

  @throws[AutoNoValidoException]
  def crearSubasta(placa: String, anho: Int, precio: Long, incremento: Long, kilometraje: Long, transmision: String, localidad: String, vigencia: Int) = {
    if (!tieneRegistroRUNT(placa) || !estaAlDiaImpuestos(placa) || estaReportadoPolicia(placa: String))
      throw new AutoNoValidoException("")
    else
      new Subasta(placa, anho, precio, incremento, kilometraje, transmision, localidad, vigencia)
  }
  
  def tieneRegistroRUNT(placa: String): Boolean = {
    wsCliente.getWebServiceImplPort().registradoRunt(placa)
  }

  def estaAlDiaImpuestos(placa: String): Boolean = {
    wsCliente.getWebServiceImplPort().vehiculoAlDiaConImpuesto(placa)
  }

  def estaReportadoPolicia(placa: String): Boolean = {
    wsCliente.getWebServiceImplPort().vehiculoReportado(placa)
  }
}

object SubastaFactory {
  def apply(wsCliente: co.com.intergrupo.cxf.WebServiceImpl) = {
    new SubastaFactory(wsCliente)
  }
}