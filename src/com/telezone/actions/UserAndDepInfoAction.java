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
import com.telezone.services.IUserAndDepInfo;
import com.telezone.serviesImpl.ReportPopedomImpl;
import com.telezone.serviesImpl.UserAndDepInfoImpl;

public class UserAndDepInfoAction extends DispatchAction{
	private IUserAndDepInfo useranddepinfo;
	

	/**
	 * 默认页面加载方法
	 */
	public ActionForward initialization(
			ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {
		this.setUseranddepinfo(new UserAndDepInfoImpl());
		
//		HttpSession session = request.getSession();
//		Ouser ouser = (Ouser) session.getAttribute("user");
		
		Map<String, Object> returnMap = useranddepinfo.initialization();
		
		request.setAttribute("ouser", returnMap.get("ouser"));
		request.setAttribute("staffer", returnMap.get("staffer"));
		
		return mapping.findForward("success");
	}
	
	/**
	 * 获得用户的部门权限
	 * @return
	 */
	public void getOuserDepartmentinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String userid = request.getParameter("userid") == null ? "0" : request.getParameter("userid");
		
		this.setUseranddepinfo(new UserAndDepInfoImpl());
		
		JSONObject jsonObj = new JSONObject();
		
		Map<String, Object> returnMap = useranddepinfo.getOuserDepartment(userid);
		
		jsonObj.putAll(returnMap);
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改用户的部门权限
	 * @return
	 */
	public void modifyOuserDepartmentinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String userid = request.getParameter("userid") == null ? "0" : request.getParameter("userid");
		String department = request.getParameter("department") == null ? "0" : request.getParameter("department");
		
		this.setUseranddepinfo(new UserAndDepInfoImpl());
		
		JSONObject jsonObj = new JSONObject();
		
		Map<String, Object> returnMap = useranddepinfo.modifyOuserDepartment(userid, department);
		
		jsonObj.putAll(returnMap);
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public IUserAndDepInfo getUseranddepinfo() {
		return useranddepinfo;
	}

	public void setUseranddepinfo(IUserAndDepInfo useranddepinfo) {
		this.useranddepinfo = useranddepinfo;
	}
}
