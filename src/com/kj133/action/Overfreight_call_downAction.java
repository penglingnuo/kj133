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
import com.kj133.entity.bo.Overfreight_call_downBO;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Overfreight_call_downAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  区域超员报警明细
	 * @param response
	 * @return ActionForward
	 */


	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        String os=request.getParameter("os");
        String oe=request.getParameter("oe");
        String aid=request.getParameter("aid");
        
        request.getSession().setAttribute("os",os);//用于传递编号
        request.getSession().setAttribute("oe",oe);//用于传递编号
        String oos = os.substring(0, 19);
        String ooe = oe.substring(0, 19);


		Overfreight_call_downBO bo=new Overfreight_call_downBO();
        
		try{
		
			List list=bo.check(aid,oos,ooe);
			request.setAttribute("listCount1", list.size());
			request.setAttribute("relist",list);
			request.getSession().setAttribute("overfreight_call_downPrint", list);//结果集
		}catch(Exception e){
			log.error("==区域超员报警查询==",e);
		}
        return mapping.findForward("init");
	}

}

