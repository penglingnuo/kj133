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
import java.util.Calendar;
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

import com.kj133.entity.Search_Locator_Batteries;
import com.kj133.entity.bo.Locator_batteriesBO;
import com.kj133.entity.vo.Locator_batteriesVO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-09-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Locator_batteriesAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  定位器电池报警
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        Locator_batteriesBO bo=new Locator_batteriesBO();
        DynaActionForm dy=(DynaActionForm)form;
        Pagination pagination = new Pagination();//分页
		String page =(String)request.getParameter("page");//获取页号
		try{
			Search_Locator_Batteries locabatteries=(Search_Locator_Batteries)dy.get("ser_locator_batteries");
			request.getSession().setAttribute("ktime", locabatteries.getStime());
			request.getSession().setAttribute("jtime", locabatteries.getEtime());
	    	pagination.setCount(20);//分页
	    	if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      }//分页
	         List list=bo.init(locabatteries,pagination);
	         List relist=bo.initPrint(locabatteries);
	         request.setAttribute("listCount", list.size());
	         if( list.size()==0 ){
	        	   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
	         }else{
	        	  request.setAttribute("Locator_batteries_List",list);
	        	  request.getSession().setAttribute("locabatteries", locabatteries);
	        	  request.getSession().setAttribute("Locator_batteries_ListPrint", relist);
	         }
        	 if(locabatteries.getMinstime()==null){
        		 locabatteries.setMinstime("15:47:51");

        	 }else{
        		 locabatteries.setMinstime(locabatteries.getMinstime());
        		 request.setAttribute("name1", "true");

        	 }
        	 if(locabatteries.getMaxstime()==null){
        		 locabatteries.setMaxstime("15:47:51");
        	 }else{
        		 locabatteries.setMaxstime(locabatteries.getMaxstime());
        		 request.setAttribute("name2", "true");
        	 }
		     request.setAttribute("pagination", pagination);//分页
		     dy.set("ser_locator_batteries",locabatteries);
		}catch(Exception e){
			log.error("==定位器电池报警==",e);
		}
			return mapping.findForward("show");
	}
	
	/**
	 * 定位器電池報警導出Excel
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward export(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        Locator_batteriesBO bo=new Locator_batteriesBO();
	        DynaActionForm dy=(DynaActionForm)form;
			try{
				Search_Locator_Batteries locabatteries=(Search_Locator_Batteries)dy.get("ser_locator_batteries");
		        List list=bo.initPrint(locabatteries);
		        
		      //创建导入格式表文件
				OutputStream os=response.getOutputStream();
				// 清空输出流
				response.reset();
				// 设置响应头和下载保存的文件名
//				response.setHeader("content-disposition","attachment;filename=bianyiyuangongxinxi.xls");
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("定位器电池报警","UTF-8")+".xls");
				// 定义输出类型
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("定位器电池报警",8);
				//	设置部分列的宽度
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 30);
				ws.setColumnView(4, 15);
				ws.setColumnView(5, 25);
				ws.setColumnView(6, 10);
				ws.setColumnView(7, 20);
				ws.setColumnView(8, 25);
				//	设置表头
				ws.addCell(new Label(0,0,"定位器号"));
				ws.addCell(new Label(1,0,"定位器名"));
				ws.addCell(new Label(2,0,"报警信息"));
				ws.addCell(new Label(3,0,"开始报警时间"));
				ws.addCell(new Label(4,0,"最后报警时间"));

				//	填充数据
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						Locator_batteriesVO v=(Locator_batteriesVO) list.get(i);
						ws.addCell(new Label(0,i+1,v.getLocatorid()));
						ws.addCell(new Label(1,i+1,v.getShortname()));
						ws.addCell(new Label(2,i+1,v.getInfo()));
						ws.addCell(new Label(3,i+1,v.getStime()));
						ws.addCell(new Label(4,i+1,v.getEtime()));
					}
				}
				wwb.write();
				wwb.close();
				os.close();
			}catch(Exception e){
				log.error("==定位器电池报警==",e);
			}
			return null;
		}
	
	/**
	 * 初始化时间
	 * */
	public ActionForward iniaialization(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm)form;
			Global go=new Global();
			try{
				 Search_Locator_Batteries locabatteries=(Search_Locator_Batteries)dy.get("ser_locator_batteries");
				 Calendar cal=Calendar.getInstance();//获取当前时间
				 SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				 String etime=format.format(cal.getTime());
			 
			     String stime=go.getDay(etime,-7);  
			       
				 locabatteries.setStime(stime);
				 locabatteries.setEtime(etime);
				 locabatteries.setMinstime("15:47:51");
				 locabatteries.setMaxstime("15:47:51");
				 dy.set("ser_locator_batteries",locabatteries);
				 
				 request.setAttribute("listCount", "0");
			 }catch(Exception e){
				 log.error("==定位器电池报警==",e);
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
				 List list=(List)request.getSession().getAttribute("Locator_batteries_ListPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/locator_batteries.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 map.put("ktime", request.getSession().getAttribute("ktime"));
		   	     map.put("jtime", request.getSession().getAttribute("jtime"));
		   	     /*map.put("locatorid", "定位器号");
		   	     map.put("shortname", "定位器名");
		   	     map.put("stime", "开始报警时间");
		     	 map.put("etime", "最后报警时间");
		   	     map.put("info", "状态信息");*/
		     	 
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
	   	     File reportFile = new File(context.getRealPath("/reports/locator_batteries.jasper"));
	   	     HashMap map=new HashMap();
	   	     map.put("ktime", request.getSession().getAttribute("ktime"));
	   	     map.put("jtime", request.getSession().getAttribute("jtime"));
	   	     /*map.put("locatorid", "定位器号");
	   	     map.put("shortname", "定位器名");
	   	     map.put("stime", "开始报警时间");
	     	 map.put("etime", "最后报警时间");
	   	     map.put("info", "状态信息");*/
	   
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("Locator_batteries_ListPrint");
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
				out.println("<span class=\"bold\">locator_batteries.jasper文件不存在或解析错误！</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
	
}

