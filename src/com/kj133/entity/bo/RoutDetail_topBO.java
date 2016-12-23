package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import com.kj133.entity.Routing;
import com.kj133.entity.Search_routDetail_top;

public class RoutDetail_topBO {
	private final Logger log = Logger.getLogger(this.getClass());

	public RoutDetail_topBO() {

	}

	/**
	 * top(œ‘ æ)
	 */
	public List top_show() throws Exception {
		List relist = null;
		Engine engine = null;
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select * from (select code ,routename ,routeinfo  from routing) as QueryTable ");
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), new Object[] {},
					Routing.class);
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
	 * top_mainFrame(Save)
	 */

	public void save(Search_routDetail_top top) throws Exception {

		Engine engine = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append("insert into Routing(code,routename,routeinfo) values(?,?,?)");
		
//		param.add("");
		param.add(top.getCode());
		param.add(top.getRoutename());
		param.add(top.getRouteinfo());
//		System.out.println("+++"+top.getCode()+"+++"+top.getRoutename()+"+++"+top.getRouteinfo());

		try {
			engine = EngineFactory.getEngine("test");
			engine.executeSpecialSQL(sb.toString(), param.toArray());
			// engine.save(rg);
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
	 * top_mainFrame(Load)
	 */
	public Object load(Class pojo_class, String code) throws Exception {
		Object obj = null;

		Engine engine = null;
		try {
			engine = EngineFactory.getEngine("test");
			obj = engine.load(pojo_class, code);
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return obj;
	}

	/**
	 * top_main_Frame update
	 */
	@SuppressWarnings("unchecked")
	public void update(Search_routDetail_top top) throws Exception {
		Engine engine = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append(" update routing set routename=?,routeinfo=? where code= ?");
		param.add(top.getRoutename());
		param.add(top.getRouteinfo());
		param.add(top.getCode());
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
	 * top_main_Frame delete
	 */
	public void delete(String id) throws Exception {
		Engine engine = null;
		Routing routing = null;
		try {
			engine = EngineFactory.getEngine("test");
			routing = (Routing) engine.load(Routing.class, id);
			engine.delete(routing);
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
	}
	
	public void delete11(String id) throws Exception {
		Engine engine = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append(" delete from routdetail where code= ?  ");
		param.add(id);
		
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
