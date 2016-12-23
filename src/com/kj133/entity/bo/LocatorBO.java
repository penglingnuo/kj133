package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Locator;
import com.kj133.entity.Search_locator;
import com.kj133.entity.vo.EditCardVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;


public class LocatorBO {
   
	private Logger log=Logger.getLogger(this.getClass());
	
	public LocatorBO(){
    	 
     }
	
	  /**编辑定位器
	   *
	   */
     @SuppressWarnings("unchecked")
	public List init(Search_locator locator,Pagination pagin)throws Exception{
    	 List relist=null;
    	 List param=new ArrayList();
    	 StringBuffer sb=new StringBuffer();
    	 Engine engine=null;
    	 String lid=locator.getLocatorid();  
    	 String lname=locator.getLocatorname();  
    	 
    	 String stime=locator.getStime(); //时间大于
    	 String etime=locator.getEtime(); //时间小于 
    	 String minstime=locator.getMinstime();
 		 String maxstime=locator.getMaxstime();
    	 
 		sb.append(" select * from (select LocatorId,LName,ShortName,x,y,z,RegDate,Mapid,modifydate,case ground when 1 then '井上设备' when 0 then '井下设备' else '不区分' end as ground,state from Locator where LocatorID<>0 ");
   	 sb.append(" ) as QueryTable  where 1=1 ");
    	 if( lid != null && !lid.equals("")){//定位器名不为空
    		 Global  gobal=new Global();
  		    List list1=gobal.SuggestLocators(lid);
 			if(list1.size()>0){//根据用户名得到卡号或者直接查询卡号
 				SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
 				sb.append(" and QueryTable.LocatorId like ? ");
 				param.add(vo.getLocatorid()+"%");
 			}else{//没有卡号或用户名
 				sb.append(" and QueryTable.LName like ? ");
 				param.add(lid);
 			}
//    		 sb.append(" and QueryTable.LocatorId like ? ");
//    		 param.add(lid+"%");
    		 
    	 }
//    	 if( lname !=null && !lname.equals("")){
//    		 sb.append(" and QueryTable.LName  like ? ");
//    		 param.add(lname+"%");
//    		 
//    	 }
    	 if(stime !=null && !stime.equals("")){
    		 sb.append( " and regdate >= ?  " );
    		 param.add(stime);
    		 
    	 }
    	 if(minstime !=null && !minstime.equals("")){
    		 sb.append( " and  convert(char(8),regdate,8)>=?  " );
    		 param.add(minstime);
    		 
    	 }if(etime !=null && !etime.equals("")){
    		 sb.append( " and regdate <= ?  " );
    		 param.add(etime);
    		 
    	 }if(maxstime !=null && !maxstime.equals("")){
    		 sb.append( " and convert(char(8),regdate,8)<=?  " );
    		 param.add(maxstime);
    		 
    	 }
    	 try{
    		 
    		 engine=EngineFactory.getEngine("test");
    		 Query query=engine.getQuery();
             relist=query.getResults(sb.toString(),param.toArray(),Locator.class,pagin);
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
     public List initPrint(Search_locator locator)throws Exception{
    	 List relist=null;
    	 List param=new ArrayList();
    	 StringBuffer sb=new StringBuffer();
    	 Engine engine=null;
    	 String lid=locator.getLocatorid();  
    	 String lname=locator.getLocatorname();  
    	 
    	 String stime=locator.getStime(); //时间大于
    	 String etime=locator.getEtime(); //时间小于 
    	 String minstime=locator.getMinstime();
 		 String maxstime=locator.getMaxstime();
    	 
 		sb.append(" select * from (select LocatorId,LName,ShortName,x,y,z,RegDate,Mapid,modifydate,case ground when 1 then '井上设备' when 0 then '井下设备' else '不区分' end as ground,state from Locator where LocatorID<>0 ");
   	 sb.append(" ) as QueryTable  where 1=1 ");
   	if( lid != null && !lid.equals("")){//定位器名不为空
		 Global  gobal=new Global();
		    List list1=gobal.SuggestLocators(lid);
		if(list1.size()>0){//根据用户名得到卡号或者直接查询卡号
			SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
			sb.append(" and QueryTable.LocatorId like ? ");
			param.add(vo.getLocatorid()+"%");
		}else{//没有卡号或用户名
			sb.append(" and QueryTable.LName like ? ");
			param.add(lid);
		}
//		 sb.append(" and QueryTable.LocatorId like ? ");
//		 param.add(lid+"%");
		 
	 }
//    	 if( lid != null && !lid.equals("")){//定位器名不为空
//    		 sb.append(" and QueryTable.LocatorId like ? ");
//    		 param.add(lid+"%");
//    		 
//    	 }if( lname !=null && !lname.equals("")){
//    		 sb.append(" and QueryTable.LName  like ? ");
//    		 param.add(lname+"%");
//    		 
//    	 }
    	 if(stime !=null && !stime.equals("")){
    		 sb.append( " and regdate >= ?  " );
    		 param.add(stime);
    		 
    	 }
    	 if(minstime !=null && !minstime.equals("")){
    		 sb.append( " and  convert(char(8),regdate,8)>=?  " );
    		 param.add(minstime);
    		 
    	 }if(etime !=null && !etime.equals("")){
    		 sb.append( " and regdate <= ?  " );
    		 param.add(etime);
    		 
    	 }if(maxstime !=null && !maxstime.equals("")){
    		 sb.append( " and convert(char(8),regdate,8)<=?  " );
    		 param.add(maxstime);
    		 
    	 }

    	 try{
    		 
    		 engine=EngineFactory.getEngine("test");
    		 Query query=engine.getQuery();
    		 relist=query.getResults(sb.toString(),param.toArray(),Locator.class);
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
		 * 查询历史记录中是否有该定位器
		 */
	 public List ifcard(String cid)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select top 1 locatorid from v_locatedata where locatorid=? ");
		 param.add(cid);
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
          relist=query.getResults(sb.toString(),param.toArray(),Locator.class);
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
      * LocatorBO
      * 删除
      */
     public void delete(String id)throws Exception{
    	 Engine engine=null;
    	 Locator locator=null;
    	 try{
    		 engine=EngineFactory.getEngine("test");
    		 locator=(Locator)engine.load(Locator.class,id);
    		 engine.delete(locator);
    		 engine.commit();
    	 }catch(Exception e){
    		 engine.rollback();
    		 log.error(e);
    		 throw e;
    	 }finally{
    		 engine.closeEngine();
    	 }
      }
     
     /**
      * LocatorBO
      * 提取单条数据
      */
     public Locator load(String id)throws Exception{
    	  Engine engine=null;
          Locator locator=null;
    	  try{
    		  engine=EngineFactory.getEngine("test");
    		  locator=(Locator)engine.load(Locator.class,id);
    		  engine.commit();
    	  }catch(Exception e){
    		  engine.rollback();
    		  log.error(e);
    		  throw e;
    	  }finally{
    		  engine.closeEngine();
    	  }
    	  return locator;
      }
     
     
      
     /**
      * LocatorBO
      * 执行特殊语句的修改
      **/
     public void executeSpecialSQL(String  sql,Object[] params)throws Exception{
    	   Engine engine=null;    	  
    	   try{
    		   engine=EngineFactory.getEngine("test");
    		   engine.executeSpecialSQL(sql,params);
    		   engine.commit();
    	   }catch(Exception e){
    		   engine.rollback();
    		   log.error(e);
    		   throw e;    	   
    		}finally{
    			engine.closeEngine();
    		}
       }
     
     /**
      * LocatorBO
      * 保存
      * */
    public void save(Locator locator)throws Exception {
    	Engine engine=null;
    	try{
    		engine=EngineFactory.getEngine("test");
    		engine.save(locator);
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
