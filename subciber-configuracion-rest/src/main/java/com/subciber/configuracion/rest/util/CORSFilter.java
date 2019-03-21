package com.subciber.configuracion.rest.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/rest/*")
public class CORSFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;

	public CORSFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String originUrl = request.getHeader("origin");
		String url = ((HttpServletRequest)request).getRequestURL().toString();
		System.out.println(url);
		// Authorize (allow) all domains to consume the content
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", buscarUrlPermitida(originUrl));
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods",
				"GET, OPTIONS, HEAD, PUT, POST");
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Max-Age", "3600");
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers",
				"x-requested-with, X-Auth-Token, Content-Type, transaccionId, aplicacion, tokens, terminal");
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Expose-Headers",
				"x-requested-with, X-Auth-Token, Content-Type, transaccionId, aplicacion, tokens, terminal, usuario, usuarioId");

		HttpServletResponse resp = (HttpServletResponse) servletResponse;

		// For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS
		// handshake
		if (request.getMethod().equals("OPTIONS")) {
			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}

		// pass the request along the filter chain
		chain.doFilter(request, servletResponse);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public String buscarUrlPermitida(String url) {

		String urlDefault = "http://localhost:8888";
		List<String> urlPermitidas = new ArrayList<>();
		urlPermitidas.add("http://localhost:8888");
		urlPermitidas.add("http://localhost:80");
		urlPermitidas.add("http://localhost:8088");
		urlPermitidas.add("http://localhost");
		urlPermitidas.add("http://vivfcons.subciber.com");

		boolean permitida = false;
		for (String urlPermitida : urlPermitidas) {
			if (urlPermitida.equalsIgnoreCase(url)) {
				permitida = true;
			}
		}

		if (permitida) {
			urlDefault = url;
		}

		return urlDefault;
	}

}
