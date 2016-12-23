package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import com.kj133.entity.Search_WeizhidisLeft;
import com.kj133.entity.WeizhidisList;
public class WeizhidisListBO {
	
   private final Logger log=Logger.getLogger(this.getClass());
   public WeizhidisListBO(){
	   
   }
    /**
     *显示地图列表
     * */
   
   @SuppressWarnings("unchecked")
public List init(Search_WeizhidisLeft left)throws Exception{
	   List relist=null;
	   List param=new ArrayList();
	   StringBuffer sb =new StringBuffer();
	   Engine engine=null;
	   sb.append(" select mapid,mapname,mapinfo from maplist where 1=1 " );
	   String explain=left.getMap_explain();  
	   String name=left.getMap_name();
      
	   if( explain !=null && !explain.equals("")){//地图说明
		   sb.append(" and mapinfo like ? ");
		   param.add(explain+"%");
		   
	   }if( name != null && !name.equals("")){//名称
		   sb.append(" and mapname like ? ");
		   param.add(name+"%");
		   
	   }
	   try{
		   engine=EngineFactory.getEngine("test");
		   Query query=engine.getQuery();
	       relist=query.getResults(sb.toString(),param.toArray(),WeizhidisList.class);
	       engine.commit();
	   }catch(Exception e){
		   engine.rollback();
		   log.error(e);
		   throw e;
	   }finally{
		   engine.closeEngine();
	   }
	   return relist;
     }
   
    
    /**
	  * 提取单条数据
	  */
	 public WeizhidisList load(String id)throws Exception{
		 Engine engine=null;
		 WeizhidisList map=null;
		 try{
			 engine=EngineFactory.getEngine("test");
			 map=(WeizhidisList)engine.load(WeizhidisList.class,id);
			 engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return map;
	 }
	
}
