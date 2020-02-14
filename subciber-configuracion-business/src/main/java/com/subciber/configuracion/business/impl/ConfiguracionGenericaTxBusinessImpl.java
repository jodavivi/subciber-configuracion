package com.subciber.configuracion.business.impl;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.subciber.configuracion.base.dto.AuditRequestDto;
import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.business.api.ConfiguracionGenericaTxBusiness;
import com.subciber.configuracion.dao.api.CampoGenericaRxDao;
import com.subciber.configuracion.dao.api.CampoGenericaTxDao;
import com.subciber.configuracion.dto.CampoGenericaDto;
import com.subciber.configuracion.dto.CampoGenericaFiltroDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.entity.VCampoGenerica;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.util.JAXBUtil;

/**
 * @description implementacion de la interface ConfiguracionGenericaTxBusiness
 * @author David Villanueva
 * @version 0.1, 14/02/2020
 * @update
 */
@Dependent
public class ConfiguracionGenericaTxBusinessImpl implements ConfiguracionGenericaTxBusiness , Serializable{

	private static final long serialVersionUID = 1L;
	static final Logger logger = LoggerFactory.getLogger(CampoGenericaTxBusinessImpl.class);
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	long timeStart = 0;
	String transactionId = null;
	
	@Inject
    private MessageProvider messageProvider;
	
	@EJB
	private CampoGenericaRxDao campoGenericaRxDao;
	
	@EJB
	private CampoGenericaTxDao campoGenericaTxDao;
	
	@Override
	public AuditResponseDto validarPeriodoActual(AuditRequestDto request) throws BusinessException {
		AuditResponseDto response = null;
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getTransaccionId());
			//Consultamos los periodos del sistema 
			CampoGenericaFiltroDto requestGenerica = new CampoGenericaFiltroDto();
			requestGenerica.setCodigoTabla("periodo_sistema");
			requestGenerica.setEstadoId(23);
			List<VCampoGenerica> consultarCampoGenericaResponse = campoGenericaRxDao.consultarCampoGenerica(requestGenerica);
			
			if(consultarCampoGenericaResponse == null || consultarCampoGenericaResponse.size() == 0) {
				throw new  DaoException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2," Información de Periodo del Sistema"));
			}
			
			VCampoGenerica periodoActual = new VCampoGenerica(); 
			for(VCampoGenerica item : consultarCampoGenericaResponse) {
				if(item.getCodigoIntegracion().equalsIgnoreCase("A")) {
					periodoActual = item;
					break;
				}
			}
			
			 Date fecha = new Date();
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(fecha);
			 calendar.add(Calendar.HOUR, -5);   
			 Date nuevaFecha = calendar.getTime();
			 DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM");
			 
			 String periodoNuevo = formatoFecha.format(nuevaFecha);
			 
			 
			 if(!periodoNuevo.equalsIgnoreCase(periodoActual.getCampo())){
				 //el periodo actual es diferente al periodo activo
				 // buscamos el Id del periodo
				 VCampoGenerica nuevoPeriodo = new VCampoGenerica();
				 for(VCampoGenerica item : consultarCampoGenericaResponse) {
					if(item.getCampo().equalsIgnoreCase(periodoNuevo)) {
						nuevoPeriodo = item;
						break;
					}
				 }
				 
				 //Limpiamos el codigoIntegracion del Periodo activo
				 RequestGenericDto<CampoGenericaDto> requestUpdate = new RequestGenericDto<CampoGenericaDto>();
				 requestUpdate.setAuditRequest(request);
				 CampoGenericaDto campoUpdate = new CampoGenericaDto();
				 campoUpdate.setId(periodoActual.getId());
				 campoUpdate.setCodigoIntegracion("");
				 requestUpdate.setObjectRequest(campoUpdate);
				 AuditResponseDto actualizarCampoGenericaResponse = campoGenericaTxDao.actualizarCampoGenerica(requestUpdate);
				 if(actualizarCampoGenericaResponse.getCodigoRespuesta() != messageProvider.codigoExito) {
					throw new DaoException(actualizarCampoGenericaResponse.getCodigoRespuesta(), actualizarCampoGenericaResponse.getMensajeRespuesta());
				 }
				 
				 //Actualizamos el nuevo periodo Activo
				 RequestGenericDto<CampoGenericaDto> requestUpdateNuevo = new RequestGenericDto<CampoGenericaDto>();
				 requestUpdateNuevo.setAuditRequest(request);
				 CampoGenericaDto campoUpdateNuevo = new CampoGenericaDto();
				 campoUpdateNuevo.setId(nuevoPeriodo.getId());
				 campoUpdateNuevo.setCodigoIntegracion("A");
				 requestUpdateNuevo.setObjectRequest(campoUpdateNuevo);
				 AuditResponseDto actualizarCampoGenericaNuevoResponse = campoGenericaTxDao.actualizarCampoGenerica(requestUpdateNuevo);
				 if(actualizarCampoGenericaNuevoResponse.getCodigoRespuesta() != messageProvider.codigoExito) {
					throw new DaoException(actualizarCampoGenericaNuevoResponse.getCodigoRespuesta(), actualizarCampoGenericaNuevoResponse.getMensajeRespuesta());
				 }
				 
			 }
			
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
		}catch (DaoException e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));		
		} finally { 
			logger.info(MessageFormat.format(messageProvider.logMensajeOut, transactionId, metodo, JAXBUtil.log(response)));
			logger.info(MessageFormat.format(messageProvider.logMensajeTime, transactionId,	metodo, (System.currentTimeMillis() - timeStart)));
			logger.info(MessageFormat.format(messageProvider.logMensajeEnd, transactionId, metodo));
		}
		return response;
	}
}
