/**
 * 
 */
package com.subciber.configuracion.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.subciber.configuracion.business.api.NotificacionRxBusiness;
import com.subciber.configuracion.dao.api.NotificacionRxDao;
import com.subciber.configuracion.dto.FiltroNotificacionDto;
import com.subciber.configuracion.dto.NotificacionDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.RequestNotificacionDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.entity.VNotificacion;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;

/**
 * @description implementacion de la interface AlertaUsuarioRxBusiness
 * @author David Villanueva
 * @version 0.1, 05/03/2019
 * @update
 */
@Dependent
public class NotificacionRxBusinessImpl implements NotificacionRxBusiness, Serializable {
	
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@Inject
    private MessageProvider messageProvider;
	@EJB
	private NotificacionRxDao notificacionRxDao;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<RequestNotificacionDto> consultarNotificaciones(RequestGenericDto<FiltroNotificacionDto> request)
			throws BusinessException { 
		
		ResponseGenericDto<RequestNotificacionDto> respuesta = null;
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			respuesta = new ResponseGenericDto<RequestNotificacionDto>();
			respuesta.getAuditResponse().setTransaccionId(request.getAuditRequest().getTransaccionId());
			
			//1. Consultamos las notificaciones
			List<VNotificacion> notificacionResponse = notificacionRxDao.consultarNotificaciones(request.getObjectRequest());
			
			if(notificacionResponse == null || notificacionResponse.size() == 0) {
				throw new  DaoException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2,"Alertas Sistema"));
			}
			RequestNotificacionDto notificaciones = new RequestNotificacionDto();
			notificaciones.setTotal(notificacionResponse.size());
			
			for(VNotificacion items :notificacionResponse) {
				NotificacionDto nuevaNotificacion = new NotificacionDto();
				nuevaNotificacion.setDescripcion(items.getDescripcion());
				nuevaNotificacion.setEmisionEstado(items.getEmisionEstado());
				nuevaNotificacion.setEmisionFecha(items.getEmisionFecha());
				nuevaNotificacion.setEstado(items.getEstado());
				nuevaNotificacion.setEstadoId(items.getEstadoId());
				nuevaNotificacion.setId(items.getId());
				nuevaNotificacion.setPrioridad(items.getPrioridad());
				nuevaNotificacion.setPrioridadId(items.getPrioridadId());
				nuevaNotificacion.setTitulo(items.getTitulo());
				notificaciones.getListNotificaciones().add(nuevaNotificacion);
			}
			respuesta.setObjectResponse(notificaciones);
			respuesta.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			respuesta.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
			
		} catch (DaoException e) {
			throw new  BusinessException(e.getCodigo(), e.getMensaje());
		} catch(Exception e) {
			throw new  BusinessException(messageProvider.codigoErrorIdt2, MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}
		return respuesta;
	}
}
