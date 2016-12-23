package com.kj133.entity.bo;
/*
 * ʱ��δ���ڳ���ͳ��
 * */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_unworkcar;

import com.kj133.entity.vo.UnworkcarVO;
import com.kj133.util.Global;

import java.math.BigDecimal;
import java.text.*;
public class UnworkcarBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public UnworkcarBO(){	
    
	}
	
	@SuppressWarnings("unchecked")
	public List init(Search_unworkcar unworkcar)throws Exception{
		List relist=null;
		List resultlist = new ArrayList();
		List param=new ArrayList();
		Engine engine=null;
		int reckon1=0;
		double ss = 0;

		StringBuffer sb=new StringBuffer();
		 Global go = new Global();

		 
		String stime=unworkcar.getStime();  
		String s1=stime.substring(0,stime.length());


     
		String etime=unworkcar.getEtime(); 
		String e1=etime.substring(0,etime.length());


		sb.append("select aa.name carname,aa.worktype cartype,isnull(bb.�ܳ���,0) carcount,count(*) reckon from ( ");
		sb.append(" select distinct Cardid,stafferid,[name],[group],worktype from staffer where [group]='����' and convert(varchar,Cardid) not in (");
		sb.append(" select distinct convert(varchar,isnull(Cardid,0)) from workattendanceex where "); 
		sb.append(" ((downtime<=? and (uptime>=? or uptime is null)) or (downtime>=? and downtime<=?) or (uptime<=? and uptime>=?)))");
		param.add(s1);
        param.add(e1);
        param.add(s1);
        param.add(e1);
        param.add(e1);
        param.add(s1);
		sb.append(" )aa left join  ");
		sb.append(" (select worktype,count(*) �ܳ��� from staffer where [group] like '����%' group by worktype) bb ");
		sb.append(" on aa.worktype=bb.worktype  group by aa.worktype,aa.name,bb.�ܳ��� order by aa.worktype ");


		try{

			engine=EngineFactory.getEngine("test");
			
//			engine.executeProcedureCall("{ call sel_unworkcar(?,?) }",new Object[]{s1,e1});
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),UnworkcarVO.class);
		


		
			if(relist != null&&relist.size()>0){
				
				int aaa=1;
				for(int i =0;i<relist.size();i++){
					List relist1 = relist;
					UnworkcarVO vo = (UnworkcarVO) relist.get(i);
					String carname = vo.getCarname();
					String cartype = vo.getCartype();
					int reckon = 1;
					for(int j = i+1; j<relist1.size(); j++){
						UnworkcarVO vo2 = (UnworkcarVO) relist1.get(j);
//						if(vo.getCartype().equals(vo2.getCartype())){
//						if(carname.equalsIgnoreCase(vo2.getCartype())){
						if(cartype.equalsIgnoreCase(vo2.getCartype())){
							carname+=","+vo2.getCarname();
							relist.remove(j);
							j--;

							++reckon;
						}
					}
					reckon1 = reckon1 + reckon;
					vo.setCount((aaa++)+"");
//					vo.setCount((++i)+"");
//					vo.setUnworkodds(reckon*100/vo.getCarcount());
//					vo.setUnworkodds(reckon*100/vo.getCarcount());
					int carc = Integer.valueOf(vo.getCarcount()).intValue();
					DecimalFormat myformat = null;
					myformat= (DecimalFormat)NumberFormat.getPercentInstance();
					myformat.applyPattern("0.00%"); //0��ʾ�ӵ�С����,00��ʾ��λС���㣬����00��һ�����֪��Ч��

					double rat = (double)reckon/(double)carc;
					String aa = ""+myformat.format(rat);
//					vo.setUnworkodds(go.RoundFracToString(reckon*100/carc+ss, 0)+"%");
					vo.setUnworkodds(aa);


					vo.setReckon(reckon);
			        vo.setCarname(carname);
			        resultlist.add(vo);  
				}
				
			}

			UnworkcarVO vo = new  UnworkcarVO();
			vo.setCount("�ϼ�");
			vo.setReckon(reckon1);
			resultlist.add(vo);
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
	public  List getAllWorkTime(Search_unworkcar unworkcar)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		
		String stime=unworkcar.getStime();  
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length());  
     
		String etime=unworkcar.getEtime(); 
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
			relist=query.getResults(sb.toString(),param.toArray(),UnworkcarVO.class);
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;
		}finally{
			engine.closeEngine();
		}
		return relist;
	}
/*	public List init(Search_unworkcar unworkcar,Pagination pagin)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		
		String sid=downwell.getSid(); 
		String gro=downwell.getGro();   
		String type=downwell.getType(); 
		String banci=downwell.getBanci();
		
		String stime=downwell.getStime();  
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length());  
		
		String etime=downwell.getEtime(); 
		String e1=etime.substring(0,10);  
		String e2=etime.substring(11,etime.length());
		
		sb.append(" select  b.ban_name as  banci,a.*from ( ");
		sb.append("  select kqtime,uptime,downtime,temp1.cardid as cardid,isnull(temp1.stafferID,'δ��') stafferid,temp1.downtime as intime,temp1.uptime as  ");
		sb.append(" upwelltime,isnull([Name],'δ��') username,isnull([group],'δ��') gro,isnull(worktype,'����') worktype, ");
		sb.append(" substring(rtrim(100+datepart(dd,uptime-downtime)-1),2,2)+'��'+substring(rtrim(100+datepart(hh,uptime-downtime)),2,2)+'Сʱ'+ ");
		sb.append(" substring(rtrim(100+datepart(n,uptime-downtime)),2,2)+'����' as worktime,substring(convert(char,temp1.downtime,120),1,10) as downdate, ");
		sb.append(" temp1.�뾮��վ as incardreader,x.ShortName as incardreadername,temp1.�뾮��λ�� as inlocator ,m.ShortName as inlocatorname,temp1.������վ as upcardreader,y.ShortName as upcardreadername,temp1. ");
		sb.append(" ������λ�� as uplocator,n.ShortName as uplocatorname from(select downtime,stafferid,cardid,uptime,downcardreaderid as �뾮��վ,downlocatorid �뾮��λ��, ");
		sb.append(" upcardreaderid ������վ,uplocatorid ������λ��,convert(char,kqtime,111) as kqtime from WorkattendanceEx where  ");
		sb.append(" downtime>=? and (uptime>=? or uptime is null) and  downtime<convert(datetime,?)+1 and  ");
		sb.append(" (uptime<convert(datetime,?)+3 or uptime is null)) as temp1 left join staffer on temp1.stafferID=staffer.stafferID left ");
		sb.append("  join locator as m on temp1.�뾮��λ��=m.locatorID left join locator as n on temp1.������λ��=n.locatorID left join cardreader x  ");
		sb.append(" on temp1.�뾮��վ=x.cardreaderid left join cardreader y on temp1.������վ=y.cardreaderid  ");
		sb.append(" ) a left join bantype b on a.downtime<cast(kqtime+' '+b.end_stime as datetime) and ");
		sb.append("  a.downtime>=dateadd(day,-cast(dateoffset as int),cast(kqtime+' '+b.start_stime as datetime)) ");		
		sb.append("  where  1=1  ");
				select * from (
	    select kqtime,uptime,downtime,temp1.cardid as ����,isnull(temp1.stafferID,'δ��') Ա�����,temp1.downtime as �뾮ʱ��,temp1.uptime as ����ʱ��,isnull([Name],'δ��') ����,isnull([group],'δ��') ����,isnull(worktype,'����') ����,substring(rtrim(100+datediff(mi,downtime,uptime)/60),2,2)+'Сʱ'+substring(rtrim(100+datediff(mi,downtime,uptime)%60),2,2)+'��' ����ʱ��,substring(convert(char,temp1.downtime,120),1,10) as downdate,temp1.�뾮��վ,x.ShortName as �뾮��վ����,temp1.�뾮��λ��,m.ShortName as �뾮��λ������,temp1.������վ,y.ShortName as ������վ����,temp1.������λ��,n.ShortName as ������λ������ from(select downtime,stafferid,cardid,uptime,downcardreaderid as �뾮��վ,downlocatorid �뾮��λ��,upcardreaderid ������վ,uplocatorid ������λ��,convert(char,kqtime,111) as kqtime from WorkattendanceEx where  downtime>='2008-6-2' and (uptime>='2008-6-2' or uptime is null) and  downtime<convert(datetime,'2008-6-3')+1 and (uptime<convert(datetime,'2008-6-3')+3 or uptime is null)) as temp1 left join staffer on temp1.stafferID=staffer.stafferID left join locator as m on temp1.�뾮��λ��=m.locatorID left join locator as n on temp1.������λ��=n.locatorID left join cardreader x on temp1.�뾮��վ=x.cardreaderid left join cardreader y on temp1.������վ=y.cardreaderid 
				) as QueryTable		
		
		param.add(s1);
		param.add(s1);
		param.add(e1);
		param.add(e1);
		
		if( sid!=null && !sid.equals("")){
			sb.append(" and rtrim(cardid) = ? ");
			param.add(sid);
		}if( gro!=null && !gro.equals("")){
			sb.append(" and gro = ? ");
			param.add(gro);
		}if( type!=null && !type.equals("")){
			sb.append(" and  worktype = ? ");
			param.add(type);
		} if( banci!=null && !banci.equals("")){
			sb.append(" and b.ban_name= ? ");
			param.add(banci);
		}
		try{
			sb.append(" and  convert(char(8),intime,8)>= ? and  convert(char(8),intime,8)<= ? ");
			param.add(s2);
			param.add(e2);
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
	
	*//**
	 * ���ڱ�������ͺϼ���Ϣ
	 * *//*
	@SuppressWarnings("unchecked")
	public  List getAllWorkTime(Search_ShuaKa downwell)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		
		String stime=downwell.getStime();  
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length());  
		
		String etime=downwell.getEtime(); 
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
			relist=query.getResults(sb.toString(),param.toArray(),DownWellVO.class);
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;
		}finally{
			engine.closeEngine();
		}
		return relist;
	}
*/
}