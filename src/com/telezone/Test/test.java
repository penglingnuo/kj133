package com.telezone.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

import com.telezone.IbatisBasicClass.OperateClass;

public class test extends TestCase {
	
	//执行XML中配置的SQL语句
	public void testIbatis() throws Exception {
//		获得分站中断信息
		OperateClass oc = new OperateClass();
		Connection conn = oc.getCurrentConnection();
//		CallableStatement stmt = (CallableStatement) conn.createStatement();
		
//		stmt=conn.prepareCall("{call dbo.Calreaderhalt()}", ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);  
		CallableStatement stmt = conn.prepareCall("{call Calreaderhalt}");  
		stmt.executeQuery();
		ResultSet rs = stmt.getResultSet();   
		
		int count = stmt.getUpdateCount();
		System.out.println(count);
	}
	
	//自定义传入SQL语句
//	public void testInputSQL() {
//		String sql = "select * from OGroup";
//		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		
//		paramMap.put("sql", sql);
//		
//		OperateClass oc = new OperateClass();
//		List list = oc.selectWithFullSQL(paramMap);
//		
//		for(int i = 0; i < list.size(); i ++) {
//			OGroup og = (OGroup)list.get(i);
//			
//			System.out.println(og.getGname());
//		}
//	}
}
