//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

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

import com.kj133.entity.Ouser;
import com.kj133.entity.Search_updatePassword;
import com.kj133.entity.bo.UserBO;
import com.kj133.util.encrypt;

/** 
 * MyEclipse Struts
 * Creation date: 02-28-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class UpdatePasswordAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request–ﬁ∏ƒ√‹¬Î
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        DynaActionForm dy=(DynaActionForm)form;
//        encrypt et = new encrypt();
        Search_updatePassword uppass=(Search_updatePassword)dy.get("ser_updatePassword");
        String oldpassword1=uppass.getOldPassword();    
        String newpassword1=uppass.getFirstPassword();  
        
        UserBO bo=new UserBO();
        Ouser user=(Ouser)request.getSession().getAttribute("user");
        String userid=user.getUserid();  
        String username = user.getOname();
        
        String oldpassword = oldpassword1;
        String newpassword = newpassword1;
        try{
            if( !oldpassword.equals(user.getOpassword())){
                   ActionMessages messages=new ActionMessages();
                   messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.updatepassword"));
                   saveMessages(request,messages);
                   return mapping.findForward("false");
            }else{
            	  bo.updatePassword(newpassword,userid);
            	  ActionMessages messages=new ActionMessages();
                  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.updatepassword"));
                  saveMessages(request,messages);
                  return mapping.findForward("false");
            }
        }catch(Exception e){
        	log.error("==–ﬁ∏ƒ√‹¬Î==",e);
        }
        	return null;
	}

}

