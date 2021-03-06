package com.harm.unit.lang.io.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harm.unit.Unit;

public class HttpUrlConnectionStudy01 implements Unit {
	private final Logger logger =  LoggerFactory.getLogger(HttpUrlConnectionStudy01.class);

	@Override
	public Object execute(Object[] obj) throws Exception {
		BufferedReader br = null;
		StringBuffer sb = null;
		Boolean result = Boolean.TRUE;
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
				do {
					line = br.readLine();
					sb.append(line);
				} while(line != null);
				
				logger.debug(sb.toString());
			}
			
		} catch(Exception e ) {
			logger.error(e.getMessage());
			result = Boolean.FALSE;
		} finally {
			if(br != null)
				br.close();
		}

		return result;
		
	}//END OF FUNCTION
	
}//END OF CLASS
