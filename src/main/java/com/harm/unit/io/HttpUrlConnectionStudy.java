package com.harm.unit.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harm.unit.Unit;

public class HttpUrlConnectionStudy implements Unit {
	private final Logger logger =  LoggerFactory.getLogger(HttpUrlConnectionStudy.class);

	@Override
	public void excute(Object[] obj) throws Exception {
		BufferedReader br = null;
		StringBuffer sb = null;
		try {
			URL url = new URL((String)obj[0]);
			HttpURLConnection hconn = (HttpURLConnection) url.openConnection();
			hconn.setRequestMethod("GET");
//			hconn.setRequestProperty("Accept-Charset", "UTF-8");
			hconn.connect();
			
			br = new BufferedReader(new InputStreamReader(hconn.getInputStream(), Charset.forName("UTF-8")));
			sb = new StringBuffer();
			String line = null;
			do {
				line = br.readLine();
				sb.append(line);
			} while(line != null);
			
			logger.debug(sb.toString());
			
		} catch(Exception e ) {
			
		}
		
	}//END OF FUNCTION
	
}//END OF CLASS
