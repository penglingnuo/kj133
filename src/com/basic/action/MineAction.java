package com.basic.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.speedframework.entity.Pagination;

import com.basic.entity.Search_WorkAtt;
import com.basic.entity.bo.PersonMineBO;
import com.basic.entity.vo.MineVO;
import com.kj133.entity.Ouser;
import com.kj133.entity.bo.WordlibBO;

public class MineAction extends DispatchAction{
	
	/**
     * 初始化 页面跳转
     * */
	public ActionForward init(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		
			return mapping.findForward("initbasic");
		}
	
	/**条件查询*/
	public ActionForward getList(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
	        DynaActionForm dy=(DynaActionForm)form;
	        PersonMineBO bo=new PersonMineBO();
	        Ouser user =(Ouser)request.getSession().getAttribute("user");
	        
	         try{
		        	 Search_WorkAtt workatt=(Search_WorkAtt)dy.get("search_workatt");
				    	workatt.setUserid(user.getUserid());
				    	List<MineVO> list=bo.getList(workatt);
					    if( list.size() ==0){
					        	   ActionMessages messages=new ActionMessages();
							       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
							       this.saveMessages(request,messages);
					     }else{
						          request.setAttribute("workperson_list",list); 
					        }
		        	 
		        	
	         }catch(Exception e){
	        	 log.error("==人员搜索==",e);
	         }
			return mapping.findForward("showbasic");
		}
}
