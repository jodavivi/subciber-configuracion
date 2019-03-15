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

import com.subciber.configuracion.business.api.TablaGenericaRxBusiness;
import com.subciber.configuracion.dao.api.TablaGenericaRxDao;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.dto.TablaGenericaDto;
import com.subciber.configuracion.dto.TablaGenericaFiltroDto;
import com.subciber.configuracion.dto.TablaGenericaResponseDto;
import com.subciber.configuracion.entity.VTablaGenerica;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;

/**
 * @description implementacion de la interface TablaGenericaRxBusiness
 * @author David Villanueva
 * @version 0.1, 11/03/2019
 * @update
 */
@Dependent
public class TablaGenericaRxBusinessImpl implements TablaGenericaRxBusiness, Serializable {

	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@Inject
    private MessageProvider messageProvider;
	@EJB
	private TablaGenericaRxDao tablaGenericaRxDao;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<TablaGenericaResponseDto> consultarTablaGenerica(
			RequestGenericDto<TablaGenericaFiltroDto> request) throws BusinessException {
		 
		ResponseGenericDto<TablaGenericaResponseDto> respuesta = null;
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			respuesta = new ResponseGenericDto<TablaGenericaResponseDto>();
			respuesta.getAuditResponse().setTransaccionId(request.getAuditRequest().getTransaccionId());
			
			List<VTablaGenerica> consultarTablaGenericaResponse =  tablaGenericaRxDao.consultarTablaGenerica(request.getObjectRequest());
			
			if(consultarTablaGenericaResponse == null) {
				throw new  DaoException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2,"Tabla Generica"));
			}
			TablaGenericaResponseDto listTablaGenericaesponse = new   TablaGenericaResponseDto();
			
			for(VTablaGenerica list: consultarTablaGenericaResponse) {
				TablaGenericaDto dto = new TablaGenericaDto();
				dto.setCodigoTabla(list.getCodigoTabla());
				dto.setDescripcionTabla(list.getDescripcionTabla());
				dto.setEstado(list.getEstado());
				dto.setEstadoId(list.getEstadoId());
				dto.setId(list.getId());
				dto.setOrden(list.getOrden());
				listTablaGenericaesponse.getTablaGenerica().add(dto);
			}
			respuesta.setObjectResponse(listTablaGenericaesponse);
			respuesta.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			respuesta.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
			
		} catch (DaoException e) {
			respuesta.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			respuesta.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch(Exception e) {
			respuesta.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			respuesta.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}
		return respuesta;
	}

}
