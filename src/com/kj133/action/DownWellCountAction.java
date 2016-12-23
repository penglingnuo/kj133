//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

import com.kj133.entity.Ouser;
import com.kj133.entity.Search_DownWellCount;
import com.kj133.entity.bo.DownWellCountBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-08-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class DownWellCountAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request �¾�����ͳ��
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        DownWellCountBO bo=new DownWellCountBO();
        DynaActionForm dy = (DynaActionForm) form;
        WordlibBO lib=new WordlibBO();
        Pagination pagination = new Pagination();//��ҳ
		String page =(String)request.getParameter("page");//��ȡҳ�ŷ�ҳ
	    try{
	    	 Search_DownWellCount downwell=(Search_DownWellCount)dy.get("ser_downwellcount");
	    	 request.getSession().setAttribute("downWellStime", downwell.getStime());//����������
	    	 request.getSession().setAttribute("downWellEtime", downwell.getEtime());
	    	 Ouser user=(Ouser)request.getSession().getAttribute("user");
	    	 String userid=user.getUserid();
	    	 pagination.setCount(200);//F
	    	 if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page)); 
		      }else {
		        pagination.setPage(1);
		      }//��ҳ
	    	List list=bo.init(userid,downwell,pagination);
	    	List relist=bo.initPrint(userid,downwell);
	    	request.setAttribute("listCount", list.size());
	    	if( list.size()== 0){
	    		   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
	    	}else{
	    		   request.setAttribute("DownWellCount_List",list);
	    	}
	    	request.getSession().setAttribute("downwellcount", downwell);
	    	request.getSession().setAttribute("downWellCountPrint", relist);
	    	List gro=lib.gro(user.getUserid()); 
			List type=lib.workType();
			request.setAttribute("gro_list",gro);
			request.setAttribute("workType_list",type);
	        request.setAttribute("pagination", pagination);//��ҳ
	        dy.set("ser_downwellcount",downwell);//���°�ֵ���û�ҳ�� 
	    }catch(Exception e){
	    	log.error("==�뾮����ͳ��==",e);
	    }
		return mapping.findForward("show");
	}
   
	/**
	 * ��ʼ��ʱ��
	 * */
	public ActionForward getWordlib(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    WordlibBO bo=new WordlibBO();
			Global go=new Global();
			try{
				   DynaActionForm dy = (DynaActionForm) form;
				   Search_DownWellCount downwell=(Search_DownWellCount)dy.get("ser_downwellcount");
				  
				   Calendar cal=Calendar.getInstance();//��ȡ��ǰʱ��
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				   String etime=format.format(cal.getTime());
			 
			       String stime=go.getDay(etime,-7);   
			       downwell.setStime(stime);
			       downwell.setEtime(etime);
			       Ouser user=(Ouser)request.getSession().getAttribute("user");
				   List gro=bo.grolist(user.getUserid()); 
				   List type=bo.workType();				   
				   request.setAttribute("gro_list",gro);
				   request.setAttribute("workType_list",type);
				   request.setAttribute("listCount", "0");
				   dy.set("ser_downwellcount",downwell);
				}catch(Exception e){
					log.error("==�뾮����ͳ��==",e);
				}
			  return mapping.findForward("init");
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
				 List list=(List)request.getSession().getAttribute("DownWellCount_ListPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/downWellCount.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 map.put("stime", request.getSession().getAttribute("downWellStime"));
	 			 map.put("etime", request.getSession().getAttribute("downWellEtime"));
	 			 /*map.put("sid", "Ա�����");
	 			 map.put("username", "����");
	 			 map.put("gro", "����");
	 			 map.put("type", "����");
	 			 map.put("downtimes", "�뾮����");*/
	 			  

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
	   	     File reportFile = new File(context.getRealPath("/reports/downWellCount.jasper"));
	   	     HashMap map=new HashMap();
			 map.put("stime", request.getSession().getAttribute("downWellStime"));
			 map.put("etime", request.getSession().getAttribute("downWellEtime"));
			 /*map.put("sid", "Ա�����");
			 map.put("username", "����");
			 map.put("gro", "����");
			 map.put("type", "����");
			 map.put("downtimes", "�뾮����");*/
		   
			 
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("DownWellCount_ListPrint");
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
				out.println("<span class=\"bold\">downWellCount.Jasper�ļ������ڻ��������</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
}

