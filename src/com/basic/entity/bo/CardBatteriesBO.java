package com.basic.entity.bo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.basic.entity.vo.CardBatteriesVO;
import com.basic.entity.vo.CardCallVO;
import com.kj133.entity.Search_Card_Batteries;

public class CardBatteriesBO {
	
	/**
	 * 定位卡报警
	 * */
	private final Logger log=Logger.getLogger(this.getClass());
	public CardBatteriesBO(){
	}
	
	
	/**定位卡电池报警
	 */
	@SuppressWarnings("unchecked")
	public List init(Search_Card_Batteries cbatteries,Pagination pagin)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		
		String dep=cbatteries.getDep();  
  		String sid=cbatteries.getSid();
        String minstime=cbatteries.getMinstime();//截取注册时间大
        String maxstime=cbatteries.getMaxstime();//截取注册时间大
	    String stime=cbatteries.getStime();//截取注册时间大
   		String etime=cbatteries.getEtime();    //截取注册时间小于
   		
   		sb.append(" select a.cardid as cardid,a.[name] as username,a.classgroup as gro,a.department as dep,'卡电池电压低' as info, ");
   		sb.append(" convert(varchar(19),MaxTime,20) as maxtime,convert(varchar(19),Mintime,20) as mintime from(select aa.*,staffer.[group] as classgroup,staffer.[name],staffer.department  ");
   		sb.append(" from ( select cardid,min(starttime) as mintime,max(endtime) as maxtime from v_locatedata where starttime< ?  and starttime> ? and endtime>= ? and state&0x02>0   ");
   		sb.append(" group by cardid) as aa left join staffer on staffer.cardid=aa.cardid )as a, recog, reportpopedom rp  where a.cardid=recog.cardid ");
   		sb.append(" and rp.department=a.department and rp.userid=? ");
   		

   		//获取前3天的时间
		String [] dateTime=stime.split(" ");
		String date=dateTime[0];
		String time=dateTime[1];
		
	   	 String[] dat = date.split("-"); //将要转换的日期字符串拆分成年月日
	     int year, month, day;
	     year = Integer.parseInt(dat[0]);
	     month = Integer.parseInt(dat[1]) - 1;
	     day = Integer.parseInt(dat[2]);
	     
	     String[]tim=time.split(":");
	     int h, m, s;
	     h=Integer.parseInt(tim[0]);
	     m=Integer.parseInt(tim[1]);
	     s=Integer.parseInt(tim[2]);
	     
	     GregorianCalendar d = new GregorianCalendar(year, month, day,h,m,s);
	     d.add(Calendar.DATE, -3);
	     Date dd = d.getTime();
	     DateFormat df = DateFormat.getDateInstance();
	     String adddate = df.format(dd);
	 	
	     param.add(etime);	
	     param.add(adddate);
   		 param.add(stime);
   		param.add(cbatteries.getUserid());

   		if( dep != null && !dep.equals("")){
   			sb.append(" and  a.department= ? ");
   			param.add(dep);
   		}
   		
   		
		  try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
		      relist=query.getResults(sb.toString(),param.toArray(),CardBatteriesVO.class,pagin);
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

	
	/**定位卡呼救报警
	 */
	@SuppressWarnings("unchecked")
	public List initCall(Search_Card_Batteries cbatteries,Pagination pagin)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		
		String dep=cbatteries.getDep();  
	    String stime=cbatteries.getStime();//截取注册时间大
   		String etime=cbatteries.getEtime();    //截取注册时间小于
   		
   	//获取前3天的时间
		String [] dateTime=stime.split(" ");
		String date=dateTime[0];
		String time=dateTime[1];
		
	   	 String[] dat = date.split("-"); //将要转换的日期字符串拆分成年月日
	     int year, month, day;
	     year = Integer.parseInt(dat[0]);
	     month = Integer.parseInt(dat[1]) - 1;
	     day = Integer.parseInt(dat[2]);
	     
	     String[]tim=time.split(":");
	     int h, m, s;
	     h=Integer.parseInt(tim[0]);
	     m=Integer.parseInt(tim[1]);
	     s=Integer.parseInt(tim[2]);
	     
	     GregorianCalendar d = new GregorianCalendar(year, month, day,h,m,s);
	     d.add(Calendar.DATE, -3);
	     Date dd = d.getTime();
	     DateFormat df = DateFormat.getDateInstance();
	     String adddate = df.format(dd);
   		
   		sb.append(" select * from (select  a.cardid as cardid,[Name] as username,[group] as gro,staffer.department as dep,'呼救' as info,convert(varchar(19),MaxTime,20) as maxtime,convert(varchar(19),Mintime,20) as mintime ");
		sb.append(" from( select cardid,min(starttime) as mintime,max(endtime) as maxtime from v_locatedata where starttime<? and starttime>? and endtime>=?  ");
		sb.append(" and state&0x08>0 group by cardid )as a,staffer, reportpopedom rp  where a.cardid=staffer.cardid  ");
		sb.append(" and rp.department=staffer.department and rp.userid=? ");
		
	 	
	     param.add(etime);	
	     param.add(adddate);
   		 param.add(stime);
   		param.add(cbatteries.getUserid());

   		if( dep != null && !dep.equals("")){
   			sb.append(" and  staffer.department= ? ");
   			param.add(dep);
   		}
  		
   		sb.append(" ) as QueryTable  ");
   		
		  try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
		      relist=query.getResults(sb.toString(),param.toArray(),CardCallVO.class,pagin);
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
	
	/**定位卡电池报警  导出Execl
	 */
	@SuppressWarnings("unchecked")
	public List initPrint(Search_Card_Batteries cbatteries)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		String dep=cbatteries.getDep();  
	    String stime=cbatteries.getStime();//截取注册时间大
   		String etime=cbatteries.getEtime();    //截取注册时间小于
   		
   		sb.append(" select a.cardid as cardid,a.[name] as username,a.classgroup as gro,a.department as dep,'卡电池电压低' as info, ");
   		sb.append(" convert(varchar(19),MaxTime,20) as maxtime,convert(varchar(19),Mintime,20) as mintime from(select aa.*,staffer.[group] as classgroup,staffer.[name],staffer.department  ");
   		sb.append(" from ( select cardid,min(starttime) as mintime,max(endtime) as maxtime from v_locatedata where starttime< ?  and starttime> ? and endtime>= ? and state&0x02>0   ");
   		sb.append(" group by cardid) as aa left join staffer on staffer.cardid=aa.cardid )as a, recog , reportpopedom rp where a.cardid=recog.cardid ");
   		sb.append(" and rp.department=a.department and rp.userid=? ");
   		
   		//获取前3天的时间
		String [] dateTime=stime.split(" ");
		String date=dateTime[0];
		String time=dateTime[1];
		
	   	 String[] dat = date.split("-"); //将要转换的日期字符串拆分成年月日
	     int year, month, day;
	     year = Integer.parseInt(dat[0]);
	     month = Integer.parseInt(dat[1]) - 1;
	     day = Integer.parseInt(dat[2]);
	     
	     String[]tim=time.split(":");
	     int h, m, s;
	     h=Integer.parseInt(tim[0]);
	     m=Integer.parseInt(tim[1]);
	     s=Integer.parseInt(tim[2]);
	     
	     GregorianCalendar d = new GregorianCalendar(year, month, day,h,m,s);
	     d.add(Calendar.DATE, -3);
	     Date dd = d.getTime();
	     DateFormat df = DateFormat.getDateInstance();
	     String adddate = df.format(dd);
	 	
	     param.add(etime);	
	     param.add(adddate);
   		 param.add(stime);
   		 param.add(cbatteries.getUserid());

   		if( dep != null && !dep.equals("")){
   			sb.append(" and  a.department= ? ");
   			param.add(dep);
   		}
  		
   		
		  try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
		      relist=query.getResults(sb.toString(),param.toArray(),CardBatteriesVO.class);
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
	
	/**定位卡呼救报警  导出Execl
	 */
	@SuppressWarnings("unchecked")
	public List initPrintCall(Search_Card_Batteries cbatteries)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		String dep=cbatteries.getDep();  
	    String stime=cbatteries.getStime();//截取注册时间大
   		String etime=cbatteries.getEtime();    //截取注册时间小于
   		

   		//获取前3天的时间
		String [] dateTime=stime.split(" ");
		String date=dateTime[0];
		String time=dateTime[1];
		
	   	 String[] dat = date.split("-"); //将要转换的日期字符串拆分成年月日
	     int year, month, day;
	     year = Integer.parseInt(dat[0]);
	     month = Integer.parseInt(dat[1]) - 1;
	     day = Integer.parseInt(dat[2]);
	     
	     String[]tim=time.split(":");
	     int h, m, s;
	     h=Integer.parseInt(tim[0]);
	     m=Integer.parseInt(tim[1]);
	     s=Integer.parseInt(tim[2]);
	     
	     GregorianCalendar d = new GregorianCalendar(year, month, day,h,m,s);
	     d.add(Calendar.DATE, -3);
	     Date dd = d.getTime();
	     DateFormat df = DateFormat.getDateInstance();
	     String adddate = df.format(dd);
	 	
	     sb.append(" select * from (select  a.cardid as cardid,[Name] as username,[group] as gro,staffer.department as dep,'呼救' as info,convert(varchar(19),MaxTime,20) as maxtime,convert(varchar(19),Mintime,20) as mintime ");
		 sb.append(" from( select cardid,min(starttime) as mintime,max(endtime) as maxtime from v_locatedata where starttime<? and starttime>? and endtime>=?  ");
		 sb.append(" and state&0x08>0 group by cardid )as a,staffer, reportpopedom rp  where a.cardid=staffer.cardid  ");
		 sb.append(" and rp.department=staffer.department and rp.userid=? ");
			
		 	
		 param.add(etime);	
		 param.add(adddate);
		 param.add(stime);
	   	 param.add(cbatteries.getUserid());

	   		if( dep != null && !dep.equals("")){
	   			sb.append(" and  staffer.department= ? ");
	   			param.add(dep);
	   		}
	  		
	   		sb.append(" ) as QueryTable  ");
		  try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
		      relist=query.getResults(sb.toString(),param.toArray(),CardCallVO.class);
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
