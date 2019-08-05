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

import com.subciber.configuracion.base.dao.GenericaJPADaoImpl;
import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.dao.api.CampoGenericaTxDao;
import com.subciber.configuracion.dao.util.ConfigDao;
import com.subciber.configuracion.dto.CampoGenericaDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
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
public class CampoGenericaTxDaoImpl  extends GenericaJPADaoImpl<Generica>  implements CampoGenericaTxDao {
	
	private static final long serialVersionUID = 1L;
	@Inject
    private MessageProvider messageProvider;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<Integer> registrarCampoGenerica(RequestGenericDto<CampoGenericaDto> request)
			throws DaoException {

		ResponseGenericDto<Integer> response = null;	
		try {
			response = new ResponseGenericDto<Integer>();
			response.getAuditResponse().setTransaccionId(request.getAuditRequest().getTerminal());
			Integer id = obtenerId(ConfigDao.sequenceGenerica);
			
			Generica campo = new Generica();
			campo.setId(id);
			campo.setUsuarioCreador(request.getAuditRequest().getUsuario());
			campo.setFechaCreacion(LocalDateTime.now());
			campo.setTerminalCreacion(request.getAuditRequest().getTerminal());
			campo.setAplicacionId(1);
			campo.setEstadoId(request.getObjectRequest().getEstadoId());
			campo.setCampo(request.getObjectRequest().getCampo());
			campo.setCodigoIntegracion(request.getObjectRequest().getCodigoIntegracion());
			campo.setCodigoTabla(request.getObjectRequest().getCodigoTabla());
			campo.setDescripcionCampo1(request.getObjectRequest().getDescripcionCampo1());
			campo.setDescripcionCampo2(request.getObjectRequest().getDescripcionCampo2());
			campo.setOrden(request.getObjectRequest().getOrden());
			campo.setPadreId(request.getObjectRequest().getPadreId());
			campo.setFuente("APP");
			campo.setTipo("C"); 
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			entityManager.merge(campo);
			entityManager.flush();
			response.setObjectResponse(id);
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
			
		}catch(PersistenceException e) {
			  Throwable th = e.getCause();
				  if(th.getCause() instanceof SQLException) {
					  SQLException cause = (SQLException) th.getCause();
					  response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt1);
					  response.getAuditResponse().setMensajeRespuesta( MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, cause.getMessage()));
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
	public AuditResponseDto actualizarCampoGenerica(RequestGenericDto<CampoGenericaDto> request) throws DaoException {
		 
		AuditResponseDto response = null;	
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTerminal());
			
			//1. Consultamos el campo 
			Generica campo = (Generica) entityManager.find(entityClass, request.getObjectRequest().getId());
			if(campo == null) {
				throw new DaoException(messageProvider.codigoErrorIdf7, messageProvider.mensajeErrorIdf7);
			}
			campo.setUsuarioModificador(request.getAuditRequest().getUsuario());
			campo.setFechaModificacion(LocalDateTime.now());
			campo.setTerminalModificador(request.getAuditRequest().getTerminal());

			if(request.getObjectRequest().getCampo() != null) {
				campo.setCampo(request.getObjectRequest().getCampo());
			}
			
			if(request.getObjectRequest().getCodigoIntegracion() != null) {
				campo.setCodigoIntegracion(request.getObjectRequest().getCodigoIntegracion());
			}
			
			if(request.getObjectRequest().getCodigoTabla() != null) {
				campo.setCodigoTabla(request.getObjectRequest().getCodigoTabla());
			}

			if(request.getObjectRequest().getDescripcionCampo1() != null) {
				campo.setDescripcionCampo1(request.getObjectRequest().getDescripcionCampo1());
			}
			
			if(request.getObjectRequest().getDescripcionCampo2() != null) {
				campo.setDescripcionCampo2(request.getObjectRequest().getDescripcionCampo2());
			}
			
			if(request.getObjectRequest().getEstadoId() != null) {
				campo.setEstadoId(request.getObjectRequest().getEstadoId());
			}
			
			if(request.getObjectRequest().getOrden() != null) {
				campo.setOrden(request.getObjectRequest().getOrden());
			}
			
			if(request.getObjectRequest().getPadreId() != null) {
				campo.setPadreId(request.getObjectRequest().getPadreId());
			}
			
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			entityManager.persist(campo);
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditResponseDto eliminarCampoGenerica(RequestGenericDto<Integer> request) throws DaoException {
		 
		AuditResponseDto response = null;	
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTerminal());
			//1. Consultamos el campo 
			Generica campo = (Generica) entityManager.find(entityClass, request.getObjectRequest());
			if(campo == null) {
				throw new DaoException(messageProvider.codigoErrorIdf7, messageProvider.mensajeErrorIdf7);
			}
			campo.setUsuarioModificador(request.getAuditRequest().getUsuario());
			campo.setFechaModificacion(LocalDateTime.now());
			campo.setTerminalModificador(request.getAuditRequest().getTerminal());
			campo.setTransaccionId(request.getAuditRequest().getTransaccionId());
			campo.setEstadoId(ConstantesConfig.eliminado);
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			entityManager.persist(campo);
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
