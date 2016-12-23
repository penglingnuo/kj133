package com.basic.action;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.speedframework.entity.Pagination;

import com.basic.entity.bo.ReaderBO;
import com.basic.entity.vo.CardReaderVo;
import com.kj133.entity.Search_ViewReader;

public class ReaderQueryAction extends DispatchAction{
	/**
	 * 查询分站信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	
	/**查询所有分站*/
	public ActionForward getList(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	    	Pagination pagination=new Pagination(); 
	    	DynaActionForm dy=(DynaActionForm)form;
	    	String page =(String)request.getParameter("page");//获取页号
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		      Date date = new java.util.Date();
		      Calendar c = Calendar.getInstance();
		      c.add(Calendar.DATE, -7);   //此处可以变成年，月，日，分等
		      String stime = format.format( c.getTime());
		      String etime = format.format(date);
	        ReaderBO bo=new ReaderBO();
	        try{
	        	Search_ViewReader viewreader=(Search_ViewReader)dy.getMap().get("ser_viewreader");//取出页面的数据
	        	String cid=viewreader.getCid();
	        	String checkreader=viewreader.getCheckreader();
	        	if ("全部".equals(checkreader) ) {
	        		checkreader="";
			      }
	        	pagination.setCount(100);// 
			      if (page != null && page.length() > 0) {
			        pagination.setPage(Integer.parseInt(page));
			      }else {
			        pagination.setPage(1);  
			    }   
	        	List list=bo.init(cid,checkreader,pagination);
	        	if( list.size()== 0){
	        		   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
	        	}else{
	        		  request.setAttribute("ViewReaderList",list);
	        	}
	        	request.setAttribute("pagination", pagination);
	        	dy.set("ser_viewreader",viewreader);//重新设置回页面	 
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
         return mapping.findForward("basicReader");
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
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("分站信息","UTF-8")+".xls");
				// 定义输出类型
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("分站信息",8);
				//	设置部分列的宽度
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 20);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 10);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 25);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 25);
				//	设置表头
				ws.addCell(new Label(0,0,"分站号"));
				ws.addCell(new Label(1,0,"分站名"));
				ws.addCell(new Label(2,0,"简称"));
				ws.addCell(new Label(3,0,"地图X坐标"));
				ws.addCell(new Label(4,0,"地图Y坐标"));
				ws.addCell(new Label(5,0,"注册时间"));
				ws.addCell(new Label(6,0,"使用状态"));
				ws.addCell(new Label(7,0,"最后修改时间"));
				
				ReaderBO bo=new ReaderBO();
				
				List list =bo.initPrint();
				//	填充数据
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						CardReaderVo  v= (CardReaderVo)list.get(i);
						ws.addCell(new Label(0,i+1,v.getCardreaderid()));
						ws.addCell(new Label(1,i+1,v.getCrname()));
						ws.addCell(new Label(2,i+1,v.getShortname()));
						ws.addCell(new Label(3,i+1,v.getX()));
						ws.addCell(new Label(4,i+1,v.getY()));
						ws.addCell(new Label(5,i+1,v.getRegdate()));
						ws.addCell(new Label(6,i+1,v.getState()));
						ws.addCell(new Label(7,i+1,v.getModifydate()));
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
