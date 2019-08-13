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
import com.subciber.configuracion.dao.api.AlertaUsuarioTxDao;
import com.subciber.configuracion.dto.AlertasUsuarioDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.entity.AlertaUsuario;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.util.ConstantesConfig;

/**
 * @author jose david villanueva villalobos
 * @Creacion 0.1, 15/03/2019
 * @Update 10/08/2019 - David Villanueva
 * 
 */
@Stateless
public class AlertaUsuarioTxDaoImpl extends BaseJPADao<AlertaUsuario> implements AlertaUsuarioTxDao {

	private static final long serialVersionUID = 1L;
	@Inject
    private MessageProvider messageProvider;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditResponseDto registrarAlertaUsuario(RequestGenericDto<AlertasUsuarioDto> request) throws DaoException {
		 
		AuditResponseDto response = null;	
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTerminal());
			
			AlertaUsuario campo = new AlertaUsuario();
			campo.setUsuarioCreador(request.getAuditRequest().getUsuario());
			campo.setFechaCreacion(LocalDateTime.now());
			campo.setTerminalCreacion(request.getAuditRequest().getTerminal());
			campo.setEstadoId(request.getObjectRequest().getEstadoId());
			campo.setAlertaTipoId(request.getObjectRequest().getAlertaTipoId());
			campo.setDescripcion(request.getObjectRequest().getDescripcion());
			campo.setEstadoRecibido(request.getObjectRequest().getEstadoRecibido());
			campo.setPrioridadId(request.getObjectRequest().getPrioridadId());
			campo.setTitulo(request.getObjectRequest().getTitulo());
			campo.setUsuarioId(request.getObjectRequest().getUsuarioId());
			
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			entityManager.merge(campo);
			entityManager.flush();
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
			
		}catch(PersistenceException e) {
			  Throwable th = e.getCause();
				  if(th.getCause() instanceof SQLException) {
					  SQLException cause = (SQLException) th.getCause();
					  if(cause.getSQLState().toString().equals("23505")) {
						  response.setCodigoRespuesta(messageProvider.codigoErrorIdf6);
					  }else {
						  response.setCodigoRespuesta(messageProvider.codigoErrorIdt1);
						  response.setMensajeRespuesta( MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, cause.getMessage()));
					  }
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
	public AuditResponseDto eliminarAlertaUsuario(RequestGenericDto<Integer> request) throws DaoException {
		AuditResponseDto response = null;	
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTerminal());
			
			//1. Consultamos la tabla  
			AlertaUsuario tabla = (AlertaUsuario) entityManager.find(entityClass, request.getObjectRequest());
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
