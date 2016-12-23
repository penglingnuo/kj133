package com.telezone.actions;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.telezone.services.IReportPopedom;
import com.telezone.serviesImpl.ReportPopedomImpl;

public class ReportPopedomAction extends DispatchAction{
	private IReportPopedom reportpopedom;
	
	/**
	 * 默认页面加载方法
	 */
	public ActionForward initialization(
			ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {
		this.setReportpopedom(new ReportPopedomImpl());
		
//		HttpSession session = request.getSession();
//		Ouser ouser = (Ouser) session.getAttribute("user");
		
		Map<String, Object> returnMap = reportpopedom.initialization();
		
		request.setAttribute("ouser", returnMap.get("ouser"));
		request.setAttribute("staffer", returnMap.get("staffer"));
		
		return mapping.findForward("success");
	}
	
	/**
	 * 获得用户的报表权限
	 * @return
	 */
	public void getOuserReportPopedom(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String userid = request.getParameter("userid") == null ? "0" : request.getParameter("userid");
		
		this.setReportpopedom(new ReportPopedomImpl());
		
		JSONObject jsonObj = new JSONObject();
		
		Map<String, Object> returnMap = reportpopedom.getOuserReportPopedom(userid);
		
		jsonObj.putAll(returnMap);
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改用户的报表权限
	 * @return
	 */
	public void modifyOuserReportPopedom(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String userid = request.getParameter("userid") == null ? "0" : request.getParameter("userid");
		String department = request.getParameter("department") == null ? "0" : request.getParameter("department");
		userid.trim();
		this.setReportpopedom(new ReportPopedomImpl());
		
		JSONObject jsonObj = new JSONObject();
		
		Map<String, Object> returnMap = reportpopedom.modifyOuserReportPopedom(userid, department);
		
		jsonObj.putAll(returnMap);
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setReportpopedom(IReportPopedom reportpopedom) {
		this.reportpopedom = reportpopedom;
	}
}
