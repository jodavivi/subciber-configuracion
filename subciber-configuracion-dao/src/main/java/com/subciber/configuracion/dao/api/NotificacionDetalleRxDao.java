/**
 * 
 */
package com.subciber.configuracion.dao.api;

import java.util.List;

import javax.ejb.Local;

import com.subciber.configuracion.dto.FiltroNotificacionDetalleDto;
import com.subciber.configuracion.entity.VNotificacionDetalle;
import com.subciber.configuracion.exception.DaoException;

/**
 * @description Interface para obtener las alertas por usuario
 * @author David Villanueva
 * @version 0.1, 05/03/2019
 * @update
 */
@Local
public interface NotificacionDetalleRxDao {

	/**
	 * @param metodo para obtener el detalle de las notificaciones del sistema 
	 * @return devuelve el detalle de la notificacion
	 * @throws DaoException
	 */
	public abstract List<VNotificacionDetalle> consultarNotificacionesDetalle(FiltroNotificacionDetalleDto request) throws DaoException;
}
