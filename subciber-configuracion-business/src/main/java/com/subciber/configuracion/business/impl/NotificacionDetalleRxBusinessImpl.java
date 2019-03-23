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

import com.subciber.configuracion.business.api.NotificacionDetalleRxBusiness;
import com.subciber.configuracion.dao.api.NotificacionDetalleRxDao;
import com.subciber.configuracion.dao.api.NotificacionRxDao;
import com.subciber.configuracion.dto.FiltroNotificacionDetalleDto;
import com.subciber.configuracion.dto.FiltroNotificacionDto;
import com.subciber.configuracion.dto.NotificacionDetalleDto;
import com.subciber.configuracion.dto.NotificacionDetalleResponseDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.entity.VNotificacion;
import com.subciber.configuracion.entity.VNotificacionDetalle;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.util.Utilitario;

/**
 * @description implementacion de la interface AlertaUsuarioRxBusiness
 * @author David Villanueva
 * @version 0.1, 05/03/2019
 * @update
 */
@Dependent
public class NotificacionDetalleRxBusinessImpl implements NotificacionDetalleRxBusiness, Serializable {
	
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@Inject
    private MessageProvider messageProvider;
	@Inject
    private Utilitario utilitario;
	@EJB
	private NotificacionRxDao notificacionRxDao;
	@EJB
	private NotificacionDetalleRxDao notificacionDetalleRxDao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<NotificacionDetalleResponseDto> consultarNotificacionDetalle(RequestGenericDto<FiltroNotificacionDetalleDto> request)
			throws BusinessException { 
		
		ResponseGenericDto<NotificacionDetalleResponseDto> respuesta = null;
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			respuesta = new ResponseGenericDto<NotificacionDetalleResponseDto>();
			respuesta.getAuditResponse().setTransaccionId(request.getAuditRequest().getTransaccionId());
			
			//1. Consultamos la notificacion
			FiltroNotificacionDto notificacionFiltro = new FiltroNotificacionDto();
			notificacionFiltro.setNotificacionId(request.getObjectRequest().getNotificacionId());
			List<VNotificacion> notificacionResponse = notificacionRxDao.consultarNotificaciones(notificacionFiltro);
			
			if(notificacionResponse == null || notificacionResponse.size() == 0) {
				throw new  DaoException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2,"Notificacion"));
			}
			
			for(VNotificacion items :notificacionResponse) {
				NotificacionDetalleResponseDto  detalle = new NotificacionDetalleResponseDto();
				detalle.setDescripcion(items.getDescripcion());
				detalle.setEmisionEstado(items.getEmisionEstado());
				detalle.setEmisionFecha(utilitario.fechaToString(items.getEmisionFecha()));
				detalle.setEstado(items.getEstado());
				detalle.setEstadoId(items.getEstadoId());
				detalle.setId(items.getId());
				detalle.setPrioridad(items.getPrioridad());
				detalle.setPrioridadId(items.getPrioridadId());
				detalle.setTitulo(items.getTitulo());
				respuesta.setObjectResponse(detalle);
			}
			
			//2 consultamos el detalle de la notificacion
			FiltroNotificacionDetalleDto filtroDetalle = new FiltroNotificacionDetalleDto();
			filtroDetalle.setNotificacionId(request.getObjectRequest().getNotificacionId());
			List<VNotificacionDetalle> consultarNotificacionesDetalleResponse = notificacionDetalleRxDao.consultarNotificacionesDetalle(filtroDetalle);
			
			if(consultarNotificacionesDetalleResponse != null && consultarNotificacionesDetalleResponse.size() > 0) {
				for(VNotificacionDetalle items : consultarNotificacionesDetalleResponse) {
					NotificacionDetalleDto detalle = new NotificacionDetalleDto();
					detalle.setId(items.getId());
					detalle.setAlertaTipo(items.getAlertaTipo());
					detalle.setAlertaTipoId(items.getAlertaTipoId());
					String[] parts = items.getUsuarios().split("\\|");
					for(String usuario : parts) {
						detalle.getUsuarios().add(usuario);
					}
					respuesta.getObjectResponse().getItemDetalle().add(detalle);
				}
			}
			
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
