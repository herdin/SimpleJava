package com.harm.unit.data.xml.jaxb;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.bind.util.ValidationEventCollector;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.harm.unit.Unit;
import com.harm.unit.data.xml.jaxb.object.Message;
import com.harm.utils.FileUtils;

/**
 * JaxB Study for .xsd schema file
 */
public class JaxBStudy001 implements Unit {
	private Logger logger = LoggerFactory.getLogger(JaxBStudy001.class);
	
	@Override
	public Object execute(Object[] obj) throws Exception {
		String testDataFullPathStr = new File(this.getClass().getResource("jaxbTestXml02.xml").getPath()).getCanonicalPath();
		String schemaFullPathStr = this.getClass().getResource("schema/TestSchema.xsd").getPath();
		String xmlString = FileUtils.getTextFromFile(testDataFullPathStr);
		
		Message message = (Message) this.convertXmlToJaxb(Message.class, xmlString, schemaFullPathStr);
		logger.debug("marshal : {}, {}, {}", message.getMessageId(), message.getGateId(), message.getCardId());
		
		message.setMessageId("MOD:"+message.getMessageId());
		message.setGateId("MOD:"+message.getGateId());
		message.setCardId("MOD:"+message.getCardId());
		
		String messageStr = this.convertJaxbToXml(Message.class, message, schemaFullPathStr);
		logger.debug("unmarshall : {}", messageStr);
		return null;
	}//END OF FUNCTION
	
	private Object convertXmlToJaxb(Class<?> clazz, String xmlString, String schemaFullPath) {
		
		Object jaxbObj = null;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File(schemaFullPath));
			unMarshaller.setSchema(schema);
			unMarshaller.setEventHandler(new JAXBValidator());
			jaxbObj = unMarshaller.unmarshal(new StringReader(xmlString)); 
					
			if(jaxbObj == null) {
				throw new JAXBException("why null?");
			}
		} catch (JAXBException e) {
			this.logger.error(e.getMessage(), e);
		} catch (SAXException e) {
			this.logger.error(e.getMessage(), e);
		}
		
		return jaxbObj;
	}//END OF convertXmlToJaxb()
	
	private String convertJaxbToXml(Class<?> clazz, Object jaxbObj, String schemaFullPath) {
		String xmlString = null;
		StringWriter stringWriter = new StringWriter();
		
		try {
			
			Marshaller marshaller = JAXBContext.newInstance(clazz).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.toString());
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//			marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, schemaFullPath);
			marshaller.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(schemaFullPath)));
			marshaller.setEventHandler(new JAXBValidator());
			
//			stringWriter.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
			
			marshaller.marshal(jaxbObj, stringWriter);
			xmlString = stringWriter.toString();
			
		} catch (JAXBException e) {
			this.logger.error(e.getMessage(), e);
		} catch (SAXException e) {
			this.logger.error(e.getMessage(), e);
		} finally {
			try {
				stringWriter.close();
			} catch (IOException e) {
				this.logger.error(e.getMessage(), e);
			}
		}
		
		return xmlString;
	}//END OF convertJaxbToXml()

	class JAXBValidator extends ValidationEventCollector {
		private Logger logger = LoggerFactory.getLogger(JAXBValidator.class);
		
		@Override
	    public boolean handleEvent(ValidationEvent event) {
			switch (event.getSeverity()) {
				case ValidationEvent.WARNING :
					this.printInfo(event); //logging point
					break;
				case ValidationEvent.ERROR :
					this.printInfo(event); //logging point
					break;
				case ValidationEvent.FATAL_ERROR :
					this.printInfo(event); //logging point
					break;
				default:
					this.printInfo(event); //logging point
					break;
			}
			return false;
		}
		
		private void printInfo(ValidationEvent event) {
			ValidationEventLocator locator = event.getLocator();
			this.logger.debug("" + locator.getLineNumber());
			this.logger.debug("" + locator.getColumnNumber());
			this.logger.debug("" + event.getMessage());
		}
	}
}//END OF CLASS