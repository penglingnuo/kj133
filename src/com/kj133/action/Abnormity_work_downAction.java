//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.kj133.entity.Ouser;
import com.kj133.entity.bo.Abnormity_work_downBO;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Abnormity_work_downAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  区域超时明细
	 * @param response
	 * @return ActionForward
	 */


	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        String downtime=request.getParameter("downtime");
        request.getSession().setAttribute("downtime",downtime);//用于传递编号
        String stafferid=request.getParameter("stafferid");
        request.getSession().setAttribute("stafferid",stafferid);//用于传递编号
        Ouser user=(Ouser)request.getSession().getAttribute("user");
        String userid=user.getUserid();

        Abnormity_work_downBO bo=new Abnormity_work_downBO();
        
		try{
		
			List list=bo.check(userid,stafferid,downtime);
			request.setAttribute("listCount1", list.size());
		       request.setAttribute("relist",list);
		       request.getSession().setAttribute("abnormity_work_downPrint", list);//结果集
		}catch(Exception e){
			log.error("==区域超市报警明细==",e);
		}
        return mapping.findForward("init");
	}

}

