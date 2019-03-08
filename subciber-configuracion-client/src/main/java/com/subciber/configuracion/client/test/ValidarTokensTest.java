/**
 * 
 */
package com.subciber.configuracion.client.test;

import com.subciber.configuracion.client.dto.FiltroTokensDto;
import com.subciber.configuracion.client.dto.ResponseValidarTokens;
import com.subciber.configuracion.client.impl.TokenClientImpl;
import com.subciber.configuracion.dto.RequestGenericDto; 

/**
 * @author josep
 *
 */
public class ValidarTokensTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RequestGenericDto<FiltroTokensDto> request = new RequestGenericDto<FiltroTokensDto>();
		request.getAuditRequest().setTransaccionId("112121212");
		request.getAuditRequest().setAplicacion("Test");
		request.getAuditRequest().setTerminal("127.0.0.1");
		FiltroTokensDto filtroToken = new FiltroTokensDto();
		filtroToken.setTokens("zmKZAT-dXWAuPYKZD1nJ378Ke_UkK3O568PuuGn0;HfKvlGoAbRucdtJa6lDMEgZbOfxkrNY93kA7Pe9QdbGlwmkp7hUEeR3/bdBRGi8l7BIxrm3qSjBfClhKytUsjkcJFhTZySEBluGhz2V5TK/iUliPeJJjAMInTjpxTOaPzXY95DLJOm1TDRKLlS8iKM/qyD9klCE7UhjW8mTFDmMbfgnqJgkZqKucawpkZw9wjed0jO4j+8AwZHiryOD5rQ==");
		request.setObjectRequest(filtroToken);
		
		try {
			TokenClientImpl validarToken = new TokenClientImpl();
			ResponseValidarTokens  response = validarToken.validarTokens(request);
			System.out.println(response);
		}catch(Exception e) {
			System.out.println(e);
		}

	}

}
