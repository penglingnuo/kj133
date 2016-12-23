//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.util.Calendar;
import java.util.List;
import java.util.Iterator;

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
import com.kj133.entity.Search_addcheck_log;
import com.kj133.entity.Wordlib;
import com.kj133.entity.bo.Addcheck_logBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-11-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Aaddcheck_logAction extends DispatchAction {

	private final Logger  log=Logger.getLogger(this.getClass());
	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request增加考勤记录里面的add,update,delete
	 * @param response
	 * @return ActionForward
	 */
	
	

    /**
     * 初始化
     * */
    public ActionForward initialization(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
    	DynaActionForm dy=(DynaActionForm)form;
    	try{
    		Search_addcheck_log locator=(Search_addcheck_log)dy.get("ser_addchecklog");
    		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		java.util.Date date = new java.util.Date();
    		java.util.Calendar c = Calendar.getInstance();
    		c.add(Calendar.HOUR, -12);
    		String stime = format.format( c.getTime());

    		String etime = format.format(date); 
    		
    		locator.setStime(stime);
		    locator.setEtime(etime);  
		       
		       request.setAttribute("listCount", "0");
    		dy.set("ser_addchecklog",locator);
    	}catch(Exception e){
    		log.error("==个人考勤记录初始化==",e);
    	}
    	return mapping.findForward("init");
    }
	
    /**
     * 保存
     * */
    public ActionForward save(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		Addcheck_logBO bo=new Addcheck_logBO();
		WordlibBO lib=new WordlibBO(); 
	    	Global glo=new Global();
	    	Ouser user=(Ouser)request.getSession().getAttribute("user");
    		String sid=request.getParameter("ser_addchecklog.stafferid");
    		String stime=request.getParameter("ser_addchecklog.stime");
    		String etime=request.getParameter("ser_addchecklog.etime");
    		String banc=request.getParameter("ser_addchecklog.banname");
    		String banz=request.getParameter("ser_addchecklog.bantypename");
    		//String bancid=request.getParameter("ser_addchecklog.banid");
    		//String banzid=request.getParameter("ser_addchecklog.bantypeid");
    		String modifyreason=request.getParameter("ser_addchecklog.modifyreason");
    		String userid=user.getUserid();
    		int banid=1;
    		int bantypeid=0;
    		String cardid="";
    		
			try {
				List<Wordlib> listbanname=lib.banNameNewBy(banc);
				Iterator<Wordlib> iter =  listbanname.iterator();
					while(iter.hasNext()){
						Wordlib wb= iter.next();
						banid=wb.getBanid();
					}
				List<Wordlib> listbantypename=lib.banZhiNameNewBy(banz);
				Iterator<Wordlib> iter1 =  listbantypename.iterator();
				while(iter1.hasNext()){
					Wordlib wb= iter1.next();
					bantypeid=wb.getBantypeid();
				}
				List<Wordlib> listcardid=lib.getCardId(sid);
				Iterator<Wordlib> iter2 =  listcardid.iterator();
				while(iter2.hasNext()){
					Wordlib wb= iter2.next();
					cardid=wb.getCardid();
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
    		
    		
    		request.getSession().setAttribute("sid",sid);
    		request.getSession().setAttribute("stime",stime);
    		request.getSession().setAttribute("etime",etime);
	    	try{
	    		List count=glo.juede2(" select top 1 cardid,stafferid from WorkattendanceEx where stafferid=? and  ((downtime>=? and downtime<=?) or (uptime>=? and uptime<=?)) ",sid,stime,etime);
	    		if( count != null && count.size()> 0){
	    			ActionMessages messages = new ActionMessages();
	    			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("yes.check.log"));
	    			saveMessages(request, messages);
	    		}else{
					bo.save2(stime,etime,sid,banid,bantypeid,modifyreason,userid,cardid);
					bo.save3(stime,etime,sid,banid,bantypeid,modifyreason,userid,cardid);
	    			ActionMessages messages = new ActionMessages();
	    		    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.add.locator"));
	    		    saveMessages(request, messages);
	    		}      
	    	}catch(Exception e){
	    		log.error("==增加个人考勤记录==",e);
	    	}
	    	return  mapping.findForward("save");
		
	   }
	/**
     * 修改考勤信息
     * */
	public ActionForward updatenew(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		Addcheck_logBO bo=new Addcheck_logBO();
		WordlibBO lib=new WordlibBO(); 
	    	Global glo=new Global();
	    	Ouser user=(Ouser)request.getSession().getAttribute("user");
    		String sid=request.getParameter("ser_addchecklog.stafferid");
    		String stime=request.getParameter("ser_addchecklog.stime");
    		String etime=request.getParameter("ser_addchecklog.etime");
    		String banc=request.getParameter("ser_addchecklog.banname");
    		String banz=request.getParameter("ser_addchecklog.bantypename");
    		String downtimenew=request.getParameter("ser_addchecklog.stimenew");
    		String uptimenew=request.getParameter("ser_addchecklog.etimenew");
    		String downtime = downtimenew;
    		String uptime=uptimenew;
    	    if(("".equals(downtimenew)) || (downtimenew==null)){
    	    	downtime = downtimenew;
    	    }else if(downtimenew.length()>=19){
    	    	downtime = downtimenew.substring(0,19);
    	    }else{
    	    	downtime = downtimenew;
    	    }
//    	   
    		String kqtime=request.getParameter("ser_addchecklog.kqtime");
    		//String bancid=request.getParameter("ser_addchecklog.banid");
    		//String banzid=request.getParameter("ser_addchecklog.bantypeid");
    		String modifyreason=request.getParameter("ser_addchecklog.modifyreason");
    		String userid=user.getUserid();
    		int banid=1;
    		int bantypeid=0;
    		
			try {
				List<Wordlib> listbanname=lib.banNameNewBy(banc);
				Iterator<Wordlib> iter =  listbanname.iterator();
					while(iter.hasNext()){
						Wordlib wb= iter.next();
						banid=wb.getBanid();
					}
				List<Wordlib> listbantypename=lib.banZhiNameNewBy(banz);
				Iterator<Wordlib> iter1 =  listbantypename.iterator();
				while(iter1.hasNext()){
					Wordlib wb= iter1.next();
					bantypeid=wb.getBantypeid();
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
    		
    		//request.getSession().setAttribute("sid",sid);
    		request.getSession().setAttribute("stime",stime);
    		//request.getSession().setAttribute("etime",etime);
	    	try{
	    		List count=glo.juede22(" select top 1 cardid,stafferid from WorkattendanceEx where stafferid=? and  ( (uptime>=? and uptime<?) or (uptime>=? and uptime<=?)) and downtime<>? ",sid,stime,etime,downtime);
	    		//List count=glo.juede2(" select top 1 cardid,stafferid from WorkattendanceEx where stafferid=? and  ((downtime>=? and downtime<=?) or (uptime>=? and uptime<=?)) ",sid,stime,etime);
	    		if( count != null && count.size()> 0){
	    			ActionMessages messages = new ActionMessages();
	    			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("yes.check.log"));
	    			saveMessages(request, messages);
	    		}else{
					bo.update2(stime,etime,sid,banid,bantypeid,kqtime,modifyreason,userid,downtime);
					bo.update3(stime,etime,sid,banid,bantypeid,kqtime,modifyreason,userid,downtime);
					bo.update4(stime,etime,sid,banid,bantypeid,kqtime,modifyreason,userid,downtime,uptime);
	    			ActionMessages messages = new ActionMessages();
	    		    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.update.cardid"));
	    		    saveMessages(request, messages);
	    		}      
	    	}catch(Exception e){
	    		log.error("==修改考勤信息==",e);
	    	}
	    	return  mapping.findForward("localizernewupd");
		
	   }
		
		/**
		 * 删除
		 * */
		
		public ActionForward delete(
				ActionMapping mapping,
				ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response) {   
			Addcheck_logBO bo=new Addcheck_logBO();
		    String lid=request.getParameter("lid");
		    String[] name=lid.split("\\,");
		    for(int i=1;i<name.length;i++){
		    	String[] name1 = name[i].split("\\ \\$ ");
		    	try{
		    		bo.delete(name1[0],name1[1]);
		    		ActionMessages messages = new ActionMessages();
		    		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.delete.cardid"));
		    		saveMessages(request, messages); 
		    		
		    	}catch(Exception e){
		    		log.error("==删除个人考勤记录==",e);
		    	}
		    }	 
		    return mapping.findForward("delete");
		}
}

