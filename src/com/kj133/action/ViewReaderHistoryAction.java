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

import com.kj133.entity.Search_ViewReaderHistory;

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

import com.kj133.entity.bo.ViewReaderHistoryBO;
import com.kj133.entity.vo.ViewReaderHistoryVO;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class ViewReaderHistoryAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request ��վ��ʷ��ѯ
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        ViewReaderHistoryBO bo=new ViewReaderHistoryBO();
        DynaActionForm dy=(DynaActionForm)form;
        Pagination pagination=new Pagination();//F
		String page =(String)request.getParameter("page");//��ȡҳ��
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	      Date date = new java.util.Date();
	      Calendar c = Calendar.getInstance();
	      c.add(Calendar.DATE, -7);   //�˴����Ա���꣬�£��գ��ֵ�
	      String stime = format.format( c.getTime());
	      String etime = format.format(date);
		try{
			  Search_ViewReaderHistory history=(Search_ViewReaderHistory)dy.get("ser_viewreaderhistory");//ȡ��formbean,Ҳ���ǲ�ѯ���ֶ�
			  pagination.setCount(200);//ÿҳ��ʾ8����¼
		      if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      }
			List list=bo.init(history,pagination);
			List relist=bo.initPrint(history);
			request.setAttribute("listCount", list.size());
			if( list.size() == 0){
				   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
			}else{
				   request.setAttribute("viewReaderHistoryList",list);
				   request.getSession().setAttribute("viewReaderHistoryPrint", relist);
			}
			if(history.getHistory_stime()==null){
				history.setHistory_stime(stime);
			      
			}else{
				history.setHistory_stime(history.getHistory_stime());
				request.setAttribute("name1", "true");
			}if(history.getHistory_etime()==null){
				history.setHistory_etime(etime);
			}else{
				history.setHistory_etime(history.getHistory_etime());
				request.setAttribute("name3", "true");
			}
			
			if(history.getMinstime()==null){
				history.setMinstime("15:47:51");
			}else{
				history.setMinstime(history.getMinstime());
				request.setAttribute("name2", "true");
			}
			
			if(history.getMaxstime()==null){
				history.setMaxstime("15:47:51");
			}else{
				history.setMaxstime(history.getMaxstime());
				request.setAttribute("name4", "true");
			}
			request.setAttribute("pagination", pagination);//F
			dy.set("ser_viewreaderhistory",history);//���°�ֵ���û�ҳ�� 
		}catch(Exception e){
			log.error("==��վ��ʷ��ѯ==",e);
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
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("��վ��ʷ��ѯ","UTF-8")+".xls");
				// �����������
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("��վ��ʷ��ѯ",10);
				//	���ò����еĿ���
				ws.setColumnView(0, 25);
				ws.setColumnView(1, 25);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 10);
				ws.setColumnView(4, 25);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 10);
				//	���ñ�ͷ
				ws.addCell(new Label(0,0,"��Ч����"));
				ws.addCell(new Label(1,0,"��Чֹ��"));
				ws.addCell(new Label(2,0,"��վ��"));
				ws.addCell(new Label(3,0,"���"));
				ws.addCell(new Label(4,0,"ע��ʱ��"));
				ws.addCell(new Label(5,0,"��ͼ��"));
				ws.addCell(new Label(6,0,"ʹ��״̬"));
				
				List list = (ArrayList)request.getSession().getAttribute("viewReaderHistoryPrint");
				//	�������
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						ViewReaderHistoryVO  v= (ViewReaderHistoryVO)list.get(i);
						ws.addCell(new Label(0,i+1,v.getC_strat()));
						ws.addCell(new Label(1,i+1,v.getC_modifydate()));
						ws.addCell(new Label(2,i+1,v.getC_cid()));
						ws.addCell(new Label(3,i+1,v.getC_sname()));
						ws.addCell(new Label(4,i+1,v.getC_regdate()));
						ws.addCell(new Label(5,i+1,v.getC_mapid()));
						ws.addCell(new Label(6,i+1,v.getC_state()));
					}
				}
				wwb.write();
				wwb.close();
				os.close();
				
			}
			catch(RowsExceededException e){
				log.error("==��վ��ʷ��ѯ==",e);
			}
			catch(WriteException ex){
				log.error("==��վ��ʷ��ѯ==",ex);
			}
			catch(IOException e){
				log.error("==��վ��ʷ��ѯ==",e);
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
				  Search_ViewReaderHistory history=(Search_ViewReaderHistory)dy.get("ser_viewreaderhistory");
				  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			      Date date = new java.util.Date();
			      Calendar c = Calendar.getInstance();
			      c.add(Calendar.DATE, -7);   //�˴����Ա���꣬�£��գ��ֵ�
			      String stime = format.format( c.getTime());
			      String etime = format.format(date);
			      history.setHistory_stime(stime);
			      history.setHistory_etime(etime);
				  
			      history.setMinstime("15:47:51");
			      history.setMaxstime("15:47:51");
				  dy.set("ser_viewreaderhistory",history);
				  request.setAttribute("listCount", "0");
		    }catch(Exception e){
		    	log.error("==��վ��ʷ��ѯ==",e);
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
				 List list=(List)request.getSession().getAttribute("viewReaderHistoryListPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/viewReaderHistory.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			/* map.put("title", "��վ��ʷ��ѯ");
	 			 map.put("c_strat", "��Ч����");
	 			 map.put("c_modifydate", "��Чֹ��");
	 			 map.put("c_cid", "��վ��");
	 			 map.put("c_sname", "���");
	 			 map.put("c_mapid", "��ͼ��");
	 			 map.put("c_state", "״̬");*/
	 			 

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
	   	     File reportFile = new File(context.getRealPath("/reports/viewReaderHistory.jasper"));
	   	     HashMap map=new HashMap();
	   	    /* map.put("title", "��վ��ʷ��ѯ");
			 map.put("c_strat", "��Ч����");
			 map.put("c_modifydate", "��Чֹ��");
			 map.put("c_cid", "��վ��");
			 map.put("c_sname", "���");
			 map.put("c_mapid", "��ͼ��");
			 map.put("c_state", "״̬");*/
		   
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("viewReaderHistoryListPrint");
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
				out.println("<span class=\"bold\">viewReaderHistory.Jasper�ļ������ڻ��������</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
	
}


 