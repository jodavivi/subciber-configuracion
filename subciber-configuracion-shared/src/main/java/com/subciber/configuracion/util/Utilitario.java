/**
 * 
 */
package com.subciber.configuracion.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subciber.configuracion.base.dto.AuditRequestDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.exception.GeneralException;
import com.subciber.configuracion.property.MessageProvider;

/**
 * @author josep
 *
 */
@Dependent
public class Utilitario {
	
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@Inject
    private MessageProvider messageProvider;
	@Inject
	private EncriptacionAES encriptacionAES;
	
	public <T> String convertObjectToJson(T object) throws GeneralException{
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		 ObjectMapper Obj = new ObjectMapper(); 
		 String jsonStr = "";
         try {
			jsonStr = Obj.writeValueAsString(object);
		} catch(Exception e) {
			throw new GeneralException(messageProvider.codigoErrorIdt4,MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}
		 
		 return jsonStr;
	 }
	 
	public <T> RequestGenericDto<T> generateRequest(T object, HttpHeaders httpHeaders, UriInfo uriInfo)
			throws GeneralException {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		RequestGenericDto<T> response = null;
		try {
		 
			 response = new RequestGenericDto<>();
			 if(isNullOrEmpty(httpHeaders.getHeaderString("transaccionId"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"1"));
			 }
			 if(isNullOrEmpty(httpHeaders.getHeaderString("aplicacion"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"2"));
			 }
			 if(isNullOrEmpty(httpHeaders.getHeaderString("tokens"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"3"));
			 }
			 if(isNullOrEmpty(httpHeaders.getHeaderString("terminal"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"4"));
			 }
			 
			 response.getAuditRequest().setTransaccionId(httpHeaders.getHeaderString("transaccionId"));
			 response.getAuditRequest().setAplicacion(httpHeaders.getHeaderString("aplicacion"));
			 response.getAuditRequest().setUsuario(httpHeaders.getHeaderString("tokens"));
			 response.getAuditRequest().setTerminal(httpHeaders.getHeaderString("terminal"));
			 response.setObjectRequest(object);
		 
		 }catch(Exception e) {
			 throw new GeneralException(messageProvider.codigoErrorIdt4,MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		 }
				 
		return response;
	 }
	 
	public AuditRequestDto generateSimpleRequest(HttpHeaders httpHeaders, UriInfo uriInfo)
			throws GeneralException {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		AuditRequestDto response = null;
		try {
			 String tokenSession = "";
			 
			 response = new AuditRequestDto();
			 if(isNullOrEmpty(httpHeaders.getHeaderString("transaccionId"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"1"));
			 }
			 if(isNullOrEmpty(httpHeaders.getHeaderString("aplicacion"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"2"));
			 }
			 if(isNullOrEmpty(httpHeaders.getHeaderString("tokens"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"3"));
			 }
			 if(isNullOrEmpty(httpHeaders.getHeaderString("terminal"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"4"));
			 }

			 response.setAplicacion(httpHeaders.getHeaderString("aplicacion"));
			 response.setTerminal(httpHeaders.getHeaderString("terminal"));
			 response.setTransaccionId(httpHeaders.getHeaderString("transaccionId")); 

		}catch(GeneralException e) {
			throw new GeneralException(e.getCodigo(), e.getMensaje());
		} catch (Exception e) {
			throw new GeneralException(messageProvider.codigoErrorIdt4,
					MessageFormat.format(messageProvider.mensajeErrorIdt4, clase, metodo,
							e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return response;
	 }
	public String encriptarString(String request) throws GeneralException {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		String response = null;
		try {
			response = encriptacionAES.encrypt(request, ConstantesConfig.claveEncripacionAES);
		} catch (Exception e) {
			throw new GeneralException(messageProvider.codigoErrorIdt4,
					MessageFormat.format(messageProvider.mensajeErrorIdt4, clase, metodo,
							e.getStackTrace()[0].getLineNumber(), e.getMessage()));

		}
		return response;
	}

	public List<String> quitarDuplicados(List<String> list) throws GeneralException{
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		List<String> result = new ArrayList<>();
		try {
			HashSet<String> set = new HashSet<>();
			for (String item : list) {

				if (!set.contains(item)) {
					result.add(item);
					set.add(item);
				}
			}
		} catch (Exception e) {
			throw new GeneralException(messageProvider.codigoErrorIdt4,
					MessageFormat.format(messageProvider.mensajeErrorIdt4, clase, metodo,
							e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}
		return result;
	}

	public boolean isNullOrEmpty(String str) {
		 boolean ok = true;
		 try {
	        if(str != null && !str.trim().isEmpty())
	        	ok = false;
		 }catch(Exception e) {
			 ok = false;
		 }
	        return ok;
	    }
	
	
	public String generarString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
