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

import com.kj133.entity.Search_PageRangeTopleft;
import com.kj133.entity.bo.PageRangeTopleftBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class PageRangeTopleftAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form 锟斤拷示锟斤拷锟斤拷诒?锟斤拷使锟斤拷ChoiceGroup
	 * @param request  系统词库维护
	 * @param response
	 * @return ActionForward
	 */
	
	/**
	 * 初始化
	 * */
	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		
		DynaActionForm dy=(DynaActionForm) form;
		Search_PageRangeTopleft prt=new Search_PageRangeTopleft();
		
	    String areaname=request.getParameter("areaname");
	    String parentarea=request.getParameter("parentarea");
	    String areaorder=request.getParameter("areaorder");
	    String areatype=request.getParameter("areatype");
	    String maxsum=request.getParameter("maxsum");
	    String caijuesum=request.getParameter("caijuesum");
	    String stayminute=request.getParameter("stayminute");
	    String areainfo=request.getParameter("areainfo");
	    
	    prt.setAreaname(areaname);
	    prt.setParentarea(parentarea);
	    prt.setAreaorder(areaorder);
	    prt.setAreatype(areatype);
	    prt.setMaxsum(Integer.parseInt(maxsum));
	    prt.setCaijuesum(Integer.parseInt(caijuesum));
	    prt.setStayminute(Integer.parseInt(stayminute));
	    prt.setAreainfo(areainfo);
	    WordlibBO lib=new WordlibBO();
		
		try {
			List name1 = lib.areaname1();
			request.setAttribute("name_list",name1);
		} catch (Exception e) {
			
			log.error("==区域设置==",e);
		}
    	 
    	 
	    request.setAttribute("areaname", areaname);
	    request.setAttribute("parentarea", parentarea);
	    request.getSession().setAttribute("areaname", areaname);
	    request.getSession().setAttribute("parentarea", parentarea);
	    

	    request.setAttribute("prt", prt);
	    dy.set("ser_pagerangetopleft",prt);

		if(areaorder.equals("一级区域")){
			return mapping.findForward("init");
		}else{
			return mapping.findForward("initer");
		}		
       
	}
	/**
	 * 初始化二级区域范围
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getlist(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		Search_PageRangeTopleft prt=(Search_PageRangeTopleft)dy.get("ser_pagerangetopleft");
		String name = request.getSession().getAttribute("parentarea").toString();

		    try{
		    	
		    	PageRangeTopleftBO bo = new PageRangeTopleftBO();
		    	List list=bo.getlist(name);
		    	
		    	request.setAttribute("list",list);
		        request.setAttribute("listCount", list.size());
		        	 
		        dy.set("ser_pagerangetopleft",prt);
		        }catch(Exception e){
		        	log.error("==初始化二级区域范围==",e);
		        	 
		        }
				return mapping.findForward("getlist");
				
		}
	/**
	 * 增加二级区域范围
	 * */
	
	public ActionForward add2(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {   
		PageRangeTopleftBO bo=new PageRangeTopleftBO();
		Global go=new Global();
	        String lid=request.getParameter("lid");
	        String aname = request.getSession().getAttribute("areaname").toString();

			String[] name=lid.split("\\,");
			
			try{
				go.delete(" delete AreaDetail where AreaName=? ", aname);
				for(int i=1;i<name.length;i++){
					
					String[] name1 = name[i].split("\\ \\$ ");
					bo.add2(aname,name1[0],name1[1]);
				}
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("true.jilu"));
				saveMessages(request, messages);
				
			}catch(Exception e){
				log.error("==增加二级区域范围==",e);
			}
			return mapping.findForward("add");
	       }
    /**
     * 更新一级区域
     * */
	public ActionForward update(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm)form;
			
			PageRangeTopleftBO bo=new PageRangeTopleftBO();
			Search_PageRangeTopleft prt=(Search_PageRangeTopleft)dy.get("ser_pagerangetopleft");
			
			String name = request.getSession().getAttribute("areaname").toString();
			
			
	         try{
				 
	        	 bo.update(prt,name);
	        	 ActionMessages messages = new ActionMessages();
			    	messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.update.cardid"));
			    	saveMessages(request, messages);
	         }catch(Exception e){
	        	 log.error("==更新一级区域==",e);
	         }
			return mapping.findForward("update");
		}
	
	/**
	 * 删除一级区域  
	 * */
	public ActionForward delete(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    PageRangeTopleftBO bo=new PageRangeTopleftBO();

		    try{
		    	bo.delete(request.getParameter("areaname1"));
		    	ActionMessages messages = new ActionMessages();
		    	messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.delete.cardid"));
		    	saveMessages(request, messages);
		    
		    }catch(Exception e){
		    	log.error("==删除一级区域==",e);
		    }
			return mapping.findForward("delete");
	}
	
	/**
	 * 增加一级区域范围设置
	 * */
	public ActionForward addtype(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		PageRangeTopleftBO bo=new PageRangeTopleftBO();
		Search_PageRangeTopleft account=(Search_PageRangeTopleft)dy.get("ser_pagerangetopleft");
		String areaname = request.getSession().getAttribute("areaname").toString();
		
		 try{
			 String zuhe = account.getZuhe();
			 String[] name=zuhe.split("\\:");
			 Global go=new Global();
			 List count=go.juede3(" select * from AreaDetail where areaname=? and type=? and pointid=? ",areaname,name[0],name[1]);
			 if(count !=null && count.size()>0){
				 ActionMessages messages = new ActionMessages();
    		     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.add.didian"));
    		     saveMessages(request, messages);
			 }else{
			 bo.addtype(account,areaname);
			 ActionMessages messages = new ActionMessages();
		     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.add.cardid"));
		     saveMessages(request, messages);
			 }
			 
		  }catch(Exception e){
			  log.error("==增加一级区域范围设置==",e);
		  } 
		return mapping.findForward("add");
	}
	/**
	 * 删除一级区域范围设置
	 * */
	public ActionForward deletetype(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		PageRangeTopleftBO bo=new PageRangeTopleftBO();
		String areaname = request.getSession().getAttribute("areaname").toString();
		String zuhe1 = request.getParameter("zuhe1");
		try{
			String[] name=zuhe1.split("\\:");
			Global go=new Global();
			List count=go.juede3(" select * from AreaDetail where areaname=? and type=? and pointid=? ",areaname,name[0],name[1]);
			if(count !=null && count.size()>0){
				bo.deletetype(areaname,name[1],name[0]);
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.delete.viewreader"));
				saveMessages(request, messages);
			}else{
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.false.didian"));
				saveMessages(request, messages);
				
			}
			
		}catch(Exception e){
			 log.error("==删除一级区域范围设置==",e);
		} 
		return mapping.findForward("add");
	}
	/**
	 * 增加一级区域范围设置
	 * */
	public ActionForward getname(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		
		PageRangeTopleftBO bo=new PageRangeTopleftBO();
		Search_PageRangeTopleft account=(Search_PageRangeTopleft)dy.get("ser_pagerangetopleft");
		
		try{
			List zuhe = bo.initarea(account);
			request.setAttribute("zuhe_list", zuhe);

			
		}catch(Exception e){
			log.error("==增加一级区域范围设置==",e);
		} 
		return mapping.findForward("getname");
	}
	/**
	 * 增加一级区域
	 * */
	public ActionForward add(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm)form;
			
			WordlibBO lib=new WordlibBO();
			
			PageRangeTopleftBO bo=new PageRangeTopleftBO();
			Search_PageRangeTopleft account=(Search_PageRangeTopleft)dy.get("ser_pagerangetopleft");
			
			 try{
				 Global go=new Global();
				 List count=go.juede(" select areaname from areainfo where areaname  = ? ",account.getAreaname());
				 if(count !=null && count.size()>0){
					 ActionMessages messages = new ActionMessages();
	    		     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.add.areaname"));
	    		     saveMessages(request, messages);
				 }else{
				 List areaid = lib.areaid();
				 int id = areaid.size();
				 bo.add(account,id);
				 ActionMessages messages = new ActionMessages();
    		     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.add.cardid"));
    		     saveMessages(request, messages);
				 }
				 
			  }catch(Exception e){
				  log.error("==增加一级区域==",e);
			  } 
			return mapping.findForward("add");
		}
}

