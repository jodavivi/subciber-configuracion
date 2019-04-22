package com.subciber.configuracion.client.impl;

import java.text.MessageFormat;

import javax.enterprise.context.Dependent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.subciber.configuracion.client.api.TokenClient;
import com.subciber.configuracion.client.dto.FiltroTokensDto;
import com.subciber.configuracion.client.dto.ResponseValidarTokens;
import com.subciber.configuracion.client.util.ConfigUrlClient;
import com.subciber.configuracion.client.util.MessageClientService;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.exception.ClientException;

@Dependent
public class TokenClientImpl implements TokenClient {

	@Override
	public ResponseValidarTokens validarTokens(RequestGenericDto<FiltroTokensDto> request) throws ClientException {
		
		ResponseValidarTokens respuesta = null;
		try {
			respuesta = new ResponseValidarTokens();
			respuesta.getAuditResponse().setTransaccionId(request.getAuditRequest().getTransaccionId());
			Client client = ClientBuilder.newClient();
			MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
			headers.add("aplicacion", request.getAuditRequest().getAplicacion());
			headers.add("terminal", request.getAuditRequest().getTerminal());
			headers.add("transaccionId", request.getAuditRequest().getTransaccionId());
			headers.add("tokens", request.getObjectRequest().getTokens());
			String url = ConfigUrlClient.urlValidarTokens;
			Response response = client.target(url).request(MediaType.APPLICATION_JSON).headers(headers).post(Entity.entity(request.getObjectRequest(), MediaType.APPLICATION_JSON));
			ResponseValidarTokens respuestaValidaTokens = response.readEntity(ResponseValidarTokens.class); 
			respuesta.getAuditResponse().setCodigoRespuesta(respuestaValidaTokens.getAuditResponse().getCodigoRespuesta());
			respuesta.getAuditResponse().setMensajeRespuesta(respuestaValidaTokens.getAuditResponse().getMensajeRespuesta());
			respuesta.setObjectResponse(respuestaValidaTokens.getObjectResponse());
			
			response.close();
		    client.close();
			
		}catch(Exception e){
			respuesta.getAuditResponse().setCodigoRespuesta(MessageClientService.codigoErrorIdt100);
			respuesta.getAuditResponse().setMensajeRespuesta(MessageFormat.format(MessageClientService.mensajeErrorIdt100,"validarTokens"));
		}
		return respuesta;
	}

}
