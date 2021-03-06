/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.kj133.action;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
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

import com.kj133.entity.Search_Employee;
import com.kj133.entity.bo.EmployeeBO;

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

import com.kj133.entity.bo.WordlibBO;
import com.kj133.entity.vo.EmployeeVO;

/** 
 * MyEclipse Struts
 * Creation date: 06-15-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class EmployeeAction extends DispatchAction {
	/*
	 * Generated Methods
	 */
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  编辑员工
	 * @param response
	 * @return ActionForward
	 */
	
	/**
	 * 初始化工种和部门
	 * */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	        WordlibBO bo=new WordlibBO();
		        try{
				   List dep=bo.dept();
				   List type=bo.workType();
				   request.setAttribute("dep_list", dep);
				   request.setAttribute("workType_list",type);
				   request.setAttribute("listCount", "0");
		        }catch(Exception e){
		        	log.error("==编辑员工-初始化==",e);
		        }
		        return mapping.findForward("employee");
	}
	/**
	 * 查询
	 * */
	public ActionForward getStaffer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	        DynaActionForm dy=(DynaActionForm)form;
			EmployeeBO bo=new EmployeeBO();
		    WordlibBO wordlib=new WordlibBO();
	        Pagination pagination = new Pagination();//分页
			String page =(String)request.getParameter("page");//获取页号分页
		    try{
		         Search_Employee employee=(Search_Employee)dy.get("search_employee");
		    	 pagination.setCount(200);//F
		    	 if (page != null && page.length() > 0) {
			        pagination.setPage(Integer.parseInt(page)); 
			      }else {
			        pagination.setPage(1);
			      } 
				 List stafferList=bo.getStaffer(employee,pagination);
				 List list=bo.getStafferPrint(employee);
				 request.setAttribute("listCount", stafferList.size());//用于页面上判断打印预览是否可用
				 if( stafferList.size() == 0){
					   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
				 }else{
				       request.setAttribute("stafferList",stafferList);
				       request.getSession().setAttribute("employeePrint", list);
				  }
			     List dep=wordlib.dept();
				 List type=wordlib.workType();
				 request.setAttribute("dep_list", dep);
				 request.setAttribute("workType_list",type);
				 request.setAttribute("pagination", pagination);//分页
			     dy.set("search_employee", employee);
				  
			}catch(Exception e){
				log.error("==编辑员工-查询==",e);  
			}
			 return mapping.findForward("employee");
	}
	
//	导出Excel
	public ActionForward doOpenExcel(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response
			){
		
			try{
				//创建导入格式表文件
				OutputStream os=response.getOutputStream();
				// 清空输出流
				response.reset();
				// 设置响应头和下载保存的文件名
//				response.setHeader("content-disposition","attachment;filename=bianyiyuangongxinxi.xls");
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("编译员工信息","UTF-8")+".xls");
				// 定义输出类型
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("编译员工信息",10);
				//	设置部分列的宽度
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 20);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 20);
				ws.setColumnView(6, 20);
				ws.setColumnView(7, 25);
				//	设置表头
				ws.addCell(new Label(0,0,"员工号"));
				ws.addCell(new Label(1,0,"卡号"));
				ws.addCell(new Label(2,0,"姓名"));
				ws.addCell(new Label(3,0,"部门"));
				ws.addCell(new Label(4,0,"班组"));
				ws.addCell(new Label(5,0,"工种"));
				ws.addCell(new Label(6,0,"职务"));
				ws.addCell(new Label(7,0,"到职日期"));
				
				List list = (ArrayList)request.getSession().getAttribute("employeePrint");
				//	填充数据
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						EmployeeVO  v= (EmployeeVO)list.get(i);
						ws.addCell(new Label(0,i+1,v.getStafferid()));
						ws.addCell(new Label(1,i+1,v.getCardid()));
						ws.addCell(new Label(2,i+1,v.getUsername()));
						ws.addCell(new Label(3,i+1,v.getDepartment()));
						ws.addCell(new Label(4,i+1,v.getGro()));
						ws.addCell(new Label(5,i+1,v.getWorktype()));
						ws.addCell(new Label(6,i+1,v.getOccupation()));
						ws.addCell(new Label(7,i+1,v.getJointime()));
					}
				}
				wwb.write();
				wwb.close();
				os.close();
				
			}
			catch(RowsExceededException e){
				log.error("==编辑员工-导出Excel==",e);
			}
			catch(WriteException ex){
				log.error("==编辑员工-导出Excel==",ex);
			}
			catch(IOException e){
				log.error("==编辑员工-导出Excel==",e);
			}
		return null;
	}
	
	/**
	 * 打印预览
	 * */
	@SuppressWarnings("unchecked")
	public ActionForward viewPrint(
			ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		    ServletContext context =this.getServlet().getServletConfig().getServletContext();
		    response.setContentType("text/html"); 
			try{
				 List list=(List)request.getSession().getAttribute("stafferListViewPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/employee.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 
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
	    		  
	    		 exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);//对单个模板进行操作
	    	     exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
	    	     //exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "images目录的路径?image=");
                 //在生成html预览的时候会生成一个px文件，而这个images_uri的路径就是那个px的路径，建议不用，就用下面 的语句就可以了···· 
	    	     //不写的话，页面上就会显示很多图片，并且是那种图片加载不成功的状态
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
	 *打印
	 * */
	@SuppressWarnings("unchecked")
	public ActionForward printEmployee(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
	     	ServletContext context = this.getServlet().getServletConfig().getServletContext();
	   	     File reportFile = new File(context.getRealPath("/reports/employee.jasper"));
	   	     HashMap map=new HashMap();
 
		   
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("stafferListViewPrint");
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
				out.println("<span class=\"bold\">Employee.Jasper文件不存在或解析错误！</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
	/**
	 * 删除
	 * */
	public ActionForward delete(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    String sid=request.getParameter("sid");
		    EmployeeBO bo=new EmployeeBO();
		    String[] name=sid.split("\\,");
		    for(int i=0;i<name.length;i++){
			    try{
			    	 bo.delete(name[i]);
			    	 ActionMessages messages=new ActionMessages();
					 messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.delete.cardid"));
				     saveMessages(request, messages); 
			    }catch(Exception e){
			    	log.error("==删除编辑员工==",e);
			    }
		    }
	          return mapping.findForward("delete");
	 }
}