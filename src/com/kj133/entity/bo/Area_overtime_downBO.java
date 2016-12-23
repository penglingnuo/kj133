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
 * ����ʱ��ϸ
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

		
		sb.append(" select aa.areaid,aa.areaname,aa.cardid,d.[name],aa.starttime,aa.endtime,aa.stayinterval,aa.�޶�ʱ�� limittime,aa.downtime,rtrim(aa.sst/60)+'Сʱ'+rtrim(sst-60*(sst/60))+'��' as settletime,aa.uptime from ");
		sb.append(" (select a.areaid,b.areaname,a.cardid,a.starttime,a.endtime,a.stayinterval, ");
		sb.append(" rtrim(b.stayminute/60)+'Сʱ'+rtrim(b.stayminute-60*(b.stayminute/60))+'��' as �޶�ʱ��,c.downtime, ");
		sb.append(" case when c.uptime is null then datediff(n,c.downtime,getdate()) else datediff(n,c.downtime,c.uptime) end "); 
		sb.append(" as sst,c.uptime from arealocatedata a,areainfo b,workattendanceex c where a.areaid=b.AreaID and dateadd(n,b.stayminute,a.starttime)<=a.endtime and c.cardid=a.cardid and c.downtime<=a.starttime and (c.uptime>=a.endtime or c.uptime is null) ");
		sb.append(" and starttime >=? and endtime <=? and a.areaid=? ");
		
		param.add(stime);
		param.add(etime);

		param.add(areaid);
		sb.append(" )as aa,staffer d where aa.cardid=d.cardid ");
/*		sb.append(" select * from (select routeorder as routeorder,a.type as type,pointID as pointid,b.shortname as shortname from RoutDetail ");
		sb.append(" as a,(select '��u31449 ' as type,shortname,cardreaderid as [ID] from cardreader union all select '��u20301 �� as type,shortname, ");
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
		
		sb.append(" select t.���� carname,t.���� cardid,t.���� cartype,t.�뾮ʱ�� intime,t.����ʱ�� upwelltime,t.����ʱ�� worktime from (");
		sb.append(" select *,datediff(mi,downtime,uptime) worktime from  ");
		sb.append(" ( select kqtime ����ʱ��,uptime,downtime,temp1.cardid as ����,isnull(temp1.stafferID,'δ��') Ա�����,temp1.downtime as �뾮ʱ��, ");
		sb.append(" temp1.uptime as ����ʱ��,isnull([Name],'δ��') ����,isnull([group],'δ��') ����,isnull(worktype,'����') ����, ");
		sb.append(" substring(rtrim(100+datediff(mi,downtime,uptime)/60),2,2)+'Сʱ'+substring(rtrim(100+datediff(mi,downtime,uptime)%60),2,2)+'��' as ����ʱ��, ");
		sb.append(" substring(convert(char,temp1.downtime,120),1,10) as downdate from(select downtime,stafferid,cardid,uptime,convert(char,kqtime,111) as kqtime from  ");
		sb.append(" (select aa.* from workattendanceex aa,recog bb where aa.cardid=bb.cardid and bb.CardMode='����') workattendanceex where ");
		sb.append("  kqtime>=? and  kqtime<convert(datetime,?)+1 and  kqtime<convert(datetime,?)+1 ) as temp1 left join staffer on temp1.stafferID=staffer.stafferID ) a where ");
		param.add(s1);
		param.add(e1);
		param.add(e1);
		
//		sb.append(" (����ʱ��>=�뾮ʱ�� or ����ʱ�� is null) and  rtrim(Ա�����) =? and  ����=? and ");
		sb.append(" (����ʱ��>=�뾮ʱ�� or ����ʱ�� is null)  ");
		if( cardid!=null && !cardid.equals("")){
			sb.append(" and rtrim(Ա�����) = ? ");
			param.add(cardid);
			
		}if( cartype!=null && !cartype.equals("")){
			sb.append(" and  ���� = ? ");
			param.add(cartype);
			
		} ;
		
		sb.append(" and  datediff(second,�뾮ʱ��,����ʱ��)>=datediff(second,?,?)   ");
		param.add(s2);
		
		param.add(s2);
		if(e2.equals("00:00:00")){
		
		sb.append(" ) t order by t.����,t.�뾮ʱ��");
		}else{
			sb.append(" and datediff(second,�뾮ʱ��,����ʱ��)<datediff(second,?,?) ");
			param.add(s2);
			param.add(e2);
			sb.append(" ) t order by t.����,t.�뾮ʱ��");
		}
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),Car_move_logVO.class);
			if(relist != null&&relist.size()>0){
				for(int i =0;i<relist.size();i++){
					Car_move_logVO vo = (Car_move_logVO) relist
					.get(i);
			        vo.setCount(String.valueOf(i + 1));//���	
			        
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
	 * ���ڱ�������ͺϼ���Ϣ
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
		sb.append(" select isnull(allworktime,' ') as allworktime from (select rtrim(convert(int,sum(convert(float,uptime-downtime))))+'��'+rtrim(datepart(hh,sum(convert(float,uptime-downtime)))) ");
		sb.append(" +'Сʱ'+rtrim(datepart(n,sum(convert(float,uptime-downtime))))+'��' as allworktime from ( select uptime,downtime, ");
		sb.append(" temp1.downtime as �뾮ʱ��  from(select downtime  , uptime   from WorkattendanceEx where  downtime>= ? and (uptime>= ? ");
		sb.append(" or uptime is null) and  downtime<convert(datetime, ?)+1 and (uptime<convert(datetime, ?)+3 or uptime  ");
		sb.append(" is null)) as temp1  ) as Sumtable  where  convert(char(8),�뾮ʱ��,8)>= ? and  convert(char(8),�뾮ʱ��,8)<= ? )  as tab");
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
