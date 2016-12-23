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

import com.kj133.entity.bo.WebInitializeBO;
import com.kj133.entity.vo.WebInitializeVO;
import com.kj133.util.Url_Connect;

/** 
 * MyEclipse Struts
 * Creation date: 02-28-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class StakeoutAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request自监视
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		Url_Connect uc = new Url_Connect();
//----------------------------------------------------------------------------------------------
	    WebInitializeBO bo=new WebInitializeBO();
	    try{
	    	List list=bo.init();
	    	WebInitializeVO web=(WebInitializeVO)list.get(0);
	    	String con=web.getStrcon();
	        
            
            
            int dbipBeg=con.indexOf("Source=");
            
//	      ----------------------------------------------------------------------------------------------		
	      		
//	      		--------------数据库连接
            	String ip1=con.substring(dbipBeg+7, con.length()); //数据库Ip
	      		String ipResult1 = uc.ipConnect(ip1);
	      		if(ipResult1.equals("aa")){
	      			request.setAttribute("listCount1", 0);
	      			request.setAttribute("ipResult1", "正常");
	      		}else{
	      			request.setAttribute("listCount1", 1);
	      			request.setAttribute("ipResult1", "数据库未连通");
	      		}
//	      		--------------网络连接连接		
//	      		String ip2 = "www.baidu.com";
//	      		String ipResult2 = uc.ipConnect(ip2); 
	      		if(ipResult1.equals("aa")){
	      			request.setAttribute("listCount2", 0);
	      			request.setAttribute("ipResult2", "网络连接正常");
	      		}else{
	      			request.setAttribute("listCount2", 1);
	      			request.setAttribute("ipResult2", "网络未连接");
	      		}
//	      		--------------采集服务器		
	      		String ip3=web.getSvip(); //采集服务器IP
	      		String ipResult3 = uc.ipConnect(ip3); 
	      		if(ipResult3.equals("aa")){
	      			request.setAttribute("listCount3", 0);
	      			request.setAttribute("ipResult3", "正常");
	      		}else{
	      			request.setAttribute("listCount3", 1);
	      			request.setAttribute("ipResult3", "中断");
	      		}
//	      --------------Web服务
	      		if(ipResult1.equals("aa")){
	      			request.setAttribute("listCount4", 0);
	      			request.setAttribute("ipResult4", "正常");
	      		}else{
	      			request.setAttribute("listCount4", 1);
	      			request.setAttribute("ipResult4", "中断");
	      		}
	    }catch(Exception e){
	    	log.error("==自监视==",e);
	    }
		

       
        	return mapping.findForward("show");
	}
	

}

