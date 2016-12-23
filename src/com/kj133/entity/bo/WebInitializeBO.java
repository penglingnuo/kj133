package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.Search_Ocx;
import com.kj133.entity.vo.WebInitializeVO;

 
public class WebInitializeBO {

	private final Logger log=Logger.getLogger(this.getClass());
	
	/***
	 * 
	 * @param user
	 * @return  所有的用户公用一个参数设置
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List init()throws Exception{
		Engine engine=null;
		List relist=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		sb.append(" select strcon, svip, wmapid, svport, keepdatatime, overminute, reoverminute, locatetype,isnull(lostlocator,-1)as lostlocator  from  WebInitialize ");
		try{
			   engine=EngineFactory.getEngine("test");
			   Query query=engine.getQuery();
		       relist=query.getResults(sb.toString(),param.toArray(),WebInitializeVO.class);
		       engine.commit();
		  }catch(Exception e){
			   engine.rollback();
			   log.error(" 初始化OCX参数错误 ", e);
			   throw e;
		  }finally{
			  engine.closeEngine();
		  }
		return relist;
	}
	
	//'Provider=SQLOLEDB.1;Password=123;Persist Security Info=True;
	//User ID=sa;Initial Catalog=tyzh;Data Source=192.168.1.26'

	

	/*** 
	 * @param  
	 * @return  增加配置初始化参数 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public  void add(Search_Ocx ocx,int choise)throws Exception{
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
	     String con="Provider=SQLOLEDB.1;Password="+ocx.getDbpassword()+";Persist Security Info=True;User ID="+ocx.getDbusername()+
	                ";Initial Catalog="+ocx.getDbname()+";Data Source="+ocx.getDbip();
	     int c=choise;
	     if(c==1){//等于1也就是修改
	    	 sb.append(" truncate table   WebInitialize ");
	     }
		 sb.append(" insert into WebInitialize values(?,?,?,?,?,?,?,?,?) ");
		 param.add(con);
		 param.add(ocx.getCip());
		 param.add(ocx.getMid());
		 param.add(ocx.getCpost());
		 param.add(60);
		 param.add(ocx.getOverminute());
		 param.add(ocx.getReoverminute());
		 param.add(ocx.getLocatetype());
		 if(ocx.getHiddenlocator().equals("0")){
			 //当html:text的disabled=true，你给text赋值，实际上是获取不到值的。
			 //0表示disabled=false
			 param.add(ocx.getLostlocator());
		 }else{
			 param.add("-1");
		 }
		 try{
			 engine=EngineFactory.getEngine("test");
			 engine.executeSpecialSQL(sb.toString(),param.toArray());
		     engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
	} 
}
