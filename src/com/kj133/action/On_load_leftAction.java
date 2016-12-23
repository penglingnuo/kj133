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
import com.kj133.entity.Search_OnLoad;
import com.kj133.entity.bo.On_load_leftBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-07-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class On_load_leftAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables
	
	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/**  
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request 在场信息查询
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        DynaActionForm dy=(DynaActionForm)form;
        On_load_leftBO bo=new On_load_leftBO();
         try{
        	   Search_OnLoad onload=(Search_OnLoad)dy.getMap().get("ser_onload");
        	   request.getSession().setAttribute("ktime", onload.getStime());
        	   request.getSession().setAttribute("jtime", onload.getEtime());
        	   
        	   Ouser user=(Ouser)request.getSession().getAttribute("user");
        	   String userid=user.getUserid();
        	   List list=bo.init(onload,userid);
        	   request.setAttribute("listCount", list.size());
        	   if( list.size()== 0){
        		   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
        	   }else{
        		   request.setAttribute("On_load_left",list);
        		   request.getSession().setAttribute("onload", onload);
        		   request.getSession().setAttribute("On_load_leftPrint", list);
        	   }
        	  
         }catch(Exception e){
        	 log.error("==在场信息查询==",e);
         }
		return mapping.findForward("show");
	}
	
	public ActionForward particular(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        On_load_leftBO bo=new On_load_leftBO();
	        String cardreaderId=request.getParameter("cid"); 
	        request.getSession().setAttribute("cid", cardreaderId);//传给报表
	        try{
	        	   Ouser user=(Ouser)request.getSession().getAttribute("user");
	        	   String userid=user.getUserid();
		    	   List list=bo.particular(userid,cardreaderId);
		    	   request.setAttribute("On_Load_Particular",list);
		    	   request.getSession().setAttribute("On_Load_ParticularPrint", list);
	        }catch(Exception e){
	        	log.error("==在场信息查询==",e);
	        }
		return mapping.findForward("particular");
	}
	/**
	 * 初始化时间
	 * */	
	public ActionForward initialization(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm)form;
			Global go=new Global();
			     try{
			    	   Search_OnLoad onload=(Search_OnLoad)dy.getMap().get("ser_onload");
			    	   
			    	   Calendar cal=Calendar.getInstance();//获取当前时间
					   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
					   String etime=format.format(cal.getTime());
				       String stime=go.getDay(etime,-7);   
				       onload.setStime(stime+" 08:30:46");
				       onload.setEtime(etime+" 20:54:39");
			           dy.set("ser_onload",onload);
			           request.setAttribute("listCount", "0");
			     }catch(Exception e){
			    	 log.error("==在场信息查询==",e);
			     }
		return mapping.findForward("show");
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
				 List list=(List)request.getSession().getAttribute("On_load_leftPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/onLoad_Left.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 map.put("ktime", request.getSession().getAttribute("ktime"));//起始时间
	 			 map.put("jtime", request.getSession().getAttribute("jtime"));//截止时间
	 			 /*map.put("cardreaderid", "分站号");
	 			 map.put("crname", "分站名称");
	 			 map.put("onscenecount", "在场人数");
	 			 map.put("mapid", "地图号");
	 			 map.put("mapname", "地图名称");*/
	 			
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
	    	     //exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "images目录的路径?image=");
                 //在生成html预览的时候会生成一个px文件，而这个images_uri的路径就是那个px的路径，建议不用，就用下面 的语句就可以了···· 
	    	     //不写的话，页面上就会显示很多图片，并且是那种图片加载不成功的状态
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
	   	     File reportFile = new File(context.getRealPath("/reports/onLoad_Left.jasper"));
	   	     HashMap map=new HashMap();
	   	     map.put("ktime", request.getSession().getAttribute("ktime"));//起始时间
			 map.put("jtime", request.getSession().getAttribute("jtime"));//截止时间
			 /*map.put("cardreaderid", "分站号");
			 map.put("crname", "分站名称");
			 map.put("onscenecount", "在场人数");
			 map.put("mapid", "地图号");
			 map.put("mapname", "地图名称");*/
			 
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("On_load_leftPrint");
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
				out.println("<span class=\"bold\">onLoad_Left.jasper文件不存在或解析错误！</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
	
	
}

