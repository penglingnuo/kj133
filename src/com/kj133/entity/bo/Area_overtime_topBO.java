package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.Search_area_overtime_top;
import com.kj133.entity.vo.Area_overtime_topVO;

public class Area_overtime_topBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public Area_overtime_topBO(){	
    
	}
	
	@SuppressWarnings("unchecked")
	public List init(Search_area_overtime_top area_overtime_top)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		 
	
	
		String stime=area_overtime_top.getStime();  
		String etime=area_overtime_top.getEtime(); 
		sb.append(" select ncount,areaid,areaname,起始时间 as st,结束时间 as et from ( ");
		sb.append(" select b.ncount,b.areaid,c.areaname,? as 起始时间,? as 结束时间 from "); 
		param.add(stime);
		param.add(etime);
		sb.append(" (select count(*)as ncount ,areaid  from (select a.areaid,b.areaname,a.cardid from arealocatedata a,areainfo b,workattendanceex c where a.areaid=b.AreaID and ");
		sb.append(" dateadd(n,b.stayminute,a.starttime)<=a.endtime and c.cardid=a.cardid and c.downtime<=a.starttime and (c.uptime>=a.endtime or c.uptime is null)and starttime >= ");
		sb.append(" ? and endtime <? ");
		param.add(stime);
		param.add(etime);
		sb.append(" )as a where areaid in(select areaid from arealocatedata group by areaid ) group by areaid )as b,areainfo c where b.areaid=c.AreaID ");
		sb.append(" ) as QueryTable ");
		
	
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),Area_overtime_topVO.class);
			
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
	 * 用于报表里面和合计信息
	 * */
	@SuppressWarnings("unchecked")
	public  List getAllWorkTime(Search_area_overtime_top area_overtime_top)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		
		String stime=area_overtime_top.getStime();  
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length());  
     
		String etime=area_overtime_top.getEtime(); 
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
			relist=query.getResults(sb.toString(),param.toArray(),Area_overtime_topVO.class);
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
