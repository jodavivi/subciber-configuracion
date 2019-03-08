/**
 * 
 */
package com.subciber.configuracion.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class AlertasSistemaDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String titulo;
	private String descripcion;
	private String prioridad;
	private String alertaTipo;
	private String tiempoExpirado;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getAlertaTipo() {
		return alertaTipo;
	}
	public void setAlertaTipo(String alertaTipo) {
		this.alertaTipo = alertaTipo;
	}
	public String getTiempoExpirado() {
		return tiempoExpirado;
	}
	public void setTiempoExpirado(String tiempoExpirado) {
		this.tiempoExpirado = tiempoExpirado;
	}
	
}
