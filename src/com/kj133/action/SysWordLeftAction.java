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

import com.kj133.entity.Ouser;
import com.kj133.entity.Search_AreaCheckStat;
import com.kj133.entity.bo.SysWordLeftBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class SysWordLeftAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  系统词库维护
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

	    try{
	    	SysWordLeftBO bo = new SysWordLeftBO();
	    	List list=bo.init();
	    	
	    	request.setAttribute("relist",list);
	        request.setAttribute("listCount", list.size());
	        }catch(Exception e){
	        	log.error("==系统词库维护==",e);
	        	 
	        }
			return mapping.findForward("show");
			
	}
	
	
	
	public ActionForward load(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		
		String maxlines = request.getParameter("maxlines");
		String wordname = request.getParameter("wordname");
		
		try {
			wordname = new String(wordname.getBytes("iso8859-1"), "UTF-8");
		} catch (Exception e) {
		}
		
		String maxvalue = request.getParameter("maxvalue");
		String minvalue = request.getParameter("minvalue");
		request.getSession().setAttribute("maxlines", maxlines);
		request.getSession().setAttribute("wordname", wordname);
		request.getSession().setAttribute("maxvalue", maxvalue);
		request.getSession().setAttribute("minvalue", minvalue);

		    try{
		    	
		    	dy.set("wordname",wordname);
		    	dy.set("maxvalue",maxvalue);
		    	dy.set("minvalue",minvalue);
		        }catch(Exception e){
		        	log.error("==加载系统词库维护==",e);
		        	 
		        }
				return mapping.findForward("load");
				
		}
	/**
	 * 修改
	 */
	public ActionForward update(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		  SysWordLeftBO bo = new SysWordLeftBO();
	        WordlibBO lib=new WordlibBO();
		    DynaActionForm dy=(DynaActionForm)form;
		    String maxvalue=dy.getString("maxvalue");  
		    String minvalue=dy.getString("minvalue");  
		    String wordname=dy.getString("wordname");  
		    String maxlines = request.getSession().getAttribute("maxlines").toString();
		    String sql=" update wordlib set MaxValue=?,MinValue=?,MaxLines=?,IfUpdate=0 where WordName=? ";
		    Object[] object=new Object[]{maxvalue,minvalue,maxlines,wordname};
		    try{
		    	List gro=lib.workType();
				request.setAttribute("workType_list",gro);
		    	bo.executeSpecoalSQL(sql,object);
		    	ActionMessages messages=new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.update.cardid"));
				saveMessages(request, messages);
		    }catch(Exception e){
		    	log.error("==修改系统词库维护==",e);
		    }
		    return mapping.findForward("update");
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
				  Search_AreaCheckStat areacheckstat=(Search_AreaCheckStat)dy.getMap().get("ser_areacheckstat");
				   Calendar cal=Calendar.getInstance();//获取当前时间
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				   String etime=format.format(cal.getTime());
			 
			       String stime=go.getDay(etime,-7);   
			       areacheckstat.setStime(stime+" 08:30:46");
			       areacheckstat.setEtime(etime+" 20:54:39");
			       Ouser user=(Ouser)request.getSession().getAttribute("user");
				   List gro=lib.gro(user.getUserid()); 
				   List type=lib.workType();
//				   List ban=lib.banName();
				   List areaid=lib.areaid();
				   request.setAttribute("gro_list",gro);
				   request.setAttribute("workType_list",type);
				   request.setAttribute("areaid_list", areaid);
//				   request.setAttribute("ban_list", ban);
				   request.setAttribute("listCount", "0");
				   dy.set("ser_areacheckstat",areacheckstat);
				}catch(Exception e){
					log.error("==系统词库维护==",e);
				}
			    return mapping.findForward("wordlib");
			 }
	 /**
	 * 打印预览
	 * */
	@SuppressWarnings("unchecked")
	public ActionForward viewPrint(
			ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		    ServletContext context =this.getServlet().getServletConfig().getServletContext();
		    response.setContentType("text/html"); 
			try{
				 List list=(List)request.getSession().getAttribute("downWell_listPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/downWell.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 map.put("summation",request.getSession().getAttribute("allWorkTime").toString());

	 			 JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,map,dataSource);
	 			 request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
	    		 
	 			 JRHtmlExporter exporter = new JRHtmlExporter();
	 			 int pageIndex = 0;
	 			 int lastPageIndex = 0;
	 			 if (jasperPrint.getPages() != null)
	 			 {
	 				lastPageIndex = jasperPrint.getPages().size() - 1;
	 			 }
	 			 String pageStr = request.getParameter("page");
	 			 try
	 			 {
	 				pageIndex = Integer.parseInt(pageStr);
	 			 }
	 			 catch(Exception e)
	 			 {
	 				 
	 			 }	 	 		
	 			 if (pageIndex < 0)
	 			 {
	 				pageIndex = 0;
	 			 }
	 			 if (pageIndex > lastPageIndex)
	 			 {
	 			 	pageIndex = lastPageIndex;
	 			 }
	 			 request.setAttribute("pageIndex", pageIndex);
	 			 request.setAttribute("lastPageIndex", lastPageIndex);
	 			
	    		 StringBuffer sbuffer = new StringBuffer();
	    		 request.setAttribute("sb", sbuffer);
	    		  
	    		 exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);//对单个模板进行操作
	    	     exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
	    	     
	    	     exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
	    	     exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(pageIndex));
      	         exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
                 exporter.exportReport();
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
	        return mapping.findForward("vprint");
	}
	
	/**
	 *打印
	 * */
	@SuppressWarnings("unchecked")
	public ActionForward print(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
	     	ServletContext context = this.getServlet().getServletConfig().getServletContext();
	   	     File reportFile = new File(context.getRealPath("/reports/downWell.jasper"));
	   	     HashMap map=new HashMap();
	   	    
			 map.put("summation", request.getSession().getAttribute("allWorkTime").toString());
		   
			 
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("downWell_listPrint");
				JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
				jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource );
			}
			catch (Exception e)
			{
			  e.printStackTrace();
			}
			if (jasperPrint != null)
			{
				response.setContentType("application/octet-stream");
				ServletOutputStream ouputStream = response.getOutputStream();			
				ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
				oos.writeObject(jasperPrint);
				oos.flush();
				oos.close();
				ouputStream.flush();
				ouputStream.close();
			}
			else
			{
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>JasperReports - Web Application Sample</title>");
				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
				out.println("</head>");
				out.println("<body bgcolor=\"white\">");
				out.println("<span class=\"bold\">downWell.Jasper文件不存在或解析错误！</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
}

