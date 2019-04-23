package com.harm.unit.rss;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harm.unit.Unit;
import com.harm.unit.io.HttpUrlConnectionStudy01;

public class RssXmlParseStudy001 implements Unit {
	private final Logger logger =  LoggerFactory.getLogger(RssXmlParseStudy001.class);
	
	@Override
	public void excute(Object[] obj) throws Exception {
		BufferedReader br = null;
		StringBuffer sb = null;
		try {
			
			int httpResponseCode = -1;
			URL url = new URL((String)obj[0]);
			HttpURLConnection hconn = (HttpURLConnection) url.openConnection();
			hconn.setRequestMethod("GET");
			hconn.connect();
			
			httpResponseCode = hconn.getResponseCode();
			logger.debug("HTTP RESPONSE CODE : {}", httpResponseCode);
			
			if(httpResponseCode == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(hconn.getInputStream(), Charset.forName("UTF-8")));
				sb = new StringBuffer();
				String line = null;
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
				
				String rssData = sb.toString();
//				rssData = rssData.substring(0, rssData.lastIndexOf(">")+1);
				logger.debug("RSS DATA : {}", rssData);
				
				JAXBContext context = JAXBContext.newInstance(RssShemaObject.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				RssShemaObject rssShemaObject = (RssShemaObject) unmarshaller.unmarshal(new StringReader(rssData));
				logger.debug("PARSED RSS DATA LENGTH : {}", rssShemaObject.getChannel().getItems().size());
				for(ItemShemaObject item : rssShemaObject.getChannel().getItems()) {
					logger.debug(item.getTitle());
				}
//				SAXBuilder saxBuilder = new SAXBuilder();
//				Document document = saxBuilder.build(new StringReader(rssData));
//				Element rootElement = document.getRootElement();
//				logger.debug(rootElement.getName());
				
			}
			
		} catch(Exception e ) {
			logger.error(e.getMessage());
		} finally {
			if(br != null)
				br.close();
		}
	}//END OF FUNCTION

}//END OF CLASS