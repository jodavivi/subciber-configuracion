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
import com.subciber.configuracion.business.api.AlertaUsuarioTxBusiness;
import com.subciber.configuracion.dao.api.AlertaUsuarioTxDao;
import com.subciber.configuracion.dto.RequestDeleteObjectDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.util.JAXBUtil;

/**
 * @description implementacion de la interface AlertaUsuarioTxBusiness
 * @author David Villanueva
 * @version 0.1, 15/03/2019
 * @update
 */
@Dependent
public class AlertaUsuarioTxBusinessImpl implements AlertaUsuarioTxBusiness, Serializable {

	private static final long serialVersionUID = 1L;
	static final Logger logger = LoggerFactory.getLogger(TablaGenericaTxBusinessImpl.class);
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	long timeStart = 0;
	String transactionId = null;
	
	@Inject
    private MessageProvider messageProvider;
	@EJB
	private AlertaUsuarioTxDao alertaUsuarioTxDao; 
	
	@Override
	public AuditResponseDto eliminarAlertaUsuario(RequestGenericDto<RequestDeleteObjectDto> request)
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
			
			AuditResponseDto  eliminarTablaGenericaResponse = null;
			for(Integer item : request.getObjectRequest().getItems()) {
				RequestGenericDto<Integer> requestEliminar = new RequestGenericDto<Integer>();
				requestEliminar.setAuditRequest(request.getAuditRequest());
				requestEliminar.setObjectRequest(item);
				eliminarTablaGenericaResponse = alertaUsuarioTxDao.eliminarAlertaUsuario(requestEliminar);
				if(eliminarTablaGenericaResponse.getCodigoRespuesta() != messageProvider.codigoExito) {
					throw new DaoException(eliminarTablaGenericaResponse.getCodigoRespuesta(), eliminarTablaGenericaResponse.getMensajeRespuesta());
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
