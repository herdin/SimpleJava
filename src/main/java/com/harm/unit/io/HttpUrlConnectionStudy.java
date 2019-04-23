package com.harm.unit.io;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.ws.spi.http.HttpContext;

import com.harm.unit.Unit;

public class HttpUrlConnectionStudy implements Unit {

	@Override
	public void excute(Object[] obj) throws Exception {
		BufferedReader br = null;
		StringBuffer sb = null;
		try {
			URL url = new URL((String)obj[0]);
			HttpURLConnection hconn = (HttpURLConnection) url.openConnection();
			hconn.setRequestMethod("GET");
			hconn.connect();
			
		} catch(Exception e ) {
			
		}
		
	}//END OF FUNCTION
	
}//END OF CLASS
