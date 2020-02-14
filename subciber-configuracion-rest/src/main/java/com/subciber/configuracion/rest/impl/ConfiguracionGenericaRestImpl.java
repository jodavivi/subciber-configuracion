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

import com.subciber.configuracion.base.dto.AuditRequestDto;
import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.business.api.ConfiguracionGenericaTxBusiness;
import com.subciber.configuracion.exception.GeneralException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.rest.api.ConfiguracionGenericaRest;
import com.subciber.configuracion.util.Utilitario;

/**
 * @description Implementacion de la interface de CampoGenericaRest
 * @author David Villanueva
 * @version 0.1, 12/03/2019
 * @update
 */
@Path("/config/generica")
public class ConfiguracionGenericaRestImpl implements ConfiguracionGenericaRest {
	
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
	private ConfiguracionGenericaTxBusiness configuracionGenericaTxBusiness;
	
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response activarPeriodoActual() {
		AuditResponseDto response = null;
		try {
			response = new AuditResponseDto();
			AuditRequestDto requestTabla = utilitario.generateSimpleRequest(httpHeaders, uriInfo);
			response.setTransaccionId(requestTabla.getTransaccionId());
			response = configuracionGenericaTxBusiness.validarPeriodoActual(requestTabla);

			if (response.getCodigoRespuesta() != messageProvider.codigoExito) {
				response.setCodigoRespuesta(response.getCodigoRespuesta());
				response.setMensajeRespuesta(response.getMensajeRespuesta());
			}
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
