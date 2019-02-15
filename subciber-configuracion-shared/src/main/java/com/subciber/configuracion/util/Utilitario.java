/**
 * 
 */
package com.subciber.configuracion.util;

import java.text.MessageFormat;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.exception.GeneralException;
import com.subciber.configuracion.property.MessageProvider;

/**
 * @author josep
 *
 */
@Dependent
public class Utilitario {
	
	@Inject
    private MessageProvider messageProvider;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
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
	 
	 public static boolean isNullOrEmpty(String str) {
		 boolean ok = true;
		 try {
	        if(str != null && !str.trim().isEmpty())
	        	ok = false;
		 }catch(Exception e) {
			 ok = false;
		 }
	        return ok;
	    }
}
