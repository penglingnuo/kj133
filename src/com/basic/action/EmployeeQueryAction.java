package com.basic.action;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
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

import com.basic.entity.bo.EmployeeBO;
import com.kj133.entity.Ouser;
import com.kj133.entity.Search_Employee;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.entity.vo.EmployeeVO;

public class EmployeeQueryAction extends DispatchAction{
	
	/*
	 * Generated Methods
	 */
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  查询员工
	 * @param response
	 * @return ActionForward
	 */
	/**
	 * 初始化班组
	 * */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm)form;
	        WordlibBO bo=new WordlibBO();
	        EmployeeBO pbo=new EmployeeBO();
	        Pagination pagination = new Pagination();//分页
			String page =(String)request.getParameter("page");//获取页号分页
			Ouser user=(Ouser)request.getSession().getAttribute("user");
		        try{
		        	Search_Employee employee=(Search_Employee)dy.get("search_employee");
			    	employee.setUserid(user.getUserid());
		        	pagination.setCount(200);//F
			    	 if (page != null && page.length() > 0) {
				        pagination.setPage(Integer.parseInt(page)); 
				      }else {
				        pagination.setPage(1);
				      } 
				   List gro=bo.deptSta(user.getUserid());
				   List stafferList=pbo.getStaffer(employee,pagination);
				   List plist=pbo.getStafferPrint(employee);
				   request.setAttribute("gro_list", gro);
				   request.setAttribute("listCount", stafferList.size());//用于页面上判断打印预览是否可用
					 if( stafferList.size() == 0){
						   ActionMessages messages=new ActionMessages();
					       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
					       this.saveMessages(request,messages);
					 }else{
					       request.setAttribute("stafferList",stafferList);
					       request.setAttribute("pagination", pagination);
					       request.getSession().setAttribute("queryEmployeePrint", plist);
					       request.getSession().setAttribute("employee", employee);
					  }
		        }catch(Exception e){
		        	log.error("==查询员工==",e);
		        }
		        
		        return mapping.findForward("employeebasic");
	}
	/**
	 * 查询
	 * */
	public ActionForward getStaffer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	        DynaActionForm dy=(DynaActionForm)form;
	        /**
	         * 获得当前用户
	         */
	        Ouser user=(Ouser)request.getSession().getAttribute("user");
			EmployeeBO bo=new EmployeeBO();
		    WordlibBO wordlib=new WordlibBO();
	        Pagination pagination = new Pagination();//分页
			String page =(String)request.getParameter("page");//获取页号分页
		    try{
		    	Search_Employee employee=(Search_Employee)dy.get("search_employee");
		    	employee.setUserid(user.getUserid());
		    	 pagination.setCount(200);
		    	 if (page != null && page.length() > 0) {
			        pagination.setPage(Integer.parseInt(page)); 
			      }else {
			        pagination.setPage(1);
			      } 
				 List stafferList=bo.getStaffer(employee, pagination);
				 request.setAttribute("listCount", stafferList.size());//用于页面上判断打印预览是否可用
				 if( stafferList.size() == 0){
					   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
				 }else{
				       request.setAttribute("stafferList",stafferList);
				       request.getSession().setAttribute("employee", employee);
				  }
				 request.setAttribute("pagination", pagination);
				 List gro=wordlib.deptSta(user.getUserid());
				 request.setAttribute("gro_list", gro);
				 request.setAttribute("pagination", pagination);//分页
			     dy.set("search_employee", employee);
				  
			}catch(Exception e){
				log.error("==查询员工==",e);
			}
			 return mapping.findForward("employeebasic");
	}
	

//	导出Excel
	public ActionForward doOpenExcel(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response
			){

//	    	List stafferList=bo.getStaffer(employee, null);
	    	
			try{
				//创建导入格式表文件
				OutputStream os=response.getOutputStream();
				// 清空输出流
				response.reset();
				// 设置响应头和下载保存的文件名
//				response.setHeader("content-disposition","attachment;filename=bianyiyuangongxinxi.xls");
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("查询员工信息","UTF-8")+".xls");
				// 定义输出类型
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("查询员工信息",10);
				//	设置部分列的宽度
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 20);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 20);
				ws.setColumnView(6, 20);
				ws.setColumnView(7, 25);
				//	设置表头
				ws.addCell(new Label(0,0,"姓名"));
				ws.addCell(new Label(1,0,"卡号"));
				ws.addCell(new Label(2,0,"部门"));
				ws.addCell(new Label(3,0,"班组"));
				ws.addCell(new Label(4,0,"工种"));
				ws.addCell(new Label(5,0,"职务"));
				
//				EmployeeBO bo=new EmployeeBO();
//				Search_Employee employee=(Search_Employee)request.getSession().getAttribute("employee");
//				List listAll=bo.getStafferPrint(employee);
				
				List list = _init_data(form, request);
				//	填充数据
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						EmployeeVO  v= (EmployeeVO)list.get(i);
						ws.addCell(new Label(0,i+1,v.getUsername()));
						ws.addCell(new Label(1,i+1,v.getCardid()));
						ws.addCell(new Label(2,i+1,v.getDepartment()));
						ws.addCell(new Label(3,i+1,v.getGro()));
						ws.addCell(new Label(4,i+1,v.getWorktype()));
						ws.addCell(new Label(5,i+1,v.getOccupation()));
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
private List _init_data(ActionForm form, HttpServletRequest request)
		throws Exception {
	DynaActionForm dy=(DynaActionForm)form;
	Ouser user=(Ouser)request.getSession().getAttribute("user");
	Search_Employee employee=(Search_Employee)dy.get("search_employee");
	employee.setUserid(user.getUserid());
	EmployeeBO bo=new EmployeeBO();
	List list = bo.getStafferPrint(employee);
	return list;
}
	
	public ActionForward doPrint(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response
			){
		try {
			List list = _init_data(form, request);
			request.setAttribute("over_ListPrint", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("vprint");
	}
}
