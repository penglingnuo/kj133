//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.util.List;
import java.util.Map;

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

import com.kj133.entity.BanType;
import com.kj133.entity.Search_BanType;
import com.kj133.entity.bo.BantypeBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;
import java.net.URLEncoder;

/**
 * MyEclipse Struts Creation date: 03-16-2007
 * 
 * XDoclet definition:
 * 
 * @struts.action scope="request" validate="true"
 */
public class BantypeAction extends DispatchAction {

	// --------------------------------------------------------- Instance
	// Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 *            班次
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dy = (DynaActionForm) form;
		Search_BanType ban = (Search_BanType) dy.get("bantype");
		BantypeBO bo = new BantypeBO();
		Global go = new Global();
		String bd = "";
		
		if (ban.getBd().equals("三八制")) {
			bd = "0";
		}
		if (ban.getBd().equals("四六制")) {
			bd = "1";
		}
		String sql = " select  * from bantype where ban_id = ? and bantypeid= ? ";
		int a = ban.getBan_id();
		
		String aa = String.valueOf(a);
		
		try {
			List count = go.juede1(sql, aa,bd);
			if (count != null && count.size() > 0) {
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"error.add.banciortype"));
				saveMessages(request, messages);
			} else {
				bo.save(ban);
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"success.add.locator"));
				saveMessages(request, messages);
				
			}
		} catch (Exception e) {
			log.error("==班次设置==",e);
		}
		return mapping.findForward("show");
	}

	/**
	 * 初始化时间设置
	 */
	public ActionForward initialization(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		WordlibBO lib = new WordlibBO();
		BantypeBO bo = new BantypeBO();
		try {
			
			List list = bo.getList();
			Map hour = lib.hour();
			Map minute = lib.minute();
			request.setAttribute("ban", list);
			request.setAttribute("hour", hour);
			request.setAttribute("minute", minute);
		} catch (Exception e) {
			log.error("==班次设置初始化==",e);
		}
		return mapping.findForward("type");
	}

	/**
	 * delte
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		BantypeBO bo = new BantypeBO();
		String id = request.getParameter("id");
		int bd = 2;
		String[] name = id.split("\\,");

		for (int i = 1; i < name.length; i++) {
			String[] name1 = name[i].split("\\ \\$ ");
			if (name1[1].equals("三八制")) {
				bd = 0;
			}
			if (name1[1].equals("四六制")) {
				bd = 1;
			}

			try {
				String bb = String.valueOf(bd);
				bo.delete(name1[0], bb);
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"success.delete.locatorid"));
				saveMessages(request, messages);
			} catch (Exception e) {
				log.error("==删除班次设置==",e);
			}
		}
		return mapping.findForward("show");
	}

	/**
	 * load
	 */
	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int bd1 = 2;
		String name = request.getParameter("banname");
		String bantypeid = request.getParameter("bantypeid");
		String ban_id = request.getParameter("ban_id");
		String bd = request.getParameter("bd");
		
		request.setAttribute("name1", name);
		request.setAttribute("bantypeid1", bantypeid);
		request.setAttribute("ban_id1", ban_id);
		request.setAttribute("bd1", bd);
		System.out.println("bantype>>>>>>>>>>>="+ request.getParameter("bd"));
		DynaActionForm dy = (DynaActionForm) form;
		BantypeBO bo = new BantypeBO();
		if (bd.equals("三八制")) {
			bd1 = 0;
		}
		if (bd.equals("四六制")) {
			
			bd1 = 1;
		}
		String bb = String.valueOf(bd1);
		try {
			Search_BanType setForm = (Search_BanType) dy.get("bantype");
			List list = bo.load1(bb, ban_id);
			BanType ban = (BanType) list.get(0);
			setForm.setBan_name(ban.getBan_name());
			setForm.setBd(bd);
			setForm.setBan_name2(name);// hidden
			setForm.setAdd_time(ban.getAdd_time());
			setForm.setQidao_time(ban.getQidao_time());
			setForm.setZaotui_time(ban.getZaotui_time());
			setForm.setBan_id(ban.getBan_id());

			String start_name = ban.getStart_time();// 上班时间
			int start_name_h = Integer.parseInt(start_name.substring(0, 2));
			int start_name_m = Integer.parseInt(start_name.substring(3, 5));
			setForm.setStart_time_h(String.valueOf(start_name_h));
			setForm.setStart_time_m(String.valueOf(start_name_m));

			String start_stime = ban.getStart_stime();// 上班开始考勤 改成=开始考勤时间
			int start_stime_h = Integer.parseInt(start_stime.substring(0, 2));
			int start_stime_m = Integer.parseInt(start_stime.substring(3, 5));
			setForm.setStart_stime_h(String.valueOf(start_stime_h));
			setForm.setStart_stime_m(String.valueOf(start_stime_m));

			String end_stime = ban.getEnd_stime();// 上班结束考勤 改成=结束考勤时间
			int end_stime_h = Integer.parseInt(end_stime.substring(0, 2));
			int end_stime_m = Integer.parseInt(end_stime.substring(3, 5));
			setForm.setEnd_stime_h(String.valueOf(end_stime_h));
			setForm.setEnd_stime_m(String.valueOf(end_stime_m));

			String end_time = ban.getEnd_time();// 下班时间
			int end_time_h = Integer.parseInt(end_time.substring(0, 2));
			int end_time_m = Integer.parseInt(end_time.substring(3, 5));
			setForm.setEnd_time_h(String.valueOf(end_time_h));
			setForm.setEnd_time_m(String.valueOf(end_time_m));

			dy.set("bantype", setForm);
		} catch (Exception e) {
			log.error("==加载班次设置==",e);
		}
		return mapping.findForward("load");
	}

	/**
	 * 初始化时间设置
	 */
	public ActionForward loadShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		WordlibBO lib = new WordlibBO();
		BantypeBO bo = new BantypeBO();
		request.getAttribute("name1");
		String a = request.getAttribute("bantypeid1").toString();
		String aa = request.getAttribute("ban_id1").toString();

		try {
			List list = bo.getList1(a, aa);

			Map hour = lib.hour();
			Map minute = lib.minute();
			request.setAttribute("ban", list);
			request.setAttribute("hour", hour);
			request.setAttribute("minute", minute);
		} catch (Exception e) {
			log.error("==班次设置初始化==",e);
		}
		return mapping.findForward("loadBanType");
	}

	/**
	 * update
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String bd1 = "";
		String ban_id1 = "";
		String bd = "";
		String bid = "";
		String bdd = "";
		String sql = "";
		
		Global go = new Global();
		BantypeBO bo = new BantypeBO();
		DynaActionForm dy = (DynaActionForm) form;
		Search_BanType ban = (Search_BanType) dy.get("bantype");
		int ban_id = ban.getBan_id();
		ban_id1 = String.valueOf(ban_id); 
		bd = request.getParameter("bd1");
		bid = request.getParameter("ban_id1");
		
		bdd = ban.getBd();
		if (bd.equals("三八制")) {
			bd1 = "0";
		}
		if (bd.equals("四六制")) {
			bd1 = "1";
		}
		if (ban.getBd().equals("三八制")) {
			bd1 = "0";
		}
		if (ban.getBd().equals("四六制")) {
			bd1 = "1";
		}
		sql = " select  * from bantype where ban_id = ? and bantypeid= ? ";


		try {
			
			if(bd.equals(bdd) && ban_id1.equals(bid)){
				bo.update(ban, bd1, bid);
				
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"success.update.locator"));
				saveMessages(request, messages);
			}else{
				List count = go.juede1(sql, ban_id1,bd1);
				if (count != null && count.size() > 0) {
					ActionMessages messages = new ActionMessages();
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.add.banciortype"));
					saveMessages(request, messages);
				} else {
					
					bo.update(ban, bd1, bid);
					
					ActionMessages messages = new ActionMessages();
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"success.update.locator"));
					saveMessages(request, messages);
				}
			}
		} catch (Exception e) {
			log.error("==更新班次设置==",e);
		}
		return mapping.findForward("show");
	}
}
