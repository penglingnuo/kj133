//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

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
import com.kj133.util.encrypt;

/** 
 * MyEclipse Struts
 * Creation date: 04-04-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class AddUserAction extends DispatchAction {

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
//		encrypt et = new encrypt();
		  //初始化用户
		//String z = "y";
	    String uid=request.getParameter("uid");//用户名
	    //String uname=request.getParameter("uname").replace(z,"");//姓名
	    String uname=request.getParameter("uname");//姓名
//	    try {
//	    	uname = new String(uname.getBytes("iso8859-1"), "UTF-8");
//		} catch (Exception e) {
//		}
	    
	    String password=request.getParameter("password");//密码
	    String createtime=request.getParameter("createtime");//创建时间
	    String departmentid=request.getParameter("departmentid");//所属部门id
	    String groupid= request.getParameter("groupid");//所属组别id
	    account.setUid(uid);
	    account.setOname(uname);
//	    account.setPassword(password);
	    account.setPassword(password);
	    account.setCratetime(createtime);
	    account.setDepartmentid(departmentid);
	    account.setGroupid(groupid); 
	    request.getSession().setAttribute("uuid1", uid);
	    request.getSession().setAttribute("ppassword1", password);
	    	    
	    dy.set("ser_account",account);
	    return mapping.findForward("user");
	}
	
	/**
	 * 修改用户
	 * */
	public ActionForward updateUser(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
  		HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		AccountBO bo=new AccountBO();
		Search_Account account=(Search_Account)dy.get("ser_account");
		
		String uuid = (String)request.getSession().getAttribute("uuid1");
        	 try{
        		 bo.updateUser(account,uuid);
        		 ActionMessages messages=new ActionMessages();
			    	messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.update.cardid"));
			    	this.saveMessages(request,messages);
        		 
	 
        	 }catch(Exception e){
        		 log.error("==修改用户==",e);
        	 }
       
		return mapping.findForward("update");
	}
	
	/**
	 * 删除用户
	 * */
	public ActionForward deleteUser(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
  		HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		AccountBO bo=new AccountBO();
		Search_Account account=(Search_Account)dy.get("ser_account");
		
		 try{
    		 bo.deleteUser(account);
    		 ActionMessages messages=new ActionMessages();
		    	messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.delete.cardid"));
		    	this.saveMessages(request,messages);
    	 }catch(Exception e){
    		 log.error("==删除用户==",e);
    	 }
		return mapping.findForward("update");
      }
}

