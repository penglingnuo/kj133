//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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

import com.kj133.entity.Search_ShuaKa;
import com.kj133.entity.bo.DownWellBO;
import com.kj133.entity.bo.GjBO;
import com.kj133.entity.bo.WordlibBO;
import com.telezone.domain.classes.ShowBackup;
import com.telezone.util.Common;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class GjAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  管技人员井下考勤
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        Pagination pagination = new Pagination();
        DynaActionForm dy=(DynaActionForm)form;
		String page =(String)request.getParameter("page");//获取页号
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
		   Calendar cal=Calendar.getInstance();//获取当前时间
		   //cal.add(Calendar.DATE, +1); 
		   Date date = new Date();
		   //String stime=format.format(cal.getTime());
	       //String etime=format.format(date);
	       
        DownWellBO bo=new DownWellBO();
        GjBO gjbo = new GjBO();
        WordlibBO lib=new WordlibBO();
	    try{
				
	        	 Search_ShuaKa  downwell=(Search_ShuaKa)dy.getMap().get("ser_shuaka");
	        	 String ktime = downwell.getKtime();//查询的时间 
	        	 //String ktime =  (String)request.getParameter("ktime");
	        	 date = format.parse(ktime);//String 转换成 DATE 
	        	 cal.setTime(date);
	        	 cal.add(Calendar.DATE, -1); //开始时间为查询的时间的前一天
	        	 String stime=format.format(cal.getTime());
	        	 cal.add(Calendar.DATE,+2);//结束时间为开始时间的后两天
	        	 String etime=format.format(cal.getTime());
	        	 
	        	 downwell.setStime(stime);
	        	 downwell.setEtime(etime);
	        	 downwell.setKtime(ktime);
	        	 downwell.setType("工人");
	        	 //request.setAttribute(ktime, ktime);
	        	 
	        	 pagination.setCount(200); 
			     if (page != null && page.length() > 0) {
			        pagination.setPage(Integer.parseInt(page));
			      }else {
			        pagination.setPage(1);
			      }
			     
	        	 List list=gjbo.init(downwell,pagination);
	        	 
	        	 request.setAttribute("listCount", list.size());
	        	 //List relist=gjbo.initPrint(downwell);
	        	 if( list.size()== 0){
	        		   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
	        	 }else{
	        		 request.setAttribute("downWell_list",list);
	        	 }
	        	 request.getSession().setAttribute("downwell", downwell);//结果集
	        	// request.getSession().setAttribute("downWellPrint", relist);//结果集
	        	 //List gro=lib.grolist(user.getUserid()); 
				 List type=lib.workType();
				 List ban=lib.banName();
				 
				 //request.setAttribute("gro_list",gro);
				 request.setAttribute("workType_list",type);
	        	 request.setAttribute("pagination", pagination);
	        	 request.setAttribute("ban_list", ban);
	        	 dy.set("ser_shuaka",downwell);
	        }catch(Exception e){
	        	log.error("==管技考勤明细==",e);
	        }
			return mapping.findForward("gj");
	}
	
	/**
	 * 人员行走轨迹+人员信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getAreaList(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        Pagination pagination = new Pagination();
	        DynaActionForm dy=(DynaActionForm)form;
			   
		       String cardid= (String)request.getParameter("cardid");
		       String name= (String)request.getParameter("name");
		       String worktype= (String)request.getParameter("worktype");
		       String downtime= (String)request.getParameter("downtime");
		       String uptime= (String)request.getParameter("uptime");
		       String banci= (String)request.getParameter("banci");
		       request.setAttribute("cardid", cardid);
		       request.setAttribute("name", name);
		       request.setAttribute("worktype", worktype);
		       request.setAttribute("downtime", downtime);
		       request.setAttribute("uptime", uptime);
		       request.setAttribute("banci", banci);
		       
		       
		       List<ShowBackup> returnList = new ArrayList<ShowBackup>();   
		       GjBO gjbo = new GjBO();
		       
		    try{
		        	 Search_ShuaKa  downwell=(Search_ShuaKa)dy.getMap().get("ser_shuaka");
		        	
		        	 List list=gjbo.getStafferByCardid(cardid,name,worktype,downtime,uptime,banci);
		        	 
		        	 if( list.size()== 0){
		        		   ActionMessages messages=new ActionMessages();
					       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
					       this.saveMessages(request,messages);
		        	 }else{
		        		
		        		 for (int i = 0; i < list.size(); i++) {
		        				ShowBackup _sb = (ShowBackup) list.get(i);

		        				String cardreaderid = _sb.getCardreaderid();
		        				String ground = _sb.getGround();
		        				String endtime = _sb.getEndtime();

		        				if (i == 0) {// 第一个
		        					returnList.add(_sb);
		        				} else {// 第一个之后
		        					if (returnList.size() == 1) {
		        						ShowBackup _rsb = (ShowBackup) returnList.get(0);
		        						if (_rsb.getCardreaderid().equals(cardreaderid) == false
		        								&& _rsb.getGround().equals(ground)) {
		        							//returnList.remove(0);
		        							returnList.add(_sb);
		        						} else {
		        							ShowBackup _rsb1 = (ShowBackup) returnList
		        									.get(returnList.size() - 1);

		        							if (_rsb1.getCardreaderid().equals(cardreaderid)) {
		        								_rsb1.setEndtime(endtime);
		        								returnList.set(returnList.size() - 1, _rsb1);
		        							} else if (!_rsb1.getCardreaderid().equals(
		        									cardreaderid)) {
		        								returnList.add(_sb);
		        							}
		        						}
		        					} else {
		        						ShowBackup _rsb = (ShowBackup) returnList
		        								.get(returnList.size() - 1);

		        						if (_rsb.getCardreaderid().equals(cardreaderid)) {
		        							_rsb.setEndtime(endtime);
		        							returnList.set(returnList.size() - 1, _rsb);
		        						} else if (!_rsb.getCardreaderid().equals(cardreaderid)) {
		        							if (ground.equals("1")
		        									&& _rsb.getGround().equals("1")
		        									&& !cardreaderid.equals(_rsb
		        											.getCardreaderid())) {
		        								break;
		        							}
		        							returnList.add(_sb);
		        						}
		        					}

		        				}

		        			}

		        			for (int i = 0; i < returnList.size(); i++) {
		        				ShowBackup _sb = (ShowBackup) returnList.get(i);

		        				Common common = new Common();

		        				_sb.setBetweentime(common.betweenTime(_sb.getStarttime(), _sb
		        						.getEndtime()));

		        				returnList.set(i, _sb);
		        			}
		        		 
		        		 request.setAttribute("downWell_list",returnList);
		        	 }
		        	 request.setAttribute("pagination", pagination);
		        	 dy.set("ser_shuaka",downwell);
		        }catch(Exception e){
		        	log.error("==管技考勤明细==",e);
		        }
				return mapping.findForward("xzgj");
		}
		
	/**
	 * 页面跳转得到当前时间
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getWordlib(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        DynaActionForm dy=(DynaActionForm)form;
		    WordlibBO lib=new WordlibBO();
			  try{
				   Search_ShuaKa shuak=(Search_ShuaKa)dy.getMap().get("ser_shuaka");
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				   Calendar cal=Calendar.getInstance();//获取当前时间
				   cal.add(Calendar.DATE, +1); 
				   Date date = new Date();
				   String stime=format.format(cal.getTime());
			       String etime=format.format(date); //当前时间

//			       shuak.setStime(etime);
//				   shuak.setEtime(stime);
				   shuak.setKtime(etime);
	
				   //List type=lib.workType();
				   //request.setAttribute("workType_list",type);
				   request.setAttribute("listCount", "0");
				   dy.set("ser_shuaka",shuak);
				}catch(Exception e){
					log.error("==管技考勤明细==",e);
				}
			    return mapping.findForward("gj");
			 }
	 /**
	 * 打印预览没用到
	 * */
	@SuppressWarnings("unchecked")
	public ActionForward viewPrint(
			ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		    ServletContext context =this.getServlet().getServletConfig().getServletContext();
		    response.setContentType("text/html"); 
			try{
				 List list=(List)request.getSession().getAttribute("downWellPrint"); 
				 
		    	 File reportFile = new File(context.getRealPath("/reports/gj.jasper"));
		    	 String path=reportFile.getPath();
		    	 System.out.println("a="+path);
		    	 System.out.println("reportFile="+reportFile.getPath());
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(path);
	 			// JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());

	 			 HashMap map=new HashMap();
	 			

	 			if(list !=null && !list.isEmpty()){
	 				
	 				 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
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
	 			}
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
	        return mapping.findForward("vprint");
	}
	
	/**
	 *打印没用到
	 * */
	@SuppressWarnings("unchecked")
	public ActionForward print(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
	     	ServletContext context = this.getServlet().getServletConfig().getServletContext();
	   	     File reportFile = new File(context.getRealPath("/reports/gj.jasper"));
	   	     HashMap map=new HashMap();
	   	    
			 map.put("summation", 1);
		   
			 
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("downWellPrint");
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
				out.println("<span class=\"bold\">gj.Jasper文件不存在或解析错误！</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
}

