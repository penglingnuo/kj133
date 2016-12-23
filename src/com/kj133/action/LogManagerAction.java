//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_LogManager;
import com.kj133.entity.Ssearch_LogManager;
import com.kj133.entity.bo.LogManagerBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.entity.vo.LogManagerVO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class LogManagerAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  日志管理
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
        
        Ssearch_LogManager slogmanager = new Ssearch_LogManager();
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	      Date date = new java.util.Date();
	      Calendar c = Calendar.getInstance();
	      c.add(Calendar.DATE, -7);   //此处可以变成年，月，日，分等
	      String stime = format.format( c.getTime());
	      String etime = format.format(date);
		String page =(String)request.getParameter("page");//获取页号
		LogManagerBO bo=new LogManagerBO();
        WordlibBO lib=new WordlibBO();
	    try{
	    	Search_LogManager  logmanager=(Search_LogManager)dy.getMap().get("ser_logmanager");
	    	slogmanager.setStime(logmanager.getStime());
	    	slogmanager.setEtime(logmanager.getEtime());
	    	slogmanager.setMinstime(logmanager.getMinstime());
	    	slogmanager.setMaxstime(logmanager.getMaxstime());
	    	slogmanager.setName(logmanager.getName());
	    	slogmanager.setUserid(logmanager.getUserid());
	    	request.getSession().setAttribute("slogmanager", slogmanager);
	        	 pagination.setCount(100); 
			     if (page != null && page.length() > 0) {
			        pagination.setPage(Integer.parseInt(page));
			      }else {
			        pagination.setPage(1);
			      }
	        	 List list=bo.init(logmanager,pagination);
	        	 List relist=bo.init1(logmanager);
	        	 
	        	 request.setAttribute("listCount", list.size());
	        	 if( list.size()== 0){
	        		   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
	        	 }else{
	        		 request.setAttribute("logmanager_list",list);
	        		 request.getSession().setAttribute("logmanager_listPrint", relist);//结果集
	        	 }
	        	 if(logmanager.getStime()==null){
	        		 logmanager.setStime(stime);
					      
					}else{
						logmanager.setStime(logmanager.getStime());
						request.setAttribute("name1", "true");
					}if(logmanager.getEtime()==null){
						logmanager.setEtime(etime);
					}else{
						logmanager.setEtime(logmanager.getEtime());
						request.setAttribute("name3", "true");
					}
					
					if(logmanager.getMinstime()==null){
						logmanager.setMinstime("15:47:51");
					}else{
						logmanager.setMinstime(logmanager.getMinstime());
						request.setAttribute("name2", "true");
					}
					
					if(logmanager.getMaxstime()==null){
						logmanager.setMaxstime("15:47:51");
					}else{
						logmanager.setMaxstime(logmanager.getMaxstime());
						request.setAttribute("name4", "true");
					}
	        	 List userid=lib.userid(); 
				 request.setAttribute("userid_list",userid);
	        	 request.setAttribute("pagination", pagination);
	        	 dy.set("ser_logmanager",logmanager);
	        }catch(Exception e){
	        	log.error("==日志管理==",e);
	        }
			return mapping.findForward("show");
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
				response.setHeader("content-disposition",
						"attachment;filename=rizhi.xls");
				// 定义输出类型
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("日志管理",10);
				//	设置部分列的宽度
				ws.setColumnView(1, 35);
				ws.setColumnView(2, 20);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 20);
				ws.setColumnView(5, 100);
				//	设置表头
				ws.addCell(new Label(0,0,"记录号"));
				ws.addCell(new Label(1,0,"时间"));
				ws.addCell(new Label(2,0,"用户号"));
				ws.addCell(new Label(3,0,"用户名"));
				ws.addCell(new Label(4,0,"操作"));
				ws.addCell(new Label(5,0,"详细情况"));
				
				List list = (ArrayList)request.getSession().getAttribute("logmanager_listPrint");
				//	填充数据
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						LogManagerVO  v= (LogManagerVO)list.get(i);
						ws.addCell(new Label(0,i+1,v.getRecordid()));
						ws.addCell(new Label(1,i+1,v.getMydate()));
						ws.addCell(new Label(2,i+1,v.getUserid()));
						ws.addCell(new Label(3,i+1,v.getName()));
						ws.addCell(new Label(4,i+1,v.getMyaction()));
						ws.addCell(new Label(5,i+1,v.getLoginfo()));
					}
				}
				wwb.write();
				wwb.close();
				os.close();
				
			}
			catch(RowsExceededException e){
				log.error("==日志管理==",e);
			}
			catch(WriteException ex){
				log.error("==日志管理==",ex);
			}
			catch(IOException e){
				log.error("==日志管理==",e);
			}
		return null;
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
				  Search_LogManager logmanager=(Search_LogManager)dy.getMap().get("ser_logmanager");
				   Calendar cal=Calendar.getInstance();//获取当前时间
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				   String etime=format.format(cal.getTime());
			 
			       String stime=go.getDay(etime,-7);   
			       logmanager.setStime(stime);
			       logmanager.setEtime(etime);
			       logmanager.setMinstime("15:47:51");
			       logmanager.setMaxstime("15:47:51");

				   List userid=lib.userid(); 
				   request.setAttribute("userid_list",userid);
				   request.setAttribute("listCount", "0");
				   dy.set("ser_logmanager",logmanager);
				}catch(Exception e){
					log.error("==日志管理==",e);
				}
			    return mapping.findForward("wordlib");
			 }
	/**
	 * 删除(delete)
	 * */
	public ActionForward delete(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		Search_LogManager alogmanager=(Search_LogManager)dy.get("ser_logmanager");
		Ssearch_LogManager logmanager = (Ssearch_LogManager)request.getSession().getAttribute("slogmanager");
	    LogManagerBO bo=new LogManagerBO();
	     try{
	    	 bo.Delete(logmanager);
	    	 ActionMessages messages = new ActionMessages();
	    	 messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.delete.log"));
	    	 saveMessages(request, messages);
	    	 dy.set("ser_logmanager",alogmanager);
 	 
	     }catch(Exception e){
	    	 log.error("==删除日志管理==",e);
	     }
		 return mapping.findForward("delete");
	  
	}

}

