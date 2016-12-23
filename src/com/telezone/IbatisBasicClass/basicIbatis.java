package com.telezone.IbatisBasicClass;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class basicIbatis {
	public SqlMapClient getSqlMapClient() {
		String resource = "SqlMapConfig.xml";
		SqlMapClient sqlMap = null;

		try {
			java.io.Reader reader = com.ibatis.common.resources.Resources
					.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

			sqlMap.startTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sqlMap;
	}

	public void endSqlMapClient(SqlMapClient SqlMapClient) {
		try {
			SqlMapClient.endTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
