//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.kj133.entity.Ouser;
import com.kj133.entity.bo.UserBO;
import com.kj133.util.SessionListener;

/**
 * MyEclipse Struts Creation date: 01-29-2007
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class LoginAction extends DispatchAction {

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

	@SuppressWarnings( { "unchecked", "static-access" })
	public ActionForward userLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBO bo = new UserBO();
		Ouser log = null;
		DynaActionForm dy = (DynaActionForm) form;

		String modelIP = request.getParameter("modelip");

		try {
			Ouser user = (Ouser) dy.get("user");
			System.out.println("登录用户名=" + user.getUserid());
			HttpSession session = request.getSession();

			SessionListener listener = new SessionListener();
			@SuppressWarnings("unused")
			boolean boo = listener.isLogined(session, user.getUserid());
			log = bo.getLog(user);

			if (log == null) {
				return mapping.findForward("no");
			} else {

				String name = log.getOname();

				Ouser ou = bo.getLogin(user, name);

				if (ou == null) {
					return mapping.findForward("no");// �û����������
				} else {
					request.getSession().setAttribute("user", ou);
					request.getSession().setAttribute("listCount1", "0");
					request.getSession().setAttribute("user1", ou.getUserid());

					return mapping.findForward("success");
				}
			}
		} catch (Exception e) {
			logger.error("==��¼==", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return �˳�ϵͳ
	 */
	public ActionForward userLogOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			session.invalidate();
		} catch (Exception e) {
			logger.error("==�˳�ϵͳ���==", e);
			e.printStackTrace();
		}
		return mapping.findForward("logout");
	}
}
