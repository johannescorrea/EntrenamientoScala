package co.com.intergrupo.cxf;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.8 2014-01-20T16:01:43.186-05:00
 * Generated source version: 2.7.8
 * 
 */
@WebServiceClient(name = "WebServiceImpl", wsdlLocation = "http://localhost:8090/jaxws-jaxrs/WebServiceImpl?wsdl", targetNamespace = "http://jaxws.intergrupo.com.co/")
public class WebServiceImpl extends Service {

  public final static URL WSDL_LOCATION;

  public final static QName SERVICE = new QName("http://jaxws.intergrupo.com.co/", "WebServiceImpl");
  public final static QName WebServiceImplPort = new QName("http://jaxws.intergrupo.com.co/", "WebServiceImplPort");
  static {
    URL url = null;
    try {
      url = new URL("http://localhost:8090/jaxws-jaxrs/WebServiceImpl?wsdl");
    } catch (MalformedURLException e) {
      java.util.logging.Logger.getLogger(WebServiceImpl.class.getName()).log(java.util.logging.Level.INFO, "Can not initialize the default wsdl from {0}", "http://localhost:8090/jaxws-jaxrs/WebServiceImpl?wsdl");
    }
    WSDL_LOCATION = url;
  }

  public WebServiceImpl(URL wsdlLocation) {
    super(wsdlLocation, SERVICE);
  }

  public WebServiceImpl(URL wsdlLocation, QName serviceName) {
    super(wsdlLocation, serviceName);
  }

  public WebServiceImpl() {
    super(WSDL_LOCATION, SERVICE);
  }

  /**
   * 
   * @return returns InterfaceWebService
   */
  @WebEndpoint(name = "WebServiceImplPort")
  public InterfaceWebService getWebServiceImplPort() {
    return super.getPort(WebServiceImplPort, InterfaceWebService.class);
  }

  /**
   * 
   * @param features
   *          A list of {@link javax.xml.ws.WebServiceFeature} to configure on
   *          the proxy. Supported features not in the <code>features</code>
   *          parameter will have their default values.
   * @return returns InterfaceWebService
   */
  @WebEndpoint(name = "WebServiceImplPort")
  public InterfaceWebService getWebServiceImplPort(WebServiceFeature... features) {
    return super.getPort(WebServiceImplPort, InterfaceWebService.class, features);
  }

}
