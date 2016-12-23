package com.kj133.entity.bo;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.vo.UserPopedomVO;
 
 

public class UserPopedomBO {
	
	public UserPopedomBO(){
		
	}
	private  final Logger log=Logger.getLogger(this.getClass());
	
	/**
	 * 初始化用户号，用户名，密码错误
	 * */
	public List init()throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		sb.append(" select userid,oname,convert(varchar(200),createtime,20)as createtime,b.uid from bs_ouser  left join (select uid from userpopedom group by uid) b on bs_ouser.userid=b.uid where UserID<>'sys' ");
		try{
			   engine=EngineFactory.getEngine("test");
			   Query query=engine.getQuery();
			   relist= query.getResults(sb.toString(), param.toArray(), UserPopedomVO.class);
			   engine.commit();
		}catch(Exception e){
			   engine.rollback();
			   log.error("--- 权限管理：初始化用户号，用户名，密码错误---", e);
			   throw e;
		}
		return relist;
	}
	
	/**
	 *  获取用户号后，判断相对应的权限
	 * */
	@SuppressWarnings("unchecked")
	public List showPage(String uid)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		sb.append(" select phref,pname,pertain_to from UserPopedom  left join  Popedom on UserPopedom.pid=Popedom.pid  where UserPopedom.uid= ? order by UserPopedom.pid ");
		param.add(uid);
		try{
			   engine=EngineFactory.getEngine("test");
			   Query query=engine.getQuery();
			   relist= query.getResults(sb.toString(), param.toArray(), UserPopedomVO.class);
			   engine.commit();
		}catch(Exception e){
			   engine.rollback();
			   log.error("--- 获取用户号后，判断权限错误---", e);
			   throw e;
		}
		return relist;
	}
	
	/**
	 *  获取所有的权限 
	 * */
	@SuppressWarnings("unchecked")
	public List allPopedom( )throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		sb.append("  select pid,pname from popedom order by pid");
		try{
			   engine=EngineFactory.getEngine("test");
			   Query query=engine.getQuery();
			   relist= query.getResults(sb.toString(), param.toArray(), UserPopedomVO.class);
			   engine.commit();
		}catch(Exception e){
			   engine.rollback();
			   log.error("--- 获取所有的权限错误---", e);
			   throw e;
		}
		return relist;
	}
	
	/**
	 *  增加权限
	 * */
	@SuppressWarnings("unchecked")
	public void savePopedom(String uid,String i)throws Exception{
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		sb.append("  insert into userpopedom(uid,pid)values(?,?) ");
		param.add(uid);
		param.add(i);	 
		try{
			   engine=EngineFactory.getEngine("test");
			   engine.executeSpecialSQL(sb.toString(),param.toArray());
			   engine.commit();
		}catch(Exception e){
			   engine.rollback();
			   log.error("--- 获取所有的权限错误---", e);
			   throw e;
		}
	}
	
	
	/**
	 *  修改权限
	 * */
	@SuppressWarnings("unchecked")
	public void updatePopedom(String uid,String pid)throws Exception{
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		sb.append(" insert into userpopedom values(?,?)");
		param.add(uid);
		param.add(pid);
		try{
			  engine=EngineFactory.getEngine("test");
			  engine.executeSpecialSQL(sb.toString(),param.toArray());
			  engine.commit();
		}catch(Exception e){
			   engine.rollback();
			   log.error("--- 修改权限错误---", e);
			   throw e;
		}
	}
	
	/**
	 *  删除表里面的用户
	 * */
	@SuppressWarnings("unchecked")
	public void deletePopedom(String uid)throws Exception{
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		sb.append(" delete from userpopedom where uid= ? ");
		param.add(uid);
		try{
			  engine=EngineFactory.getEngine("test");
			  engine.executeSpecialSQL(sb.toString(),param.toArray());
			  engine.commit();
		}catch(Exception e){
			   engine.rollback();
			   log.error("---删除用户错误---", e);
			   throw e;
		}
	}
	
	/**
	 *  根据用户名，从数据库里面获取对应的pid 
	 * */
	@SuppressWarnings("unchecked")
	public List checkPopedom(String uid )throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		sb.append("  select pid from userpopedom where uid= ? order by pid");
		param.add(uid);
		try{
			   engine=EngineFactory.getEngine("test");
			   Query query=engine.getQuery();
			   relist= query.getResults(sb.toString(), param.toArray(), UserPopedomVO.class);
			   engine.commit();
		}catch(Exception e){
			   engine.rollback();
			   log.error("--- 根据用户名,获取权限错误---", e);
			   throw e;
		}
		return relist;
	}
}
