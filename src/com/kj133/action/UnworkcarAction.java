//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import com.kj133.entity.Search_unworkcar;
import com.kj133.entity.bo.UnworkcarBO;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class UnworkcarAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  时段未出勤车辆统计
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        DynaActionForm dy=(DynaActionForm)form;
        UnworkcarBO bo=new UnworkcarBO();
	    try{
	    	Search_unworkcar  unworkcar=(Search_unworkcar)dy.getMap().get("unworkcar");
	        	 List list=bo.init(unworkcar);
	        	 request.setAttribute("listCount", list.size());

	        	 if( list.size()== 0){
	        		   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
	        	 }else{
	        		 request.setAttribute("unworkcar_list",list);
	        		 request.getSession().setAttribute("unworkcar", unworkcar);//条件参数
	        		 request.getSession().setAttribute("unworkcarPrint", list);//结果集
	        	 }
	        	 dy.set("unworkcar",unworkcar);
	        }catch(Exception e){
	        	log.error("==时段未出勤车辆统计==",e);
	        }
			return mapping.findForward("show");
	}
	
	public ActionForward getWordlib(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        DynaActionForm dy=(DynaActionForm)form;
			  try{
				  Search_unworkcar unworkcar=(Search_unworkcar)dy.getMap().get("unworkcar");
				  SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				   Calendar cal=Calendar.getInstance();//获取当前时间
				   cal.add(Calendar.DATE, -7); 
				   Date date = new Date();
				   String stime=format.format(cal.getTime());
			 
			       String etime=format.format(date);  

			       unworkcar.setStime(stime);
			       unworkcar.setEtime(etime);
				   request.setAttribute("listCount", "0");
				   dy.set("unworkcar",unworkcar);

				}catch(Exception e){
					log.error("==时段未出勤车辆统计==",e);
				}
			    return mapping.findForward("wordlib");
			 }
	 
}
