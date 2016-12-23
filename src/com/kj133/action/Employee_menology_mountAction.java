/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.kj133.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.kj133.entity.Search_Employee_menology_mount;
import com.kj133.entity.bo.Employee_menology_mountBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.entity.vo.Employee_menology_mountVO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-26-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class Employee_menology_mountAction extends DispatchAction {
	/*
	 * Generated Methods
	 */
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��ʼ��
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    System.out.print("init");
			WordlibBO lib=new WordlibBO();
			DynaActionForm dy=(DynaActionForm)form;
		    Employee_menology_mountBO  mount=new Employee_menology_mountBO();
		    try{
		    	 Search_Employee_menology_mount mou=(Search_Employee_menology_mount)dy.get("mount");
		    	 List list=mount.init(mou);
		    	 request.setAttribute("mountList", list);
		    	 List type=lib.workType();
				 request.setAttribute("workType_list",type);
		    }catch(Exception e){
		    	log.error("==Ա����������==",e);
		    }
		return mapping.findForward("init");
	}
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��ʼ��
	 */
	public ActionForward loadAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			WordlibBO lib=new WordlibBO();
			DynaActionForm dy=(DynaActionForm)form;
		    try{
		    	 Search_Employee_menology_mount mou=(Search_Employee_menology_mount)dy.get("mount");
		    	 mou.setMintime("");
		    	 mou.setSid("");
		    	 
		    	 List type=lib.workType();
				 request.setAttribute("workType_list",type);
		    }catch(Exception e){
		    	log.error("==Ա����������==",e);
		    }
		return mapping.findForward("loadAdd");
	}
	
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��ʼ��
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    Employee_menology_mountBO  bo=new Employee_menology_mountBO();
		    Global bal=new Global();
		    try{
		    	 String sid=request.getParameter("mount.sid");
		    	 String worktype=request.getParameter("mount.worktype");
		    	 String mintime=request.getParameter("mount.mintime");
		    	 StringBuffer sb=new StringBuffer();
	    		 sb.append(" select  *  from AttendanceSet where stafferid= ? ");
		    	 if(sid !=null && !sid.equals("")){//Ա���Ų�Ϊ��(���õĴ���������������ھ��޸ķ��������)
		    		  List list=bal.juede(sb.toString(), sid);
		    		  if(list.size()>0){
		    		      bo.update(mintime, sid);
		    		  }else{
		    			  bo.add(sid,mintime);
		    		  }
		    	 }
		    	 if(worktype != null && !worktype.equals("")){//���ֲ�Ϊ��
		    		 List list=bal.getStafferid(worktype);
		    		 for(int i=0;i<list.size();i++){
		    			 Employee_menology_mountVO vo=(Employee_menology_mountVO) list.get(i);
		    			  List list2=bal.juede(sb.toString(), vo.getStafferid());
			    		  if(list2.size()>0){
			    		      bo.update(mintime, vo.getStafferid());
			    		  }else{
			    			  bo.add(vo.getStafferid(), mintime);
			    		  }
		    			
		    		 }
		    	 }
		    }catch(Exception e){
		    	log.error("==����Ա����������==",e);
		    }
		return  mapping.findForward("add");
	}
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return   ��ѯ
	 */
	public ActionForward getList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    DynaActionForm dy=(DynaActionForm)form;
		    WordlibBO lib=new WordlibBO();
		    Employee_menology_mountBO  mount=new Employee_menology_mountBO();
		    try{
		    	 Search_Employee_menology_mount mou=(Search_Employee_menology_mount)dy.get("mount");
		    	 List list=mount.init(mou);
		    	 request.setAttribute("mountList", list);
		    	 List type=lib.workType();
				 request.setAttribute("workType_list",type);
		    	 dy.set("mount",mou);//���°�ֵ���û�ҳ�� 
		    }catch(Exception e){
		    	log.error("==Ա���������� ��ѯ==",e);
		    }
		    return mapping.findForward("init");
	}
	
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return   ɾ��
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    Employee_menology_mountBO  bo=new Employee_menology_mountBO();
		    String cid=request.getParameter("sid"); 
		    String[] name=cid.split("\\,");
		    DynaActionForm dy=(DynaActionForm)form;
		    WordlibBO lib=new WordlibBO();
		    Employee_menology_mountBO  mount=new Employee_menology_mountBO();
			for(int i=0;i<name.length;i++){
				   try{
				    	 bo.delete(name[i]);
				    	 ActionMessages messages=new ActionMessages();
						 messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.delete.cardid"));
						 saveMessages(request, messages); 
						
						 Search_Employee_menology_mount mou=(Search_Employee_menology_mount)dy.get("mount");
				    	 List list=mount.init(mou);
				    	 request.setAttribute("mountList", list);
				    	 List type=lib.workType();
						 request.setAttribute("workType_list",type);
				    	 dy.set("mount",mou);//���°�ֵ���û�ҳ�� 
				    }catch(Exception e){
				    	log.error("==ɾ��Ա����������==",e);
				    }
			 }
			 
		  return mapping.findForward("init");
	}
}