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

import com.kj133.entity.Ouser;
import com.kj133.entity.Search_Note;

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
import com.kj133.entity.bo.NoteBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.entity.vo.NoteVO;
import com.kj133.util.Global;


/** 
 * MyEclipse Struts
 * Creation date: 02-06-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class NoteAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute  
	 * @param form
	 * @param request  明细记录查询
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    NoteBO bo=new NoteBO();
	    WordlibBO lib=new WordlibBO();
	    DynaActionForm dy=(DynaActionForm)form;
	    List list=null;
	    Pagination pagination = new Pagination(); 
		String page =(String)request.getParameter("page");
		int currentPageIdx = 1;  // 第一次获取的时候是null值
		try{
		     try {
				currentPageIdx = Integer.parseInt(page);
			  }catch (Exception e) 
			  { }
    	     if (currentPageIdx < 1)
    			 currentPageIdx = 1;
    	      
		   Search_Note note=(Search_Note)dy.get("ser_note");
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
	       Ouser user=(Ouser)request.getSession().getAttribute("user");
	         List gro=lib.gro(user.getUserid()); 
			 List type=lib.workType();
			 request.setAttribute("gro_list",gro);
			 request.setAttribute("workType_list",type);
	         request.setAttribute("Note_List",list);
	         request.getSession().setAttribute("Note_ListPrint", list);
	         request.setAttribute("listCount", list.size());
		     request.setAttribute("pagination", pagination);//F
	    	 List allcount=bo.getAllcount(note);//用的是自己的查询语句，
	    	 NoteVO vo=(NoteVO)allcount.get(0);//speed的分页标签
		     pagination.setTotalCount(Integer.parseInt(vo.getCount()));//总的记录数
		     dy.set("ser_note",note);
		}catch(Exception e){
			log.error("==明细记录查询==",e);
		}
		return mapping.findForward("show");
		}
	 /**
	  * 下拉框的值
	  * */
	public ActionForward getWordlib(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm)form;
		    WordlibBO lib=new WordlibBO();
		    Global go=new Global();
			try{
				   Search_Note note=(Search_Note)dy.get("ser_note");
				   note.setRad("radand");
					
				   Calendar cal=Calendar.getInstance();//获取当前时间
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				   String etime=format.format(cal.getTime());
				 
				   String stime=go.getDay(etime,-7);   
				   note.setStime(stime+" 08:30:46");
				   note.setEtime(etime+" 20:54:39");
				   Ouser user=(Ouser)request.getSession().getAttribute("user");
				   List gro=lib.grolist(user.getUserid()); 
				   List type=lib.workType();
				   request.setAttribute("gro_list",gro);
				   request.setAttribute("workType_list",type);
				   dy.set("ser_note",note);
				   request.setAttribute("listCount", "0");
				}catch(Exception e){
					log.error("==明细记录查询==",e);
				}
			    return mapping.findForward("wordlib");
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
				 List list=(List)request.getSession().getAttribute("Note_ListPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/note.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 map.put("ktime", request.getSession().getAttribute("ktime"));//起始时间
	 			 map.put("jtime", request.getSession().getAttribute("jtime"));//截止时间
	 			/* map.put("cid", "卡号");
	 			 map.put("name", "姓名");
	 			 map.put("type", "工种");
	 			 map.put("gro", "班组");
	 			 map.put("sname", "分站");
	 			 map.put("stime", "起始时间");
	 			 map.put("etime", "截止时间");
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
	   	     File reportFile = new File(context.getRealPath("/reports/note.jasper"));
	   	     HashMap map=new HashMap();
	   	     map.put("ktime", request.getSession().getAttribute("ktime"));//起始时间
			 map.put("jtime", request.getSession().getAttribute("jtime"));//截止时间
			 /*map.put("cid", "卡号");
			 map.put("name", "姓名");
			 map.put("type", "工种");
			 map.put("gro", "班组");
			 map.put("sname", "分站");
			 map.put("stime", "起始时间");
			 map.put("etime", "截止时间");
		     map.put("info","状态信息");*/
			 
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("Note_ListPrint");
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
				out.println("<span class=\"bold\">Note.Jasper文件不存在或解析错误！</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
	
}

