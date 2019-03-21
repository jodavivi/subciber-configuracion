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

import com.subciber.configuracion.business.api.CampoGenericaRxBusiness;
import com.subciber.configuracion.dao.api.CampoGenericaRxDao;
import com.subciber.configuracion.dto.CampoGenericaDto;
import com.subciber.configuracion.dto.CampoGenericaFiltroDto;
import com.subciber.configuracion.dto.CampoGenericaResponseDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.entity.VCampoGenerica;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;

/**
 * @description implementacion de la interface CampoGenericaRxBusiness
 * @author David Villanueva
 * @version 0.1, 12/03/2019
 * @update
 */
@Dependent
public class CampoGenericaRxBusinessImpl implements CampoGenericaRxBusiness, Serializable{

	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@Inject
    private MessageProvider messageProvider;
	@EJB
	private CampoGenericaRxDao campoGenericaRxDao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<CampoGenericaResponseDto> consultarCampoGenerica(
			RequestGenericDto<CampoGenericaFiltroDto> request) throws BusinessException { 
		
		ResponseGenericDto<CampoGenericaResponseDto> respuesta = null;
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			respuesta = new ResponseGenericDto<CampoGenericaResponseDto>();
			respuesta.getAuditResponse().setTransaccionId(request.getAuditRequest().getTransaccionId());
			
			List<VCampoGenerica> consultarCampoGenericaResponse =  campoGenericaRxDao.consultarCampoGenerica(request.getObjectRequest());
			
			if(consultarCampoGenericaResponse == null) {
				throw new  DaoException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2,"Campo Generica"));
			}
			CampoGenericaResponseDto listCampoGenericaesponse = new   CampoGenericaResponseDto();
			
			for(VCampoGenerica list: consultarCampoGenericaResponse) {
				CampoGenericaDto dto = new CampoGenericaDto();
				dto.setCampo(list.getCampo());
				dto.setCodigoIntegracion(list.getCodigoIntegracion());
				dto.setCodigoTabla(list.getCodigoTabla());
				dto.setDescripcionCampo1(list.getDescripcionCampo1());
				dto.setDescripcionCampo2(list.getDescripcionCampo2());
				dto.setEstado(list.getEstado());
				dto.setId(list.getId());
				dto.setEstadoId(list.getEstadoId());
				dto.setOrden(list.getOrden());
				dto.setPadreId(list.getPadreId());
				
				listCampoGenericaesponse.getCampoGenerica().add(dto);
			}
			respuesta.setObjectResponse(listCampoGenericaesponse);
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
