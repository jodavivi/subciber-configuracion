package com.subciber.configuracion.rest.impl;

import java.io.InputStream;
import java.text.MessageFormat;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.subciber.configuracion.dto.CargaArchivoDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.GeneralException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.rest.api.UploadRest;
import com.subciber.configuracion.util.Utilitario;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * @description Implementacion de la interface de UploadRest
 * @author David Villanueva
 * @version 0.1, 16/07/2019
 * @update
 */
@Path("/file")
public class UploadRestImpl implements UploadRest {

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
	
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response registrarArchivo(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		 
		ResponseGenericDto<Integer> response = null;
		RequestGenericDto<CargaArchivoDto> requestTabla = null;
		try {
			
			CargaArchivoDto request = new CargaArchivoDto();
			response = new ResponseGenericDto<Integer>();
			requestTabla = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestTabla.getAuditRequest().getTransaccionId());
			 
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

}
