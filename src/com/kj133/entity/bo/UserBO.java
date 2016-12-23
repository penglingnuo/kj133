package com.kj133.entity.bo;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.Ouser;
import com.kj133.util.encrypt;

public class UserBO {
	
	private Logger log=Logger.getLogger(this.getClass());
	public UserBO(){
	}
	
	/**
	*用户登陆
	*/
	@SuppressWarnings("unchecked")
	public Ouser getLogin(Ouser o,String name) throws Exception{
		
		Ouser user=null;
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		List param=new ArrayList();
		sb.append("select userID,oPassWord,oname from BS_OUser where userID= ? and  oPassWord = ? "); 
		param.add(o.getUserid());
		param.add(o.getOpassword());
		try{
			  engine=EngineFactory.getEngine("test");
		      Query query=engine.getQuery();
			  List list=query.getResults(sb.toString(),param.toArray(),Ouser.class);
			     if( list!=null && list.size()>0) 
				  {
			    	 
				     user = (Ouser) list.get(0);//把值给user
				  }else{
				 
				  }
		  }catch(Exception e){
			  engine.rollback();
			  log.error(e);
			  throw e;
		  }finally{
			  engine.closeEngine();
		  }
		  return user;
	}
	public Ouser getLog(Ouser o) throws Exception{

		Ouser user=null;
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		List param=new ArrayList();
		sb.append("select oname from BS_OUser where userID= ?"); 
		param.add(o.getUserid());
		
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			List list=query.getResults(sb.toString(),param.toArray(),Ouser.class);
			if( list!=null && list.size()>0) 
			{
				user = (Ouser) list.get(0);//把值给user
				
			}else{
				
			}
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;
		}finally{
			engine.closeEngine();
		}
		return user;
	}
	
	
	@SuppressWarnings("unchecked")
	public List userLogin(Ouser o) throws Exception{
		Engine engine=null;
		List relist=null;
		StringBuffer sb=new StringBuffer();
		List param=new ArrayList();
		sb.append("select userID,oPassWord from BS_OUser where userID= ? and  oPassWord = ? "); 
		param.add(o.getUserid());
		param.add(o.getOpassword());
//		System.out.println(sb.toString());
		try{
			  engine=EngineFactory.getEngine("test");
		      Query query=engine.getQuery();
		      relist=query.getResults(sb.toString(),param.toArray(),Ouser.class);
		      engine.commit(); 
		}catch(Exception e){
			  engine.rollback();
			  log.error(e);
			  throw e;
		  }finally{
			  engine.closeEngine();
		  }
		  return relist;
	}
	
	
	
	/**
	 * 修改密码
	 * */
      @SuppressWarnings("unchecked")
	public List updatePassword(String newPassword,String userid) throws Exception {
    	  Engine engine=null;
    	  List relist=null;
    	  List param=new ArrayList();
    	  StringBuffer sb=new StringBuffer();
    	  sb.append("update BS_OUser set opassword= ? where userid= ? ");
    	  param.add(newPassword);
    	  param.add(userid);
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
		  return relist;
      }
}
