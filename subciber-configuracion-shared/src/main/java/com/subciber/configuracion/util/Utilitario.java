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
	 
	 public <T> RequestGenericDto<T> generateRequest(T object, HttpHeaders httpHeaders, UriInfo uriInfo) throws GeneralException{
		 metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		 RequestGenericDto<T> request = null;
		 try {
		 
		 request = new RequestGenericDto<>();
		 request.getAuditRequest().setAplicacion(httpHeaders.getHeaderString("aplicacion"));
		 request.getAuditRequest().setTerminal(httpHeaders.getHeaderString("terminal"));
		 request.getAuditRequest().setTransaccionId(httpHeaders.getHeaderString("transaccionId"));
		 request.getAuditRequest().setUsuario("David");
		 request.getAuditRequest().setUsuarioId(1);
		 request.setObjectRequest(object);
		 
		 }catch(Exception e) {
			 throw new GeneralException(messageProvider.codigoErrorIdt4,MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		 }
				 
		return request;
	 }
	 
	 
	 

}
