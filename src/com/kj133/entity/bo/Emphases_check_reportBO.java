package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import com.kj133.entity.Search_emphases_check_report;
import com.kj133.entity.vo.Emphases_check_reportVO;
import com.kj133.util.Global;

public class Emphases_check_reportBO {
	/**
	 * �ص㿼�����±�
	 */
	private Logger log = Logger.getLogger(Emphases_check_reportBO.class);

	public Emphases_check_reportBO() {

	}

    String stime = "";
    String etime = "";
	@SuppressWarnings("unchecked")
	public List getList(Search_emphases_check_report check, String userId)
			throws Exception {
		List relist = null;
		List resultlist = new ArrayList();
		List param = new ArrayList();
		Engine engine = null;
		StringBuffer sb = new StringBuffer();
		Global global = new Global();
		String chooseTime = check.getIsChoose();
		if (chooseTime != null && !chooseTime.equals("")) {
			if (chooseTime.length() < 8) {
				
				int days = global.getDaysOfMonth(chooseTime);

				String tempStrB = chooseTime + "-01";
				String tempStrE = chooseTime + "-" + days;
				
				stime = global.getDay(tempStrB, -1);
				etime = tempStrE;
			} else {
				stime = global.getDay(check.getIsChoose(), -1);
				etime = check.getDaytime();

			}

		}
		sb.append(" select a3.*, (Ban01+Ban02+Ban03 ) as BanNum, a4.totalNum, (downsum*1.0)/InToNum as pavIntoNum,  ");
		sb.append(" case  ");
		sb.append(" when (Ban01+Ban02+Ban03 ) =0 then 0 "); 
		sb.append(" when (Ban01+Ban02+Ban03 ) <> 0 then 1.0*worktime/( Ban01+Ban02+Ban03 ) ");
		sb.append(" end as  pavtime from ( ");
		sb.append(" select a2.type, sum(a2.downsum) downsum, sum(a2.worktime) worktime, "); 
		sb.append(" sum(Ban01) as 'Ban01', sum(Ban02) as 'Ban02', sum(Ban03) as 'Ban03', count(*) InToNum from ( "); 
		sb.append(" select a1.*, b1.cardMode, b1.cardid, b1.occupation, b1.iftemp, b1.ifother, ");
		sb.append(" case  ");
		sb.append(" when b1.cardMode='����'       then b1.cardMode "); 
		sb.append(" when a1.[group]='���쵼'      then a1.[group]  ");
		sb.append(" when b1.occupation='������Ա' then b1.occupation  ");
		sb.append(" when (b1.occupation='����Ա') or (b1.occupation='�߼�Ա') then b1.occupation "); 
		sb.append(" when b1.occupation='�����쵼' then b1.occupation  ");
		sb.append(" when b1.occupation='����˾��' then b1.occupation   ");
		sb.append(" when b1.iftemp='��ʽ��'  then b1.iftemp  ");
		sb.append(" when b1.iftemp='����'  then b1.iftemp  ");
		sb.append(" when b1.ifother='��ί��' then b1.ifother  ");
		sb.append(" else '����'  ");
		sb.append(" end as type from ( "); 
		sb.append(" select a0.stafferid,a0.downsum, a0.worktime, ");
		sb.append(" Ban01,Ban02,Ban03 ");
		sb.append(" ,b0.[name], b0.[group],b0.worktype, b0.shunxu from ( "); 
		sb.append(" select  stafferid, sum(downsum) as downsum, sum(worktime) as worktime ");
		sb.append(" ,case when Ban_ID = 1 and BanTypeID = 0 then Count(stafferid) else 0 end 'Ban01' ");
		sb.append(" ,case when Ban_ID = 2 and BanTypeID = 0 then Count(stafferid) else 0 end 'Ban02' ");
		sb.append(" ,case when Ban_ID = 3 and BanTypeID = 0 then Count(stafferid) else 0 end 'Ban03' ");
		sb.append(" from finattendance where userid = ?  group by stafferid, Ban_id, BanTypeId )  ");
		param.add(userId);
		
		sb.append(" as a0 left join staffer b0 on a0.stafferid = b0.stafferid  )as  ");
		sb.append(" a1 left join (  ");
		sb.append(" select b0.stafferid,b0.cardid,b0.occupation,b0.iftemp,b0.ifother,a0.CardMode from staffer as "); 
		sb.append(" b0 left join recog  ");
		sb.append(" a0 on b0.cardid=a0.cardid) as "); 
		sb.append(" b1 on a1.stafferid = b1.stafferid  ");
		sb.append(" where ((cardMode='����') or ([group]='���쵼') or (occupation='������Ա') or (occupation='����Ա')or "); 
		sb.append(" (occupation='�߼�Ա')or (occupation='�����쵼') or (occupation='����˾��') or (iftemp='��ʽ��')or  ");
		sb.append(" (iftemp='����') or (ifother='��ί��'))) as  ");
		sb.append(" a2  group by type)  as a3 ");
		sb.append(" left join (   ");
		sb.append(" select type,count(*) TotalNum from ( "); 
		sb.append(" select  ");
		sb.append(" case    ");
		sb.append(" WHEN B.CARDMODE='����' THEN B.CARDMODE "); 
		sb.append(" WHEN A.[GROUP]='���쵼' THEN A.[GROUP]  ");
		sb.append(" WHEN OCCUPATION='������Ա' THEN OCCUPATION  "); 
		sb.append(" WHEN (OCCUPATION='����Ա') OR (OCCUPATION='�߼�Ա') THEN OCCUPATION "); 
		sb.append(" WHEN OCCUPATION='�����쵼' THEN OCCUPATION  ");
		sb.append(" WHEN OCCUPATION='����˾��' THEN OCCUPATION  ");
		sb.append(" WHEN IFTEMP='��ʽ��' THEN IFTEMP ");
		sb.append(" WHEN IFTEMP='����' THEN IFTEMP  ");
		sb.append(" WHEN IFOTHER='��ί��' THEN IFOTHER  ");
		sb.append(" END AS TYPE ");
		sb.append(" from staffer a,recog b where a.cardid=b.cardid and((cardMode='����') or "); 
		sb.append(" ([group]='���쵼') or (occupation='������Ա') or (occupation='����Ա') or ");
		sb.append(" (occupation='�߼�Ա')or (occupation='�����쵼') or (occupation='����˾��') or "); 
		sb.append(" (iftemp='��ʽ��') or (iftemp='����') or (ifother='��ί��'))) a where type is not null group by type "); 
		sb.append(" ) a4  on a3.type = a4.type ");

		/*sb.append("select t.type worktype,t.downsum downtime,t.worktime alltime,t.zao zaoban,t.zhong zhongban,t.wan wanban,t.�뾮���� peoplecount,t.������ allperson,t.�ϼ� bancicount,round(t.pav,0) counttime,round(t.pav1,2) avgtimes from(");
		sb.append("select g.*,h.������,(zao+zhong+wan) �ϼ�,1.0*worktime/(zao+zhong+wan) pav,1.0*downsum/�뾮���� pav1 from (");
		sb.append("select type,sum(downsum) downsum,sum(worktime) worktime,sum(zao) zao,sum(zhong) zhong,sum(wan) wan,count(*) �뾮���� from (");
		sb.append("select *,1.0*worktime/(zao+zhong+wan) pav from (");
		sb.append("select a.*,b.cardMode,b.cardid,occupation,iftemp,ifother");
		sb.append(",case when b.cardMode='����' then b.cardMode ");
		sb.append("when a.[group]='���쵼' then a.[group] ");
		sb.append("when occupation='������Ա' then occupation");
		sb.append(" when (occupation='����Ա') or (occupation='�߼�Ա') then occupation");
		sb.append(" when occupation='�����쵼' then occupation ");
		sb.append("when occupation='����˾��' then occupation ");
		sb.append("when iftemp='��ʽ��' then iftemp ");
		sb.append("when iftemp='����' then iftemp ");
		sb.append("when ifother='��ί��' then ifother ");
		sb.append("else '����' end as type ");
		sb.append("from (");
		sb.append("select name,shunxu,[group],worktype,stafferid,sum(downsum) downsum,sum(worktime) worktime,sum(zao) zao,sum(zhong) zhong,");
		sb.append("sum(wan) wan");
		sb.append(" from (");
		sb.append("select b.[name],b.shunxu,b.[group],b.worktype,a.stafferid,a.downsum,a.worktime,a.kqtime,a.starttime,a.endtime");
		sb.append(",case when convert(varchar(2),a.Ban_name)='��' then 1 else 0 end zao");
		sb.append(",case when convert(varchar(2),a.Ban_name)='��' then 1 else 0 end zhong");
		sb.append(",case when convert(varchar(2),a.Ban_name)='��' then 1 else 0 end wan");
		sb.append(" from finattendance a,staffer b ");
		sb.append("where  a.stafferid=b.stafferid and a.userid=?");
		param.add(userId);
		String chooseTime = check.getIsChoose();
		if (chooseTime != null && !chooseTime.equals("")) {
			if (chooseTime.length() < 8) {
				
				int days = global.getDaysOfMonth(chooseTime);

				String tempStrB = chooseTime + "-01";
				String tempStrE = chooseTime + "-" + days;
				
				stime = global.getDay(tempStrB, -1);
				etime = tempStrE;
				sb.append("and a.kqtime>convert(datetime,'" + stime + "')");
				//param.add(tempStrB);
				sb.append("and a.kqtime<convert(datetime,'" + etime + "')");
				//param.add(tempStrE);
			} else {
				stime = global.getDay(check.getIsChoose(), -1);
				etime = check.getDaytime();

				sb.append("and a.kqtime=convert(datetime,'"+ check.getIsChoose() + "')");
				//param.add(check.getIsChoose());
			}

		}
		sb.append(") as aa group by name,shunxu,[group],worktype,stafferid");
		sb.append(") a ");
		sb.append("left join (select b.stafferid,b.cardid,b.occupation,b.iftemp,b.ifother,c.CardMode from staffer b left join recog c on b.cardid=c.cardid) b");
		sb.append(" on a.stafferid=b.stafferid");
		sb.append(") bb where ((cardMode='����') or ([group]='���쵼') or (occupation='������Ա') or (occupation='����Ա') or (occupation='�߼�Ա')");
		sb.append(" or (occupation='�����쵼') or (occupation='����˾��') or (iftemp='��ʽ��') or (iftemp='����') or (ifother='��ί��'))");
		sb.append(") e group by type");
		sb.append(") g left join (");
		sb.append("select type,count(*) ������ from (");
		sb.append("select case when b.cardMode='����' then b.cardMode");
		sb.append(" when a.[group]='���쵼' then a.[group] ");
		sb.append(" when occupation='������Ա' then occupation");
		sb.append(" when (occupation='����Ա') or (occupation='�߼�Ա') then occupation");
		sb.append(" when occupation='�����쵼' then occupation ");
		sb.append(" when occupation='����˾��' then occupation");
		sb.append(" when iftemp='��ʽ��' then iftemp ");
		sb.append(" when iftemp='����' then iftemp");
		sb.append(" when ifother='��ί��' then ifother end as type");
		sb.append(" from staffer a,recog b where a.cardid=b.cardid and");
		sb.append("((cardMode='����') or ([group]='���쵼') or (occupation='������Ա') or (occupation='����Ա') or (occupation='�߼�Ա')");
		sb.append(" or (occupation='�����쵼') or (occupation='����˾��') or (iftemp='��ʽ��') or (iftemp='����') or (ifother='��ί��'))");
		sb.append(") a where type is not null group by type");
		sb.append(") h on g.type=h.type");
		sb.append(") t");*/
		try {
			engine = EngineFactory.getEngine("test");
			 engine.executeProcedureCall("{ call CalFinalattendance(?,?,?)}",new Object[] {stime,etime,userId});
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),Emphases_check_reportVO.class);
			int zaoban = 0;
			int zhongban = 0;
			int wanban = 0;
			int bancicount = 0;
			int totalnum = 0;
			int intonum = 0;
			float avgtimes = 0;
			int bannum = 0;
			int downsum = 0;
//			int allperson = 0;      //������
			
//			int peoplecount = 0;   //�뾮����
//          int downtime = 0;     //�뾮����
			String alltime = ""; // ��ʱ��
			int alltimeh = 0;
			int alltimes = 0;
			String counttime = ""; // ƽ��ʱ��
			int counttimeh = 0;
			int counttimes = 0;
			float avgtimeh = 0;
			if (relist != null && relist.size() > 0) {
				for (int i = 0; i < relist.size(); i++) {
					Emphases_check_reportVO vo = (Emphases_check_reportVO) relist.get(i);
					vo.setCount(String.valueOf(i + 1));//���

					zaoban = zaoban + vo.getBan01();//�������
					zhongban = zhongban + vo.getBan02();//�а�����
					wanban = wanban + vo.getBan03();//�������
					totalnum += vo.getTotalnum();
					intonum += vo.getIntonum();
					downsum += vo.getDownsum();
					bannum += vo.getBannum();
					
					

					int totalTime = Integer.valueOf(vo.getWorktime());
					int hour = totalTime / 60;
					int min = totalTime % 60;
					alltime = hour + "Сʱ" + min + "��";

					String temp = vo.getPavtime().substring(0,vo.getPavtime().indexOf("."));
					int avgTime = Integer.valueOf(temp);
//					int avgTime = Integer.valueOf(vo.getPavtime());
					int hour1 = avgTime / 60;
					int min1 = avgTime % 60;
					counttime = hour1 + "Сʱ" + min1 + "��";

					alltimes += totalTime;
//					counttimes += avgTime;

					vo.setPavintonum(Float.valueOf(global.RoundFracToString(Float.valueOf(vo.getPavintonum()), 3)));
					vo.setWorktime(alltime);
					vo.setPavtime(counttime);
					
					resultlist.add(vo);//����д
				}

				//ͳ�� 	
//				counttimes = counttimes / relist.size();
				avgtimes = avgtimes / relist.size();

				String totalHourStr = "";
				int totalH = alltimes / 60;
				int totalM = alltimes % 60;
				totalHourStr = totalH + "Сʱ" + totalM + "��";

				String avgHourStr = "";
//				int avgH = counttimes / 60;
//				int avgM = counttimes % 60;
				int avgH = alltimes/bannum / 60;
				int avgM = alltimes/bannum % 60;
				avgHourStr = avgH + "Сʱ" + avgM + "��";

				Emphases_check_reportVO vo = new Emphases_check_reportVO();

				vo.setCount("�ϼ�");

				vo.setBan01(zaoban);
				vo.setBan02(zhongban);
		    	vo.setBan03(wanban);
		    	vo.setDownsum(downsum);
		    	vo.setIntonum(intonum);
		    	vo.setTotalnum(totalnum);
		    	vo.setBannum(bannum);
				vo.setWorktime(totalHourStr);
				vo.setPavtime(avgHourStr);
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