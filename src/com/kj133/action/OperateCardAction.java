//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

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

import com.kj133.entity.Recog;
import com.kj133.entity.bo.OperateCardBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-10-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class OperateCardAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request编辑卡里面的操作
	 * @param response
	 * @return ActionForward
	 */
	private final Logger  log=Logger.getLogger(this.getClass());
	/**
	 * save
	 * */
	public ActionForward save(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) { 
		    DynaActionForm dy=(DynaActionForm)form;
	        OperateCardBO bo=new OperateCardBO();
	        WordlibBO lib=new WordlibBO();
			Recog recog=new Recog(); 
			Global go=new Global();
	        String cid=dy.getString("cardid");
			try{
				  List count=go.juede(" select * from recog where cardid= ? ",cid);
				  if( count != null && count.size()> 0){
						  ActionMessages messages=new ActionMessages();
						  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.add.cardid"));
						  saveMessages(request, messages);
				  }else{
				          List gro=lib.workType();
						  request.setAttribute("workType_list",gro);
						  BeanUtils.populate(recog,dy.getMap());
						  bo.save(recog);
						  ActionMessages messages=new ActionMessages();
						  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.add.cardid"));
                          saveMessages(request, messages); 
				  }
			    }catch(Exception e){
			    	log.error("==增加卡号==",e);
			    }       
			    return mapping.findForward("save");
	}
	public ActionForward savelx(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) { 
		DynaActionForm dy=(DynaActionForm)form;
		OperateCardBO bo=new OperateCardBO();
		Recog recog=new Recog(); 
		Global go=new Global();
		String cidmin=dy.getString("mincardid");
		String cidmax=dy.getString("maxcardid");
		try{
			int a = Integer.valueOf(cidmin);
			int b = Integer.valueOf(cidmax);
			 for(int i=a;i<=b;i++){
				 String bb= String.valueOf(i);
				 //第一种方法：
				 if (bb.indexOf("4") == -1) {

					 List count=go.juede(" select * from recog where cardid= ? ",bb);
					 if( count != null && count.size()> 0){
							ActionMessages messages=new ActionMessages();
							messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.add.cardid"));
							saveMessages(request, messages);
						}else{
							
							BeanUtils.populate(recog,dy.getMap());
							bo.savelx(recog,bb);
							ActionMessages messages=new ActionMessages();
							messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.add.cardid"));
							saveMessages(request, messages); 
						}
				 }
//				第二种方法：
//				 if(bb.contains("4")){
//					 
//				 }else{
//					 System.out.println(bb);
//				 }
			 }	
			
		}catch(Exception e){
			log.error("==增加除4外卡号==",e);
		}       
		return mapping.findForward("save");
	}
	 /**
     * 提取单条数据
     * */     
	public ActionForward load(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        String cid=request.getParameter("cid");       
	        DynaActionForm dy=(DynaActionForm)form;
        	OperateCardBO bo=new OperateCardBO();
	        try{
	            Recog recog=(Recog)bo.load(cid); 
	        	dy.set("recordid",recog.getRecordid());
	        	dy.set("cardid",recog.getCardid());
	        	dy.set("cardmode",recog.getCardmode());
	        	dy.set("cardstate",recog.getCardstate());
	        }catch(Exception e){
	        	log.error("==提取卡号==",e);
	        }
	        	return mapping.findForward("load");
	}
	
	/**
	 * 修改
	 */
	public ActionForward update(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        OperateCardBO bo=new OperateCardBO();
	        WordlibBO lib=new WordlibBO();
		    DynaActionForm dy=(DynaActionForm)form;
		    String recordid=dy.getString("recordid");  
		    String cardid=dy.getString("cardid");  
		    String cardmode=dy.getString("cardmode");  
		    String cardstate=dy.getString("cardstate"); 
		    String sql=" update recog set  cardid= ?,cardmode= ?,cardstate= ? where  recordid= ?";
		    Object[] object=new Object[]{cardid,cardmode,cardstate,recordid};
		    try{
		    	List gro=lib.workType();
				request.setAttribute("workType_list",gro);
		    	bo.executeSpecoalSQL(sql,object);
		    	ActionMessages messages=new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.update.cardid"));
				saveMessages(request, messages);
		    }catch(Exception e){
		    	log.error("==修改卡号==",e);
		    }
		    return mapping.findForward("update");
	}
	
	/**
	 *delete
	 * */
	public ActionForward delete(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    OperateCardBO bo=new OperateCardBO();
		    String cid=request.getParameter("cid"); 
			String[] name=cid.split("\\,");
			
			for(int i=0;i<name.length;i++){
				   try{
					   List list = bo.ifcard(cid);
					   if(list.size() == 0){
				    	bo.delete(name[i]);
				    	ActionMessages messages=new ActionMessages();
						messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.delete.cardid"));
						saveMessages(request, messages);   
					   }else{
						   ActionMessages messages=new ActionMessages();
							messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("false.delete.cardid"));
							saveMessages(request, messages);   
					   }
				    }catch(Exception e){
				    	log.error("==删除卡号==",e);
				    }
			   }
		return  mapping.findForward("delete");
	}
}

