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

import com.kj133.entity.Ouser;
import com.kj133.entity.Search_Call_Report;

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

import com.kj133.entity.bo.Call_reportBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-09-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Call_reportAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  ������ʷ��ѯ
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		Call_reportBO bo=new Call_reportBO();
		WordlibBO lib=new WordlibBO();
		DynaActionForm dy=(DynaActionForm)form;
		Pagination pagination = new Pagination();//��ҳ
	    String page =(String)request.getParameter("page");//��ȡҳ��F
		try{
			Search_Call_Report call=(Search_Call_Report)dy.get("ser_call_report");
			request.getSession().setAttribute("ktime", call.getStime());
			request.getSession().setAttribute("jtime", call.getEtime());
	    	pagination.setCount(20);//��ҳ
	    	if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      }//��ҳ
	         List list=bo.init(call,pagination);
	         List relist=bo.initPrint(call);
	         request.setAttribute("listCount", list.size());
	         if( list.size()==0){
	        	   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
	         }else{
		          request.setAttribute("Call_report_List",list);
		          request.getSession().setAttribute("Call_Report", call);
		          request.getSession().setAttribute("Call_report_ListPrint", relist);
	         }
	         Ouser user=(Ouser)request.getSession().getAttribute("user");
			 String  userid=user.getUserid();
	         List gro=lib.gro(userid); 
        	 if(call.getMinstime()==null){
        		 call.setMinstime("15:47:51");

        	 }else{
        		 call.setMinstime(call.getMinstime());

        	 }
        	 if(call.getMaxstime()==null){
        		 call.setMaxstime("15:47:51");
        	 }else{
        		 call.setMaxstime(call.getMaxstime());

        	 }
			 request.setAttribute("gro_list",gro);
		     request.setAttribute("pagination", pagination);//��ҳ
		     dy.set("ser_call_report",call);
		}catch(Exception e){
			log.error("==������ʷ��ѯ==",e);
		}
		return mapping.findForward("show");
	}
	
	  /**
	  * ��ʼ�������ʱ��
	  * */
	public ActionForward getGroup(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    WordlibBO lib=new WordlibBO();
			DynaActionForm dy=(DynaActionForm)form;
			Global go=new Global();
			try{
			   Search_Call_Report call=(Search_Call_Report)dy.get("ser_call_report");	
			   Calendar cal=Calendar.getInstance();//��ȡ��ǰʱ��
			   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
			   String etime=format.format(cal.getTime());
		 
		       String stime=go.getDay(etime,-7);
			   call.setStime(stime);
			   call.setEtime(etime);
			   call.setMinstime("15:47:51");
			   call.setMaxstime("15:47:51");
			   Ouser user=(Ouser)request.getSession().getAttribute("user");
			   List gro=lib.grolist(user.getUserid()); 
			   request.setAttribute("gro_list",gro);
			   request.setAttribute("listCount", "0");
			   dy.set("ser_call_report",call);
			}catch(Exception e){
				log.error("==������ʷ��ѯ==",e);
			}
		    return mapping.findForward("gro");
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
				 List list=(List)request.getSession().getAttribute("Call_report_ListPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/call_report.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 map.put("ktime", request.getSession().getAttribute("ktime"));//��ʼʱ��
				 map.put("jtime", request.getSession().getAttribute("jtime"));//��ֹʱ��
	 			
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
	   	     File reportFile = new File(context.getRealPath("/reports/call_report.jasper"));
	   	     HashMap map=new HashMap();
	   	     map.put("ktime", request.getSession().getAttribute("ktime"));//��ʼʱ��
			 map.put("jtime", request.getSession().getAttribute("jtime"));//��ֹʱ��
			/* map.put("stafferid", "����");
			 map.put("username", "����");
			 map.put("gro", "����");
			 map.put("stime", "��ʼ����ʱ��");
			 map.put("etime", "������ʱ��");
			 map.put("info", "״̬��Ϣ");*/
			 
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("Call_report_ListPrint");
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
				out.println("<span class=\"bold\">call_report.jasper�ļ������ڻ��������</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
	
}
 
