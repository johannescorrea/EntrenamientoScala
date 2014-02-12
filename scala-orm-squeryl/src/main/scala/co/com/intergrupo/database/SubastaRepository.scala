package co.com.intergrupo.database

import org.squeryl.PrimitiveTypeMode.transaction
import org.squeryl.PrimitiveTypeMode._
import co.com.intergrupo.entities.ModeloVehiculo
import co.com.intergrupo.entities.Persona
import co.com.intergrupo.entities.Subasta
import co.com.intergrupo.entities.HistorialSubasta

object SubastaRepository extends TraitConnection {

  connect

  def registrarSubasta(subasta: Subasta, vendedor: Persona, tipoVehiculo: ModeloVehiculo) = {
    inTransaction {
      if (!vendedor.isPersisted) {
        SchemaSubasta.persona.insert(vendedor)
      }

      if (!tipoVehiculo.isPersisted) {
        SchemaSubasta.tipoModeloVehiculo.insert(tipoVehiculo)
      }

      subasta.personaVendedor.assign(vendedor)
      subasta.modelo.assign(tipoVehiculo)
      SchemaSubasta.subasta.insert(subasta)
    }
  }

  def buscarSubasta(id: Long): Subasta = {
    inTransaction {
      SchemaSubasta.subasta.where(sub => sub.id === id).single
    }
  }

  def adicionarOferta(subasta: Subasta, oferta: HistorialSubasta, comprador: Persona) {
    inTransaction {
      oferta.personaComprador.assign(comprador)
      subasta.historial.associate(oferta)
    }
  }
}