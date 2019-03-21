package com.subciber.configuracion.dao.api;

import javax.ejb.Local;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.dto.NotificacionDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.DaoException;

/**
 * @description Interface para el mantenimiento de las notificaciones
 * @author David Villanueva
 * @version 0.1, 17/03/2019
 * @update
 */
@Local
public interface NotificacionTxDao {

	/**
	 * @param metodo para crear una notificacion
	 * @return 
	 * @throws DaoException
	 */
	public abstract ResponseGenericDto<Integer> registrarNotificacion(RequestGenericDto<NotificacionDto> request) throws DaoException;
	
	/**
	 * @param metodo para actualizar una notificacion
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto actualizarNotificacion(RequestGenericDto<NotificacionDto> request) throws DaoException;

	/**
	 * @param metodo para eliminar una notificacion
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto eliminarNotificacion(RequestGenericDto<Integer> request) throws DaoException;

}
