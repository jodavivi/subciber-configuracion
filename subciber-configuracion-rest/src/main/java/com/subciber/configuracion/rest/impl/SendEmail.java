package com.subciber.configuracion.rest.impl;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.sun.mail.util.MailSSLSocketFactory;

public class SendEmail {

	public static void main(String[] args) throws UnsupportedEncodingException {

        // Mention the Recipient's email address
        String to = "jodavivi@gmail.com";

        // Mention the Sender's email address
        String from = "sistema@vivfcons.com";

        // Mention the SMTP server address. Below Gmail's SMTP server is being used to send email
        String host = "184.171.242.18";

        MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    sf.setTrustAllHosts(true);
    // or
    // sf.setTrustedHosts(new String[] { "my-server" });  

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        properties.put("mail.smtp.user", "sistema@vivfcons.com");
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("sistema@vivfcons.com", "Peru123..@");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
    		message.setSubject(MimeUtility.encodeText("Probando 19","UTF-8","B")); 
            message.setContent("Probando 19", "text/html; charset=utf-8"); 
			message.setSentDate(new Date());  
//			Transport.send(message); 
			 Transport t = session.getTransport("smtp");
		        t.connect("sistema@vivfcons.com", "Peru123..@");
		        t.sendMessage(message, message.getAllRecipients());
		        t.close();
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
