package com.harm.unit.data.xml.jaxb;

import com.harm.unit.Unit;
import com.harm.unit.data.xml.jaxb.schema.SchemaObject003;
import com.harm.unit.data.xml.jaxb.schema.SchemaObject003001;
import com.harm.unit.data.xml.jaxb.schema.SchemaObject003002;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * JaxB Study for schema object with complex type and build pattern
 */
public class JaxBStudy003 implements Unit {
	private Logger logger = LoggerFactory.getLogger(JaxBStudy003.class);
	
	@Override
	public Object execute(Object[] obj) throws Exception {
		JAXBContext context = JAXBContext.newInstance(SchemaObject003.class);
		StringWriter sw = new StringWriter();
		Marshaller marshaller = context.createMarshaller();

		SchemaObject003 schemaObjec003 = new SchemaObject003
				.Builder()
				.id("root-id")
				.name("root-name")
				.alias("root-alias")
				.innerObject01(new SchemaObject003001
						.Builder()
						.innerId("inner-id1")
						.innerName("inner-name1")
						.innerAlias("inner-alias1")
						.build()
				)
				.innerOBject02(new SchemaObject003002(new String[]{"inner-string1", "inner-string2", "inner-string3"}))
				.build();
		marshaller.marshal(schemaObjec003, sw);
		String marshallData = sw.toString();

		logger.debug("marshal data : {}", marshallData);


		return null;
	}//END OF FUNCTION
	
}//END OF CLASS