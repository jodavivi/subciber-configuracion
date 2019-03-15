package com.subciber.configuracion.dao.api;

import javax.ejb.Local;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.dto.CampoGenericaDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.DaoException;

/**
 * @description Interface para el mantenimiento de los campos de la tabla generica
 * @author David Villanueva
 * @version 0.1, 13/03/2019
 * @update
 */
@Local
public interface CampoGenericaTxDao {

	/**
	 * @param metodo para registrar campo generica
	 * @return 
	 * @throws DaoException
	 */
	public abstract ResponseGenericDto<Integer> registrarCampoGenerica(RequestGenericDto<CampoGenericaDto> request) throws DaoException;
	
	/**
	 * @param metodo para actualizar el campo generica
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto actualizarCampoGenerica(RequestGenericDto<CampoGenericaDto> request) throws DaoException;
	
	
	/**
	 * @param metodo para actualizar el campo generica
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto eliminarCampoGenerica(RequestGenericDto<Integer> request) throws DaoException;
	
	
}
