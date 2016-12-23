package com.basic.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_OverTime;
import com.kj133.entity.vo.OverTimeVO;

public class OverTimeBO {
	
	private static final Logger log=Logger.getLogger(OverTimeBO.class);
	public OverTimeBO(){
		
	}
	
	
	/**
	 * 获取查询下井超时的结果集
	 
	@SuppressWarnings("unchecked")
	public List getList(Search_OverTime over,Pagination pagin)throws Exception {
		List relist=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		
		String stime=over.getStime(); 
		String etime=over.getEtime();
		String department=over.getDpt();
		String hours=over.getHours();
		
		sb.append(" select temp1.* from (select w.cardid,isnull(s.name,'未绑定')username,isnull(s.department,'未绑定')department, ");
		sb.append(" isnull(s.[group],'未绑定') gro, ");
		sb.append(" case when  datediff(mi,downtime,uptime) >= (select max(mintime) mintime from worktime) and datediff(mi,downtime,uptime) < (select max(maxtime) mintime from worktime) then '一般超时'  else '严重超时' end as info, ");
		sb.append(" w.downtime,w.uptime,");
		sb.append(" substring(rtrim(100+(datediff(mi,downtime,uptime))/60),2,2)+'小时'+substring(rtrim(100+(datediff(mi,downtime,uptime))%60),2,2)+'分' as staytime,  ");
        sb.append(" substring(rtrim(100+(datediff(mi,dateadd(mi,(select min(mintime) mintime from worktime),downtime),uptime))/60),2,2)+'小时'+substring(rtrim(100+(datediff(mi,dateadd(mi,(select min(mintime) mintime from worktime),downtime),uptime))%60),2,2)+'分' as overtime ");
        sb.append(" from workattendanceex w  left join staffer s on s.cardid =w.cardid  ");
        sb.append(" where downtime>= ? and uptime>=?  and downtime< ? and uptime<(convert ");
        sb.append(" (datetime, ? )+3) and datediff(mi,downtime,uptime) >= (select min(mintime) mintime from worktime) ");
		param.add(stime);
		param.add(stime);
		param.add(etime);
		param.add(etime);
		
		
		if(hours!=null && !hours.equals("")){
			sb.append(" and datediff(mi,downtime,uptime)> ?");
			int hor=Integer.parseInt(hours);
			hor=hor*60;
			param.add(hor);
			
		}
		sb.append(" ) temp1,reportpopedom rep where userid=?  and temp1.department=rep.department ");
		param.add(over.getUserid());
		if(department!=null && !department.equals("")){
			sb.append(" and temp1.department=?");
			param.add(department);
		}
		
		try{
			engine=EngineFactory.getEngine("test");
		    Query query=engine.getQuery();
		    relist=query.getResults(sb.toString(),param.toArray(),OverTimeVO.class,pagin);
		    engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;
		}finally{
			engine.closeEngine();
		}
		return relist;
	}* */
	/**
	 * 获取查询下井超时的结果集
	 * */
	@SuppressWarnings("unchecked")
	public List getList(Search_OverTime over,Pagination pagin)throws Exception {
		List relist=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		
		String stime=over.getStime(); 
		String etime=over.getEtime();
		String department=over.getDpt();
		String hours=over.getHours();
		
		sb.append(" select temp1.* from (select w.cardid,isnull(s.name,'未绑定')username,isnull(s.department,'未绑定')department, ");
		sb.append(" isnull(s.[group],'未绑定') gro, ");
		sb.append(" case when  datediff(mi,downtime,uptime) > wo.mintime and datediff(mi,downtime,uptime) <= wo.maxtime then '一般超时'  else '严重超时' end as info, ");
		sb.append(" w.downtime,w.uptime,");
		sb.append(" substring(rtrim(100+(datediff(mi,downtime,uptime))/60),2,2)+'小时'+substring(rtrim(100+(datediff(mi,downtime,uptime))%60),2,2)+'分' as staytime,  ");
        sb.append(" substring(rtrim(100+(datediff(mi,dateadd(mi,(wo.MinTime),downtime),uptime))/60),2,2)+'小时'+substring(rtrim(100+(datediff(mi,dateadd(mi,(wo.MinTime),downtime),uptime))%60),2,2)+'分' as overtime,  ");
        sb.append(" substring(rtrim(100+(wo.MinTime)/60),2,2)+'小时'+substring(rtrim(100+(str(FLOOR(wo.MinTime)))%60),2,2)+'分' as ratetime ");
        sb.append(" from workattendanceex w  left join staffer s on s.cardid =w.cardid  ");
        sb.append(" left join Workovertime wo on  wo.stafferid=w.StafferID  ");
        sb.append(" where downtime>= ? and uptime>=?  and downtime< ? and uptime<(convert ");
        sb.append(" (datetime, ? )+3) and datediff(mi,downtime,uptime) >= wo.MinTime ");
		param.add(stime);
		param.add(stime);
		param.add(etime);
		param.add(etime);
		
		
		if(hours!=null && !hours.equals("")){
			sb.append(" and datediff(mi,downtime,uptime)> ?");
			int hor=Integer.parseInt(hours);
			hor=hor*60;
			param.add(hor);
			
		}
		sb.append(" ) temp1,reportpopedom rep where userid=?  and temp1.department=rep.department ");
		param.add(over.getUserid());
		if(department!=null && !department.equals("")){
			sb.append(" and temp1.department=?");
			param.add(department);
		}
		
		try{
			engine=EngineFactory.getEngine("test");
		    Query query=engine.getQuery();
		    relist=query.getResults(sb.toString(),param.toArray(),OverTimeVO.class,pagin);
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
	
//	@SuppressWarnings("unchecked")
//	public List getListPrint(Search_OverTime over)throws Exception {
//		List relist=null;
//		Engine engine=null;
//		List param=new ArrayList();
//		StringBuffer sb=new StringBuffer();
//		
//		String stime=over.getStime(); 
//		String etime=over.getEtime();
//		String department=over.getDpt();
//		String hours=over.getHours();
//		
//		sb.append(" select temp1.* from (select w.cardid,isnull(s.name,'未绑定')username,isnull(s.department,'未绑定')department, ");
//		sb.append(" isnull(s.[group],'未绑定') gro, ");
//		sb.append(" case when  datediff(mi,downtime,uptime) >= (select max(mintime) mintime from worktime) and datediff(mi,downtime,uptime) < (select max(maxtime) mintime from worktime) then '一般超时'  else '严重超时' end as info, ");
//		sb.append(" w.downtime,w.uptime,");
//		sb.append(" substring(rtrim(100+(datediff(mi,downtime,uptime))/60),2,2)+'小时'+substring(rtrim(100+(datediff(mi,downtime,uptime))%60),2,2)+'分' as staytime,  ");
//        sb.append(" substring(rtrim(100+(datediff(mi,dateadd(mi,(select min(mintime) mintime from worktime),downtime),uptime))/60),2,2)+'小时'+substring(rtrim(100+(datediff(mi,dateadd(mi,(select min(mintime) mintime from worktime),downtime),uptime))%60),2,2)+'分' as overtime ");
//        sb.append(" from workattendanceex w  left join staffer s on s.cardid =w.cardid  ");
//        sb.append(" where downtime>= ? and uptime>=?  and downtime< ? and uptime<(convert ");
//        sb.append(" (datetime, ? )+3) and datediff(mi,downtime,uptime) >= (select min(mintime) mintime from worktime) ");
//		param.add(stime);
//		param.add(stime);
//		param.add(etime);
//		param.add(etime);
//		
//		
//		if(hours!=null && !hours.equals("")){
//			sb.append(" and datediff(mi,downtime,uptime)> ?");
//			int hor=Integer.parseInt(hours);
//			hor=hor*60;
//			param.add(hor);
//			
//		}
//		sb.append(" ) temp1,reportpopedom rep where userid=?  and temp1.department=rep.department ");
//		param.add(over.getUserid());
//		if(department!=null && !department.equals("")){
//			sb.append(" and temp1.department=?");
//			param.add(department);
//		}
//		
//		try{
//			engine=EngineFactory.getEngine("test");
//		    Query query=engine.getQuery();
//		    relist=query.getResults(sb.toString(),param.toArray(),OverTimeVO.class);
//		    engine.commit();
//		}catch(Exception e){
//			engine.rollback();
//			log.error(e);
//			throw e;
//		}finally{
//			engine.closeEngine();
//		}
//		return relist;
//}}
@SuppressWarnings("unchecked")
public List getListPrint(Search_OverTime over)throws Exception {
	List relist=null;
	Engine engine=null;
	List param=new ArrayList();
	StringBuffer sb=new StringBuffer();
	
	String stime=over.getStime(); 
	String etime=over.getEtime();
	String department=over.getDpt();
	String hours=over.getHours();
	
	sb.append(" select temp1.* from (select w.cardid,isnull(s.name,'未绑定')username,isnull(s.department,'未绑定')department, ");
	sb.append(" isnull(s.[group],'未绑定') gro, ");
	sb.append(" case when  datediff(mi,downtime,uptime) > wo.mintime and datediff(mi,downtime,uptime) <= wo.maxtime then '一般超时'  else '严重超时' end as info, ");
	sb.append(" w.downtime,w.uptime,");
	sb.append(" substring(rtrim(100+(datediff(mi,downtime,uptime))/60),2,2)+'小时'+substring(rtrim(100+(datediff(mi,downtime,uptime))%60),2,2)+'分' as staytime,  ");
    sb.append(" substring(rtrim(100+(datediff(mi,dateadd(mi,(wo.MinTime),downtime),uptime))/60),2,2)+'小时'+substring(rtrim(100+(datediff(mi,dateadd(mi,(wo.MinTime),downtime),uptime))%60),2,2)+'分' as overtime,  ");
    sb.append(" substring(rtrim(100+(wo.MinTime)/60),2,2)+'小时'+substring(rtrim(100+(str(FLOOR(wo.MinTime)))%60),2,2)+'分' as ratetime ");
    sb.append(" from workattendanceex w  left join staffer s on s.cardid =w.cardid  ");
    sb.append(" left join Workovertime wo on  wo.stafferid=w.StafferID  ");
    sb.append(" where downtime>= ? and uptime>=?  and downtime< ? and uptime<(convert ");
    sb.append(" (datetime, ? )+3) and datediff(mi,downtime,uptime) >= wo.MinTime ");
	param.add(stime);
	param.add(stime);
	param.add(etime);
	param.add(etime);
	
	
	if(hours!=null && !hours.equals("")){
		sb.append(" and datediff(mi,downtime,uptime)> ?");
		int hor=Integer.parseInt(hours);
		hor=hor*60;
		param.add(hor);
		
	}
	sb.append(" ) temp1,reportpopedom rep where userid=?  and temp1.department=rep.department ");
	param.add(over.getUserid());
	if(department!=null && !department.equals("")){
		sb.append(" and temp1.department=?");
		param.add(department);
	}
	
	try{
		engine=EngineFactory.getEngine("test");
	    Query query=engine.getQuery();
	    relist=query.getResults(sb.toString(),param.toArray(),OverTimeVO.class);
	    engine.commit();
	}catch(Exception e){
		engine.rollback();
		log.error(e);
		throw e;
	}finally{
		engine.closeEngine();
	}
	return relist;
}}
