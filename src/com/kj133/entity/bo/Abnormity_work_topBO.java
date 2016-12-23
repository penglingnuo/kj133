package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.Search_abnormity_work_top;
import com.kj133.entity.vo.Abnormity_work_topVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;

public class Abnormity_work_topBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public Abnormity_work_topBO(){	
    
	}
	
	@SuppressWarnings("unchecked")
	public List init(Search_abnormity_work_top abnormity_work_top,String userid)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		 
	
		String sid = abnormity_work_top.getStafferid();
		String stime=abnormity_work_top.getStime();  
 
     
		String etime=abnormity_work_top.getEtime(); 

		
		sb.append("select a.stafferid,b.name,b.Cardid,convert(varchar(19),a.downtime,20) downtime,convert(varchar(19),uptime,20) uptime,a.异常次数 abnormitylist,b.[group] gro,b.worktype,b.occupation from "); 
		sb.append(" (select stafferid,downtime,uptime,sum(1) as 异常次数 from errorwork where userid=? ");
		param.add(userid);
		 
		sb.append(" group by stafferid,downtime,uptime) a ");
		sb.append(" left join staffer b on a.stafferid=b.stafferid where 1=1 ");
		if(sid!=null && !sid.equals("")){
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and a.stafferid=? ");
				param.add(vo.getStafferid());
			}else{//没有卡号或用户名
				sb.append(" and b.[name]=? ");
				param.add(sid);
			}
		}
		try{
			engine=EngineFactory.getEngine("test");
			engine.executeProcedureCall("{ call SelectErrorWork(?,?,?) }",new Object[]{userid,stime,etime});
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),Abnormity_work_topVO.class);
			
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
	public  List getAllWorkTime(Search_abnormity_work_top abnormity_work_top)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		
		String stime=abnormity_work_top.getStime();  
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length());  
     
		String etime=abnormity_work_top.getEtime(); 
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
			relist=query.getResults(sb.toString(),param.toArray(),Abnormity_work_topVO.class);
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
