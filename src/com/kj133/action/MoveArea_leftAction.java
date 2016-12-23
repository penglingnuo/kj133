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
import org.speedframework.entity.Pagination;

import com.kj133.entity.Ouser;
import com.kj133.entity.Search_MoveArea;
import com.kj133.entity.bo.MoveArea_leftBO;
import com.kj133.util.Global;


/** 
 * MyEclipse Struts
 * Creation date: 02-08-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class MoveArea_leftAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request 活动区域查询
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    MoveArea_leftBO bo=new MoveArea_leftBO();
	    DynaActionForm dy=(DynaActionForm)form;
	    Pagination pagination = new Pagination();
	    String page =(String)request.getParameter("page");//获取页号
	  
	        try{
	        	Search_MoveArea move=(Search_MoveArea)dy.getMap().get("ser_movearea");
	    	pagination.setCount(100); 
		     if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      }
	        
	    	Ouser user=(Ouser)request.getSession().getAttribute("user");
	    	String userid=user.getUserid();
	    	List list1=bo.init1(userid,move,pagination);
	    	List list=bo.init(userid,move);
	    	if( list1.size()== 0){
			   ActionMessages messages=new ActionMessages();
		       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
		       this.saveMessages(request,messages);
	    	}else{
	    		
			  request.setAttribute("MoveArea_List",list1);
			  request.setAttribute("pagination", pagination);
			  request.getSession().setAttribute("move", move);
			  request.getSession().setAttribute("MoveArea_ListPrint", list);
	    	}
	    	
	    }catch(Exception e){
	    	log.error("==活动区域查询==",e);
	    }
		return mapping.findForward("show");
	}
    
	/**
	 * 活动区域的详细信息  
	 * */
	public ActionForward particular(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    MoveArea_leftBO bo=new MoveArea_leftBO(); 
		    Pagination pagination = new Pagination();
	        String page =(String)request.getParameter("page");//获取页号
		    String cid=request.getParameter("cid");
		    request.setAttribute("cid", cid);
		    request.getSession().setAttribute("cidqy", cid);
		    try{
		    	pagination.setCount(100); 
			     if (page != null && page.length() > 0) {
			        pagination.setPage(Integer.parseInt(page));
			      }else {
			        pagination.setPage(1);
			      }
		    	 Ouser user=(Ouser)request.getSession().getAttribute("user");
			     String userid=user.getUserid();
		         List list1=bo.particular1(userid,cid,pagination);
		         request.setAttribute("listCount", list1.size());
		         request.setAttribute("Move_Area_Particular_List",list1);
		         request.setAttribute("pagination", pagination);
		    }catch(Exception e){
		    	log.error("==活动区域查询详细信息==",e);
		    }
		    	return mapping.findForward("particular");
	}
	/**
	 * 活动区域的详细信息-----分页  
	 * */
	public ActionForward particular1(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		MoveArea_leftBO bo=new MoveArea_leftBO(); 
		Pagination pagination = new Pagination();
		String page =(String)request.getParameter("page");//获取页号
		String cid = (String)request.getSession().getAttribute("cidqy");
		request.setAttribute("cid", cid);
		try{
			pagination.setCount(100); 
			if (page != null && page.length() > 0) {
				pagination.setPage(Integer.parseInt(page));
			}else {
				pagination.setPage(1);
			}
			Ouser user=(Ouser)request.getSession().getAttribute("user");
			String userid=user.getUserid();
			List list1=bo.particular1(userid,cid,pagination);
			request.setAttribute("listCount", list1.size());
			request.setAttribute("Move_Area_Particular_List",list1);
			request.setAttribute("pagination", pagination);
		}catch(Exception e){
			log.error("==活动区域查询分页==",e);
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
			Global go=new Global();
			DynaActionForm dy=(DynaActionForm)form;
		     try{
			       Search_MoveArea move=(Search_MoveArea)dy.getMap().get("ser_movearea"); 
			       Calendar cal=Calendar.getInstance();//获取当前时间
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				   String etime=format.format(cal.getTime());
			       String stime=go.getDay(etime,-7);   
			       move.setStime(stime+" 08:30:46");
			       move.setEtime(etime+" 20:54:39");
			       dy.set("ser_movearea",move);
			       request.setAttribute("listCount", "0");
		     }catch(Exception e){
		    	 log.error("==活动区域查询==",e);
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
				 List list=(List)request.getSession().getAttribute("MoveArea_ListPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/movearea.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 map.put("title", "活动区域表("+request.getSession().getAttribute("sid")+")");
	 			 /*map.put("username", "");
	 			 map.put("cardreaderid", "分站号");
	 			 map.put("crname", "分站名称");
	 			 map.put("appeartimes", "出现次数");
	 			 map.put("staytime", "总停留时间");
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
	   	     File reportFile = new File(context.getRealPath("/reports/movearea.jasper"));
	   	     HashMap map=new HashMap();
	   	     map.put("title", "活动区域表("+request.getSession().getAttribute("sid")+")");
	   	    /* map.put("cid", request.getSession().getAttribute("sid"));
			 map.put("username", "");
			 map.put("cardreaderid", "分站号");
			 map.put("crname", "分站名称");
			 map.put("appeartimes", "出现次数");
			 map.put("staytime", "总停留时间");
			 map.put("mapid", "地图号");
			 map.put("mapname", "地图名称");*/
		   
			 
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("MoveArea_ListPrint");
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
				out.println("<span class=\"bold\">movearea.Jasper文件不存在或解析错误！</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
}

