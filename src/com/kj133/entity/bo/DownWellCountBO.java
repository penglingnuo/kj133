package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_DownWellCount;
import com.kj133.entity.vo.DownWellCountVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;
public class DownWellCountBO {
    

	 private Logger log=Logger.getLogger(this.getClass());
	 public DownWellCountBO(){
		 
	 }
		/**
		 * 下井次数统计
		 * */
	 @SuppressWarnings("unchecked")
	public List init(String userid,Search_DownWellCount downwellcount,Pagination pagin)throws Exception{
		 List relist=null;
		 List param = new ArrayList();
		 Engine engine=null;
		 String sql=" select QueryTable.* from (select downwell.stafferID as sid,case [Name] when ''  "
			 +"  then '未绑定' else [Name] end as username,case [group] when '' then '未绑定'  " 
			 +"  else [group] end as gro,case [Department] when '' then '未绑定'  else [Department] end as department,case worktype when '' then '其他' else worktype  " 
			 +"  end as type,downtimes as downtimes,convert(char,starttime,102) as stime, " 
			 +"  convert(char,endtime,102) as etime from downwell,staffer where " 
			 +"   staffer.stafferID=downwell.stafferID and Operator= ? )as QueryTable,reportpopedom rp  where (rp.department=QueryTable.gro or rp.department=QueryTable.department ) and rp.userid='"+userid+"' and 1=1 ";
		 param.add(userid);
		 String stime=downwellcount.getStime();    
		 String etime=downwellcount.getEtime();    
		 String sid=downwellcount.getStafferid();  
		 String type=downwellcount.getWorktype();  
         String gro=downwellcount.getGroup();     
        
          if(sid!=null && !sid.equals("")){
        	  Global  gobal=new Global();
   		    List list=gobal.SuggestEmployees(sid);
  			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
  				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
  				sql += " and rtrim(sid)=? ";
  				param.add(vo.getStafferid());
  			}else{//没有卡号或用户名
  				sql += " and username=? ";
  				param.add(sid);
  			}
//				sql += " and rtrim(sid) =?";
//				param.add(sid);	
	       }if(type !=null && !type.equals("")){
	    	    sql += " and type =?";
	    	    param.add(type);
	       }if(gro!=null && !gro.equals("")){
	    	   sql += " and ( gro =? or ) ";
			   param.add(gro);
	       }
		 try{ 
			 //System.out.println("sql="+sql);
			 engine=EngineFactory.getEngine("test");
			 engine.executeProcedureCall("{ call calDownwell(?,?,?) }",new Object[]{userid,stime,etime});
			 Query query=engine.getQuery();
			 relist=query.getResults(sql,param.toArray(),DownWellCountVO.class,pagin);
			// System.out.println("sql="+sql);
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
	 @SuppressWarnings("unchecked")
	 public List initPrint(String userid,Search_DownWellCount downwellcount)throws Exception{
		 List relist=null;
		 List param = new ArrayList();
		 Engine engine=null;
		 String sql=" select QueryTable.* from (select downwell.stafferID as sid,case [Name] when ''  "
			 +"  then '未绑定' else [Name] end as username,case [group] when '' then '未绑定'  " 
			 +"  else [group] end as gro,case worktype when '' then '其他' else worktype  " 
			 +"  end as type,downtimes as downtimes,convert(char,starttime,102) as stime, " 
			 +"  convert(char,endtime,102) as etime from downwell,staffer where " 
			 +"   staffer.stafferID=downwell.stafferID and Operator= ? )as QueryTable,reportpopedom rp  where rp.department=QueryTable.gro and rp.userid='"+userid+"' and 1=1 ";
		 param.add(userid);
		 String stime=downwellcount.getStime();    
		 String etime=downwellcount.getEtime();    
		 String sid=downwellcount.getStafferid();  
		 String type=downwellcount.getWorktype();  
		 String gro=downwellcount.getGroup();     
		 
		 if(sid!=null && !sid.equals("")){
			 Global  gobal=new Global();
	   		    List list=gobal.SuggestEmployees(sid);
	  			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
	  				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
	  				sql += " and rtrim(sid)=? ";
	  				param.add(vo.getStafferid());
	  			}else{//没有卡号或用户名
	  				sql += " and username=? ";
	  				param.add(sid);
	  			}	
		 }if(type !=null && !type.equals("")){
			 sql += " and type =?";
			 param.add(type);
		 }if(gro!=null && !gro.equals("")){
			 sql += " and gro =?";
			 param.add(gro);
		 }
		 try{ 
			 engine=EngineFactory.getEngine("test");
			 engine.executeProcedureCall("{ call calDownwell(?,?,?) }",new Object[]{userid,stime,etime});
			 Query query=engine.getQuery();
			 relist=query.getResults(sql,param.toArray(),DownWellCountVO.class);
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
