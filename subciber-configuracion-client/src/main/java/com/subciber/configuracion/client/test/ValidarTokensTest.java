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
		filtroToken.setTokens("Ennf-pt7OvC6IJ7JwynSkcYixnIxaTH0fxlTkzkg;HfKvlGoAbRucdtJa6lDMEgZbOfxkrNY93kA7Pe9QdbG/xH5fA1rURJV+cFZkSqE7N4ek4qI3Ssb6e/6EAms80mHh40iotH+Ci34N7NWWTNPESxisys+9eRqp4UEyNeOGzXY95DLJOm1TDRKLlS8iKM/qyD9klCE7UhjW8mTFDmMbfgnqJgkZqKucawpkZw9wJgeioJiH0UWDdd+PmAV/096G9uuqDIHPOQC0sdrXYZ3FroHZ00d3TXtGPi+lLSJDzUSfVaYR+CCMoKb85Eawc57NoERgofYgys8Foa8iVsnAHQDa+s8fjKwViCvkpXPz9JR5WIcb+Kgd3DzBGsnIjuf0j/CL0ncJPvXGQ5nD+v0S3+1/VrKng/lkvwXlMNrqdztNsPVnYlKzOTpdiPn3FUCVfUaX1tW407rH0+tUJFlsbmszmM2MMsvFzmqnkKQr0RgTSrcAnBby2IXgmTclUShSQzQM9ki276L5S1c9+1w9Otn/iYeRG6pqt70W8xKOYzdNgH2P77BEnQZyBlAigP1qbBa4/KzwedhlPH82S/vJVuSxggV241sLEy9yUv9z");
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
