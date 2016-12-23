package com.kj133.entity.bo;

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

import com.kj133.entity.Search_Card_Batteries;
import com.kj133.entity.vo.Card_batteriesVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;
public class Card_batteriesBO {
	
	/**
	 * ����ر���
	 * */
	private final Logger log=Logger.getLogger(this.getClass());
	public Card_batteriesBO(){
	}
	
	@SuppressWarnings("unchecked")
	public List init(Search_Card_Batteries cbatteries,Pagination pagin)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		
		String gro=cbatteries.getGro();  
  		String sid=cbatteries.getSid();
        String minstime=cbatteries.getMinstime();//��ȡע��ʱ���
        String maxstime=cbatteries.getMaxstime();//��ȡע��ʱ���
	    String stime=cbatteries.getStime();//��ȡע��ʱ���
   		String etime=cbatteries.getEtime();    //��ȡע��ʱ��С��
   		
		sb.append("  select * from (select a.cardid as cardid,cardname as cardname,a.[name] as username,a.classgroup as gro,'����ص�ѹ��' as info, ");
		sb.append(" MaxTime as maxtime,Mintime as mintime from(select aa.*,staffer.[group] as classgroup,staffer.[name] from ( select cardid,  ");
		sb.append(" min(starttime) as mintime,max(endtime) as maxtime from v_locatedata where starttime< ?  and starttime> ? and endtime>= ? and state&0x02>0  ");
		sb.append(" group by cardid) as aa left join staffer on staffer.cardid=aa.cardid )as a, recog  where a.cardid=recog.cardid ");

   		//��ȡǰ3���ʱ��
	   	 String[] date = stime.split("-"); //��Ҫת���������ַ�����ֳ�������
	     int year, month, day;
	     year = Integer.parseInt(date[0]);
	     month = Integer.parseInt(date[1]) - 1;
	     day = Integer.parseInt(date[2]);
	     GregorianCalendar d = new GregorianCalendar(year, month, day);
	     d.add(Calendar.DATE, -3);
	     Date dd = d.getTime();
	     DateFormat df = DateFormat.getDateInstance();
	     String adddate = df.format(dd);
	 	
	     param.add(etime);	
	     param.add(adddate);
   		 param.add(stime);

   		if( sid!= null && !sid.equals("")){
   			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and a.cardid=? ");
				param.add(vo.getStafferid());
			}else{//û�п��Ż��û���
				sb.append(" and cardname=? ");
				param.add(sid);
			}
//   			sb.append("  and a.cardid= ?  ");
//   			param.add(sid);
   		}if( gro != null && !gro.equals("")){
   			sb.append(" and  a.classgroup= ? ");
   			param.add(gro);
   		}if( minstime != null && !minstime.equals("")){
   			sb.append(" and  convert(char(8),mintime,8)>= ? ");
   			param.add(minstime);
   		}if( maxstime != null && !maxstime.equals("")){
   			sb.append(" and  convert(char(8),maxtime,8)<? ");
   			param.add(maxstime);
   		}
   		sb.append("   ) as QueryTable   ");
   		
		  try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
		      relist=query.getResults(sb.toString(),param.toArray(),Card_batteriesVO.class,pagin);
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
	public List initPrint(Search_Card_Batteries cbatteries)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		String gro=cbatteries.getGro();  
  		String sid=cbatteries.getSid();
        String minstime=cbatteries.getMinstime();//��ȡע��ʱ���
        String maxstime=cbatteries.getMaxstime();//��ȡע��ʱ���
	    String stime=cbatteries.getStime();//��ȡע��ʱ���
   		String etime=cbatteries.getEtime();    //��ȡע��ʱ��С��
		sb.append("  select * from (select a.cardid as cardid,cardname as cardname,a.[name] as username,a.classgroup as gro,'����ص�ѹ��' as info, ");
		sb.append(" MaxTime as maxtime,Mintime as mintime from(select aa.*,staffer.[group] as classgroup,staffer.[name] from ( select cardid,  ");
		sb.append(" min(starttime) as mintime,max(endtime) as maxtime from v_locatedata where starttime< ?  and starttime> ? and endtime>= ? and state&0x02>0  ");
		sb.append(" group by cardid) as aa left join staffer on staffer.cardid=aa.cardid )as a, recog  where a.cardid=recog.cardid ");

   		//��ȡǰ3���ʱ��
	   	 String[] date = stime.split("-"); //��Ҫת���������ַ�����ֳ�������
	     int year, month, day;
	     year = Integer.parseInt(date[0]);
	     month = Integer.parseInt(date[1]) - 1;
	     day = Integer.parseInt(date[2]);
	     GregorianCalendar d = new GregorianCalendar(year, month, day);
	     d.add(Calendar.DATE, -3);
	     Date dd = d.getTime();
	     DateFormat df = DateFormat.getDateInstance();
	     String adddate = df.format(dd);
	 	
	     param.add(etime);	
	     param.add(adddate);
   		 param.add(stime);

   		
   		if( sid != null && !sid.equals("")){
   			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and a.cardid=? ");
				param.add(vo.getStafferid());
			}else{//û�п��Ż��û���
				sb.append(" and cardname=? ");
				param.add(sid);
			}
   		}if( gro != null && !gro.equals("")){
   			sb.append(" and  a.classgroup= ? ");
   			param.add(gro);
   		}if( minstime != null && !minstime.equals("")){
   			sb.append(" and  convert(char(8),mintime,8)>= ? ");
   			param.add(minstime);
   		}if( maxstime != null && !maxstime.equals("")){
   			sb.append(" and  convert(char(8),maxtime,8)<? ");
   			param.add(maxstime);
   		}
   		sb.append("   ) as QueryTable   ");
		try{
			
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),Card_batteriesVO.class);
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
