package com.telezone.services;

import java.util.Map;

import com.kj133.entity.Ouser;

public interface IUserAndDepInfo {
	/**
	 * 页面初始化时执行的方法
	 */
	Map<String, Object> initialization();
	
	/**
	 * 修改用户部门权限
	 */
	Map<String, Object> getOuserDepartment(String Userid);
	
	/**
	 * 修改用户部门权限
	 */
	Map<String, Object> modifyOuserDepartment(String Userid, String departmentid);
}
