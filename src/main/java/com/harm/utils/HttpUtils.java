package com.harm.utils;

import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpStatusClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private static final Charset POST_SEND_BYTE_CHARSET = Charset.forName("UTF-8");

    public static String sendAndRecv(
            String requestUrl
            , HttpMethod httpMethod
            , String paramStr
    ) {
        return HttpUtils.sendAndRecv(requestUrl, httpMethod, null, null, paramStr);
    }//END OF FUNCTION

    public static String sendAndRecv(
            String requestUrl
            , HttpMethod httpMethod
            , String[] headerKeys
            , String[] headerValues
            , String paramStr
    ) {
        String targetUrl = requestUrl;
        URL url = null;
        HttpURLConnection conn = null;
        String recvStr = null;

        try {
            logger.debug("connect to : " + targetUrl + " : method : " + httpMethod.name() + " : param : " + paramStr);

            if(HttpMethod.GET.name().equals(httpMethod.name())) {
                targetUrl = targetUrl + "?" + paramStr;
            }

            url = new URL(targetUrl);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod(httpMethod.name());

            if(headerKeys != null) {
                for(int i=0; i<headerKeys.length; i++) {
                    conn.setRequestProperty(headerKeys[i], headerValues[i]);
                }
            }

            if(HttpMethod.GET.name().equals(httpMethod.name())) {
                ;
            } else if(HttpMethod.POST.name().equals(httpMethod.name())) {
                if(paramStr == null)
                    paramStr = "";
                byte[] paramBytes = paramStr.getBytes(HttpUtils.POST_SEND_BYTE_CHARSET);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length", String.valueOf(paramBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(paramBytes);
            } else {
                throw new IOException("NOT SUPPORT HTTP METHOD");
            }
            int responseCode = conn.getResponseCode();
            BufferedReader br;
            logger.debug("responseCode : " + responseCode);
            logger.debug("responseHeader : ");
            Map<String, List<String>> keysMap = conn.getHeaderFields();
            Iterator<String> keysIter = keysMap.keySet().iterator();
            while(keysIter.hasNext()) {
                String key = keysIter.next();
                List<String> values = keysMap.get(key);
                logger.trace(key + " - ");
                for(String value : values) {
                    logger.trace(" : " + value);
                }
            }

            if(HttpStatusClass.SUCCESS.contains(responseCode)) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            recvStr = res.toString();
            logger.debug("recvStr : " + recvStr);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        } catch (ProtocolException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return recvStr;
    }//END OF FUNCTION

}//END OF CLASS