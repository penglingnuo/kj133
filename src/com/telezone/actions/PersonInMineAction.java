package com.telezone.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.kj133.entity.Ouser;
import com.telezone.domain.classes.CardReader;
import com.telezone.domain.classes.PersonInMineDetailInfo;
import com.telezone.services.IGas;
import com.telezone.services.IPersonInMine;
import com.telezone.serviesImpl.GasImpl;
import com.telezone.serviesImpl.PersonInMineImpl;

public class PersonInMineAction extends DispatchAction {
	private IPersonInMine personInMine;

	private final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @Date 2010 - 06 - 12
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 * 
	 */
	public ActionForward In(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		this.setPersonInMineImpl(new PersonInMineImpl());

		returnMap = personInMine.getThePlaceOfPersonsInMine();

		List<PersonInMineDetailInfo> list = (List<PersonInMineDetailInfo>) returnMap
				.get("info");
		request.setAttribute("info", list);

		return mapping.findForward("success");
	}

	/**
	 * 
	 * @Date 2010-06-19
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 */
	public ActionForward downArea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		this.setPersonInMineImpl(new PersonInMineImpl());

		Map<String, Object> returnMap = new HashMap<String, Object>();

		returnMap = personInMine.getTheNumberOfPersonsInMine();

		List<CardReader> workedCardReaderList = (List<CardReader>) returnMap
				.get("workedCardReaderList");
		List<CardReader> personInCardReaderList = (List<CardReader>) returnMap
				.get("personInCardReaderList");
		List resultList = (List) returnMap.get("resultList");

		request.setAttribute("CardReader", workedCardReaderList);
		request.setAttribute("personInCardReaderList", personInCardReaderList);
		request.setAttribute("resultList", resultList);

		return mapping.findForward("personInMineArea");
	}

	/**
	 * 
	 * @Date 2010-06-24
	 * 
	 * @author LYee
	 * 
	 * @return null
	 * @throws Exception
	 */
	public ActionForward detailedData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setPersonInMineImpl(new PersonInMineImpl());

		request.setCharacterEncoding("UTF-8");

		String cardreaderid = request.getParameter("c") == null ? "0" : request
				.getParameter("c");
		String department = request.getParameter("d") == null ? "0" : request
				.getParameter("d");

		department = new String(department.getBytes("GBK"), "GB2312").trim();

		Map<String, Object> returnMap = new HashMap<String, Object>();

		returnMap = personInMine.detailedData(cardreaderid, department);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("data", returnMap.get("list"));

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @Date 2010-06-29
	 * 
	 * @author LYee
	 * 
	 * @return null
	 */
	public ActionForward getLocationData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setPersonInMineImpl(new PersonInMineImpl());
		String userid = request.getSession().getAttribute("user1").toString();
		Map<String, Object> returnMap = new HashMap<String, Object>();

//		returnMap = personInMine.locationData();
		returnMap = personInMine.locationDataByuserid(userid);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("data", returnMap.get("list"));

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * 根据员工姓名查询员工信息
	 * @Date 2010-06-29
	 * 
	 * @author LYee
	 * 
	 * @return null
	 */
	public ActionForward getLocationDataByStaffername(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setPersonInMineImpl(new PersonInMineImpl());
		String userid = request.getSession().getAttribute("user1").toString();
		String staffername = request.getParameter("staffername");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap = personInMine.locationDataByuseridandstaffername(userid,staffername);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("data", returnMap.get("list"));

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * 
	 * @Date 2010-07-01
	 * 
	 * @author LYee
	 * 
	 * @return null
	 */
	public ActionForward getWayOfStaffera(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setPersonInMineImpl(new PersonInMineImpl());

		request.setCharacterEncoding("gb2312");

		String mapId = request.getParameter("id") == null ? "0" : request
				.getParameter("id");
		String cardID = request.getParameter("cid") == null ? "0" : request
				.getParameter("cid");

		mapId = new String(mapId.getBytes("GB2312"), "GB2312");
		cardID = new String(cardID.getBytes("GB2312"), "GB2312");

		Map<String, Object> returnMap = new HashMap<String, Object>();

		returnMap = personInMine.getWayOfStaffer(mapId, cardID);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("data", returnMap.get("list"));

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @Date 2010-07-05
	 * 
	 * @author LYee
	 * 
	 * @return null
	 */
	public ActionForward getStafferInMine(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setPersonInMineImpl(new PersonInMineImpl());
		return null;
	}

	/**
	 * 
	 * @Date 2010-07-08
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 */
	public ActionForward getOutOfTimeCard(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setPersonInMineImpl(new PersonInMineImpl());

		Map<String, Object> outOcTimeCard = (Map) personInMine
				.getOutOfTimeCard();

		List<PersonInMineDetailInfo> list = (List<PersonInMineDetailInfo>) outOcTimeCard
				.get("list");

		request.setAttribute("outOfTimeCard", list);

		return mapping.findForward("outOfTimeCard");
	}

	/**
	 * 
	 * @Date 2010-07-10
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setPersonInMineImpl(new PersonInMineImpl());
		
		Map<String, Object> returnMap = personInMine.search();
		
		return mapping.findForward("search");
	}

	/**
	 * 
	 * @Date 2010-07-10
	 * 
	 * @author LYee
	 * 
	 * @return null
	 */
	public ActionForward searchContent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setPersonInMineImpl(new PersonInMineImpl());

		String content = request.getParameter("c") == null ? "0" : request
				.getParameter("c");
		String cardid=null;
		if(content!=null&& !content.equals("")){
			String []cs=content.split("--");
			cardid=cs[0];
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		Ouser user =(Ouser)request.getSession().getAttribute("user");

		paramMap.put("content", cardid);
		paramMap.put("userid", user.getUserid());

		Map<String, Object> returnMap = personInMine.search(paramMap);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("list", returnMap.get("list"));

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}
	
	/**
	 * 
	 * @Date 2010-08-14
	 * 
	 * @author LYee
	 * 
	 * @return null
	 */
	public void getStafferInCardReader(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		this.setPersonInMineImpl(new PersonInMineImpl());
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		String type = request.getParameter("type") == null ? "0" : request.getParameter("type");
		String ground = request.getParameter("ground") == null ? "0" : request.getParameter("ground");
		String userid = request.getSession().getAttribute("user1").toString();
		
		Map<String, Object> returnMap = personInMine.getStafferInCardReader(id, type, ground,userid);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("cr", returnMap.get("cr"));
		jsonObj.put("list", returnMap.get("list"));

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	/**
	 * 
	 * @Date 2010-08-14
	 * 
	 * @author LYee
	 * 
	 * @return null
	 */
	public void getStafferInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		this.setPersonInMineImpl(new PersonInMineImpl());
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		
		Map<String, Object> returnMap = personInMine.getStafferInfo(id);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("info", returnMap.get("info"));
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public IPersonInMine getPersonInMineImpl() {
		return personInMine;
	}

	public void setPersonInMineImpl(IPersonInMine personInMineImpl) {
		this.personInMine = personInMineImpl;
	}
}
