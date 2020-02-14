package com.subciber.configuracion.business.test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
 
	public static void main(String[] args) {
        Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "mail.vivfcons.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.debug", "true");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.quitwait", "false");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("sistema@vivfcons.com","Peru123..@");
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sistema@vivfcons.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("joseph19_20@hotmail.com")
            );
            message.setSubject("Testing SUBCIBER16666");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");  
            Transport.send(message); 
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
}
