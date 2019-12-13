package com.harm.unit.smtp;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

class MailAuth extends Authenticator{

	PasswordAuthentication pa;

	public MailAuth() {
		String mail_id = "herdin86";
		String mail_pw = "password";

		pa = new PasswordAuthentication(mail_id, mail_pw);
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
}

public class SimpleJavaxSMTPStudy003 implements Unit {
	private Logger logger = LoggerFactory.getLogger(SimpleJavaxSMTPStudy003.class);

	public static void main(String[] args) throws Exception {
		new SimpleJavaxSMTPStudy003().execute(null);
	}

	@Override
	public Object execute(Object[] obj) throws Exception {
		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587");

		Authenticator auth = new MailAuth();

		Session session = Session.getDefaultInstance(prop, auth);

		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setSentDate(new Date());

			msg.setFrom(new InternetAddress("herdin86@gmail.com", "VISITOR"));
			InternetAddress to = new InternetAddress("herdin86@gmail.com");
			msg.setRecipient(Message.RecipientType.TO, to);
			msg.setSubject("제목", "UTF-8");
			msg.setText("안녕하세요 테스트 메일입니다.", "UTF-8");

			Transport.send(msg);

		} catch(AddressException ae) {
			System.out.println("AddressException : " + ae.getMessage());
		} catch(MessagingException me) {
			System.out.println("MessagingException : " + me.getMessage());
		} catch(UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException : " + e.getMessage());
		}


		출처: https://shxrecord.tistory.com/118 [첫 발]
		return null;
	}// END OF FUNCTION

}// END OF CLASS