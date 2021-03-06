package com.kj133.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kj133.entity.Ouser;

public class EncodingFilter extends HttpServlet implements Filter {

 
	private static final long serialVersionUID = 1L;

	
	 protected String encoding;          // �����ַ����   
	 protected boolean ignore;            // �Ƿ���Դ�Сд   
	 protected FilterConfig filterConfig; // ��ʼ������   
	  
	 public void init(FilterConfig filterConfig) throws ServletException    
	 {   
	  // ��web.xml�ļ��ж�ȡencoding��ֵ   
	  encoding = filterConfig.getInitParameter("encoding");    
	  // ��web.xml�ļ��ж�ȡignore��ֵ   
	  String value = filterConfig.getInitParameter("ignore");    
	  // ������������Ϊ���Դ�Сд   
	  if(value == null)    
	  {    
	   ignore = true;    
	  }   
	  else if(value.equalsIgnoreCase("yes"))    
	  {    
	   ignore = true;    
	  }    
	  else if(value.equalsIgnoreCase("true"))    
	  {    
	   ignore = true;    
	  }    
	 }    
	 // doFilter����   
	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException    
	 {   
	    HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		httpRequest.setCharacterEncoding("UTF-8"); // �ı�������뷽ʽ
		httpResponse.setCharacterEncoding("UTF-8"); // �ı���Ӧ���뷽ʽ
		 
		 
	  if(ignore || request.getCharacterEncoding() == null)    
	  {   
	   // ���Ϊ���ȴ�web.xml�еõ�   
	   String encoding = selectEncoding(request);    
	   if(encoding != null)    
	   {   
	    // �����ַ����   
	    request.setCharacterEncoding(encoding);
	    
	   }   
	  }   
	  // ����ִ��   
	  chain.doFilter(request, response);    
	 }    
	 
	 // �õ��ַ����   
	 private String selectEncoding(ServletRequest request)    
	 {    
	  return encoding;    
	 }    
	 public void destroy()    
	 {    
	     
	 }    


  	/*@SuppressWarnings("unused")
	private FilterConfig config = null;

	@SuppressWarnings("unused")
	private String encoding = null;

	private String forword = null;

	private String path = null;

	private boolean bl = true;
 
 
  	public void destroy() {
		config = null;
		encoding = null;
		forword = null;
		path = null;
		bl = true;

	}
	

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		encoding = config.getInitParameter("encoding");
		forword = config.getInitParameter("forword");

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		httpRequest.setCharacterEncoding("gb2312"); // �ı�������뷽ʽ
		httpResponse.setCharacterEncoding("gb2312"); // �ı���Ӧ���뷽ʽ
		path = httpRequest.getServletPath(); // �������·��
//		---------------------------Ȩ�޿���----------------------------------

		if (path.endsWith(".jsp") || path.endsWith(".do")) {			      
				if (path.endsWith("login.jsp") || path.endsWith("login.do")) {
					
				} else {Ouser admin = (Ouser) httpRequest.getSession().getAttribute("user");
					if (admin == null || "".equals(admin)) {
						bl = false;
					} 
				}	
		}
		if (bl) {
			chain.doFilter(request, response); //�Ѿ���½,����˴�����   
		}else { // �Ƿ�����ϵͳ�����ص�½ҳ��
			bl = true;
//			String pass = "no";
//			httpRequest.getSession().setAttribute("pass", pass);
			httpRequest.getRequestDispatcher(forword).forward(httpRequest,httpResponse);
		}
	}*/
}
