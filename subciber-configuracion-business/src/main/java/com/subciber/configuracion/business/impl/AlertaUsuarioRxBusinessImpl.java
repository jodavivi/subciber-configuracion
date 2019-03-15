/**
 * 
 */
package com.subciber.configuracion.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.subciber.configuracion.base.dto.AuditRequestDto;
import com.subciber.configuracion.business.api.AlertaUsuarioRxBusiness;
import com.subciber.configuracion.dao.api.AlertaUsuarioRxDao;
import com.subciber.configuracion.dto.AlertasSistemaDto;
import com.subciber.configuracion.dto.FiltroAlertasSistemaDto;
import com.subciber.configuracion.dto.RequestAlertaSistemaDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.entity.VAlertaUsuario;
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
public class AlertaUsuarioRxBusinessImpl implements AlertaUsuarioRxBusiness, Serializable {
	
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@Inject
    private MessageProvider messageProvider;
	@EJB
	private AlertaUsuarioRxDao alertaUsuarioRxDao;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<RequestAlertaSistemaDto> consultarAlertasSistema(AuditRequestDto request)
			throws BusinessException { 
		
		ResponseGenericDto<RequestAlertaSistemaDto> respuesta = null;
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			respuesta = new ResponseGenericDto<RequestAlertaSistemaDto>();
			respuesta.getAuditResponse().setTransaccionId(request.getTransaccionId());
			
			//1. Consultamos las alertas por usuario
			FiltroAlertasSistemaDto filtroAlertaSistema = new FiltroAlertasSistemaDto();
			filtroAlertaSistema.setUsuarioCodigo(request.getUsuario());
			filtroAlertaSistema.setEstadoRecibido("M");
			List<VAlertaUsuario> alertasSistemaResponse = alertaUsuarioRxDao.alertasSistema(filtroAlertaSistema);
			
			if(alertasSistemaResponse == null || alertasSistemaResponse.size() == 0) {
				throw new  DaoException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2,"Alertas Sistema"));
			}
			RequestAlertaSistemaDto alertaSistema = new RequestAlertaSistemaDto();
			alertaSistema.setNumAlertas(alertasSistemaResponse.size());
			
			for(VAlertaUsuario alerta :alertasSistemaResponse) {
				AlertasSistemaDto nuevaAlerta = new AlertasSistemaDto();
				nuevaAlerta.setAlertaTipo(alerta.getAlertaTipo());
				nuevaAlerta.setDescripcion(alerta.getDescripcion());
				nuevaAlerta.setId(alerta.getId());
				nuevaAlerta.setPrioridad(alerta.getPrioridad());
				nuevaAlerta.setCodigoPrioridad(alerta.getCodigoPrioridad());
				if(alerta.getFechaCreacion() != null) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				    String formatDateTime = alerta.getFechaCreacion().format(formatter);
				    nuevaAlerta.setTiempoExpirado(formatDateTime);
				}
				nuevaAlerta.setTitulo(alerta.getTitulo());
				alertaSistema.getAlertasSistema().add(nuevaAlerta);
			}
			respuesta.setObjectResponse(alertaSistema);
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
