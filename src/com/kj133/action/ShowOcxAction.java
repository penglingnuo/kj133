/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.kj133.action;


 
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import com.kj133.entity.Ouser;
import com.kj133.entity.Search_Ocx;
import com.kj133.entity.bo.WebInitializeBO;
import com.kj133.entity.vo.WebInitializeVO;
import com.kj133.util.TestID;
import com.kj133.util.TestValue;

/** 
 * MyEclipse Struts
 * Creation date: 03-27-2008
 * 
 * XDoclet definition:
 * @struts.action parameter="method" validate="true"
 */

public class ShowOcxAction extends DispatchAction {
	/*
	WebInitialize(
	const strCon, //数据库连接字符串
	strUser,//用户
	 svIP,//采集机IP
	wMapID: WideString; //地图号
	svPort,//采集机端口
	KeepDataTime,//报警保留时间  60分钟
	overminute,//超时报警时间   720
	Reoverminute: SYSUINT;//严重超时报警时间  720
	LocateType,//默认定位方式,1=区域；0=精确  1
	LostLocator:integer//定位中断保留的论询次数。-1：一直保留，0，不保留    -1
	);
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response  初始化OCX  
	 * @return ActionForward
	 */
	
	 
	private final Logger log=Logger.getLogger(this.getClass());
	
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    WebInitializeBO bo=new WebInitializeBO();
		    try{
		    	Ouser user = (Ouser)request.getSession().getAttribute("user");
		    	String userId=user.getUserid();
		    	request.setAttribute("uid", userId);//用户名
		    	List list=bo.init();
		    	if( list.size()<1 ){ //初始化的时候，表里面没有数据,跳转到添加页面
		    		  return mapping.findForward("ocxAddJsp");
		    	}else{
		    	WebInitializeVO web=(WebInitializeVO)list.get(0);
		    	request.getSession().setAttribute("con", web.getStrcon());//数据库连接字符串
	        	request.setAttribute("ip", web.getSvip());//采集机IP
	        	request.setAttribute("mapid", web.getWmapid());//打开的地图号
	        	request.setAttribute("post", web.getSvport());//采集机端口
	            request.setAttribute("keepdatatime", web.getKeepdatatime());//报警保留时间,默认时间60分
	            request.setAttribute("overminute", web.getOverminute());//井下超时
	            request.setAttribute("removeminute", web.getReoverminute());//严重超时
	            request.setAttribute("locatetype", web.getLocatetype());//默认定位方式
	            request.setAttribute("lostlocator", web.getLostlocator());//定位中断保留的论询次数
	            //之所以写成的request.getSession是因为可以节省一次读取webinitialize表。
	            //WebInitializeVO web=(WebInitializeVO)list.get(0);就是读了一次数据库
	            //加载的时候同样要读取webinitialize
	            
	            return mapping.findForward("init");
	             
		    	}
		    }catch(Exception e){
		    	log.error("==初始化OCX错误==", e);
		    	e.printStackTrace();
		    }
		       return null;
	 }
	
	/***
     * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return  增加OCX配置参数
	 */
	public ActionForward ocxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    DynaActionForm dy=(DynaActionForm)form;
		    WebInitializeBO bo=new WebInitializeBO();
		    try{
		    	 Search_Ocx ocx=(Search_Ocx)dy.getMap().get("search_ocx");
		    	 bo.add(ocx,0);
		    }catch(Exception e){
		    	log.error("==增加OCX配置参数错误==", e);
		    	e.printStackTrace();
		    }
		        return mapping.findForward("ocxAdd");
	 }
	
	
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return  加载参数设置
	 */
	public ActionForward ocxLoad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    DynaActionForm dy=(DynaActionForm)form;
		    WebInitializeBO bo=new WebInitializeBO();
		    try{
		    	Search_Ocx  ocx=(Search_Ocx)dy.getMap().get("search_ocx");
		    	List list=bo.init();
		    	WebInitializeVO web=(WebInitializeVO)list.get(0);
		    	String con=web.getStrcon();
		        int passwordBeg=con.indexOf("Password");
		    	int passwordEnd=con.indexOf(";Persist");
		    	String dbpassword=con.substring(passwordBeg+9, passwordEnd);
                ocx.setDbpassword(dbpassword);//数据库密码
                
                int dbusernameBeg=con.indexOf("ID=");
                int dbusernameEnd=con.indexOf(";Initial");
                String dbusername=con.substring(dbusernameBeg+3,dbusernameEnd);
                ocx.setDbusername(dbusername);//数据库用户名
                
                int dbnameBeg=con.indexOf("Catalog=");
                int dbnameEnd=con.indexOf(";Data Source");
                String dbname=con.substring(dbnameBeg+8, dbnameEnd);
                ocx.setDbname(dbname);//数据库名称
                
                int dbipBeg=con.indexOf("Source=");
                String dbip=con.substring(dbipBeg+7, con.length());
                
                ocx.setDbip(dbip);//数据库Ip
                
		        String ip=web.getSvip();
		    	ocx.setCip(ip);//采集服务器IP
		    	String mid=web.getWmapid();
		    	ocx.setMid(mid);//地图号
		    	String post=web.getSvport();
                ocx.setCpost(post);//端口号
                String overminute=web.getOverminute();
                ocx.setOverminute(overminute);//井下超时
                String removeminute=web.getReoverminute();
                ocx.setReoverminute(removeminute);//严重超时
                String locatetype=web.getLocatetype();
                ocx.setLocatetype(locatetype);//默认定位方式
                String lostlocator=web.getLostlocator();
                ocx.setLostlocator(lostlocator);//定位中断保留的论询次数
                //不给他设置为0，那么就有可能保存上次设置的参数为-1
                ocx.setHiddenlocator("0");
		    	dy.set("search_ocx",ocx); 
		    }catch(Exception e){
		    	log.error("==加载OCX参数设置错误==", e);
		    	e.printStackTrace();
		    }
		        return mapping.findForward("ocxLoad");
	 }
	
	/***
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return  修改OCX参数
	 */
	public ActionForward ocxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    DynaActionForm dy=(DynaActionForm)form;
		    WebInitializeBO bo=new WebInitializeBO();
		    try{
		    	  Search_Ocx ocx=(Search_Ocx)dy.getMap().get("search_ocx"); 
	        	  bo.add(ocx,1);
		    }catch(Exception e){
		    	log.error("==修改OCX参数错误==", e);
		    	e.printStackTrace();
		    }
		        return mapping.findForward("ocxUpdate");
	 }
	
	
	
	/***
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return   解析OCX返回值
	 */
	@SuppressWarnings("unchecked")
	public ActionForward ocxEvent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    try{
		    	 
		    	 List reList=new ArrayList();
		    	 List reList2=new ArrayList();
		    	 TestID tid=null;
		    	 TestValue tva=null;
		    	 String id=request.getParameter("id");
		    	 String id_va=request.getParameter("id_value");
		    	  
		    	 String[] spid=id.split("\\,");//id
		    	 for(int i=0;i<spid.length;i++){
		    	     tid=new TestID(spid[i]);
		    	     reList.add(tid);     
		    	 }
		        
		    	 String[] spva=id_va.split("\\,");//id_value
		         for(int j=0;j<spva.length;j++){
		        	  tva=new TestValue(spva[j]);
		        	  reList2.add(tva);
		         }
		         request.setAttribute("re", reList);
		         request.setAttribute("re2", reList2);
		         
		    }catch(Exception e){
		    	  log.error("==解析xml错误==", e);
		    	  e.printStackTrace();
		    }
		        return mapping.findForward("show");
	 }
	
	/***
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return    获取onData方法的AllData并发送
	 */
	public ActionForward onData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    try{
		    	 
//		    	 System.out.println(request.getParameter("id"));
		    	 String resu=request.getParameter("resu"); //AllData
		    	 String utfXml =java.net.URLDecoder.decode(resu, "UTF-8");
		         SAXBuilder sax = new SAXBuilder();  
		         Reader in= new StringReader(utfXml);
		     	 Document doc = sax.build(in); 
				 Element root = doc.getRootElement();
				 StringBuffer strQuery=new StringBuffer();
				 strQuery.append("//card");
				 List find = XPath.selectNodes(root,strQuery.toString());
				 
				 StringBuffer sb=new StringBuffer();
				 sb.append("<CARDDATA>");
				 for (int i = 0; i < find.size(); i++) {
					Element findelement = (Element) find.get(i);
					sb.append("<CARD>");
					sb.append("<A>"+findelement.getAttributeValue("a")+"</A>");
					sb.append("<B>"+findelement.getAttributeValue("b")+"</B>");
					sb.append("<C>"+findelement.getAttributeValue("c")+"</C>");
					sb.append("<D>"+findelement.getAttributeValue("d")+"</D>");
					sb.append("<E>"+findelement.getAttributeValue("e")+"</E>");
					sb.append("<F>"+findelement.getAttributeValue("f")+"</F>");
					sb.append("<G>"+findelement.getAttributeValue("g")+"</G>");
					sb.append("<H>"+findelement.getAttributeValue("h")+"</H>");
					sb.append("<I>"+findelement.getAttributeValue("i")+"</I>");
					sb.append("<J>"+findelement.getAttributeValue("j")+"</J>");
					sb.append("<K>"+findelement.getAttributeValue("k")+"</K>");
					sb.append("<L>"+findelement.getAttributeValue("l")+"</L>");
					sb.append("<M>"+findelement.getAttributeValue("m")+"</M>");
					sb.append("<N>"+findelement.getAttributeValue("n")+"</N>");
					sb.append("<P>"+findelement.getAttributeValue("p")+"</P>");
					sb.append("<Q>"+findelement.getAttributeValue("q")+"</Q>");
					sb.append("<R>"+findelement.getAttributeValue("r")+"</R>");
					sb.append("<S>"+findelement.getAttributeValue("s")+"</S>");
					sb.append("</CARD>");
				 }
			     sb.append("</CARDDATA>");
			     
			     StringBuffer sbXml=new StringBuffer();
    		     sbXml.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    		     sbXml.append(sb.toString());
    		     response.setContentType("text/xml;charset=UTF-8");
    		     response.getWriter().write(sbXml.toString());
    		     response.getWriter().flush();
    		     response.getWriter().close();
		    }catch(Exception e){
		    	log.error("===获取onData方法的AllData解析或者发送错误===", e);
		    	e.printStackTrace();
		    }
		    return null;
	}
	
}


 