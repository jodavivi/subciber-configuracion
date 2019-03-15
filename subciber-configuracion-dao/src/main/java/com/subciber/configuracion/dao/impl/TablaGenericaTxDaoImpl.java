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
import com.subciber.configuracion.dao.api.TablaGenericaTxDao;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.dto.TablaGenericaDto;
import com.subciber.configuracion.entity.Generica;
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
public class TablaGenericaTxDaoImpl  extends BaseJPADao<Generica>  implements TablaGenericaTxDao {
	
	private static final long serialVersionUID = 1L;
	@Inject
    private MessageProvider messageProvider;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<Integer> registrarTablaGenerica(RequestGenericDto<TablaGenericaDto> request)
			throws DaoException {

		ResponseGenericDto<Integer> response = null;	
		try {
			response = new ResponseGenericDto<Integer>();
			response.getAuditResponse().setTransaccionId(request.getAuditRequest().getTerminal());
			Generica tabla = new Generica();
			tabla.setUsuarioCreador(request.getAuditRequest().getUsuario());
			tabla.setFechaCreacion(LocalDateTime.now());
			tabla.setTerminalCreacion(request.getAuditRequest().getTerminal());
			tabla.setAplicacionId(1);
			tabla.setCodigoTabla(request.getObjectRequest().getCodigoTabla());
			tabla.setDescripcionTabla(request.getObjectRequest().getDescripcionTabla());
			tabla.setEstadoId(request.getObjectRequest().getEstadoId());
			tabla.setFuente("APP");
			tabla.setTipo("T"); 
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
	public AuditResponseDto actualizarTablaGenerica(RequestGenericDto<TablaGenericaDto> request) throws DaoException {
		 
		AuditResponseDto response = null;	
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTerminal());
			
			//1. Consultamos la tabla  
			Generica tabla = (Generica) entityManager.find(entityClass, request.getObjectRequest().getId());
			if(tabla == null) {
				throw new DaoException(messageProvider.codigoErrorIdf7, messageProvider.mensajeErrorIdf7);
			}
			
			tabla.setUsuarioModificador(request.getAuditRequest().getUsuario());
			tabla.setFechaModificacion(LocalDateTime.now());
			
			tabla.setTerminalModificador(request.getAuditRequest().getTerminal());
			if(request.getObjectRequest().getCodigoTabla()!=null) {
				tabla.setCodigoTabla(request.getObjectRequest().getCodigoTabla());
			}
			if(request.getObjectRequest().getDescripcionTabla()!=null) {
				tabla.setCodigoTabla(request.getObjectRequest().getDescripcionTabla());
			}
			if(request.getObjectRequest().getEstadoId()!=null) {
				tabla.setEstadoId(request.getObjectRequest().getEstadoId());
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
	public AuditResponseDto eliminarTablaGenerica(RequestGenericDto<Integer> request) throws DaoException {
		 
		AuditResponseDto response = null;	
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTerminal());
			
			//1. Consultamos la tabla  
			Generica tabla = (Generica) entityManager.find(entityClass, request.getObjectRequest());
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
