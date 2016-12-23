package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.vo.HistoryNote_LocatorVO;
public class HistoryNote_LocatorBO {
	
	private final Logger log=Logger.getLogger(this.getClass());
    public HistoryNote_LocatorBO(){
    	
    }
    @SuppressWarnings("unchecked")
	public List init(String time,String id)throws Exception{
    	List relist=null;
    	List param=new ArrayList();
    	Engine engine=null;
    	String date=time.substring(0,19);
    	StringBuffer sb=new StringBuffer();
    	sb.append("  declare @ds datetime,@dss datetime select @ds=convert(datetime, ?),@dss=@ds-7  " );
    	sb.append("  select a.cardid as cid,a.cardreaderid as rid,a.locatorid as lid,a.mapid as mid,a.state as state,a.starttime ");
    	sb.append("  as stime,a.endtime as etime,a.stayinterval as stay,a.Endby as endby,a.Startby as startby ,a.Antenna as ant,d.info as ov,b.[Name] as username,");
    	sb.append(" b.worktype as type,b.[group] as gro,c.info as info from v_locatedata as a left join staffer as b on a.stafferID=b.stafferID,alarminfo as c,startendinfo ");
    	sb.append("  as d where a.endby=d.state and a.state=c.state and starttime>@dss and  starttime<=@ds and endtime>@ds and cardreaderid= ? ");
 
         param.add(date);
         param.add(id);
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    		relist=query.getResults(sb.toString(),param.toArray(),HistoryNote_LocatorVO.class);
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
