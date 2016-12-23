package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_AreaCheckList;
import com.kj133.entity.Search_AreaCheckStat;
import com.kj133.entity.vo.AreaCheckListVO;
import com.kj133.entity.vo.AreaCheckStatVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;
public class AreaCheckStatBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public AreaCheckStatBO(){	
    
	}
	
	@SuppressWarnings("unchecked")
	public List init(Search_AreaCheckStat areacheckstat,Pagination pagin)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		 
		String sid=areacheckstat.getSid(); 
		String gro=areacheckstat.getGro();   
		String type=areacheckstat.getType();
		String areaid=areacheckstat.getAreaid();
//		String banci=areachecklist.getBanci();
		 
		String stime=areacheckstat.getStime();  
//		String s1=stime.substring(0,10);  
//		String s2=stime.substring(11,stime.length());  
		
     
		String etime=areacheckstat.getEtime(); 
//		String e1=etime.substring(0,10);  
//		String e2=etime.substring(11,etime.length());
		String userid = areacheckstat.getUserid();
		
		sb.append(" select 员工编号 stafferid,卡号 cardid,姓名 name,工种 worktype,班组 gro,区域编号 areaid,区域名称 areaname,substring(rtrim(100+sum(worktime)/60),2,2)+'小时'+substring(rtrim(100+sum(worktime)%60),2,2)+'分' sticktime,sum(worktime) worktime,count(*) areacount from ( ");
		sb.append(" select bb.areaname 区域名称,aa.* from ( ");
		sb.append(" select datediff(mi,a.starttime,a.endtime) worktime,a.areaid as 区域编号,a.cardid as 卡号,a.enterreader,a.leavereader,b.stafferid as 员工编号,b.[name] as 姓名,b.worktype as 工种,b.[group] as 班组,a.starttime as 进入区域时间, "); 
		sb.append(" a.endtime as 离开区域时间,c.downtime as 入井时间,c.uptime as 升井时间  ");
		sb.append(" from arealocatedata a,staffer b,workattendanceex c,reportpopedom rp where rp.department=b.department and rp.userid='"+userid+"' and a.cardid=b.cardid and c.stafferid=b.stafferid and a.starttime+0.00028>=c.downtime and (a.starttime<=c.uptime+0.00028 or c.uptime is null) ");
		sb.append(" and starttime>=? ");
		param.add(stime);
		sb.append(" and endtime<=? ");
		param.add(etime);
		if( sid!=null && !sid.equals("")){
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and b.stafferid=? ");
				param.add(vo.getStafferid());
			}else{//没有卡号或用户名
				sb.append(" and b.[name]=? ");
				param.add(sid);
			}
//			sb.append(" and b.stafferid=? ");
//			param.add(sid);
		}if( gro!=null && !gro.equals("")){
			sb.append(" and b.[group]=? ");
			param.add(gro);
		}if( type!=null && !type.equals("")){
			sb.append(" and b.worktype=? ");
			param.add(type);
		} if( areaid!=null && !areaid.equals("")){
			sb.append(" and a.areaid=? ");
			param.add(areaid);
		}
		sb.append(" ) aa left join v_areainfo bb on aa.区域编号=bb.AreaID ");
		sb.append(" ) a group by 员工编号,卡号,姓名,工种,班组,区域编号,区域名称 ");

		
		try{
		    

			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),AreaCheckStatVO.class,pagin);
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
	public List initPrint(Search_AreaCheckStat areacheckstat)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		
		String sid=areacheckstat.getSid(); 
		String gro=areacheckstat.getGro();   
		String type=areacheckstat.getType();
		String areaid=areacheckstat.getAreaid();
//		String banci=areachecklist.getBanci();
		
		String stime=areacheckstat.getStime();  
//		String s1=stime.substring(0,10);  
//		String s2=stime.substring(11,stime.length());  
		
		String etime=areacheckstat.getEtime(); 
//		String e1=etime.substring(0,10);  
//		String e2=etime.substring(11,etime.length());
		String userid = areacheckstat.getUserid();
		sb.append(" select 员工编号 stafferid,卡号 cardid,姓名 name,工种 worktype,班组 gro,区域编号 areaid,区域名称 areaname,substring(rtrim(100+sum(worktime)/60),2,2)+'小时'+substring(rtrim(100+sum(worktime)%60),2,2)+'分' sticktime,sum(worktime) worktime,count(*) areacount from ( ");
		sb.append(" select bb.areaname 区域名称,aa.* from ( ");
		sb.append(" select datediff(mi,a.starttime,a.endtime) worktime,a.areaid as 区域编号,a.cardid as 卡号,a.enterreader,a.leavereader,b.stafferid as 员工编号,b.[name] as 姓名,b.worktype as 工种,b.[group] as 班组,a.starttime as 进入区域时间, "); 
		sb.append(" a.endtime as 离开区域时间,c.downtime as 入井时间,c.uptime as 升井时间  ");
		sb.append(" from arealocatedata a,staffer b,workattendanceex c,reportpopedom rp where rp.department=b.department and rp.userid='"+userid+"' and a.cardid=b.cardid and c.stafferid=b.stafferid and a.starttime+0.00028>=c.downtime and (a.starttime<=c.uptime+0.00028 or c.uptime is null) ");
		sb.append(" and starttime>=? ");
		param.add(stime);
		sb.append(" and endtime<=? ");
		param.add(etime);
		if( sid!=null && !sid.equals("")){
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and b.stafferid=? ");
				param.add(vo.getStafferid());
			}else{//没有卡号或用户名
				sb.append(" and b.[name]=? ");
				param.add(sid);
			}
		}if( gro!=null && !gro.equals("")){
			sb.append(" and b.[group]=? ");
			param.add(gro);
		}if( type!=null && !type.equals("")){
			sb.append(" and b.worktype=? ");
			param.add(type);
		} if( areaid!=null && !areaid.equals("")){
			sb.append(" and a.areaid=? ");
			param.add(areaid);
		}
		sb.append(" ) aa left join v_areainfo bb on aa.区域编号=bb.AreaID ");
		sb.append(" ) a group by 员工编号,卡号,姓名,工种,班组,区域编号,区域名称 ");
		
		
		try{
			
			
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),AreaCheckStatVO.class);
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
	}
}
