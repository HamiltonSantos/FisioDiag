package br.com.faddvm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String uri = request.getRequestURI();
		if (uri.endsWith("login") || uri.contains("resources") | uri.contains("webjars")) {
			return true;
		}
		if (request.getSession().getAttribute("fisioterapeutaLogado") != null) {
			return true;
		}
		
		response.sendRedirect("/faddvm/login");
		return false;
	}
}
