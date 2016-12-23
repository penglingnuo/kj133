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
import com.telezone.domain.classes.ShowBackup;
public class GjBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public GjBO(){	
    
	}
	
	@SuppressWarnings("unchecked")
	public List init(Search_ShuaKa downwell,Pagination pagin)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		  
		String type=downwell.getType(); 
		 
		String stime=downwell.getStime();
		
		String etime=downwell.getEtime(); 
		String ktime=downwell.getKtime();
		//String ktime=downwell.getKtime();	
		
		
		sb.append("select s.cardid,s.name,s.department,s.occupation as worktype,CONVERT(VARCHAR(20),w.downtime,20) downtime, CONVERT(VARCHAR(20),w.uptime + 0.000,20)uptime ,substring(rtrim(100 + datepart(dd, uptime - downtime) - 1), 2, 2) + '天' + substring(rtrim(100 + datepart(hh, uptime - downtime)), 2, 2) + '小时'+ substring(rtrim(100 + datepart(n, uptime - downtime)), 2, 2) + '分钟' as worktime,Ban_Name as banci from");
		sb.append("(select stafferid,name,cardid,department,occupation  from staffer where 1=1");
		if( type!=null && !type.equals("")){
			sb.append(" and worktype= ?  ");
			param.add(type);
		}
		sb.append(") s,");
		sb.append("(select *,convert(varchar(5),downtime,108) as starttime from workattendanceex where 1=1 ");
		if( stime!=null && !stime.equals("") && etime!=null && !etime.equals("")  && ktime!=null && !ktime.equals("")){
			sb.append(" and downtime between ? and ? and Convert(varchar(10),kqTime,120)=?");
			param.add(stime);
			param.add(etime);
			param.add(ktime);			
		} 
		sb.append(" and uptime is not null and downtime is not null) w,bantype");
		sb.append(" where s.stafferid=w.stafferid and ((start_stime < end_stime and starttime>=start_stime and starttime<end_stime) or (start_stime > end_stime and (starttime>=start_stime or starttime<end_stime)))");	
		sb.append(" order by Department");
		/*sb.append("UNION  all ");
		sb.append("select s.cardid,s.name,s.department,s.worktype,CONVERT(VARCHAR(20),w.downtime,20)downtime , CONVERT(VARCHAR(20),w.uptime,20)uptime,substring(rtrim(100 + datepart(dd, uptime - downtime) - 1), 2, 2) + '天' + substring(rtrim(100 + datepart(hh, uptime - downtime)), 2, 2) + '小时'+ substring(rtrim(100 + datepart(n, uptime - downtime)), 2, 2) + '分钟' as worktime,'中班' as banci from staffer s,workattendanceex w where s.cardid=w.cardid and w.uptime is not null and DateName (Hh,w.kqtime) >= 11 and DateName (Hh,w.kqtime) < 20 " );
		if( stime!=null && !stime.equals("")){
			sb.append(" and downtime between ? and ? ");
			param.add(stime);
			param.add(etime);
		}if( type!=null && !type.equals("")){
			sb.append(" and worktype= ?  ");
			param.add(type);
		}	
		sb.append("UNION  all ");
		sb.append("select s.cardid,s.name,s.department,s.worktype,CONVERT(VARCHAR(20),w.downtime,20)downtime , CONVERT(VARCHAR(20),w.uptime,20)uptime,substring(rtrim(100 + datepart(dd, uptime - downtime) - 1), 2, 2) + '天' + substring(rtrim(100 + datepart(hh, uptime - downtime)), 2, 2) + '小时'+ substring(rtrim(100 + datepart(n, uptime - downtime)), 2, 2) + '分钟' as worktime,'晚班' as banci from staffer s,workattendanceex w where s.cardid=w.cardid and w.uptime is not null and DateName (Hh,w.kqtime) >= 20");
		if( stime!=null && !stime.equals("")){
			sb.append(" and downtime between ? and ? ");
			param.add(stime);
			param.add(etime);
		}if( type!=null && !type.equals("")){
			sb.append(" and worktype= ?  ");
			param.add(type);
		}	
		sb.append("UNION  all ");
		sb.append("select s.cardid,s.name,s.department,s.worktype,CONVERT(VARCHAR(20),w.downtime,20)downtime , CONVERT(VARCHAR(20),w.uptime,20)uptime,substring(rtrim(100 + datepart(dd, uptime - downtime) - 1), 2, 2) + '天' + substring(rtrim(100 + datepart(hh, uptime - downtime)), 2, 2) + '小时'+ substring(rtrim(100 + datepart(n, uptime - downtime)), 2, 2) + '分钟' as worktime,'晚班' as banci from staffer s,workattendanceex w where s.cardid=w.cardid and w.uptime is not null and  DateName (Hh,w.kqtime) < 5 ");
		if( stime!=null && !stime.equals("")){
			sb.append(" and downtime between ? and ? ");
			param.add(stime);
			param.add(etime);
		}if( type!=null && !type.equals("")){
			sb.append(" and worktype= ?  ");
			param.add(type);
		}*/
		System.out.println("init："+sb.toString());
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
		String type=downwell.getType(); 
		String stime=downwell.getStime();  
		String etime=downwell.getEtime(); 
		
		
		
		sb.append("select s.cardid,s.name,s.department,s.worktype,CONVERT(VARCHAR(20),w.downtime,20)downtime , CONVERT(VARCHAR(20),w.uptime,20)uptime,substring(rtrim(100 + datepart(dd, uptime - downtime) - 1), 2, 2) + '天' + substring(rtrim(100 + datepart(hh, uptime - downtime)), 2, 2) + '小时'+ substring(rtrim(100 + datepart(n, uptime - downtime)), 2, 2) + '分钟' as worktime,'早班' as banci from staffer s,workattendanceex w where s.cardid=w.cardid and w.uptime is not null and DateName (Hh,w.kqtime) >= 5 and DateName (Hh,w.kqtime) < 11" ); 
		if( stime!=null && !stime.equals("")){
			sb.append(" and downtime between ? and ? ");
			param.add(stime);
			param.add(etime);
		}if( type!=null && !type.equals("")){
			sb.append(" and worktype= ?  ");
			param.add(type);
		}	
		sb.append("UNION  all ");
		sb.append("select s.cardid,s.name,s.department,s.worktype,CONVERT(VARCHAR(20),w.downtime,20)downtime , CONVERT(VARCHAR(20),w.uptime,20)uptime,substring(rtrim(100 + datepart(dd, uptime - downtime) - 1), 2, 2) + '天' + substring(rtrim(100 + datepart(hh, uptime - downtime)), 2, 2) + '小时'+ substring(rtrim(100 + datepart(n, uptime - downtime)), 2, 2) + '分钟' as worktime,'中班' as banci from staffer s,workattendanceex w where s.cardid=w.cardid and w.uptime is not null and DateName (Hh,w.kqtime) >= 11 and DateName (Hh,w.kqtime) < 20 " );
		if( stime!=null && !stime.equals("")){
			sb.append(" and downtime between ? and ? ");
			param.add(stime);
			param.add(etime);
		}if( type!=null && !type.equals("")){
			sb.append(" and worktype= ?  ");
			param.add(type);
		}	
		sb.append("UNION  all ");
		sb.append("select s.cardid,s.name,s.department,s.worktype,CONVERT(VARCHAR(20),w.downtime,20)downtime , CONVERT(VARCHAR(20),w.uptime,20)uptime,substring(rtrim(100 + datepart(dd, uptime - downtime) - 1), 2, 2) + '天' + substring(rtrim(100 + datepart(hh, uptime - downtime)), 2, 2) + '小时'+ substring(rtrim(100 + datepart(n, uptime - downtime)), 2, 2) + '分钟' as worktime,'晚班' as banci from staffer s,workattendanceex w where s.cardid=w.cardid and w.uptime is not null and DateName (Hh,w.kqtime) >= 20");
		if( stime!=null && !stime.equals("")){
			sb.append(" and downtime between ? and ? ");
			param.add(stime);
			param.add(etime);
		}if( type!=null && !type.equals("")){
			sb.append(" and worktype= ?  ");
			param.add(type);
		}	
		sb.append("UNION  all ");
		sb.append("select s.cardid,s.name,s.department,s.worktype,CONVERT(VARCHAR(20),w.downtime,20)downtime , CONVERT(VARCHAR(20),w.uptime,20)uptime,substring(rtrim(100 + datepart(dd, uptime - downtime) - 1), 2, 2) + '天' + substring(rtrim(100 + datepart(hh, uptime - downtime)), 2, 2) + '小时'+ substring(rtrim(100 + datepart(n, uptime - downtime)), 2, 2) + '分钟' as worktime,'晚班' as banci from staffer s,workattendanceex w where s.cardid=w.cardid and w.uptime is not null and  DateName (Hh,w.kqtime) < 5 ");
		if( stime!=null && !stime.equals("")){
			sb.append(" and downtime between ? and ? ");
			param.add(stime);
			param.add(etime);
		}if( type!=null && !type.equals("")){
			sb.append(" and worktype= ?  ");
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
	/**
	 * 根据用户cardid得到用户行走轨迹
	 * @param cardid
	 * @param name
	 * @param worktype
	 * @param downtime
	 * @param uptime
	 * @param banci
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getStafferByCardid(String cardid, String name, String worktype,
			String downtime, String uptime, String banci) {
		
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		
		sb.append("select vl.starttime,vl.endtime,cr.crname,cr.cardreaderid,cr.ground from v_locatedata vl,cardreader cr where  vl.cardreaderid = cr.cardreaderid " ); 
		if( cardid!=null && !cardid.equals("")){
			sb.append(" and vl.cardid= ?  ");
			param.add(cardid);
		}if( downtime!=null && !downtime.equals("") && uptime!=null && !uptime.equals("")){
			sb.append(" and starttime>= ? ");
			param.add(downtime);
		}if( downtime!=null && !downtime.equals("") && uptime!=null && !uptime.equals("")){
			sb.append(" and endtime<= ? ");
			param.add(uptime);
		}
		sb.append(" order by vl.starttime  ");
		try{

			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),ShowBackup.class);
			System.out.println("sb="+sb.toString());
			engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}finally{
			engine.closeEngine();
		}
		return relist;
	}
	

}
