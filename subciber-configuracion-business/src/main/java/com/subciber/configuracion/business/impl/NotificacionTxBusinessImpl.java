/**
 * 
 */
package com.subciber.configuracion.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.business.api.NotificacionTxBusiness;
import com.subciber.configuracion.dao.api.NotificacionTxDao;
import com.subciber.configuracion.dao.api.NotificacionUsuarioTxDao;
import com.subciber.configuracion.dto.CrearNotificacionDto;
import com.subciber.configuracion.dto.NotificacionDetalleDto;
import com.subciber.configuracion.dto.NotificacionDto;
import com.subciber.configuracion.dto.NotificacionUsuarioDto;
import com.subciber.configuracion.dto.RequestDeleteDto;
import com.subciber.configuracion.dto.RequestDeleteObjectDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;
import com.subciber.configuracion.util.ConstantesConfig;
import com.subciber.configuracion.util.JAXBUtil;

/**
 * @description implementacion de la interface TablaGenericaTxBusiness
 * @author David Villanueva
 * @version 0.1, 11/03/2019
 * @update
 */
@Dependent
public class NotificacionTxBusinessImpl implements NotificacionTxBusiness , Serializable {

	private static final long serialVersionUID = 1L;
	static final Logger logger = LoggerFactory.getLogger(NotificacionTxBusinessImpl.class);
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	long timeStart = 0;
	String transactionId = null;
	
	@Inject
    private MessageProvider messageProvider;
	@EJB
	private NotificacionTxDao notificacionTxDao; 
	@EJB
	private NotificacionUsuarioTxDao notificacionUsuarioTxDao; 
	
	
	@Override
	public ResponseGenericDto<Integer> registrarNotificacion(RequestGenericDto<CrearNotificacionDto> request)
			throws BusinessException {
		ResponseGenericDto<Integer> response = null;
		try {
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			response = new ResponseGenericDto<Integer>();
			response.getAuditResponse().setTransaccionId(transactionId);	
			
			//1. Creamos la notificacion
			RequestGenericDto<NotificacionDto> inputNotificacion = new RequestGenericDto<NotificacionDto>();
			inputNotificacion.setAuditRequest(request.getAuditRequest());
			NotificacionDto notificacionDto = new NotificacionDto();
			notificacionDto.setDescripcion(request.getObjectRequest().getDescripcion());
			notificacionDto.setEmisionEstado("P");
			
			DateTimeFormatter formatterFechaEmision= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			notificacionDto.setEmisionFecha(LocalDateTime.parse(request.getObjectRequest().getEmisionFecha(), formatterFechaEmision));
			notificacionDto.setEstadoId(ConstantesConfig.activo);
			notificacionDto.setPrioridadId(request.getObjectRequest().getPrioridadId());
			notificacionDto.setTitulo(request.getObjectRequest().getTitulo());
			inputNotificacion.setObjectRequest(notificacionDto);
			ResponseGenericDto<Integer> registrarNotificacionResponse = notificacionTxDao.registrarNotificacion(inputNotificacion);
			
			if(registrarNotificacionResponse.getAuditResponse().getCodigoRespuesta() != messageProvider.codigoExito) {
				throw new DaoException(registrarNotificacionResponse.getAuditResponse().getCodigoRespuesta(), registrarNotificacionResponse.getAuditResponse().getMensajeRespuesta());
			}
			
			// 2. Registramos el detalle
			
			for(NotificacionDetalleDto detalle: request.getObjectRequest().getItemsNotificaciones()) {
				RequestGenericDto<NotificacionUsuarioDto> inputNotificacionUsuario = new RequestGenericDto<NotificacionUsuarioDto>();
				inputNotificacionUsuario.setAuditRequest(request.getAuditRequest());
				NotificacionUsuarioDto notificacionUsuarioDto = new NotificacionUsuarioDto();
				notificacionUsuarioDto.setAlertaTipoId(detalle.getAlertaTipoId());
				notificacionUsuarioDto.setEstadoId(ConstantesConfig.activo);
				notificacionUsuarioDto.setNotificacionId(Integer.valueOf(registrarNotificacionResponse.getObjectResponse()));
				
				//obtenemos la lista de usuarios
				StringBuilder usuarios = new StringBuilder();
				for(NotificacionDetalleDto item : request.getObjectRequest().getItemsNotificaciones()){
					for(String user : item.getUsuarios()) {
						usuarios.append(user);
						usuarios.append("|");
					}
				}
				String users = "";
				if(usuarios.toString() != null && usuarios.toString().length() > 0) {
					users = usuarios.toString().substring(usuarios.toString().length()-1);
				}
				
				notificacionUsuarioDto.setUsuarios(users); 
				inputNotificacionUsuario.setObjectRequest(notificacionUsuarioDto);
				ResponseGenericDto<Integer> registrarNotificacionUsuarioResponse =  notificacionUsuarioTxDao.registrarNotificacionUsuario(inputNotificacionUsuario);
				if(registrarNotificacionUsuarioResponse.getAuditResponse().getCodigoRespuesta() != messageProvider.codigoExito) {
					throw new DaoException(registrarNotificacionUsuarioResponse.getAuditResponse().getCodigoRespuesta(), registrarNotificacionUsuarioResponse.getAuditResponse().getMensajeRespuesta());
				}
			}
			
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
	public AuditResponseDto actualizarNotificacion(RequestGenericDto<CrearNotificacionDto> request)
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
			
			//1. Actualizamos la notificacion
			RequestGenericDto<NotificacionDto> inputNotificacion = new RequestGenericDto<NotificacionDto>();
			inputNotificacion.setAuditRequest(request.getAuditRequest());
			NotificacionDto notificacionDto = new NotificacionDto();
			notificacionDto.setId(request.getObjectRequest().getId());
			notificacionDto.setDescripcion(request.getObjectRequest().getDescripcion());
			DateTimeFormatter formatterFechaEmision= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			notificacionDto.setEmisionFecha(LocalDateTime.parse(request.getObjectRequest().getEmisionFecha(), formatterFechaEmision));
			notificacionDto.setEstadoId(request.getObjectRequest().getEstadoId());
			notificacionDto.setPrioridadId(request.getObjectRequest().getPrioridadId());
			notificacionDto.setTitulo(request.getObjectRequest().getTitulo());
			inputNotificacion.setObjectRequest(notificacionDto);
			AuditResponseDto actualizarNotificacionResponse = notificacionTxDao.actualizarNotificacion(inputNotificacion);
			
			if(actualizarNotificacionResponse.getCodigoRespuesta() != messageProvider.codigoExito) {
				throw new DaoException(actualizarNotificacionResponse.getCodigoRespuesta(), actualizarNotificacionResponse.getMensajeRespuesta());
			}
			
			// 2. Actualizamos  el detalle
			
			for(NotificacionDetalleDto detalle: request.getObjectRequest().getItemsNotificaciones()) {
				
				if(detalle.getAccion().equalsIgnoreCase("add")) {
				
					RequestGenericDto<NotificacionUsuarioDto> inputNotificacionUsuario = new RequestGenericDto<NotificacionUsuarioDto>();
					inputNotificacionUsuario.setAuditRequest(request.getAuditRequest());
					NotificacionUsuarioDto notificacionUsuarioDto = new NotificacionUsuarioDto();
					notificacionUsuarioDto.setAlertaTipoId(detalle.getAlertaTipoId());
					notificacionUsuarioDto.setEstadoId(ConstantesConfig.activo);
					notificacionUsuarioDto.setNotificacionId(request.getObjectRequest().getId());
					
					//obtenemos la lista de usuarios
					StringBuilder usuarios = new StringBuilder();
					for(NotificacionDetalleDto item : request.getObjectRequest().getItemsNotificaciones()){
						for(String user : item.getUsuarios()) {
							usuarios.append(user);
							usuarios.append("|");
						}
					}
					String users = "";
					if(usuarios.toString() != null && usuarios.toString().length() > 0) {
						users = usuarios.toString().substring(usuarios.toString().length()-1);
					}
					
					notificacionUsuarioDto.setUsuarios(users); 
					inputNotificacionUsuario.setObjectRequest(notificacionUsuarioDto);
					ResponseGenericDto<Integer> registrarNotificacionUsuarioResponse =  notificacionUsuarioTxDao.registrarNotificacionUsuario(inputNotificacionUsuario);
					if(registrarNotificacionUsuarioResponse.getAuditResponse().getCodigoRespuesta() != messageProvider.codigoExito) {
						throw new DaoException(registrarNotificacionUsuarioResponse.getAuditResponse().getCodigoRespuesta(), registrarNotificacionUsuarioResponse.getAuditResponse().getMensajeRespuesta());
					}
				
				}
				
				if(detalle.getAccion().equalsIgnoreCase("upd")) {
					
					RequestGenericDto<NotificacionUsuarioDto> inputNotificacionUsuario = new RequestGenericDto<NotificacionUsuarioDto>();
					inputNotificacionUsuario.setAuditRequest(request.getAuditRequest());
					NotificacionUsuarioDto notificacionUsuarioDto = new NotificacionUsuarioDto();
					notificacionUsuarioDto.setId(detalle.getId());
					notificacionUsuarioDto.setAlertaTipoId(detalle.getAlertaTipoId());
					notificacionUsuarioDto.setEstadoId(ConstantesConfig.activo);
					
					//obtenemos la lista de usuarios
					StringBuilder usuarios = new StringBuilder();
					for(NotificacionDetalleDto item : request.getObjectRequest().getItemsNotificaciones()){
						for(String user : item.getUsuarios()) {
							usuarios.append(user);
							usuarios.append("|");
						}
					}
					String users = "";
					if(usuarios.toString() != null && usuarios.toString().length() > 0) {
						users = usuarios.toString().substring(usuarios.toString().length()-1);
					}
					notificacionUsuarioDto.setUsuarios(users); 
					inputNotificacionUsuario.setObjectRequest(notificacionUsuarioDto);
					AuditResponseDto actualizarNotificacionUsuarioResponse =  notificacionUsuarioTxDao.actualizarNotificacionUsuario(inputNotificacionUsuario);
					if(actualizarNotificacionUsuarioResponse.getCodigoRespuesta() != messageProvider.codigoExito) {
						throw new DaoException(actualizarNotificacionUsuarioResponse.getCodigoRespuesta(), actualizarNotificacionUsuarioResponse.getMensajeRespuesta());
					}
				}
				if(detalle.getAccion().equalsIgnoreCase("del")) {
					RequestGenericDto<Integer> inputNotificacionUsuario = new RequestGenericDto<Integer>();
					inputNotificacionUsuario.setAuditRequest(request.getAuditRequest());
					inputNotificacionUsuario.setObjectRequest(detalle.getId());
					AuditResponseDto eliminarNotificacionUsuarioResponse =  notificacionUsuarioTxDao.eliminarNotificacionUsuario(inputNotificacionUsuario);
					if(eliminarNotificacionUsuarioResponse.getCodigoRespuesta() != messageProvider.codigoExito) {
						throw new DaoException(eliminarNotificacionUsuarioResponse.getCodigoRespuesta(), eliminarNotificacionUsuarioResponse.getMensajeRespuesta());
					}
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

	@Override
	public AuditResponseDto eliminarNotificacion(RequestGenericDto<RequestDeleteObjectDto> request)
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

			//1. Eliminamos la notificacion
			
			for(Integer notificacion :  request.getObjectRequest().getItems()) {
				RequestGenericDto<Integer> requestNotificacion = new RequestGenericDto<Integer>();
				requestNotificacion.setAuditRequest(request.getAuditRequest());
				requestNotificacion.setObjectRequest(notificacion);
				AuditResponseDto eliminarNotificacionResponse = notificacionTxDao.eliminarNotificacion(requestNotificacion);
				if(eliminarNotificacionResponse.getCodigoRespuesta()  != messageProvider.codigoExito) {
					throw new DaoException(eliminarNotificacionResponse.getCodigoRespuesta(), eliminarNotificacionResponse.getMensajeRespuesta());
				}
				
				RequestDeleteDto requestNotificaccionUsuario = new RequestDeleteDto();
				requestNotificaccionUsuario.setAuditRequest(request.getAuditRequest());
				requestNotificaccionUsuario.getItems().add(notificacion);
				AuditResponseDto eliminarNotificacionUsuarioxIdResponse = notificacionUsuarioTxDao.eliminarNotificacionUsuarioxId(requestNotificaccionUsuario);
				if(eliminarNotificacionUsuarioxIdResponse.getCodigoRespuesta()  != messageProvider.codigoExito) {
					throw new DaoException(eliminarNotificacionUsuarioxIdResponse.getCodigoRespuesta(), eliminarNotificacionUsuarioxIdResponse.getMensajeRespuesta());
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
