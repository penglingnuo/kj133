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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.kj133.entity.bo.SysWordLeftBO;
import com.kj133.entity.bo.SysWordRightBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

/**
 * MyEclipse Struts Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * 
 * @struts.action scope="request" validate="true"
 */
public class SysWordRightAction extends DispatchAction {

	// --------------------------------------------------------- Instance
	// Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 *            系统词库维护
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String wordname = request.getParameter("wordname");
		String wordtype = request.getParameter("wordtype");
		String maxvalue = request.getParameter("maxvalue");
		String minvalue = request.getParameter("minvalue");
		String maxlines = request.getParameter("maxlines");
		request.getSession().setAttribute("wordname1", wordname);
		request.getSession().setAttribute("wordtype1", wordtype);
		request.getSession().setAttribute("maxvalue1", maxvalue);
		request.getSession().setAttribute("minvalue1", minvalue);
		request.getSession().setAttribute("maxlines1", maxlines);
		


		try {
			SysWordRightBO bo = new SysWordRightBO();
			List list = bo.init(wordname);
			
			request.setAttribute("relist", list);
			
			request.setAttribute("listCount", list.size());

		} catch (Exception e) {
			log.error("==系统词库维护==",e);
			
		}
		return mapping.findForward("show");
		
	}
	
	public ActionForward initer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {

				
				String name = request.getSession().getAttribute("wordname3").toString();
				request.getSession().getAttribute("wordtype3");
				request.getSession().getAttribute("maxvalue3");
				request.getSession().getAttribute("minvalue3");
				request.getSession().getAttribute("maxlines3");
				SysWordRightBO bo = new SysWordRightBO();
				List list = bo.init(name);
				request.setAttribute("relist", list);
				
				request.setAttribute("listCount", list.size());
				

		} catch (Exception e) {
			log.error("==系统词库维护==",e);
			
		}
		return mapping.findForward("show");
		
	}
	
	public ActionForward load(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		

		String wordvalue = request.getParameter("wordvalue");
		String recordid = request.getParameter("recordid");
		String valuetype = request.getParameter("valuetype");


		request.getSession().setAttribute("wordname2", request.getSession().getAttribute("wordname1").toString());
		request.getSession().setAttribute("wordtype2", request.getSession().getAttribute("wordtype1").toString());
		request.getSession().setAttribute("maxvalue2", request.getSession().getAttribute("maxvalue1").toString());
		request.getSession().setAttribute("minvalue2", request.getSession().getAttribute("minvalue1").toString());
		request.getSession().setAttribute("maxlines2", request.getSession().getAttribute("maxlines1").toString());
		
		request.getSession().setAttribute("wordname3", request.getSession().getAttribute("wordname1").toString());
		request.getSession().setAttribute("wordtype3", request.getSession().getAttribute("wordtype1").toString());
		request.getSession().setAttribute("maxvalue3", request.getSession().getAttribute("maxvalue1").toString());
		request.getSession().setAttribute("minvalue3", request.getSession().getAttribute("minvalue1").toString());
		request.getSession().setAttribute("maxlines3", request.getSession().getAttribute("maxlines1").toString());
		
		request.getSession().setAttribute("recordid", recordid);
		request.getSession().setAttribute("wordvalue", wordvalue);
        if(valuetype.equals("允许删改")){
        	
		    try{

		    	dy.set("wordvalue",wordvalue);
		    	

		        }catch(Exception e){
		        	 e.printStackTrace();
		        	 
		        }
				return mapping.findForward("load");
        }else{
        	 try{

        		 ActionMessages messages=new ActionMessages();
     			messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("not.bowdlerize"));
     			saveMessages(request, messages);
 		    	

 		        }catch(Exception e){
 		        	log.error("==系统词库维护==",e);
 		        	 
 		        }
			return mapping.findForward("reload");
        }
				
		}
	/**
	 * 修改
	 */
	public ActionForward update(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().setAttribute("wordname3", request.getSession().getAttribute("wordname2").toString());
		request.getSession().setAttribute("wordtype3", request.getSession().getAttribute("wordtype2").toString());
		request.getSession().setAttribute("maxvalue3", request.getSession().getAttribute("maxvalue2").toString());
		request.getSession().setAttribute("minvalue3", request.getSession().getAttribute("minvalue2").toString());
		request.getSession().setAttribute("maxlines3", request.getSession().getAttribute("maxlines2").toString());
		  SysWordLeftBO bo = new SysWordLeftBO();
	        WordlibBO lib=new WordlibBO();
		    DynaActionForm dy=(DynaActionForm)form;
		    String wordvalue=dy.getString("wordvalue");  
		    String recordid = request.getSession().getAttribute("recordid").toString();
		    String sql=" update wordlib set wordvalue=? where recordid=?  ";
		    Object[] object=new Object[]{wordvalue,recordid};
		    try{
		    	List gro=lib.workType();
				request.setAttribute("workType_list",gro);
		    	bo.executeSpecoalSQL(sql,object);
		    	ActionMessages messages=new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.update.cardid"));
				saveMessages(request, messages);
		    }catch(Exception e){
		    	log.error("==修改系统词库维护==",e);
		    }
		    return mapping.findForward("update");
	}
	
	/**success.delete.cardid
	 * 删除系统词值
	 * */
	public ActionForward delete(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
  		HttpServletResponse response) {

		request.getSession().setAttribute("wordname3", request.getSession().getAttribute("wordname2").toString());
		request.getSession().setAttribute("wordtype3", request.getSession().getAttribute("wordtype2").toString());
		request.getSession().setAttribute("maxvalue3", request.getSession().getAttribute("maxvalue2").toString());
		request.getSession().setAttribute("minvalue3", request.getSession().getAttribute("minvalue2").toString());
		request.getSession().setAttribute("maxlines3", request.getSession().getAttribute("maxlines2").toString());
		String wordvalue =request.getSession().getAttribute("wordvalue").toString();
		String id =request.getSession().getAttribute("recordid").toString();
		SysWordRightBO bo = new SysWordRightBO();
		 try{
    		 bo.delete(id,wordvalue);
		    	ActionMessages messages=new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.delete.cardid"));
				saveMessages(request, messages);
    	 }catch(Exception e){
    		 log.error("==系统词库维护==",e);
    	 }
		return mapping.findForward("delete");
      }
	

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dy = (DynaActionForm) form;
		SysWordRightBO bo = new SysWordRightBO();
		Global go=new Global();
		String wordname1 = request.getSession().getAttribute("wordname1").toString();
		
		String wordtype1 = request.getSession().getAttribute("wordtype1").toString();
		String maxvalue1 = request.getSession().getAttribute("maxvalue1").toString();
		String minvalue1 = request.getSession().getAttribute("minvalue1").toString();
		String maxlines1 = request.getSession().getAttribute("maxlines1").toString();
		request.getSession().setAttribute("wordname3", wordname1);
		request.getSession().setAttribute("wordtype3", wordtype1);
		request.getSession().setAttribute("maxvalue3", maxvalue1);
		request.getSession().setAttribute("minvalue3", minvalue1);
		request.getSession().setAttribute("maxlines3", maxlines1);
		String wordvalue1 = dy.getString("wordvalue");
		String valuetype1 = "3";
		String module1 = "1";
		
		 String sql=" insert into wordlib (WordName,WordValue,WordType,ValueType,MaxValue,MinValue,MaxLines,module) values(?, ?, ?, ?, ?, ?, ?, ?) ";
		
		 Object[] object=new Object[]{wordname1,wordvalue1,wordtype1,valuetype1,maxvalue1,minvalue1,maxlines1,module1};
		request.getSession().setAttribute("wordname2", wordname1);
		

		try {
			List count=go.juede1(" select wordname,wordvalue from wordlib where wordname= ? and wordvalue=? ",wordname1,wordvalue1);
			if( count != null && count.size()> 0){
				

				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("sys.repeat"));
				saveMessages(request, messages);
				
			}else{

			bo.add(sql,object);

			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.add.cardid"));
			saveMessages(request, messages);
			 }
		} catch (Exception e) {
			log.error("==系统词库维护==",e);
		}
		return mapping.findForward("add");
	}


}
