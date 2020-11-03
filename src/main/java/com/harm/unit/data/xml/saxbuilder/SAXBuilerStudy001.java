package com.harm.unit.data.xml.saxbuilder;

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
	public Object execute(Object[] obj) throws Exception {
		SAXBuilder saxBuilder = new SAXBuilder();


	this.logger.debug("path {}", this.getClass().getResource(""));
		Document document = saxBuilder.build(this.getClass().getResource("saxTestXml02.xml").getPath());
		Element rootElement = document.getRootElement();
		logger.debug(rootElement.getName());
		List<Element> elements = rootElement.getChildren();
		for(Element element : elements) {
			logger.debug(element.getName());
		}

//			Element pokemon = new Element("pokemon");
//			Document doc = new Document(pokemon);
//			ArrayList<String> pokemons = new ArrayList<>();
//			pokemons.add("aaaa1");
//			pokemons.add("aaaa2");
//			pokemons.add("aaaa3");
//			pokemons.add("aaaa4");
//			for(String p:  pokemons) {
//				Element poke = new Element("pokemon");
//				poke.addContent(new Element("nombre").setText("\n" + p));
//				doc.getRootElement().addContent(poke);
//			}
//			XMLOutputter output = new XMLOutputter();
//			Format format = Format.getPrettyFormat();
////			format.setExpandEmptyElements(false);
//			format.setTextMode(Format.TextMode.TRIM_FULL_WHITE);
//			output.setFormat(format);
//			output.output(doc, new FileWriter("pokemons-tipo.xml"));
		return null;
	}//END OF FUNCTION

}//END OF CLASS