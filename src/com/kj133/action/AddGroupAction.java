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
	 * 初始化
	 * */
	public ActionForward initializtion(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		
		DynaActionForm dy=(DynaActionForm) form;
		Search_Account account=new Search_Account();
		  //初始化组别和用户
		//String z="y";
	    String gid=request.getParameter("groupid");//组别编号
	    //String gname=request.getParameter("gname").replace(z,"");//组别名称
	    //String gdepict=request.getParameter("gdepict").replace(z,"");//组别描述
	    
	    String gname=request.getParameter("gname");//组别名称
	    String gdepict=request.getParameter("gdepict");//组别描述
//	    try {
//	    	gname = new String(gname.getBytes("iso8859-1"), "UTF-8");
//	    	gdepict = new String(gdepict.getBytes("iso8859-1"), "UTF-8");
//		} catch (Exception e) {
//		}
	    
        account.setGroup_id(gid);
	    account.setGroup_name(gname);
	    account.setGroup_depict(gdepict);
	    
	    //初始化用户
	    String uid=request.getParameter("uid");//用户名
	    String uname=request.getParameter("uname");//姓名
	    String password=request.getParameter("password");//密码
	    String createtime=request.getParameter("createtime");//创建时间
	    String departmentid=request.getParameter("departmentid");//所属部门id
	    String groupid= request.getParameter("groupid");//所属组别id
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
     * 更新组别
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
	        	 log.error("==更新班组==",e);
	         }
			return mapping.findForward("update");
		}
	
	/**
	 * 删除组别  
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
		    	log.error("==删除班组==",e);
		    }
			return mapping.findForward("update");
	}
	
	/**
	 * 增加用户
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
				  log.error("==增加用户==",e);
			  } 
			return mapping.findForward("update");
		}

}
 