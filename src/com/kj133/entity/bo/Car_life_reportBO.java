package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_car_life_report;

import com.kj133.entity.vo.Car_life_reportVO;

import com.kj133.util.Global;

public class Car_life_reportBO {
	/**
	 * ���������/����ϸ��
	 * */
	private Logger log = Logger.getLogger(Car_life_reportBO.class);
	

	public Car_life_reportBO() {

	}
	private String flag = "temp" ;
	@SuppressWarnings("unchecked")
	public List getList(Search_car_life_report car_life_report, String userId)
			throws Exception {
		String sTime = "";
		String eTime = "";
		List relist = null;
		List resultlist = new ArrayList();
		List param = new ArrayList();
		Engine engine = null;
		StringBuffer sb = new StringBuffer();
		
	/*	sb.append(" select name,shunxu,worktype,cardid,stafferid,sum(����) �ܷ���,count(*) ����,ban_ID,banname"); 
		sb.append("  from (");
		sb.append(" select aa.*,recog.CardMode from (");
		sb.append(" select b.[name],b.shunxu,b.[group],b.worktype,b.cardid,a.ban_ID,a.stafferid,datediff(n,starttime,endtime) ����,convert(varchar(2),a.Ban_name) banname,a.worktime"); 
		sb.append(" from finattendance a,staffer b where convert(char(10),a.kqtime,20)>='2008-10-24' and convert(char(10),a.kqtime,20)<'2008-10-25' and a.stafferid=b.stafferid and a.userid='sys' and a.starttime<=a.endtime");
		sb.append(" ) aa left join recog on aa.Cardid=recog.Cardid");
		sb.append("  where recog.CardMode='����'");
		sb.append(" ) d group by name,shunxu,worktype,cardid,stafferid,ban_ID,banname order by worktype,name,banname");
		*/
		sb.append("select d.cardid ,d.���� fenzhong,d.name,d.banname,d.worktype,sum(d.zao) zaoban,sum(d.zhong) zhongban,sum(d.wan) wanban,count(d.zao+d.zhong+d.wan) tote,sum(d.����) alltime,round(1.0*worktime/(zao+zhong+wan+other),0) counttime");
		sb.append(" from (");
		sb.append("select aa.*,recog.CardMode from (");
		sb.append("select b.[name],b.shunxu,b.[group],b.worktype,b.cardid,a.stafferid,a.downsum,a.worktime,datediff(n,starttime,endtime) ����,convert(varchar(2),a.Ban_name) banname "); 
		sb.append(",case when convert(varchar(2),a.Ban_name)='��' then 1 else 0 end zao");
		sb.append(",case when convert(varchar(2),a.Ban_name)='��' then 1 else 0 end zhong");
		sb.append(",case when convert(varchar(2),a.Ban_name)='��' then 1 else 0 end wan");
		sb.append(",case when (convert(varchar(2),a.Ban_name)<>'��' and convert(varchar(2),a.Ban_name)<>'��' and convert(varchar(2),a.Ban_name)<>'��') then 1 else 0 end other"); 
        sb.append(" from finattendance a,staffer b where a.stafferid=b.stafferid and a.userid=? and a.starttime<=a.endtime ");
		param.add(userId);
		String chooseTime = car_life_report.getIsChoose();
		
		Global global = new Global();
		
		if(chooseTime!=null&&!chooseTime.equals("")){
			if(chooseTime.length()<8){
				int days = global.getDaysOfMonth(chooseTime);
				flag = "month";
				
				String tempStrB = chooseTime +"-01";
				String tempStrE = chooseTime + "-" +String.valueOf(days);
				
				sTime = global.getDay(tempStrB, -1);
				eTime = tempStrE;
				String eetime = global.getDay(tempStrE, +1);
				sb.append(" and convert(char(10),a.kqtime,20)>= ? ");
				param.add(tempStrB);
				sb.append(" and convert(char(10),a.kqtime,20)< ?");
				param.add(eetime);
//				sb.append(" and a.kqtime>convert(datetime,'"+sTime+"')");
				
//				sb.append(" and a.kqtime<convert(datetime,'"+eTime+"')");
				
				
			}else{
				flag = "day";
				String ssTime = global.getDay(car_life_report.getIsChoose(), +1);
				
				sTime = global.getDay(car_life_report.getIsChoose(), -1);
			
				eTime = car_life_report.getDaytime();
				
			
//				sb.append(" and a.kqtime=convert(datetime,'"+car_life_report.getIsChoose()+"')");
				sb.append(" and convert(char(10),a.kqtime,20)>= ? ");
				param.add(eTime);
				sb.append(" and convert(char(10),a.kqtime,20)< ?");
				param.add(ssTime);
				
				
			
				
			}
			
		}
		if (car_life_report.getCartype() != null && !car_life_report.getCartype().equals("")) {
			sb.append(" and b.worktype = '"+car_life_report.getCartype()+"' ");
			
		}

		sb.append(") aa left join recog on aa.Cardid=recog.Cardid");
		sb.append(" where recog.CardMode='����'");
		sb.append(") d group by name,����,shunxu,worktype,cardid,stafferid,banname,zao,zhong,wan,worktime,other order by worktype,name,banname,zao,zhong,wan,worktime,other");
		
		try {
			engine = EngineFactory.getEngine("test");
			
			engine.executeProcedureCall("{call CalFinalattendancecar (?,?,?) }",new Object[]{sTime,eTime,userId});

			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),Car_life_reportVO.class);
			int zaoban = 0;
			int zhongban = 0;
			int wanban = 0;
			int tote = 0;
			String alltime = ""; // ��ʱ��
			String zaotimes= ""; // ��ʱ��
			int zaos = 0;
			int zhongs = 0;
			int wans = 0;

			String zhongtimes= ""; // ��ʱ��

			String wantimes= ""; // ��ʱ��
			int alltimeh = 0;
			int alltimes = 0;
			String counttime = ""; // ƽ��ʱ��
			int counttimeh = 0;
			int counttimes = 0;
			if (relist != null && relist.size() > 0) {
				for (int i = 0; i < relist.size(); i++) {
					Car_life_reportVO vo = (Car_life_reportVO) relist.get(i);
					vo.setCount(String.valueOf(i + 1));//���

					zaoban = zaoban + vo.getZaoban();//�������
					zhongban = zhongban + vo.getZhongban();//�а�����
					wanban = wanban + vo.getWanban();//�������
					tote = tote + vo.getTote();  //�ϼ�����
					if(vo.getBanname().equals("��")){
					int	zt = Integer.valueOf(vo.getFenzhong()).intValue();
					int zhour = zt / 60;
					int zmin = zt % 60;
					zaotimes = zhour + "Сʱ" + zmin + "��";
					zaos += zt;
					}
					if(vo.getBanname().equals("��")){
						int	zht = Integer.valueOf(vo.getFenzhong()).intValue();
						int zhhour = zht / 60;
						int zhmin = zht % 60;
						zhongtimes = zhhour + "Сʱ" + zhmin + "��";	
						zhongs += zht;
					}
					if(vo.getBanname().equals("��")){
						int	wt = Integer.valueOf(vo.getFenzhong()).intValue();
						int whour = wt / 60;
						int wmin = wt % 60;
						wantimes = whour + "Сʱ" + wmin + "��";	
						wans += wt;
					}
					

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
					
					
					vo.setWantimes(wantimes);
					vo.setZhongtimes(zhongtimes);
					vo.setZaotimes(zaotimes);
					vo.setAlltime(alltime);
					vo.setCounttime(counttime);
					resultlist.add(vo);//����д
				}
               //ͳ�� 	
				
				
				counttimes = counttimes/relist.size();
				
				String zaoStr = "";
				int zrH = zaos / 60;
				int zrM = zaos % 60;
				zaoStr = zrH + "Сʱ" + zrM + "��";
				
				String zhongStr = "";
				int zhrH = zhongs / 60;
				int zhrM = zhongs % 60;
				zhongStr = zhrH + "Сʱ" + zhrM + "��";
				
				String wanStr = "";
				int wrH = wans / 60;
				int wrM = wans % 60;
				wanStr = wrH + "Сʱ" + wrM + "��";

				String totalHourStr = "";
				int totalH = alltimes / 60;
				int totalM = alltimes % 60;
				totalHourStr = totalH + "Сʱ" + totalM + "��";

				String avgHourStr = "";
				int avgH = counttimes / 60;
				int avgM = counttimes % 60;
				avgHourStr = avgH + "Сʱ" + avgM + "��";

				Car_life_reportVO vo = new Car_life_reportVO();
				vo.setCount("�ϼ�");
				vo.setZaoban(zaoban);
				vo.setZhongban(zhongban);
				vo.setWanban(wanban);
				vo.setTote(tote);
                
//				vo.setZaotimes(zaoStr);
//				vo.setZhongtimes(zhongStr);
//				vo.setWantimes(wanStr);
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