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

import com.basic.entity.bo.DepartmentMenologyBO;
import com.basic.entity.vo.DepartmentMenologyVO;
import com.kj133.entity.Ouser;
import com.kj133.entity.Search_Department_menology;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

public class DepartmentMenologyAction extends DispatchAction{
	
	
	private final Logger  log=Logger.getLogger(this.getClass());
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  部门考勤月报
	 * @param response
	 * @return ActionForward
	 */
	
	/**
     * 初始化时间、班组、部门
     * */
	public ActionForward initialization(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        DynaActionForm dy=(DynaActionForm)form;
	        WordlibBO lib=new WordlibBO();
	        Calendar cal=Calendar.getInstance();//获取当前时间
			SimpleDateFormat format =new SimpleDateFormat("yyyy-MM");
			String etime=format.format(cal.getTime());
	        
	         try{
		        	 Search_Department_menology men=(Search_Department_menology)dy.get("department_menology");
		        	 men.setStime(etime);
		        	 Ouser user =(Ouser)request.getSession().getAttribute("user");
		        	 List gro=lib.group(user);
		        	 List dep=lib.yaoqiaoDept(user.getUserid());
		        	 request.setAttribute("gro_list",gro);
		        	 request.setAttribute("dep_list",dep);
		        	 dy.set("department_menology",men);
		        	
                     request.setAttribute("listCount", "0");
	         }catch(Exception e){
	        	 log.error("==部门考勤月报==",e);
	         }
			return mapping.findForward("showbasic");
		}
	
	/**部门考勤月报查询*/
	public ActionForward getList(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm)form;
	        WordlibBO lib=new WordlibBO();
	        DepartmentMenologyBO bo=new DepartmentMenologyBO();
	        Pagination pagination = new Pagination(); 
		    String page =(String)request.getParameter("page");
	        Global go=new Global();
	        try{
		        	Search_Department_menology men=(Search_Department_menology)dy.get("department_menology");
		        	request.getSession().setAttribute("yuefen",men.getStime());
		            request.getSession().setAttribute("dayCount", go.getDaysOfMonth(men.getStime()));
		        	Ouser user =(Ouser)request.getSession().getAttribute("user");
		        	men.setUserid(user.getUserid());
		        	
		        	pagination.setCount(200); 
			    	if (page != null && page.length() > 0) {
				        pagination.setPage(Integer.parseInt(page));
				      }else {
				        pagination.setPage(1);
				      } 
		        	List relist= bo.getList(men,pagination);
		        	request.setAttribute("listCount", relist.size());
		        	if( relist.size()== 0){
		        		   ActionMessages messages=new ActionMessages();
					       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
					       this.saveMessages(request,messages);
		        	 }else{
		        		 request.setAttribute("men",relist);
		        		 request.getSession().setAttribute("department_menology", men);
		        		 request.getSession().setAttribute("department_menologyPrint", relist);
		        	 }
		        	List gro=lib.group(user);
		        	List dep=lib.yaoqiaoDept(user.getUserid());
		        	request.setAttribute("gro_list",gro);
		        	request.setAttribute("dep_list",dep);
		        	request.setAttribute("pagination", pagination);
		        	dy.set("department_menology",men);
	        }catch(Exception e){
	        	log.error("==部门考勤月报==",e);
	        }
			return mapping.findForward("showbasic");
		}
	
	
//	导出Excel
	public ActionForward doOpenExcel(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response
			){
		
			try{
				
				DynaActionForm dy=(DynaActionForm)form;
		        DepartmentMenologyBO bo=new DepartmentMenologyBO();
		        Global go=new Global();
		        
		        Search_Department_menology men=(Search_Department_menology)dy.get("department_menology");
	        	request.getSession().setAttribute("yuefen",men.getStime());
	            request.getSession().setAttribute("dayCount", go.getDaysOfMonth(men.getStime()));
	        	Ouser user =(Ouser)request.getSession().getAttribute("user");
	        	men.setUserid(user.getUserid());
		        
	        	List list = bo.getList(men);
	        	
				//创建导入格式表文件
				OutputStream os=response.getOutputStream();
				// 清空输出流
				response.reset();
				// 设置响应头和下载保存的文件名
//				response.setHeader("content-disposition","attachment;filename=bianyiyuangongxinxi.xls");
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("部门考勤月报","UTF-8")+".xls");
				// 定义输出类型
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("部门考勤月报",11);
				//	设置部分列的宽度
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 20);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 10);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 10);
				ws.setColumnView(10, 10);
				ws.setColumnView(11, 10);
				//	设置表头
				ws.addCell(new Label(0,0,"姓名"));
				ws.addCell(new Label(1,0,"卡号"));
				ws.addCell(new Label(2,0,"部门"));
				ws.addCell(new Label(3,0,"班制"));
				ws.addCell(new Label(4,0,"早班"));
				ws.addCell(new Label(5,0,"中班"));
				ws.addCell(new Label(6,0,"晚班"));
				ws.addCell(new Label(7,0,"实井数"));
				ws.addCell(new Label(8,0,"虚井数"));
				ws.addCell(new Label(9,0,"总数"));
				ws.addCell(new Label(10,0,"总时长"));

				//	填充数据
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						DepartmentMenologyVO v=(DepartmentMenologyVO) list.get(i);
						ws.addCell(new Label(0,i+1,v.getName()));
						ws.addCell(new Label(1,i+1,v.getCardid()));
						ws.addCell(new Label(2,i+1,v.getDepartment()));
						ws.addCell(new Label(3,i+1,v.getBantypename()));
						ws.addCell(new Label(4,i+1,v.getZao()));
						ws.addCell(new Label(5,i+1,v.getZhong()));
						ws.addCell(new Label(6,i+1,v.getWan()));
						ws.addCell(new Label(7,i+1,v.getOvertimecountsg()));
						ws.addCell(new Label(8,i+1,v.getOvertimecountxg()));
						ws.addCell(new Label(9,i+1,v.getCountall()));
						ws.addCell(new Label(10,i+1,v.getWorkalltime()));
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
