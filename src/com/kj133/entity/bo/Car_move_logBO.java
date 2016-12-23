package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_car_move_log;
import com.kj133.entity.vo.Car_move_logVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;

public class Car_move_logBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public Car_move_logBO(){	
    
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
		String maxstime=car_move_log.getMaxstime(); 
		String minstime=car_move_log.getMinstime(); 
		/*		String gro=car_move_log.getGro();   
		String banci=car_move_log.getBanci();
		 */
		String stime=car_move_log.getStime();  
//		String s1=stime.substring(0,10);  
//		String s2=stime.substring(11,stime.length());  
     
		String etime=car_move_log.getEtime(); 
//		String e1=etime.substring(0,10);  
//		String e2=etime.substring(11,etime.length());
		sb.append(" select t.���� carname,t.worktime wtime,t.���� cardid,t.���� cartype,convert(varchar(19),t.�뾮ʱ��,20) intime,convert(varchar(19),t.����ʱ��,20) upwelltime,t.����ʱ�� worktime from (");
		sb.append(" select *,datediff(mi,downtime,uptime) worktime from  ");
		sb.append(" ( select kqtime ����ʱ��,uptime,downtime,temp1.cardid as ����,isnull(temp1.stafferID,'δ��') Ա�����,temp1.downtime as �뾮ʱ��, ");
		sb.append(" temp1.uptime as ����ʱ��,isnull([Name],'δ��') ����,isnull([group],'δ��') ����,isnull(worktype,'����') ����, ");
		sb.append(" substring(rtrim(100+datediff(mi,downtime,uptime)/60),2,2)+'Сʱ'+substring(rtrim(100+datediff(mi,downtime,uptime)%60),2,2)+'��' as ����ʱ��, ");
		sb.append(" substring(convert(char,temp1.downtime,120),1,10) as downdate from(select downtime,stafferid,cardid,uptime,convert(char,kqtime,111) as kqtime from  ");
		sb.append(" (select aa.* from workattendanceex aa,recog bb where aa.cardid=bb.cardid and bb.CardMode='����') workattendanceex where ");
		sb.append("  kqtime>=? and  kqtime<convert(datetime,?)+1 and  kqtime<convert(datetime,?)+1 ) as temp1 left join staffer on temp1.stafferID=staffer.stafferID ) a where 1=1 ");
		param.add(stime);
		param.add(etime);
		param.add(etime);
		
//		sb.append(" (����ʱ��>=�뾮ʱ�� or ����ʱ�� is null) and  rtrim(Ա�����) =? and  ����=? and ");
		
		if( cardid!=null && !cardid.equals("")){
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(cardid);
			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and rtrim(Ա�����)=? ");
				param.add(vo.getStafferid());
			}else{//û�п��Ż��û���
				sb.append(" and rtrim(����)=? ");
				param.add(cardid);
			}
//			sb.append(" and rtrim(Ա�����) = ? ");
//			param.add(cardid);
			
		}if( cartype!=null && !cartype.equals("")){
			sb.append(" and  ���� = ? ");
			param.add(cartype);
			
		}if( minstime!=null && !minstime.equals("")){
			sb.append(" and (����ʱ��>=�뾮ʱ�� or ����ʱ�� is null) and  datediff(second,�뾮ʱ��,����ʱ��)>=datediff(second,?,?)   ");
			param.add("00:00:00");
			param.add(minstime);
			
		}if( maxstime!=null && !maxstime.equals("")){
			sb.append(" and datediff(second,�뾮ʱ��,����ʱ��)<datediff(second,?,?) ");
			param.add("00:00:00");
			param.add(maxstime);
			
		}
		sb.append(" ) t order by t.����,t.�뾮ʱ��");

		
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),Car_move_logVO.class);
			int wts = 0;
			if(relist != null && relist.size()>0){
				
				for(int i =0;i<relist.size();i++){
					Car_move_logVO vo = (Car_move_logVO) relist
					.get(i);
			        vo.setCount(String.valueOf(i + 1));//���
			        
			        int	wt = Integer.valueOf(vo.getWtime()== null ? 0 : Integer.parseInt(vo.getWtime()));
			        wts += wt;
			        
			        String Str = "";
					int wtH = wts / 60;
					int wtM = wts % 60;
					int ctime = wts / relist.size();
					int ctH = ctime /60;
					int ctM = ctime % 60;
					String ctStr = "";
					ctStr = ctH + "Сʱ" + ctM + "��";
					Str = wtH + "Сʱ" + wtM + "��";
			        if(i==(relist.size()-1))
			        {
			        	vo.setAcount(relist.size());
			        	vo.setAtime(Str);
			        	vo.setCounttime(ctStr);
			        }
			        resultlist.add(vo);  
				}
				
//				Car_move_logVO vo = new Car_move_logVO();
				
				
//				
//				resultlist.add(vo);
				
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
