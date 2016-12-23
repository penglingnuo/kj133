package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;


import com.kj133.entity.Search_Account;
import com.kj133.entity.Search_PageRangeTopleft;
import com.kj133.entity.Search_unworkcar;
import com.kj133.entity.Wordlib;
import com.kj133.entity.WorkattendanceEx;

import com.kj133.entity.vo.PageRangeTopleftVO;
import com.kj133.entity.vo.PageRangeToprightVO;
import com.kj133.entity.vo.UnworkcarVO;
import com.kj133.util.Global;
public class PageRangeTopleftBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public PageRangeTopleftBO(){	
    
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
	 * 初始化二级区域范围
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public List getlist(String name)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		 
//		sb.append(" select case when areaorder='一级区域' then AreaName+'1' when areaorder='二级区域' then ParentArea+'2' end  temporder,areaid,AreaName,ParentArea,AreaOrder,AreaType,MaxSum,CaiJueSum,StayMinute,AreaInfo from AreaInfo  order by temporder ");
		sb.append(" select AreaName,a.type,pointID as [ID],b.shortname from AreaDetail as a, ");
		sb.append(" (select '分站' as type,shortname,cardreaderid as [ID] from cardreader  ");
		sb.append(" union all select '定位器' as type,shortname,locatorid as [ID] from locator ");
		sb.append(" )as b where a.type=b.type and a.pointID=b.[ID] and AreaName=? ");
		param.add(name);
//		sb.append(" ");

		
		try{
		   
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),PageRangeTopleftVO.class);
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
     * 
     * 增加
     */
    public void add2(String name,String name1,String name2)throws Exception{
   	 Engine engine=null;
   	 StringBuffer sb=new StringBuffer();
   	 List param=new ArrayList();
 		 sb.append(" insert into AreaDetail(areaname,type,pointid) values(?,?,?) ");
 		 param.add(name);
 		 param.add(name1);
 		 param.add(name2);
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
	
	public List initarea(Search_PageRangeTopleft unworkcar)throws Exception{
		List relist=null;
		List resultlist = new ArrayList();
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		
		sb.append(" select '分站' as type,shortname,cardreaderid as [ID] from cardreader where cardreaderid<>0 union all select '定位器',shortname,locatorid from locator ");

//        param.add(s1);

		try{

			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),PageRangeTopleftVO.class);

		
			if(relist != null&&relist.size()>0){
				
				
				for(int i =0;i<relist.size();i++){
					List relist1 = relist;
					PageRangeTopleftVO vo = (PageRangeTopleftVO) relist.get(i);
					String type = vo.getType();
					String id = vo.getId();
					String shortname = vo.getShortname();
					String zuhe = type+":"+id+":"+shortname;
					vo.setZuhe(zuhe);
					resultlist.add(vo);

				}
				
			}

//			UnworkcarVO vo = new  UnworkcarVO();
//			vo.setCount("合计");
//			vo.setReckon(reckon1);
//			resultlist.add(vo);
			engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;
		}finally{
			engine.closeEngine();
		}
		return resultlist;
	}	
	 /**
	  * 修改一级区域
	  * */
	 @SuppressWarnings("unchecked")
	public void update(Search_PageRangeTopleft prt,String name)throws Exception {
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer(); 
//		 sb.append(" update ogroup set gname= ?,gdescription = ? where groupid=? ");
		 sb.append(" update AreaInfo set AreaName=?,AreaType=?,MaxSum=?,CaiJueSum=?,StayMinute=?,Areainfo=? where AreaName=? ");
		 param.add(prt.getAreaname());
		 param.add(prt.getAreatype());
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
	  * 增加区域范围
	  * */
	 @SuppressWarnings("unchecked")
	 public void addtype(Search_PageRangeTopleft prt,String id)throws Exception{
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 
		 String zuhe = prt.getZuhe();
		 String[] name=zuhe.split("\\:");
		 sb.append(" insert into AreaDetail(AreaName,PointID,Type)");
		 sb.append("values(?,?,?) ");
		 param.add(id);
		 param.add(name[1]);
		 param.add(name[0]);
		 
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
	  * 删除区域范围
	  * */
	 @SuppressWarnings("unchecked")
	 public void deletetype(String id,String name0,String name1)throws Exception{
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 
//		 String zuhe = prt.getZuhe();
//		 String[] name=zuhe.split("\\:");
		 sb.append(" delete AreaDetail where AreaName=? and PointID=? and type=?  ");
//		 sb.append("values(?,?,?) ");
		 param.add(id);
		 param.add(name0);
		 param.add(name1);
		 
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
	   * 增加一级区域
	   * */
	  @SuppressWarnings("unchecked")
	public void add(Search_PageRangeTopleft prt,int id)throws Exception{
		     Engine engine=null;
			 List param=new ArrayList();
			 StringBuffer sb=new StringBuffer();
			 sb.append(" insert into AreaInfo(AreaID,AreaName,AreaOrder,AreaType,MaxSum,CaiJueSum,StayMinute,Areainfo) ");
			 sb.append("values(?,?,'一级区域',?,?,?,?,?) ");
			 param.add(id);
			 param.add(prt.getAreaname());
//			 param.add(prt.getAreaorder());
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
