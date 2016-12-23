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

import com.kj133.entity.CardReader1;
import com.kj133.entity.Search_ViewReader;

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

import com.kj133.entity.bo.EditViewReaderBO;

/** 
 * MyEclipse Struts
 * Creation date: 02-01-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class EditViewReaderAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * �༭��վ
	 * ��ȡ����
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
    	Pagination pagination=new Pagination(); 
    	DynaActionForm dy=(DynaActionForm)form;
    	String page =(String)request.getParameter("page");//��ȡҳ��
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	      Date date = new java.util.Date();
	      Calendar c = Calendar.getInstance();
	      c.add(Calendar.DATE, -7);   //�˴����Ա���꣬�£��գ��ֵ�
	      String stime = format.format( c.getTime());
	      String etime = format.format(date);
        EditViewReaderBO bo=new EditViewReaderBO();
        try{
        	Search_ViewReader viewreader=(Search_ViewReader)dy.getMap().get("ser_viewreader");//ȡ��ҳ�������
        	pagination.setCount(100);// 
		      if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);  
		    }   
        	List list=bo.init(viewreader,pagination);
        	List relist=bo.initPrint(viewreader);
        	request.setAttribute("listCount", list.size());
        	if( list.size()== 0){
        		   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
        	}else{
        		  request.setAttribute("ViewReaderList",list);
        		  request.getSession().setAttribute("editViewReaderPrint", relist);
        	}
        	if(viewreader.getStime()==null){
        		viewreader.setStime(stime);
			      
			}else{
				viewreader.setStime(viewreader.getStime());
				request.setAttribute("name1", "true");
			}if(viewreader.getEtime()==null){
				viewreader.setEtime(etime);
			}else{
				viewreader.setEtime(viewreader.getEtime());
				request.setAttribute("name3", "true");
			}
			if(viewreader.getMinstime()==null){
				viewreader.setMinstime("15:47:51");
			}else{
				viewreader.setMinstime(viewreader.getMinstime());
				request.setAttribute("name2", "true");
			}if(viewreader.getMaxstime()==null){
				viewreader.setMaxstime("15:47:51");
			}else{
				viewreader.setMaxstime(viewreader.getMaxstime());
				request.setAttribute("name4", "true");
			}
        	request.setAttribute("pagination", pagination);
        	dy.set("ser_viewreader",viewreader);//�������û�ҳ��	 
        }catch(Exception e){
        	log.error("==�༭��վ��Ϣ--��ѯ==",e);
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
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("�����վ��Ϣ","UTF-8")+".xls");
				// �����������
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("�����վ��Ϣ",10);
				//	���ò����еĿ��
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
				ws.setColumnView(10, 10);
				ws.setColumnView(11, 10);
				ws.setColumnView(12, 10);
				ws.setColumnView(13, 10);
				ws.setColumnView(14, 10);
				ws.setColumnView(15, 10);
				ws.setColumnView(16, 10);
				//	���ñ�ͷ
				ws.addCell(new Label(0,0,"��վ��"));
				ws.addCell(new Label(1,0,"��վ��"));
				ws.addCell(new Label(2,0,"���"));
				ws.addCell(new Label(3,0,"��ͼX����"));
				ws.addCell(new Label(4,0,"��ͼY����"));
				ws.addCell(new Label(5,0,"��ͼZ����"));
				ws.addCell(new Label(6,0,"ע��ʱ��"));
				ws.addCell(new Label(7,0,"��ͼ��"));
				ws.addCell(new Label(8,0,"�����ж�"));
				ws.addCell(new Label(9,0,"ָ����λ��"));
				ws.addCell(new Label(10,0,"��λ������"));
				ws.addCell(new Label(11,0,"ָ�������ж�"));
				ws.addCell(new Label(12,0,"ʹ��״̬"));
				ws.addCell(new Label(13,0,"�豸����"));
				ws.addCell(new Label(14,0,"�鿨��վ"));
				ws.addCell(new Label(15,0,"ʹ������"));
				ws.addCell(new Label(16,0,"��������"));
				
				List list = (ArrayList)request.getSession().getAttribute("editViewReaderPrint");
				//	�������
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						CardReader1  v= (CardReader1)list.get(i);
						ws.addCell(new Label(0,i+1,v.getCardreaderid()));
						ws.addCell(new Label(1,i+1,v.getCrname()));
						ws.addCell(new Label(2,i+1,v.getShortname()));
						ws.addCell(new Label(3,i+1,v.getX()));
						ws.addCell(new Label(4,i+1,v.getY()));
						ws.addCell(new Label(5,i+1,v.getZ()));
						ws.addCell(new Label(6,i+1,v.getRegdate()));
						ws.addCell(new Label(7,i+1,v.getMapid()));
						ws.addCell(new Label(8,i+1,v.getIgnoretimes()));
						ws.addCell(new Label(9,i+1,v.getIgnorelocator()));
						ws.addCell(new Label(10,i+1,v.getSname()));
						ws.addCell(new Label(11,i+1,v.getLocatorignoretimes()));
						ws.addCell(new Label(12,i+1,v.getState()));
						ws.addCell(new Label(13,i+1,v.getGround()));
						ws.addCell(new Label(14,i+1,v.getCheckreader()));
						ws.addCell(new Label(15,i+1,v.getUseantenna1()));
						ws.addCell(new Label(16,i+1,v.getIfalarm()));
					}
				}
				wwb.write();
				wwb.close();
				os.close();
				
			}
			catch(RowsExceededException e){
				log.error("==�༭��վ��Ϣ--����Excel==",e);
			}
			catch(WriteException ex){
				log.error("==�༭��վ��Ϣ--����Excel==",ex);
			}
			catch(IOException e){
				log.error("==�༭��վ��Ϣ--����Excel==",e);
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
				Search_ViewReader viewreader=(Search_ViewReader)dy.get("ser_viewreader"); 
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			      Date date = new java.util.Date();
			      Calendar c = Calendar.getInstance();
			      c.add(Calendar.DATE, -7);   //�˴����Ա���꣬�£��գ��ֵ�
			      String stime = format.format( c.getTime());
			      String etime = format.format(date);
			      viewreader.setStime(stime);
			      viewreader.setEtime(etime);
				  
			      viewreader.setMinstime("15:47:51");
			      viewreader.setMaxstime("15:47:51");
				dy.set("ser_viewreader",viewreader);
				request.setAttribute("listCount", "0");
			}catch(Exception e){
				log.error("==�༭��վ��Ϣ==",e);
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
				 List list=(List)request.getSession().getAttribute("viewReaderListViewPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/viewReader.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			/* map.put("title", "��վ�嵥");
	 			 map.put("cardreaderid", "��վ��");
	 			 map.put("crname", "��վ����");
	 			 map.put("shortname", "�ص�");
	 			 map.put("x", "X����");
	 			 map.put("y", "Y����");
	 			 map.put("mapid", "��ͼ��");
	 			 map.put("regdate", "ע��ʱ��");*/

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
	   	     File reportFile = new File(context.getRealPath("/reports/viewReader.jasper"));
	   	     HashMap map=new HashMap();
	   	     /*map.put("title", "��վ�嵥");
			 map.put("cardreaderid", "��վ��");
			 map.put("crname", "��վ����");
			 map.put("shortname", "�ص�");
			 map.put("x", "X����");
			 map.put("y", "Y����");
			 map.put("mapid", "��ͼ��");
			 map.put("regdate", "ע��ʱ��");*/
		   
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("viewReaderListViewPrint");
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
				out.println("<span class=\"bold\">viewReader.Jasper�ļ������ڻ��������</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
	
}

