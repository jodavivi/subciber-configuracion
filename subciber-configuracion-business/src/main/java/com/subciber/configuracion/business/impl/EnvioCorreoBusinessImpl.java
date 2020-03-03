package com.subciber.configuracion.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.business.api.EnvioCorreoBusiness;
import com.subciber.configuracion.business.dto.EnvioCorreoDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.util.ConstantesConfig;

/**
 * @description implementacion de la interface EnvioCorreoBusiness
 * @author David Villanueva
 * @version 0.1, 14/02/2019
 * @update
 */
@Dependent
public class EnvioCorreoBusinessImpl implements EnvioCorreoBusiness, Serializable {

	private static final long serialVersionUID = 1L;
	
//	@Resource(name="java:jboss/mail/Default")
//	private Session session;
	
	@Inject
    private MessageProvider messageProvider;
	
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditResponseDto enviarCorreo(RequestGenericDto<EnvioCorreoDto> request) throws BusinessException {
		
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		String messageContent = "";
		String subject = "";
		AuditResponseDto auditResponse = null;
		try {
			subject = request.getObjectRequest().getAsunto(); 
			auditResponse = new AuditResponseDto();
			
			Properties prop = getEmailProperties();
			System.out.println(prop.getProperty("javax.net.ssl.trustStore"));
			 Session session = Session.getInstance(prop,
		                new javax.mail.Authenticator() {
		                    protected PasswordAuthentication getPasswordAuthentication() {
		                        return new PasswordAuthentication("sistema@vivfcons.com","Peru123..@");
		                    }
		                });
			 
			messageContent =  request.getObjectRequest().getCuerpo(); 
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(request.getObjectRequest().getCorreoDestino())); 
			message.setFrom(new InternetAddress(ConstantesConfig.CORREO_ORIGEN)); 
			message.setSubject(MimeUtility.encodeText(subject,"UTF-8","B")); 
			message.setContent(messageContent, "text/html; charset=utf-8"); 
			message.setSentDate(new Date());  
			Transport.send(message); 
			auditResponse.setCodigoRespuesta(messageProvider.codigoExito);
			auditResponse.setMensajeRespuesta(messageProvider.mensajeExito);
			
		} catch (Exception e) { 
			e.printStackTrace();
			throw new  BusinessException(messageProvider.codigoErrorIdt2, MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}
		
		return auditResponse;
	}

	public Properties getEmailProperties() {
		final Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "184.171.242.18");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.debug", "true");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.quitwait", "false");
        //prop.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk1.8.0_172\\jre\\lib\\security\\cacerts"); // Windows
        prop.setProperty("javax.net.ssl.trustStore", "/usr/lib/jvm/java-11-openjdk-amd64/lib/security/cacerts"); //Linux
        prop.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		return prop;
	}
 
}