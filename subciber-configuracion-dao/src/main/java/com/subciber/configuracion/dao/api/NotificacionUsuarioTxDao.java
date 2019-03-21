package com.subciber.configuracion.dao.api;

import javax.ejb.Local;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.dto.NotificacionUsuarioDto;
import com.subciber.configuracion.dto.RequestDeleteDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.DaoException;

/**
 * @description Interface para el mantenimiento de las notificaciones de usuario
 * @author David Villanueva
 * @version 0.1, 17/03/2019
 * @update
 */
@Local
public interface NotificacionUsuarioTxDao {

	/**
	 * @param metodo para crear el detalle de la notificacion
	 * @return 
	 * @throws DaoException
	 */
	public abstract ResponseGenericDto<Integer> registrarNotificacionUsuario(RequestGenericDto<NotificacionUsuarioDto> request) throws DaoException;
	
	/**
	 * @param metodo para actualizar el detalle de la notificacion
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto actualizarNotificacionUsuario(RequestGenericDto<NotificacionUsuarioDto> request) throws DaoException;

	/**
	 * @param metodo para eliminar el detalle de la notificacion
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto eliminarNotificacionUsuario(RequestGenericDto<Integer> request) throws DaoException;

	/**
	 * @param metodo para eliminar el detalle de la notificacion masivo
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto eliminarNotificacionUsuarioxId(RequestDeleteDto request) throws DaoException;
}
