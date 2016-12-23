//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kj133.entity.bo.AccountBO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.kj133.entity.Search_Account;
import com.kj133.util.Global;
 

/** 
 * MyEclipse Struts
 * Creation date: 03-05-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class AddDepartmentAction extends DispatchAction {

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
	           
            // 初始化部门和组别 
			String z="y";
			String did=request.getParameter("departmentid");//部门编号
            String dname=request.getParameter("dname").replace(z,"");//部门名字 
            String depict=request.getParameter("depict").replace(z,"");//部门描述
            
//            try {
//            	dname = new String(dname.getBytes("iso8859-1"), "UTF-8");
//            	depict = new String(depict.getBytes("iso8859-1"), "UTF-8");
//    		} catch (Exception e) {
//    		}
    		
            account.setDepartment_id(did);
		    account.setDepartment_dname(dname);
		    account.setDepartment_depict(depict);
		   
		    //初始化组别和用户
		    String gid=request.getParameter("groupid");//组别编号
		    String gname=request.getParameter("gname");//组别名称
		    String gdepict=request.getParameter("gdepict");//组别描述
            account.setGroup_id(gid);
		    account.setGroup_name(gname);
		    account.setGroup_depict(gdepict);
		    
		    dy.set("ser_account",account);
	        return mapping.findForward("account"); 
	} 
	
	/**
	 * 部门(add)
	 * */
	public ActionForward addDepartment(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		Global go=new Global();
		AccountBO bo=new AccountBO();
		Search_Account account=(Search_Account)dy.get("ser_account");
			 try{
			     List list=go.juede1(" select departmentid from bs_odepartment where departmentid= ? or dname= ? ",account.getDepartment_id(),account.getDepartment_dname());
			     List list1=go.juede1(" select dname from bs_odepartment where departmentid= ? or dname= ? ",account.getDepartment_id(),account.getDepartment_dname());
			     
			     if( list != null && list.size()>0){
			    	 ActionMessages messages = new ActionMessages();
	    		     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.add.department_id"));
	    		     saveMessages(request, messages);
			     }if(list1 != null && list1.size()>0){
			    	 ActionMessages messages = new ActionMessages();
	    		     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.add.department_name"));
	    		     saveMessages(request, messages);
			     }else{
			    	  bo.Adddepartment(account);
			    	  ActionMessages messages = new ActionMessages();
			    	  messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.add.stafferid"));
			    	  saveMessages(request, messages);
			    	  
			     }
			 }catch(Exception e){
				 log.error("==增加部门==",e);
			 }
		 return mapping.findForward("department");
	}
	
	public ActionForward updateDepartment(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm)form;
			AccountBO bo=new AccountBO();
			Search_Account account=(Search_Account)dy.get("ser_account");
	         try{
	        	 bo.updateDepartment(account);
	        	 ActionMessages messages = new ActionMessages();
		    	  messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.update.cardid"));
		    	  saveMessages(request, messages);
	         }catch(Exception e){
	        	 log.error("==修改部门==",e);
	         }
			return mapping.findForward("update");
		}
	
	/**
	 * 部门(delete)
	 * */
	public ActionForward deleteDepartment(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		Search_Account account=(Search_Account)dy.get("ser_account");
	    AccountBO bo=new AccountBO();
	    Global go=new Global();
	     try{
	    	    List count=go.juede(" select * from bs_ouser where departmentid = ? ",account.getDepartment_id());
	    		if( count != null && count.size() > 0 ){
	    			 ActionMessages messages = new ActionMessages();
	    		     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.delete.department"));
	    		     saveMessages(request, messages);
	    		}else{
	    			 bo.Deletedepartment(account);
	    			 ActionMessages messages=new ActionMessages();
				    	messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.delete.cardid"));
				    	this.saveMessages(request,messages);
	    		}
	    	 
	     }catch(Exception e){
	    	 log.error("==删除部门==",e);
	     }
		 return mapping.findForward("department");
	  
	}
	
	/**
	 * 组别(add)
	 * */
	public ActionForward addGroup(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		Global go=new Global();
		AccountBO bo=new AccountBO();
		Search_Account account=(Search_Account)dy.get("ser_account");
			  try{
				   List count=go.juede1(" select groupid from ogroup where groupid = ? or gname= ? ",account.getGroup_id(),account.getGroup_name());
				   List count1=go.juede1(" select gname from ogroup where groupid = ? or gname= ? ",account.getGroup_id(),account.getGroup_name());
				   if( count != null && count.size()>0 ){
					     ActionMessages messages = new ActionMessages();
		    		     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.add.group_id"));
		    		     saveMessages(request, messages);
				   }if( count1 != null && count1.size()>0 ){
					     ActionMessages messages = new ActionMessages();
		    		     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.add.group_name"));
		    		     saveMessages(request, messages);
				   }else{
					   List count2=go.juede(" select * from bs_odepartment where departmentid = ? ",account.getGroup_id());
					   if( count2 != null && count2.size()>0){
						   ActionMessages messages = new ActionMessages();
			    		   messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.add.group_id2"));
			    		   saveMessages(request, messages);
					   }else{
						   bo.addGroup(account);
						   ActionMessages messages = new ActionMessages();
			    		   messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.add.cardid"));
			    		   saveMessages(request, messages);
					   }
				   }  
			  }catch(Exception e){
				  log.error("==增加组别==",e);
			  }
		 return mapping.findForward("department");
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

