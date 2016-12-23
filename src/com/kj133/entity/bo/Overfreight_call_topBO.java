package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_overfreight_call_top;
import com.kj133.entity.vo.Overfreight_call_topVO;


public class Overfreight_call_topBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public Overfreight_call_topBO(){	
    
	}
	
	@SuppressWarnings("unchecked")
	public List init(Search_overfreight_call_top overfreight_call_top,String userid)throws Exception{
		List relist=null;
		
		List param=new ArrayList();
		Engine engine=null;
		
		
		StringBuffer sb=new StringBuffer();
		
		String at = overfreight_call_top.getAreatype();
		String an = overfreight_call_top.getAreaname();
	
		String stime=overfreight_call_top.getStime();  
		  
     
		String etime=overfreight_call_top.getEtime(); 
		

//		System.out.println("stime = " + stime);
//		System.out.println("etime = " + etime);
		
		sb.append("select ����� aid,�������� aname,��Ա���� otype,��Ա��ʼʱ�� os,��Ա����ʱ�� oe from ( ");
		sb.append(" select a.areaid as �����,a.overtype as ��Ա����,convert(varchar(19),a.overStime,20) as ��Ա��ʼʱ��,convert(varchar(19),a.overEtime,20) as ��Ա����ʱ��,a.userid as �û���,b.AreaName �������� from OverPeople a,v_areainfo b where a.userid=? and a.areaid=b.AreaID ");
//		sb.append("select a.areaid as �����,a.overtype as ��Ա����,a.overStime as ��Ա��ʼʱ��,a.overEtime as ��Ա����ʱ��,a.userid as �û���,b.AreaName �������� from OverPeople a,v_areainfo b where a.areaid=b.AreaID ");
		param.add(userid);
		if(at!=null && !at.equals("")){
			sb.append(" and b.areatype=? ");
			param.add(at);
		}
		if(an!=null && !an.equals("")){
			sb.append(" and b.areaname=? ");
			param.add(an);
		}
		sb.append(") as QueryTable ");
				
		/*sb.append(" select ����� aid,�������� aname,���뱨��ʱ�� atime,count(*) acount from ( ");
		sb.append(" select c.*,d.cardid from ( ");
		sb.append(" select AreaID �����,AreaName ��������,RecordTime ���뱨��ʱ�� from ( ");
		sb.append(" select a.*,b.AreaName,b.AreaType,b.maxsum,b.caijuesum from areadata a left join v_AreaInfo b on a.AreaID=b.AreaID and a.PeopleCount>0 ");
		sb.append(" ) aa where AreaType='��������' and maxsum=0 and caijuesum=0 ");
		sb.append(" and recordtime>=? and recordtime<=? ");
		
		param.add(stime);
		param.add(etime);
		sb.append(" ) c left join  ");
		sb.append(" (select * from arealocatedata) d on c.�����=d.areaid where "); 
		sb.append(" starttime>=c.���뱨��ʱ��-1 and starttime<=c.���뱨��ʱ�� and (endtime>=c.���뱨��ʱ�� or endtime is null) ");
		sb.append(" ) cc group by �����,��������,���뱨��ʱ�� ");*/
		
	
		try{
			engine=EngineFactory.getEngine("test");
			engine.executeProcedureCall("{call calovertime (?,?,?) }",new Object[]{userid,stime,etime});
		

			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),Overfreight_call_topVO.class);
			
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
	
	
/*	public List choiceRadio() throws Exception {
		Engine engine = null;
		List result = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select aid,areaname,areaorder,parentarea,areatype,maxsum,caijuesum,stayminute from ( ");
		sb.append(" select AreaID as aid,areaname,areaorder,parentarea,areatype,maxsum,caijuesum,stayminute from v_areainfo "); 
		sb.append(" ) as QueryTable ");
		
//		sb.append(" select '��վ' as choice_type,shortname as choice_shortname,cardreaderid as choice_cardreaderid  ");
//		sb.append(" from cardreader where cardreaderid<>0 union all select '��λ��',shortname,locatorid from locator ");
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			result = query.getResults(sb.toString(), param.toArray(),
					Area_listVO.class);
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return result;
	}*/

	
	
	/**
	 * ���ڱ�������ͺϼ���Ϣ
	 * */
	@SuppressWarnings("unchecked")
	public  List getAllWorkTime(Search_overfreight_call_top overfreight_call)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		
		String stime=overfreight_call.getStime();  
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length());  
     
		String etime=overfreight_call.getEtime(); 
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
			relist=query.getResults(sb.toString(),param.toArray(),Overfreight_call_topVO.class);
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
