package com.subciber.configuracion.business.api;

import com.subciber.configuracion.dto.FiltroNotificacionDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.RequestNotificacionDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.BusinessException;

/**
 * @description Interface para las notificaciones del sistema
 * @author David Villanueva
 * @version 0.1, o5/03/2019
 * @update
 */
public interface NotificacionRxBusiness {

	/**
	 * @param metodo para consultar las notificaciones del sistema
	 * @return devuelve la informacion de las alertas  del sistema
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<RequestNotificacionDto> consultarNotificaciones(RequestGenericDto<FiltroNotificacionDto> request) throws BusinessException;

}
