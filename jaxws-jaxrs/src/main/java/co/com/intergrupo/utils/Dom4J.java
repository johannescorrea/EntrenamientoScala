package co.com.intergrupo.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4J {

	private Log log = LogFactory.getLog(Dom4J.class);

	public Document getDocument(File xml) {
		Document document = null;
		try {
			// SAXParserFactory factory = SAXParserFactory.newInstance();
			// SchemaFactory schemaFactory =
			// SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			// factory.setSchema(schemaFactory.newSchema(new Source[] { new
			// StreamSource(xml.getAbsolutePath().replace(".xml", ".xsd")) }));
			// SAXParser parser = factory.newSAXParser();
			SAXReader reader = new SAXReader();// parser.getXMLReader()
			// reader.setValidation(false);
			reader.setErrorHandler(new SaxErrorHandler());
			document = reader.read(xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return document;
	}

	public Document getDocumento(File xml) {
		Document document = null;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			factory.setSchema(schemaFactory.newSchema(new File(xml
					.getAbsolutePath().replace(".xml", ".xsd"))));
			SAXParser parser = factory.newSAXParser();
			SAXReader reader = new SAXReader(parser.getXMLReader());
			reader.setValidation(true);
			reader.setErrorHandler(new SaxErrorHandler());
			document = reader.read(xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return document;
	}

	public void writeXml(File xml, Document document) {
		XMLWriter writer = null;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter(new FileWriter(xml), format);
			writer.write(document);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}
}
