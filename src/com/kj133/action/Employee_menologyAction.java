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

import com.kj133.entity.Ouser;
import com.kj133.entity.Search_Employeet_menology;
import com.kj133.entity.bo.Employee_menologyBO;
import com.kj133.entity.bo.WordlibBO;



/** 
 * MyEclipse Struts
 * Creation date: 03-15-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Employee_menologyAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request Ա�������±� //�������ҳ
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    WordlibBO lib=new WordlibBO();
	    Employee_menologyBO bo=new Employee_menologyBO();
        DynaActionForm dy=(DynaActionForm)form;
        Pagination pagination = new Pagination();//��ҳ
		String page =(String)request.getParameter("page");//��ȡҳ�ŷ�ҳ
        try{
        	
	    	//List list=bo.init(userid,downwell,pagination);
        	Ouser user=(Ouser)request.getSession().getAttribute("user");
        	Search_Employeet_menology emplo=(Search_Employeet_menology)dy.get("employee_menology");
        	request.getSession().setAttribute("riqi", emplo.getStime());
        	pagination.setCount(200);//F
	    	 if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page)); 
		      }else {
		        pagination.setPage(1);
		      }//��ҳ      
        	List list=bo.getList(emplo,user.getUserid());
        	request.setAttribute("listCouont", list.size());
			System.out.print("listsize="+list.size());
        	//list =list.subList(0, 50);
        	List gro=lib.grolist(user.getUserid()); 
        	List type=lib.workType();
        	  	
        	if( list.size()== 0){
     		   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
     	 }else{
     		request.setAttribute("relist",list);
			request.getSession().setAttribute("employee_menology", emplo);
			request.getSession().setAttribute("employee_menologyPrint", list);
     	 }
        	
        	request.setAttribute("workType_list",type);
			request.setAttribute("gro_list",gro);
			
        	dy.set("employee_menology",emplo);
        }catch(Exception e){
        	log.error("==Ա�������±�==",e);
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
	        WordlibBO lib=new WordlibBO();
	        DynaActionForm dy=(DynaActionForm)form;
	        try{
	        	Search_Employeet_menology emplo=(Search_Employeet_menology)dy.get("employee_menology");
	        	Calendar cal=Calendar.getInstance();//��ȡ��ǰʱ��
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM");
			    String etime=format.format(cal.getTime());
	        	emplo.setStime(etime);
	        	Ouser user=(Ouser)request.getSession().getAttribute("user");
	        	List gro=lib.grolist(user.getUserid()); 
	        	List type=lib.workType();
	        	request.setAttribute("workType_list",type);
				request.setAttribute("gro_list",gro);
				//request.setAttribute("listCouont", "1");
	        	dy.set("employee_menology",emplo);
	        }catch(Exception e){
	        	log.error("==Ա�������±�==",e);
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
				 List list=(List)request.getSession().getAttribute("employee_menologyPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/employee_menology.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 //map.put("title","�·ݿ��ڱ�");
	 			 //map.put("danwei", " ");
				 map.put("riqi", "�·ݣ�"+request.getSession().getAttribute("riqi"));
				 /*map.put("count", "���");
				 map.put("staffername", "����");
				 map.put("worktype", "����");
				 map.put("sum1", "���");
				 map.put("sum2", "�а�");
				 map.put("sum3", "���");
				 map.put("sum4", "�ܳ���");
				 map.put("sum5", "�Ӱ�"); */
				 
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
	   	     File reportFile = new File(context.getRealPath("/reports/employee_menology.jasper"));
	   	     HashMap map=new HashMap();
	   	   //  map.put("title","�·ݿ��ڱ�");
			// map.put("danwei", " ");
			 map.put("riqi", "�·ݣ�"+request.getSession().getAttribute("riqi"));
			/* map.put("count", "���");
			 map.put("staffername", "����");
			 map.put("worktype", "����");
			 map.put("sum1", "���");
			 map.put("sum2", "�а�");
			 map.put("sum3", "���");
			 map.put("sum4", "�ܳ���");
			 map.put("sum5", "�Ӱ�");*/
			  
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("employee_menologyPrint");
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
				out.println("<span class=\"bold\">employee_menology.Jasper�ļ������ڻ��������</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
	

}

