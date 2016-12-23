package com.kj133.entity.bo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_stripclip_query_top;
import com.kj133.entity.vo.Stripclip_query_topVO;
import com.kj133.util.Global;

public class Stripclip_query_topBO {

	private final Logger log = Logger.getLogger(this.getClass());

	public Stripclip_query_topBO() {

	}

	@SuppressWarnings("unchecked")
	public List init(Search_stripclip_query_top stripclip_query_top)
			throws Exception {
		List relist = null;
		List result = new ArrayList();
		List param = new ArrayList();
		
		Engine engine = null;
		Global go=new Global();

		StringBuffer sb = new StringBuffer();
		
		
		

		String stime = stripclip_query_top.getStime();

		String etime = stripclip_query_top.getEtime();
		
		String minitime = stripclip_query_top.getMinitime();
		
		int space = Integer.valueOf(stripclip_query_top.getSpace()).intValue();
		

		 
//		 System.out.println("space = " + space);
//		 System.out.println("minitime = " + minitime);
		sb.append(" select a.cardid,a.stafferid,a.downtime,b.[name] from( ");
		sb.append(" select cardid,stafferid,downtime,uptime from workattendanceex "); 
		sb.append(" where  ");
		sb.append(" downtime>? and downtime<? ");
		param.add(stime);
		param.add(etime);
		sb.append(" and (uptime is null or datediff(hh,downtime,uptime)>=?) ");
		param.add(minitime);
		
		sb.append(" ) as a left join staffer as b on a.stafferid=b.stafferid ");
		sb.append(" order by downtime ");

/*		sb.append(" select a.areaid,b.areaname,recordtime,visitorcount,minercount,peoplecount ");
		sb.append(" from areadata a left join v_areainfo b on a.areaid=b.areaid where  ");
		if (an!= null && !an.equals("")){
			System.out.println("123");
			sb.append(" a.areaid=? and  ");
			param.add(an);
		}
		sb.append(" (visitorcount>0 or minercount>0 or peoplecount>0) and recordtime>? and recordtime<? order by recordtime desc ");
		param.add(stime);
		param.add(etime);
*/
		try {
			engine = EngineFactory.getEngine("test");
			// engine.executeProcedureCall("{call calovertime (?,?,?) }",new
			// Object[]{userid,stime,etime});
			
			List resultlist = new ArrayList();
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),
					Stripclip_query_topVO.class);
			if(relist != null&&relist.size()>0){
				int teams = 1;
				for(int i = 0;i<relist.size();i++){
					Stripclip_query_topVO vo = (Stripclip_query_topVO)relist.get(i);
					if(i+1<relist.size()){
						
					for(int j = i+1;j<relist.size();j++){
						Stripclip_query_topVO vo2 = (Stripclip_query_topVO)relist.get(j);
						String se = (vo.getDowntime()).substring(0, 19);
						String ee = (vo2.getDowntime()).substring(0, 19);
						int gog = go.getTimesBetween(se, ee);
							if(gog<=space){
								vo.setTeams(teams);
							}else{
								vo.setTeams(teams++);
							}
							resultlist.add(vo);
							break;
						}	
					}else{		
						vo.setTeams(teams);
						resultlist.add(vo);
						break;
					}

					
				}

				}
			result.add(0,resultlist);
			
//			2 step
			List result2 = this.getDataList(resultlist,space);
			
			result.add(1,result2);

			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
//		return resultlist;
		return result;
	}
	
	public List getDataList(List resultlist,int space){
		
		List retlist = new ArrayList();
		Global go1=new Global();
		retlist.clear();
		for(int t = 0;t<resultlist.size()-1;t++){
			Stripclip_query_topVO v1 = (Stripclip_query_topVO)resultlist.get(t);
			
			String temp = String.valueOf(v1.getTeams());
			Stripclip_query_topVO  v0= null;
				for(int s = t+1;s<resultlist.size();s++){
					Stripclip_query_topVO v2 = (Stripclip_query_topVO)resultlist.get(s);
					String temp2 = String.valueOf(v2.getTeams());
					String se = (v1.getDowntime()).substring(0, 19);
					String ee = (v2.getDowntime()).substring(0, 19);
					
					
					try {
						int gog  = go1.getDaysBetween(se,ee);
						if(space != 0 && gog<=space){
							if(temp.equals(temp2)){  
								v0 = new Stripclip_query_topVO();
								v0.setTeams1(v1.getTeams());
								v0.setCardidA(v1.getCardid());
								v0.setCardidB(v2.getCardid());
								v0.setDowntimeA(v1.getDowntime());
								v0.setDowntimeB(v2.getDowntime());
								v0.setNameA(v1.getName());
								v0.setNameB(v2.getName());
								v0.setStafferidA(v1.getStafferid());
								v0.setStafferidB(v2.getStafferid());
								
								retlist.add(v0);
							}else{
								continue;
							}
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
		}
		return retlist;
	}

	/*
	 * public List choiceRadio() throws Exception { Engine engine = null; List
	 * result = null; List param = new ArrayList(); StringBuffer sb = new
	 * StringBuffer();
	 * 
	 * sb.append(" select
	 * aid,areaname,areaorder,parentarea,areatype,maxsum,caijuesum,stayminute
	 * from ( "); sb.append(" select AreaID as
	 * aid,areaname,areaorder,parentarea,areatype,maxsum,caijuesum,stayminute
	 * from v_areainfo "); sb.append(" ) as QueryTable ");
	 *  // sb.append(" select '分站' as choice_type,shortname as
	 * choice_shortname,cardreaderid as choice_cardreaderid "); // sb.append("
	 * from cardreader where cardreaderid<>0 union all select
	 * '定位器',shortname,locatorid from locator "); try { engine =
	 * EngineFactory.getEngine("test"); Query query = engine.getQuery(); result =
	 * query.getResults(sb.toString(), param.toArray(), Area_listVO.class);
	 * engine.commit(); } catch (Exception e) { engine.rollback(); log.error(e);
	 * throw e; } finally { engine.closeEngine(); } return result; }
	 */

	/**
	 * 用于报表里面和合计信息
	 */
	/*
	 * @SuppressWarnings("unchecked") public List
	 * getAllWorkTime(Search_areatime_query_top areatime_query_top)throws
	 * Exception{ List relist=null; List param=new ArrayList(); Engine
	 * engine=null;
	 * 
	 * String stime=areatime_query_top.getStime(); String
	 * s1=stime.substring(0,10); String s2=stime.substring(11,stime.length());
	 * 
	 * String etime=areatime_query_top.getEtime(); String
	 * e1=etime.substring(0,10); String e2=etime.substring(11,etime.length());
	 * 
	 * StringBuffer sb=new StringBuffer(); sb.append(" select
	 * isnull(allworktime,' ') as allworktime from (select
	 * rtrim(convert(int,sum(convert(float,uptime-downtime))))+'天'+rtrim(datepart(hh,sum(convert(float,uptime-downtime))))
	 * "); sb.append("
	 * +'小时'+rtrim(datepart(n,sum(convert(float,uptime-downtime))))+'分' as
	 * allworktime from ( select uptime,downtime, "); sb.append(" temp1.downtime
	 * as 入井时间 from(select downtime , uptime from WorkattendanceEx where
	 * downtime>= ? and (uptime>= ? "); sb.append(" or uptime is null) and
	 * downtime<convert(datetime, ?)+1 and (uptime<convert(datetime, ?)+3 or
	 * uptime "); sb.append(" is null)) as temp1 ) as Sumtable where
	 * convert(char(8),入井时间,8)>= ? and convert(char(8),入井时间,8)<= ? ) as tab");
	 * param.add(s1); param.add(s1);
	 * 
	 * param.add(e1); param.add(e1); param.add(s2); param.add(e2);
	 * 
	 * try{ engine=EngineFactory.getEngine("test"); Query
	 * query=engine.getQuery();
	 * relist=query.getResults(sb.toString(),param.toArray(),Areatime_query_topVO.class);
	 * }catch(Exception e){ engine.rollback(); log.error(e); throw e; }finally{
	 * engine.closeEngine(); } return relist; }
	 */
}
