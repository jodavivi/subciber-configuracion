/**
 * 
 */
package com.subciber.configuracion.dao.api;

import java.util.List;

import javax.ejb.Local;

import com.subciber.configuracion.dto.FiltroAlertasSistemaDto;
import com.subciber.configuracion.entity.VAlertaUsuario;
import com.subciber.configuracion.exception.DaoException;

/**
 * @description Interface para obtener las alertas por usuario
 * @author David Villanueva
 * @version 0.1, 05/03/2019
 * @update
 */
@Local
public interface AlertaUsuarioRxDao {

	/**
	 * @param metodo para obtener las alertas del sistema segun filtro
	 * @return devuelve la lista de alertas 
	 * @throws DaoException
	 */
	public abstract List<VAlertaUsuario> alertasSistema(FiltroAlertasSistemaDto request) throws DaoException;
}
