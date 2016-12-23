package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;

import com.kj133.entity.Search_Account;
import com.kj133.util.Global;
import com.kj133.util.encrypt;

public class AccountBO {
 
	 private static final Logger log=Logger.getLogger(Global.class);
	 
	 /**
	  * 增加部门
	  * */
	 @SuppressWarnings("unchecked")
	public void Adddepartment(Search_Account account)throws Exception{
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" insert into BS_ODepartment values(?,?,?,?,?) ");
		 param.add(account.getDepartment_id());
		 param.add(account.getDepartment_dname());
		 param.add(account.getDepartment_depict());
		 param.add("");
		 param.add("");
		 try{
			 engine=EngineFactory.getEngine("test");
	         engine.executeSpecialSQL(sb.toString(),param.toArray());
	         engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
	 }
	 /**
	  * 修改部门
	  * */
	 @SuppressWarnings("unchecked")
	public void updateDepartment(Search_Account account)throws Exception {
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer(); 
		 sb.append(" update BS_ODepartment set dname = ?,ddescription= ? where departmentid=? ");
		 param.add(account.getDepartment_dname());
		 param.add(account.getDepartment_depict());
		 param.add(account.getDepartment_id());
		 try{
			 engine=EngineFactory.getEngine("test");
			 engine.executeSpecialSQL(sb.toString(),param.toArray());
			 engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 
	 }
	 /**
	  * 删除部门
	  * */
	 @SuppressWarnings("unchecked")
	public void Deletedepartment(Search_Account account)throws Exception{
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" delete from BS_Odepartment where departmentid= ? ");
		 param.add(account.getDepartment_id());
		 try{
			 engine=EngineFactory.getEngine("test");
			 engine.executeSpecialSQL(sb.toString(),param.toArray());
			 engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
	  }
	 
	 /**
	  * 增加组别
	  * */
	 @SuppressWarnings("unchecked")
	public void addGroup(Search_Account account)throws Exception{
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" insert into ogroup (GroupID,GName,Gdescription,Departmentid) values(?,?,?,?)");
		 param.add(account.getGroup_id());
		 param.add(account.getGroup_name());
		 param.add(account.getGroup_depict());
		 param.add(account.getDepartment_id());
		 try{
			 engine=EngineFactory.getEngine("test");
			 engine.executeSpecialSQL(sb.toString(),param.toArray());
			 engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
	 }
	 
	 /**
	  * 修改组别
	  * */
	 @SuppressWarnings("unchecked")
	public void updateGroup(Search_Account account)throws Exception {
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer(); 
		 sb.append(" update ogroup set gname= ?,gdescription = ? where groupid=? ");
		 param.add(account.getGroup_name());
		 param.add(account.getGroup_depict());
		 param.add(account.getGroupid());
		 try{
			 engine=EngineFactory.getEngine("test");
			 engine.executeSpecialSQL(sb.toString(),param.toArray());
			 engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 
	 }
	 /**
	  * 删除组别
	  * */
	 @SuppressWarnings("unchecked")
	public void deleteGroup(String gid)throws Exception {
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" delete from ogroup where groupid = ?");
		 param.add(gid);
		 try{
			 engine=EngineFactory.getEngine("test");
			 engine.executeSpecialSQL(sb.toString(),param.toArray());
			 engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
	 }
	 
	  /**
	   * 增加用户
	   * */
	  @SuppressWarnings("unchecked")
	public void addUser(Search_Account account)throws Exception{
		     Engine engine=null;
//		     encrypt et = new encrypt();
			 List param=new ArrayList();
			 StringBuffer sb=new StringBuffer();
			 
			 sb.append(" insert into bs_ouser(userid,oname,opassword,departmentid,groupid,createtime) values(?,?,?,?,?,getDate());");
			 param.add(account.getUid());
			 param.add(account.getOname());
			 if(account.getPassword()==null||account.getPassword().equals("")){
				 param.add("123456");
			 }else{
				 param.add(account.getPassword());
			 }
			 param.add(account.getDepartment_id());
			 param.add(account.getGroupid());
			 sb.append(" insert into userpopedom values(?,2);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,5);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,7);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,8);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,9);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,10);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,11);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,12);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,13);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,14);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,15);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,16);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,17);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,18);");
			 param.add(account.getUid());
			 sb.append(" insert into userpopedom values(?,19);");
			 param.add(account.getUid());
			 
			 try{
				 engine=EngineFactory.getEngine("test");
				 engine.executeSpecialSQL(sb.toString(),param.toArray());
				 engine.commit();
			 }catch(Exception e){
				 engine.rollback();
				 log.error(e);
				 throw e;
			 }finally{
				 engine.closeEngine();
			 }
	    }
	  
	   /**
	    * 修改用户
	    * */
	  @SuppressWarnings("unchecked")
	public void  updateUser(Search_Account account,String name)throws Exception {
		     Engine engine=null;
//		     encrypt et = new encrypt();
			 List param=new ArrayList();
			 StringBuffer sb=new StringBuffer();
			 sb.append(" update bs_ouser set userid= ?,oname= ?,opassword= ? where userid= ? ;");
			 param.add(account.getUid());
			 param.add(account.getOname());
			 param.add(account.getPassword());
			 param.add(name);
			 //修改功能权限
			 sb.append(" update reportpopedom set userid=? where userid=?;");
			 param.add(account.getUid());
			 param.add(name);
			 //修改部门权限，数据权限
			 sb.append(" update userpopedom set uid=? where uid=?;");
			 param.add(account.getUid());
			 param.add(name);
			 try{
				 engine=EngineFactory.getEngine("test");
				 engine.executeSpecialSQL(sb.toString(),param.toArray());
				 engine.commit();
			 }catch(Exception e){
				 engine.rollback();
				 log.error(e);
				 throw e;
			 }finally{
				 engine.closeEngine();
			 }
	  }
	  /**
		  * 删除用户
		  * */
		 @SuppressWarnings("unchecked")
		public void deleteUser(Search_Account account)throws Exception {
			 Engine engine=null;
			 List param=new ArrayList();
			 StringBuffer sb=new StringBuffer();
			 sb.append(" delete from bs_ouser where userid= ? ");
			 param.add(account.getUid());
			 sb.append(" delete from userpopedom where uid= ? ");
			 param.add(account.getUid());
			 sb.append(" delete from reportpopedom where userid= ? ");
			 param.add(account.getUid());
			 try{
				 engine=EngineFactory.getEngine("test");
				 engine.executeSpecialSQL(sb.toString(),param.toArray());
				 engine.commit();
			 }catch(Exception e){
				 engine.rollback();
				 log.error(e);
				 throw e;
			 }finally{
				 engine.closeEngine();
			 }
		 }
}
