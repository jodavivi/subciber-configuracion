/**
 * 
 */
package com.subciber.configuracion.rest.impl;

import java.text.MessageFormat;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.business.api.EnvioCorreoBusiness;
import com.subciber.configuracion.business.dto.EnvioCorreoDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.exception.GeneralException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.rest.api.EnvioCorreoRest;
import com.subciber.configuracion.util.Utilitario;

/**
 * @description Implementación para el envio de correo
 * @author David Villanueva
 * @version 0.1, 15/02/2019
 * @update
 */
@Path("/enviocorreo")
public class EnvioCorreoRestImpl implements EnvioCorreoRest {

	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@Inject
	private MessageProvider messageProvider;
	@Inject
	private Utilitario utilitario;
	@Context
	private HttpHeaders httpHeaders;
	@Context
	private UriInfo uriInfo;
	@Context 
	private HttpServletRequest httpServletRequest;
	@Inject
	private EnvioCorreoBusiness envioCorreoBusiness;

	/**
	 * {@inheritDoc}
	 */
	@POST
	@Path("/")
	@Produces("application/json")
	@Override
	public Response enviarCorreo(EnvioCorreoDto request) {
		AuditResponseDto response= new AuditResponseDto();
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		RequestGenericDto<EnvioCorreoDto> requestGenerarico = null;
		try {
			requestGenerarico = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.setTransaccionId(requestGenerarico.getAuditRequest().getTransaccionId());
			envioCorreoBusiness.enviarCorreo(requestGenerarico);
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
		} catch (BusinessException e) {
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (GeneralException e) {
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3,
					clase, metodo, e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		} 
		return Response.status(200).entity(response).build();
	}

}
