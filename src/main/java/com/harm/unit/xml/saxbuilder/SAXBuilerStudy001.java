package com.harm.unit.xml.saxbuilder;

import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harm.unit.Unit;

public class SAXBuilerStudy001 implements Unit {
	private Logger logger = LoggerFactory.getLogger(SAXBuilder.class);
	
	@Override
	public void excute(Object[] obj) throws Exception {
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(this.getClass().getResource("saxTestXml02.xml").getPath());
		Element rootElement = document.getRootElement();
		logger.debug(rootElement.getName());
		List<Element> elements = rootElement.getChildren();
		for(Element element : elements) {
			logger.debug(element.getName());
		}
	}//END OF FUNCTION

}//END OF CLASS