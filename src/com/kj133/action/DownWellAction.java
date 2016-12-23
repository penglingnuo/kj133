//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.speedframework.entity.Pagination;

import com.kj133.entity.Ouser;
import com.kj133.entity.Search_ShuaKa;
import com.kj133.entity.bo.DownWellBO;
import com.kj133.entity.bo.WordlibBO;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class DownWellAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  井下考勤
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        Pagination pagination = new Pagination();
        DynaActionForm dy=(DynaActionForm)form;
		String page =(String)request.getParameter("page");//获取页号
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Calendar cal=Calendar.getInstance();//获取当前时间
		   cal.add(Calendar.DATE, +1); 
		   Date date = new Date();
		   String stime=format.format(cal.getTime());
	       String etime=format.format(date); 
        DownWellBO bo=new DownWellBO();
        WordlibBO lib=new WordlibBO();
	    try{
				 Ouser user=(Ouser)request.getSession().getAttribute("user");
				 String userid = user.getUserid();
				 
	        	 Search_ShuaKa  downwell=(Search_ShuaKa)dy.getMap().get("ser_shuaka");
	        	 downwell.setUserid(userid);
	        	 
	        	 
	        	 pagination.setCount(200); 
			     if (page != null && page.length() > 0) {
			        pagination.setPage(Integer.parseInt(page));
			      }else {
			        pagination.setPage(1);
			      }
			     
	        	 List list=bo.init(downwell,pagination);
	        	 
	        	 request.setAttribute("listCount", list.size());
	        	 List relist=bo.initPrint(downwell);
	        	 if( list.size()== 0){
	        		   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
	        	 }else{
	        		 request.setAttribute("downWell_list",list);
	        	 }
	        	 request.getSession().setAttribute("downwell", downwell);//结果集
	        	 request.getSession().setAttribute("downWellPrint", relist);//结果集
	        	 List gro=lib.grolist(user.getUserid()); 
				 List type=lib.workType();
				 List ban=lib.banName();
				 
				 if(downwell.getMintime()==null){
					 downwell.setMintime("15:47:51");
	        	 }else{
	        		 downwell.setMintime(downwell.getMintime());
	        		 request.setAttribute("name7", "true");
	        	 }if(downwell.getMaxtime()==null){
	        		 downwell.setMaxtime("15:47:51");
	        	 }else{
	        		 downwell.setMaxtime(downwell.getMaxtime());
	        		 request.setAttribute("name8", "true");
	        	 }if(downwell.getStime()==null){
	        		 downwell.setStime(etime);
	        	 }else{
	        		 downwell.setStime(downwell.getStime());
	        		 request.setAttribute("name9", "true");
	        	 }if(downwell.getEtime()==null){
	        		 downwell.setEtime(stime);
	        	 }else{
	        		 downwell.setEtime(downwell.getEtime());
	        		 request.setAttribute("name10", "true");
	        	 }if(downwell.getDstime()==null){
	        		 downwell.setDstime("15:47:51");
	        	 }else{
	        		 downwell.setDstime(downwell.getDstime());
	        		 request.setAttribute("name1", "true");
	        	 }if(downwell.getDetime()==null){
	        		 downwell.setDetime("15:47:51");
	        	 }else{
	        		 downwell.setDetime(downwell.getDetime());
	        		 request.setAttribute("name3", "true");
	        	 }if(downwell.getUstime()==null){
	        		 downwell.setUstime("15:47:51");
	        	 }else{
	        		 downwell.setUstime(downwell.getUstime());
	        		 request.setAttribute("name2", "true");
	        	 }if(downwell.getUetime()==null){
	        		 downwell.setUetime("15:47:51");
	        	 }else{
	        		 downwell.setUetime(downwell.getUetime());
	        		 request.setAttribute("name4", "true");
	        	 }if(downwell.getKsdate()==null){
	        		 downwell.setKsdate(etime);
	        	 }else{
	        		 downwell.setKsdate(downwell.getKsdate());
	        		 request.setAttribute("name6", "true");
	        	 }if(downwell.getKedate()==null){
	        		 downwell.setKedate(stime);
	        	 }else{
	        		 downwell.setKedate(downwell.getKedate());
	        		 request.setAttribute("name5", "true");
	        	 }
				 request.setAttribute("gro_list",gro);
				 request.setAttribute("workType_list",type);
	        	 request.setAttribute("pagination", pagination);
	        	 request.setAttribute("ban_list", ban);
	        	 dy.set("ser_shuaka",downwell);
	        }catch(Exception e){
	        	log.error("==井下考勤明细==",e);
	        }
			return mapping.findForward("show");
	}
	
	public ActionForward getWordlib(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        DynaActionForm dy=(DynaActionForm)form;
		    WordlibBO lib=new WordlibBO();
			  try{
				   Search_ShuaKa shuak=(Search_ShuaKa)dy.getMap().get("ser_shuaka");
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				   Calendar cal=Calendar.getInstance();//获取当前时间
				   cal.add(Calendar.DATE, +1); 
				   Date date = new Date();
				   String stime=format.format(cal.getTime());
			       String etime=format.format(date); 

			       shuak.setStime(etime);
				   shuak.setEtime(stime);
				   shuak.setMintime("15:47:51");
				   shuak.setMaxtime("15:47:51");
				   shuak.setDstime("15:47:51");
				   shuak.setDetime("15:47:51");
				   shuak.setUstime("15:47:51");
				   shuak.setUetime("15:47:51");
				   shuak.setKsdate(etime);
				   shuak.setKedate(stime);
				   Ouser user=(Ouser)request.getSession().getAttribute("user");
				   List gro=lib.grolist(user.getUserid()); 
				   List type=lib.workType();
				   List ban=lib.banName();
				   request.setAttribute("gro_list",gro);
				   request.setAttribute("workType_list",type);
				   request.setAttribute("ban_list", ban);
				   request.setAttribute("listCount", "0");
				   dy.set("ser_shuaka",shuak);
				}catch(Exception e){
					log.error("==井下考勤明细==",e);
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

