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
import com.kj133.entity.Search_car_check_menology;
import com.kj133.entity.bo.Car_check_menologyBO;
import com.kj133.entity.bo.WordlibBO;



/** 
 * MyEclipse Struts
 * Creation date: 03-15-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Car_check_menologyAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request 车辆考勤月览表
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    WordlibBO lib=new WordlibBO();
	    Car_check_menologyBO bo=new Car_check_menologyBO();
        DynaActionForm dy=(DynaActionForm)form;
        try{
        	Ouser user=(Ouser)request.getSession().getAttribute("user");
        	Search_car_check_menology car_check_menology=(Search_car_check_menology)dy.get("car_check_menology");
        	request.getSession().setAttribute("riqi", car_check_menology.getStime());
        	List list=bo.getList(car_check_menology,user.getUserid());
        	request.setAttribute("listCount", list.size());
        	String userid = user.getUserid();
        	List gro=lib.gro(userid); 
        	List type = lib.carType();
        	 if( list.size()== 0){
      		   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
      	 }else{
      		request.setAttribute("relist",list);
      	 }
        	request.setAttribute("workType_list", type);
			request.setAttribute("gro_list",gro);
			
			request.getSession().setAttribute("car_check_menology", car_check_menology);
			request.getSession().setAttribute("car_check_menologyPrint", list);
        	dy.set("car_check_menology",car_check_menology);
        }catch(Exception e){
        	log.error("==车辆考勤月览表==",e);
        }
		return mapping.findForward("show");
	}
	/**
	 * 初始化时间和班组
	 * */
	public ActionForward getWordlib(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        WordlibBO lib=new WordlibBO();
	        DynaActionForm dy=(DynaActionForm)form;
	        try{
	        	Search_car_check_menology car_check_menology=(Search_car_check_menology)dy.get("car_check_menology");
	        	Calendar cal=Calendar.getInstance();//获取当前时间
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM");
			    String etime=format.format(cal.getTime());
			    car_check_menology.setStime(etime);
	        	
	        	List type = lib.carType();
	        	request.setAttribute("workType_list", type);
	        	dy.set("car_check_menology",car_check_menology);
	        	request.setAttribute("listCount", "0");
	        }catch(Exception e){
	        	log.error("==车辆考勤月览表==",e);
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
				 List list=(List)request.getSession().getAttribute("employee_menologyPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/employee_menology.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 //map.put("title","月份考勤表");
	 			 //map.put("danwei", " ");
				 map.put("riqi", "月份："+request.getSession().getAttribute("riqi"));
				 /*map.put("count", "序号");
				 map.put("staffername", "姓名");
				 map.put("worktype", "工种");
				 map.put("sum1", "早班");
				 map.put("sum2", "中班");
				 map.put("sum3", "晚班");
				 map.put("sum4", "总出勤");
				 map.put("sum5", "加班"); */
				 
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
	   	     File reportFile = new File(context.getRealPath("/reports/employee_menology.jasper"));
	   	     HashMap map=new HashMap();
	   	   //  map.put("title","月份考勤表");
			// map.put("danwei", " ");
			 map.put("riqi", "月份："+request.getSession().getAttribute("riqi"));
			/* map.put("count", "序号");
			 map.put("staffername", "姓名");
			 map.put("worktype", "工种");
			 map.put("sum1", "早班");
			 map.put("sum2", "中班");
			 map.put("sum3", "晚班");
			 map.put("sum4", "总出勤");
			 map.put("sum5", "加班");*/
			  
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("employee_menologyPrint");
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
				out.println("<span class=\"bold\">employee_menology.Jasper文件不存在或解析错误！</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
	

}

