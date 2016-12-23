package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;


import com.kj133.entity.Search_PageRangeTopleft;
import com.kj133.entity.Wordlib;

import com.kj133.entity.vo.PageRangeToprightVO;
public class PageRangeDownleftBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public PageRangeDownleftBO(){	
    
	}
	
	@SuppressWarnings("unchecked")
	public List init()throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		 
		sb.append(" select case when areaorder='一级区域' then AreaName+'1' when areaorder='二级区域' then ParentArea+'2' end  temporder,areaid,AreaName,ParentArea,AreaOrder,AreaType,MaxSum,CaiJueSum,StayMinute,AreaInfo from AreaInfo  order by temporder ");

		
		try{
		   
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),PageRangeToprightVO.class);
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
	  * 修改二级区域
	  * */
	 @SuppressWarnings("unchecked")
	public void update(Search_PageRangeTopleft prt,String name)throws Exception {
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer(); 
//		 sb.append(" update ogroup set gname= ?,gdescription = ? where groupid=? ");
		 sb.append(" update AreaInfo set AreaName=?,AreaType=?,parentarea=?,MaxSum=?,CaiJueSum=?,StayMinute=?,Areainfo=? where AreaName=? ");
		 param.add(prt.getAreaname());
		 param.add(prt.getAreatype());
		 param.add(prt.getParentarea());
		 param.add(prt.getMaxsum());
		 param.add(prt.getCaijuesum());
		 param.add(prt.getStayminute());
		 param.add(prt.getAreainfo());
		 param.add(name);
//		 param.add(account.getGroup_name());
//		 param.add(account.getGroup_depict());
//		 param.add(account.getGroupid());
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
	
	  /**
	   * 增加二级区域
	   * */
	  @SuppressWarnings("unchecked")
	public void add(Search_PageRangeTopleft prt,int id)throws Exception{
		     Engine engine=null;
			 List param=new ArrayList();
			 StringBuffer sb=new StringBuffer();
			 sb.append(" insert into AreaInfo(AreaID,AreaName,AreaOrder,ParentArea,AreaType,MaxSum,CaiJueSum,StayMinute,Areainfo)");
			 sb.append("values(?,?,'二级区域',?,?,?,?,?,?) ");
//			 sb.append(" insert into AreaInfo(AreaID,AreaName,AreaOrder,AreaType,MaxSum,CaiJueSum,StayMinute,Areainfo) ");
//			 sb.append("values(?,?,'一级区域',?,?,?,?,?) ");
			 param.add(id);
			 param.add(prt.getAreaname());
//			 param.add(prt.getAreaorder());
			 param.add(prt.getParentarea());
			 param.add(prt.getAreatype());
			 param.add(prt.getMaxsum());
			 param.add(prt.getCaijuesum());
			 param.add(prt.getStayminute());
			 param.add(prt.getAreainfo());
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
	
	 /**
	  * 删除一级区域
	  * */
	 @SuppressWarnings("unchecked")
	public void delete(String name)throws Exception {
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" delete from AreaInfo where areaname = ?");
		 param.add(name);
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
	
	 public Wordlib load(String id)throws Exception{
			List relist=null;
			List param=new ArrayList();
			Engine engine=null;
			Wordlib wordlib = null;
			StringBuffer sb=new StringBuffer();
//			sb.append(" select distinct wordname,MaxValue,MinValue  from wordlib where module=1 and wordname=? order by WordName ");
//			param.add(id);

		 try{
			 engine=EngineFactory.getEngine("test");
			 wordlib = (Wordlib)engine.load(Wordlib.class, id);
//			 Query query=engine.getQuery();
//			 relist=query.getResults(sb.toString(),param.toArray(),SysWordLeftVO.class);
			 engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return wordlib;
	 }
	 /**
		 * update
		 */
	 public void executeSpecoalSQL(String sql,Object[] obj)throws Exception{
		 Engine engine=null;
		 try{
			 engine=EngineFactory.getEngine("test");
			 engine.executeSpecialSQL(sql,obj);
		     engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 } 
	 }	
	 
	 public List load1(String id)throws Exception{
			List relist=null;
			List param=new ArrayList();
			Engine engine=null;
			StringBuffer sb=new StringBuffer();
			sb.append(" select distinct wordname,MaxValue,MinValue  from wordlib where module=1 and wordname=? order by WordName ");
			param.add(id);

		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
			 relist=query.getResults(sb.toString(),param.toArray(),PageRangeToprightVO.class);
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
	

/*	*//**
	 * 用于报表里面和合计信息
	 * *//*
	@SuppressWarnings("unchecked")
	public  List getAllWorkTime(Search_AreaCheckList areachecklist)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		
		String stime=areachecklist.getStime();  
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length());  
     
		String etime=areachecklist.getEtime(); 
		String e1=etime.substring(0,10);  
		String e2=etime.substring(11,etime.length());
		
		StringBuffer sb=new StringBuffer();
		sb.append(" select isnull(allworktime,' ') as allworktime from (select rtrim(convert(int,sum(convert(float,uptime-downtime))))+'天'+rtrim(datepart(hh,sum(convert(float,uptime-downtime)))) ");
		sb.append(" +'小时'+rtrim(datepart(n,sum(convert(float,uptime-downtime))))+'分' as allworktime from ( select uptime,downtime, ");
		sb.append(" temp1.downtime as 入井时间  from(select downtime  , uptime   from WorkattendanceEx where  downtime>= ? and (uptime>= ? ");
		sb.append(" or uptime is null) and  downtime<convert(datetime, ?)+1 and (uptime<convert(datetime, ?)+3 or uptime  ");
		sb.append(" is null)) as temp1  ) as Sumtable  where  convert(char(8),入井时间,8)>= ? and  convert(char(8),入井时间,8)<= ? )  as tab");
		param.add(s1);
		param.add(s1);
		
		param.add(e1);
		param.add(e1);
		param.add(s2);
		param.add(e2);

		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),AreaCheckListVO.class);
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;
		}finally{
			engine.closeEngine();
		}
		return relist;
	}*/
}
