package com.subciber.configuracion.dao.api;

import javax.ejb.Local;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.exception.DaoException;

/**
 * @description Interface para el mantenimiento de alertas del usuario
 * @author David Villanueva
 * @version 0.1, 15/03/2019
 * @update
 */
@Local
public interface AlertaUsuarioTxDao {

	/**
	 * @param metodo para eliminar el mensaje de alerta
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto eliminarAlertaUsuario(RequestGenericDto<Integer> request) throws DaoException;
	
}
