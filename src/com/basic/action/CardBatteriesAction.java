package com.basic.action;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.speedframework.entity.Pagination;

import com.basic.entity.bo.CardBatteriesBO;
import com.basic.entity.vo.CardBatteriesVO;
import com.basic.entity.vo.CardCallVO;
import com.kj133.entity.Ouser;
import com.kj133.entity.Search_Card_Batteries;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;



public class CardBatteriesAction extends DispatchAction{
	
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request卡电池报警
	 * @param response
	 * @return ActionForward
	 */
	
	/**
	  * 初始化时间和班组
	  * */
	public ActionForward getBatteriesAlarm(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			CardBatteriesBO bo=new CardBatteriesBO();
		    WordlibBO lib=new WordlibBO();
		    Global go=new Global();
		    DynaActionForm dy=(DynaActionForm)form;
		    Pagination pagination = new Pagination(); 
		    String page =(String)request.getParameter("page"); 
		    Ouser user=(Ouser)request.getSession().getAttribute("user");
			try{
			   Search_Card_Batteries cbatteries=(Search_Card_Batteries)dy.get("ser_card_batteries");
			   pagination.setCount(200); 
		    	if (page != null && page.length() > 0) {
			        pagination.setPage(Integer.parseInt(page));
			      }else {
			        pagination.setPage(1);
			      } 
			   Calendar cal=Calendar.getInstance();//获取当前时间
			   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
			   String etime=format.format(cal.getTime());
		 
		       String stime=go.getDay(etime,-7);
			   cbatteries.setStime(stime+" 00:00:00");
			   cbatteries.setEtime(etime+" 23:59:59");
			   
			   cbatteries.setUserid(user.getUserid());
			   List<CardBatteriesVO> list=bo.init(cbatteries,pagination);
			   
			   
		       request.setAttribute("listCount", list.size());
		       if( list.size() ==0){
		        	   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
		        }else{
			          request.setAttribute("Card_batteries_List",list); 
			          request.getSession().setAttribute("cbatteries", cbatteries);
		        }
			   
			   List dep=lib.yaoqiaoDept(user.getUserid());
			   request.setAttribute("dep_list",dep);
			   request.setAttribute("pagination", pagination);//分页
			   dy.set("ser_card_batteries",cbatteries);
			   
			}catch(Exception e){
				log.error("==卡电池报警查询==",e);
			}
		    return mapping.findForward("basicInit");
      }

	/**定位卡电池报警报表*/
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		CardBatteriesBO bo=new CardBatteriesBO();
	    WordlibBO lib=new WordlibBO();
	    DynaActionForm dy=(DynaActionForm)form;
		Pagination pagination = new Pagination(); 
	    String page =(String)request.getParameter("page"); 
	    Ouser user=(Ouser)request.getSession().getAttribute("user");
		try{
			Search_Card_Batteries cbatteries=(Search_Card_Batteries)dy.get("ser_card_batteries");
			request.getSession().setAttribute("ktime", cbatteries.getStime());
			request.getSession().setAttribute("jtime", cbatteries.getEtime());
	    	pagination.setCount(200); 
	    	if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      } 
	    	cbatteries.setUserid(user.getUserid());
	    	List<CardBatteriesVO> list=bo.init(cbatteries,pagination);
			   
	         request.setAttribute("listCount", list.size());
	         if( list.size() ==0){
	        	   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
	         }else{
		          request.setAttribute("Card_batteries_List",list); 
		          request.getSession().setAttribute("cbatteries", cbatteries);
	         }
	        
			 String  userid=user.getUserid();
			 List dep=lib.yaoqiaoDept(user.getUserid());
			 request.setAttribute("dep_list",dep);
		     request.setAttribute("pagination", pagination);//分页
		     dy.set("ser_card_batteries",cbatteries);
		}catch(Exception e){
			log.error("==卡电池报警查询==",e);
		}
			return mapping.findForward("basicShow");
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
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("定位卡电池报警","UTF-8")+".xls");
				// 定义输出类型
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("定位卡电池报警",10);
				//	设置部分列的宽度
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 20);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 20);
				ws.setColumnView(5, 20);
				ws.setColumnView(6, 30);
				ws.setColumnView(7, 30);
				//	设置表头
				ws.addCell(new Label(0,0,"卡号"));
				ws.addCell(new Label(1,0,"姓名"));
				ws.addCell(new Label(2,0,"部门"));
				ws.addCell(new Label(3,0,"班组"));
				ws.addCell(new Label(4,0,"报警信息"));
				ws.addCell(new Label(5,0,"开始报警时间"));
				ws.addCell(new Label(6,0,"最后报警时间"));
				
				CardBatteriesBO bo=new CardBatteriesBO();
				DynaActionForm dy=(DynaActionForm)form;
				Search_Card_Batteries cbatteries=(Search_Card_Batteries)request.getSession().getAttribute("cbatteries");
				List<CardBatteriesVO> relist=bo.initPrint(cbatteries);
				
				List list = relist;
				//	填充数据
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						CardBatteriesVO v=(CardBatteriesVO) list.get(i);
						ws.addCell(new Label(0,i+1,v.getCardid()));
						ws.addCell(new Label(1,i+1,v.getUsername()));
						ws.addCell(new Label(2,i+1,v.getDep()));
						ws.addCell(new Label(3,i+1,v.getGro()));
						ws.addCell(new Label(4,i+1,v.getInfo()));
						ws.addCell(new Label(5,i+1,v.getMintime()));
						ws.addCell(new Label(6,i+1,v.getMaxtime()));
					}
				}
				wwb.write();
				wwb.close();
				os.close();
				
			}
			catch(Exception e){
				log.error("==定位卡电池报警-导出Excel==",e);
			}
		return null;
	}
	
	/**
	  * 初始化时间和班组
	  * */
	public ActionForward getCallAlarm(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			CardBatteriesBO bo=new CardBatteriesBO();
		    WordlibBO lib=new WordlibBO();
		    Global go=new Global();
		    DynaActionForm dy=(DynaActionForm)form;
		    Pagination pagination = new Pagination(); 
		    String page =(String)request.getParameter("page");
		    Ouser user=(Ouser)request.getSession().getAttribute("user");
			try{
			   Search_Card_Batteries cbatteries=(Search_Card_Batteries)dy.get("ser_card_batteries");
			   pagination.setCount(200); 
		    	if (page != null && page.length() > 0) {
			        pagination.setPage(Integer.parseInt(page));
			      }else {
			        pagination.setPage(1);
			      } 
			   Calendar cal=Calendar.getInstance();//获取当前时间
			   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
			   String etime=format.format(cal.getTime());
		 
		       String stime=go.getDay(etime,-7);
			   cbatteries.setStime(stime+" 00:00:00");
			   cbatteries.setEtime(etime+" 23:59:59");
			   
			   cbatteries.setUserid(user.getUserid());
			   List<CardBatteriesVO> list=bo.initCall(cbatteries,pagination);
			   
			   
		       request.setAttribute("listCount", list.size());
		       if( list.size() ==0){
		        	   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
		        }else{
			          request.setAttribute("Card_call_List",list); 
			          request.getSession().setAttribute("call", cbatteries);
		        }
			   List dep=lib.yaoqiaoDept(user.getUserid());
			   request.setAttribute("dep_list",dep);
			   request.setAttribute("pagination", pagination);//分页
			   dy.set("ser_card_batteries",cbatteries);
			   
			}catch(Exception e){
				log.error("==定位卡呼救报警==",e);
			}
		    return mapping.findForward("basicInitCall");
     }

	/**定位卡呼救报警报表*/
	public ActionForward getListCall(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		CardBatteriesBO bo=new CardBatteriesBO();
	    WordlibBO lib=new WordlibBO();
	    DynaActionForm dy=(DynaActionForm)form;
		Pagination pagination = new Pagination(); 
	    String page =(String)request.getParameter("page"); 
	    Ouser user=(Ouser)request.getSession().getAttribute("user");
		try{
			Search_Card_Batteries cbatteries=(Search_Card_Batteries)dy.get("ser_card_batteries");
			request.getSession().setAttribute("ktime", cbatteries.getStime());
			request.getSession().setAttribute("jtime", cbatteries.getEtime());
	    	pagination.setCount(200); 
	    	if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      } 
	    	cbatteries.setUserid(user.getUserid());
	    	List<CardBatteriesVO> list=bo.initCall(cbatteries,pagination);
			   
	         request.setAttribute("listCount", list.size());
	         if( list.size() ==0){
	        	   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
	         }else{
		          request.setAttribute("Card_call_List",list); 
		          request.getSession().setAttribute("call", cbatteries);
	         }
			 String  userid=user.getUserid();
			 List dep=lib.yaoqiaoDept(user.getUserid());
			 request.setAttribute("dep_list",dep);
		     request.setAttribute("pagination", pagination);//分页
		     dy.set("ser_card_batteries",cbatteries);
		}catch(Exception e){
			log.error("==定位卡呼救报警==",e);
		}
			return mapping.findForward("basicShowCall");
	}

//	导出Excel
	public ActionForward doOpenExcelCall(
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
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("定位卡呼救报警","UTF-8")+".xls");
				// 定义输出类型
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("定位卡呼救报警",10);
				//	设置部分列的宽度
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 20);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 20);
				ws.setColumnView(5, 20);
				ws.setColumnView(6, 30);
				ws.setColumnView(7, 30);
				//	设置表头
				ws.addCell(new Label(0,0,"卡号"));
				ws.addCell(new Label(1,0,"姓名"));
				ws.addCell(new Label(2,0,"部门"));
				ws.addCell(new Label(3,0,"班组"));
				ws.addCell(new Label(4,0,"报警信息"));
				ws.addCell(new Label(5,0,"开始报警时间"));
				ws.addCell(new Label(6,0,"最后报警时间"));
				
				CardBatteriesBO bo=new CardBatteriesBO();
				DynaActionForm dy=(DynaActionForm)form;
				Search_Card_Batteries cbatteries=(Search_Card_Batteries)request.getSession().getAttribute("call");
				List<CardBatteriesVO> relist=bo.initPrintCall(cbatteries);
				
				List list = relist;
				//	填充数据
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						CardCallVO v=(CardCallVO) list.get(i);
						ws.addCell(new Label(0,i+1,v.getCardid()));
						ws.addCell(new Label(1,i+1,v.getUsername()));
						ws.addCell(new Label(2,i+1,v.getDep()));
						ws.addCell(new Label(3,i+1,v.getGro()));
						ws.addCell(new Label(4,i+1,v.getInfo()));
						ws.addCell(new Label(5,i+1,v.getMintime()));
						ws.addCell(new Label(6,i+1,v.getMaxtime()));
					}
				}
				wwb.write();
				wwb.close();
				os.close();
				
			}
			catch(Exception e){
				log.error("==定位卡呼救报警-导出Excel==",e);
			}
		return null;
	}
	
}
