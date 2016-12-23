package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_outcar_life_report;

import com.kj133.entity.vo.Outcar_life_reportVO;


import com.kj133.util.Global;

public class Outcar_life_reportBO {
	/**
	 * 车辆出车日月报
	 * */
	private Logger log = Logger.getLogger(Outcar_life_reportBO.class);
	

	public Outcar_life_reportBO() {

	}
	private String flag = "temp" ;
	@SuppressWarnings("unchecked")
	public List getList(Search_outcar_life_report outcar_life_report, String userId)
			throws Exception {
		String sTime = "";
		String eTime = "";
		List relist = null;
		List resultlist = new ArrayList();
		List param = new ArrayList();
		Engine engine = null;
		StringBuffer sb = new StringBuffer();
		
		
//		sb.append("select a.name,a.worktype,a.banname,a.分钟,a.次数,a.班平均时长,a.车数 车辆数,ceiling(c.总车数*e.各班出车率/100) 车数,e.各班出车率 出勤率,b.总分钟,c.总车数,c.出车率,d.合计车数 合计车辆数,ceiling(c.出车率*c.总车数/100) 合计车数,b.总分钟/d.合计车数 平均时长 from  "); 
		sb.append("select a.name,a.worktype,a.banname,a.次数 cishu,a.班平均时长 bancounttime,ceiling(c.总车数*e.各班出车率/100) cheshu,e.各班出车率 banodds,b.总分钟 alltime,c.总车数 cars,c.出车率 carodds,ceiling(c.出车率*c.总车数/100)  tote,b.总分钟/d.合计车数  counttime from  "); 
//		sb.append("select a.worktype,a.banname,a.次数 cishu,a.班平均时长 bancounttime,ceiling(c.总车数*e.各班出车率/100) cheshu,e.各班出车率 banodds,b.总分钟 alltime,c.总车数 cars,c.出车率 carodds,ceiling(c.出车率*c.总车数/100) tote,b.总分钟/d.合计车数 counttime from "); 
		

		sb.append("( ");
//		sb.append("--表a  ");
		sb.append("select name,worktype,banname,sum(分钟)分钟,sum(次数) 次数,1.0*sum(分钟)/sum(次数) 班平均时长,count(*) 车数 from ( ");
		sb.append("select b.name,b.worktype,a.stafferid,sum(datediff(n,starttime,endtime)) 分钟,convert(varchar(2),a.Ban_name) banname,count(*) 次数 ");   
//		sb.append("select worktype,banname,sum(分钟)分钟,sum(次数) 次数,1.0*sum(分钟)/sum(次数) 班平均时长,count(*) 车数 from ( ");
//		sb.append("select b.worktype,a.stafferid,sum(datediff(n,starttime,endtime)) 分钟,convert(varchar(2),a.Ban_name) banname,count(*) 次数 ");   
		sb.append("from finattendance a,staffer b where a.stafferid=b.stafferid and a.userid=? "); 
		param.add(userId);
		String chooseTime = outcar_life_report.getIsChoose();
		
		Global global = new Global();
		
		if(chooseTime!=null&&!chooseTime.equals("")){
			if(chooseTime.length()<8){
				int days = global.getDaysOfMonth(chooseTime);
				flag = "month";
				
				String tempStrB = chooseTime +"-01";
				String tempStrE = chooseTime + "-" +String.valueOf(days);
				
				sTime = global.getDay(tempStrB, -1);
				eTime = tempStrE;
				sb.append(" and a.kqtime>convert(datetime,'"+sTime+"') ");
				
				sb.append(" and a.kqtime<convert(datetime,'"+eTime+"') ");
	
				
			}else{
				flag = "day";
				sTime = global.getDay(outcar_life_report.getIsChoose(), -1);
			
				eTime = outcar_life_report.getDaytime();

			
				sb.append(" and a.kqtime=convert(datetime,'"+outcar_life_report.getIsChoose()+"') ");
			
				
			}
			
		}
		sb.append(" group by worktype,a.stafferid,name,convert(varchar(2),a.Ban_name) ");
		sb.append(") aa group by worktype,banname,name ");
//		sb.append(" group by worktype,a.stafferid,convert(varchar(2),a.Ban_name) ");
//		sb.append(") aa group by worktype,banname ");
//        sb.append("--表a ");
		sb.append(") a ");
		sb.append(" left join "); 
		sb.append("( ");
//		sb.append("--表b ");
		sb.append("select b.worktype,sum(datediff(n,starttime,endtime)) 总分钟 "); 
		sb.append("from finattendance a,staffer b where a.stafferid=b.stafferid and a.userid=? "); 
		param.add(userId);
		sb.append(" group by worktype ");
//		sb.append("--表b ");
		sb.append(") b ");
        sb.append("	 on a.worktype=b.worktype  ");
		sb.append("	 left join ");
		sb.append("	( ");
//		sb.append("	--表c ");
//		sb.append("	-------按车辆类型求出出车率，总车数 ");
		sb.append("	select worktype,总车数,100.0*sum(出车率)/? 出车率 from "); 
		
		if(chooseTime!=null&&!chooseTime.equals("")){
			if(chooseTime.length()<8){
				int days = global.getDaysOfMonth(chooseTime);
				flag = "month";				
				param.add(days);
				
			}else{
				flag = "day";


			param.add(1);
				
			}
			
		}
		
//		param.add(String.valueOf(days));
		sb.append("	( ");
		sb.append("	select a.*,b.总车数,1.0*a.车数/b.总车数 出车率 from "); 
		sb.append("	( ");
		sb.append("	select worktype,kqtime,sum(当天入井次数) 合计次数,count(*) 车数 from ( ");
		sb.append(" select b.worktype,a.stafferid,convert(varchar(10),kqtime,20) kqtime,count(*) 当天入井次数 ");
		sb.append("	from finattendance a,staffer b where a.stafferid=b.stafferid and a.userid=?  ");
		param.add(userId);
		sb.append("	 group by worktype,a.stafferid,convert(varchar(10),kqtime,20) ");
		sb.append("	) aa group by worktype,kqtime ");
		sb.append("	) a ");
		sb.append("	 left join "); 
		sb.append("	( ");
		sb.append("	select worktype,count(*) 总车数 from staffer "); 
		sb.append("	left join recog on recog.cardid=staffer.cardid ");
		sb.append("	 where staffer.cardid is not null and recog.CardMode='车辆' group by staffer.worktype ");
		sb.append("	) b ");
		sb.append("	 on a.worktype=b.worktype "); 
		sb.append("	) a ");
		sb.append("	 group by worktype,总车数 ");
//		sb.append("	-------按车辆类型求出出车率，总车数 ");
//		sb.append("	--表c ");
		sb.append("	) c  ");
		sb.append("	 on a.worktype=c.worktype  ");
//		sb.append("	 --order by a.worktype  ");
		sb.append("	 left join  ");
		sb.append("	( ");
//		sb.append("	--表d ");
		sb.append("	select worktype,count(*) 合计车数 from ( ");
		sb.append("select distinct b.worktype,a.stafferid ");
		sb.append("	from finattendance a,staffer b where a.stafferid=b.stafferid and a.userid=?) aa ");
		param.add(userId);
		sb.append("	 group by worktype ");
//		sb.append("	--表d ");
		sb.append("	) d  ");
		sb.append("	on a.worktype=d.worktype ");
		sb.append(" left join  ");
		sb.append("	( ");
//		sb.append("	-------表e   按车辆类型求出各班次出车率，总车数 ");
		sb.append("	select worktype,banname,100.0*sum(出车率)/? 各班出车率 from ");
		if(chooseTime!=null&&!chooseTime.equals("")){
			if(chooseTime.length()<8){
				int days = global.getDaysOfMonth(chooseTime);
				flag = "month";				
				param.add(days);
				
			}else{
				flag = "day";


			param.add(1);
				
			}
			
		}
		
		
		sb.append("	( ");
		sb.append("	select a.*,b.总车数,1.0*a.车数/b.总车数 出车率 from "); 
		sb.append("( ");
		sb.append("	select worktype,kqtime,banname,count(*) 车数 from ( ");
		sb.append("	select distinct b.worktype,a.stafferid,convert(varchar(10),kqtime,20) kqtime,convert(varchar(2),a.Ban_name) banname "); 
		sb.append("	from finattendance a,staffer b where a.stafferid=b.stafferid and a.userid=?  ");
		param.add(userId);
		sb.append(") aa group by worktype,kqtime,banname ");
		sb.append(") a ");
		sb.append(" left join "); 
		sb.append("( ");
		sb.append("select worktype,count(*) 总车数 from staffer "); 
		sb.append(" left join recog on recog.cardid=staffer.cardid ");
		sb.append(" where staffer.cardid is not null and recog.CardMode='车辆' group by staffer.worktype ");
		sb.append(") b ");
		sb.append(" on a.worktype=b.worktype "); 
		sb.append(") a ");
		sb.append(" group by worktype,banname,总车数 ");
//		sb.append("-------表e   按车辆类型求出各班次出车率，总车数 ");
		sb.append(") e  ");
		sb.append(" on a.worktype=e.worktype and a.banname=e.banname ");
		Global go = new Global();

		try {
			engine = EngineFactory.getEngine("test");
			
			engine.executeProcedureCall("{call CalFinalattendancecar (?,?,?) }",new Object[]{sTime,eTime,userId});

			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),Outcar_life_reportVO.class);

			int tote = 0;
			String alltime = ""; // 总时长
			String zaotimes = "";  //早平均时长
			String zhongtimes = "";//中平均时长
			String wantimes = "";//晚平均时长
			int zaocheshu =0;
			int zhongcheshu =0;
			int wancheshu =0;
			int wancishu =0;
			int zaocishu =0;
			int zhongcishu =0;
			int zaocishus = 0;
			int zhongcishus=0;
			int wancishus = 0;
			int zaocheshus = 0;
			int zhongcheshus = 0;
			int wancheshus = 0;
			double zaoodds = 0.00;
			double zhongodds = 0.00;
			double wanodds = 0.00;
			double zao = 0;
			double zhong = 0;
			double wan = 0;
			double caroddss = 0;

			
			String zaocheid = "";
			String zhongcheid = "";
			String wancheid = "";
			int cars = 0;
			int zaos = 0;
			int zhongs = 0;
			int wans = 0;
			int alltimeh = 0;
			int alltimes = 0;
			String counttime = ""; // 平均时长
			
			int counttimeh = 0;
			int counttimes = 0;
			if (relist != null && relist.size() > 0) {
				int aaa=1;
				for (int i = 0; i < relist.size(); i++) {
					List relist1 = relist;
					Outcar_life_reportVO vo = (Outcar_life_reportVO) relist.get(i);
					String name = vo.getName();
				//把车名称以 ，分割开	
					int cishu1 = 1;
					for(int j = i+1; j<relist1.size(); j++){
						Outcar_life_reportVO vo2 = (Outcar_life_reportVO) relist1.get(j);
						if(vo.getWorktype().equals(vo2.getWorktype())){
							name+=","+vo2.getName();
							relist.remove(j);
							j--;
							++cishu1;
						}
					}
					
					
					
					vo.setCount((aaa++)+"");
//					vo.setCount(String.valueOf(i + 1));//序号
					
					
					if(vo.getBanname().equals("早")){
						String temp = vo.getBancounttime().substring(0,vo.getBancounttime().indexOf("."));
						int	zt = Integer.valueOf(temp).intValue();						
						int zhour = zt / 60;
						int zmin = zt % 60;
						zaotimes = zhour + "小时" + zmin + "分";
						zaocheshu = vo.getCheshu();
						vo.setZaocishu(cishu1);
						zaoodds = vo.getBanodds();
						zaocheid = vo.getName();
						zaocishus += cishu1;
						zaocheshus +=vo.getCheshu();
						vo.setZaocheid(name);
						zaos += zt;
						zao +=zaoodds;
					}
					if(vo.getBanname().equals("中")){
						String temp = vo.getBancounttime().substring(0,vo.getBancounttime().indexOf("."));
						int	zht = Integer.valueOf(temp).intValue();
						int zhhour = zht / 60;
						int zhmin = zht % 60;
						zhongtimes = zhhour + "小时" + zhmin + "分";	
						zhongcheshu = vo.getCheshu();
						vo.setZhongcishu(cishu1);
						zhongodds = vo.getBanodds();
						
						vo.setZhongcheid(name);
						zhongcheid = vo.getName();
						zhongcishus += cishu1;
						zhongcheshus +=vo.getCheshu();
						zhong += zhongodds;
						zhongs += zht;
					}
					if(vo.getBanname().equals("晚")){
						String temp = vo.getBancounttime().substring(0,vo.getBancounttime().indexOf("."));
						int	wt = Integer.valueOf(temp).intValue();
						int whour = wt / 60;
						int wmin = wt % 60;
						wantimes = whour + "小时" + wmin + "分";	
						wancheshu = vo.getCheshu();
						vo.setWancishu(cishu1);
						wanodds = vo.getBanodds();
						wancheid = vo.getName();
						wancishus += cishu1;
						wancheshus +=vo.getCheshu();
						vo.setWancheid(name);
						wan +=wanodds;
						
						
						wans += wt;
					}
					cars = cars +vo.getCars();

					tote = tote + vo.getTote();  //合计人数
					caroddss =caroddss +  Double.valueOf(vo.getCarodds()).doubleValue();;
					

					int totalTime = Integer.valueOf(vo.getAlltime()).intValue();
					int hour = totalTime / 60;
					int min = totalTime % 60;
					alltime = hour + "小时" + min + "分";

//					String temp = vo.getCounttime().substring(0,
//							vo.getCounttime().indexOf("."));
//					int avgTime = Integer.valueOf(temp).intValue();
					int avgTime = Integer.valueOf(vo.getCounttime()).intValue();
					int hour1 = avgTime / 60;
					int min1 = avgTime % 60;
					counttime = hour1 + "小时" + min1 + "分";

					alltimes += totalTime;
					counttimes += avgTime;
					
//					vo.setZaocheid(zaocheid);       
//					vo.setZhongcheid(zhongcheid);
//					vo.setWancheid(wancheid);
//				    vo.setCarodds(carodds);
					
					vo.setZaoodds(go.RoundFracToString(zaoodds,2)+"%");
					String zhos = go.RoundFracToString(zhongodds,2);
					vo.setZhongodds(zhos+"%");
					vo.setWanodds(go.RoundFracToString(wanodds,2)+"%");
					vo.setZaocheshu(zaocheshu);
//					vo.setZaocishu(zaocishu);
					vo.setZhongcheshu(zhongcheshu);
//					vo.setZhongcishu(zhongcishu);
					vo.setWancheshu(wancheshu);
//					vo.setWancishu(wancishu);
					vo.setZaoct(zaotimes);
					vo.setCarodds(go.RoundFracToString(Double.valueOf(vo.getCarodds()).doubleValue(), 2)+"%");
					vo.setZhongct(zhongtimes);
					vo.setWanct(wantimes);
					vo.setAlltime(alltime);
					vo.setCounttime(counttime);
					resultlist.add(vo);//必须写
				}
               //统计 	
				counttimes = counttimes/relist.size();
				zaos = zaos/relist.size();
				zao = zao/relist.size();
				zhong = zhong/relist.size();
				wan = wan/relist.size();
				caroddss =caroddss/relist.size();
				
				String zaoStr = "";
				int zrH = zaos / 60;
				int zrM = zaos % 60;
				zaoStr = zrH + "小时" + zrM + "分";
				
				zhongs = zhongs/relist.size();
				String zhongStr = "";
				int zhrH = zhongs / 60;
				int zhrM = zhongs % 60;
				zhongStr = zhrH + "小时" + zhrM + "分";
				
				wans = wans/relist.size();
				String wanStr = "";
				int wrH = wans / 60;
				int wrM = wans % 60;
				wanStr = wrH + "小时" + wrM + "分";

				String totalHourStr = "";
				int totalH = alltimes / 60;
				int totalM = alltimes % 60;
				totalHourStr = totalH + "小时" + totalM + "分";

				String avgHourStr = "";
				int avgH = counttimes / 60;
				int avgM = counttimes % 60;
				avgHourStr = avgH + "小时" + avgM + "分";

				Outcar_life_reportVO vo = new Outcar_life_reportVO();
				vo.setCount("合计");
				
				vo.setZaoodds(go.RoundFracToString(zao,2)+"%");
				
				String wsd = go.RoundFracToString(zhong,2);
					
				vo.setZhongodds(wsd+"%");
				vo.setWanodds(go.RoundFracToString(wan,2)+"%");
				vo.setZaocheshu(zaocheshus);
				vo.setZaocishu(zaocishus);
				vo.setZhongcheshu(zhongcheshus);
				vo.setZhongcishu(zhongcishus);
				vo.setWancheshu(wancheshus);
				vo.setWancishu(wancishus);
				vo.setCarodds(go.RoundFracToString(caroddss, 2)+"%");
				vo.setZaoct(zaoStr);
				vo.setZhongct(zhongStr);
				vo.setWanct(wanStr);

				vo.setCars(cars);
				vo.setTote(tote);

				vo.setAlltime(totalHourStr);
				vo.setCounttime(avgHourStr);
				resultlist.add(vo);//必须写

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