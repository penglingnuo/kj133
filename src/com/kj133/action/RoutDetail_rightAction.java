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

import com.kj133.entity.Search_routDetail_right;
import com.kj133.entity.bo.RoutDetail_rightBO;
import com.kj133.entity.vo.Seek_left_downVO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 05-17-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class RoutDetail_rightAction extends DispatchAction {

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
	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		List check = null ;
        String code=request.getParameter("code");
        request.getSession().setAttribute("code",code);//用于传递编号
        RoutDetail_rightBO bo=new RoutDetail_rightBO();
		try{
			   
			   check=bo.check(code);
			   
		       request.setAttribute("check",check);
		}catch(Exception e){
			log.error("==巡检路线设置==",e);
		}
        return mapping.findForward("init");
	}
  
	 /**
	  * rightFrmae_save 选择地点类型
	  * */
	public ActionForward save(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm)form;
			Global go=new Global();
			RoutDetail_rightBO bo=new RoutDetail_rightBO();
			try{
				    String code=request.getSession().getAttribute("code").toString();
					Search_routDetail_right right=(Search_routDetail_right)dy.get("routDetail_right");
					StringBuffer sb=new StringBuffer();
					sb.append(" select * from RoutDetail where code= ");
					sb.append(" '"+code+"' "+" and routeorder= ?");
					 
					List count=go.juede(sb.toString(),right.getRouteorder());
					if( count.size() >0){
		            	 ActionMessages messages=new ActionMessages();
						 messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.add.shunxu"));
						 saveMessages(request, messages);
		             }else{
		            	 bo.save(code,right);
		             }
			}catch(Exception e){
				log.error("==巡检路线设置--选择地点类型==",e);
			}
	        return mapping.findForward("save"); 
		}
	
	 /**
	  * rightFrmae_save onclick确定，需要在rightFram上显示，也就是说需要调用check方法
	  * ，直接调用的话，获取不到，那就写个方法用来获取code
	  * */
	public ActionForward save_ok(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			RoutDetail_rightBO bo=new RoutDetail_rightBO();
			try{
				  String code=(String)request.getSession().getAttribute("code");
				  List check=bo.check(code);
			      request.setAttribute("check",check); 
			}catch(Exception e){
				log.error("==巡检路线设置==",e);
			}
			 return mapping.findForward("init");
		}
	 /**
	  * rightFrmae_save
	  * */
	public ActionForward showRadio(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			RoutDetail_rightBO bo=new RoutDetail_rightBO();
			try{
					 String routeorder=request.getParameter("routeorder");
					 String param=request.getParameter("param");
					 request.getSession().setAttribute("param",param);
					 if(routeorder.equals("")){
						 routeorder="0";
					 } 
					 request.getSession().setAttribute("routeorder",routeorder);//用于传递顺序
					 List result=bo.choiceRadio();
				     request.setAttribute("choice", result);
			}catch(Exception e){
				log.error("==巡检路线设置==",e);
			}
	        return mapping.findForward("showRadio");
		}
	
	 /**
	  * rightFrmae_save
	  * */
	public ActionForward addChoiceRadio(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			DynaActionForm dy=(DynaActionForm)form;
			try{
					Search_routDetail_right right=(Search_routDetail_right)dy.get("routDetail_right");
					String array=request.getParameter("radio");
	                String[] radio=array.split("\\+");
				    for(int i=0;i<radio.length;i++){
				    	   right.setPointid(radio[0]);
				    	   right.setShortname(radio[1]);
				    	   right.setType(radio[2]);
				      }
				     String routeorder= request.getSession().getAttribute("routeorder").toString();
				     right.setRouteorder(routeorder);
				     /* 执行相同的操作到不同的页面 */
				     String param=request.getSession().getAttribute("param").toString();
				     if(param.equals("save")){
				    	 return  mapping.findForward("addChoiceRadio");
				     }else{
				    	 return  mapping.findForward("updateChoiceRadio");
				     }
			}catch(Exception e){
				log.error("==巡检路线设置==",e);
			}
			return null;
		}
	
	/**
	 * load
	 * */
	public ActionForward load(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			RoutDetail_rightBO bo=new RoutDetail_rightBO();
			DynaActionForm dy=(DynaActionForm)form;
			try{
				   Search_routDetail_right right=(Search_routDetail_right)dy.get("routDetail_right");
				   String code=request.getSession().getAttribute("code").toString();
				   String routeorder=request.getParameter("routeorder");
				   List list=bo.load(code,routeorder);
				   Seek_left_downVO vo=(Seek_left_downVO)list.get(0);
			       right.setRouteorder(vo.getRouteorder());
			       right.setType(vo.getType());
			       right.setPointid(vo.getPointid());
			       right.setShortname(vo.getShortname());
			       
			}catch(Exception e){
				log.error("==巡检路线设置==",e);
			}
			 return  mapping.findForward("load");
		}
	
	
	/**
	 * update
	 * */
	public ActionForward update(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			RoutDetail_rightBO bo=new RoutDetail_rightBO();
			DynaActionForm dy=(DynaActionForm)form;
			try{
				  String code=request.getSession().getAttribute("code").toString();				  
				  Search_routDetail_right right=(Search_routDetail_right)dy.get("routDetail_right");
				  bo.update(right,code);
			}catch(Exception e){
				log.error("==巡检路线设置==",e);
			}
			 return  mapping.findForward("update");
		}
	
	/**
	 * delete
	 * */
	public ActionForward delete(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			RoutDetail_rightBO bo=new RoutDetail_rightBO();
			try{
				  String code=request.getSession().getAttribute("code").toString();
				  String routeorder=request.getParameter("routeorder");
		          String[] name=routeorder.split("\\,");
				  for(int i=0;i<name.length;i++){
					    bo.delete(code,name[i]);
					    ActionMessages messages=new ActionMessages();
				    	messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.delete.cardid"));
				    	this.saveMessages(request,messages);
				   }	 
			}catch(Exception e){
				log.error("==巡检路线设置==",e);
			}
			  return mapping.findForward("delete");
		}
}

