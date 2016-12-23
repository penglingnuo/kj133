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
	 * @param request�Լ���
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
	      		
//	      		--------------���ݿ�����
            	String ip1=con.substring(dbipBeg+7, con.length()); //���ݿ�Ip
	      		String ipResult1 = uc.ipConnect(ip1);
	      		if(ipResult1.equals("aa")){
	      			request.setAttribute("listCount1", 0);
	      			request.setAttribute("ipResult1", "����");
	      		}else{
	      			request.setAttribute("listCount1", 1);
	      			request.setAttribute("ipResult1", "���ݿ�δ��ͨ");
	      		}
//	      		--------------������������		
//	      		String ip2 = "www.baidu.com";
//	      		String ipResult2 = uc.ipConnect(ip2); 
	      		if(ipResult1.equals("aa")){
	      			request.setAttribute("listCount2", 0);
	      			request.setAttribute("ipResult2", "������������");
	      		}else{
	      			request.setAttribute("listCount2", 1);
	      			request.setAttribute("ipResult2", "����δ����");
	      		}
//	      		--------------�ɼ�������		
	      		String ip3=web.getSvip(); //�ɼ�������IP
	      		String ipResult3 = uc.ipConnect(ip3); 
	      		if(ipResult3.equals("aa")){
	      			request.setAttribute("listCount3", 0);
	      			request.setAttribute("ipResult3", "����");
	      		}else{
	      			request.setAttribute("listCount3", 1);
	      			request.setAttribute("ipResult3", "�ж�");
	      		}
//	      --------------Web����
	      		if(ipResult1.equals("aa")){
	      			request.setAttribute("listCount4", 0);
	      			request.setAttribute("ipResult4", "����");
	      		}else{
	      			request.setAttribute("listCount4", 1);
	      			request.setAttribute("ipResult4", "�ж�");
	      		}
	    }catch(Exception e){
	    	log.error("==�Լ���==",e);
	    }
		

       
        	return mapping.findForward("show");
	}
	

}

