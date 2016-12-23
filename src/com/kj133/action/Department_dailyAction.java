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
import com.kj133.entity.Search_Department_daily;
import com.kj133.entity.bo.Department_dailyBO;
import com.kj133.entity.bo.WordlibBO;

/** 
 * MyEclipse Struts
 * Creation date: 03-13-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Department_dailyAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables
	private final Logger  log=Logger.getLogger(this.getClass());
	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request ���ſ����ձ�
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    WordlibBO lib=new WordlibBO();
	    Department_dailyBO bo=new Department_dailyBO();
	    DynaActionForm dy=(DynaActionForm)form;
	    try{
		      Search_Department_daily dep=(Search_Department_daily)dy.get("department");
		      
		      Ouser user=(Ouser)request.getSession().getAttribute("user");
		      List relist=bo.getList(dep,user.getUserid());
		      request.setAttribute("listCount", relist.size());
		      if( relist.size()== 0){
       		   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
       	 }else{
       		request.setAttribute("depart",relist);
		      request.getSession().setAttribute("department", dep);
		      request.getSession().setAttribute("department_dailyPrint", relist);
       	 }
		      
		      List gro=lib.gro(user.getUserid()); 
		      request.setAttribute("gro_list",gro);
		      
		      dy.set("department",dep);
	    }catch(Exception e){
	    	log.error("==���ſ����ձ�==",e);
	   }
	         return mapping.findForward("department");
	}
	
	/**
	 * ��ʼ��ʱ��Ͱ���
	 * */
	public ActionForward initialization(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    WordlibBO lib=new WordlibBO();
		    DynaActionForm dy=(DynaActionForm)form;
		    try{
				   Calendar call=Calendar.getInstance();
				   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
				   String time=format.format(call.getTime());
				   Search_Department_daily dep=(Search_Department_daily)dy.get("department");
				   dep.setStime(time);
				   Ouser user=(Ouser)request.getSession().getAttribute("user");
				   String  userid=user.getUserid();
				   List gro=lib.gro(userid); 
				   request.setAttribute("gro_list",gro);
				   request.setAttribute("listCount", "0");
				   dy.set("department",dep);
		    }catch(Exception e){
		    	log.error("==���ſ����ձ�==",e);
		    } 
			   return mapping.findForward("department");
	}
	
	 /**
	 * ��ӡԤ��
	 * */
	@SuppressWarnings("unchecked")
	public ActionForward viewPrint(
			ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		    ServletContext context =this.getServlet().getServletConfig().getServletContext();
		    response.setContentType("text/html"); 
			try{
				 List list=(List)request.getSession().getAttribute("depart_dailyPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/department_daily.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			// map.put("title"," ���ſ����ձ�");
				 map.put("riqi", request.getSession().getAttribute("riqi"));
				/* map.put("count", "���");
				 map.put("gro", "����");
				 map.put("allperson", "������");
				 map.put("zaocount", "���");
				 map.put("zhongcount", "�а�");
				 map.put("wancount", "���");
				 map.put("allcount", "�ܼ�");
				 map.put("xiucount", "�ݰ�");
				 map.put("addcount", "���мӰ�");*/
	 			  

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
	    		  
	    		 exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);//�Ե���ģ����в���
	    	     exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
	    	     //exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "imagesĿ¼��·��?image=");
                 //������htmlԤ����ʱ�������һ��px�ļ��������images_uri��·�������Ǹ�px��·�������鲻�ã��������� �����Ϳ����ˡ������� 
	    	     //��д�Ļ���ҳ���Ͼͻ���ʾ�ܶ�ͼƬ������������ͼƬ���ز��ɹ���״̬
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
	 *��ӡ
	 * */
	@SuppressWarnings("unchecked")
	public ActionForward print(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
	     	ServletContext context = this.getServlet().getServletConfig().getServletContext();
	   	     File reportFile = new File(context.getRealPath("/reports/department_daily.jasper"));
	   	     HashMap map=new HashMap();
	   	    // map.put("title"," ���ſ����ձ�");
			 map.put("riqi", request.getSession().getAttribute("riqi"));
			/* map.put("count", "���");
			 map.put("gro", "����");
			 map.put("allperson", "������");
			 map.put("zaocount", "���");
			 map.put("zhongcount", "�а�");
			 map.put("wancount", "���");
			 map.put("allcount", "�ܼ�");
			 map.put("xiucount", "�ݰ�");
			 map.put("addcount", "���мӰ�");*/
			  
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("depart_dailyPrint");
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
				out.println("<span class=\"bold\">department_daily.Jasper�ļ������ڻ��������</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
}

