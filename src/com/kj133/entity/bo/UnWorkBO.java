package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_DownWellCount;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.entity.vo.UnWorkVO;
import com.kj133.util.Global;
public class UnWorkBO {
    

	 private Logger log=Logger.getLogger(this.getClass());
	 public UnWorkBO(){
		 
	 }
		/**
		 * 未工作人员明细
		 * */
	 @SuppressWarnings("unchecked")
	 public List init(String userid,Search_DownWellCount downwellcount,Pagination pagin)throws Exception{
		 List relist=null;
		 List param = new ArrayList();
		 Engine engine=null;
		 StringBuffer sb=new StringBuffer();

		 String sql="select sid,username,cid,gro,type,oation from (select stafferid as sid,name as username,cardid as cid,[group] as gro,worktype as type,occupation as oation from staffer where cardid is not null and stafferid not in (select distinct stafferid from workattendanceex where kqtime>=? and kqtime<=?)";

		 String stime=downwellcount.getStime();    
		 String etime=downwellcount.getEtime();    
		 String sid=downwellcount.getStafferid();  
		 String type=downwellcount.getWorktype();  
         String gro=downwellcount.getGroup();
         
        param.add(stime);
        param.add(etime);

       
//        跟数据库表字段对应上；
          if(sid!=null && !sid.equals("")){
        	  Global  gobal=new Global();
   		    List list=gobal.SuggestEmployees(sid);
  			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
  				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
  				sql += " and stafferid=? ";
  				param.add(vo.getStafferid());
  			}else{//没有卡号或用户名
  				sql += " and [name]=? ";
  				param.add(sid);
  			}
//				sql += " and rtrim(stafferid) =?";
//				param.add(sid);	
	       }if(type !=null && !type.equals("")){
	    	    sql += " and worktype =?";
	    	    param.add(type);
	       }if(gro!=null && !gro.equals("")){
	    	   sql += " and [group] =?";
			   param.add(gro);			   
	       }
	       sql += ") as QueryTable ,reportpopedom rp  where rp.department=[gro] and rp.userid='sys' order by gro ";	 
		 try{ 
			 engine=EngineFactory.getEngine("test");
			
//			 engine.executeProcedureCall("{ call calDownwell(?,?,?) }",new Object[]{userid,stime,etime});
			 
			 Query query=engine.getQuery();
			 relist=query.getResults(sql,param.toArray(),UnWorkVO.class,pagin);
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
	 public List initPrint(String userid,Search_DownWellCount downwellcount)throws Exception{
		 List relist=null;
		 List param = new ArrayList();
		 Engine engine=null;
		 StringBuffer sb=new StringBuffer();
		 
		 String sql="select sid,username,cid,gro,type,oation from (select stafferid as sid,name as username,cardid as cid,[group] as gro,worktype as type,occupation as oation from staffer where cardid is not null and stafferid not in (select distinct stafferid from workattendanceex where kqtime>=? and kqtime<=?)";
		 
		 String stime=downwellcount.getStime();    
		 String etime=downwellcount.getEtime();    
		 String sid=downwellcount.getStafferid();  
		 String type=downwellcount.getWorktype();  
		 String gro=downwellcount.getGroup();
		 
		 param.add(stime);
		 param.add(etime);
		 
		 
//        跟数据库表字段对应上；
		 if(sid!=null && !sid.equals("")){
			 Global  gobal=new Global();
	   		    List list=gobal.SuggestEmployees(sid);
	  			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
	  				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
	  				sql += " and stafferid=? ";
	  				param.add(vo.getStafferid());
	  			}else{//没有卡号或用户名
	  				sql += " and [name]=? ";
	  				param.add(sid);
	  			}	
		 }if(type !=null && !type.equals("")){
			 sql += " and worktype =?";
			 param.add(type);
		 }if(gro!=null && !gro.equals("")){
			 sql += " and [group] =?";
			 param.add(gro);			   
		 }
		 sql += ") as QueryTable ,reportpopedom rp  where rp.department=[gro] and rp.userid='"+userid+"' order by gro ";	 
		 try{ 
			 engine=EngineFactory.getEngine("test");
			 
//			 engine.executeProcedureCall("{ call calDownwell(?,?,?) }",new Object[]{userid,stime,etime});
			 
			 Query query=engine.getQuery();
			 System.out.println("sql="+sql);
			 relist=query.getResults(sql,param.toArray(),UnWorkVO.class);
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

}
