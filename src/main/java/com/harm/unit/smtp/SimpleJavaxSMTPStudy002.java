package com.harm.unit.smtp;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * ����
 * https://ktko.tistory.com/entry/JAVA-SMTP%EC%99%80-Mail-%EB%B0%9C%EC%86%A1%ED%95%98%EA%B8%B0Google-Naver
 */
public class SimpleJavaxSMTPStudy002 implements Unit {
	private Logger logger = LoggerFactory.getLogger(SimpleJavaxSMTPStudy002.class);

	public static void main(String[] args) throws Exception {
		new SimpleJavaxSMTPStudy002().execute(null);
	}

	@Override
	public Object execute(Object[] obj) throws Exception {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(prop, new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		         String username = "herdin86@gmail.com";
		         String password = "password";
		         return new PasswordAuthentication(username, password);
		    }
	    });
		session.setDebug(true);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("herdin86@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("herdin86@gmail.com"));
		message.setSubject("메일 제목");
		message.setContent("하이 헬로우 안녕", "text/plain");
		message.setSentDate(new Date());
		Transport.send(message);
		return null;
	}// END OF FUNCTION

}// END OF CLASS