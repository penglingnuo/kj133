package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.Search_emphases_report;

import com.kj133.entity.vo.Emphases_reportVO;
import com.kj133.entity.vo.SuggestEmployeeVO;

import com.kj133.util.Global;

public class Emphases_reportBO {
	/**
	 * �ص㿼��������ϸ��
	 * */
	private Logger log = Logger.getLogger(Emphases_reportBO.class);
	

	public Emphases_reportBO() {

	}
	private String flag = "temp" ;
	@SuppressWarnings("unchecked")
	public List getList(Search_emphases_report check, String userId)
			throws Exception {
		String sTime = "";
		String eTime = "";
		List relist = null;
		List resultlist = new ArrayList();
		List param = new ArrayList();
		Engine engine = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select t.name,t.[group],t.type worktype,t.stafferid,t.downsum downwellcount,t.worktime as alltime,t.zao zaoban,t.zhong zhongban,t.wan wanban,t.cardid,round(t.pav,0) counttime from (");
		sb.append("select *,1.0*worktime/(zao+zhong+wan+other) pav from ( ");
		sb.append("select a.*,b.cardMode,b.cardid,occupation,iftemp,ifother ");
		sb.append(",case when b.cardMode='����' then b.cardMode  ");
		sb.append("when a.[group]='���쵼' then a.[group] ");
		sb.append("when occupation='������Ա' then occupation ");
		sb.append("when (occupation='����Ա') or (occupation='�߼�Ա') then occupation ");
		sb.append("when occupation='�����쵼' then occupation ");
		sb.append("when occupation='����˾��' then occupation ");
		sb.append("when iftemp='��ʽ��' then iftemp ");
		sb.append("when iftemp='����' then iftemp ");
		sb.append("when ifother='��ί��' then ifother ");
		sb.append("else '����' end as type ");
		sb.append(" from ( ");
		sb.append("select name,shunxu,[group],worktype,stafferid,sum(downsum) downsum,sum(worktime) worktime,sum(zao) zao,sum(zhong) zhong,");
		sb.append("sum(wan) wan ,sum(other) other");
		sb.append(" from ( ");
		sb.append("select b.[name],b.shunxu,b.[group],b.worktype,a.stafferid,a.downsum,a.worktime,a.kqtime");
		sb.append(",case when convert(varchar(2),a.Ban_name)='��' then 1 else 0 end zao ");
		sb.append(",case when convert(varchar(2),a.Ban_name)='��' then 1 else 0 end zhong ");
		sb.append(",case when convert(varchar(2),a.Ban_name)='��' then 1 else 0 end wan ");
		sb.append(",case when (convert(varchar(2),a.Ban_name)<>'��' and convert(varchar(2),a.Ban_name)<>'��' and convert(varchar(2),a.Ban_name)<>'��') then 1 else 0 end other ");
		sb.append("from finattendance a,staffer b ");
		sb.append("where  a.stafferid=b.stafferid and a.userid=? ");
		param.add(userId);
		String chooseTime = check.getIsChoose();
		
		Global global = new Global();
		
		if(chooseTime!=null&&!chooseTime.equals("")){
			if(chooseTime.length()<8){
				int days = global.getDaysOfMonth(chooseTime);
				flag = "month";
				
				String tempStrB = chooseTime +"-01";
				String tempStrE = chooseTime + "-" +String.valueOf(days);
				
				sTime = global.getDay(tempStrB, -1);
				eTime = tempStrE;
				sb.append("and a.kqtime>convert(datetime,'"+sTime+"')");
				//param.add(tempStrB);
				sb.append("and a.kqtime<convert(datetime,'"+eTime+"')");
				//param.add(tempStrE);
			}else{
				flag = "day";
				sTime = global.getDay(check.getIsChoose(), -1);
			
				eTime = check.getDaytime();
			
				sb.append("and a.kqtime=convert(datetime,'"+check.getIsChoose()+"')");
			
				//param.add(check.getIsChoose());
			}
			
		}

		if(check.getCardid()!=null&&!check.getCardid().equals("")){
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(check.getCardid());
			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and b.stafferid=? ");
				param.add(vo.getStafferid());
			}else{//û�п��Ż��û���
				sb.append(" and b.[name]=? ");
				param.add(check.getCardid());
			}
//			sb.append(" and a.stafferid = ?");
//			param.add(check.getCardid());
		}
		if (check.getGro() != null && !check.getGro().equals("")) {
			sb.append(" and b.[group] = '"+check.getGro()+"' ");
			//param.add(check.getGro());
		}
		sb.append(") as aa group by name,shunxu,[group],worktype,stafferid ");
		sb.append(") a ");
		sb.append("left join (select b.stafferid,b.cardid,b.occupation,b.iftemp,b.ifother,c.CardMode from staffer b left join recog c on b.cardid=c.cardid) b ");
		sb.append("on a.stafferid=b.stafferid ");
		sb.append(") bb where ((cardMode='����') or ([group]='���쵼') or (occupation='������Ա') or (occupation='����Ա') or (occupation='�߼�Ա') ");
		sb.append(" or (occupation='�����쵼') or (occupation='����˾��') or (iftemp='��ʽ��') or (iftemp='����') or (ifother='��ί��')) ");
		sb.append(") t ,reportpopedom rp  where rp.department=t.[group] and rp.userid='"+userId+"' order by t.type ");

		
		try {
			engine = EngineFactory.getEngine("test");
//			if(!flag.equals("temp")&&flag.equals("day"))
//			{				
//				System.out.print(check.getDaytime());
				engine.executeProcedureCall("{call CalFinalattendance (?,?,?) }",new Object[]{sTime,eTime,userId});
//			}
//			if(!flag.equals("temp")&&flag.equals("month"))
//			{			
//				System.out.print(check.getMonthtime());
//				engine.executeProcedureCall("{call CalFinalattendance (?,?,?) }",new Object[]{,chooseTime,userId});
//			}
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),Emphases_reportVO.class);
			int zaoban = 0;
			int zhongban = 0;
			int wanban = 0;
			int downwellcount = 0;
			String alltime = ""; // ��ʱ��
			int alltimeh = 0;
			int alltimes = 0;
			String counttime = ""; // ƽ��ʱ��
			int counttimeh = 0;
			int counttimes = 0;
			if (relist != null && relist.size() > 0) {
				for (int i = 0; i < relist.size(); i++) {
					Emphases_reportVO vo = (Emphases_reportVO) relist.get(i);
					vo.setCount(String.valueOf(i + 1));//���

					zaoban = zaoban + vo.getZaoban();//�������
					zhongban = zhongban + vo.getZhongban();//�а�����
					wanban = wanban + vo.getWanban();//�������
					downwellcount = downwellcount + vo.getDownwellcount();//�뾮����

					int totalTime = Integer.valueOf(vo.getAlltime()).intValue();
					int hour = totalTime / 60;
					int min = totalTime % 60;
					alltime = hour + "Сʱ" + min + "��";

					String temp = vo.getCounttime().substring(0,
							vo.getCounttime().indexOf("."));
					int avgTime = Integer.valueOf(temp).intValue();
					int hour1 = avgTime / 60;
					int min1 = avgTime % 60;
					counttime = hour1 + "Сʱ" + min1 + "��";

					alltimes += totalTime;
					counttimes += avgTime;
					// }
					//	    		 int hourd=Integer.parseInt(vo.getAlltime().substring(0,d));
					//	    		 int secod=Integer.parseInt(vo.getAlltime().substring(d+2,vo.getAlltime().length()-1));
					//	    		 alltimeh=alltimeh+hourd;
					//	    		 alltimes=alltimes+secod;//��ʱ��
					//	    		 
					//	    		 int c=vo.getCounttime().indexOf("Сʱ");
					//	    		 int hourdc=Integer.parseInt(vo.getCounttime().substring(0,c));
					//	    		 int secodc=Integer.parseInt(vo.getCounttime().substring(c+2,vo.getCounttime().length()-1));
					//	    		 counttimeh=counttimeh+hourdc;
					//	    		 counttimes=counttimes+secodc;//ƽ��ʱ��
					vo.setAlltime(alltime);
					vo.setCounttime(counttime);
					resultlist.add(vo);//����д
				}

				//            	alltime=String.valueOf(alltimeh+alltimes/60)+"Сʱ"+alltimes%60+"��";
				//            	counttime=String.valueOf(counttimeh+counttimes/60)+"Сʱ"+counttimes%60+"��";
				//ͳ�� 	
				counttimes = counttimes/relist.size();

				String totalHourStr = "";
				int totalH = alltimes / 60;
				int totalM = alltimes % 60;
				totalHourStr = totalH + "Сʱ" + totalM + "��";

				String avgHourStr = "";
				int avgH = counttimes / 60;
				int avgM = counttimes % 60;
				avgHourStr = avgH + "Сʱ" + avgM + "��";

				Emphases_reportVO vo = new Emphases_reportVO();
				vo.setCount("�ϼ�");
				vo.setZaoban(zaoban);
				vo.setZhongban(zhongban);
				vo.setWanban(wanban);
				vo.setDownwellcount(downwellcount);
				vo.setAlltime(totalHourStr);
				vo.setCounttime(avgHourStr);
				resultlist.add(vo);//����д

			}
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return resultlist;
	}
}