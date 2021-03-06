package com.subciber.configuracion.business.test;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailPruebaTest {

	public static void main(String[] args) {
		//Setting up configurations for the email connection to the Google SMTP server using TLS
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.vivfcons.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        System.out.println(System.getProperty("java.home"));
//        props.setProperty("javax.net.ssl.trustStore",
//		"C:\\Program Files\\Java\\jdk1.8.0_172\\jre\\lib\\security\\cacerts");
//        props.setProperty("javax.net.ssl.trustStorePassword", "changeit");
//        System.out.println(props.getProperty("javax.net.ssl.trustStore"));
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sistema@vivfcons.com", "Peru123..@");
            }
        });
        try {
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
            //Storing the comma seperated values to email addresses
            String to = "sistema@vivfcons.com,jodavivi@gmail.com";
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);
            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);
            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
            msg.setSubject("Sample Mail : " + timeStamp);
            msg.setSentDate(new Date());
            msg.setText("Sampel System Generated mail");
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            System.out.println("Mail has been sent successfully");
        } catch (MessagingException mex) {
            System.out.println("Unable to send an email" + mex);
        }

	}

}
