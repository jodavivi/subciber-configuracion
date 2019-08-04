/**
 * 
 */
package com.subciber.configuracion.dao.util;

/**
 * @author josep
 *
 */
public class ConfigDao {

	public static final String persistenceUnit = "basePU";
	public static final String sequenceNotificacion = "select nextval ('\"Configuracion\".\"Notificacion_Id_seq\"')";
	public static final String sequenceGenerica = "select nextval ('\"General\".\"Generica_Id_seq\"')";
	
}
