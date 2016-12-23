package com.kj133.util;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.kj133.entity.Ouser;
 

public class SessionListener implements HttpSessionListener{
	   private static java.util.Hashtable hUserName = new Hashtable();
	   public void sessionCreated(HttpSessionEvent se){
	        System.out.println("Session Created.");
	   }
	   public void sessionDestroyed(HttpSessionEvent se){
	      hUserName.remove(se.getSession());
	      System.out.println("Session Destroyed.");
	   }
	   
	     /*
	      * isAlreadyEnter-用于判断用户是否已经登录以及相应的处理方法
	      * @param sUserName String-登录的用户名称
	      * @return boolean-该用户是否已经登录过的标志
	      */
	   @SuppressWarnings("unchecked")
    	public synchronized static boolean isLogined(HttpSession session,String sUserName){
	         boolean flag = false;
	         if(hUserName.containsKey(sUserName)){
	              flag = true;
	              HttpSession vsession = (HttpSession)hUserName.get(sUserName);
	              try {
	                    vsession.invalidate();
	                   } catch (Exception ex){}
	                } else{
	                     flag = false;
	                   }
	               hUserName.remove(sUserName);
	               hUserName.put(sUserName,session);
	               return flag;
	     }
	   
	   /**
	    * 判断用户是否在线
	    * */
	    public synchronized static boolean isOnline(HttpSession session){
	         boolean flag = true;
	         Ouser user = (Ouser) session.getAttribute("user");
	         HttpSession vsession = (HttpSession) hUserName.get(user.getUserid());
	         if (session.getId().equals(vsession.getId())) {
	             flag = true;
	            }else {
	                flag = false;
	             } return flag;
	        }
 }

/**
 *   通过在login的servlet或action中的登陆方法中调用该HttpSessionListener.isLogined()方法判断是否已经登陆了，
 *   如果已经登陆，则不让登陆（踢第一个还是后面的，自己决定）． 
 * */







