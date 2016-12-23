//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import com.kj133.entity.Locator;
import com.kj133.entity.bo.LocatorBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-11-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Operator_locatorAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request�༭��λ�������add,update,delete
	 * @param response
	 * @return ActionForward
	 */
	
    /**
     * ����
     * */
	public ActionForward save(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	    	LocatorBO bo=new LocatorBO();
	    	Global glo=new Global();
	    	Locator locator=new Locator();
	    	DynaActionForm dy=(DynaActionForm)form;
	    	try{
	    		String lid=dy.getString("locatorid"); 
	    		List list=glo.juede(" select * from locator where locatorid= ? ",lid);
	    		if( list != null && list.size() >0 ){
	    			  ActionMessages messages = new ActionMessages();
	    		      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.add.locatorid"));
	    		      saveMessages(request, messages);
	    		      return mapping.findForward("false");
	    		}else{
		    		  String mid=dy.getString("mapid");
	    			  List count=glo.juede(" select * from maplist where mapid= ?",mid);
	    			  if( count != null && count.size()==0 ){
	    				  ActionMessages messages = new ActionMessages();
		    		      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.add.mapid"));
		    		      saveMessages(request, messages);
		    		      return mapping.findForward("false");
	    			  }else{
			    		  BeanUtils.populate(locator,dy.getMap());
			    		  bo.save(locator);
			    		  ActionMessages messages = new ActionMessages();
		    		      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.add.locator"));
		    		      saveMessages(request, messages);
		    		      return  mapping.findForward("save");  
	    			  }
	    		} 
	    	}catch(Exception e){
	    		log.error("==�༭��λ����Ϣ==",e);
	    	}
	    			return null;
		
	   }
	
	 /**
	  * ��ȡ��������
	  * */
	 public ActionForward load(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    DynaActionForm dy=(DynaActionForm)form;
		    String lid=request.getParameter("lid");
		    String rid=request.getParameter("rid");
		    request.getSession().setAttribute("lid", lid);
		    try{
		    	LocatorBO bo=new LocatorBO(); 
		    	Locator lr=(Locator)bo.load(lid);
		    	dy.set("recordid",rid);
		    	dy.set("locatorid",lid);
		        dy.set("lname",lr.getLname());
		        dy.set("shortname",lr.getShortname());
		        dy.set("x",lr.getX());
		        dy.set("y",lr.getY());
		        dy.set("z",lr.getZ());
		        dy.set("ground",lr.getGround());
		        dy.set("state",lr.getState());
		        dy.set("mapid",lr.getMapid());
		    }catch(Exception e){
		    	log.error("==���ض�λ����Ϣ==",e);
		    }
	    	return mapping.findForward("load");
	        }

		/**
		 * �޸�
		 * */
		public ActionForward update(
				ActionMapping mapping,
				ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response) {
			    DynaActionForm dy=(DynaActionForm)form;
				Global glo=new Global();
			    LocatorBO bo=new LocatorBO(); 
			        
			    String lid=request.getSession().getAttribute("lid").toString();
			    String locatorid=dy.getString("locatorid");
			    String lname=dy.getString("lname");  
			    String shortname=dy.getString("shortname");   
			    String x=dy.getString("x");   
			    String y=dy.getString("y");   
			    String z=dy.getString("z");   
			    String ground=dy.getString("ground");   
			    String state=dy.getString("state");   
			    String mapid=dy.getString("mapid"); 
			    String sql=" update locator set  locatorid= ?,lname= ? , shortname= ? , " +
			    		" x= ? , y= ?, z=?,ground= ?,state = ?,mapid= ?,modifydate= ?  where 1=1 and locatorid= ? ";
			    //��ȡϵͳ�ĵ�ǰʱ��
			    Calendar cal=Calendar.getInstance();
			    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    String time=formatter.format(cal.getTime());
			    Object[] obj=new Object[] {locatorid,lname,shortname,x,y,z,ground,state,mapid,time,lid};
			    try{
			    	   List count=glo.juede(" select * from maplist where mapid= ?",mapid);
			    	   if( count != null && count.size()== 0 ){
		    				  ActionMessages messages = new ActionMessages();
			    		      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.update.mapid"));
			    		      saveMessages(request, messages);
			    		  	  return mapping.findForward("load");
			    	   }else{
			    	       bo.executeSpecialSQL(sql,obj);
					       ActionMessages messages = new ActionMessages();
					       messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.update.locator"));
			    		   saveMessages(request, messages); 
			    		   return mapping.findForward("update"); 
			    	   }
			    }catch(Exception e){
			    	log.error("==�޸Ķ�λ����Ϣ==",e);
			    }
			    return null;
		     }
		
		/**
		 * ɾ��
		 * */
		
		public ActionForward delete(
				ActionMapping mapping,
				ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response) {   
		    	LocatorBO bo=new LocatorBO();
		        String lid=request.getParameter("lid");
				String[] name=lid.split("\\,");
				   for(int i=0;i<name.length;i++){
					    try{
					    	List list = bo.ifcard(lid);
					    	if(list.size() == 0){
					    		bo.delete(name[i]);
						        ActionMessages messages = new ActionMessages();
					    		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.delete.cardid"));
					    		saveMessages(request, messages);  
							   }else{
								   ActionMessages messages=new ActionMessages();
									messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("false.delete.locator"));
									saveMessages(request, messages);   
							   }
					        
					    }catch(Exception e){
					    	log.error("==ɾ����λ����Ϣ==",e);
					    }
				   }	 
				   	return mapping.findForward("delete");
		       }
		  
}

