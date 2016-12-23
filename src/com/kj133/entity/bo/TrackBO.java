package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.vo.TrackVO;

public class TrackBO {
	  private Logger log=Logger.getLogger(this.getClass());
      
	  @SuppressWarnings("unchecked")
	  /**  ���֣����ţ�����������*/
	public List getStaffer( )throws Exception{
    	   Engine  engine=null;
    	   List list=null;
    	   List param=new ArrayList();
    	   StringBuffer sb=new StringBuffer();
    	   sb.append(" select [id],isnull(worktype,'����') as 'worktype',cardid as 'cardid',isnull([name],'δ��') as 'username',isnull([group],'δ��') as 'gro' from socket_staffer a left join staffer b on a.[id]=b.cardid  order by [id]  ");
    	   try{
    		   engine=EngineFactory.getEngine("test");
    		   Query query=engine.getQuery();
    		   list=query.getResults(sb.toString(),param.toArray(), TrackVO.class);
    		   
    		   engine.commit();
    	   }catch(Exception e){
    		   engine.rollback();
 			   log.error(e);
 			   throw e;
    	   }finally{
    		   engine.closeEngine();
    	   }
    	 return list;
      }
	 
	  /** ������վ����վ��� */
	  @SuppressWarnings("unchecked")
	public List getCardReader()throws Exception{
		   Engine engine=null;
		   List relist=null;
		   List param=new ArrayList();
		   StringBuffer sb=new StringBuffer();
		   sb.append(" select  id,shortname  from socket_cardreader left join   cardreader on socket_cardreader.id=cardreader.cardreaderid order by socket_cardreader.id");
		   try{
			    engine=EngineFactory.getEngine("test");
			    Query  query=engine.getQuery();
		        relist=query.getResults(sb.toString(), param.toArray(), TrackVO.class);
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
	  
	  /** ������λ������λ����� */
	  @SuppressWarnings("unchecked")
	public List getLocator()throws Exception{
		   Engine engine=null;
		   List relist=null;
		   List param=new ArrayList();
		   StringBuffer sb=new StringBuffer();
		   sb.append(" select sl.id,locatorid,shortname from socket_locator sl left join locator  lo on sl.id= lo.locatorid ");
		   try{
			    engine=EngineFactory.getEngine("test");
			    Query  query=engine.getQuery();
		        relist=query.getResults(sb.toString(), param.toArray(), TrackVO.class);
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
	  
	    /** ��״̬ */
		public List getState()throws Exception{
			   Engine engine=null;
			   List relist=null;
			   List param=new ArrayList();
			   StringBuffer sb=new StringBuffer();
			   sb.append(" select state,info from alarmInfo ");
			   try{
				    engine=EngineFactory.getEngine("test");
				    Query  query=engine.getQuery();
			        relist=query.getResults(sb.toString(), param.toArray(), TrackVO.class);
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
/**
 * Socket����   

 * ��������һֱ�ڷ������ݣ����ͻ�����һֱ�������ݣ���������ʾ��ҳ��ķ�����
 * �ѽ��յ������ݷŵ��Ը����캯�����棬Ȼ��ת����List��Ȼ����ʾ��ҳ����
 * �����û�յ��Ը����ݣ��������ݿ��������һ�Σ������Ļ��������ݿ�ķ��ʾ�
 * ̫Ƶ���������뵽�Ľ���취���ǣ�����д�Ը�sql��䣬�������Щ�̶�����Ϣ��
 * ��Ϊ�����Ǵ�1024��ʼ�ģ����Կ��������ݿ����洴��һ��test_staffer��ֻ��
 * һ��id,�Ǵ�0��ʼ�ģ������Ļ����������յ���������1055����ô�Ҿ�ֱ��д�Ը�
 * �����ӣ�����list.get(1055)��ʱ����Ҳ����ֱ�ӻ�ȡ���� 1055���Ӧ�����ݡ�
 */ 