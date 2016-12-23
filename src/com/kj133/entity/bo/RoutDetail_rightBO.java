package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.Search_routDetail_right;
import com.kj133.entity.vo.RoutDetail_rightVO;
import com.kj133.entity.vo.Seek_left_downVO;

public class RoutDetail_rightBO {
	private Logger log = Logger.getLogger(this.getClass());

	public RoutDetail_rightBO() {

	}

	/**
	 * rightFrame(查看巡检路线)
	 */
	@SuppressWarnings("unchecked")
	public List check(String code) throws Exception {
		Engine engine = null;
		List result = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select * from (select routeorder as routeorder,a.type as type,pointID as pointid,b.shortname as shortname from RoutDetail ");
		sb
				.append(" as a,(select '分站' as type,shortname,cardreaderid as [ID] from cardreader union all select '定位器' as type,shortname, ");
		sb
				.append(" locatorid as [ID] from locator)as b where a.type=b.type and a.pointID=b.[ID] and code= ? ) as QueryTable order by routeorder ");
		param.add(code);
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			result = query.getResults(sb.toString(), param.toArray(),
					RoutDetail_rightVO.class);
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return result;
	}
	public List check1() throws Exception {
		Engine engine = null;
		List result = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb
		.append(" select * from (select routeorder as routeorder,a.type as type,pointID as pointid,b.shortname as shortname from RoutDetail ");
		sb
		.append(" as a,(select '分站' as type,shortname,cardreaderid as [ID] from cardreader union all select '定位器' as type,shortname, ");
		sb
		.append(" locatorid as [ID] from locator)as b where a.type=b.type and a.pointID=b.[ID]) as QueryTable order by routeorder ");
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			result = query.getResults(sb.toString(), param.toArray(),
					RoutDetail_rightVO.class);
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return result;
	}

	/**
	 * svae
	 */
	@SuppressWarnings("unchecked")
	public void save(String code, Search_routDetail_right right)
			throws Exception {
		Engine engine = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb
				.append(" insert into RoutDetail(Code,RouteOrder,Type,PointID)values(?,?,?,?) ");
		param.add(code);
		
		param.add(right.getRouteorder());
		param.add(right.getType());
		param.add(right.getPointid());
		try {
			engine = EngineFactory.getEngine("test");
			engine.executeSpecialSQL(sb.toString(), param.toArray());
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}

	}

	/**
	 * choice
	 */
	public List choiceRadio() throws Exception {
		Engine engine = null;
		List result = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select '分站' as choice_type,shortname as choice_shortname,cardreaderid as choice_cardreaderid  ");
		sb
				.append(" from cardreader where cardreaderid<>0 union all select '定位器',shortname,locatorid from locator ");
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			result = query.getResults(sb.toString(), param.toArray(),
					Seek_left_downVO.class);
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return result;
	}

	/**
	 * load
	 */
	@SuppressWarnings("unchecked")
	public List load(String code, String routeorder) throws Exception {
		Engine engine = null;
		List result = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select * from (select routeorder as routeorder,a.type as type,pointID as pointid,b.shortname as shortname from RoutDetail  ");
		sb
				.append(" as a,(select '分站' as type,shortname,cardreaderid as [ID] from cardreader union all select '定位器' as type,shortname, ");
		sb
				.append(" locatorid as [ID] from locator)as b where a.type=b.type and a.pointID=b.[ID] and code= ? ) as QueryTable where routeorder= ? ");
		param.add(code);
		param.add(routeorder);
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			result = query.getResults(sb.toString(), param.toArray(),
					Seek_left_downVO.class);
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return result;
	}

	/**
	 * load
	 */
	@SuppressWarnings("unchecked")
	public void update(Search_routDetail_right right, String code)
			throws Exception {
		Engine engine = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb
				.append(" update RoutDetail set Type=? ,PointID= ? where code= ? and RouteOrder= ? ");
		param.add(right.getType());
		param.add(right.getPointid());
		param.add(code);
		param.add(right.getRouteorder());
		try {
			engine = EngineFactory.getEngine("test");
			engine.executeSpecialSQL(sb.toString(), param.toArray());
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
	}

	/**
	 * delete
	 */
	@SuppressWarnings("unchecked")
	public void delete(String code, String routeorder) throws Exception {
		Engine engine = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append(" delete from routdetail where code= ? and routeorder= ? ");
		param.add(code);
		param.add(routeorder);
		try {
			engine = EngineFactory.getEngine("test");
			engine.executeSpecialSQL(sb.toString(), param.toArray());
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
	}

}
