/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
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

import com.kj133.entity.Search_Pworkset;
import com.kj133.entity.bo.PworksetBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-26-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class PworksetAction extends DispatchAction {
	/*
	 * Generated Methods
	 */
	/*
	 * Generated Methods
	 */
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��ʼ��
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		Search_Pworkset mou=(Search_Pworkset)dy.get("pworkset");
			WordlibBO lib=new WordlibBO();
			PworksetBO  bo=new PworksetBO();
		    try{
		    	 List list=bo.init();
		    	 request.setAttribute("mountList", list);
		    	 List type=lib.workType();
				 request.setAttribute("workType_list",type);
				 mou.setEm("");
				 mou.setWorktype("");
		    }catch(Exception e){
		    	log.error("==��Ա��������==",e);
		    }
		return mapping.findForward("init");
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return   ��ѯ
	 */
	public ActionForward getList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    DynaActionForm dy=(DynaActionForm)form;
		    WordlibBO lib=new WordlibBO();
		    PworksetBO  mount=new PworksetBO();
		    try{
		    	 Search_Pworkset mou=(Search_Pworkset)dy.get("pworkset");
		    	 List list=mount.init1(mou);
		    	 if( list.size()== 0){
	        		   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
	        	 }else{
	        		 request.setAttribute("mountList", list);
	        	 }
		    	 
		    	 List type=lib.workType();
				 request.setAttribute("workType_list",type);
		    }catch(Exception e){
		    	log.error("==��Ա��������==",e);
		    }
		    return mapping.findForward("init");
	}


	/**
	 * ɾ��
	 * */
	
	public ActionForward deleteSite(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {   
		PworksetBO  bo=new PworksetBO();
		WordlibBO lib=new WordlibBO();
	        String lid=request.getParameter("lid");

			String[] name=lid.split("\\,");
			String type1 = request.getParameter("type1");
			String aname = request.getParameter("aname");
			String sid1 = request.getParameter("sid1");
			try{


			   for(int i=1;i<name.length;i++){

					String[] name1 = name[i].split("\\ \\$ ");
		    		bo.deleteSite(name1[0],name1[1],name1[2]);

			   }
			   List zuhe_list = lib.initarea();
				List list=bo.siteinit(sid1);
				request.setAttribute("relist", list);
				request.setAttribute("zuhe_list",zuhe_list);
			   ActionMessages messages = new ActionMessages();
	    		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.delete.cardid"));
	    		saveMessages(request, messages); 
			}catch(Exception e){
				log.error("==��Ա��������--ɾ���ص�==",e);
		    }

			   request.setAttribute("sid", sid1);
				request.setAttribute("type", type1);
				request.setAttribute("name", aname);
			   	return mapping.findForward("siteinit");
	       }

	
	/**
	 * ɾ��
	 * */
	
	public ActionForward deleteTime(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {   
		WordlibBO lib=new WordlibBO();
		PworksetBO  bo=new PworksetBO();
		String lid=request.getParameter("lid");
		String[] name=lid.split("\\,");

		String type1 = request.getParameter("type1");
		String id = request.getParameter("id");
		String aname = request.getParameter("aname");

		try{
			for(int i=1;i<name.length;i++){

				bo.deleteTime(name[i]);
			}
			List zuhe_list = lib.initarea();
			List list=bo.siteinit(id);
			request.setAttribute("relist", list);
			request.setAttribute("zuhe_list",zuhe_list);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.delete.cardid"));
			saveMessages(request, messages); 
		
		}catch(Exception e){
			log.error("==��Ա��������--ɾ��ʱ��==",e);
		}	

		request.setAttribute("sid", id);
		request.setAttribute("type", type1);
		request.setAttribute("name", aname);
		return mapping.findForward("timeinit");
	}
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ���ӵص�����
	 */
	public ActionForward addSite(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PworksetBO  bo=new PworksetBO();
		Global bal=new Global();
		WordlibBO lib=new WordlibBO();
		
		try{
			
			String sid=request.getParameter("sid");
			String name=request.getParameter("name");
			String type=request.getParameter("type");
			String zuhe=request.getParameter("pworkset.zuhe");
			String[] names=zuhe.split("\\:");
			String appointminute=request.getParameter("pworkset.appointminute");
			List zuhe_list = lib.initarea();
			List count=bal.juede3(" select stafferid,idtype,appointid from stafferworkset where stafferid=? and idtype= ? and appointid=? ",sid,names[0],names[1]);
			if( count != null && count.size()> 0){
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("false.yuangong.didian"));
				saveMessages(request, messages);				
				
			}else{
				bo.addSite(sid,names[0],names[1],appointminute);
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.add.locator"));
				saveMessages(request, messages);
			}
			List list = bo.siteinit(sid);
			request.setAttribute("sid", sid);
			request.setAttribute("type", type);
			request.setAttribute("name", name);
			
			request.setAttribute("relist", list);
			request.setAttribute("zuhe_list",zuhe_list);

		}catch(Exception e){
			log.error("==��Ա��������--���ӵص�==",e);
		}
		return  mapping.findForward("siteinit");
	}
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ����ͨ����������ʱ��ҳ��
	 */
	public ActionForward addgongzhong(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		WordlibBO lib=new WordlibBO();
		try{
			
			List type=lib.workType();
			request.setAttribute("workType_list",type);
			

		}catch(Exception e){
			log.error("==��Ա��������--����ͨ����������ʱ��ҳ��==",e);
		}
		return mapping.findForward("addgongzhong");
	}
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ���ӹ��ַ�Χ��ʱ������
	 */
	public ActionForward addgzTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		Search_Pworkset mou=(Search_Pworkset)dy.get("pworkset");
		PworksetBO  bo=new PworksetBO();
		WordlibBO lib=new WordlibBO();
		try{
			if(mou.getWorktype().equals("ȫ������")){
				lib.getstaffer1();
				bo.addgzTime(mou);
			}else{
				lib.getstaffer(mou.getWorktype());
				bo.addgzTime(mou);
			}
				
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.add.locator"));
				saveMessages(request, messages);
		}catch(Exception e){
			log.error("==��Ա��������--���ӹ��ַ�Χ��ʱ������==",e);
		}
		
		return  mapping.findForward("addgzTime");
	}
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ����ʱ������
	 */
	public ActionForward addTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		Search_Pworkset mou=(Search_Pworkset)dy.get("pworkset");
		PworksetBO  bo=new PworksetBO();
		Global bal=new Global();
		WordlibBO lib=new WordlibBO();
		try{
			
			String sid=request.getParameter("sid");
			String name=request.getParameter("name");
			String type=request.getParameter("type");
			StringBuffer sb=new StringBuffer();
			sb.append(" select  *  from workovertime where stafferid= ? ");
			
				List list=bal.juede(sb.toString(), sid);
				if(list.size()>0){
					bo.updateTime(mou,sid);
					ActionMessages messages = new ActionMessages();
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.update.locator"));
					saveMessages(request, messages);
				}else{
					bo.addTime(mou,sid);
					ActionMessages messages = new ActionMessages();
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.add.locator"));
					saveMessages(request, messages);
				}
				
				List zuhe_list = lib.initarea();
				List alist=bo.timeinit(sid);
				
				request.setAttribute("relist", alist);
				request.setAttribute("zuhe_list",zuhe_list);
			
				request.setAttribute("sid", sid);
				request.setAttribute("type", type);
				request.setAttribute("name", name);


		}catch(Exception e){
			log.error("==��Ա��������--����ʱ��==",e);
		}

		return  mapping.findForward("timeinit");
	}

	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return   ��ʼ���ص�����
	 */
	public ActionForward siteinit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		WordlibBO lib=new WordlibBO();
		PworksetBO  mount=new PworksetBO();
		String sid = request.getParameter("stafferid");
		String type = request.getParameter("worktype");
		String name = request.getParameter("name");
		try{
			List zuhe_list = lib.initarea();
			List list=mount.siteinit(sid);
			request.setAttribute("sid", sid);
			request.setAttribute("type", type);
			request.setAttribute("name", name);
			request.setAttribute("relist", list);
			request.setAttribute("zuhe_list",zuhe_list);
		}catch(Exception e){
			log.error("==��Ա��������--�ص��ʼ��==",e);
		}
		return mapping.findForward("siteinit");
	}
	
	/**
	 * ɾ��
	 * */
	
	public ActionForward deleteSite1(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {   
		PworksetBO  bo=new PworksetBO();
		WordlibBO lib=new WordlibBO();
		String lid=request.getParameter("lid");
		
		String[] name=lid.split("\\,");
		try{
			
			for(int i=1;i<name.length;i++){
				
				String[] name1 = name[i].split("\\ \\$ ");
				
				bo.deleteSite(name1[0],name1[1],name1[2]);
				
			}
			
			List zuhe_list = lib.initarea();
			List list=bo.siteinit1();
			
			request.setAttribute("relist", list);
			
			request.setAttribute("zuhe_list",zuhe_list);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.delete.cardid"));
			saveMessages(request, messages); 
		}catch(Exception e){
			log.error("==��Ա��������--ɾ���ص�==",e);
		}
		return mapping.findForward("siteinit1");
	}
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return   ��ʼ���ص�����
	 */
	public ActionForward siteinit1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		DynaActionForm dy=(DynaActionForm)form;
		WordlibBO lib=new WordlibBO();
		PworksetBO  mount=new PworksetBO();

		try{
//			Search_Pworkset mou=(Search_Pworkset)dy.get("pworkset");
			List zuhe_list = lib.initarea();
			List list=mount.siteinit1();
			
			request.setAttribute("relist", list);
			request.setAttribute("zuhe_list",zuhe_list);
			
//			dy.set("pworkset",mou);//���°�ֵ���û�ҳ�� 
		}catch(Exception e){
			log.error("==��Ա��������--�ص��ʼ��==",e);
		}
		return mapping.findForward("siteinit1");
	}
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return   ��ʼ��ʱ������
	 */
	public ActionForward timeinit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		WordlibBO lib=new WordlibBO();
		PworksetBO  bo=new PworksetBO();
		String sid = request.getParameter("stafferid");
		String type = request.getParameter("worktype");
		String name = request.getParameter("name");
		try{
//			Search_Pworkset mou=(Search_Pworkset)dy.get("pworkset");
			List zuhe_list = lib.initarea();
			List list=bo.timeinit(sid);
			request.setAttribute("sid", sid);
			request.setAttribute("type", type);
			request.setAttribute("name", name);
			request.setAttribute("relist", list);
			request.setAttribute("zuhe_list",zuhe_list);
//			dy.set("pworkset",mou);//���°�ֵ���û�ҳ�� 
		}catch(Exception e){
			log.error("==��Ա��������--ʱ���ʼ��==",e);
		}
		return mapping.findForward("timeinit");
	}
	
	/**
	 * ɾ��
	 * */
	
	public ActionForward deleteTime1(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {   
		WordlibBO lib=new WordlibBO();
		PworksetBO  bo=new PworksetBO();
		String lid=request.getParameter("lid");
		String[] name=lid.split("\\,");

		

		try{
			for(int i=1;i<name.length;i++){

				bo.deleteTime(name[i]);
			}
			List zuhe_list = lib.initarea();
			List list=bo.timeinit1();
			
			request.setAttribute("relist", list);
			request.setAttribute("zuhe_list",zuhe_list);
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.delete.cardid"));
			saveMessages(request, messages); 
		
		}catch(Exception e){
			log.error("==��Ա��������--ɾ��ʱ��==",e);
		}	

		return mapping.findForward("timeinit1");
	}
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return   ��ʼ��ʱ������
	 */
	public ActionForward timeinit1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		DynaActionForm dy=(DynaActionForm)form;
		WordlibBO lib=new WordlibBO();
		PworksetBO  bo=new PworksetBO();
		
		try{
//			Search_Pworkset mou=(Search_Pworkset)dy.get("pworkset");
			List zuhe_list = lib.initarea();
			List list=bo.timeinit1();
			
			request.setAttribute("relist", list);
			request.setAttribute("zuhe_list",zuhe_list);
//			dy.set("pworkset",mou);//���°�ֵ���û�ҳ�� 
		}catch(Exception e){
			log.error("==��Ա��������--ʱ���ʼ��==",e);
		}
		return mapping.findForward("timeinit1");
	}
	
	

}