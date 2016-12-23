//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;


import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.kj133.entity.Locator;
import com.kj133.entity.Search_locator;

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

import com.kj133.entity.bo.LocatorBO;

/** 
 * MyEclipse Struts
 * Creation date: 01-30-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LocalizerAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request 编辑定位器
	 * @param response
	 * @return ActionForward
	 */
	private final Logger  log=Logger.getLogger(this.getClass());
	/**
	 * 获取List
	 * */
	public ActionForward getlist(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		Pagination pagination=new Pagination();
	    DynaActionForm dy=(DynaActionForm)form;
	    LocatorBO bo=new LocatorBO();
	    String page =(String)request.getParameter("page");//获取页号
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	      Date date = new java.util.Date();
	      Calendar c = Calendar.getInstance();
	      c.add(Calendar.DATE, -7);   //此处可以变成年，月，日，分等
	      String stime = format.format( c.getTime());
	      String etime = format.format(date);
        try{
        	Search_locator locator=(Search_locator)dy.get("ser_locator");
            
             pagination.setCount(200); 
		   if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      }
	        List list= bo.init(locator,pagination);
	        List relist= bo.initPrint(locator);
	        request.setAttribute("listCount", list.size());
	        if( list.size()==0){
	        	   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
	        }else{
	        	  request.setAttribute("loactor",list);
	        	  request.getSession().setAttribute("localizerPrint", relist);
//	        	  request.getSession().setAttribute("loactorViewPrint", list);
	        }
	        if(locator.getStime()==null){
	        	locator.setStime(stime);
			      
			}else{
				locator.setStime(locator.getStime());
				request.setAttribute("name1", "true");
			}if(locator.getEtime()==null){
				locator.setEtime(etime);
			}else{
				locator.setEtime(locator.getEtime());
				request.setAttribute("name3", "true");
			}
			
			if(locator.getMinstime()==null){
				locator.setMinstime("15:47:51");
			}else{
				locator.setMinstime(locator.getMinstime());
				request.setAttribute("name2", "true");
			}
			
			if(locator.getMaxstime()==null){
				locator.setMaxstime("15:47:51");
			}else{
				locator.setMaxstime(locator.getMaxstime());
				request.setAttribute("name4", "true");
			}
		    request.setAttribute("pagination", pagination);
		    dy.set("ser_locator",locator);
		 }catch(Exception e){
			 log.error("==编辑定位器==",e);
		 }
			return mapping.findForward("localizer");
	   }

//	导出Excel
	public ActionForward doOpenExcel(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response
			){
		
			try{
				//创建导入格式表文件
				OutputStream os=response.getOutputStream();
				// 清空输出流
				response.reset();
				// 设置响应头和下载保存的文件名
//				response.setHeader("content-disposition","attachment;filename=bianyiyuangongxinxi.xls");
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("编译定位器信息","UTF-8")+".xls");
				// 定义输出类型
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("编译定位器信息",10);
				//	设置部分列的宽度
				ws.setColumnView(0, 10);
				ws.setColumnView(1, 10);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 10);
				ws.setColumnView(4, 10);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 25);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 10);
				//	设置表头
				ws.addCell(new Label(0,0,"定位器号"));
				ws.addCell(new Label(1,0,"定位器名"));
				ws.addCell(new Label(2,0,"简称"));
				ws.addCell(new Label(3,0,"地图X坐标"));
				ws.addCell(new Label(4,0,"地图Y坐标"));
				ws.addCell(new Label(5,0,"地图Z坐标"));
				ws.addCell(new Label(6,0,"注册时间"));
				ws.addCell(new Label(7,0,"地图号"));
				ws.addCell(new Label(8,0,"设备性质"));
				ws.addCell(new Label(9,0,"使用状态"));
				
				List list = (ArrayList)request.getSession().getAttribute("localizerPrint");
				//	填充数据
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						Locator  v= (Locator)list.get(i);
						ws.addCell(new Label(0,i+1,v.getLocatorid()));
						ws.addCell(new Label(1,i+1,v.getLname()));
						ws.addCell(new Label(2,i+1,v.getShortname()));
						ws.addCell(new Label(3,i+1,v.getX()));
						ws.addCell(new Label(4,i+1,v.getY()));
						ws.addCell(new Label(5,i+1,v.getZ()));
						ws.addCell(new Label(6,i+1,v.getRegdate()));
						ws.addCell(new Label(7,i+1,v.getMapid()));
						ws.addCell(new Label(8,i+1,v.getGround()));
						ws.addCell(new Label(9,i+1,v.getState()));
					}
				}
				wwb.write();
				wwb.close();
				os.close();
				
			}
			catch(RowsExceededException e){
				log.error("==编辑定位器==",e);
			}
			catch(WriteException ex){
				log.error("==编辑定位器==",ex);
			}
			catch(IOException e){
				log.error("==编辑定位器==",e);
			}
		return null;
	}
	        /**
	         * 初始化
	         * */
        public ActionForward initialization(
        		ActionMapping mapping,
        		ActionForm form,
        		HttpServletRequest request,
        		HttpServletResponse response) {
	        	DynaActionForm dy=(DynaActionForm)form;
	        	try{
	        		Search_locator locator=(Search_locator)dy.get("ser_locator");
	        		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				      Date date = new java.util.Date();
				      Calendar c = Calendar.getInstance();
				      c.add(Calendar.DATE, -7);   //此处可以变成年，月，日，分等
				      String stime = format.format( c.getTime());
				      String etime = format.format(date);
				      locator.setStime(stime);
				      locator.setEtime(etime);
					  
				      locator.setMinstime("15:47:51");
				      locator.setMaxstime("15:47:51");
	        		dy.set("ser_locator",locator);
	        		request.setAttribute("listCount", "0");
	        	}catch(Exception e){
	        		log.error("==编辑定位器==",e);
	        	}
	        	return mapping.findForward("localizer");
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
    				 List list=(List)request.getSession().getAttribute("loactorViewPrint");   
    		    	 File reportFile = new File(context.getRealPath("/reports/locator.jasper"));
    	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
    	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
    	 			 HashMap map=new HashMap();
    	 			/* map.put("title", "定位器清单");
    	 			 map.put("locatorid", "定位器号");
    	 			 map.put("lname", "定位器名称");
    	 			 map.put("shortname", "地点");
    	 			 map.put("x", "X坐标");
    	 			 map.put("y", "Y坐标");
    	 			 map.put("mapid", "地图号");
    	 			 map.put("regdate", "注册时间");*/

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
    	   	     File reportFile = new File(context.getRealPath("/reports/locator.jasper"));
    	   	     HashMap map=new HashMap();
    	   	   /*  map.put("title", "定位器清单");
	 			 map.put("locatorid", "定位器号");
	 			 map.put("lname", "定位器名称");
	 			 map.put("shortname", "地点");
	 			 map.put("x", "X坐标");
	 			 map.put("y", "Y坐标");
	 			 map.put("mapid", "地图号");
	 			 map.put("regdate", "注册时间");*/
    		   
    			JasperPrint jasperPrint = null;
    		    try
    			{
    				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
    				List list=(List)request.getSession().getAttribute("loactorViewPrint");
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
    				out.println("<span class=\"bold\">locator.Jasper文件不存在或解析错误！</span>");
    				out.println("</body>");
    				out.println("</html>");
    			}			
    		 return null;
    	  }
    	
}

