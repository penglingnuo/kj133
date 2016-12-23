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
	  /**  工种，卡号，姓名，班组*/
	public List getStaffer( )throws Exception{
    	   Engine  engine=null;
    	   List list=null;
    	   List param=new ArrayList();
    	   StringBuffer sb=new StringBuffer();
    	   sb.append(" select [id],isnull(worktype,'其他') as 'worktype',cardid as 'cardid',isnull([name],'未绑定') as 'username',isnull([group],'未绑定') as 'gro' from socket_staffer a left join staffer b on a.[id]=b.cardid  order by [id]  ");
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
	 
	  /** 所属分站，分站简称 */
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
	  
	  /** 所属定位器，定位器简称 */
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
	  
	    /** 卡状态 */
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
 * Socket分析   

 * 服务器端一直在发送数据，而客户端则一直在收数据，把数据显示在页面的方法是
 * 把接收到的数据放到以个构造函数里面，然后转换成List，然后显示到页面上
 * 如果我没收到以个数据，就在数据库里面读，一次，那样的话，对数据库的访问就
 * 太频繁。现在想到的解决办法就是，首先写以个sql语句，来输出以些固定的信息，
 * 因为卡号是从1024开始的，所以可以在数据库里面创建一个test_staffer表，只有
 * 一列id,是从0开始的，这样的话，假如我收到的数据是1055，那么我就直接写以个
 * 左连接，当我list.get(1055)的时候，那也就是直接获取到了 1055想对应的数据。
 */ 