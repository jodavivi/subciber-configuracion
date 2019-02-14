/**
 * 
 */
package com.subciber.configuracion.base.dao;

import java.text.MessageFormat;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;

/**
 * @author josep
 * @param <T>
 *
 */
@Dependent
public abstract  class GenericaJPADaoImpl<T> extends BaseJPADao<T>  implements GenericaJPADao<T> {

	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@Inject
    private MessageProvider messageProvider;
	
	public T get(int id) throws DaoException {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		T o = null;
		try {
			o = (T) entityManager.find(entityClass, id);
			
		}catch(Exception e) {
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
	     return o; 
	}

}
