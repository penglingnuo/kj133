package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.GjUser;

public class GjUserBO {

	private Logger log = Logger.getLogger(this.getClass());

	public GjUserBO() {
	}

	/**
	 * 管技用户登陆
	 */
	@SuppressWarnings("unchecked")
	public GjUser getGjLogin(GjUser gjuser) throws Exception {
		GjUser gju = null;
		Engine engine = null;
		StringBuffer sb = new StringBuffer();
		List param = new ArrayList();
		sb
				.append(" select id,gjusername,gjuserpwd from gjuser where gjuserpwd=? and gjusername=? ");
		param.add(gjuser.getGjuserpwd());
		param.add(gjuser.getGjusername());
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			List list = query.getResults(sb.toString(), param.toArray(),
					GjUser.class);
			if (list != null && list.size() > 0) {
				gju = (GjUser) list.get(0);// 把值给user
			} else {
				gju = null;
			}
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return gju;
	}

	/**
	 * 管技用户修改用户名，密码
	 */
	@SuppressWarnings("unchecked")
	public boolean updategjpwd(String gjusername, String newPassword, String id)
			throws Exception {
		Engine engine = null;
		boolean result = false;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append(" update gjuser set ");
		if (gjusername != null && !gjusername.equals("")) {
			sb.append(" gjusername=?,");
			param.add(gjusername);
		}
		if (newPassword != null && !newPassword.equals("")) {
			sb.append(" gjuserpwd=? ");
			param.add(newPassword);
		}
		if (id != null && !id.equals("")) {
			sb.append("  where id=? ");
			param.add(id);
		}
		try {
			engine = EngineFactory.getEngine("test");
			engine.executeSpecialSQL(sb.toString(), param.toArray());
			engine.commit();
			result = true;
		} catch (Exception e) {
			result = false;
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return result;
	}

	/**
	 * 增加管技用户
	 * 
	 * @param gjuser
	 * @return
	 */
	public boolean addgjuser(GjUser gjuser) {
		Engine engine = null;
		boolean result = false;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into gjuser values (?,?) ");
		param.add(gjuser.getGjusername());
		param.add(gjuser.getGjuserpwd());
		try {
			engine = EngineFactory.getEngine("test");
			engine.executeSpecialSQL(sb.toString(), param.toArray());
			engine.commit();
			result = true;
		} catch (RuntimeException e) {
			result = false;
			engine.rollback();
			log.error(e);
			e.printStackTrace();
		} finally {
			engine.closeEngine();
		}
		return result;
	}

	/**
	 * 查询出所有的用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public List selAlluser() throws Exception {
		Engine engine = null;
		List gjuserlist = null;
		StringBuffer sb = new StringBuffer();
		List param=new ArrayList();
		sb.append(" select id,gjusername,gjuserpwd from gjuser where gjusername!='admin' order by id ");
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			gjuserlist = query.getResults(sb.toString(), param.toArray(), GjUser.class);
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return gjuserlist;
	}

	/**
	 * 删除管技用户
	 * 
	 * @param gjuser
	 * @return
	 */
	public boolean delgjuser(String id) {
		Engine engine = null;
		boolean result = false;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append(" delete gjuser where id=? ");
		param.add(id);
		try {
			engine = EngineFactory.getEngine("test");
			engine.executeSpecialSQL(sb.toString(), param.toArray());
			engine.commit();
			result = true;
		} catch (RuntimeException e) {
			result = false;
			engine.rollback();
			log.error(e);
			e.printStackTrace();
		} finally {
			engine.closeEngine();
		}
		return result;
	}

	/**
	 * 管技检查用户名是否是重复
	 */
	public boolean checkgjusername(String gjusername) throws Exception {
		boolean result = false;
		GjUser gju = null;
		Engine engine = null;
		StringBuffer sb = new StringBuffer();
		List param = new ArrayList();
		sb.append(" select id,gjusername,gjuserpwd from gjuser where gjusername=? ");
		param.add(gjusername);
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			List list = query.getResults(sb.toString(), param.toArray(),GjUser.class);
			if(list==null){
				result=true;
			}else{
				if (list.size() >= 1) {
					result=false;
				}else if(list.size()==0) {
					result=true;
				}
			}
		} catch (Exception e) {
			result = false;
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return result;
	}
}
