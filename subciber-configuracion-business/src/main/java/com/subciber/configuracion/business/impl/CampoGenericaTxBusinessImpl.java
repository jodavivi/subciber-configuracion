/**
 * 
 */
package com.subciber.configuracion.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.business.api.CampoGenericaTxBusiness;
import com.subciber.configuracion.dao.api.CampoGenericaTxDao;
import com.subciber.configuracion.dto.CampoGenericaDto;
import com.subciber.configuracion.dto.RequestDeleteObjectDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.util.JAXBUtil;

/**
 * @description implementacion de la interface eliminarCampoGenericaResponse
 * @author David Villanueva
 * @version 0.1, 11/03/2019
 * @update
 */
@Dependent
public class CampoGenericaTxBusinessImpl implements CampoGenericaTxBusiness , Serializable {

	private static final long serialVersionUID = 1L;
	static final Logger logger = LoggerFactory.getLogger(CampoGenericaTxBusinessImpl.class);
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	long timeStart = 0;
	String transactionId = null;
	
	@Inject
    private MessageProvider messageProvider;
	@EJB
	private CampoGenericaTxDao campoGenericaTxDao; 
	
 
	@Override
	public ResponseGenericDto<Integer> registrarCampoGenerica(RequestGenericDto<CampoGenericaDto> request)
			throws BusinessException {
		
		ResponseGenericDto<Integer> response = null;

		try {
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			response = new ResponseGenericDto<>();
			response.getAuditResponse().setTransaccionId(transactionId);		
			ResponseGenericDto<Integer>  registrarCampoGenericaResponse = campoGenericaTxDao.registrarCampoGenerica(request);
			
			if(registrarCampoGenericaResponse.getAuditResponse().getCodigoRespuesta() != messageProvider.codigoExito) {
				throw new DaoException(registrarCampoGenericaResponse.getAuditResponse().getCodigoRespuesta(), registrarCampoGenericaResponse.getAuditResponse().getMensajeRespuesta());
			}
			response.setObjectResponse(registrarCampoGenericaResponse.getObjectResponse());
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
		}catch (DaoException e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));		
		} finally { 
			logger.info(MessageFormat.format(messageProvider.logMensajeOut, transactionId, metodo, JAXBUtil.log(response)));
			logger.info(MessageFormat.format(messageProvider.logMensajeTime, transactionId,	metodo, (System.currentTimeMillis() - timeStart)));
			logger.info(MessageFormat.format(messageProvider.logMensajeEnd, transactionId, metodo));
		}
		
		return response;
	}

 
	@Override
	public AuditResponseDto actualizarCampoGenerica(RequestGenericDto<CampoGenericaDto> request)
			throws BusinessException {
		 
		AuditResponseDto response = null;

		try {
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			response = new AuditResponseDto();
			response.setTransaccionId(transactionId);		
			AuditResponseDto  actualizarCampoGenericaResponse = campoGenericaTxDao.actualizarCampoGenerica(request);
			
			if(actualizarCampoGenericaResponse.getCodigoRespuesta() != messageProvider.codigoExito) {
				throw new DaoException(actualizarCampoGenericaResponse.getCodigoRespuesta(), actualizarCampoGenericaResponse.getMensajeRespuesta());
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

 
	@Override
	public AuditResponseDto eliminarCampoGenerica(RequestGenericDto<RequestDeleteObjectDto> request)
			throws BusinessException {
		 
		AuditResponseDto response = null;

		try {
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			response = new AuditResponseDto();
			response.setTransaccionId(transactionId);		
			
			AuditResponseDto  eliminarCampoGenericaResponse = null;
			for(Integer item : request.getObjectRequest().getItems()) {
				RequestGenericDto<Integer> requestEliminar = new RequestGenericDto<Integer>();
				requestEliminar.setAuditRequest(request.getAuditRequest());
				requestEliminar.setObjectRequest(item);
				eliminarCampoGenericaResponse = campoGenericaTxDao.eliminarCampoGenerica(requestEliminar);
				if(eliminarCampoGenericaResponse.getCodigoRespuesta() != messageProvider.codigoExito) {
					throw new DaoException(eliminarCampoGenericaResponse.getCodigoRespuesta(), eliminarCampoGenericaResponse.getMensajeRespuesta());
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
