package com.telezone.services;

import java.util.Map;

import com.kj133.entity.Ouser;

public interface IReportPopedom {
	/**
	 * 页面初始化时执行的方法
	 */
	Map<String, Object> initialization();
	
	/**
	 * 修改用户权限
	 */
	Map<String, Object> getOuserReportPopedom(String Userid);
	
	/**
	 * 修改用户权限
	 */
	Map<String, Object> modifyOuserReportPopedom(String Userid, String departmentid);
}
