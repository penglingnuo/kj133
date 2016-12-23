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

import com.kj133.entity.Search_HistoryNote;
import com.kj133.entity.bo.HistoryNoteBO;
import com.kj133.entity.vo.HistoryNoteVO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-07-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class HistoryNoteAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form 
	 * @param request  历史记录查询
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        HistoryNoteBO bo=new HistoryNoteBO();
        DynaActionForm dy=(DynaActionForm)form;
        Pagination pagination = new Pagination();//F
		String page =(String)request.getParameter("page");//获取页号F
		int currentPageIdx = 1;  // 第一次获取的时候是null值
        List list=null;
		try{
				 try {
	    				currentPageIdx = Integer.parseInt(page);
	    			  }catch (Exception e) 
	    			  { }
		    	 if (currentPageIdx < 1)
		    			 currentPageIdx = 1;
		    	     
				 Search_HistoryNote note=(Search_HistoryNote)dy.get("ser_historynote");
				 request.getSession().setAttribute("ktime", note.getStime());
				 request.getSession().setAttribute("jtime", note.getEtime());
		    	 pagination.setCount(2000);//F
		    	 if(currentPageIdx == 1){
		    		 pagination.setPage(1);
				     list=bo.first(note);
				     if( list.size()== 0){
				    	   ActionMessages messages=new ActionMessages();
					       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
					       this.saveMessages(request,messages);
				     }
				 }else{
					 pagination.setPage(Integer.parseInt(page));
					 list=bo.next(note,page);
				   }
		    	 
		         request.setAttribute("HistoryNote_List",list);
		         request.setAttribute("listCount", list.size());
		         request.getSession().setAttribute("HistoryNote_List", note);
		         request.getSession().setAttribute("HistoryNote_ListPrint", list);
			     request.setAttribute("pagination", pagination);//F
			     List count=bo.getAllcount(note);
			     HistoryNoteVO vo=(HistoryNoteVO)count.get(0);
			     pagination.setTotalCount(Integer.parseInt(vo.getCount()));//总的记录数
			     if(note.getMinstime()==null){
		    		 note.setMinstime("15:47:51");

	        	 }else{
	        		 note.setMinstime(note.getMinstime());
	        		 request.setAttribute("name1", "true");

	        	 }
	        	 if(note.getMaxstime()==null){
	        		 note.setMaxstime("15:47:51");
	        	 }else{
	        		 note.setMaxstime(note.getMaxstime());
	        		 request.setAttribute("name2", "true");

	        	 }
			     dy.set("ser_historynote",note);
			}catch(Exception e){
				log.error("==历史记录查询==",e);
			}
			 return mapping.findForward("show"); 
	}
	/**
	 * 初始化时间
	 * */
	public ActionForward initialization(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    DynaActionForm dy=(DynaActionForm)form;
			Global go=new Global();
			try{
				  Search_HistoryNote note=(Search_HistoryNote)dy.get("ser_historynote");
				  note.setRad("radand");
					
				   Calendar cal=Calendar.getInstance();//获取当前时间
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				   String etime=format.format(cal.getTime());
			 
			       String stime=go.getDay(etime,-7);   
			       note.setStime(stime);
			       note.setEtime(etime);
			       note.setMinstime("15:47:51");
			       note.setMaxstime("15:47:51");
					 
				   dy.set("ser_historynote",note);
				   request.setAttribute("listCount", "0");
				}catch(Exception e){
					log.error("==历史记录查询==",e);
				}
		 return mapping.findForward("show"); 
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
				 List list=(List)request.getSession().getAttribute("HistoryNote_ListPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/historyNote.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 map.put("ktime", request.getSession().getAttribute("ktime"));//起始时间
	 			 map.put("jtime", request.getSession().getAttribute("jtime"));//截止时间
	 			/* map.put("cid", "分站号");
	 			 map.put("name", "分站名称");
	 			 map.put("times", "记录时间");
	 			 map.put("temper", "温度");
	 			 map.put("ccount", "卡数");
	 			 map.put("info","状态信息");*/
	 			
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
	public ActionForward print(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
	     	ServletContext context = this.getServlet().getServletConfig().getServletContext();
	   	     File reportFile = new File(context.getRealPath("/reports/historyNote.jasper"));
	   	     HashMap map=new HashMap();
	   	     map.put("ktime", request.getSession().getAttribute("ktime"));//起始时间
			 map.put("jtime", request.getSession().getAttribute("jtime"));//截止时间
			 /*map.put("cid", "分站号");
 			 map.put("name", "分站名称");
 			 map.put("times", "记录时间");
 			 map.put("temper", "温度");
 			 map.put("ccount", "卡数");
 			 map.put("info","状态信息");*/
			 
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("HistoryNote_ListPrint");
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
				out.println("<span class=\"bold\">historyNote.Jasper文件不存在或解析错误！</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
	

}

