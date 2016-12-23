package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import com.kj133.entity.Search_department_day;
import com.kj133.entity.vo.Department_dayVO;


public class Department_dayBO {
     
	 private static final Logger log=Logger.getLogger(BantypeBO.class);
	 public Department_dayBO(){
    	  
      }
	  
    
	 /**
	  * 获取结果集
	  * */
	 @SuppressWarnings("unchecked")
	public List getResult(Search_department_day search)throws Exception{
		    Engine engine=null;
		    StringBuffer sb=new StringBuffer();
		    List relist=null;
		    List param=new ArrayList();
		    sb.append(" declare @ds datetime declare @de datetime select @ds=? select @de= ? ");
		    sb.append(" select * from (select department as department,department as department1 ,department as department2,count(*) as  ");
		    sb.append(" allcount ,sum(downcount) as peoplecount ,sum(downcount) as count ,convert(varchar(20),sum(worktime)/3600) +'小时'+convert(varchar(20),  ");
		    sb.append(" (sum(worktime)%3600)/60) +'分钟' as  worktime, convert(varchar(20),avg(worktime)/3600)+'小时'+convert(varchar(20), "); 
		    sb.append(" (avg(worktime)%3600)/60)+'分钟' as avgworktime , avg(downcount) as avgdowncount,'1' as Dep,'1' as Dep1 from(select s.cardid,t.stafferid, ");
		    sb.append("	 s.department,s.[group],t.downcount,t.worktime from(select stafferid,sum(1) as downcount,sum(datediff(ss,downtime,  ");
		    sb.append("  uptime)) as worktime from( select stafferID,uptime,downtime from workattendanceEx where downtime>=@ds and downtime  ");
		    sb.append("  <@de+1 and uptime>@ds and uptime<@de+3 ) as temp1 group by stafferid )as t ,staffer as s where t.stafferid=s.stafferid   ");
		    sb.append("  ) as passtable left join shunxu on passtable.[group]=shunxu.[group] group by department ");
			sb.append("  union all  ");
			sb.append("  select department as department, passtable.[group] as gro ,'     '+passtable.[group] as gro1,count(*) as   ");
			sb.append("  allcount ,sum(downcount) as peoplecount ,'0' as count,convert(varchar(20),sum(worktime)/3600) +'小时'+convert(varchar(20),   ");
			sb.append(" (sum(worktime)%3600)/60) +'分钟' as  worktime, convert(varchar(20),avg(worktime)/3600)+'小时'+convert(varchar(20),   ");
			sb.append(" (avg(worktime)%3600)/60)+'分钟' as avgworktime , avg(downcount) as avgdowncount,'2' as Gro,'2' as Gro1 from(select s.cardid,t.stafferid,   ");
			sb.append("  s.department,s.[group],t.downcount,t.worktime from(select stafferid,sum(1) as downcount,sum(datediff(ss,downtime,   ");
			sb.append("  uptime)) as worktime from( select stafferID,uptime,downtime from workattendanceEx where downtime>=@ds and downtime  ");
			sb.append("  <@de+1 and uptime>@ds and uptime<@de+3 ) as temp1 group by stafferid )as t ,staffer as s where t.stafferid=s.stafferid  ");
			sb.append("	 ) as passtable left join shunxu on passtable.[group]=shunxu.[group]   ");
			sb.append("	 group by department, passtable.[group]   ");				               
	        sb.append("  union all ");
	        sb.append("  select department,passtable.[group],'          '+username,cardid,downcount,'0' as count,convert(varchar(20),worktime/3600)+'小时'+convert(varchar(20),(worktime%3600)/60) +'分钟'  ");
	        sb.append("  as  wtime,convert(varchar(20),(worktime/downcount)/3600)+'小时'+convert(varchar(20),((worktime/downcount)%3600)/60) +'分钟' as  avgtime  ");
	        sb.append("  ,'0' as gro,'3' as  Us,'2' as  Us1 from( select s.cardid ,s.[name] as username,t.downcount,t.worktime,s.department ,s.[group]    ");
	        sb.append("  from( select stafferid,sum(1) as downcount,sum(datediff(ss,downtime,uptime)) as worktime from( select stafferID,uptime,downtime from workattendanceEx  ");
	        sb.append("  where downtime>=@ds and downtime<@de+1 and uptime>@ds and uptime<@de+3 ) as temp1 group by stafferid ");
	        sb.append("  )as t ,staffer as s where t.stafferid=s.stafferid ) as passtable left join shunxu on passtable.[group]=shunxu.[group] ");
	        sb.append("  ) as QueryTable   ");
            param.add(search.getStime());
            param.add(search.getEtime());
		    try{
			     sb.append("   order by department,dep1,department1,dep,department2 ");
			     engine=EngineFactory.getEngine("test");
		    	 Query query=engine.getQuery();
		    	 relist=query.getResults(sb.toString(),param.toArray(),Department_dayVO.class);
		    	 engine.commit();
		    }catch(Exception e){
	    		  log.error(e);
	    		  engine.rollback();
	    		  throw e;
	    	  }finally{	    		
	    		  engine.closeEngine();
	    	 }
	    	 return relist;
	     }  
} 
 
 
