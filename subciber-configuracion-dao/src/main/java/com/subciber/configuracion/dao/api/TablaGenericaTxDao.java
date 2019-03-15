package com.subciber.configuracion.dao.api;

import javax.ejb.Local;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.dto.TablaGenericaDto;
import com.subciber.configuracion.exception.DaoException;

/**
 * @description Interface para el mantenimiento de tablas genericas
 * @author David Villanueva
 * @version 0.1, 13/03/2019
 * @update
 */
@Local
public interface TablaGenericaTxDao {

	/**
	 * @param metodo para registrar tabla generica
	 * @return 
	 * @throws DaoException
	 */
	public abstract ResponseGenericDto<Integer> registrarTablaGenerica(RequestGenericDto<TablaGenericaDto> request) throws DaoException;
	
	/**
	 * @param metodo para actualizar tabla generica
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto actualizarTablaGenerica(RequestGenericDto<TablaGenericaDto> request) throws DaoException;
	
	
	/**
	 * @param metodo para actualizar tabla generica
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto eliminarTablaGenerica(RequestGenericDto<Integer> request) throws DaoException;
	
	
}
