package com.harm.main;

import com.harm.unit.smtp.SimpleJavaxSMTPStudy001;

public class MainApp {
	public static void main(String[] args) throws Exception {
//		DefaultUnitHandler.start(new DefaultUnitHandler(new CacheStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new ProxyStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new NettyServerStudy()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new SAXBuilerStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new JaxBStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new JaxBStudy002()));
//		DefaultUnitHandler.start(new String[] {"http://dailytravel.co.kr/rss"}, new DefaultUnitHandler(new HttpUrlConnectionStudy01()));
//		DefaultUnitHandler.start(new String[] {"http://dailytravel.co.kr/rss"}, new DefaultUnitHandler(new RssXmlParseStudy001()));
//		try {
//			System.out.println(HashingUtils.sha256("12341234", Charset.forName("UTF-8")));
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		new SimpleJavaxSMTPStudy001().excute(null);
//		DefaultUnitHandler.start(new DefaultUnitHandler(new SimpleJavaxSMTPStudy001()));
	}//END OF FUNCTIION
}//END OF CLASS
