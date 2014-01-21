package co.com.intergrupo.utils;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SaxErrorHandler implements ErrorHandler {

  private boolean validate = true;

  public void warning(SAXParseException exception) throws SAXException {
    System.out.println("\nWARNING");
  }

  public void error(SAXParseException exception) throws SAXException {
    validate = false;
  }

  public void fatalError(SAXParseException exception) throws SAXException {
    validate = false;
  }

  public boolean isValidate() {
    return validate;
  }

}