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
	      * isAlreadyEnter-�����ж��û��Ƿ��Ѿ���¼�Լ���Ӧ�Ĵ�����
	      * @param sUserName String-��¼���û�����
	      * @return boolean-���û��Ƿ��Ѿ���¼���ı�־
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
	    * �ж��û��Ƿ�����
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
 *   ͨ����login��servlet��action�еĵ�½�����е��ø�HttpSessionListener.isLogined()�����ж��Ƿ��Ѿ���½�ˣ�
 *   ����Ѿ���½�����õ�½���ߵ�һ�����Ǻ���ģ��Լ��������� 
 * */







