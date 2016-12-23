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

	// ���ݶ����ַ�����ý����
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
	
	// ��ȡjspҳ��ͳ������
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

	// ���ݶ����ַ�����ý����
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

	// ��÷�վ����λ����Ϣ
	public List selectCardReaderAndLocatorData(String sql,
			Map<String, Object> map) {
		List result = null;
		try {
			if (map.get("key").toString().equals("1")) {// ��վ��Ϣ
				CardReader cr = (CardReader) map.get("Obj");
				result = sqlMap.queryForList(sql, cr);
				sqlMap.endTransaction();
			} else if (map.get("key").toString().equals("2")) {// ��վ��Ϣ
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
	 * ��Ibatis���JDBC�ĵ�ǰConnection����
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
