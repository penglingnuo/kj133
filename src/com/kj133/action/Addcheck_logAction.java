//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;


import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Addcheck_log;
import com.kj133.entity.Ouser;
import com.kj133.entity.Search_addcheck_log;
import com.kj133.entity.bo.Addcheck_logBO;
import com.kj133.entity.bo.WordlibBO;

/** 
 * MyEclipse Struts
 * Creation date: 01-30-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class Addcheck_logAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request ���ӿ��ڼ�¼
	 * @param response
	 * @return ActionForward
	 */

	/**
	 * ��ȡList
	 * */
	public ActionForward getlist(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    DynaActionForm dy=(DynaActionForm)form;
	    Addcheck_logBO bo=new Addcheck_logBO();
        try{
        	Search_addcheck_log locator=(Search_addcheck_log)dy.get("ser_addchecklog");
        	request.getSession().setAttribute("sid", locator.getStafferid());
        	request.getSession().setAttribute("etime", locator.getEtime());
        	request.getSession().setAttribute("stime", locator.getStime());

	        List list= bo.init(locator);
	        request.setAttribute("listCount", list.size());
	        if( list.size()==0){
	        	   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
	        }else{
	        	  request.setAttribute("list",list);
	        	  request.getSession().setAttribute("loactorViewPrint", list);
	        }
		    dy.set("ser_addchecklog",locator);
		 }catch(Exception e){
			 log.error("==���ӿ��ڼ�¼��ѯ==",e);
		 }
			return mapping.findForward("localizer");
	   }


	/**
	 * ��ȡList������ת
	 * */
	public ActionForward getlistadd(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    DynaActionForm dy=(DynaActionForm)form;
	    WordlibBO lib=new WordlibBO(); 
	 
        try{
        	Search_addcheck_log locator=(Search_addcheck_log)dy.get("ser_addchecklog");
        	request.getSession().setAttribute("sid", locator.getStafferid());
        	request.getSession().setAttribute("etime", locator.getEtime());
        	request.getSession().setAttribute("stime", locator.getStime());
        	List banc=lib.banNameNew();
        	List banz=lib.banZhiNameNew();
        	request.setAttribute("banc_list",banc);
        	request.setAttribute("banz_list",banz);
	       
		    dy.set("ser_addchecklog",locator);
		 }catch(Exception e){
			 log.error("==���ӿ��ڼ�¼��ѯ==",e);
		 }
			return mapping.findForward("init");
	   }
	
	/**
	 * ��ȡList�޸���ת
	 * add by YZh
	 * */
	
	public ActionForward getlistupd(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    DynaActionForm dy=(DynaActionForm)form;
	    Addcheck_logBO bo=new Addcheck_logBO();
	    WordlibBO lib=new WordlibBO(); 
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        try{
        	Search_addcheck_log locator=(Search_addcheck_log)dy.get("ser_addchecklog");
        	Ouser user=(Ouser)request.getSession().getAttribute("user");
			List gro=lib.deptSta(user.getUserid()); 
			//List banz=lib.banZhiNameNew();
			List banc=lib.banNameNew(); 
			locator.setStime(df.format(new Date()));
			locator.setEtime(df.format(new Date()));
			String staffName=locator.getStafferid();
			String sid=null;
			if(staffName!=null&&!staffName.equals("")){
				String []sns=staffName.split("--");
				sid=sns[0];
			}
        	request.getSession().setAttribute("sid", sid);
        	//request.getSession().setAttribute("etime", locator.getEtime());
        	//request.getSession().setAttribute("stime", locator.getStime());
        	request.setAttribute("gro_list",gro);
        	//request.setAttribute("banz_list",banz);
        	request.setAttribute("banc_list",banc);
		    dy.set("ser_addchecklog",locator);
		 }catch(Exception e){
			 log.error("==��ѯ���ڼ�¼��ѯ==",e);
		 }
			return mapping.findForward("localizernewupd");
	   }
	
	/**
	 * ��ȡList��ѯ��ת
	 * add by YZh
	 * */
	public ActionForward getlistnew(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    DynaActionForm dy=(DynaActionForm)form;
	    Addcheck_logBO bo=new Addcheck_logBO();
	    WordlibBO lib=new WordlibBO(); 
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        try{
        	Search_addcheck_log locator=(Search_addcheck_log)dy.get("ser_addchecklog");
        	Ouser user=(Ouser)request.getSession().getAttribute("user");
			List gro=lib.deptSta(user.getUserid()); 
			//List banz=lib.banZhiNameNew();
			List banc=lib.banNameNew(); 
			locator.setStime(df.format(new Date()));
			locator.setEtime(df.format(new Date()));
        	request.getSession().setAttribute("sid", locator.getStafferid());
        	//request.getSession().setAttribute("etime", locator.getEtime());
        	//request.getSession().setAttribute("stime", locator.getStime());
        	request.setAttribute("gro_list",gro);
        	//request.setAttribute("banz_list",banz);
        	request.setAttribute("banc_list",banc);
		    dy.set("ser_addchecklog",locator);
		 }catch(Exception e){
			 log.error("==��ѯ���ڼ�¼��ѯ==",e);
		 }
			return mapping.findForward("localizernew");
	   }

	
	/**
	 * ��ȡList��ѯ��תɾ��ʹ��
	 * add by YZh
	 * */
	public ActionForward getlistnewdel(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    DynaActionForm dy=(DynaActionForm)form;
	    Addcheck_logBO bo=new Addcheck_logBO();
	    WordlibBO lib=new WordlibBO(); 
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        try{
        	Search_addcheck_log locator=(Search_addcheck_log)dy.get("ser_addchecklog");
        	Ouser user=(Ouser)request.getSession().getAttribute("user");
			List gro=lib.deptSta(user.getUserid()); 
			//List banz=lib.banZhiNameNew();
			List banc=lib.banNameNew(); 
        	
			locator.setStime(df.format(new Date()));
			locator.setEtime(df.format(new Date()));
        	request.getSession().setAttribute("sid", locator.getStafferid());
        	request.getSession().setAttribute("etime", locator.getEtime());
        	request.getSession().setAttribute("stime", locator.getStime());
        	request.setAttribute("gro_list",gro);
        	//request.setAttribute("banz_list",banz);
        	request.setAttribute("banc_list",banc);
		    dy.set("ser_addchecklog",locator);
		 }catch(Exception e){
			 log.error("==��ѯ���ڼ�¼��ѯ==",e);
		 }
			return mapping.findForward("localizernewdel");
	   }

	
	/**
	 * ��ȡ��ѯ����List
	 * add by YZh
	 * */
	public ActionForward getlistworkatt(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    DynaActionForm dy=(DynaActionForm)form;
	    Addcheck_logBO bo=new Addcheck_logBO();
	    WordlibBO lib=new WordlibBO(); 
	    Pagination pagination = new Pagination();
	    String page =(String)request.getParameter("page");//��ȡҳ��
	    
        try{
        	Search_addcheck_log locator=(Search_addcheck_log)dy.get("ser_addchecklog");
        	Ouser user=(Ouser)request.getSession().getAttribute("user");
			List gro=lib.deptSta(user.getUserid()); 
			List banc=lib.banNameNew(); 
			 pagination.setCount(100); 
		     if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      }
			String staffName=locator.getStafferid();
			String sid=null;//Ա�����
			if(staffName!=null &&!staffName.equals("")){
				String [] sns=staffName.split("--");
				sid=sns[0];
			}
        	request.getSession().setAttribute("sid", sid);
        	request.getSession().setAttribute("etime", locator.getEtime());
        	request.getSession().setAttribute("stime", locator.getStime());
        	locator.setUserid(user.getUserid());
        	request.setAttribute("gro_list",gro);
        	request.setAttribute("banc_list",banc);
        	List list= bo.initnew(locator,pagination);
	        request.setAttribute("listCount", list.size());
	        if( list.size()==0){
	        	   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
	        }else{
	        	  request.setAttribute("list",list);
	        	  request.getSession().setAttribute("loactorViewPrint", list);
	        }
		    dy.set("ser_addchecklog",locator);
		    request.setAttribute("pagination", pagination);
		 }catch(Exception e){
			 log.error("==��ѯ���ڼ�¼��ѯ==",e);
		 }
			return mapping.findForward("localizernew");
	   }
	/**
	 * ��ȡ��ѯ����List �޸���
	 * add by YZh
	 * */
	public ActionForward getlistworkattupd(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    DynaActionForm dy=(DynaActionForm)form;
	    Addcheck_logBO bo=new Addcheck_logBO();
	    WordlibBO lib=new WordlibBO(); 
	    Pagination pagination = new Pagination();
	    String page =(String)request.getParameter("page");//��ȡҳ��
	    
        try{
        	Search_addcheck_log locator=(Search_addcheck_log)dy.get("ser_addchecklog");
        	Ouser user=(Ouser)request.getSession().getAttribute("user");
			List gro=lib.deptSta(user.getUserid()); 
			List banc=lib.banNameNew(); 
			 pagination.setCount(100); 
		     if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      }
		     String staffName=locator.getStafferid();
				String sid=null;//Ա�����
				if(staffName!=null &&!staffName.equals("")){
					String [] sns=staffName.split("--");
					sid=sns[0];
				}
        	request.getSession().setAttribute("sid", sid);
        	request.getSession().setAttribute("etime", locator.getEtime());
        	request.getSession().setAttribute("stime", locator.getStime());
        	locator.setUserid(user.getUserid());
        	request.setAttribute("gro_list",gro);
        	request.setAttribute("banc_list",banc);
        	List list= bo.initnew(locator,pagination);
	        request.setAttribute("listCount", list.size());
	        if( list.size()==0){
	        	   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
	        }else{
	        	  request.setAttribute("list",list);
	        	  request.getSession().setAttribute("loactorViewPrint", list);
	        }
		    dy.set("ser_addchecklog",locator);
		    request.setAttribute("pagination", pagination);
		 }catch(Exception e){
			 log.error("==��ѯ���ڼ�¼��ѯ==",e);
		 }
			return mapping.findForward("localizernewupd");
	   }
	
	/**
	 * ��ȡ��ѯ����List �޸�-��ת��
	 * add by YZh
	 * */
	public ActionForward getlistworkattupdnew(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    DynaActionForm dy=(DynaActionForm)form;
	    Addcheck_logBO bo=new Addcheck_logBO();
	    WordlibBO lib=new WordlibBO();
	    String stafferidnew = request.getParameter("stafferid");
	    String downtimenew1 = request.getParameter("downtime");
	    String downtimenew = downtimenew1;
	    if(("".equals(downtimenew1)) || (downtimenew1==null)){
	    	 downtimenew = downtimenew1;
	    }else if(downtimenew1.length()>=19){
	    	 downtimenew = downtimenew1.substring(0,19);
	    }else{
	    	 downtimenew = downtimenew1;
	    }
	    
        try{
        	
        	Search_addcheck_log locator=(Search_addcheck_log)dy.get("ser_addchecklog");
        	Ouser user=(Ouser)request.getSession().getAttribute("user");
        	List<Addcheck_log> workaddlist=bo.updateBefor(stafferidnew, downtimenew);
        	Iterator<Addcheck_log> iter =  workaddlist.iterator();
			while(iter.hasNext()){
				Addcheck_log al= iter.next();
				locator.setCardid(al.getCardid());
				locator.setEtime(al.getUptime());
				locator.setEtimenew(al.getUptime());
				locator.setKqtime(al.getKqtime());
				locator.setBantypename(al.getBantypename());
				locator.setBanname(al.getBanname());
				locator.setModifyreason(al.getModifyreason());
			}
        	
        	locator.setStime(downtimenew);
        	locator.setStimenew(downtimenew);
        	locator.setStafferid(stafferidnew);
        	
        	List banz=lib.banZhiNameNew(); 
			List banc=lib.banNameNew(); 
			request.setAttribute("banc_list",banc);
        	request.setAttribute("banz_list",banz);
			dy.set("ser_addchecklog",locator);
        	
		 }catch(Exception e){
			 log.error("==�޸Ŀ��ڼ�¼��ѯ==",e);
		 }
			return mapping.findForward("localizernewupdnew");
	   }
	
	/**
	 * ��ȡ��ѯ����List ɾ����
	 * add by YZh
	 * */
	public ActionForward getlistworkattdel(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    DynaActionForm dy=(DynaActionForm)form;
	    Addcheck_logBO bo=new Addcheck_logBO();
	    WordlibBO lib=new WordlibBO(); 
	    Pagination pagination = new Pagination();
	    String page =(String)request.getParameter("page");//��ȡҳ��
	    
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		
	    
        try{
        	Search_addcheck_log locator=(Search_addcheck_log)dy.get("ser_addchecklog");
        	Ouser user=(Ouser)request.getSession().getAttribute("user");
        	//locator.setStime(df.format(new Date()));
    		//locator.setEtime(df.format(new Date()));
			List gro=lib.deptSta(user.getUserid()); 
			List banc=lib.banNameNew(); 
			 pagination.setCount(100); 
		     if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      }
		     String staffName=locator.getStafferid();
		     String sid=null;//Ա�����
				if(staffName!=null &&!staffName.equals("")){
					String [] sns=staffName.split("--");
					sid=sns[0];
				}
        	request.getSession().setAttribute("sid", sid);
        	request.getSession().setAttribute("etime", locator.getEtime());
        	request.getSession().setAttribute("stime", locator.getStime());
        	locator.setUserid(user.getUserid());
        	request.setAttribute("gro_list",gro);
        	request.setAttribute("banc_list",banc);
        	List list= bo.initnew(locator,pagination);
	        request.setAttribute("listCount", list.size());
	        if( list.size()==0){
	        	   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
	        }else{
	        	  request.setAttribute("list",list);
	        	  request.getSession().setAttribute("loactorViewPrint", list);
	        }
		    dy.set("ser_addchecklog",locator);
		    request.setAttribute("pagination", pagination);
		 }catch(Exception e){
			 log.error("==ɾ�����ڼ�¼��ѯ==",e);
		 }
			return mapping.findForward("localizernewdel");
	   }
	
	/**
	 * ----------------������ʼ��-------------
	 * */
	public ActionForward initializationadd(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Search_addcheck_log locator=(Search_addcheck_log)dy.get("ser_addchecklog");
			locator.setStafferid(request.getSession().getAttribute("sid").toString());
			locator.setEtime(request.getSession().getAttribute("etime").toString());
			String stime=request.getSession().getAttribute("stime").toString();
			if(("".equals(stime)) || (stime==null)){
				locator.setStime(stime);
			}else if(stime.length()>=10){
				String stime1=stime.substring(0,10);
				locator.setStime(stime1);
			}else{
				locator.setStime(stime);
			}
			
			//locator.setStime(request.getSession().getAttribute("stime").toString());
			
			request.setAttribute("listCount", "0");
			dy.set("ser_addchecklog",locator);
		}catch(Exception e){
			log.error("==���ӿ��ڼ�¼��ʼ��==",e);
		}
		return mapping.findForward("localizernew");
	}
	        /**
	         * ��ʼ��
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
	        		log.error("==���ӿ��ڼ�¼��ѯ==",e);
	        	}
	        	return mapping.findForward("localizer");
	        }
        
        /**
    	 * ��ӡԤ��
    	 * */
    	@SuppressWarnings("unchecked")
    	public ActionForward viewPrint(
    			ActionMapping mapping,ActionForm form,
    			HttpServletRequest request,HttpServletResponse response) throws IOException{
    		    ServletContext context =this.getServlet().getServletConfig().getServletContext();
    		    response.setContentType("text/html"); 
    			try{
    				 List list=(List)request.getSession().getAttribute("loactorViewPrint");   
    		    	 File reportFile = new File(context.getRealPath("/reports/locator.jasper"));
    	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
    	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
    	 			 HashMap map=new HashMap();
    	 			
    	 			 JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,map,dataSource);
    	 			 request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
    	    		 
    	 			 JRHtmlExporter exporter = new JRHtmlExporter();
    	 			 int pageIndex = 0;
    	 			 int lastPageIndex = 0;
    	 			 if (jasperPrint.getPages() != null)
    	 			 {
    	 				lastPageIndex = jasperPrint.getPages().size() - 1;
    	 			 }
    	 			 String pageStr = request.getParameter("page");
    	 			 try
    	 			 {
    	 				pageIndex = Integer.parseInt(pageStr);
    	 			 }
    	 			 catch(Exception e)
    	 			 {
    	 				 
    	 			 }	 	 		
    	 			 if (pageIndex < 0)
    	 			 {
    	 				pageIndex = 0;
    	 			 }
    	 			 if (pageIndex > lastPageIndex)
    	 			 {
    	 			 	pageIndex = lastPageIndex;
    	 			 }
    	 			 request.setAttribute("pageIndex", pageIndex);
    	 			 request.setAttribute("lastPageIndex", lastPageIndex);
    	 			
    	    		 StringBuffer sbuffer = new StringBuffer();
    	    		 request.setAttribute("sb", sbuffer);
    	    		  
    	    		 exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);//�Ե���ģ����в���
    	    	     exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
    	    	     //exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "imagesĿ¼��·��?image=");
                     //������htmlԤ����ʱ�������һ��px�ļ��������images_uri��·�������Ǹ�px��·�������鲻�ã��������� �����Ϳ����ˡ������� 
    	    	     //��д�Ļ���ҳ���Ͼͻ���ʾ�ܶ�ͼƬ������������ͼƬ���ز��ɹ���״̬
    	    	     exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
    	    	     exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(pageIndex));
          	         exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
                     exporter.exportReport();
    		    }catch(Exception e){
    		    	e.printStackTrace();
    		    }
    	        return mapping.findForward("vprint");
    	}
    	
    	/**
    	 *��ӡ
    	 * */
    	@SuppressWarnings("unchecked")
    	public ActionForward print(
    			ActionMapping mapping, ActionForm form,
    			HttpServletRequest request,HttpServletResponse response) throws IOException {
    	     	ServletContext context = this.getServlet().getServletConfig().getServletContext();
    	   	     File reportFile = new File(context.getRealPath("/reports/locator.jasper"));
    	   	     HashMap map=new HashMap();
    			JasperPrint jasperPrint = null;
    		    try
    			{
    				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
    				List list=(List)request.getSession().getAttribute("loactorViewPrint");
    				JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
    				jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource );
    			}
    			catch (Exception e)
    			{
    			  e.printStackTrace();
    			}
    			if (jasperPrint != null)
    			{
    				response.setContentType("application/octet-stream");
    				ServletOutputStream ouputStream = response.getOutputStream();			
    				ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
    				oos.writeObject(jasperPrint);
    				oos.flush();
    				oos.close();
    				ouputStream.flush();
    				ouputStream.close();
    			}
    			else
    			{
    				response.setContentType("text/html");
    				PrintWriter out = response.getWriter();
    				out.println("<html>");
    				out.println("<head>");
    				out.println("<title>JasperReports - Web Application Sample</title>");
    				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
    				out.println("</head>");
    				out.println("<body bgcolor=\"white\">");
    				out.println("<span class=\"bold\">locator.Jasper�ļ������ڻ��������</span>");
    				out.println("</body>");
    				out.println("</html>");
    			}			
    		 return null;
    	  }
    	
}

