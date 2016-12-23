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

import com.kj133.entity.Search_ViewLocatorHistory;
import com.kj133.entity.bo.ViewLocatorHistoryBO;
import com.kj133.entity.vo.ViewLocatorHistoryVO;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class ViewLocatorHistoryAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request��λ����ʷ��ѯ
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		ViewLocatorHistoryBO bo=new ViewLocatorHistoryBO();
		DynaActionForm dy=(DynaActionForm) form;
		Pagination pagination=new Pagination();
		String page =(String)request.getParameter("page");//��ȡҳ��
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	      Date date = new java.util.Date();
	      Calendar c = Calendar.getInstance();
	      c.add(Calendar.DATE, -7);   //�˴����Ա���꣬�£��գ��ֵ�
	      String stime = format.format( c.getTime());
	      String etime = format.format(date);
	      
		try{
			  Search_ViewLocatorHistory locatorhistory=(Search_ViewLocatorHistory)dy.getMap().get("ser_viewrlocatorhistory");
			  pagination.setCount(200);//ÿҳ��ʾ8����¼
		      if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      }
			List list=bo.getList(locatorhistory,pagination);
			List relist=bo.getListPrint(locatorhistory);
			request.setAttribute("listCount", list.size());
			if( list.size()==0){
				   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
			}else{
				   request.setAttribute("viewLocatorHistoryList",list);
				   request.getSession().setAttribute("viewLocatorHistoryPrint", relist);
//				   request.getSession().setAttribute("viewLocatorHistoryListPrint", list);
			}
			request.setAttribute("pagination", pagination);
			
			if(locatorhistory.getStime()==null){
				locatorhistory.setStime(stime);
			      
			}else{
				locatorhistory.setStime(locatorhistory.getStime());
				request.setAttribute("name1", "true");
			}if(locatorhistory.getEtime()==null){
				locatorhistory.setEtime(etime);
			}else{
				locatorhistory.setEtime(locatorhistory.getEtime());
				request.setAttribute("name3", "true");
			}
			
			if(locatorhistory.getMinstime()==null){
				locatorhistory.setMinstime("15:47:51");
			}else{
				locatorhistory.setMinstime(locatorhistory.getMinstime());
				request.setAttribute("name2", "true");
			}
			
			if(locatorhistory.getMaxstime()==null){
				locatorhistory.setMaxstime("15:47:51");
			}else{
				locatorhistory.setMaxstime(locatorhistory.getMaxstime());
				request.setAttribute("name4", "true");
			}
			dy.set("ser_viewrlocatorhistory",locatorhistory);
			}catch(Exception e){
				log.error("==��λ����ʷ��Ϣ��ѯ==",e);
			}
			

		return mapping.findForward("show");
	}
   
//	����Excel
	public ActionForward doOpenExcel(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response
			){
		
			try{
				//���������ʽ���ļ�
				OutputStream os=response.getOutputStream();
				// ��������
				response.reset();
				// ������Ӧͷ�����ر�����ļ���
//				response.setHeader("content-disposition","attachment;filename=bianyiyuangongxinxi.xls");
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("��λ����ʷ��ѯ","UTF-8")+".xls");
				// �����������
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("��λ����ʷ��ѯ",10);
				//	���ò����еĿ��
				ws.setColumnView(0, 25);
				ws.setColumnView(1, 25);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 10);
				ws.setColumnView(4, 10);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 10);
				ws.setColumnView(7, 25);
				ws.setColumnView(8, 10);
				
				//	���ñ�ͷ
				ws.addCell(new Label(0,0,"��Ч����"));
				ws.addCell(new Label(1,0,"��Чֹ��"));
				ws.addCell(new Label(2,0,"��λ����"));
				ws.addCell(new Label(3,0,"���"));
				ws.addCell(new Label(4,0,"��ͼX����"));
				ws.addCell(new Label(5,0,"��ͼY����"));
				ws.addCell(new Label(6,0,"��ͼZ����"));
				ws.addCell(new Label(7,0,"ע��ʱ��"));
				ws.addCell(new Label(8,0,"��ͼ��"));
				
				List list = (ArrayList)request.getSession().getAttribute("viewLocatorHistoryPrint");
				//	�������
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						ViewLocatorHistoryVO  v= (ViewLocatorHistoryVO)list.get(i);
						ws.addCell(new Label(0,i+1,v.getLoca_startdate()));
						ws.addCell(new Label(1,i+1,v.getLoca_modifydate()));
						ws.addCell(new Label(2,i+1,v.getLoca_locatorid()));
						ws.addCell(new Label(3,i+1,v.getLoca_shortname()));
						ws.addCell(new Label(4,i+1,v.getLoca_x()));
						ws.addCell(new Label(5,i+1,v.getLoca_y()));
						ws.addCell(new Label(6,i+1,v.getLoca_z()));
						ws.addCell(new Label(7,i+1,v.getLoca_regdate()));
						ws.addCell(new Label(8,i+1,v.getLoca_mapid()));
					}
				}
				wwb.write();
				wwb.close();
				os.close();
				
			}
			catch(RowsExceededException e){
				log.error("==��λ����ʷ��Ϣ��ѯ==",e);
			}
			catch(WriteException ex){
				log.error("==��λ����ʷ��Ϣ��ѯ==",ex);
			}
			catch(IOException e){
				log.error("==��λ����ʷ��Ϣ��ѯ==",e);
			}
		return null;
	}
	/**
	 * ��ʼ��ʱ��
	 * */
	public ActionForward initialization(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm)form;
			  try{
				  Search_ViewLocatorHistory locatorhistory=(Search_ViewLocatorHistory)dy.get("ser_viewrlocatorhistory");
				  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			      Date date = new java.util.Date();
			      Calendar c = Calendar.getInstance();
			      c.add(Calendar.DATE, -7);   //�˴����Ա���꣬�£��գ��ֵ�
			      String stime = format.format( c.getTime());
			      String etime = format.format(date);
			      locatorhistory.setStime(stime);
			      locatorhistory.setEtime(etime);
				  
				  locatorhistory.setMinstime("15:47:51");
				  locatorhistory.setMaxstime("15:47:51");
				  dy.set("ser_viewrlocatorhistory",locatorhistory);
				  request.setAttribute("listCount", "0");
			  }catch(Exception e){
				  log.error("==��λ����ʷ��Ϣ��ѯ==",e);
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
				 List list=(List)request.getSession().getAttribute("viewLocatorHistoryListPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/ViewLocatorHistory.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			/* map.put("title", "��λ����ʷ��ѯ");
	 			 map.put("loca_startdate", "��Ч����");
	 			 map.put("loca_modifydate", "��Чֹ��");
	 			 map.put("loca_locatorid", "��λ����");
	 			 map.put("loca_shortname", "���");
	 			 map.put("loca_x", "x����");
	 			 map.put("loca_y", "y����");
	 			 map.put("loca_mapid", "��ͼ��");
	 			 */
	 			 

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
	   	     File reportFile = new File(context.getRealPath("/reports/ViewLocatorHistory.jasper"));
	   	     HashMap map=new HashMap();
	   	   /*  map.put("title", "��λ����ʷ��ѯ");
	   	     map.put("loca_startdate", "��Ч����");
			 map.put("loca_modifydate", "��Чֹ��");
			 map.put("loca_locatorid", "��λ����");
			 map.put("loca_shortname", "���");
			 map.put("loca_x", "x����");
			 map.put("loca_y", "y����");
			 map.put("loca_mapid", "��ͼ��");*/
			 
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("viewLocatorHistoryListPrint");
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
				out.println("<span class=\"bold\">viewLocatorHistory.Jasper�ļ������ڻ��������</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
}

