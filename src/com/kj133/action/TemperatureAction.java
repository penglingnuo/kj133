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
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_Temperature;
import com.kj133.entity.bo.TemperatureBO;
import com.kj133.entity.vo.TemperatureVO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class TemperatureAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  �¶Ȳ�ѯ
	 * @param response 
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        DynaActionForm dy=(DynaActionForm) form;
        TemperatureBO bo=new TemperatureBO();
        Pagination pagination=new Pagination();
        List list=null;
        
		String page =(String)request.getParameter("page");//��ȡҳ��
		int currentPageIdx = 1;  // ��һ�λ�ȡ��ʱ����nullֵ
		try{
			    try {
    				currentPageIdx = Integer.parseInt(page);
    			  } catch (Exception e) 
    			  { }
    	      if (currentPageIdx < 1)
    			  currentPageIdx = 1;
    	      
		      Search_Temperature  ser=(Search_Temperature)dy.get("ser_temperature");
		      request.getSession().setAttribute("ktime", ser.getStartime());
		      request.getSession().setAttribute("jtime", ser.getEndtime());
			  pagination.setCount(200); 
			  if(currentPageIdx == 1){
					  pagination.setPage(1);
			          list=bo.isNull(ser);
			          if( list.size()==0){
			        	   ActionMessages messages=new ActionMessages();
					       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
					       this.saveMessages(request,messages);
			          }
				}else{
					    pagination.setPage(Integer.parseInt(page));
			            list=bo.notNull(ser,page);
			            
			   }
		    request.setAttribute("temperature_list",list);
		    request.getSession().setAttribute("sertemperature", ser);
		    request.getSession().setAttribute("temperaturePrint", list);
		    request.setAttribute("listCount", list.size());
			request.setAttribute("pagination", pagination);
		  
			List allcount=bo.allCount(ser);//��ҳ��
		    TemperatureVO vo=(TemperatureVO)allcount.get(0);
		    pagination.setTotalCount(Integer.parseInt(vo.getAllcount()));
		    if(ser.getMinstime()==null){
		    	ser.setMinstime("15:47:51");

       	 }else{
       		ser.setMinstime(ser.getMinstime());
       		request.setAttribute("name1", "true");
       	 }
       	 if(ser.getMaxstime()==null){
       		ser.setMaxstime("15:47:51");
       	 }else{
       		ser.setMaxstime(ser.getMaxstime());
       		request.setAttribute("name2", "true");

       	 }
		    dy.set("ser_temperature",ser);
		}catch(Exception e){
			log.error("==�¶Ȳ�ѯ==",e);
		}
		return mapping.findForward("show");
		}
	   
	/**
	 * ��ʼ��ʱ��
	 * */
	public ActionForward initialization(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm) form;
			Global go=new Global();
			 try{
				 Search_Temperature  temp=(Search_Temperature)dy.get("ser_temperature");
				 Calendar cal=Calendar.getInstance();//��ȡ��ǰʱ��
				 SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				 String etime=format.format(cal.getTime());
			 
			     String stime=go.getDay(etime,-7);   
			     temp.setStartime(stime);
			     temp.setEndtime(etime);
			     temp.setMinstime("15:47:51");
			     temp.setMaxstime("15:47:51");
			     request.setAttribute("listCount", "0");
				 dy.set("ser_temperature",temp);
			 }catch(Exception e){
				 log.error("==�¶Ȳ�ѯ==",e);
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
				 List list=(List)request.getSession().getAttribute("temperature_listPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/temperature.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 map.put("ktime", request.getSession().getAttribute("ktime"));
				 map.put("jtime", request.getSession().getAttribute("jtime"));
				 /*map.put("cid", "��վ��");
				 map.put("cname", "��վ����");
				 map.put("temp", "�¶�");
				 map.put("times", "��¼ʱ��");*/
	 		
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
	   	     File reportFile = new File(context.getRealPath("/reports/temperature.jasper"));
	   	     HashMap map=new HashMap();
	   	     map.put("ktime", request.getSession().getAttribute("ktime"));
			 map.put("jtime", request.getSession().getAttribute("jtime"));
			 /*map.put("cid", "��վ��");
			 map.put("cname", "��վ����");
			 map.put("temp", "�¶�");
			 map.put("times", "��¼ʱ��");*/
	 	   		 
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("temperature_listPrint");
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
				out.println("<span class=\"bold\">Temperature.Jasper�ļ������ڻ��������</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
	
	
}

