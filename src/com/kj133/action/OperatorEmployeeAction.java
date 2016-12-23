//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import com.kj133.entity.AddEmployee_Form;
import com.kj133.entity.Ouser;
import com.kj133.entity.bo.WordlibBO;

/** 
 * MyEclipse Struts
 * Creation date: 03-23-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class OperatorEmployeeAction extends DispatchAction {

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
	 * �����ӣ����г�ʼ��
	 * */
	public ActionForward initializerator(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    WordlibBO lib=new WordlibBO();
		    DynaActionForm dy=(DynaActionForm)form;
		    String cid=request.getParameter("cid");
		    try{
		    	       AddEmployee_Form emp=(AddEmployee_Form)dy.get("addEmployee");
					   Calendar cal=Calendar.getInstance();
					   SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
					   String time=formatter.format(cal.getTime());
					   emp.setSex("��");
					   emp.setBirthday("1980-01-01");
					   emp.setJointime(time);
					   emp.setCardid(cid);
					   Ouser user=(Ouser)request.getSession().getAttribute("user");
					   List gro=lib.gro(user.getUserid()); //���
					   List dept=lib.dept();//����
					   List type=lib.workType();//����
					   List dis=lib.distinction();//ְ��
					   List headship=lib.headship();//ְ��
					   List cardid=lib.getCardId();//����
					   request.setAttribute("gro_list",gro);
					   request.setAttribute("dept_list",dept);
					   request.setAttribute("workType_list",type);
					   request.setAttribute("dis_list",dis);
					   request.setAttribute("headship_list",headship);
					   request.setAttribute("cardid_list", cardid);
			 }catch(Exception e){
				 log.error("==�༭Ա����Ϣ==",e);
			 }
			   return mapping.findForward("add.success");
		 }
}

