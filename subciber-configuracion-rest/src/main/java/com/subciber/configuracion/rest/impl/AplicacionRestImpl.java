/**
 * 
 */
package com.subciber.configuracion.rest.impl;

import java.net.Inet4Address;
import java.text.MessageFormat;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.subciber.configuracion.business.api.AplicacionBusiness;
import com.subciber.configuracion.dto.CnfAplicacionDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.exception.GeneralException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.rest.api.AplicacionRest;
import com.subciber.configuracion.util.Utilitario;

/**
 * @author josep
 *
 */
@Path("/aplicacion")
public class AplicacionRestImpl implements AplicacionRest{

	@Inject
    private MessageProvider messageProvider;
	@Inject
	private AplicacionBusiness aplicacionBusiness;
	@Inject
	private Utilitario utilitario;
	@Context 
	HttpHeaders httpHeaders;
	@Context
    UriInfo uriInfo;
	
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	/*
	
	@Context 
	HttpServletRequest httpServletRequest;
	*/
	@GET
	@Path("/")
	@Produces("application/json")
	@Override
	public Response consultarAplicacion(){
		
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		ResponseGenericDto<CnfAplicacionDto> response = new ResponseGenericDto<CnfAplicacionDto>();
		CnfAplicacionDto getAplicacion = null;
		try {
			
			Integer i = 1;
			System.out.println(Inet4Address.getLocalHost().getHostAddress());
			/*System.out.println(Inet4Address.getLocalHost().getHostAddress());
			System.out.println(uriInfo.getAbsolutePath().toASCIIString());
			for(String header : httpHeaders.getRequestHeaders().keySet()){
				System.out.println(header + " : " + httpHeaders.getRequestHeader(header).get(0));
			}
			System.out.println("getAuthType: " + httpServletRequest.getAuthType() );
			System.out.println("Session: " + httpServletRequest.getSession());
			HttpSession session = httpServletRequest.getSession(false);
			System.out.println("Session False: " + session.getId());
			*/
			//RequestGenericDto<Integer> request = utilitario.generateRequest(i, httpHeaders, uriInfo);
			//System.out.println(request.getAuditRequest().getAplicacion());
			//System.out.println(utilitario.convertObjectToJson(request));
			getAplicacion = aplicacionBusiness.get(1);
			
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
			response.setObjectResponse(getAplicacion);
			// display to console
			//System.out.println(utilitario.convertObjectToJson(response));
			//System.out.println(utilitario.generateRequest(response, httpHeaders));
			
		}catch (BusinessException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		//}catch(GeneralException e) {
		//	response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
		//	response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		}catch (Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}
		return Response.status(200).entity(response).build();
	}

}
