/**
 * 
 */
package com.subciber.configuracion.dao.api;

import java.util.List;

import javax.ejb.Local;

import com.subciber.configuracion.dto.TablaGenericaFiltroDto;
import com.subciber.configuracion.entity.VTablaGenerica;
import com.subciber.configuracion.exception.DaoException;

/**
 * @description Interface para obtener la lista de compos y tablas genericas
 * @author David Villanueva
 * @version 0.1, 11/03/2019
 * @update
 */
@Local
public interface TablaGenericaRxDao {

	/**
	 * @param metodo obtener la lista de tablas
	 * @return devuelve informacion de las tablas
	 * @throws DaoException
	 */
	public abstract List<VTablaGenerica> consultarTablaGenerica(TablaGenericaFiltroDto request) throws DaoException;
	
}
