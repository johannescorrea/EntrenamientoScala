
package co.com.intergrupo.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.com.intergrupo.cxf package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SaludoResponse_QNAME = new QName("http://jaxws.intergrupo.com.co/", "saludoResponse");
    private final static QName _RegistradoRuntResponse_QNAME = new QName("http://jaxws.intergrupo.com.co/", "registradoRuntResponse");
    private final static QName _RegistradoRunt_QNAME = new QName("http://jaxws.intergrupo.com.co/", "registradoRunt");
    private final static QName _Saludo_QNAME = new QName("http://jaxws.intergrupo.com.co/", "saludo");
    private final static QName _VehiculoReportado_QNAME = new QName("http://jaxws.intergrupo.com.co/", "vehiculoReportado");
    private final static QName _VehiculoAlDiaConImpuestoResponse_QNAME = new QName("http://jaxws.intergrupo.com.co/", "vehiculoAlDiaConImpuestoResponse");
    private final static QName _Vehiculo_QNAME = new QName("http://intergrupo.com.co/vehiculos", "vehiculo");
    private final static QName _ConsultarVehiculo_QNAME = new QName("http://jaxws.intergrupo.com.co/", "consultarVehiculo");
    private final static QName _VehiculoReportadoResponse_QNAME = new QName("http://jaxws.intergrupo.com.co/", "vehiculoReportadoResponse");
    private final static QName _ConsultarVehiculoResponse_QNAME = new QName("http://jaxws.intergrupo.com.co/", "consultarVehiculoResponse");
    private final static QName _VehiculoAlDiaConImpuesto_QNAME = new QName("http://jaxws.intergrupo.com.co/", "vehiculoAlDiaConImpuesto");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.com.intergrupo.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Vehiculo }
     * 
     */
    public Vehiculo createVehiculo() {
        return new Vehiculo();
    }

    /**
     * Create an instance of {@link SaludoResponse }
     * 
     */
    public SaludoResponse createSaludoResponse() {
        return new SaludoResponse();
    }

    /**
     * Create an instance of {@link RegistradoRuntResponse }
     * 
     */
    public RegistradoRuntResponse createRegistradoRuntResponse() {
        return new RegistradoRuntResponse();
    }

    /**
     * Create an instance of {@link RegistradoRunt }
     * 
     */
    public RegistradoRunt createRegistradoRunt() {
        return new RegistradoRunt();
    }

    /**
     * Create an instance of {@link Saludo }
     * 
     */
    public Saludo createSaludo() {
        return new Saludo();
    }

    /**
     * Create an instance of {@link VehiculoReportado }
     * 
     */
    public VehiculoReportado createVehiculoReportado() {
        return new VehiculoReportado();
    }

    /**
     * Create an instance of {@link VehiculoAlDiaConImpuestoResponse }
     * 
     */
    public VehiculoAlDiaConImpuestoResponse createVehiculoAlDiaConImpuestoResponse() {
        return new VehiculoAlDiaConImpuestoResponse();
    }

    /**
     * Create an instance of {@link ConsultarVehiculo }
     * 
     */
    public ConsultarVehiculo createConsultarVehiculo() {
        return new ConsultarVehiculo();
    }

    /**
     * Create an instance of {@link VehiculoReportadoResponse }
     * 
     */
    public VehiculoReportadoResponse createVehiculoReportadoResponse() {
        return new VehiculoReportadoResponse();
    }

    /**
     * Create an instance of {@link ConsultarVehiculoResponse }
     * 
     */
    public ConsultarVehiculoResponse createConsultarVehiculoResponse() {
        return new ConsultarVehiculoResponse();
    }

    /**
     * Create an instance of {@link VehiculoAlDiaConImpuesto }
     * 
     */
    public VehiculoAlDiaConImpuesto createVehiculoAlDiaConImpuesto() {
        return new VehiculoAlDiaConImpuesto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaludoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.intergrupo.com.co/", name = "saludoResponse")
    public JAXBElement<SaludoResponse> createSaludoResponse(SaludoResponse value) {
        return new JAXBElement<SaludoResponse>(_SaludoResponse_QNAME, SaludoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistradoRuntResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.intergrupo.com.co/", name = "registradoRuntResponse")
    public JAXBElement<RegistradoRuntResponse> createRegistradoRuntResponse(RegistradoRuntResponse value) {
        return new JAXBElement<RegistradoRuntResponse>(_RegistradoRuntResponse_QNAME, RegistradoRuntResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistradoRunt }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.intergrupo.com.co/", name = "registradoRunt")
    public JAXBElement<RegistradoRunt> createRegistradoRunt(RegistradoRunt value) {
        return new JAXBElement<RegistradoRunt>(_RegistradoRunt_QNAME, RegistradoRunt.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Saludo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.intergrupo.com.co/", name = "saludo")
    public JAXBElement<Saludo> createSaludo(Saludo value) {
        return new JAXBElement<Saludo>(_Saludo_QNAME, Saludo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VehiculoReportado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.intergrupo.com.co/", name = "vehiculoReportado")
    public JAXBElement<VehiculoReportado> createVehiculoReportado(VehiculoReportado value) {
        return new JAXBElement<VehiculoReportado>(_VehiculoReportado_QNAME, VehiculoReportado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VehiculoAlDiaConImpuestoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.intergrupo.com.co/", name = "vehiculoAlDiaConImpuestoResponse")
    public JAXBElement<VehiculoAlDiaConImpuestoResponse> createVehiculoAlDiaConImpuestoResponse(VehiculoAlDiaConImpuestoResponse value) {
        return new JAXBElement<VehiculoAlDiaConImpuestoResponse>(_VehiculoAlDiaConImpuestoResponse_QNAME, VehiculoAlDiaConImpuestoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vehiculo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://intergrupo.com.co/vehiculos", name = "vehiculo")
    public JAXBElement<Vehiculo> createVehiculo(Vehiculo value) {
        return new JAXBElement<Vehiculo>(_Vehiculo_QNAME, Vehiculo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarVehiculo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.intergrupo.com.co/", name = "consultarVehiculo")
    public JAXBElement<ConsultarVehiculo> createConsultarVehiculo(ConsultarVehiculo value) {
        return new JAXBElement<ConsultarVehiculo>(_ConsultarVehiculo_QNAME, ConsultarVehiculo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VehiculoReportadoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.intergrupo.com.co/", name = "vehiculoReportadoResponse")
    public JAXBElement<VehiculoReportadoResponse> createVehiculoReportadoResponse(VehiculoReportadoResponse value) {
        return new JAXBElement<VehiculoReportadoResponse>(_VehiculoReportadoResponse_QNAME, VehiculoReportadoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarVehiculoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.intergrupo.com.co/", name = "consultarVehiculoResponse")
    public JAXBElement<ConsultarVehiculoResponse> createConsultarVehiculoResponse(ConsultarVehiculoResponse value) {
        return new JAXBElement<ConsultarVehiculoResponse>(_ConsultarVehiculoResponse_QNAME, ConsultarVehiculoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VehiculoAlDiaConImpuesto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.intergrupo.com.co/", name = "vehiculoAlDiaConImpuesto")
    public JAXBElement<VehiculoAlDiaConImpuesto> createVehiculoAlDiaConImpuesto(VehiculoAlDiaConImpuesto value) {
        return new JAXBElement<VehiculoAlDiaConImpuesto>(_VehiculoAlDiaConImpuesto_QNAME, VehiculoAlDiaConImpuesto.class, null, value);
    }

}
