package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.Search_area_padlock_top;
import com.kj133.entity.vo.Area_padlock_topVO;

public class Area_padlock_topBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public Area_padlock_topBO(){	
    
	}
	
	@SuppressWarnings("unchecked")
	public List init(Search_area_padlock_top area_padlock_top)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		 
	
	
		String stime=area_padlock_top.getStime();  
		String etime=area_padlock_top.getEtime(); 

		sb.append(" select 区域号 aid,区域名称 aname,禁入报警时间 atime,count(*) acount from ( ");
		sb.append(" select c.*,d.cardid from ( ");
		sb.append(" select AreaID 区域号,AreaName 区域名称,RecordTime 禁入报警时间 from ( ");
		sb.append(" select a.*,b.AreaName,b.AreaType,b.maxsum,b.caijuesum from areadata a left join v_AreaInfo b on a.AreaID=b.AreaID and a.PeopleCount>0 ");
		sb.append(" ) aa where AreaType='限制区域' and maxsum=0 and caijuesum=0 ");
		sb.append(" and recordtime>=? and recordtime<=? ");
		param.add(stime);
		param.add(etime);
		sb.append(" ) c left join  ");
		sb.append(" (select * from arealocatedata) d on c.区域号=d.areaid where "); 
		sb.append(" starttime>=c.禁入报警时间-1 and starttime<=c.禁入报警时间 and (endtime>=c.禁入报警时间 or endtime is null) ");
		sb.append(" ) cc group by 区域号,区域名称,禁入报警时间 ");
		
	
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),Area_padlock_topVO.class);
			
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
	public  List getAllWorkTime(Search_area_padlock_top area_padlock_top)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		
		String stime=area_padlock_top.getStime();  
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length());  
     
		String etime=area_padlock_top.getEtime(); 
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
			relist=query.getResults(sb.toString(),param.toArray(),Area_padlock_topVO.class);
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
