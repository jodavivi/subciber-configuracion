/**
 * 
 */
package com.subciber.configuracion.dao.impl;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.time.LocalDateTime;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import com.subciber.configuracion.base.dao.BaseJPADao;
import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.dao.api.NotificacionTxDao;
import com.subciber.configuracion.dao.util.ConfigDao;
import com.subciber.configuracion.dto.NotificacionDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.entity.Notificacion;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.util.ConstantesConfig;
import com.subciber.configuracion.util.Utilitario;

/**
 * @author jose david villanueva villalobos
 * @Creacion 0.1, 13/03/2019
 * @Update
 * 
 */
@Stateless
public class NotificacionTxDaoImpl extends BaseJPADao<Notificacion> implements NotificacionTxDao {

	private static final long serialVersionUID = 1L;
	@Inject
    private MessageProvider messageProvider;
	@Inject
	private Utilitario utilitario;
	
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	/**
	 * {@inheritDoc}
	 */
	public ResponseGenericDto<Integer> registrarNotificacion(RequestGenericDto<NotificacionDto> request)
			throws DaoException {
 
		ResponseGenericDto<Integer> response = null;	
		try {
			response = new ResponseGenericDto<Integer>();
			response.getAuditResponse().setTransaccionId(request.getAuditRequest().getTerminal());
			
			//obetenemos el Id
			Integer idGenerado = utilitario.generarSequenciaQuery(entityManager, ConfigDao.sequenceNotificacion);
			
			Notificacion tabla = new Notificacion();
			tabla.setId(idGenerado);
			tabla.setUsuarioCreador(request.getAuditRequest().getUsuario());
			tabla.setFechaCreacion(LocalDateTime.now());
			tabla.setTerminalCreacion(request.getAuditRequest().getTerminal());
			tabla.setDescripcion(request.getObjectRequest().getDescripcion());
			tabla.setEmisionEstado(request.getObjectRequest().getEmisionEstado());
			tabla.setEmisionFecha(utilitario.stringTofecha(request.getObjectRequest().getEmisionFecha()));
			tabla.setEstadoId(request.getObjectRequest().getEstadoId());
			tabla.setPrioridadId(request.getObjectRequest().getPrioridadId());
			tabla.setTitulo(request.getObjectRequest().getTitulo());
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			entityManager.merge(tabla);
			entityManager.flush();
			response.setObjectResponse(tabla.getId());
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
			
		}catch(PersistenceException e) {
			  Throwable th = e.getCause();
				  if(th.getCause() instanceof SQLException) {
					  SQLException cause = (SQLException) th.getCause();
					  System.out.println(cause.getSQLState());
						  if(cause.getSQLState().toString().equals("23505")) {
							  response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdf6);
							  response.getAuditResponse().setMensajeRespuesta( MessageFormat.format(messageProvider.mensajeErrorIdf6, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, cause.getMessage()));
						  }else {
							  response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt1);
							  response.getAuditResponse().setMensajeRespuesta( MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, cause.getMessage()));
						  }
					  }
			 
		}catch(Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt1);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		return response; 
	}

	/**
	 * {@inheritDoc}
	 */ 
	@Override
	public AuditResponseDto actualizarNotificacion(RequestGenericDto<NotificacionDto> request) throws DaoException {
 
		AuditResponseDto response = null;	
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTerminal());
			
			//1. Consultamos la tabla  
			Notificacion tabla = (Notificacion) entityManager.find(entityClass, request.getObjectRequest().getId());
			if(tabla == null) {
				throw new DaoException(messageProvider.codigoErrorIdf7, messageProvider.mensajeErrorIdf7);
			}
			
			tabla.setUsuarioModificador(request.getAuditRequest().getUsuario());
			tabla.setFechaModificacion(LocalDateTime.now());
			tabla.setTerminalModificador(request.getAuditRequest().getTerminal());
			
			if(request.getObjectRequest().getDescripcion()!=null) {
				tabla.setDescripcion(request.getObjectRequest().getDescripcion());
			}
			if(request.getObjectRequest().getEmisionEstado()!=null) {
				tabla.setEmisionEstado(request.getObjectRequest().getEmisionEstado());
			}
			if(request.getObjectRequest().getEmisionFecha()!=null) {
				tabla.setEmisionFecha(utilitario.stringTofecha(request.getObjectRequest().getEmisionFecha()));
			}
			if(request.getObjectRequest().getEstadoId()!=null) {
				tabla.setEstadoId(request.getObjectRequest().getEstadoId());
			}
			if(request.getObjectRequest().getPrioridadId()!=null) {
				tabla.setPrioridadId(request.getObjectRequest().getPrioridadId());
			}
			if(request.getObjectRequest().getTitulo()!=null) {
				tabla.setTitulo(request.getObjectRequest().getTitulo());
			}
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			entityManager.persist(tabla);
			entityManager.flush();
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
			
		}catch(DaoException e) {
			 response.setCodigoRespuesta( e.getCodigo());
			 response.setMensajeRespuesta( e.getMensaje());

		}catch(PersistenceException e) {
			  Throwable th = e.getCause();
				  if(th.getCause() instanceof SQLException) {
					  SQLException cause = (SQLException) th.getCause();
					  response.setCodigoRespuesta(messageProvider.codigoErrorIdt1);
					  response.setMensajeRespuesta( MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, cause.getMessage()));
				  	}
			 
		}catch(Exception e) {
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt1);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		return response; 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditResponseDto eliminarNotificacion(RequestGenericDto<Integer> request) throws DaoException {
 
		AuditResponseDto response = null;	
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTerminal());
			
			//1. Consultamos la Notificacion  
			Notificacion tabla = (Notificacion) entityManager.find(entityClass, request.getObjectRequest());
			if(tabla == null) {
				throw new DaoException(messageProvider.codigoErrorIdf7, messageProvider.mensajeErrorIdf7);
			}
			tabla.setUsuarioModificador(request.getAuditRequest().getUsuario());
			tabla.setFechaModificacion(LocalDateTime.now());
			tabla.setTerminalModificador(request.getAuditRequest().getTerminal());
			tabla.setId(request.getObjectRequest());
			tabla.setEstadoId(ConstantesConfig.eliminado);
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			entityManager.persist(tabla);
			entityManager.flush();
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
			
		}catch(PersistenceException e) {
			  Throwable th = e.getCause();
				  if(th.getCause() instanceof SQLException) {
					  SQLException cause = (SQLException) th.getCause();
					  response.setCodigoRespuesta(messageProvider.codigoErrorIdt1);
					  response.setMensajeRespuesta( MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, cause.getMessage()));
				  	}
			 
		}catch(Exception e) {
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt1);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		return response; 
	}

}
