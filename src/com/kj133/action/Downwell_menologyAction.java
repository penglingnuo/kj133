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
import com.kj133.entity.Search_DownWell_menology;
import com.kj133.entity.bo.Downwell_menologyBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 03-14-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Downwell_menologyAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request �����¾��±�
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    DynaActionForm dy=(DynaActionForm)form;
	    WordlibBO lib=new WordlibBO();
	    Downwell_menologyBO bo=new Downwell_menologyBO();
        try{
        	   Ouser user =(Ouser)request.getSession().getAttribute("user");
        	   Search_DownWell_menology well=(Search_DownWell_menology)dy.get("downwell_menology");
        	   
        	   request.getSession().setAttribute("yuefen", well.getStime());
        	   Global go=new Global();
    		   request.getSession().setAttribute("dayCount", go.getDaysOfMonth(well.getStime()));//��ǰ�·ݵ�����
        	   List list=bo.getList(well,user.getUserid());
        	   if( list.size()== 0){
        		   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
        	 }else{
        		 request.setAttribute("down_list",list);
        		 request.getSession().setAttribute("downwell_menologyPrint", list);
        	 }
        	   request.getSession().setAttribute("downwell_menology", well);
        	   //Ouser user=(Ouser)request.getSession().getAttribute("user");
        	   List gro=lib.gro(user.getUserid()); 
			   request.setAttribute("gro_list",gro);
			   
	           dy.set("downwell_menology",well);
         }catch(Exception e){
        	 log.error("==�����뾮�±�==",e);
        }
		 return mapping.findForward("show");
	}
     /**
      * ��ʼ��ʱ��Ͱ���
      * */
	public ActionForward initialization(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        DynaActionForm dy=(DynaActionForm)form;
	        WordlibBO lib=new WordlibBO();
	        try{
	        	   Search_DownWell_menology well=(Search_DownWell_menology)dy.get("downwell_menology");
	        	   Calendar cal=Calendar.getInstance();//��ȡ��ǰʱ��
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM");
				   String etime=format.format(cal.getTime());
	        	   well.setStime(etime);
	        	   Ouser user=(Ouser)request.getSession().getAttribute("user");
		    	   List gro=lib.grolist(user.getUserid()); 
				   request.setAttribute("gro_list",gro);
		           //dy.set("downwell_menology",well);
				   request.setAttribute("listCount", "0");
	        }catch(Exception e){
	        	log.error("==�����뾮�±�==",e);
		    }
			return mapping.findForward("show");
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
				 List list=(List)request.getSession().getAttribute("down_listPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/downwell_menology.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 
				 map.put("yuefen", "�·ݣ�"+request.getSession().getAttribute("yuefen")+" �� ");
				 map.put("dayCount", "������ "+request.getSession().getAttribute("dayCount"));
				 
				 
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
	   	     File reportFile = new File(context.getRealPath("/reports/downwell_menology.jasper"));
	   	     HashMap map=new HashMap();
	   	     map.put("yuefen", "�·ݣ�"+request.getSession().getAttribute("yuefen")+" �� ");
			 map.put("dayCount", "������ ");
			 map.put("dayCount", "������ "+request.getSession().getAttribute("dayCount"));
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("down_listPrint");
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
				out.println("<span class=\"bold\">downwell_menology.Jasper�ļ������ڻ��������</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
	
	
	
}

