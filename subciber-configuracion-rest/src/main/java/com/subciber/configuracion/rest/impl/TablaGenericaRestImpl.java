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
import com.subciber.configuracion.business.api.TablaGenericaRxBusiness;
import com.subciber.configuracion.business.api.TablaGenericaTxBusiness;
import com.subciber.configuracion.dto.RequestDeleteObjectDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.dto.TablaGenericaDto;
import com.subciber.configuracion.dto.TablaGenericaFiltroDto;
import com.subciber.configuracion.dto.TablaGenericaResponseDto;
import com.subciber.configuracion.exception.GeneralException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.rest.api.TablaGenericaRest;
import com.subciber.configuracion.util.Utilitario;

/**
 * @description Implementacion de la interface de TablaGenericaRest
 * @author David Villanueva
 * @version 0.1, 11/03/2019
 * @update
 */

@Path("/maestra/tablas")
public class TablaGenericaRestImpl implements TablaGenericaRest{

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
	private TablaGenericaRxBusiness tablaGenericaRxBusiness;
	@Inject
	private TablaGenericaTxBusiness tablaGenericaTxBusiness;
	
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response consultarTablaGenerica() {
		
		ResponseGenericDto<TablaGenericaResponseDto> response = null;
		RequestGenericDto<TablaGenericaFiltroDto> requestTabla = null;
		try { 
			
			TablaGenericaFiltroDto requestInput = new TablaGenericaFiltroDto();
			response = new ResponseGenericDto<TablaGenericaResponseDto>();
			requestTabla = utilitario.generateRequest(requestInput, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestTabla.getAuditRequest().getTransaccionId());
			response = tablaGenericaRxBusiness.consultarTablaGenerica(requestTabla);

			if (response.getAuditResponse().getCodigoRespuesta() != messageProvider.codigoExito) {
				response.getAuditResponse()
						.setCodigoRespuesta(response.getAuditResponse().getCodigoRespuesta());
				response.getAuditResponse()
						.setMensajeRespuesta(response.getAuditResponse().getMensajeRespuesta());
			}
		} catch (GeneralException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo, e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return Response.status(200).entity(response).build();
	}

	@GET
	@Path("/{tabla}")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response consultarTablaGenericaFiltro() {
		
		ResponseGenericDto<TablaGenericaResponseDto> response = null;
		RequestGenericDto<TablaGenericaFiltroDto> requestTabla = null;
		try { 
			TablaGenericaFiltroDto requestInput = new TablaGenericaFiltroDto();
			requestInput.setTablaCodigo(uriInfo.getPathParameters().getFirst("tabla"));
			response = new ResponseGenericDto<TablaGenericaResponseDto>();
			requestTabla = utilitario.generateRequest(requestInput, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestTabla.getAuditRequest().getTransaccionId());
			response = tablaGenericaRxBusiness.consultarTablaGenerica(requestTabla);

			if (response.getAuditResponse().getCodigoRespuesta() != messageProvider.codigoExito) {
				response.getAuditResponse()
						.setCodigoRespuesta(response.getAuditResponse().getCodigoRespuesta());
				response.getAuditResponse()
						.setMensajeRespuesta(response.getAuditResponse().getMensajeRespuesta());
			}
		} catch (GeneralException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo, e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return Response.status(200).entity(response).build();
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response registrarTablaGenerica(TablaGenericaDto request) {
		
		ResponseGenericDto<Integer> response = null;
		RequestGenericDto<TablaGenericaDto> requestTabla = null;
		try {
			response = new ResponseGenericDto<Integer>();
			requestTabla = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestTabla.getAuditRequest().getTransaccionId());
			response = tablaGenericaTxBusiness.registrarTablaGenerica(requestTabla);

			if (response.getAuditResponse().getCodigoRespuesta() != messageProvider.codigoExito) {
				response.getAuditResponse()
						.setCodigoRespuesta(response.getAuditResponse().getCodigoRespuesta());
				response.getAuditResponse()
						.setMensajeRespuesta(response.getAuditResponse().getMensajeRespuesta());
			}
		} catch (GeneralException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo, e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return Response.status(200).entity(response).build();
	}

	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response actualizarTablaGenerica(TablaGenericaDto request) {
		 
		AuditResponseDto response = null;
		RequestGenericDto<TablaGenericaDto> requestTabla = null;
		try {
			response = new AuditResponseDto();
			requestTabla = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.setTransaccionId(requestTabla.getAuditRequest().getTransaccionId());
			response = tablaGenericaTxBusiness.actualizarTablaGenerica(requestTabla);

			if (response.getCodigoRespuesta() != messageProvider.codigoExito) {
				response.setCodigoRespuesta(response.getCodigoRespuesta());
				response.setMensajeRespuesta(response.getMensajeRespuesta());
			}
		} catch (GeneralException e) {
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo, e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return Response.status(200).entity(response).build();
	}

	@DELETE
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response eliminarTablaGenerica(RequestDeleteObjectDto request) {
		 
		AuditResponseDto response = null;
		RequestGenericDto<RequestDeleteObjectDto> requestTabla = null;
		
		try {
			response = new AuditResponseDto();
			requestTabla = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.setTransaccionId(requestTabla.getAuditRequest().getTransaccionId());
			response = tablaGenericaTxBusiness.eliminarTablaGenerica(requestTabla);

			if (response.getCodigoRespuesta() != messageProvider.codigoExito) {
				response.setCodigoRespuesta(response.getCodigoRespuesta());
				response.setMensajeRespuesta(response.getMensajeRespuesta());
			}
		} catch (GeneralException e) {
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo, e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return Response.status(200).entity(response).build();
	}

}
