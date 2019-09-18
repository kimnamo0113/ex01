package com.yi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		logger.info("AuthInterceptor preHadler.............");
		if(session.getAttribute("Auth")==null) { //로그인 안된 상태
			logger.info("current user is not login.............");
			saveDest(request);
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return false; //controller 진입을 막음
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}	
	
	
	private void saveDest(HttpServletRequest request) {
		String uri = request.getRequestURI(); //목적지
		String query = request.getQueryString(); //매개변수
		
		if(query==null || query.equals("null")) {
			query="";
			
		}
		else {
			query = "?"+query;
		}
		if(request.getMethod().equalsIgnoreCase("get")) {
			logger.info("dest : "+uri+query);
			request.getSession().setAttribute("dest", uri+query);
		}
		
	}
}
