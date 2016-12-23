package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_OverTime;
import com.kj133.entity.vo.OverTimeVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;
public class OverTimeBO {
  
	private static final Logger log=Logger.getLogger(OverTimeBO.class);
	public OverTimeBO(){
		
	}
	/**
	 * ��ȡ��ѯ�¾���ʱ�Ľ����
	 * */
	@SuppressWarnings("unchecked")
	public List getList(Search_OverTime over,Pagination pagin)throws Exception {
		List relist=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		
		String stime=over.getStime(); 
		String etime=over.getEtime();
		String minstime=over.getMinstime();
		String maxstime=over.getMaxstime();
		String minetime=over.getMinetime();
		String maxetime=over.getMaxetime();
		String cid=over.getSid();
		String gro=over.getGro();
		
		
		sb.append(" select * from (select * from ( select temp1.cardid as cardid,isnull([Name],'δ��') username,");
		sb.append(" isnull([group],'δ��') gro,isnull(worktype,'����') worktype, ");
		sb.append(" case when (temp1.uptime>=dateadd(mi,1080,temp1.downtime)) then   '���س�ʱ'  else '�뾮��ʱ' end as info,convert(varchar(19),temp1.downtime,20) as downtime,");
		sb.append(" convert(varchar(19),temp1.uptime,20) as uptime,");
		sb.append(" substring(rtrim(100+(datediff(mi,downtime,uptime))/60),2,2)+'Сʱ'+substring(rtrim(100+(datediff(mi,downtime,uptime))%60),2,2)+'��' as staytime,  ");
        sb.append(" substring(rtrim(100+(datediff(mi,dateadd(mi,720,temp1.downtime),uptime))/60),2,2)+'Сʱ'+substring(rtrim(100+(datediff(mi,dateadd(mi,720,temp1.downtime),uptime))%60),2,2)+'��' as overtime ");
        sb.append(" from(select downtime,stafferid,cardid,uptime from WorkattendanceEX  ");
        sb.append(" where downtime>= ? and uptime>=?  and downtime< ? and uptime<convert ");
        sb.append(" (datetime, ? )+3) as temp1 left join staffer on temp1.stafferID=staffer.stafferID where ");
        sb.append(" temp1.uptime>=dateadd(mi,720,temp1.downtime)) as QueryTable   where  1=1 ");
//		sb.append(" convert(varchar(19),temp1.uptime,20) as uptime,substring(rtrim(100+datepart(dd,uptime-downtime)-1),2,2)+'��'+");
//		sb.append(" substring(rtrim(100+datepart(hh,uptime-downtime)),2,2)+'Сʱ'+ ");
//		sb.append(" substring(rtrim(100+datepart(n,uptime-downtime)),2,2)+'����' as staytime,  ");
//		sb.append(" substring(rtrim(100+datepart(dd,uptime-dateadd(mi,720,temp1.downtime))-1),2,2)+'��'+  ");
//		sb.append(" substring(rtrim(100+datepart(hh,uptime-dateadd(mi,720,temp1.downtime))),2,2)+'Сʱ'+ ");
//        sb.append(" substring(rtrim(100+datepart(n,uptime-dateadd(mi,720,temp1.downtime))),2,2)+'����' as overtime ");
//        sb.append(" from(select downtime,stafferid,cardid,uptime from WorkattendanceEX  ");
//        sb.append(" where downtime>= ? and uptime>=?  and downtime< ? and uptime<convert ");
//        sb.append(" (datetime, ? )+3) as temp1 left join staffer on temp1.stafferID=staffer.stafferID where ");
//        sb.append(" temp1.uptime>=dateadd(mi,720,temp1.downtime)) as QueryTable   where  1=1 ");
		param.add(stime);
		param.add(stime);
		param.add(etime);
		param.add(etime);
		
		
		if( cid != null && !cid.equals("")){
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(cid);
			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and cardid=? ");
				param.add(vo.getStafferid());
			}else{//û�п��Ż��û���
				sb.append(" and username=? ");
				param.add(cid);
			}
//			sb.append("  and  cardid = ? ");
//			param.add(cid);
		}if( gro != null && !gro.equals("")){
			sb.append(" and  gro = ? ");
			param.add(gro);
		}if( minstime != null && !minstime.equals("")){
			sb.append(" and convert(char(8),downtime,8)>=? ");
			param.add(minstime);
		}if( maxstime != null && !maxstime.equals("")){
			sb.append(" and convert(char(8),downtime,8)<? ");
			param.add(maxstime);
		}if( minetime != null && !minetime.equals("")){
			sb.append(" and convert(char(8),uptime,8)>=? ");
			param.add(minetime);
		}if( maxetime != null && !maxetime.equals("")){
			sb.append(" and convert(char(8),uptime,8)<? ");
			param.add(maxetime);
		}
		sb.append(" ) as QueryTable ");
		try{
			/*sb.append(" and convert(char(8),downtime,8)>=? and  convert(char(8),downtime,8)<? ) as QueryTable   ");
			param.add(s2);
			param.add(e2);*/
			engine=EngineFactory.getEngine("test");
		    Query query=engine.getQuery();
		    relist=query.getResults(sb.toString(),param.toArray(),OverTimeVO.class,pagin);
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
	public List getListPrint(Search_OverTime over)throws Exception {
		List relist=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		
		String stime=over.getStime(); 
		String etime=over.getEtime();
		String minstime=over.getMinstime();
		String maxstime=over.getMaxstime();
		String minetime=over.getMinetime();
		String maxetime=over.getMaxetime();
		String cid=over.getSid();
		String gro=over.getGro();
		
		sb.append(" select * from (select * from ( select temp1.cardid as cardid,isnull([Name],'δ��') username,");
		sb.append(" isnull([group],'δ��') gro,isnull(worktype,'����') worktype, ");
		sb.append(" case when (temp1.uptime>=dateadd(mi,1080,temp1.downtime)) then   '���س�ʱ'  else '�뾮��ʱ' end as info,convert(varchar(19),temp1.downtime,20) as downtime,");
		sb.append(" convert(varchar(19),temp1.uptime,20) as uptime,");
		sb.append(" substring(rtrim(100+(datediff(mi,downtime,uptime))/60),2,2)+'Сʱ'+substring(rtrim(100+(datediff(mi,downtime,uptime))%60),2,2)+'��' as staytime,  ");
        sb.append(" substring(rtrim(100+(datediff(mi,dateadd(mi,720,temp1.downtime),uptime))/60),2,2)+'Сʱ'+substring(rtrim(100+(datediff(mi,dateadd(mi,720,temp1.downtime),uptime))%60),2,2)+'��' as overtime ");
        sb.append(" from(select downtime,stafferid,cardid,uptime from WorkattendanceEX  ");
        sb.append(" where downtime>= ? and uptime>=?  and downtime< ? and uptime<convert ");
        sb.append(" (datetime, ? )+3) as temp1 left join staffer on temp1.stafferID=staffer.stafferID where ");
        sb.append(" temp1.uptime>=dateadd(mi,720,temp1.downtime)) as QueryTable   where  1=1 ");
		param.add(stime);
		param.add(stime);
		param.add(etime);
		param.add(etime);
		
		
		if( cid != null && !cid.equals("")){
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(cid);
			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and cardid=? ");
				param.add(vo.getStafferid());
			}else{//û�п��Ż��û���
				sb.append(" and username=? ");
				param.add(cid);
			}
		}if( gro != null && !gro.equals("")){
			sb.append(" and  gro = ? ");
			param.add(gro);
		}if( minstime != null && !minstime.equals("")){
			sb.append(" and convert(char(8),downtime,8)>=? ");
			param.add(minstime);
		}if( maxstime != null && !maxstime.equals("")){
			sb.append(" and convert(char(8),downtime,8)<? ");
			param.add(maxstime);
		}if( minetime != null && !minetime.equals("")){
			sb.append(" and convert(char(8),uptime,8)>=? ");
			param.add(minetime);
		}if( maxetime != null && !maxetime.equals("")){
			sb.append(" and convert(char(8),uptime,8)<? ");
			param.add(maxetime);
		}
		sb.append(" ) as QueryTable ");

		try{
			
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),OverTimeVO.class);
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
