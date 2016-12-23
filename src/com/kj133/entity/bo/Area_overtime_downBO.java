package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import com.kj133.entity.Search_car_move_log;
import com.kj133.entity.vo.Area_overtime_downVO;
import com.kj133.entity.vo.Car_move_logVO;

/**
 * 区域超时明细
 * @author wang
 *
 */

public class Area_overtime_downBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public Area_overtime_downBO(){	
    
	}
	

	@SuppressWarnings("unchecked")
	public List check(String stime,String etime,String areaid) throws Exception {
		Engine engine = null;
		List relist = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();

		
		sb.append(" select aa.areaid,aa.areaname,aa.cardid,d.[name],aa.starttime,aa.endtime,aa.stayinterval,aa.限定时间 limittime,aa.downtime,rtrim(aa.sst/60)+'小时'+rtrim(sst-60*(sst/60))+'分' as settletime,aa.uptime from ");
		sb.append(" (select a.areaid,b.areaname,a.cardid,a.starttime,a.endtime,a.stayinterval, ");
		sb.append(" rtrim(b.stayminute/60)+'小时'+rtrim(b.stayminute-60*(b.stayminute/60))+'分' as 限定时间,c.downtime, ");
		sb.append(" case when c.uptime is null then datediff(n,c.downtime,getdate()) else datediff(n,c.downtime,c.uptime) end "); 
		sb.append(" as sst,c.uptime from arealocatedata a,areainfo b,workattendanceex c where a.areaid=b.AreaID and dateadd(n,b.stayminute,a.starttime)<=a.endtime and c.cardid=a.cardid and c.downtime<=a.starttime and (c.uptime>=a.endtime or c.uptime is null) ");
		sb.append(" and starttime >=? and endtime <=? and a.areaid=? ");
		
		param.add(stime);
		param.add(etime);

		param.add(areaid);
		sb.append(" )as aa,staffer d where aa.cardid=d.cardid ");
/*		sb.append(" select * from (select routeorder as routeorder,a.type as type,pointID as pointid,b.shortname as shortname from RoutDetail ");
		sb.append(" as a,(select '分u31449 ' as type,shortname,cardreaderid as [ID] from cardreader union all select '定u20301 器 as type,shortname, ");
		sb.append(" locatorid as [ID] from locator)as b where a.type=b.type and a.pointID=b.[ID] and code= ? ) as QueryTable order by routeorder ");
		param.add(code);*/
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),Area_overtime_downVO.class);
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return relist;
	}


	@SuppressWarnings("unchecked")
	public List init(Search_car_move_log car_move_log)throws Exception{
		List relist=null;
		List resultlist = new ArrayList();
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		 
		String cardid=car_move_log.getCardid(); 
		String cartype=car_move_log.getCartype(); 
		/*		String gro=car_move_log.getGro();   
		String banci=car_move_log.getBanci();
		 */
		String stime=car_move_log.getStime();  
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length());  
     
		String etime=car_move_log.getEtime(); 
		String e1=etime.substring(0,10);  
		String e2=etime.substring(11,etime.length());
		
		sb.append(" select t.姓名 carname,t.卡号 cardid,t.工种 cartype,t.入井时间 intime,t.升井时间 upwelltime,t.工作时间 worktime from (");
		sb.append(" select *,datediff(mi,downtime,uptime) worktime from  ");
		sb.append(" ( select kqtime 考勤时间,uptime,downtime,temp1.cardid as 卡号,isnull(temp1.stafferID,'未绑定') 员工编号,temp1.downtime as 入井时间, ");
		sb.append(" temp1.uptime as 升井时间,isnull([Name],'未绑定') 姓名,isnull([group],'未绑定') 班组,isnull(worktype,'其他') 工种, ");
		sb.append(" substring(rtrim(100+datediff(mi,downtime,uptime)/60),2,2)+'小时'+substring(rtrim(100+datediff(mi,downtime,uptime)%60),2,2)+'分' as 工作时间, ");
		sb.append(" substring(convert(char,temp1.downtime,120),1,10) as downdate from(select downtime,stafferid,cardid,uptime,convert(char,kqtime,111) as kqtime from  ");
		sb.append(" (select aa.* from workattendanceex aa,recog bb where aa.cardid=bb.cardid and bb.CardMode='车辆') workattendanceex where ");
		sb.append("  kqtime>=? and  kqtime<convert(datetime,?)+1 and  kqtime<convert(datetime,?)+1 ) as temp1 left join staffer on temp1.stafferID=staffer.stafferID ) a where ");
		param.add(s1);
		param.add(e1);
		param.add(e1);
		
//		sb.append(" (升井时间>=入井时间 or 升井时间 is null) and  rtrim(员工编号) =? and  工种=? and ");
		sb.append(" (升井时间>=入井时间 or 升井时间 is null)  ");
		if( cardid!=null && !cardid.equals("")){
			sb.append(" and rtrim(员工编号) = ? ");
			param.add(cardid);
			
		}if( cartype!=null && !cartype.equals("")){
			sb.append(" and  工种 = ? ");
			param.add(cartype);
			
		} ;
		
		sb.append(" and  datediff(second,入井时间,升井时间)>=datediff(second,?,?)   ");
		param.add(s2);
		
		param.add(s2);
		if(e2.equals("00:00:00")){
		
		sb.append(" ) t order by t.工种,t.入井时间");
		}else{
			sb.append(" and datediff(second,入井时间,升井时间)<datediff(second,?,?) ");
			param.add(s2);
			param.add(e2);
			sb.append(" ) t order by t.工种,t.入井时间");
		}
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),Car_move_logVO.class);
			if(relist != null&&relist.size()>0){
				for(int i =0;i<relist.size();i++){
					Car_move_logVO vo = (Car_move_logVO) relist
					.get(i);
			        vo.setCount(String.valueOf(i + 1));//序号	
			        
			        resultlist.add(vo);  
				}
			
			
				
				
			}
			engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;
		}finally{
			engine.closeEngine();
		}
		return resultlist;
	}	
	
	/**
	 * 用于报表里面和合计信息
	 * */
	@SuppressWarnings("unchecked")
	public  List getAllWorkTime(Search_car_move_log car_move_log)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		
		String stime=car_move_log.getStime();  
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length());  
     
		String etime=car_move_log.getEtime(); 
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
			relist=query.getResults(sb.toString(),param.toArray(),Car_move_logVO.class);
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
