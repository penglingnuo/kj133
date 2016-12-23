package com.basic.action;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.speedframework.entity.Pagination;

import com.basic.entity.Search_WorkAtt;
import com.basic.entity.bo.WorkAttBO;
import com.basic.entity.vo.WorkAttAreaAddVO;
import com.basic.entity.vo.WorkAttAreaVO;
import com.basic.entity.vo.WorkAttVO;
import com.kj133.entity.Ouser;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

public class WorkAttAction extends DispatchAction{
	
	/**
     * 初始化 班组
     * */
	public ActionForward initialization(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        DynaActionForm dy=(DynaActionForm)form;
	        WordlibBO lib=new WordlibBO();
	        WorkAttBO bo=new WorkAttBO();
	        Pagination pagination = new Pagination(); 
	        Ouser user =(Ouser)request.getSession().getAttribute("user");
		    String page =(String)request.getParameter("page");
	        
	         try{
		        	 Search_WorkAtt workatt=(Search_WorkAtt)dy.get("search_workatt");
		        	 pagination.setCount(200); 
				    	if (page != null && page.length() > 0) {
					        pagination.setPage(Integer.parseInt(page));
					      }else {
					        pagination.setPage(1);
					      } 
				    	workatt.setUserid(user.getUserid());
				    	List list=bo.init(workatt,pagination);
				    	WorkAttVO attVO=(WorkAttVO) bo.count(workatt).get(0);
				    	int total=attVO.getTotal();
				    	
				    	request.setAttribute("listCount", list.size());
				    	if(total>0){
				    		request.setAttribute("countall", total);
				    	}else{
				    		request.setAttribute("countall", 0);
				    	}
				    	
					       if( list.size() ==0){
					        	   ActionMessages messages=new ActionMessages();
							       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
							       this.saveMessages(request,messages);
					        }else{
						          request.setAttribute("workatt_list",list); 
						          request.getSession().setAttribute("workatt", workatt);
					        }
		        	 
		        	 List gro=lib.deptSta(user.getUserid());
		        	 request.setAttribute("gro_list",gro);
		        	 request.setAttribute("pagination", pagination);
		        	 dy.set("search_workatt",workatt);
		        	
                     request.setAttribute("listCount", "0");
	         }catch(Exception e){
	        	 log.error("==实时信息==",e);
	         }
			return mapping.findForward("showbasic");
		}

	/**条件查询*/
	public ActionForward getList(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        DynaActionForm dy=(DynaActionForm)form;
	        WordlibBO lib=new WordlibBO();
	        WorkAttBO bo=new WorkAttBO();
	        Pagination pagination = new Pagination(); 
	        Ouser user =(Ouser)request.getSession().getAttribute("user");
		    String page =(String)request.getParameter("page");
		    
	         try{
		        	 Search_WorkAtt workatt=(Search_WorkAtt)dy.get("search_workatt");
		        	 pagination.setCount(200); 
				    	if (page != null && page.length() > 0) {
					        pagination.setPage(Integer.parseInt(page));
					      }else {
					        pagination.setPage(1);
					      } 
				    	workatt.setUserid(user.getUserid());
				    	List list=bo.getList(workatt, pagination);
				    	WorkAttVO attVO=(WorkAttVO) bo.count(workatt).get(0);
				    	int total=attVO.getTotal();
				    	request.setAttribute("listCount", list.size());
				    	request.setAttribute("listCount", list.size());
				    	if(total>0){
				    		request.setAttribute("countall", total);
				    	}else{
				    		request.setAttribute("countall", 0);
				    	}
					       if( list.size() ==0){
					        	   ActionMessages messages=new ActionMessages();
							       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
							       this.saveMessages(request,messages);
					        }else{
						          request.setAttribute("workatt_list",list); 
						          request.getSession().setAttribute("workatt", workatt);
					        }
		        	 
					List gro=lib.deptSta(user.getUserid());
		        	 request.setAttribute("gro_list",gro);
		        	 request.setAttribute("pagination", pagination);
		        	 dy.set("search_workatt",workatt);
		        	
	         }catch(Exception e){
	        	 log.error("==实时信息==",e);
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
				//创建导入格式表文件
				OutputStream os=response.getOutputStream();
				// 清空输出流
				response.reset();
				// 设置响应头和下载保存的文件名
//				response.setHeader("content-disposition","attachment;filename=bianyiyuangongxinxi.xls");
				response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("实时信息","UTF-8")+".xls");
				// 定义输出类型
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("实时信息",8);
				//	设置部分列的宽度
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 30);
				ws.setColumnView(4, 15);
				ws.setColumnView(5, 25);
				ws.setColumnView(6, 10);
				ws.setColumnView(7, 20);
				ws.setColumnView(8, 25);
				//	设置表头
				ws.addCell(new Label(0,0,"姓名"));
				ws.addCell(new Label(1,0,"卡号"));
				ws.addCell(new Label(2,0,"班组"));
				ws.addCell(new Label(3,0,"职务"));
				ws.addCell(new Label(4,0,"下井时间"));
				ws.addCell(new Label(5,0,"工作时长"));
				ws.addCell(new Label(6,0,"当前分站"));
				ws.addCell(new Label(7,0,"进入分站时间"));
				
				WorkAttBO bo=new WorkAttBO();
				DynaActionForm dy=(DynaActionForm)form;
				Search_WorkAtt workatt=(Search_WorkAtt)request.getSession().getAttribute("workatt");
				List list= bo.getListExecl(workatt);
				
				//	填充数据
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						WorkAttVO v=(WorkAttVO) list.get(i);
						ws.addCell(new Label(0,i+1,v.getName()));
						ws.addCell(new Label(1,i+1,v.getCardid()));
						ws.addCell(new Label(2,i+1,v.getGroup()));
						ws.addCell(new Label(3,i+1,v.getOccupation()));
						ws.addCell(new Label(4,i+1,v.getDowntime()));
						ws.addCell(new Label(5,i+1,v.getWorktime()));
						ws.addCell(new Label(6,i+1,v.getCrname()));
						ws.addCell(new Label(7,i+1,v.getStarttime()));
					}
				}
				wwb.write();
				wwb.close();
				os.close();
				
			}
			catch(Exception e){
				log.error("==实时信息-导出Excel==",e);
			}
		return null;
	}
	
	/**轨迹列表---实时----
	 * @throws UnsupportedEncodingException */
	public ActionForward getAll(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
	        DynaActionForm dy=(DynaActionForm)form;
	        WorkAttBO bo=new WorkAttBO();
	        JSONObject jsonObject = new JSONObject();

	        String cardid=(String)request.getParameter("cardid");
		    String downtime=(String)request.getParameter("downtime");
		    String note="";
	         try{
	        	 		List liststa=bo.getStafferList(cardid,downtime);
				    	List list=bo.getAll(cardid,downtime);
				    	List listadd=bo.getAllAdd(cardid, downtime);
				    	List<WorkAttAreaAddVO> listnew = new ArrayList();
					       if( liststa.size() ==0 || list.size()==0){
					        	   ActionMessages messages=new ActionMessages();
							       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
							       this.saveMessages(request,messages);
							       note="该员工刚下井，没有轨迹！";
					        }else{
					        	//得到停留表中的最后一个分站的离开时间，用于最后一条记录的进入分站时间
					        	Global glo= new Global();
					        	
					        	String starttime="";
					        	if( !(listadd.size() ==0 || liststa.size()==0)){
					        		Iterator iter = list.iterator();
					   	         	while (iter.hasNext()) {
					   	         	WorkAttAreaVO waavo = (WorkAttAreaVO) iter.next();
					   	         	starttime = waavo.getEndtime();
					   	         }
					   	         	Iterator iteradd = listadd.iterator();
					   	         	while (iteradd.hasNext()) {
					   	         	WorkAttAreaAddVO waaaddvo = (WorkAttAreaAddVO) iteradd.next();
					   	         	waaaddvo.setStarttime(starttime);
					   	         	String endtime=waaaddvo.getEndtime();
					   	         	String times=glo.getTimeBetween(endtime,starttime);
					   	         	waaaddvo.setStayinterval(times);
					   	         	list.add(waaaddvo);
					   	         }
						    	}
					        	jsonObject.put("workatt_list",list);
					        	jsonObject.put("workatt_list_staffer",liststa);
					        }
					       jsonObject.put("note",note);
					       response.setCharacterEncoding("UTF-8");
						   response.setContentType("application/json;charset=UTF-8");
						   response.getWriter().println(jsonObject.toString());
		        	
	         }catch(Exception e){
	        	 log.error("==轨迹列表---实时==",e);
	         }
			return null;
		}
}
