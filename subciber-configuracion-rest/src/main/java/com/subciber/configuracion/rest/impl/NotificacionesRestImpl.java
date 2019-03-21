/**
 * 
 */
package com.subciber.configuracion.rest.impl;

import java.text.MessageFormat;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.business.api.NotificacionRxBusiness;
import com.subciber.configuracion.business.api.NotificacionTxBusiness;
import com.subciber.configuracion.dto.CrearNotificacionDto;
import com.subciber.configuracion.dto.FiltroNotificacionDto;
import com.subciber.configuracion.dto.RequestDeleteObjectDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.RequestNotificacionDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.exception.GeneralException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.rest.api.NotificacionesRest;
import com.subciber.configuracion.util.Utilitario;

/**
 * @description Implementacion de la interface de AlertaSistemaRest
 * @author David Villanueva
 * @version 0.1, 05/03/2019
 * @update
 */
@Path("/notificaciones")
public class NotificacionesRestImpl implements NotificacionesRest {

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
	private NotificacionRxBusiness notificacionRxBusiness;
	@Inject
	private NotificacionTxBusiness notificacionTxBusiness;

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Override
	public Response consultarNotificaciones() {
		ResponseGenericDto<RequestNotificacionDto> response = null;
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			response = new ResponseGenericDto<RequestNotificacionDto>();
			FiltroNotificacionDto fltroNotificacion = new FiltroNotificacionDto();
			//1. Generamos el request
			RequestGenericDto<FiltroNotificacionDto> requestGenerarico = utilitario.generateRequest(fltroNotificacion, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestGenerarico.getAuditRequest().getTransaccionId());
			//2. Consultamos las notificaciones
			ResponseGenericDto<RequestNotificacionDto> consultarAlertasSistemaResponse = notificacionRxBusiness
					.consultarNotificaciones(requestGenerarico);
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

	@GET
	@Path("/{notificacionId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Override
	public Response mostrarNotificacion() {
		 
		ResponseGenericDto<RequestNotificacionDto> response = null;
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			response = new ResponseGenericDto<RequestNotificacionDto>();
			//1. Generamos el filtro
			FiltroNotificacionDto fltroNotificacion = new FiltroNotificacionDto();
			fltroNotificacion.setNotificacionId(Integer.parseInt(uriInfo.getPathParameters().getFirst("notificacionId")));
			//2. Generamos el request
			RequestGenericDto<FiltroNotificacionDto> requestGenerarico = utilitario.generateRequest(fltroNotificacion, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestGenerarico.getAuditRequest().getTransaccionId());
			//3. Consultamos las notificaciones
			ResponseGenericDto<RequestNotificacionDto> consultarAlertasSistemaResponse = notificacionRxBusiness
					.consultarNotificaciones(requestGenerarico);
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

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Override
	public Response crearNotificacion(CrearNotificacionDto request) {
	 
		ResponseGenericDto<Integer> response = null;
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			response = new ResponseGenericDto<Integer>();
			//1. Generamos el request
			RequestGenericDto<CrearNotificacionDto> requestGenerarico = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestGenerarico.getAuditRequest().getTransaccionId());
			//2. Registramos la notificacion
			response = notificacionTxBusiness.registrarNotificacion(requestGenerarico);
			
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

	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Override
	public Response actualizarNotificacion(CrearNotificacionDto request) {
		AuditResponseDto response = null;
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			response = new AuditResponseDto();
			//1. Generamos el request
			RequestGenericDto<CrearNotificacionDto> requestGenerarico = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.setTransaccionId(requestGenerarico.getAuditRequest().getTransaccionId());
			//2. Actualizamos la notificacion
			response = notificacionTxBusiness.actualizarNotificacion(requestGenerarico);
			
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

	@DELETE
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Override
	public Response eliminarNotificacion(RequestDeleteObjectDto request) {
		 
		AuditResponseDto response = null;
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			response = new AuditResponseDto();
			//1. Generamos el request
			RequestGenericDto<RequestDeleteObjectDto> requestGenerarico = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.setTransaccionId(requestGenerarico.getAuditRequest().getTransaccionId());
			//2. Eliminamaos la notificacion
			response = notificacionTxBusiness.eliminarNotificacion(requestGenerarico);
			
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
