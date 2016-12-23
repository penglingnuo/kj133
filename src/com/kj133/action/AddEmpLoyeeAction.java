//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import com.kj133.entity.AddEmployee_Form;
import com.kj133.entity.bo.EmployeeBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 03-23-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class AddEmpLoyeeAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request 增加员工信息
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		EmployeeBO bo=new EmployeeBO();
		Global go=new Global();
		try{
		        AddEmployee_Form emp=(AddEmployee_Form)dy.get("addEmployee");
				List count=go.juede("select * from staffer where stafferid= ?",emp.getStafferid());
				if( count != null && count.size()>0)
				{
					  ActionMessages messages = new ActionMessages();
		    		  messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.add.stafferid"));
		    		  saveMessages(request, messages);
		    		  return mapping.findForward("error");
				}else{
				      bo.save(emp);
				      ActionMessages messages = new ActionMessages();
			    	  messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.add.stafferid"));
			    	  saveMessages(request, messages);
				      return mapping.findForward("success");
				}	 
        }catch(Exception e){
        	log.error("==增加员工信息==",e);
        }
		return null;
	}
}

