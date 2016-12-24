package com.kj133.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kj133.entity.Ouser;

public class AuthLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		Ouser user = (Ouser) session.getAttribute("user");
		// 获得用户请求的URI
		String path = request.getRequestURI();
		String webName = request.getContextPath();
		// 登陆页面无需过滤
		if(user != null || path.indexOf("/login") > -1 || path.equals(webName+"/")){
			chain.doFilter(request, response);
			return;
		}else{//用户为空跳转到登录页面
			response.sendRedirect(webName+"/login.jsp");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
