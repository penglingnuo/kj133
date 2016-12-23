//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Ouser;
import com.kj133.entity.Search_DownWellCount;
import com.kj133.entity.bo.UnWorkBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 27-05-2008
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class UnWorkAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request 未出勤人员明细
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		UnWorkBO bo=new UnWorkBO();
        DynaActionForm dy = (DynaActionForm) form;
        WordlibBO lib=new WordlibBO();
        Pagination pagination = new Pagination();//分页
		String page =(String)request.getParameter("page");//获取页号分页
		List relist = null;
	    try{
	    	 Search_DownWellCount downwell=(Search_DownWellCount)dy.get("ser_downwellcount");
	    	 request.getSession().setAttribute("downWellStime", downwell.getStime());//传到报表上
	    	 request.getSession().setAttribute("downWellEtime", downwell.getEtime());
	    	 Ouser user=(Ouser)request.getSession().getAttribute("user");
	    	 String userid=user.getUserid();
	    	 pagination.setCount(200);//F
	    	 if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page)); 
		      }else {
		        pagination.setPage(1);
		      }//分页
	    	List list=bo.init(userid,downwell,pagination);
	    	relist=bo.initPrint(userid,downwell);
	    	request.setAttribute("listCount", list.size());
	    	if( list.size()== 0){
	    		   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
	    	}else{
	    		   request.setAttribute("unwork_List",list);
	    	}
	    	request.getSession().setAttribute("unwork", downwell);
	    	request.getSession().setAttribute("unworkPrint", relist);
	    	
	    	List gro=lib.gro(user.getUserid()); 
			List type=lib.workType();
			request.setAttribute("gro_list",gro);
			request.setAttribute("workType_list",type);
	        request.setAttribute("pagination", pagination);//分页
	        dy.set("ser_downwellcount",downwell);//重新把值设置回页面 
	    }catch(Exception e){
	    	log.error("==未出勤人员明细==",e);
	    }
		return mapping.findForward("show");
	}
   
	/**
	 * 初始化时间
	 * */
	public ActionForward getWordlib(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    WordlibBO bo=new WordlibBO();
			Global go=new Global();
			try{
				   DynaActionForm dy = (DynaActionForm) form;
				   Search_DownWellCount downwell=(Search_DownWellCount)dy.get("ser_downwellcount");
				  
				   Calendar cal=Calendar.getInstance();//获取当前时间
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				   String etime=format.format(cal.getTime());
			 
			       String stime=go.getDay(etime,-7);   
			       downwell.setStime(stime);
			       downwell.setEtime(etime);
			       Ouser user=(Ouser)request.getSession().getAttribute("user");
				   List gro=bo.grolist(user.getUserid()); 
				   List type=bo.workType();				   
				   request.setAttribute("gro_list",gro);
				   request.setAttribute("workType_list",type);
				   request.setAttribute("listCount", "0");
				   dy.set("ser_downwellcount",downwell);
				}catch(Exception e){
					log.error("==未出勤人员明细==",e);
				}
			  return mapping.findForward("init");
	     }
	
	 
	
}

