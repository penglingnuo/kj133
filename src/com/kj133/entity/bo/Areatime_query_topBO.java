package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_areatime_query_top;
import com.kj133.entity.vo.Areatime_query_topVO;

public class Areatime_query_topBO {

	private final Logger log = Logger.getLogger(this.getClass());

	public Areatime_query_topBO() {

	}

	@SuppressWarnings("unchecked")
	public List init(Search_areatime_query_top areatime_query_top,Pagination pagin)
			throws Exception {
		List relist = null;

		List param = new ArrayList();
		Engine engine = null;

		StringBuffer sb = new StringBuffer();
		String an = areatime_query_top.getAreaid();

		String stime = areatime_query_top.getStime();

		String etime = areatime_query_top.getEtime();
		

		sb.append(" select a.areaid,b.areaname,recordtime,visitorcount,minercount,peoplecount ");
		sb.append(" from areadata a left join v_areainfo b on a.areaid=b.areaid where  ");
		if (an!= null && !an.equals("")){
			sb.append(" a.areaid=? and ");
			param.add(an);
		}
		sb.append("  (visitorcount>0 or minercount>0 or peoplecount>0) and recordtime>? and recordtime<? order by recordtime desc ");
		param.add(stime);
		param.add(etime);

		try {
			engine = EngineFactory.getEngine("test");
			// engine.executeProcedureCall("{call calovertime (?,?,?) }",new
			// Object[]{userid,stime,etime});

			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),Areatime_query_topVO.class,pagin);

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
	public List initPrint(Search_areatime_query_top areatime_query_top)
	throws Exception {
		List relist = null;
		
		List param = new ArrayList();
		Engine engine = null;
		
		StringBuffer sb = new StringBuffer();
		String an = areatime_query_top.getAreaid();
		
		String stime = areatime_query_top.getStime();
		
		String etime = areatime_query_top.getEtime();
		

		sb.append(" select a.areaid,b.areaname,recordtime,visitorcount,minercount,peoplecount ");
		sb.append(" from areadata a left join v_areainfo b on a.areaid=b.areaid where  ");
		if (an!= null && !an.equals("")){
			
			sb.append(" a.areaid=? and  ");
			param.add(an);
		}
		sb.append(" (visitorcount>0 or minercount>0 or peoplecount>0) and recordtime>? and recordtime<? order by recordtime desc ");
		param.add(stime);
		param.add(etime);
		
		try {
			engine = EngineFactory.getEngine("test");
			// engine.executeProcedureCall("{call calovertime (?,?,?) }",new
			// Object[]{userid,stime,etime});
			
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),
					Areatime_query_topVO.class);
			
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
