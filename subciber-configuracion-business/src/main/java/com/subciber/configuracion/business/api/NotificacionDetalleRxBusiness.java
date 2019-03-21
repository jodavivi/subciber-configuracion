package com.subciber.configuracion.business.api;

import com.subciber.configuracion.dto.FiltroNotificacionDetalleDto;
import com.subciber.configuracion.dto.NotificacionDetalleResponseDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.BusinessException;

/**
 * @description Interface para las consultas del detalle de la notificacion
 * @author David Villanueva
 * @version 0.1, 16/03/2019
 * @update
 */
public interface NotificacionDetalleRxBusiness {

	/**
	 * @param metodo para consultar el detalle de la  notificacion
	 * @return devuelve la informacion de las alertas  del sistema
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<NotificacionDetalleResponseDto> consultarNotificacionDetalle(RequestGenericDto<FiltroNotificacionDetalleDto> request) throws BusinessException;

}
