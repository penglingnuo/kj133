//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.kj133.entity.Search_manamount_query;
import com.kj133.entity.bo.Manamount_queryBO;

import com.kj133.entity.bo.WordlibBO;

import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Manamount_queryAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
//	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  人数曲线
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

        DynaActionForm dy=(DynaActionForm)form;

        
        Manamount_queryBO bo=new Manamount_queryBO();
        WordlibBO lib=new WordlibBO();
	    try{
	    	Search_manamount_query  manamount_query=(Search_manamount_query)dy.getMap().get("manamount_query");
 	    	List areaname = lib.areaname();
		    request.setAttribute("areaname_list",areaname);
		    List list=bo.init(manamount_query);
	        	 if( list.size()== 0){
	        		   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
	        	 }else{
	        		 String stime = manamount_query.getStime();
	        		 String etime = manamount_query.getEtime();
	        		 
	        		 request.setAttribute("stime",stime);
	        		 request.setAttribute("etime",etime);
	        		 request.setAttribute("relist",list);
	        	 }

	        	 dy.set("manamount_query",manamount_query);
	        }catch(Exception e){
//	        	e.printStackTrace();
	        	log.error("==人数曲线查询==",e);
	        }
			return mapping.findForward("show");
	}
	
	
	public ActionForward getWordlib(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			Global go=new Global();
	        DynaActionForm dy=(DynaActionForm)form;
		    WordlibBO lib=new WordlibBO();
			  try{
				  Search_manamount_query manamount_query=(Search_manamount_query)dy.getMap().get("manamount_query");
				   Calendar cal=Calendar.getInstance();//获取当前时间
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				   String etime=format.format(cal.getTime());
			       String stime=go.getDay(etime,-7);   
			       manamount_query.setStime(stime+" 00:00:00");
			       manamount_query.setEtime(etime+" 00:00:00");

			      List areaname = lib.areaname();
			      request.setAttribute("areaname_list",areaname);
				   dy.set("manamount_query",manamount_query);
				}catch(Exception e){
					
//					e.printStackTrace();
					log.error("==人数曲线查询==",e);
				}
			    return mapping.findForward("show");
			 }
	
	
}

