/**
 * 
 */
package com.subciber.configuracion.business.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.MessageFormat;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.commons.mail.HtmlEmail;

import com.subciber.configuracion.business.dto.EnvioCorreoDto;
import com.subciber.configuracion.business.impl.EnvioCorreoBusinessImpl;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.exception.BusinessException;

/**
 * @author josep
 *
 */
public class SendHtmlEmail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HtmlEmail	email	= new HtmlEmail();
		String		html	= null;
		StrSubstitutor strSub = null;
		try {
			html   = SendHtmlEmail.fileToString(SendHtmlEmail.class.getResourceAsStream("/CrearUsuario.html"), "utf-8");
			//System.out.println(html.toString());
			String mensaje = html.toString();
				EnvioCorreoDto req = new EnvioCorreoDto();
				req.setAsunto("Prueba");
				req.setCorreoDestino("jodavivi@gmail.com");
				req.setCuerpo("Prueba");
				EnvioCorreoBusinessImpl correo = new EnvioCorreoBusinessImpl();
				RequestGenericDto<EnvioCorreoDto> request = new RequestGenericDto<>();
				EnvioCorreoDto  correoDto = new EnvioCorreoDto();
				correoDto.setAsunto("Prueba");
				correoDto.setCorreoDestino("jodavivi@gmail.com");
				correoDto.setCuerpo(mensaje.replace("{0}", "jodavivi").replace("{1}", "2131123123"));
				request.setObjectRequest(correoDto);
				correo.enviarCorreo(request);
		}catch(BusinessException b) {
			b.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private static String fileToString(InputStream input, String encoding) throws IOException {
		StringWriter	  sw = new StringWriter();
		InputStreamReader in = new InputStreamReader(input, encoding);
 
		char[]	buffer = new char[1024 * 2];
		int		n	   = 0;
		while (-1 != (n = in.read(buffer))) {
			sw.write(buffer, 0, n);
		}    		
		return sw.toString();
	}	 
 
}