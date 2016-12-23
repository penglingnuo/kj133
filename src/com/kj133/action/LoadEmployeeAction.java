//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.kj133.entity.AddEmployee_Form;
import com.kj133.entity.Ouser;
import com.kj133.entity.Staffer;
import com.kj133.entity.bo.EmployeeBO;
import com.kj133.entity.bo.WordlibBO;

/** 
 * MyEclipse Struts
 * Creation date: 03-24-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoadEmployeeAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	
	 /**
	  * 修改员工信息
	  * */
	public ActionForward update(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			EmployeeBO bo=new EmployeeBO();
			DynaActionForm dy=(DynaActionForm)form;
			String names = request.getParameter("names");
			try{
				   AddEmployee_Form emp=(AddEmployee_Form)dy.get("addEmployee");
				   String cid=emp.getCardid();
				   String sid=emp.getStafferid();
				   bo.upda(emp,cid,sid,names);
				   ActionMessages messages=new ActionMessages();
				   messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.update.cardid"));
				   saveMessages(request, messages);
			  }catch(Exception e){
				  log.error("==修改员工信息==",e);
			}
			return mapping.findForward("update");
	}
	
	/**
	 *加载员工信息 
	 * */
	public ActionForward load(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		EmployeeBO bo=new EmployeeBO();
	    WordlibBO lib=new WordlibBO();
		    DynaActionForm dy=(DynaActionForm)form;
		    AddEmployee_Form emp=(AddEmployee_Form)dy.get("addEmployee");
		    try{
		       Ouser user=(Ouser)request.getSession().getAttribute("user");
			   List gro=lib.gro(user.getUserid()); //组别
			   List dept=lib.dept();//部门
			   List type=lib.workType();//工种
			   List dis=lib.distinction();//职称
			   List headship=lib.headship();//职务
			   List cardid=lib.getCardId();//卡号（有卡号没有员工信息 ）
			   request.setAttribute("gro_list",gro);
			   request.setAttribute("dept_list",dept);
			   request.setAttribute("workType_list",type);
			   request.setAttribute("dis_list",dis);
			   request.setAttribute("headship_list",headship);
			   request.setAttribute("cardid_list", cardid);
			   //--------------------------------------------------
			   String sid=request.getParameter("sid");
			   
			   Staffer sta=(Staffer)bo.load(sid);
			   emp.setStafferid(sid);
			   emp.setName(sta.getName());
			   emp.setCardid(sta.getCardid());
			   emp.setSex(sta.getSex());
			   emp.setStature(sta.getStature());
			   emp.setWeight(sta.getWeight());
			   emp.setEyesight(sta.getEyesight());
			   emp.setBloodtype(sta.getBloodtype());
			   emp.setEducation(sta.getEducation());
			   emp.setMarriage(sta.getMarriage());
			   emp.setCertificate(sta.getCertificate());
			   emp.setCertificateid(sta.getCertificateid());
			   emp.setBirthday(sta.getBirthday());
			   emp.setWorkphone(sta.getWorkphone());
			   emp.setMobile(sta.getMobile());
			   emp.setPostzip(sta.getPostzip());
			   emp.setAddress(sta.getAddress());
			   emp.setHomephone(sta.getHomephone());
			   emp.setGroup(sta.getGroup());
			   emp.setDepartment(sta.getDepartment());
			   emp.setWorktype(sta.getWorktype());
			   emp.setTechnica(sta.getTechnica());
			   emp.setOccupation(sta.getOccupation());
			   emp.setJointime(sta.getJointime());
			   emp.setCardid(sta.getCardid()); 
			   emp.setGivekjcard_time(sta.getGivekjcard_time());
			   emp.setGivewecard_time(sta.getGivewecard_time());
			   emp.setWe_cardid(sta.getWe_cardid());
			   emp.setDep_id(sta.getDep_id());
			   emp.setGzpwd(sta.getGzpwd());
			   emp.setGztype(sta.getGztype());
			   emp.setShunxu(sta.getShunxu());
			   emp.setIfother(sta.getIfother());
			   emp.setIftemp(sta.getIftemp());
			   emp.setIfpublic(sta.getIfpublic());
			   emp.setIfupdate(sta.getIfupdate());
			   emp.setStafferinfo(sta.getStafferinfo());
			   emp.setWorkarea(sta.getWorkarea());
			   emp.setCimportant_post(sta.getCimportant_post());


			   request.setAttribute("cardid",sta.getCardid());
			   request.setAttribute("mid",sid);
		    }catch(Exception e){
		    	log.error("==加载员工信息==",e);
		    }
		    return mapping.findForward("load");
	}
	
	 /**
	  * 显示图片
	  * */

	public ActionForward show(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    EmployeeBO bo=new EmployeeBO();
			try{
				 String sid=request.getParameter("id");
				 Staffer sta=(Staffer)bo.load(sid);
				 if(sta.getPhoto()!=null)
			     {
			    	    InputStream inputStream = sta.getPhoto().getBinaryStream();
						OutputStream fileOutputStream =response.getOutputStream();
						byte[] buf = new byte[1];
						int len = 0;
						while((len = inputStream.read(buf)) != -1) {
						    fileOutputStream.write(buf, 0, len);
						}
						inputStream.close();
						fileOutputStream.close();
			      } 
			 }catch(Exception e){
				 log.error("==显示员工图片==",e);
			 }
		 return null;
	}

}

