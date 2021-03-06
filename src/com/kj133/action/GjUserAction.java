//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.kj133.entity.GjUser;
import com.kj133.entity.bo.GjUserBO;
import com.kj133.util.SessionListener;

/**
 * MyEclipse Struts Creation date: 01-29-2007
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class GjUserAction extends DispatchAction {

	// --------------------------------------------------------- Instance
	// Variables

	// --------------------------------------------------------- Methods

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	private final Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 管技登陆
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward gjuserLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		GjUserBO bjbo = new GjUserBO();
		DynaActionForm dy = (DynaActionForm) form;
		GjUser gjuser = (GjUser) dy.get("gjuser");
		System.out.println("登录用户名=" + gjuser.getGjusername());
		try {
			HttpSession session = request.getSession();
			SessionListener listener = new SessionListener();
			GjUser gj = bjbo.getGjLogin(gjuser);
			
			if(gj==null){
				return mapping.findForward("gjno"); 
			}else{
				request.getSession().setAttribute("gjuser", gj);
				return mapping.findForward("success");
			}
		} catch (Exception e) {
			logger.error("==管技用户登陆==", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 管技用户注销
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ϵͳ
	 */
	public ActionForward gjuserLogOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("gjuser");
			session.invalidate();
		} catch (Exception e) {
			logger.error("==管技注销用户==", e);
			e.printStackTrace();
		}
		return mapping.findForward("gjlogout");
	}
	/**
	 * 管技修改密码
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updategjpwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		GjUserBO bjbo = new GjUserBO();
		DynaActionForm dy = (DynaActionForm) form;
		GjUser gjuser = new GjUser();
		GjUser gj=new GjUser();
		String oldpwd =request.getParameter("ser_updatePassword.oldPassword");
		String newpwd = request.getParameter("ser_updatePassword.secondPassword");
		String id = request.getParameter("id");
		String gjusername = request.getParameter("gjusername");
		gjuser.setGjusername(gjusername);
		gjuser.setGjuserpwd(oldpwd);
		try {
			//查询输入的原密码是否正确
			gj=bjbo.getGjLogin(gjuser);
			if(gj==null){
				ActionMessages messages=new ActionMessages();
			    messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.updatepassword"));
			    this.saveMessages(request,messages);
			}else{
				boolean result=bjbo.updategjpwd(null,newpwd, id);
				if(result){
					ActionMessages messages=new ActionMessages();
				    messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.updatepassword"));
				    this.saveMessages(request,messages);
				}
			}
		} catch (Exception e) {
			logger.error("==管技修改密码户==", e);
			e.printStackTrace();
		}
		return mapping.findForward("gjupdate");
	}
	
	/**
	 * 管技增加用户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addgjuser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		GjUserBO bjbo = new GjUserBO();
		String gjusername = request.getParameter("gjuser.gjusername");
		String gjuserpwd = request.getParameter("gjuser.gjuserpwd");
		GjUser gjuser = new GjUser(); 
		gjuser.setGjusername(gjusername);
		gjuser.setGjuserpwd(gjuserpwd);
		
		try {
			//检查用户名是否存在
			boolean res=bjbo.checkgjusername(gjusername);
			if(res){
				//不存在增加
				boolean result = bjbo.addgjuser(gjuser);
				if(result){
					ActionMessages messages=new ActionMessages();
				    messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.add.stafferid"));
				    this.saveMessages(request,messages);
				}
			}else{
				//存在
				ActionMessages messages=new ActionMessages();
			    messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.add.user_name"));
			    this.saveMessages(request,messages);
			}
		} catch (Exception e) {
			logger.error("==管技修改密码户==", e);
			e.printStackTrace();
		}
		return mapping.findForward("addgjuser");
	}
	
	/**
	 * 管技检查用户信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward checkgjusername(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		GjUserBO bjbo = new GjUserBO();
		String gjusername = request.getParameter("gjusername");
		boolean result=false;
		try {
			result = bjbo.checkgjusername(gjusername);
			if(!result){
				ActionMessages messages=new ActionMessages();
			    messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.add.user_name"));
			    this.saveMessages(request,messages);
			}
		} catch (Exception e) {
			logger.error("==管技检查用户信息==", e);
			e.printStackTrace();
		}
		return mapping.findForward("addgjuser");
	}
	
	/**
	 * 查询出所有管技用户信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getallgjuser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
   		GjUserBO bjbo = new GjUserBO();
		try {
			List gjlist = bjbo.selAlluser();
			request.getSession().setAttribute("gjlist", gjlist);
		} catch (Exception e) {
			logger.error("==查询出所有管技用户信息==", e);
			e.printStackTrace();
		}
		return mapping.findForward("toguanligjuser");
	}
	/**
	 * 删除管技用户信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delgjuser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
   		GjUserBO bjbo = new GjUserBO();
   		String id  = request.getParameter("id");
		try {
			boolean result = bjbo.delgjuser(id);
			if(result){
				ActionMessages messages=new ActionMessages();
			    messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.delete.cardid"));
			    this.saveMessages(request,messages);
			}
		} catch (Exception e) {
			logger.error("==删除管技用户信息==", e);
			e.printStackTrace();
		}
		return mapping.findForward("delgjuser");
	}
}
