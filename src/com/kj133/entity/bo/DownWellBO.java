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
		
		
		sb.append(" select kqtime,���� cardid,Ա����� stafferid,���� username,���� gro,���� worktype,downtime intime,uptime upwelltime,����ʱ�� worktime,�뾮��վ incardreader,�뾮��վ���� incardreadername,�뾮��λ�� upcardreader,�뾮��λ������ upcardreadername,������վ inlocator,������վ���� inlocatorname,������λ�� uplocator,������λ������ uplocatorname,���� department from ( ");
		sb.append(" select convert(float,uptime-downtime) days,kqtime,convert(varchar(19),uptime,20) uptime,convert(varchar(19),downtime,20) downtime,temp1.cardid as ����,isnull(temp1.stafferID,'δ��') Ա�����,convert(datetime,temp1.downtime,20) as �뾮ʱ��,convert(datetime,temp1.uptime,20) as ����ʱ��,isnull([Name],'δ��') ����,department ����,isnull([group],'δ��') ����,isnull(worktype,'����') ����,substring(rtrim(100+datediff(mi,downtime,uptime)/60),2,2)+'Сʱ'+substring(rtrim(100+datediff(mi,downtime,uptime)%60),2,2)+'��' ����ʱ��,substring(convert(char,temp1.downtime,120),1,10) as downdate,temp1.�뾮��վ,x.ShortName as �뾮��վ����,temp1.�뾮��λ��,m.ShortName as �뾮��λ������,temp1.������վ,y.ShortName as ������վ����,temp1.������λ��,n.ShortName as ������λ������ from(select downtime,stafferid,cardid,uptime,downcardreaderid as �뾮��վ,downlocatorid �뾮��λ��,upcardreaderid ������վ,uplocatorid ������λ��,convert(char,kqtime,111) as kqtime from WorkattendanceEx where 1=1  "); 
		
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
		sb.append(" ) as temp1 left join staffer on temp1.stafferID=staffer.stafferID left join locator as m on temp1.�뾮��λ��=m.locatorID left join locator as n on temp1.������λ��=n.locatorID left join cardreader x on temp1.�뾮��վ=x.cardreaderid left join cardreader y on temp1.������վ=y.cardreaderid "); 
		
		sb.append(" ) as QueryTable ,reportpopedom rp  ");
		sb.append(" where ����=rp.department and  userid='"+userid+"' and 1=1   "); 
		
		
		if( dstime!=null && !dstime.equals("")){
			sb.append(" and convert(char(8),�뾮ʱ��,8)>=?  ");
			param.add(dstime);
		}if( detime!=null && !detime.equals("")){
			sb.append("  and  convert(char(8),�뾮ʱ��,8)<=?  ");
			param.add(detime);
		}if( ustime!=null && !ustime.equals("")){
			sb.append("  and  convert(char(8),����ʱ��,8)>=?  ");
			param.add(ustime);
		}if( uetime!=null && !uetime.equals("")){
			sb.append(" and convert(char(8),����ʱ��,8)<=? ");
			param.add(uetime);
		}if( mintime!=null && !mintime.equals("")){
			sb.append("  and  datediff(second,�뾮ʱ��,����ʱ��)>=datediff(second,?,?)  ");
			param.add("00:00:00");
			param.add(mintime);
		}if( maxtime!=null && !maxtime.equals("")){
			sb.append("  and  datediff(second,�뾮ʱ��,����ʱ��)<datediff(second,?,?)  ");
			param.add("00:00:00");
			param.add(maxtime);
		}if( sid!=null && !sid.equals("")){
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and rtrim(Ա�����)=? ");
				param.add(vo.getStafferid());
			}else{//û�п��Ż��û���
				sb.append(" and ����=? ");
				param.add(sid);
			}
//			sb.append(" and rtrim(cardid) = ? ");
//			param.add(sid);
		}if( gro!=null && !gro.equals("")){
			sb.append(" and ���� = ? ");
			param.add(gro);
		}if( type!=null && !type.equals("")){
			sb.append(" and  ���� = ? ");
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
		
		
		sb.append(" select kqtime,���� cardid,Ա����� stafferid,���� username,���� gro,���� worktype,downtime intime,uptime upwelltime,����ʱ�� worktime,�뾮��վ incardreader,�뾮��վ���� incardreadername,�뾮��λ�� upcardreader,�뾮��λ������ upcardreadername,������վ inlocator,������վ���� inlocatorname,������λ�� uplocator,������λ������ uplocatorname,���� department from ( ");
		sb.append(" select convert(float,uptime-downtime) days,kqtime,convert(varchar(19),uptime,20) uptime,convert(varchar(19),downtime,20) downtime,temp1.cardid as ����,isnull(temp1.stafferID,'δ��') Ա�����,convert(datetime,temp1.downtime,20) as �뾮ʱ��,convert(datetime,temp1.uptime,20) as ����ʱ��,isnull([Name],'δ��') ����,department ����,isnull([group],'δ��') ����,isnull(worktype,'����') ����,substring(rtrim(100+datediff(mi,downtime,uptime)/60),2,2)+'Сʱ'+substring(rtrim(100+datediff(mi,downtime,uptime)%60),2,2)+'��' ����ʱ��,substring(convert(char,temp1.downtime,120),1,10) as downdate,temp1.�뾮��վ,x.ShortName as �뾮��վ����,temp1.�뾮��λ��,m.ShortName as �뾮��λ������,temp1.������վ,y.ShortName as ������վ����,temp1.������λ��,n.ShortName as ������λ������ from(select downtime,stafferid,cardid,uptime,downcardreaderid as �뾮��վ,downlocatorid �뾮��λ��,upcardreaderid ������վ,uplocatorid ������λ��,convert(char,kqtime,111) as kqtime from WorkattendanceEx where 1=1  "); 
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
		sb.append(" ) as temp1 left join staffer on temp1.stafferID=staffer.stafferID left join locator as m on temp1.�뾮��λ��=m.locatorID left join locator as n on temp1.������λ��=n.locatorID left join cardreader x on temp1.�뾮��վ=x.cardreaderid left join cardreader y on temp1.������վ=y.cardreaderid "); 
		
		sb.append(" ) as QueryTable ,reportpopedom rp  ");
		sb.append(" where ����=rp.department and userid='"+userid+"' and 1=1   "); 
		
		if( dstime!=null && !dstime.equals("")){
			sb.append(" and convert(char(8),�뾮ʱ��,8)>=?  ");
			param.add(dstime);
		}if( detime!=null && !detime.equals("")){
			sb.append("  and  convert(char(8),�뾮ʱ��,8)<=?  ");
			param.add(detime);
		}if( ustime!=null && !ustime.equals("")){
			sb.append("  and  convert(char(8),����ʱ��,8)>=?  ");
			param.add(ustime);
		}if( uetime!=null && !uetime.equals("")){
			sb.append("  and convert(char(8),����ʱ��,8)<=?  ");
			param.add(uetime);
		}if( mintime!=null && !mintime.equals("")){
			sb.append("  and  datediff(second,�뾮ʱ��,����ʱ��)>=datediff(second,?,?)  ");
			param.add("00:00:00");
			param.add(mintime);
		}if( maxtime!=null && !maxtime.equals("")){
			sb.append("  and  datediff(second,�뾮ʱ��,����ʱ��)<datediff(second,?,?)  ");
			param.add("00:00:00");
			param.add(maxtime);
		}if( sid!=null && !sid.equals("")){
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and rtrim(����)=? ");
				param.add(vo.getStafferid());
			}else{//û�п��Ż��û���
				sb.append(" and ����=? ");
				param.add(sid);
			}
//			sb.append(" and rtrim(cardid) = ? ");
//			param.add(sid);
		}if( gro!=null && !gro.equals("")){
			sb.append(" and ���� = ? ");
			param.add(gro);
		}if( type!=null && !type.equals("")){
			sb.append(" and  ���� = ? ");
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
