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
import javax.persistence.Query;

import com.subciber.configuracion.base.dao.BaseJPADao;
import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.dao.api.NotificacionUsuarioTxDao;
import com.subciber.configuracion.dto.NotificacionUsuarioDto;
import com.subciber.configuracion.dto.RequestDeleteDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.entity.NotificacionUsuario;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.util.ConstantesConfig;

/**
 * @author jose david villanueva villalobos
 * @Creacion 0.1, 13/03/2019
 * @Update
 * 
 */
@Stateless
public class NotificacionUsuarioTxDaoImpl extends BaseJPADao<NotificacionUsuario> implements NotificacionUsuarioTxDao {

	private static final long serialVersionUID = 1L;
	@Inject
    private MessageProvider messageProvider;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<Integer> registrarNotificacionUsuario(RequestGenericDto<NotificacionUsuarioDto> request)
			throws DaoException { 
		ResponseGenericDto<Integer> response = null;	
		try {
			response = new ResponseGenericDto<Integer>();
			response.getAuditResponse().setTransaccionId(request.getAuditRequest().getTerminal());
			NotificacionUsuario tabla = new NotificacionUsuario();
			tabla.setUsuarioCreador(request.getAuditRequest().getUsuario());
			tabla.setFechaCreacion(LocalDateTime.now());
			tabla.setTerminalCreacion(request.getAuditRequest().getTerminal());
			tabla.setEstadoId(request.getObjectRequest().getEstadoId());
			tabla.setNotificacionId(request.getObjectRequest().getNotificacionId());
			tabla.setAlertaTipoId(request.getObjectRequest().getAlertaTipoId());
			tabla.setUsuarios(request.getObjectRequest().getUsuarios());
			
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
	public AuditResponseDto actualizarNotificacionUsuario(RequestGenericDto<NotificacionUsuarioDto> request)
			throws DaoException { 
		AuditResponseDto response = null;	
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTerminal());
			
			//1. Consultamos la tabla  
			NotificacionUsuario tabla = (NotificacionUsuario) entityManager.find(entityClass, request.getObjectRequest().getId());
			if(tabla == null) {
				throw new DaoException(messageProvider.codigoErrorIdf7, messageProvider.mensajeErrorIdf7);
			}
			
			tabla.setUsuarioModificador(request.getAuditRequest().getUsuario());
			tabla.setFechaModificacion(LocalDateTime.now());
			tabla.setTerminalModificador(request.getAuditRequest().getTerminal());
			if(request.getObjectRequest().getAlertaTipoId()!=null) {
				tabla.setAlertaTipoId(request.getObjectRequest().getAlertaTipoId());
			}
			if(request.getObjectRequest().getEstadoId()!=null) {
				tabla.setEstadoId(request.getObjectRequest().getEstadoId());
			}
			if(request.getObjectRequest().getNotificacionId()!=null) {
				tabla.setNotificacionId(request.getObjectRequest().getNotificacionId());
			}
			if(request.getObjectRequest().getUsuarios()!=null) {
				tabla.setUsuarios(request.getObjectRequest().getUsuarios());
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
	public AuditResponseDto eliminarNotificacionUsuario(RequestGenericDto<Integer> request) throws DaoException { 
		AuditResponseDto response = null;	
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTerminal());
			
			//1. Consultamos la Notificacion  
			NotificacionUsuario tabla = (NotificacionUsuario) entityManager.find(entityClass, request.getObjectRequest());
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

	@Override
	public AuditResponseDto eliminarNotificacionUsuarioxId(RequestDeleteDto request) throws DaoException {
	
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		StringBuilder jpql 			= null;
		StringBuilder jpqlSelect 	= null;
		StringBuilder jpqlWhere  	= null;
		AuditResponseDto response 	= null;
		try {
			response = new AuditResponseDto();
			jpqlSelect = new StringBuilder();
			jpqlSelect.append("UPDATE ");
			jpqlSelect.append(entityClass.getSimpleName());
			jpqlSelect.append(" SET ");
			jpqlSelect.append("estadoId 			= 25, ");
			jpqlSelect.append("fechaModificacion 	= :fechaModificacion, ");
			jpqlSelect.append("terminalModificador 	= :terminalModificador, ");
			jpqlSelect.append("transaccionId 		= :transaccionId, ");
			jpqlSelect.append("usuarioModificador 	= :usuarioModificador ");
			
			jpqlWhere = new StringBuilder();
			jpqlWhere.append("WHERE ");
			jpqlWhere.append("estadoId 		= :estadoId ");
			jpqlWhere.append("and ");
			jpqlWhere.append("notificacionId  	= :notificacionId ");
			
			jpql = new StringBuilder();
			jpql.append(jpqlSelect);
			jpql.append(jpqlWhere);
			Query query = entityManager.createQuery(jpql.toString());
			query.setParameter("fechaModificacion", LocalDateTime.now());
			query.setParameter("terminalModificador", request.getAuditRequest().getTerminal());
			query.setParameter("transaccionId", request.getAuditRequest().getTransaccionId());
			query.setParameter("usuarioModificador", request.getAuditRequest().getUsuario());
			query.setParameter("estadoId", ConstantesConfig.activo);
			query.setParameter("notificacionId", request.getItems().get(0));
			query.executeUpdate();
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
		} catch (Exception e) {
			 Throwable th = e.getCause();
			  if(th.getCause() instanceof SQLException) {
				  SQLException cause = (SQLException) th.getCause();
				  response.setCodigoRespuesta(messageProvider.codigoErrorIdt1);
				  response.setMensajeRespuesta( MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, cause.getMessage()));
			  }
			  response.setCodigoRespuesta(messageProvider.codigoErrorIdt1);
			  response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		
		return response;
	}

}
