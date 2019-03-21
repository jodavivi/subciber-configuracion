package com.subciber.configuracion.business.api;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.dto.CrearNotificacionDto;
import com.subciber.configuracion.dto.RequestDeleteObjectDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.BusinessException;

/**
 * @description Interface para la tabla generica
 * @author David Villanueva
 * @version 0.1, 11/03/2019
 * @update
 */
public interface NotificacionTxBusiness {

	/**
	 * @param metodo para registrar una notificaicon
	 * @return devuelve el Id de la notificacion creada
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<Integer> registrarNotificacion(RequestGenericDto<CrearNotificacionDto> request) throws BusinessException;

	/**
	 * @param metodo para actualizar la notificacion
	 * @return devuelve el objeto de Auditoria
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto actualizarNotificacion(RequestGenericDto<CrearNotificacionDto> request) throws BusinessException;

	/**
	 * @param metodo para eliminar una notificacion
	 * @return devuelve el objeto de Auditoria
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto eliminarNotificacion(RequestGenericDto<RequestDeleteObjectDto> request) throws BusinessException;

}
