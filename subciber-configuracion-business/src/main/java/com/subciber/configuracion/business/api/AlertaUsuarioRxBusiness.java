package com.subciber.configuracion.business.api;

import com.subciber.configuracion.base.dto.AuditRequestDto;
import com.subciber.configuracion.dto.RequestAlertaSistemaDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.BusinessException;

/**
 * @description Interface para las alertas del sistema
 * @author David Villanueva
 * @version 0.1, o5/03/2019
 * @update
 */
public interface AlertaUsuarioRxBusiness {

	/**
	 * @param metodo para consultar las alertas del sistema
	 * @return devuelve la informacion de las alertas  del sistema
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<RequestAlertaSistemaDto> consultarAlertasSistema(AuditRequestDto request) throws BusinessException;

}
