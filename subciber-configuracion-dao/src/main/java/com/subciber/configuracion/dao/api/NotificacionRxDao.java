/**
 * 
 */
package com.subciber.configuracion.dao.api;

import java.util.List;

import javax.ejb.Local;

import com.subciber.configuracion.dto.FiltroNotificacionDto;
import com.subciber.configuracion.entity.VNotificacion;
import com.subciber.configuracion.exception.DaoException;

/**
 * @description Interface para obtener las alertas por usuario
 * @author David Villanueva
 * @version 0.1, 05/03/2019
 * @update
 */
@Local
public interface NotificacionRxDao {

	/**
	 * @param metodo para obtener las notificaciones del sistema 
	 * @return devuelve la lista de notificaciones 
	 * @throws DaoException
	 */
	public abstract List<VNotificacion> consultarNotificaciones(FiltroNotificacionDto request) throws DaoException;
}
