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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.subciber.configuracion.base.dto.AuditRequestDto;
import com.subciber.configuracion.business.api.AlertaUsuarioRxBusiness;
import com.subciber.configuracion.dto.RequestAlertaSistemaDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.exception.GeneralException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.rest.api.AlertaSistemaRest;
import com.subciber.configuracion.util.Utilitario;

/**
 * @description Implementacion de la interface de AlertaSistemaRest
 * @author David Villanueva
 * @version 0.1, 05/03/2019
 * @update
 */
@Path("/alertas")
public class AlertaSistemaRestImpl implements AlertaSistemaRest {

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
	private AlertaUsuarioRxBusiness alertaUsuarioRxBusiness;

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Override
	public Response consultarAlertasSistema() {
		ResponseGenericDto<RequestAlertaSistemaDto> response = new ResponseGenericDto<RequestAlertaSistemaDto>();
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			AuditRequestDto requestGenerarico = utilitario.generateSimpleRequest(httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestGenerarico.getTransaccionId());
			ResponseGenericDto<RequestAlertaSistemaDto> consultarAlertasSistemaResponse = alertaUsuarioRxBusiness
					.consultarAlertasSistema(requestGenerarico);
			response.setObjectResponse(consultarAlertasSistemaResponse.getObjectResponse());
			response.setAuditResponse(consultarAlertasSistemaResponse.getAuditResponse());
		} catch (BusinessException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (GeneralException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3,
					clase, metodo, e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return Response.status(200).entity(response).build();
	}

}
