package com.subciber.configuracion.rest.util;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.subciber.configuracion.client.api.TokenClient;
import com.subciber.configuracion.client.dto.FiltroTokensDto;
import com.subciber.configuracion.client.dto.ResponseValidarTokens;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.property.MessageProvider;

@Provider
@PreMatching
public class FilterRequestService implements ContainerRequestFilter {

	@Inject
	private TokenClient tokenClient;
	@Inject
    private MessageProvider messageProvider;
	 	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		 
		String session = requestContext.getHeaderString("tokens");
		boolean loggedIn = (session != null && session != "" && session.length() > 0); 
	    
	    if (loggedIn) {
	    	
	    	try {
	    		
	    		RequestGenericDto<FiltroTokensDto> tokens = new RequestGenericDto<FiltroTokensDto>();
    	 		tokens.getAuditRequest().setTransaccionId(requestContext.getHeaderString("transaccionId"));
    	 		tokens.getAuditRequest().setAplicacion(requestContext.getHeaderString("aplicacion"));
    	 		tokens.getAuditRequest().setTerminal(requestContext.getHeaderString("terminal"));
    	 		FiltroTokensDto inputTokens = new FiltroTokensDto();
    	 		inputTokens.setTokens(session);
    	 		tokens.setObjectRequest(inputTokens);
    	 		ResponseValidarTokens validarTokensResponse = tokenClient.validarTokens(tokens);
    	 		 if(validarTokensResponse.getAuditResponse().getCodigoRespuesta() != messageProvider.codigoExito) {
    	 	    	 requestContext.abortWith(
    		                 Response.status(Response.Status.UNAUTHORIZED)
    		         .entity("No tiene permisos para utilizar este recurso")
    		                 .build());
    	 		 }else {
    	 			requestContext.getHeaders().add("tokens2", validarTokensResponse.getObjectResponse().getNuevoTokens());
    		    	requestContext.getHeaders().add("usuario", validarTokensResponse.getObjectResponse().getUsuario());
    		    	requestContext.getHeaders().add("usuarioId", validarTokensResponse.getObjectResponse().getUsuarioId().toString());
    		    	
    	 		 }
	    		
	    	}catch(Exception e) {
	    		requestContext.abortWith(
		                 Response.status(Response.Status.UNAUTHORIZED)
		         .entity("No tiene permisos para utilizar este recurso")
		                 .build());
	    	}
	
	     }else {
	    	 requestContext.abortWith(
	                 Response.status(Response.Status.UNAUTHORIZED)
	         .entity("No tiene permisos para utilizar este recurso")
	                 .build());
	     }
		
	}

}

