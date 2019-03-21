/**
 * 
 */
package com.subciber.configuracion.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.subciber.configuracion.base.dao.BaseJPADao;
import com.subciber.configuracion.dao.api.NotificacionDetalleRxDao;
import com.subciber.configuracion.dto.FiltroNotificacionDetalleDto;
import com.subciber.configuracion.entity.VNotificacionDetalle;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;

/**
 * @author jose david villanueva villalobos
 * @Creacion 05 mar. 2019
 * @Update
 * 
 */
@Stateless
public class NotificacionDetalleRxDaoImpl  extends BaseJPADao<VNotificacionDetalle> implements NotificacionDetalleRxDao, Serializable {

	@Inject
    private MessageProvider messageProvider;
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@Override
	public List<VNotificacionDetalle> consultarNotificacionesDetalle(FiltroNotificacionDetalleDto request) throws DaoException {
		
		StringBuilder jpql = null;
		StringBuilder jpqlSelect = null;
		StringBuilder jpqlWhere = null;
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		TypedQuery<VNotificacionDetalle> query = null;
		List<VNotificacionDetalle> resultado = null;
		try {
			jpqlSelect = new StringBuilder();
			jpqlSelect.append("SELECT ");
			jpqlSelect.append("u ");
			jpqlSelect.append("FROM ");
			jpqlSelect.append(entityClass.getSimpleName());
			jpqlSelect.append(" u ");
			
			jpqlWhere = new StringBuilder();
			jpqlWhere.append("WHERE ");
			jpqlWhere.append("1 = 1 ");
			
			if(request.getId() != null) {
				jpqlWhere.append("and u.id = :id ");
			}
			if(request.getNotificacionId() != null) {
				jpqlWhere.append("and u.notificacionId = :notificacionId ");
			}
			if(request.getEmisionEstado() != null) {
				jpqlWhere.append("and u.emisionEstado = :emisionEstado ");
			}
			if(request.getEmisionFecha() != null) {
				jpqlWhere.append("and u.emisionFecha = :emisionFecha ");
			}
			jpql = new StringBuilder();
			jpql.append(jpqlSelect);
			jpql.append(jpqlWhere);
			query = entityManager.createQuery(jpql.toString(), VNotificacionDetalle.class);
			
			if(request.getId() != null) {
				query.setParameter("id", request.getId()); 
			}
			if(request.getNotificacionId() != null) {
				query.setParameter("notificacionId", request.getNotificacionId()); 
			}
			if(request.getEmisionEstado() != null) {
				query.setParameter("emisionEstado", request.getEmisionEstado()); 
			}
			if(request.getEmisionFecha() != null) {
				query.setParameter("emisionFecha", request.getEmisionFecha()); 
			}
			
			resultado = query.getResultList();
			
		}catch (NoResultException e){
			resultado = null;
		} catch(Exception e) {
			String error = "";
			 Throwable th = e.getCause();
			  if(th.getCause() instanceof SQLException) {
				  SQLException cause = (SQLException) th.getCause();
				  error =cause.getMessage();
			  }else {
				  error = e.getCause().toString();
			  }
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, error));

		}
		return resultado;
		
	} 
}
