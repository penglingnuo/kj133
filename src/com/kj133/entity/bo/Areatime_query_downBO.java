package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.vo.Areatime_query_downVO;

/**
 * ����ʱ��ϸ
 * 
 * @author wang
 * 
 */

public class Areatime_query_downBO {

	private final Logger log = Logger.getLogger(this.getClass());

	public Areatime_query_downBO() {

	}

	@SuppressWarnings("unchecked")
	public List check(String atime, String aid) throws Exception {
		Engine engine = null;
		List relist = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();

		sb.append(" select aa.areaid,bb.areaname,aa.cardid,aa.stafferid,aa.[name],aa.worktype,aa.[group] as gro,aa.starttime,cc.ShortName inway,aa.endtime,dd.ShortName outway,aa.downtime,aa.uptime from ( ");
		sb.append(" select a.areaid,a.cardid,a.enterreader,a.leavereader,b.stafferid,b.[name],b.worktype,b.[group],a.starttime, ");
		sb.append(" a.endtime,c.downtime,c.uptime  ");
		sb.append(" from arealocatedata a,staffer b,workattendanceex c where a.cardid=b.cardid and c.stafferid=b.stafferid ");
		sb.append(" and a.starttime<=? and (a.endtime >=? or a.endtime is null) and c.downtime<=? and (c.uptime>=? or c.uptime is null) ");
		param.add(atime);
		param.add(atime);
		param.add(atime);
		param.add(atime);
		sb.append("  and a.areaid=? ");
		param.add(aid);
		sb.append(" ) aa left join v_areainfo bb on aa.areaid=bb.AreaID ");
		sb.append("  left join (select * from cardreader where cardreaderid<>0) cc on aa.enterreader=cc.cardreaderid ");
		sb.append(" left join (select * from cardreader where cardreaderid<>0) dd on aa.leavereader=dd.cardreaderid ");

		/*
		 * sb.append(" select
		 * aa.areaid,aa.recordtime,aa.maxsum,aa.caijuesum,aa.peoplecount,aa.minercount,aa.allchaomans,aa.chaomans,bb.areaname
		 * aname from ( "); sb.append(" select
		 * a.areaid,a.recordtime,b.maxsum,b.caijuesum, "); sb.append("
		 * a.peoplecount,a.minercount, "); sb.append(" case when
		 * a.peoplecount-b.maxsum<0 then 0 else a.peoplecount-b.maxsum end as
		 * allchaomans, "); sb.append(" case when a.minercount-b.caijuesum<0
		 * then 0 else a.minercount-b.caijuesum end as chaomans from areadata
		 * a,v_areainfo b where a.areaid=b.areaid and (a.peoplecount>b.maxsum or
		 * a.minercount>b.caijuesum) "); sb.append(" and a.areaid=0 ");
		 * sb.append(" and a.recordtime>=? "); param.add(btime); sb.append(" and
		 * a.recordtime<=? "); param.add(atime); sb.append(" ) aa left join
		 * v_areainfo bb on aa.areaid=bb.areaid ");
		 */
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),
					Areatime_query_downVO.class);
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

	/*
	 * @SuppressWarnings("unchecked") public List init(Search_car_move_log
	 * car_move_log)throws Exception{ List relist=null; List resultlist = new
	 * ArrayList(); List param=new ArrayList(); Engine engine=null; StringBuffer
	 * sb=new StringBuffer();
	 * 
	 * String cardid=car_move_log.getCardid(); String
	 * cartype=car_move_log.getCartype(); String gro=car_move_log.getGro();
	 * String banci=car_move_log.getBanci();
	 * 
	 * String stime=car_move_log.getStime(); String s1=stime.substring(0,10);
	 * String s2=stime.substring(11,stime.length());
	 * 
	 * String etime=car_move_log.getEtime(); String e1=etime.substring(0,10);
	 * String e2=etime.substring(11,etime.length());
	 * 
	 * sb.append(" select t.���� carname,t.���� cardid,t.���� cartype,t.�뾮ʱ��
	 * intime,t.����ʱ�� upwelltime,t.����ʱ�� worktime from ("); sb.append(" select
	 * *,datediff(mi,downtime,uptime) worktime from "); sb.append(" ( select
	 * kqtime ����ʱ��,uptime,downtime,temp1.cardid as
	 * ����,isnull(temp1.stafferID,'δ��') Ա�����,temp1.downtime as �뾮ʱ��, ");
	 * sb.append(" temp1.uptime as ����ʱ��,isnull([Name],'δ��')
	 * ����,isnull([group],'δ��') ����,isnull(worktype,'����') ����, "); sb.append("
	 * substring(rtrim(100+datediff(mi,downtime,uptime)/60),2,2)+'Сʱ'+substring(rtrim(100+datediff(mi,downtime,uptime)%60),2,2)+'��'
	 * as ����ʱ��, "); sb.append(" substring(convert(char,temp1.downtime,120),1,10)
	 * as downdate from(select
	 * downtime,stafferid,cardid,uptime,convert(char,kqtime,111) as kqtime from
	 * "); sb.append(" (select aa.* from workattendanceex aa,recog bb where
	 * aa.cardid=bb.cardid and bb.CardMode='����') workattendanceex where ");
	 * sb.append(" kqtime>=? and kqtime<convert(datetime,?)+1 and kqtime<convert(datetime,?)+1 )
	 * as temp1 left join staffer on temp1.stafferID=staffer.stafferID ) a where
	 * "); param.add(s1); param.add(e1); param.add(e1);
	 *  // sb.append(" (����ʱ��>=�뾮ʱ�� or ����ʱ�� is null) and rtrim(Ա�����) =? and ����=?
	 * and "); sb.append(" (����ʱ��>=�뾮ʱ�� or ����ʱ�� is null) "); if( cardid!=null &&
	 * !cardid.equals("")){ sb.append(" and rtrim(Ա�����) = ? ");
	 * param.add(cardid);
	 * 
	 * }if( cartype!=null && !cartype.equals("")){ sb.append(" and ���� = ? ");
	 * param.add(cartype);
	 *  } ;
	 * 
	 * sb.append(" and datediff(second,�뾮ʱ��,����ʱ��)>=datediff(second,?,?) ");
	 * param.add(s2);
	 * 
	 * param.add(s2); if(e2.equals("00:00:00")){
	 * 
	 * sb.append(" ) t order by t.����,t.�뾮ʱ��"); }else{ sb.append(" and
	 * datediff(second,�뾮ʱ��,����ʱ��)<datediff(second,?,?) "); param.add(s2);
	 * param.add(e2); sb.append(" ) t order by t.����,t.�뾮ʱ��"); } try{
	 * engine=EngineFactory.getEngine("test"); Query query=engine.getQuery();
	 * relist=query.getResults(sb.toString(),param.toArray(),Car_move_logVO.class);
	 * if(relist != null&&relist.size()>0){ for(int i =0;i<relist.size();i++){
	 * Car_move_logVO vo = (Car_move_logVO) relist .get(i);
	 * vo.setCount(String.valueOf(i + 1));//���
	 * 
	 * resultlist.add(vo); }
	 * 
	 * 
	 * 
	 *  } // engine.commit(); }catch(Exception e){ engine.rollback();
	 * log.error(e); throw e; }finally{ engine.closeEngine(); } return
	 * resultlist; }
	 * 
	 *//**
		 * ���ڱ�������ͺϼ���Ϣ
		 */
	/*
	 * @SuppressWarnings("unchecked") public List
	 * getAllWorkTime(Search_car_move_log car_move_log)throws Exception{ List
	 * relist=null; List param=new ArrayList(); Engine engine=null;
	 * 
	 * String stime=car_move_log.getStime(); String s1=stime.substring(0,10);
	 * String s2=stime.substring(11,stime.length());
	 * 
	 * String etime=car_move_log.getEtime(); String e1=etime.substring(0,10);
	 * String e2=etime.substring(11,etime.length());
	 * 
	 * StringBuffer sb=new StringBuffer(); sb.append(" select
	 * isnull(allworktime,' ') as allworktime from (select
	 * rtrim(convert(int,sum(convert(float,uptime-downtime))))+'��'+rtrim(datepart(hh,sum(convert(float,uptime-downtime))))
	 * "); sb.append("
	 * +'Сʱ'+rtrim(datepart(n,sum(convert(float,uptime-downtime))))+'��' as
	 * allworktime from ( select uptime,downtime, "); sb.append(" temp1.downtime
	 * as �뾮ʱ�� from(select downtime , uptime from WorkattendanceEx where
	 * downtime>= ? and (uptime>= ? "); sb.append(" or uptime is null) and
	 * downtime<convert(datetime, ?)+1 and (uptime<convert(datetime, ?)+3 or
	 * uptime "); sb.append(" is null)) as temp1 ) as Sumtable where
	 * convert(char(8),�뾮ʱ��,8)>= ? and convert(char(8),�뾮ʱ��,8)<= ? ) as tab");
	 * param.add(s1); param.add(s1);
	 * 
	 * param.add(e1); param.add(e1); param.add(s2); param.add(e2);
	 * 
	 * try{ engine=EngineFactory.getEngine("test"); Query
	 * query=engine.getQuery();
	 * relist=query.getResults(sb.toString(),param.toArray(),Car_move_logVO.class);
	 * }catch(Exception e){ engine.rollback(); log.error(e); throw e; }finally{
	 * engine.closeEngine(); } return relist; }
	 */
}
