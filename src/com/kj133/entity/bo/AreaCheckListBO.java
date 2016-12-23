package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_AreaCheckList;
import com.kj133.entity.vo.AreaCheckListVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;
public class AreaCheckListBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public AreaCheckListBO(){	
    
	}
	
	@SuppressWarnings("unchecked")
	public List init(Search_AreaCheckList areachecklist,Pagination pagin)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		 
		String sid=areachecklist.getSid(); 
		String gro=areachecklist.getGro();   
		String type=areachecklist.getType();
		String areaid=areachecklist.getAreaid();
		String minstime=areachecklist.getMinstime();
		String maxstime=areachecklist.getMaxstime();
		String stime=areachecklist.getStime();  
		String etime=areachecklist.getEtime(); 
		String userid=areachecklist.getUserid();
		sb.append(" select bb.areaname,aa.*,cc.ShortName sshortname,dd.ShortName eshortname from ( ");
		sb.append(" select a.areaid,a.cardid,a.enterreader,a.leavereader,b.stafferid,b.[name],b.worktype,b.[group] gro,convert(varchar(19),a.starttime,20) as starttime, ");
		sb.append(" convert(varchar(19),a.endtime,20) as endtime,substring(rtrim(100+datediff(mi,a.starttime,a.endtime)/60),2,2)+'小时'+substring(rtrim(100+datediff(mi,a.starttime,a.endtime)%60),2,2)+'分' setime,convert(varchar(19),c.downtime,20) as downtime,convert(varchar(19),c.uptime,20) as uptime "); 
		sb.append(" from arealocatedata a,staffer b,workattendanceex c ,reportpopedom rp where  rp.department=b.department and rp.userid='"+userid+"' and a.cardid=b.cardid and c.stafferid=b.stafferid and a.starttime+0.00028>=c.downtime and (a.starttime<=c.uptime+0.00028 or c.uptime is null) ");
		sb.append(" and starttime>=? ");
		param.add(stime);
		sb.append(" and endtime<=? ");
		param.add(etime);
		

		if( minstime!=null && !minstime.equals("")){
			sb.append(" and datediff(mi,a.starttime,a.endtime)>=? ");
			param.add(Integer.valueOf(minstime.substring(0,2))*60+Integer.valueOf(minstime.substring(3,5)));
		}if( maxstime!=null && !maxstime.equals("")){
			sb.append(" and datediff(mi,a.starttime,a.endtime)<=? ");
			param.add(Integer.valueOf(maxstime.substring(0,2))*60+Integer.valueOf(maxstime.substring(3,5)));
		}if( sid!=null && !sid.equals("")){
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
		sb.append(" ) aa left join v_areainfo bb on aa.areaid=bb.AreaID ");
		sb.append(" left join (select * from cardreader where cardreaderid<>0) cc on aa.enterreader=cc.cardreaderid ");
		sb.append(" left join (select * from cardreader where cardreaderid<>0) dd on aa.leavereader=dd.cardreaderid ");

		
		try{
		    
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),AreaCheckListVO.class,pagin);
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
	public List initPrint(Search_AreaCheckList areachecklist)throws Exception{
		List param=new ArrayList();
		List queryList = new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		
		String sid=areachecklist.getSid(); 
		String gro=areachecklist.getGro();   
		String type=areachecklist.getType();
		String areaid=areachecklist.getAreaid();
		String minstime=areachecklist.getMinstime();
		String maxstime=areachecklist.getMaxstime();
		String stime=areachecklist.getStime();  
		String etime=areachecklist.getEtime(); 
		String userid=areachecklist.getUserid();
		
		sb.append(" select bb.areaname,aa.*,cc.ShortName sshortname,dd.ShortName eshortname from ( ");
		sb.append(" select a.areaid,a.cardid,a.enterreader,a.leavereader,b.stafferid,b.[name],b.worktype,b.[group] gro,convert(varchar(19),a.starttime,20) as starttime, ");
		sb.append(" convert(varchar(19),a.endtime,20) as endtime,substring(rtrim(100+datediff(mi,a.starttime,a.endtime)/60),2,2)+'小时'+substring(rtrim(100+datediff(mi,a.starttime,a.endtime)%60),2,2)+'分' setime,convert(varchar(19),c.downtime,20) as downtime,convert(varchar(19),c.uptime,20) as uptime "); 
		sb.append(" from arealocatedata a,staffer b,workattendanceex c ,reportpopedom rp where  rp.department=b.department and rp.userid='"+userid+"' and a.cardid=b.cardid and c.stafferid=b.stafferid and a.starttime+0.00028>=c.downtime and (a.starttime<=c.uptime+0.00028 or c.uptime is null) ");
		sb.append(" and starttime>=? ");
		param.add(stime);
		sb.append(" and endtime<=? ");
		param.add(etime);
		
		if( minstime!=null && !minstime.equals("")){
			sb.append(" and datediff(mi,a.starttime,a.endtime)>=? ");
			param.add(Integer.valueOf(minstime.substring(0,2))*60+Integer.valueOf(minstime.substring(3,5)));
		}if( maxstime!=null && !maxstime.equals("")){
			sb.append(" and datediff(mi,a.starttime,a.endtime)<=? ");
			param.add(Integer.valueOf(maxstime.substring(0,2))*60+Integer.valueOf(maxstime.substring(3,5)));
		}if( sid!=null && !sid.equals("")){
			Global  gobal=new Global();
			List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){
			SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
			sb.append(" and b.stafferid=? ");
			param.add(vo.getStafferid());
			}else{
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
		sb.append(" ) aa left join v_areainfo bb on aa.areaid=bb.AreaID ");
		sb.append(" left join (select * from cardreader where cardreaderid<>0) cc on aa.enterreader=cc.cardreaderid ");
		sb.append(" left join (select * from cardreader where cardreaderid<>0) dd on aa.leavereader=dd.cardreaderid ");
		
		
		try{
			
			
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			//xin jia
			queryList = query.getResults(sb.toString(),param.toArray(),AreaCheckListVO.class);
			engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;
		}finally{
			engine.closeEngine();
		}
		
		return queryList;
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
