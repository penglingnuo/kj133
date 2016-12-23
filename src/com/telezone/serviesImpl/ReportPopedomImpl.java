package com.telezone.serviesImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.Ouser;
import com.kj133.entity.Staffer;
import com.telezone.domain.classes.ReportPopedom;
import com.telezone.services.IReportPopedom;

public class ReportPopedomImpl implements IReportPopedom {
	private final Logger logger = Logger.getLogger(this.getClass());

	public Map<String, Object> initialization() {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		Engine engine = null;

		StringBuffer sb = new StringBuffer();
		sb.append("select * from bs_ouser where UserID <> 'sys' ");
		List paramList = new ArrayList();

		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			List list = query.getResults(sb.toString(), paramList.toArray(),
					Ouser.class);

			returnMap.put("ouser", list);
		} catch (Exception e) {
			engine.rollback();
			logger.error(e);
		} finally {
			engine.closeEngine();
		}

		sb = new StringBuffer();
		sb.append("select distinct department from staffer");

		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			List list = query.getResults(sb.toString(), paramList.toArray(),
					Staffer.class);

			returnMap.put("staffer", list);
		} catch (Exception e) {
			engine.rollback();
			logger.error(e);
		} finally {
			engine.closeEngine();
		}

		return returnMap;
	}

	public Map<String, Object> getOuserReportPopedom(String Userid) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		Engine engine = null;
		List paramList = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from reportpopedom where userid = ?");
		paramList.add(Userid);

		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			List list = query.getResults(sb.toString(), paramList.toArray(),
					ReportPopedom.class);

			returnMap.put("reportpopedom", list);
		} catch (Exception e) {
			engine.rollback();
			logger.error(e);
		} finally {
			engine.closeEngine();
		}
		
		return returnMap;
	}

	public Map<String, Object> modifyOuserReportPopedom(String Userid,
			String departmentid) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		Engine engine = null;
		List paramList = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append("delete from reportpopedom where userid = '" + Userid+"'");

		List list = Arrays.asList(departmentid.split(","));
		
		try {
			engine = EngineFactory.getEngine("test");
			engine.executeSpecialSQL(sb.toString(), (new ArrayList()).toArray());
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			logger.error(e);
		} finally {
			engine.closeEngine();
		}
		
		for(int i = 0; i < list.size(); i ++) {
			sb = new StringBuffer();
			paramList = new ArrayList();
			sb.append("insert into reportpopedom values(?, ?)");
			paramList.add(Userid);
			paramList.add(list.get(i));
			
			
			engine = EngineFactory.getEngine("test");
			engine.executeSpecialSQL(sb.toString(), paramList.toArray());
			engine.commit();
		}
		
		returnMap.put("isSuccess", "1");
		
		return returnMap;
	}
}
