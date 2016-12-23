package com.telezone.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.telezone.services.IShowBackup;
import com.telezone.serviesImpl.ShowBackupImpl;

public class ShowBackupAction extends DispatchAction {
	private IShowBackup iShowBackup;

	/**
	 * ���������壺��ʾ�ط�����
	 * 
	 * @Date 2010 - 08 - 23
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("success");
	}

	/**
	 * ���������壺������ԱID��������ʾ����ľ��¹켣
	 * 
	 * @Date 2010 - 08 - 28
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception
	 */
	public void searchHistoryByCardidDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setIShowBackup(new ShowBackupImpl());

		String keyWord = request.getParameter("c") == null ? "0" : request
				.getParameter("c");
		String date = request.getParameter("d") == null ? "0" : request
				.getParameter("d");

		Map<String, Object> returnMap = iShowBackup.searchHistoryByCardidDate(
				keyWord, date);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("list", returnMap.get("list"));
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���������壺������Ա��ʾ���¹켣
	 * 
	 * @Date 2010 - 08 - 23
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception
	 */
	public void search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setIShowBackup(new ShowBackupImpl());

		String keyWord = request.getParameter("c") == null ? "0" : request
				.getParameter("c");
		String recordid = request.getParameter("r") == null ? "0" : request
				.getParameter("r");

		Map<String, Object> returnMap = iShowBackup.search(keyWord, recordid);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sinfo", returnMap.get("sinfo"));
		jsonObj.put("data", returnMap.get("returnList"));

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IShowBackup getIShowBackup() {
		return iShowBackup;
	}

	public void setIShowBackup(IShowBackup showBackup) {
		iShowBackup = showBackup;
	}
}
