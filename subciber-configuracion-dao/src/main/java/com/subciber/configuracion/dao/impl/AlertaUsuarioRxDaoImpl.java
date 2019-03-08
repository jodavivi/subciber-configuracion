/**
 * 
 */
package com.subciber.configuracion.dao.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.subciber.configuracion.base.dao.BaseJPADao;
import com.subciber.configuracion.dao.api.AlertaUsuarioRxDao;
import com.subciber.configuracion.dto.FiltroAlertasSistemaDto;
import com.subciber.configuracion.entity.VAlertaUsuario;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;

/**
 * @author jose david villanueva villalobos
 * @Creacion 05 mar. 2019
 * @Update
 * 
 */
@Stateless
public class AlertaUsuarioRxDaoImpl  extends BaseJPADao<VAlertaUsuario> implements AlertaUsuarioRxDao, Serializable {

	@Inject
    private MessageProvider messageProvider;
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@Override
	public List<VAlertaUsuario> alertasSistema(FiltroAlertasSistemaDto request) throws DaoException {
		
		StringBuilder jpql = null;
		StringBuilder jpqlSelect = null;
		StringBuilder jpqlWhere = null;
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		TypedQuery<VAlertaUsuario> query = null;
		List<VAlertaUsuario> resultado = null;
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
			
			if(request.getUsuarioId() != null) {
				jpqlWhere.append("and u.usuarioId = :usuarioId ");
			}
			if(request.getUsuarioCodigo() != null) {
				jpqlWhere.append("and u.codigo = :usuarioCodigo ");
			}
			if(request.getUsuario() != null) {
				jpqlWhere.append("and u.usuario = :usuario ");
			}
			if(request.getEstadoRecibido() != null) {
				jpqlWhere.append("and u.estadoRecibido = :estadoRecibido ");
			}
			jpql = new StringBuilder();
			jpql.append(jpqlSelect);
			jpql.append(jpqlWhere);
			query = entityManager.createQuery(jpql.toString(), VAlertaUsuario.class);
			
			if(request.getUsuarioId() != null) {
				query.setParameter("usuarioId", request.getUsuarioId()); 
			}
			if(request.getUsuarioCodigo() != null) {
				query.setParameter("usuarioCodigo", request.getUsuarioCodigo()); 
			}
			if(request.getUsuario() != null) {
				query.setParameter("usuario", request.getUsuario()); 
			}
			if(request.getEstadoRecibido() != null) {
				query.setParameter("estadoRecibido", request.getEstadoRecibido()); 
			}
			
			resultado = query.getResultList();
			
		}catch (NoResultException e){
			resultado = null;
		} catch(Exception e) {
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		return resultado;
		
	} 
}
