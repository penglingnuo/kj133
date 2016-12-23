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

import com.kj133.entity.Search_StandHelp;
import com.kj133.entity.bo.StandHelpBO;
import com.kj133.entity.vo.StandHelpVO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 03-12-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class StandHelpAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  ��վ������ѯ
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		Pagination pagin=new Pagination();
		StandHelpBO bo=new StandHelpBO();
		String page=request.getParameter("page");
		try{
			Search_StandHelp standhelp=(Search_StandHelp)dy.get("standHelp");
		    pagin.setCount(200);
		    if( page != null && page.length()>0){
		    	pagin.setPage(Integer.parseInt(page));
		    }else{
		    	pagin.setPage(1);
		    }
			List stand=bo.getList(standhelp,pagin);
//			List relist=bo.getListPrint(standhelp);
			request.setAttribute("listCount", stand.size());
			request.getSession().setAttribute("Search_StandHelp", standhelp);
			if( stand.size()==0){
				   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
			}else{
			       request.setAttribute("StandList",stand);
//			       request.getSession().setAttribute("StandListPrint", relist);
			}
       	 if(standhelp.getMinstime()==null){
       		standhelp.setMinstime("00:00:00");

    	 }else{
    		 standhelp.setMinstime(standhelp.getMinstime());
    		 request.setAttribute("name1", "true");
    	 }
    	 if(standhelp.getMaxstime()==null){
    		 standhelp.setMaxstime("00:00:00");
    	 }else{
    		 standhelp.setMaxstime(standhelp.getMaxstime());
    		 request.setAttribute("name2", "true");
    	 }
		    request.setAttribute("pagination",pagin);//��ҳ
		}catch(Exception e){
			log.error("==��վ������ѯ==",e);
		}
		return mapping.findForward("show");
	}
	
	public ActionForward export(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	{
		try{
			StandHelpBO bo=new StandHelpBO();
			DynaActionForm dy=(DynaActionForm)form;
			Search_StandHelp standhelp=(Search_StandHelp)dy.get("standHelp");
			List list= bo.getListPrint(standhelp);
			
			//���������ʽ���ļ�
			OutputStream os=response.getOutputStream();
			// ��������
			response.reset();
			// ������Ӧͷ�����ر�����ļ���
//			response.setHeader("content-disposition","attachment;filename=bianyiyuangongxinxi.xls");
			response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("��վ����","UTF-8")+".xls");
			// �����������
			response.setContentType("APPLICATION/msexcel");		
			WritableWorkbook wwb=Workbook.createWorkbook(os);
			WritableSheet ws =wwb.createSheet("��վ����",8);
			//	���ò����еĿ��
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 10);
			ws.setColumnView(3, 30);
			ws.setColumnView(4, 15);
			ws.setColumnView(5, 25);
			ws.setColumnView(6, 10);
			ws.setColumnView(7, 20);
			ws.setColumnView(8, 25);
			//	���ñ�ͷ
			ws.addCell(new Label(0,0,"��վ��"));
			ws.addCell(new Label(1,0,"��վ����"));
			ws.addCell(new Label(2,0,"��ʼ����ʱ��"));
			ws.addCell(new Label(3,0,"��󱨾�ʱ��"));
			ws.addCell(new Label(4,0,"��������"));
			ws.addCell(new Label(5,0,"״̬��Ϣ"));

			//	�������
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					StandHelpVO v=(StandHelpVO) list.get(i);
					ws.addCell(new Label(0,i+1,v.getCardreaderid()));
					ws.addCell(new Label(1,i+1,v.getShortname()));
					ws.addCell(new Label(2,i+1,v.getMintime()));
					ws.addCell(new Label(3,i+1,v.getMaxtime()));
					ws.addCell(new Label(4,i+1,v.getAlarmtimes()));
					ws.addCell(new Label(5,i+1,v.getInfo()));
				}
			}
			wwb.write();
			wwb.close();
			os.close();
			
		}
		catch(Exception e){
			log.error("==��վ����-����Excel==",e);
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
			Global go=new Global();
			DynaActionForm dy=(DynaActionForm)form;
			try{
				Search_StandHelp stand=(Search_StandHelp)dy.get("standHelp");
				Calendar cal=Calendar.getInstance();//��ȡ��ǰʱ��
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				String etime=format.format(cal.getTime());
                String stime=go.getDay(etime,-7);   
			       
				stand.setStime(stime);
		        stand.setEtime(etime);
		        stand.setMinstime("00:00:00");
		        stand.setMaxstime("00:00:00");
		        dy.set("standHelp",stand);
		        request.setAttribute("listCount", "0");
			}catch(Exception e){
				log.error("==��վ������ѯ==",e);
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
				 List list=(List)request.getSession().getAttribute("StandListPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/standHelp.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 /*map.put("cardreaderid", "��վ��");
	 		   	 map.put("shortname", "��վ����");
	 		   	 map.put("info", "״̬��Ϣ");
	 		     map.put("mintime", "��ʼ����ʱ��");
	 		   	 map.put("maxtime", "��󱨾�ʱ��");
	 		     map.put("alarmtimes", "��������");*/
	 		     	 
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
	   	     File reportFile = new File(context.getRealPath("/reports/standHelp.jasper"));
	   	     HashMap map=new HashMap();
	   	     /*map.put("cardreaderid", "��վ��");
	   	     map.put("shortname", "��վ����");
	   	     map.put("info", "״̬��Ϣ");
	     	 map.put("mintime", "��ʼ����ʱ��");
	   	     map.put("maxtime", "��󱨾�ʱ��");
	     	 map.put("alarmtimes", "��������");*/
	   	    
			 
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("StandListPrint");
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
				out.println("<span class=\"bold\">standHelp.jasper�ļ������ڻ��������</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
}

