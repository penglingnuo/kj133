//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;


import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.struts.actions.DispatchAction;
import com.kj133.entity.bo.Stripclip_query_rightBO;


/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Stripclip_query_rightAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  区域超时明细
	 * @param response
	 * @return ActionForward
	 */


	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        String areaid=request.getParameter("areaid");
        String recordtime=request.getParameter("recordtime");
        request.getSession().setAttribute("areaid",areaid);//用于传递编号
        request.getSession().setAttribute("recordtime",recordtime);//用于传递编号
        String rt = recordtime.substring(0, 19);
        
//        System.out.println("os =" + os);
//        System.out.println("oe =" + oe);
//        String aid = request.getParameter("aid");
//        request.getSession().setAttribute("aid", aid);
        
         
//		String s1=atime.substring(0,10);
//		String ss=go.getDay(s1,-1); 
//				
//		String s2=atime.substring(11,19); 
//		
//		String atime1=atime.substring(0,19);  
//		String btime = ss+" "+s2;
//		System.out.println("========="+atime1+"===");
//		System.out.println("========="+btime+"===");

        Stripclip_query_rightBO bo=new Stripclip_query_rightBO();
        
		try{
		
			List list=bo.check(rt,areaid);
		       request.setAttribute("relist",list);
		}catch(Exception e){
		   e.printStackTrace();
		}
        return mapping.findForward("init");
	}
/*	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
//        Pagination pagination = new Pagination();
        DynaActionForm dy=(DynaActionForm)form;
//		String page =(String)request.getParameter("page");//获取页号
        Area_overtime_downBO bo=new Area_overtime_downBO();
        WordlibBO lib=new WordlibBO();
	    try{
	    	Search_car_move_log  car_move_log=(Search_car_move_log)dy.getMap().get("car_move_log");
	        	 pagination.setCount(1); 
			     if (page != null && page.length() > 0) {
			        pagination.setPage(Integer.parseInt(page));
			      }else {
			        pagination.setPage(1);
			      }
	        	 List list=bo.init(car_move_log);
	        	 request.setAttribute("listCount", list.size());
	        	 List workTimeList=bo.getAllWorkTime(car_move_log);
	        	 Iterator it=workTimeList.iterator();
	        	 while(it.hasNext()){
	        		 Car_move_logVO vo=(Car_move_logVO)it.next();
	        		 request.getSession().setAttribute("allWorkTime", vo.getAllworktime());	 
	        	 }
	        	 if( list.size()== 0){
	        		   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
	        	 }else{
	        		 request.setAttribute("car_move_log_list",list);
	        		 request.getSession().setAttribute("car_move_log_listPrint", list);//结果集
	        	 }
	        	 List gro=lib.gro(); 
				 List type=lib.workType();

				 request.setAttribute("workType_list",type);
//	        	 request.setAttribute("pagination", pagination);

	        	 dy.set("car_move_log",car_move_log);
	        }catch(Exception e){
	        	 e.printStackTrace();
	        }
			return mapping.findForward("show");
	}
	
	public ActionForward getWordlib(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			Global go=new Global();
	        DynaActionForm dy=(DynaActionForm)form;
		    WordlibBO lib=new WordlibBO();
			  try{
				  Search_car_move_log car_move_log=(Search_car_move_log)dy.getMap().get("car_move_log");
				   Calendar cal=Calendar.getInstance();//获取当前时间
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				   String etime=format.format(cal.getTime());
			 
			       String stime=go.getDay(etime,-7);   
			       car_move_log.setStime(stime+" 00:00:00");
			       car_move_log.setEtime(etime+" 00:00:00");

				 
				   List type=lib.workType();
				  
				   
				   request.setAttribute("workType_list",type);
				  
				   request.setAttribute("listCount", "0");
				   dy.set("car_move_log",car_move_log);
				}catch(Exception e){
					e.printStackTrace();
				}
			    return mapping.findForward("wordlib");
			 }
	 *//**
	 * 打印预览
	 * *//*
	@SuppressWarnings("unchecked")
	public ActionForward viewPrint(
			ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		    ServletContext context =this.getServlet().getServletConfig().getServletContext();
		    response.setContentType("text/html"); 
			try{
				 List list=(List)request.getSession().getAttribute("downWell_listPrint");   
		    	 File reportFile = new File(context.getRealPath("/reports/downWell.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			 map.put("summation",request.getSession().getAttribute("allWorkTime").toString());

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
	    	     
	    	     exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
	    	     exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(pageIndex));
      	         exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
                 exporter.exportReport();
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
	        return mapping.findForward("vprint");
	}
	
	*//**
	 *打印
	 * *//*
	@SuppressWarnings("unchecked")
	public ActionForward print(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
	     	ServletContext context = this.getServlet().getServletConfig().getServletContext();
	   	     File reportFile = new File(context.getRealPath("/reports/downWell.jasper"));
	   	     HashMap map=new HashMap();
	   	    
			 map.put("summation", request.getSession().getAttribute("allWorkTime").toString());
		   
			 
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("downWell_listPrint");
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
				out.println("<span class=\"bold\">downWell.Jasper文件不存在或解析错误！</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }*/
}

