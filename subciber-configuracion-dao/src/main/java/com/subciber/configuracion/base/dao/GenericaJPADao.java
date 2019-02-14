/**
 * 
 */
package com.subciber.configuracion.base.dao;

import com.subciber.configuracion.exception.DaoException;

/**
 * @author josep
 *
 */
public interface GenericaJPADao<T> {
	     
	    public T get(int id) throws DaoException;
	    
}
