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
import com.kj133.entity.MapAdd;
import com.kj133.entity.MapList;
import com.kj133.entity.bo.MapListBO;

/** 
 * MyEclipse Struts
 * Creation date: 03-22-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoadMapAction extends DispatchAction {

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
		MapListBO bo=new MapListBO();
		try{
			 MapAdd mapadd=(MapAdd)dy.get("mapadd");
			 MapList map=(MapList)bo.load(request.getParameter("mid"));
			 mapadd.setId(map.getMapid());
			 mapadd.setInfo(map.getMapinfo());
			 mapadd.setName(map.getMapname());
			 mapadd.setScale(map.getScale());     
		     request.setAttribute("mid",map.getMapid());
		}catch(Exception e){
			log.error("==加载地图信息==",e);
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
		    MapListBO bo=new MapListBO();
			try{
				 String mid=request.getParameter("id");
				 
				 MapList map=(MapList)bo.load(mid);
				 if(map.getMapdata()!=null)
			     {
			    	    InputStream inputStream = map.getCompressdata().getBinaryStream();
			    	    //map.getMapdata();显示的大图，map.getCompressdata()显示的压缩图片，因为小，所以加载的时候速度快	
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
				 log.error("==显示地图图片==",e);
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
		    MapListBO bo=new MapListBO();
		    try{
				  MapAdd map=(MapAdd)dy.get("mapadd");
				  bo.update(map);
				  ActionMessages messages=new ActionMessages();
         		  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.update.cardid"));
         		  saveMessages(request,messages);
		    }catch(Exception e)
		    {
		    	log.error("==修改地图图片==",e);
		    }
		    return mapping.findForward("update");
		}
}

