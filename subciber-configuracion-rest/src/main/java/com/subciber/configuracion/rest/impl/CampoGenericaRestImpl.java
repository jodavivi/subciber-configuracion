/**
 * 
 */
package com.subciber.configuracion.rest.impl;

import java.text.MessageFormat;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.subciber.configuracion.business.api.CampoGenericaRxBusiness;
import com.subciber.configuracion.dto.CampoGenericaDto;
import com.subciber.configuracion.dto.CampoGenericaFiltroDto;
import com.subciber.configuracion.dto.CampoGenericaResponseDto;
import com.subciber.configuracion.dto.RequestDeleteObjectDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.GeneralException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.rest.api.CampoGenericaRest;
import com.subciber.configuracion.util.Utilitario;

/**
 * @description Implementacion de la interface de CampoGenericaRest
 * @author David Villanueva
 * @version 0.1, 12/03/2019
 * @update
 */
@Path("/maestra/tablas")
public class CampoGenericaRestImpl implements CampoGenericaRest{

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
	private CampoGenericaRxBusiness campoGenericaRxBusiness;
	
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;

	@GET
	@Path("/{tabla}/campos")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response consultarCampoGenerica() {
		ResponseGenericDto<CampoGenericaResponseDto> response = null;
		RequestGenericDto<CampoGenericaFiltroDto> requestTabla = null;
		try {
			CampoGenericaFiltroDto requestInpput = new CampoGenericaFiltroDto();
			requestInpput.setCodigoTabla(uriInfo.getPathParameters().getFirst("tabla"));
			response = new ResponseGenericDto<CampoGenericaResponseDto>();
			requestTabla = utilitario.generateRequest(requestInpput, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestTabla.getAuditRequest().getTransaccionId());
			response = campoGenericaRxBusiness.consultarCampoGenerica(requestTabla);

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
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3,
					clase, metodo, e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return Response.status(200).entity(response).build();
	}

	@Override
	public Response registrarCampoGenerica(CampoGenericaDto request) {
		
		return null;
	}

	@Override
	public Response actualizarCampoGenerica(CampoGenericaDto request) {
		
		return null;
	}

	@Override
	public Response eliminarCampoGenerica(RequestDeleteObjectDto request) {
		
		return null;
	}

}
