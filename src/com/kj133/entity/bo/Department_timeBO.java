package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.Search_department_day;
import com.kj133.entity.vo.Department_timeVO;

public class Department_timeBO {
    private Logger log=Logger.getLogger(this.getClass()); 
	public Department_timeBO(){    	 
     }
	
    @SuppressWarnings("unchecked")
	public List getList(Search_department_day time)throws Exception {
    	List relist=null;
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
        sb.append(" declare @dt datetime  select @dt=convert(datetime, ? ) ");
        sb.append(" select   department,department as department1 ,department as department2 ,count(*) as peoplecount ,count(*) as allcount, convert(varchar(20), ");
        sb.append(" sum(datediff(s,downtime,@dt))/3600)+'小时'+convert(varchar(20),sum(datediff(s,downtime,@dt))%3600/60)+'分钟'as sumtime, ");
        sb.append(" convert(datetime,avg(convert(float,downtime))) as avgdatetime,convert(varchar(20),avg(datediff(s,downtime,@dt))/3600)+ ");
        sb.append(" '小时'+ convert(varchar(20),avg(datediff(s,downtime,@dt))%3600/60)+'分钟'as avgtime,null  as location ,'1' as dep ,'1' as dep1 "); 
        sb.append("  from( select d.downtime,s.department,[group] from (select max(downtime) as downtime,cardid from (select cardid,downtime  ");
        sb.append(" from workattendanceEx a where downtime<=@dt and downtime>@dt-3 and (uptime is null or uptime>@dt)) as e group by cardid) as d, ");
        sb.append(" (select a.stafferid,a.cardid,a.cardreaderid,a.locatorid from v_locatedata a  where a.starttime = (select max(starttime) from ");
        sb.append(" v_locatedata where cardid=a.cardid and starttime<@dt and starttime>@dt-3) )as v,staffer as s,cardreader as c,locator as l ");
        sb.append(" where d.cardid=v.cardid and v.stafferid=s.stafferid and v.cardreaderid=c.cardreaderid and v.locatorid=l.locatorid ");
        sb.append(" ) as passtable left join shunxu on passtable.[group]=shunxu.[group] group by department ");
        sb.append(" union all ");
        sb.append(" select   department,passtable.[group] as gro ,'      '+passtable.[group] as gr1 ,count(*) as peoplecount ,'0' as allcount,convert(varchar(20),sum ");
        sb.append(" (datediff(s,downtime,@dt))/3600)+'小时'+ convert(varchar(20),sum(datediff(s,downtime,@dt))%3600/60)+'分钟'as sumtime, ");
        sb.append(" convert(datetime,avg(convert(float,downtime))) as avgdatetime, convert(varchar(20),avg(datediff(s,downtime,@dt))/3600)+'小时' ");
        sb.append(" + convert(varchar(20),avg(datediff(s,downtime,@dt))%3600/60)+'分钟'as avgtime, null as location ,'2' as gro,'2' as gro2  from( ");
        sb.append(" select d.downtime,s.department,[group] from (select max(downtime) as downtime,cardid from (select cardid,downtime from  ");
        sb.append(" workattendanceEx a where downtime<=@dt and downtime>@dt-3 and (uptime is null or uptime>@dt)) as e group by cardid) as d, ");
        sb.append(" (select a.stafferid,a.cardid,a.cardreaderid,a.locatorid from v_locatedata a where a.starttime = (select max(starttime) from  ");
        sb.append(" v_locatedata where cardid=a.cardid and starttime<@dt and starttime>@dt-3) )as v,staffer as s,cardreader as c,locator as l ");
        sb.append(" where d.cardid=v.cardid and v.stafferid=s.stafferid and v.cardreaderid=c.cardreaderid and v.locatorid=l.locatorid ) as passtable ");
        sb.append("  left join shunxu on passtable.[group]=shunxu.[group] group by department,passtable.[group]  ");
        sb.append(" union all  ");
        sb.append(" select  department,passtable.[group] as gro,'           '+name,cardid ,'0' as allcount,convert(varchar(20),datediff(s,downtime,@dt)/3600)+'小时'+convert( ");
        sb.append(" varchar(20),datediff(s,downtime,@dt)%3600/60)+'分'as avgdatetime,downtime,  crname,lname  ,'3' as  Us,'2' as  Us1 from( ");
        sb.append(" select d.cardid,c.shortname as crname,l.shortname as lname,d.downtime,s.department,[group],[name],v.stafferid from ");
        sb.append(" (select max(downtime) as downtime,cardid from (select cardid,downtime from workattendanceEx a where downtime<=@dt and  ");
        sb.append(" downtime>@dt-3 and (uptime is null or uptime>@dt) ) as e group by cardid) as d, (select a.stafferid,a.cardid,a.cardreaderid, ");
        sb.append(" a.locatorid from v_locatedata a  where a.starttime = (select max(starttime) from v_locatedata where cardid=a.cardid and  ");
        sb.append(" starttime<@dt and starttime>@dt-3) )as v,staffer as s,cardreader as c,locator as l where d.cardid=v.cardid and v.stafferid= ");
        sb.append(" s.stafferid and v.cardreaderid=c.cardreaderid and v.locatorid=l.locatorid ) as passtable left join shunxu on passtable.[group] ");
        sb.append(" =shunxu.[group] order by department,dep1,department1,dep,department2 ");
    	param.add(time.getStime());
    	try{
    	       engine=EngineFactory.getEngine("test");
    	       Query query=engine.getQuery();
    	       relist=query.getResults(sb.toString(),param.toArray(),Department_timeVO.class);
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
