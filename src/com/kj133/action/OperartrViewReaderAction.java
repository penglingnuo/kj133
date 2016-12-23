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

import com.kj133.entity.CardReader;
import com.kj133.entity.Search_OperatorViewReaderForm;
import com.kj133.entity.bo.OperartrViewReaderBO;
import com.kj133.util.Global;
 


/** 
 * MyEclipse Struts
 * Creation date: 02-11-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class OperartrViewReaderAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request 编辑分站里的add,update,delete
	 * @param response
	 * @return ActionForward
	 */
	/**
	   * 编辑分站
	   * save
	   * */
	public ActionForward save(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		     DynaActionForm dy=(DynaActionForm)form;
		     OperartrViewReaderBO bo=new OperartrViewReaderBO();
		     Global go=new Global();
		     Search_OperatorViewReaderForm operReader=(Search_OperatorViewReaderForm)dy.get("ser_operator_viewReader");
		     operReader.setGasIgnore("1");
		     try{
		    	  List count=go.juede(" select * from cardreader where cardreaderid = ? ",operReader.getCardreaderid());
		    	  if( count != null && count.size()> 0){
		        	  ActionMessages messages=new ActionMessages();
					  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.add.vewreaderid"));
					  saveMessages(request, messages);
		              return mapping.findForward("false");
		        }else{ 
//		        	 List count2=go.juede(" select * from maplist where mapid = ? ",operReader.getMapid());
		        	 List count2=go.juede(" select * from MoreMaps where mapid=? ",operReader.getMapid());
			    	  if( count2 != null && count2.size()== 0)
		        	  {
			    	      
			    		  ActionMessages messages=new ActionMessages();
						  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.add.mapid"));
						  saveMessages(request, messages);
			              return mapping.findForward("false");  
		        	  }else{
			        	  bo.save(operReader);
			        	  ActionMessages messages=new ActionMessages();
						  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.add.vewreader"));
						  saveMessages(request, messages);
						  return mapping.findForward("save"); 
		        	  }
		        } 
		     }catch(Exception e){
		    	 log.error("==编辑分站信息==",e);
		     }
		     
		    return null;
		   }
    
	 /**
	  * 编辑分站
	  * 删除
	  * */
	public ActionForward delete(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        OperartrViewReaderBO bo=new OperartrViewReaderBO();
		    String cid=request.getParameter("cid");  
		    String[] name=cid.split("\\,");
		    for(int i=0;i<name.length;i++){
			    try{
			    	List list = bo.ifcard(cid);
			    	if(list.size() == 0){
			    		bo.delete(name[i]);
				    	 ActionMessages messages=new ActionMessages();
						 messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.delete.viewreader"));
						 saveMessages(request, messages);   
					   }else{
						   ActionMessages messages=new ActionMessages();
							messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("false.delete.viewreader"));
							saveMessages(request, messages);   
					   }
			    	 
			    }catch(Exception e){
			    	log.error("==删除编辑分站信息==",e);
			    }
		   }
		    return  mapping.findForward("delete");
	 }
	 /**
	  * 编辑分站
	  * 提取单条记录
	  * */
	public ActionForward load(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    String cid=request.getParameter("cid");  
		    String rid=request.getParameter("rid");  
		    request.getSession().setAttribute("cid", cid);
		    OperartrViewReaderBO bo=new OperartrViewReaderBO();
		    DynaActionForm dy=(DynaActionForm)form;
		    try{
		    	 Search_OperatorViewReaderForm oper=(Search_OperatorViewReaderForm)dy.get("ser_operator_viewReader");	 
		    	 CardReader reader=(CardReader)bo.load(cid);
		         oper.setRecordid(rid);
		         oper.setCardreaderid(reader.getCardreaderid());
		         oper.setCrname(reader.getCrname());
		    	 oper.setShortname(reader.getShortname());
		    	 oper.setX(reader.getX());
		    	 oper.setY(reader.getY());
		    	 oper.setIgnoretimes(reader.getIgnoretimes());
		    	 oper.setLocatorignoretimes(reader.getLocatorignoretimes());
		    	 oper.setMapid(reader.getMapid());
		    	 oper.setIgnorelocator(reader.getIgnorelocator());
		    	 oper.setGround(reader.getGround());
		    	 oper.setState(reader.getState());
		    	 oper.setCheckreader(reader.getCheckreader());
		    	 oper.setIfalarm(reader.getIfalarm());
		    	 oper.setZ(reader.getZ());
		    	 String count=reader.getUseantenna();
		    	 
		    	 int check=Integer.parseInt(count);
		    	 if( check == 1){
		        	 oper.setUseantenna(new String[]{"1"});
		         }else  if( check == 2){
		        	 oper.setUseantenna(new String[]{"2"});
		         }else   if( check == 3){
		        	 oper.setUseantenna(new String[]{"1","2"});
		         }else  if( check == 4){
		        	 oper.setUseantenna(new String[]{"4"});
		         }else   if( check == 5){
		        	 oper.setUseantenna(new String[]{"1","4"});
		         }else   if( check == 6){
		        	 oper.setUseantenna(new String[]{"2","4"});
		         }else  if( check == 7){
		        	 oper.setUseantenna(new String[]{"1","2","4"});
		         }	 
		       }catch(Exception e){
		    	   log.error("==加载分站信息==",e);
		    }
		   return  mapping.findForward("load");
	   }
	/**
	 * 修改分站
	 * */
	public ActionForward update(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    DynaActionForm dy=(DynaActionForm)form;
		    Global go=new Global();
		    OperartrViewReaderBO bo=new OperartrViewReaderBO();
		    String cc = request.getSession().getAttribute("cid").toString();
		    try{
		    	  Search_OperatorViewReaderForm operReader=(Search_OperatorViewReaderForm)dy.get("ser_operator_viewReader");
		   	      List count2=go.juede(" select * from maplist where mapid = ? ",operReader.getMapid());
		    	  if( count2 != null && count2.size()== 0)
	        	  {
	        		  ActionMessages messages=new ActionMessages();
					  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.add.mapid"));
					  saveMessages(request, messages);
					  return  mapping.findForward("load");
	        	  }else{
			    	  bo.update(operReader,cc);
			    	  ActionMessages messages=new ActionMessages();
					  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.update.viewreader"));
				      saveMessages(request, messages); 
	        	  }
		    	
		    }catch(Exception e){
		    	log.error("==修改分站信息==",e);
		    }
		   return mapping.findForward("update");
	 }	  
}

