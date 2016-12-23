//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.basic.action;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.speedframework.entity.Pagination;

import com.basic.entity.Search_WorkAtt;
import com.basic.entity.bo.EmployeeBO;
import com.basic.entity.bo.WorkAttHisBO;
import com.basic.entity.vo.WorkAttHisVO;
import com.kj133.entity.Ouser;
import com.kj133.entity.Search_Account;
import com.kj133.entity.Search_Card_Batteries;
import com.kj133.entity.Search_Employee;
import com.kj133.entity.Search_ShuaKa;
import com.kj133.entity.bo.DownWellBO;
import com.kj133.entity.bo.GjBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.entity.vo.EmployeeVO;
import com.kj133.util.Global;
import com.telezone.domain.classes.ShowBackup;
import com.telezone.util.Common;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class WorkAttHisAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	
	/**
	 * 初始化
	 * */
	public ActionForward initializtion(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		WordlibBO lib=new WordlibBO();
		Ouser user =(Ouser)request.getSession().getAttribute("user");
		Global go=new Global();
		DynaActionForm dy=(DynaActionForm)form;
		Search_WorkAtt search_workatt=(Search_WorkAtt)dy.get("search_workatt");
		Calendar cal=Calendar.getInstance();//获取当前时间
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
		String etime=format.format(cal.getTime());
	 
	    String stime=go.getDay(etime,-1);
	    search_workatt.setStarttime(stime+" 00:00:00");
	    search_workatt.setEndtime(etime+" 23:59:59");
		try {
			List gro = lib.group(user);
			request.setAttribute("gro_list",gro);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("listCount", 0);//用于页面上判断打印预览是否可用
	    return mapping.findForward("show");
	}
	
	/** 
	 * 历史信息查询
	 * @param mapping
	 * @param form
	 * @param request  
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
        WorkAttHisBO watbo = new WorkAttHisBO();
        WordlibBO lib=new WordlibBO();
	    try{
				
	    	Search_WorkAtt search_workatt=(Search_WorkAtt)dy.get("search_workatt");
	    	Ouser user =(Ouser)request.getSession().getAttribute("user");
	    	search_workatt.setUserid(user.getUserid());
	        	 pagination.setCount(200); 
			     if (page != null && page.length() > 0) {
			        pagination.setPage(Integer.parseInt(page));
			      }else {
			        pagination.setPage(1);
			      }
			     
	        	 List list=watbo.init(search_workatt,pagination);
	        	 
	        	 request.setAttribute("listCount", list.size());
	        	 if( list.size()== 0){
	        		   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
	        	 }else{
	        		 request.setAttribute("workatthis_list",list);
	        		 request.getSession().setAttribute("search_workatt", search_workatt);
	        	 }
				 List gro=lib.group(user);
				 
				 request.setAttribute("gro_list",gro);
	        	 request.setAttribute("pagination", pagination);
	        	 dy.set("search_workatt",search_workatt);
	        }catch(Exception e){
	        	log.error("==历史信息查询==",e);
	        }
			return mapping.findForward("show");
	}
	
	/**
	 * 人员行走轨迹
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
			   
		       String cardid= (String)request.getParameter("cardid");
		       String downtime= (String)request.getParameter("downtime");
		       String uptime= (String)request.getParameter("uptime");
		       WorkAttHisBO watbo = new WorkAttHisBO();
		       JSONObject jsonObject=new JSONObject();
		       try {
				List list = watbo.arealist(cardid, downtime, uptime);
				List liststafferhis = watbo.getStafferListHis(cardid, downtime);
				 request.setAttribute("listCount", list.size());
	        	 if( liststafferhis.size()== 0){
	        		   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
				       request.setAttribute("listCount", 0);
	        	 }else{
	        		 jsonObject.put("area_list",list);
	        		 jsonObject.put("workatt_list_staffer_his",liststafferhis);
	        		 response.setCharacterEncoding("UTF-8");
	        		 response.setContentType("application/json;charset=UTF-8");
	        		 response.getWriter().println(jsonObject.toString());
	        	 }
			} catch (Exception e) {
				e.printStackTrace();
			}
				return null;
		}
		
	//导出Excel
	public ActionForward doOpenExcel(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response
			){
		DynaActionForm dy=(DynaActionForm)form;
			try{
				//创建导入格式表文件
				OutputStream os=response.getOutputStream();
				// 清空输出流
				response.reset();
				// 设置响应头和下载保存的文件名
//				response.setHeader("content-disposition","attachment;filename=bianyiyuangongxinxi.xls");
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("历史信息查询","UTF-8")+".xls");
				// 定义输出类型
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("历史信息查询",10);
				//	设置部分列的宽度
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 20);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 20);
				ws.setColumnView(5, 25);
				ws.setColumnView(6, 25);
				ws.setColumnView(7, 25);
				//	设置表头
				ws.addCell(new Label(0,0,"姓名"));
				ws.addCell(new Label(1,0,"卡号"));
				ws.addCell(new Label(2,0,"班组"));
				ws.addCell(new Label(3,0,"职务"));
				ws.addCell(new Label(4,0,"下井时间"));
				ws.addCell(new Label(5,0,"出井时间"));
				ws.addCell(new Label(6,0,"下井时长"));
				
				WorkAttHisBO bo=new WorkAttHisBO();
				Search_WorkAtt search_workatt=(Search_WorkAtt)request.getSession().getAttribute("search_workatt");
				List listAll=bo.initPrint(search_workatt);
				
				List list =listAll;
				//	填充数据
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						WorkAttHisVO  v= (WorkAttHisVO)list.get(i);
						ws.addCell(new Label(0,i+1,v.getName()));
						ws.addCell(new Label(1,i+1,v.getCardid()));
						ws.addCell(new Label(2,i+1,v.getGroup()));
						ws.addCell(new Label(3,i+1,v.getOccupation()));
						ws.addCell(new Label(4,i+1,v.getDowntime()));
						ws.addCell(new Label(5,i+1,v.getUptime()));
						ws.addCell(new Label(6,i+1,v.getWorktime()));
					}
				}
				wwb.write();
				wwb.close();
				os.close();
				
			}
			catch(Exception e){
				log.error("==历史信息查询-导出Excel==",e);
			}
		return null;
	} 
}

