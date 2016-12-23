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
	 * ��ʼ���û��ţ��û������������
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
			   log.error("--- Ȩ�޹�����ʼ���û��ţ��û������������---", e);
			   throw e;
		}
		return relist;
	}
	
	/**
	 *  ��ȡ�û��ź��ж����Ӧ��Ȩ��
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
			   log.error("--- ��ȡ�û��ź��ж�Ȩ�޴���---", e);
			   throw e;
		}
		return relist;
	}
	
	/**
	 *  ��ȡ���е�Ȩ�� 
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
			   log.error("--- ��ȡ���е�Ȩ�޴���---", e);
			   throw e;
		}
		return relist;
	}
	
	/**
	 *  ����Ȩ��
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
			   log.error("--- ��ȡ���е�Ȩ�޴���---", e);
			   throw e;
		}
	}
	
	
	/**
	 *  �޸�Ȩ��
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
			   log.error("--- �޸�Ȩ�޴���---", e);
			   throw e;
		}
	}
	
	/**
	 *  ɾ����������û�
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
			   log.error("---ɾ���û�����---", e);
			   throw e;
		}
	}
	
	/**
	 *  �����û����������ݿ������ȡ��Ӧ��pid 
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
			   log.error("--- �����û���,��ȡȨ�޴���---", e);
			   throw e;
		}
		return relist;
	}
}
