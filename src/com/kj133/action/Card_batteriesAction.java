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
import com.kj133.entity.Search_Card_Batteries;

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

import com.kj133.entity.bo.Card_batteriesBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-09-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Card_batteriesAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request����ر���
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		Card_batteriesBO bo=new Card_batteriesBO();
	    WordlibBO lib=new WordlibBO();
	    DynaActionForm dy=(DynaActionForm)form;
		Pagination pagination = new Pagination(); 
	    String page =(String)request.getParameter("page"); 
		try{
			Search_Card_Batteries cbatteries=(Search_Card_Batteries)dy.get("ser_card_batteries");
			request.getSession().setAttribute("ktime", cbatteries.getStime());
			request.getSession().setAttribute("jtime", cbatteries.getEtime());
	    	pagination.setCount(200); 
	    	if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      } 
	         List list=bo.init(cbatteries,pagination);
	         List relist=bo.initPrint(cbatteries);
	         request.setAttribute("listCount", list.size());
	         if( list.size() ==0){
	        	   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
	         }else{
		          request.setAttribute("Card_batteries_List",list); 
		          request.getSession().setAttribute("cbatteries", cbatteries);
		          request.getSession().setAttribute("Card_batteries_ListPrint", relist);
	         }
	         Ouser user=(Ouser)request.getSession().getAttribute("user");
			 String  userid=user.getUserid();
	         List gro=lib.gro(userid); 
	         if(cbatteries.getMinstime()==null){
	        	 cbatteries.setMinstime("15:47:51");

        	 }else{
        		 cbatteries.setMinstime(cbatteries.getMinstime());
        		 request.setAttribute("name1", "true");

        	 }
        	 if(cbatteries.getMaxstime()==null){
        		 cbatteries.setMaxstime("15:47:51");
        	 }else{
        		 cbatteries.setMaxstime(cbatteries.getMaxstime());
        		 request.setAttribute("name2", "true");

        	 }
			 request.setAttribute("gro_list",gro);
		     request.setAttribute("pagination", pagination);//��ҳ
		     dy.set("ser_card_batteries",cbatteries);
		}catch(Exception e){
			log.error("==����ر�����ѯ==",e);
		}
			return mapping.findForward("show");
	}

		/**
		  * ��ʼ��ʱ��Ͱ���
		  * */
		public ActionForward getWorkType(
				ActionMapping mapping,
				ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response) {
			    WordlibBO lib=new WordlibBO();
			    Global go=new Global();
			    DynaActionForm dy=(DynaActionForm)form;
				try{
				   Search_Card_Batteries cbatteries=(Search_Card_Batteries)dy.get("ser_card_batteries");
				   Calendar cal=Calendar.getInstance();//��ȡ��ǰʱ��
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				   String etime=format.format(cal.getTime());
			 
			       String stime=go.getDay(etime,-7);   
				   cbatteries.setStime(stime);
				   cbatteries.setEtime(etime);
				   cbatteries.setMinstime("15:47:51");
				   cbatteries.setMaxstime("15:47:51");
				   Ouser user=(Ouser)request.getSession().getAttribute("user");
				   List gro=lib.grolist(user.getUserid()); 
				   request.setAttribute("gro_list",gro);
				   dy.set("ser_card_batteries",cbatteries);
				   
				   request.setAttribute("listCount", "0");
				}catch(Exception e){
					log.error("==����ر�����ѯ==",e);
				}
			    return mapping.findForward("type");
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
					 List list=(List)request.getSession().getAttribute("Card_batteries_ListPrint");   
			    	 File reportFile = new File(context.getRealPath("/reports/card_batteries.jasper"));
		 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
		 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
		 			 HashMap map=new HashMap();
		 			 map.put("ktime", request.getSession().getAttribute("ktime"));//��ʼʱ��
					 map.put("jtime", request.getSession().getAttribute("jtime"));//��ֹʱ��
					/* map.put("cardid", "����");
					 map.put("username", "����");
					 map.put("gro", "����");
					 map.put("mintime", "��ʼ����ʱ��");
					 map.put("maxtime", "��󱨾�ʱ��");
					 map.put("info", "������Ϣ");*/
		 			
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
		   	     File reportFile = new File(context.getRealPath("/reports/card_batteries.jasper"));
		   	     HashMap map=new HashMap();
		   	     map.put("ktime", request.getSession().getAttribute("ktime"));//��ʼʱ��
				 map.put("jtime", request.getSession().getAttribute("jtime"));//��ֹʱ��
				 /*map.put("cardid", "����");
				 map.put("username", "����");
				 map.put("gro", "����");
				 map.put("mintime", "��ʼ����ʱ��");
				 map.put("maxtime", "��󱨾�ʱ��");
				 map.put("info", "������Ϣ");*/
				 
				JasperPrint jasperPrint = null;
			    try
				{
					JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
					List list=(List)request.getSession().getAttribute("Card_batteries_ListPrint");
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
					out.println("<span class=\"bold\">card_batteries.jasper�ļ������ڻ��������</span>");
					out.println("</body>");
					out.println("</html>");
				}			
			 return null;
		  }
		
		
}

