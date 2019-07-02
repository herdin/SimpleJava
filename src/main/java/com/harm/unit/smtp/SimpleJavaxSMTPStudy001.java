package com.harm.unit.smtp;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harm.unit.Unit;

/**
 * ����
 * https://ktko.tistory.com/entry/JAVA-SMTP%EC%99%80-Mail-%EB%B0%9C%EC%86%A1%ED%95%98%EA%B8%B0Google-Naver
 */
public class SimpleJavaxSMTPStudy001 implements Unit {
	private Logger logger = LoggerFactory.getLogger(SimpleJavaxSMTPStudy001.class);

	@Override
	public Object execute(Object[] obj) throws Exception {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(prop, new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		         String username = "herdin86@gmail.com"; // gmail �����;
		         String password = "passwod  // �н����� �Ϻη� Ʋ�� ��������� ���� �� �ȵǴ°ž�";
		         return new PasswordAuthentication(username, password);
		    }
	    });
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("herdin86@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("herdin86@gmail.com"));
		message.setSubject("mail subject");
		message.setContent("������ ����", "text/plain");
		message.setSentDate(new Date());
		Transport.send(message);
		return null;
	}// END OF FUNCTION

}// END OF CLASS