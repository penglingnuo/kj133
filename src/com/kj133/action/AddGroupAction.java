//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

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

import com.kj133.entity.Search_Account;
import com.kj133.entity.bo.AccountBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 04-04-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class AddGroupAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward  
	 */
	/**
	 * ��ʼ��
	 * */
	public ActionForward initializtion(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		
		DynaActionForm dy=(DynaActionForm) form;
		Search_Account account=new Search_Account();
		  //��ʼ�������û�
		//String z="y";
	    String gid=request.getParameter("groupid");//�����
	    //String gname=request.getParameter("gname").replace(z,"");//�������
	    //String gdepict=request.getParameter("gdepict").replace(z,"");//�������
	    
	    String gname=request.getParameter("gname");//�������
	    String gdepict=request.getParameter("gdepict");//�������
//	    try {
//	    	gname = new String(gname.getBytes("iso8859-1"), "UTF-8");
//	    	gdepict = new String(gdepict.getBytes("iso8859-1"), "UTF-8");
//		} catch (Exception e) {
//		}
	    
        account.setGroup_id(gid);
	    account.setGroup_name(gname);
	    account.setGroup_depict(gdepict);
	    
	    //��ʼ���û�
	    String uid=request.getParameter("uid");//�û���
	    String uname=request.getParameter("uname");//����
	    String password=request.getParameter("password");//����
	    String createtime=request.getParameter("createtime");//����ʱ��
	    String departmentid=request.getParameter("departmentid");//��������id
	    String groupid= request.getParameter("groupid");//�������id
	    account.setUid(uid);
	    account.setOname(uname);
	    account.setPassword(password);
	    account.setCratetime(createtime);
	    account.setDepartmentid(departmentid);
	    account.setGroupid(groupid); 
	    
	    dy.set("ser_account",account);
				
       return mapping.findForward("group");
	}
	
    /**
     * �������
     * */
	public ActionForward updateGroup(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm)form;
			AccountBO bo=new AccountBO();
			Search_Account account=(Search_Account)dy.get("ser_account");
	         try{
	        	 bo.updateGroup(account);
	        	 ActionMessages messages=new ActionMessages();
			    	messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.update.cardid"));
			    	this.saveMessages(request,messages);
	         }catch(Exception e){
	        	 log.error("==���°���==",e);
	         }
			return mapping.findForward("update");
		}
	
	/**
	 * ɾ�����  
	 * */
	public ActionForward deleteGroup(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			AccountBO bo=new AccountBO();
			Global go=new Global();
			DynaActionForm dy=(DynaActionForm)form;
			Search_Account account=(Search_Account)dy.get("ser_account");
		    try{
		    	List count=go.juede(" select * from bs_ouser where groupid= ? ",account.getGroup_id());
		        if( count != null && count.size()>0 ){
		        	 ActionMessages messages = new ActionMessages();
	    		     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.delete.user"));
	    		     saveMessages(request, messages);
		        }else{
		        	 bo.deleteGroup(account.getGroup_id());
		        	 ActionMessages messages=new ActionMessages();
				    	messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.delete.cardid"));
				    	this.saveMessages(request,messages);
		        }
		    }catch(Exception e){
		    	log.error("==ɾ������==",e);
		    }
			return mapping.findForward("update");
	}
	
	/**
	 * �����û�
	 * */
	public ActionForward addUser(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm)form;
			Global go=new Global();
			AccountBO bo=new AccountBO();
			Search_Account account=(Search_Account)dy.get("ser_account");
			 try{
				   List count=go.juede(" select userid from bs_ouser where userid  = ?",account.getUid());
				   if( count != null && count.size()>0 ){
					     ActionMessages messages = new ActionMessages();
		    		     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.add.user_id"));
		    		     saveMessages(request, messages);
				   }else{
						  bo.addUser(account);
						  ActionMessages messages = new ActionMessages();
			    		     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.add.cardid"));
			    		     saveMessages(request, messages);
				   }  
			  }catch(Exception e){
				  log.error("==�����û�==",e);
			  } 
			return mapping.findForward("update");
		}

}
 