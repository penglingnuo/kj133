package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
public class WeizhidisManagerBO {
	
	 private static final Logger log=Logger.getLogger(WeizhidisManagerBO.class);
	
	/**
	 * 地图管理
	 * */
	 public WeizhidisManagerBO(){
		 
	 }
	 
	 /**
	  * 增加地图
	  * */
	 public void addMap()throws Exception {
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
	     
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
