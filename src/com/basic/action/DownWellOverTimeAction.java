package com.basic.action;

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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.speedframework.entity.Pagination;

import com.basic.entity.bo.DownWellOverTimeBO;
import com.kj133.entity.Ouser;
import com.kj133.entity.Search_OverTime;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.entity.vo.OverTimeVO;
import com.kj133.util.Global;

public class DownWellOverTimeAction extends DispatchAction{
	
	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping 

	 * @param form
	 * @param request  下井超时报警
	 * @param response
	 * @return ActionForward
	 */
	
	/**
	 * 初始化
	 * */
	public ActionForward initialization(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		 	DownWellOverTimeBO vo=new DownWellOverTimeBO();
		    Pagination pagin=new Pagination();
		    String page=request.getParameter("page");
		    DynaActionForm dy=(DynaActionForm)form;
		    Ouser user=(Ouser)request.getSession().getAttribute("user");
		    WordlibBO lib=new WordlibBO();
		    Global go=new Global();
		    try{
		    	 Search_OverTime over=(Search_OverTime)dy.get("overtime");
		    	 Calendar cal=Calendar.getInstance();//获取当前时间
				 SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				 String etime=format.format(cal.getTime());
				 String stime=go.getDay(etime,-1);     
		    	 over.setStime(stime+" 00:00:00");
		    	 over.setEtime(etime+" 23:59:59");
		    	 
		    	 pagin.setCount(200);
		    	 if( page != null && page.length()>0){
			    	pagin.setPage(Integer.parseInt(page));
			     }else{
			         pagin.setPage(1);	 
			     }
		    	 over.setUserid(user.getUserid());
		    	 List time=vo.getInit(over,pagin);
		    	 if( time.size() ==0){
		    		   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
		    	 }else{
					  request.setAttribute("over_List",time);
					  request.getSession().setAttribute("OverTime", over);
		    	 }
		    	 
		    	 List dpt=lib.yaoqiaoDept(user.getUserid());
				 request.setAttribute("dpt_list",dpt);
				 request.setAttribute("listCount", "0");
				 request.setAttribute("pagination", pagin);
				 dy.set("overtime",over);
		    }catch(Exception e){
		    	log.error("==入井超时查询==",e);
		    }
		return mapping.findForward("basicShow");
	}
	
	
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		   WordlibBO lib=new WordlibBO();
		   DownWellOverTimeBO vo=new DownWellOverTimeBO();
		   Pagination pagin=new Pagination();
		   Ouser user =(Ouser)request.getSession().getAttribute("user");
		   DynaActionForm dy=(DynaActionForm)form;
		   String page=request.getParameter("page");
		   try{
		    	 Search_OverTime over=(Search_OverTime)dy.get("overtime");
		    	 request.getSession().setAttribute("ktime", over.getStime());
		    	 request.getSession().setAttribute("jtime", over.getEtime());
		    	 pagin.setCount(200);
		    	 if( page != null && page.length()>0){
			    	pagin.setPage(Integer.parseInt(page));
			     }else{
			         pagin.setPage(1);	 
			     }
		    	 over.setUserid(user.getUserid());
		    	 List time=vo.getList(over,pagin);
//		    	 List retime=vo.getListPrint(over);
		    	 request.setAttribute("listCount", time.size());
		    	 if( time.size() ==0){
		    		   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
		    	 }else{
					  request.setAttribute("over_List",time);
					  request.getSession().setAttribute("OverTime", over);
//					  request.getSession().setAttribute("over_ListPrint", retime);
		    	 }
		    	 List dpt=lib.yaoqiaoDept(user.getUserid());
				 request.setAttribute("dpt_list",dpt);
				 request.setAttribute("pagination",pagin);
				 
				 dy.set("overtime",over);
		   }catch(Exception e){
			   log.error("==入井超时查询==",e);
		}
		return mapping.findForward("basicShow");
	}
	
	
	/**
	 *打印
	 * */
	@SuppressWarnings("unchecked")
	public ActionForward print(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
	     	ServletContext context = this.getServlet().getServletConfig().getServletContext();
	   	     File reportFile = new File(context.getRealPath("/reports/overTime.jasper"));
	   	     
	   	     DynaActionForm dy=(DynaActionForm)form;
	   	     Search_OverTime over=(Search_OverTime)dy.get("overtime");
	   	     
	   	     HashMap map=new HashMap();
	   	     map.put("ktime", over.getStime());
	   	     map.put("jtime", over.getEtime());
	   	    /* map.put("cardid", "卡号");
	   	     map.put("username", "姓名");
	   	     map.put("gro", "班组");
	     	 map.put("info", "状态信息");
	   	     map.put("downtime", "入井时间");
	     	 map.put("staytime", "停留时间");
	     	 map.put("overtime", "超时时间");*/
	   	    
			 
			JasperPrint jasperPrint = null;
		    try
			{
		    	DownWellOverTimeBO vo=new DownWellOverTimeBO();
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list= vo.getListPrint(over);
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
				out.println("<span class=\"bold\">overTime.jasper文件不存在或解析错误！</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
	
//	导出Excel
	public ActionForward doOpenExcel(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response
			){
		
			DynaActionForm dy=(DynaActionForm)form;
			Search_OverTime over=(Search_OverTime)dy.get("overtime");
			DownWellOverTimeBO vo=new DownWellOverTimeBO();
  	     
			try{
				//创建导入格式表文件
				OutputStream os=response.getOutputStream();
				// 清空输出流
				response.reset();
				// 设置响应头和下载保存的文件名
//				response.setHeader("content-disposition","attachment;filename=bianyiyuangongxinxi.xls");
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("入井超时报警","UTF-8")+".xls");
				// 定义输出类型
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("入井超时报警",10);
				//	设置部分列的宽度
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 20);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 20);
				ws.setColumnView(5, 20);
				ws.setColumnView(6, 25);
				ws.setColumnView(7, 25);
				ws.setColumnView(8, 20);
				ws.setColumnView(9, 20);
				ws.setColumnView(10, 20);
				//	设置表头
				ws.addCell(new Label(0,0,"卡号"));
				ws.addCell(new Label(1,0,"姓名"));
				ws.addCell(new Label(2,0,"部门"));
				ws.addCell(new Label(3,0,"班组"));
				ws.addCell(new Label(4,0,"状态信息"));
				ws.addCell(new Label(5,0,"入井时间"));
				ws.addCell(new Label(6,0,"升井时间"));
				ws.addCell(new Label(7,0,"停留时间"));
				ws.addCell(new Label(8,0,"额定时间"));
				ws.addCell(new Label(9,0,"超时时间"));

//				Search_OverTime over=(Search_OverTime)request.getSession().getAttribute("OverTime");
//				List retime=vo.getListPrint(over);
				List list = vo.getListPrint(over);
				//	填充数据
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						OverTimeVO  v= (OverTimeVO)list.get(i);
						ws.addCell(new Label(0,i+1,v.getCardid()));
						ws.addCell(new Label(1,i+1,v.getUsername()));
						ws.addCell(new Label(2,i+1,v.getDepartment()));
						ws.addCell(new Label(3,i+1,v.getGro()));
						ws.addCell(new Label(4,i+1,v.getInfo()));
						ws.addCell(new Label(5,i+1,v.getDowntime()));
						ws.addCell(new Label(6,i+1,v.getUptime()));
						ws.addCell(new Label(7,i+1,v.getStaytime()));
						ws.addCell(new Label(8,i+1,v.getRatetime()));
						ws.addCell(new Label(9,i+1,v.getOvertime()));
					}
				}
				wwb.write();
				wwb.close();
				os.close();
				
			}
			catch(Exception e){
				log.error("==查询员工-导出Excel==",e);
			}
		return null;
	}
}
