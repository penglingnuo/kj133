package com.telezone.IbatisBasicClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.telezone.domain.classes.CardReader;
import com.telezone.domain.classes.Locator;

public class OperateClass {
	private final Logger logger = Logger.getLogger(this.getClass());

	static SqlMapClient sqlMap = null;

	static {
		basicIbatis bi = new basicIbatis();
		sqlMap = bi.getSqlMapClient();
	}

	// 根据对象字符串获得结果集
	public List selectWithObjectString(String sqlName) {
		List user = null;
		try {
			user = sqlMap.queryForList(sqlName);
			sqlMap.endTransaction();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return user;
	}
	
	// 获取jsp页面统计数据
	public List selectCountList(String sqlName) {
		List intranet = null;
		try {
			intranet = sqlMap.queryForList(sqlName);
			sqlMap.endTransaction();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return intranet;
	}

	// 根据对象字符串获得结果集
	public List selectWithObjectString(String sqlName, Object obj) {
		List user = null;
		try {
			user = sqlMap.queryForList(sqlName, obj);
			sqlMap.endTransaction();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return user;
	}

	// 获得分站、定位器信息
	public List selectCardReaderAndLocatorData(String sql,
			Map<String, Object> map) {
		List result = null;
		try {
			if (map.get("key").toString().equals("1")) {// 分站信息
				CardReader cr = (CardReader) map.get("Obj");
				result = sqlMap.queryForList(sql, cr);
				sqlMap.endTransaction();
			} else if (map.get("key").toString().equals("2")) {// 基站信息
				Locator lr = (Locator) map.get("Obj");
				result = sqlMap.queryForList(sql, lr);
				sqlMap.endTransaction();
			}
		} catch (Exception e) {
			logger.info(e);
		}
		return result;
	}

	/*
	 * 从Ibatis获得JDBC的当前Connection连接
	 * 
	 * @author LYee
	 * 
	 * @Date 2010-06-10
	 * 
	 * @return Connection
	 */
	public Connection getCurrentConnection() {
		basicIbatis bi = new basicIbatis();
		sqlMap = bi.getSqlMapClient();
		Connection conn = null;

		try {
			conn = sqlMap.getCurrentConnection();
		} catch (SQLException e) {
			logger.info(e);
		}

		return conn;
	}

	// Insert
	public String insert(String sqlName, Object obj) {
		String returnStr = "1";
		try {
			sqlMap.endTransaction();
			sqlMap.startTransaction();
			sqlMap.insert(sqlName, obj);
			sqlMap.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			returnStr = "0";
		}

		finally {
			try {
				sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnStr;
	}
	
	// Insert
	public String update(String sqlName, Object obj) {
		String returnStr = "1";
		try {
			sqlMap.update(sqlName, obj);
		} catch (Exception e) {
			e.printStackTrace();
			returnStr = "0";
		}
		
		finally {
			try {
				sqlMap.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnStr;
	}

	// Delete
	public void delete() {
	}
}
