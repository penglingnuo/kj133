package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_ShuaKa;
import com.kj133.entity.vo.DownWellVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;
public class DownWellBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public DownWellBO(){	
    
	}
	
	@SuppressWarnings("unchecked")
	public List init(Search_ShuaKa downwell,Pagination pagin)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		 
		String sid=downwell.getSid(); 
		String gro=downwell.getGro();   
		String type=downwell.getType(); 
		String banci=downwell.getBanci();
		 
		String stime=downwell.getStime();  
		String etime=downwell.getEtime(); 
		String dstime=downwell.getDstime(); 
		String detime=downwell.getDetime(); 
		String ksdate=downwell.getKsdate(); 
		String kedate=downwell.getKedate(); 
		String ustime=downwell.getUstime(); 
		String uetime=downwell.getUetime(); 
		String mintime=downwell.getMintime(); 
		String maxtime=downwell.getMaxtime(); 
		String userid=downwell.getUserid();
		
		
		sb.append(" select kqtime,卡号 cardid,员工编号 stafferid,姓名 username,班组 gro,工种 worktype,downtime intime,uptime upwelltime,工作时间 worktime,入井分站 incardreader,入井分站名称 incardreadername,入井定位器 upcardreader,入井定位器名称 upcardreadername,升井分站 inlocator,升井分站名称 inlocatorname,升井定位器 uplocator,升井定位器名称 uplocatorname,部门 department from ( ");
		sb.append(" select convert(float,uptime-downtime) days,kqtime,convert(varchar(19),uptime,20) uptime,convert(varchar(19),downtime,20) downtime,temp1.cardid as 卡号,isnull(temp1.stafferID,'未绑定') 员工编号,convert(datetime,temp1.downtime,20) as 入井时间,convert(datetime,temp1.uptime,20) as 升井时间,isnull([Name],'未绑定') 姓名,department 部门,isnull([group],'未绑定') 班组,isnull(worktype,'其他') 工种,substring(rtrim(100+datediff(mi,downtime,uptime)/60),2,2)+'小时'+substring(rtrim(100+datediff(mi,downtime,uptime)%60),2,2)+'分' 工作时间,substring(convert(char,temp1.downtime,120),1,10) as downdate,temp1.入井分站,x.ShortName as 入井分站名称,temp1.入井定位器,m.ShortName as 入井定位器名称,temp1.升井分站,y.ShortName as 升井分站名称,temp1.升井定位器,n.ShortName as 升井定位器名称 from(select downtime,stafferid,cardid,uptime,downcardreaderid as 入井分站,downlocatorid 入井定位器,upcardreaderid 升井分站,uplocatorid 升井定位器,convert(char,kqtime,111) as kqtime from WorkattendanceEx where 1=1  "); 
		
		if( stime!=null && !stime.equals("")){
			sb.append(" and downtime>=? and (uptime>=? or uptime is null) ");
			param.add(stime);
			param.add(stime);
		}if( etime!=null && !etime.equals("")){
			sb.append("  and  downtime<convert(datetime,?)+1 and (uptime<convert(datetime,?)+3 or uptime is null)  ");
			param.add(etime);
			param.add(etime);
		}if( ksdate!=null && !ksdate.equals("")){
		sb.append(" and kqtime>=? ");
		param.add(ksdate);
		
	    }if( kedate!=null && !kedate.equals("")){
		sb.append(" and  kqtime<convert(datetime,?)+1  ");
		param.add(kedate);
		}
		sb.append(" ) as temp1 left join staffer on temp1.stafferID=staffer.stafferID left join locator as m on temp1.入井定位器=m.locatorID left join locator as n on temp1.升井定位器=n.locatorID left join cardreader x on temp1.入井分站=x.cardreaderid left join cardreader y on temp1.升井分站=y.cardreaderid "); 
		
		sb.append(" ) as QueryTable ,reportpopedom rp  ");
		sb.append(" where 部门=rp.department and  userid='"+userid+"' and 1=1   "); 
		
		
		if( dstime!=null && !dstime.equals("")){
			sb.append(" and convert(char(8),入井时间,8)>=?  ");
			param.add(dstime);
		}if( detime!=null && !detime.equals("")){
			sb.append("  and  convert(char(8),入井时间,8)<=?  ");
			param.add(detime);
		}if( ustime!=null && !ustime.equals("")){
			sb.append("  and  convert(char(8),升井时间,8)>=?  ");
			param.add(ustime);
		}if( uetime!=null && !uetime.equals("")){
			sb.append(" and convert(char(8),升井时间,8)<=? ");
			param.add(uetime);
		}if( mintime!=null && !mintime.equals("")){
			sb.append("  and  datediff(second,入井时间,升井时间)>=datediff(second,?,?)  ");
			param.add("00:00:00");
			param.add(mintime);
		}if( maxtime!=null && !maxtime.equals("")){
			sb.append("  and  datediff(second,入井时间,升井时间)<datediff(second,?,?)  ");
			param.add("00:00:00");
			param.add(maxtime);
		}if( sid!=null && !sid.equals("")){
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and rtrim(员工编号)=? ");
				param.add(vo.getStafferid());
			}else{//没有卡号或用户名
				sb.append(" and 姓名=? ");
				param.add(sid);
			}
//			sb.append(" and rtrim(cardid) = ? ");
//			param.add(sid);
		}if( gro!=null && !gro.equals("")){
			sb.append(" and 班组 = ? ");
			param.add(gro);
		}if( type!=null && !type.equals("")){
			sb.append(" and  工种 = ? ");
			param.add(type);
		}

	
		try{

			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),DownWellVO.class,pagin);
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
	@SuppressWarnings("unchecked")
	public List initPrint(Search_ShuaKa downwell)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		
		String sid=downwell.getSid(); 
		String gro=downwell.getGro();   
		String type=downwell.getType(); 
		
		
		String stime=downwell.getStime();  
		String etime=downwell.getEtime(); 
		String dstime=downwell.getDstime(); 
		String detime=downwell.getDetime(); 
		String ksdate=downwell.getKsdate(); 
		String kedate=downwell.getKedate(); 
		String ustime=downwell.getUstime(); 
		String uetime=downwell.getUetime(); 
		String mintime=downwell.getMintime(); 
		String maxtime=downwell.getMaxtime(); 
		String userid=downwell.getUserid();
		
		
		sb.append(" select kqtime,卡号 cardid,员工编号 stafferid,姓名 username,班组 gro,工种 worktype,downtime intime,uptime upwelltime,工作时间 worktime,入井分站 incardreader,入井分站名称 incardreadername,入井定位器 upcardreader,入井定位器名称 upcardreadername,升井分站 inlocator,升井分站名称 inlocatorname,升井定位器 uplocator,升井定位器名称 uplocatorname,部门 department from ( ");
		sb.append(" select convert(float,uptime-downtime) days,kqtime,convert(varchar(19),uptime,20) uptime,convert(varchar(19),downtime,20) downtime,temp1.cardid as 卡号,isnull(temp1.stafferID,'未绑定') 员工编号,convert(datetime,temp1.downtime,20) as 入井时间,convert(datetime,temp1.uptime,20) as 升井时间,isnull([Name],'未绑定') 姓名,department 部门,isnull([group],'未绑定') 班组,isnull(worktype,'其他') 工种,substring(rtrim(100+datediff(mi,downtime,uptime)/60),2,2)+'小时'+substring(rtrim(100+datediff(mi,downtime,uptime)%60),2,2)+'分' 工作时间,substring(convert(char,temp1.downtime,120),1,10) as downdate,temp1.入井分站,x.ShortName as 入井分站名称,temp1.入井定位器,m.ShortName as 入井定位器名称,temp1.升井分站,y.ShortName as 升井分站名称,temp1.升井定位器,n.ShortName as 升井定位器名称 from(select downtime,stafferid,cardid,uptime,downcardreaderid as 入井分站,downlocatorid 入井定位器,upcardreaderid 升井分站,uplocatorid 升井定位器,convert(char,kqtime,111) as kqtime from WorkattendanceEx where 1=1  "); 
		sb.append("   "); 
		if( stime!=null && !stime.equals("")){
			sb.append(" and downtime>=? and (uptime>=? or uptime is null) ");
			param.add(stime);
			param.add(stime);
		}if( etime!=null && !etime.equals("")){
			sb.append("  and  downtime<convert(datetime,?)+1 and (uptime<convert(datetime,?)+3 or uptime is null)  ");
			param.add(etime);
			param.add(etime);
		}if( ksdate!=null && !ksdate.equals("")){
		sb.append(" and kqtime>=? ");
		param.add(ksdate);
		
	    }if( kedate!=null && !kedate.equals("")){
		sb.append(" and  kqtime<convert(datetime,?)+1  ");
		param.add(kedate);
		}
		sb.append(" ) as temp1 left join staffer on temp1.stafferID=staffer.stafferID left join locator as m on temp1.入井定位器=m.locatorID left join locator as n on temp1.升井定位器=n.locatorID left join cardreader x on temp1.入井分站=x.cardreaderid left join cardreader y on temp1.升井分站=y.cardreaderid "); 
		
		sb.append(" ) as QueryTable ,reportpopedom rp  ");
		sb.append(" where 部门=rp.department and userid='"+userid+"' and 1=1   "); 
		
		if( dstime!=null && !dstime.equals("")){
			sb.append(" and convert(char(8),入井时间,8)>=?  ");
			param.add(dstime);
		}if( detime!=null && !detime.equals("")){
			sb.append("  and  convert(char(8),入井时间,8)<=?  ");
			param.add(detime);
		}if( ustime!=null && !ustime.equals("")){
			sb.append("  and  convert(char(8),升井时间,8)>=?  ");
			param.add(ustime);
		}if( uetime!=null && !uetime.equals("")){
			sb.append("  and convert(char(8),升井时间,8)<=?  ");
			param.add(uetime);
		}if( mintime!=null && !mintime.equals("")){
			sb.append("  and  datediff(second,入井时间,升井时间)>=datediff(second,?,?)  ");
			param.add("00:00:00");
			param.add(mintime);
		}if( maxtime!=null && !maxtime.equals("")){
			sb.append("  and  datediff(second,入井时间,升井时间)<datediff(second,?,?)  ");
			param.add("00:00:00");
			param.add(maxtime);
		}if( sid!=null && !sid.equals("")){
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and rtrim(卡号)=? ");
				param.add(vo.getStafferid());
			}else{//没有卡号或用户名
				sb.append(" and 姓名=? ");
				param.add(sid);
			}
//			sb.append(" and rtrim(cardid) = ? ");
//			param.add(sid);
		}if( gro!=null && !gro.equals("")){
			sb.append(" and 班组 = ? ");
			param.add(gro);
		}if( type!=null && !type.equals("")){
			sb.append(" and  工种 = ? ");
			param.add(type);
		}
		
		try{
			
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			System.out.println("sql="+sb.toString());
			relist=query.getResults(sb.toString(),param.toArray(),DownWellVO.class);
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
