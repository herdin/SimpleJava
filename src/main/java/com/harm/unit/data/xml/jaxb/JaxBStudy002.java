package com.harm.unit.data.xml.jaxb;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harm.unit.Unit;
import com.harm.unit.data.xml.jaxb.schema.SchemaObject002;
import com.harm.utils.FileUtils;

/**
 * JaxB Study for schema object
 */
public class JaxBStudy002 implements Unit {
	private Logger logger = LoggerFactory.getLogger(JaxBStudy002.class);
	
	@Override
	public Object execute(Object[] obj) throws Exception {
		String testDataFullPathStr = new File(this.getClass().getResource("jaxbTestXml02.xml").getPath()).getCanonicalPath();
		String xmlString = FileUtils.getTextFromFile(testDataFullPathStr);
		
		logger.debug("raw xml data {}", xmlString);
		
		JAXBContext context = JAXBContext.newInstance(SchemaObject002.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		SchemaObject002 message = (SchemaObject002)unmarshaller.unmarshal(new StringReader(xmlString));
		
		logger.debug("unmarshal : {}, {}, {}", message.getMessageId(), message.getGateId(), message.getCardId());
		
		message.setMessageId("MOD:"+message.getMessageId());
		message.setGateId("MOD:"+message.getGateId());
		message.setCardId("MOD:"+message.getCardId());
		
		StringWriter sw = new StringWriter();
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(message, sw);
		String marshallData = sw.toString();
		
		logger.debug("marshal data : {}", marshallData);
		return null;
	}//END OF FUNCTION
	
}//END OF CLASS