/**
 * 
 */
package com.subciber.configuracion.business.test;

import com.subciber.configuracion.business.dto.EnvioCorreoDto;
import com.subciber.configuracion.business.impl.EnvioCorreoBusinessImpl;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.exception.BusinessException;

/**
 * @author josep
 *
 */
public class EnvioCorreoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			EnvioCorreoDto req = new EnvioCorreoDto();
			req.setAsunto("Prueba");
			req.setCorreoDestino("jodavivi@gmail.com");
			req.setCuerpo("Prueba");
			EnvioCorreoBusinessImpl correo = new EnvioCorreoBusinessImpl();
			RequestGenericDto<EnvioCorreoDto> request = new RequestGenericDto<>();
			EnvioCorreoDto  correoDto = new EnvioCorreoDto();
			correoDto.setAsunto("Prueba");
			correoDto.setCorreoDestino("jodavivi@gmail.com");
			correoDto.setCuerpo("Pruebaaa");
			request.setObjectRequest(correoDto);
			correo.enviarCorreo(request);
		}catch(BusinessException b) {
			System.out.println("Errorbb: " + b);
			}catch(Exception e){
				System.out.println("Error: " + e);
			}

	}

}
