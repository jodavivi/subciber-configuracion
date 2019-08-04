package com.subciber.configuracion.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.mail.Authenticator;
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
			final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() { 
					return new PasswordAuthentication(ConstantesConfig.CUENTA_CORREO, ConstantesConfig.CLAVE_CORREO);
				}
			}); 
			messageContent =  request.getObjectRequest().getCuerpo(); 
			final Message message = new MimeMessage(session); 
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(request.getObjectRequest().getCorreoDestino())); 
			message.setFrom(new InternetAddress(ConstantesConfig.CORREO_ORIGEN)); 
			message.setSubject(MimeUtility.encodeText(subject,"UTF-8","B")); 
			message.setContent(messageContent, "text/html; charset=utf-8"); 
			message.setSentDate(new Date()); 
			Transport.send(message); 
			auditResponse.setCodigoRespuesta(messageProvider.codigoExito);
			auditResponse.setMensajeRespuesta(messageProvider.mensajeExito);
			
		} catch (Exception e) { 
			throw new  BusinessException(messageProvider.codigoErrorIdt2, MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}
		
		return auditResponse;
	}

	public Properties getEmailProperties() {
		final Properties config = new Properties(); 
		config.put("mail.smtp.auth", "true");
		config.put("mail.smtp.starttls.enable", "false");
		config.put("mail.smtp.host", ConstantesConfig.SERVIDOR_SMTP);
		config.put("mail.smtp.port", ConstantesConfig.PORT_SERVIDOR_SMTP); 
		return config;
	}
}