//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.io.InputStream;
import java.io.OutputStream;
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
import com.kj133.entity.EmergencyAdd;
import com.kj133.entity.ExitPath;
import com.kj133.entity.bo.EmergencyListBO;

/** 
 * MyEclipse Struts
 * Creation date: 03-22-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoadEmergencyAction extends DispatchAction {

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
	 * 加载信息
	 * */
	public ActionForward load(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        DynaActionForm dy=(DynaActionForm)form;
        EmergencyListBO bo=new EmergencyListBO();
		try{
			EmergencyAdd mapadd=(EmergencyAdd)dy.get("emergencyadd");
			String aa = request.getParameter("mname");
			ExitPath map=(ExitPath)bo.load(aa);
			
			mapadd.setName(map.getPathname());
			 mapadd.setInfo(map.getPathinfo());
		     request.setAttribute("mname",map.getPathname());
		     request.setAttribute("aaname",aa);
		}catch(Exception e){
			log.error("==增加避灾路线==",e);
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
		EmergencyListBO bo=new EmergencyListBO();
			try{
				 String mname=request.getParameter("name");
				 
				 ExitPath map=(ExitPath)bo.load(mname);
				 if(map.getPathimage()!=null)
			     {
					 InputStream inputStream = null;
					 
					 if(map.getCompressdata()!=null){
						 inputStream = map.getCompressdata().getBinaryStream();
					 }else{
						 inputStream = map.getPathimage().getBinaryStream();
					 }  
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
				 log.error("==显示避灾路线==",e);
			 }
		 return null;
	}
	
	/**
	 * 修改图片信息
	 * */
	public ActionForward update(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    DynaActionForm dy=(DynaActionForm)form;
		    EmergencyListBO bo=new EmergencyListBO();
		    try{
		    	EmergencyAdd map=(EmergencyAdd)dy.get("emergencyadd");
				  bo.update(map);
				  ActionMessages messages=new ActionMessages();
         		  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.update.cardid"));
         		  saveMessages(request,messages);
		    }catch(Exception e)
		    {
		    	log.error("==修改避灾路线==",e);
		    }
		    return mapping.findForward("update");
		}
}

