package com.basic.entity.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.basic.entity.Search_WorkAtt;
import com.basic.entity.vo.WorkAttAreaVO;
import com.basic.entity.vo.WorkAttHisAreaVO;
import com.basic.entity.vo.WorkAttHisVO;

public class WorkAttHisBO {

	private final Logger log = Logger.getLogger(this.getClass());

	public WorkAttHisBO() {

	}

	/** 根据员工卡号查询员工信息 */

	@SuppressWarnings("unchecked")
	public List getStafferListHis(String cardid, String downtime)
			throws Exception {
		List relist = null;
		List param = new ArrayList();
		Engine engine = null;
		StringBuffer sb = new StringBuffer();
		sb.append(" select top 1 st.stafferID,st.Name,st.CardID,st.Department,st.occupation,convert(varchar,we.Downtime,20) Downtime,convert(varchar,we.Uptime,20) Uptime, ");
		sb.append(" substring(rtrim(100+(datediff(mi,we.Downtime,we.Uptime))/60),2,2)+'时'+ ");
		sb.append(" substring(rtrim(100+(datediff(mi,we.Downtime,we.Uptime))%60),2,2)+'分' as worktime ");
		sb.append(" from WorkattendanceEx we ,staffer st ");
		sb.append(" where st.CardID=we.cardid ");

		if (downtime != null && !downtime.equals("")) {
			sb.append(" and we.Downtime=? ");
			param.add(downtime);
		}

		if (cardid != null && !cardid.equals("")) {
			sb.append(" and we.cardid=? ");
			param.add(cardid);

		}

		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			// System.out.println("sb=" +sb.toString());
			relist = query.getResults(sb.toString(), param.toArray(),
					WorkAttAreaVO.class);
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return relist;
	}

	/**
	 * 历史信息查询
	 * 
	 * @param search_workatt
	 * @param pagin
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List init(Search_WorkAtt search_workatt, Pagination pagin)
			throws Exception {
		List relist = null;
		List param = new ArrayList();
		Engine engine = null;
		StringBuffer sb = new StringBuffer();
		String idname = search_workatt.getCardid();// 卡号和姓名
		String cardid=null;//卡号
		if(idname!=null&&!idname.equals("")){
			String [] idnames=idname.split("--");
			cardid=idnames[0];
		}
		String gro = search_workatt.getGroup();// 班组
		String starttime = search_workatt.getStarttime();// 查询的开始时间
		String endtime = search_workatt.getEndtime();// 查询的结束时间
		String userid = search_workatt.getUserid();// 用户id

		if (cardid != null && !cardid.equals("")) {
			sb.append(" SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED  ");
		}
		sb.append(" select a.name,a.cardid,a.[group],a.occupation,convert(varchar(20),a.downtime,120) downtime,convert(varchar(20),a.uptime,120)uptime,a.worktime from( ");
		sb.append(" select isnull(s.name,'未绑定')name,w.cardid,isnull(s.[group],'未绑定')[group],isnull(s.occupation,'未绑定')occupation,s.department,w.downtime,w.uptime, ");
		sb.append(" substring(rtrim(100+(datediff(mi,downtime,uptime))/60),2,2)+'时'+ ");
		sb.append(" substring(rtrim(100+(datediff(mi,downtime,uptime))%60),2,2)+'分' as worktime ");
		sb.append(" from workattendanceex w ");
		sb.append(" left join staffer s  on s.cardid=w.cardid   ");
		if (cardid != null && !cardid.equals("")) {
			sb.append(" and s.cardid=?  ");
			param.add(cardid);
		}
		if (gro != null && !gro.equals("")) {
			sb.append(" and s.[group]=? ");
			param.add(gro);
		}
		sb.append(" where w.uptime is not null ");
		if (starttime != null && !starttime.equals("")) {
			sb.append(" and w.uptime>=?  and w.uptime<=? ");
			param.add(starttime);
			param.add(endtime);
		}
		if (starttime != null && !starttime.equals("")) {
			sb.append(" and w.downtime>=?  and w.downtime<=? ");
			param.add(starttime);
			param.add(endtime);
		}
		sb.append(" )a,reportpopedom rp ");
		sb.append(" where rp.department=a.department and rp.userid='" + userid
				+ "' ");
		sb.append(" order by a.cardid ");
		try {

			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),
					WorkAttHisVO.class, pagin);
			// System.out.println("cardid="+cardid);
			// System.out.println("gro="+gro);
			// System.out.println("stime="+starttime);
			// System.out.println("etime="+endtime);
			// System.out.println("sb=="+sb.toString());
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return relist;
	}

	/**
	 * 行走轨迹
	 * 
	 * @param cardid
	 * @param name
	 * @param group
	 * @param occupation
	 * @param downtime
	 * @param uptime
	 * @param worktime
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List arealist(String cardid, String downtime, String uptime)
			throws Exception {
		List relist = null;
		List param = new ArrayList();
		Engine engine = null;
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		Calendar cald = Calendar.getInstance();
		Calendar calu = Calendar.getInstance();
		cald.setTime(df.parse(downtime));
		calu.setTime(df.parse(uptime));
		cald.add(Calendar.MINUTE, -30);
		calu.add(Calendar.MINUTE, +30);
		String dt = df.format(cald.getTime());
		String ut = df.format(calu.getTime());

		sb.append(" select convert(varchar(20),vl.intime,120) starttime,convert(varchar(20),vl.outtime,120) endtime,stayinterval,cr.crname from v_stayinterval vl  ");
		sb.append(" left join cardreader cr on vl.cardreaderid = cr.cardreaderid ");
		if (cardid != null && !cardid.equals("")) {
			sb.append(" where vl.cardid=?  ");
			param.add(cardid);
		}
		if (downtime != null && !downtime.equals("")) {
			sb.append(" and vl.intime<=? and vl.intime>=? ");
			param.add(ut);
			param.add(dt);
		}
		sb.append(" order by vl.intime ");
		try {

			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),
					WorkAttHisAreaVO.class);
			// System.out.println("stime"+starttime);
			// System.out.println("etime"+endtime);
			// System.out.println("sb=="+sb.toString());
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return relist;

	}

	@SuppressWarnings("unchecked")
	public List initPrint(Search_WorkAtt search_workatt) throws Exception {
		List relist = null;
		List param = new ArrayList();
		Engine engine = null;
		StringBuffer sb = new StringBuffer();
		String idname = search_workatt.getCardid();// 卡号和姓名
		String cardid=null;//卡号
		if(idname!=null&&!idname.equals("")){
			String [] idnames=idname.split("--");
			cardid=idnames[0];
		}
		String gro = search_workatt.getGroup();// 班组
		String starttime = search_workatt.getStarttime();// 查询的开始时间
		String endtime = search_workatt.getEndtime();// 查询的结束时间
		String userid = search_workatt.getUserid();// 用户id

		if (cardid != null && !cardid.equals("")) {
			sb.append(" SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED  ");
		}
		sb.append(" select a.name,a.cardid,a.[group],a.occupation,convert(varchar(20),a.downtime,120)downtime,convert(varchar(20),a.uptime,120)uptime,a.worktime from( ");
		sb.append(" select isnull(s.name,'未绑定')name,w.cardid,isnull(s.[group],'未绑定')[group],isnull(s.occupation,'未绑定')occupation,s.department,w.downtime,w.uptime, ");
		sb.append(" substring(rtrim(100+(datediff(mi,downtime,uptime))/60),2,2)+'时'+ ");
		sb.append(" substring(rtrim(100+(datediff(mi,downtime,uptime))%60),2,2)+'分' as worktime ");
		sb.append(" from workattendanceex w ");
		sb.append(" left join staffer s  on s.cardid=w.cardid   ");
		if (cardid != null && !cardid.equals("")) {
			sb.append(" and s.cardid=?  ");
			param.add(cardid);
		}
		if (gro != null && !gro.equals("")) {
			sb.append(" and s.[group]=? ");
			param.add(gro);
		}
		sb.append(" where w.uptime is not null ");
		if (starttime != null && !starttime.equals("")) {
			sb.append(" and w.uptime>=?  and w.uptime<=? ");
			param.add(starttime);
			param.add(endtime);
		}
		if (starttime != null && !starttime.equals("")) {
			sb.append(" and w.downtime>=?  and w.downtime<=? ");
			param.add(starttime);
			param.add(endtime);
		}
		sb.append(" )a,reportpopedom rp ");
		sb.append(" where rp.department=a.department and rp.userid='" + userid
				+ "' ");
		sb.append(" order by a.cardid ");
		try {

			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),
					WorkAttHisVO.class);
			// System.out.println("stime"+starttime);
			// System.out.println("etime"+endtime);
			// System.out.println("sb=="+sb.toString());
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return relist;

	}

}
