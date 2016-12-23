package com.kj133.entity.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.CardReader;
import com.kj133.entity.CardReader1;
import com.kj133.entity.Locator;
import com.kj133.entity.Search_OperatorViewReaderForm;
import com.kj133.entity.vo.EditCardVO;
public class OperartrViewReaderBO {

	 /**
     * 编辑分站
     * save
     * */
	 private Logger log=Logger.getLogger(this.getClass());
    
	 @SuppressWarnings("unchecked")
	public void save( Search_OperatorViewReaderForm oper)throws Exception{
  	    Engine engine=null;
  	    List param=new ArrayList();
  	    StringBuffer sb=new StringBuffer();
  	    sb.append(" insert into cardreader(CardReaderId,CRName,ShortName,x,y,Mapid,ignoretimes,ignorelocator,locatorignoretimes, state,ground,checkreader,useantenna,gasIgnore,webx,weby,regdate) values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,getdate() ) ");
  	    param.add(oper.getCardreaderid());
  	    param.add(oper.getCrname());
  	    param.add(oper.getShortname());
	  	param.add(oper.getX());
	  	param.add(oper.getY());
	  	param.add(oper.getMapid());
	  	param.add(oper.getIgnoretimes());
	  	param.add(oper.getIgnorelocator());
	  	param.add(oper.getLocatorignoretimes());
	  	param.add(oper.getState());
	  	param.add(oper.getGround());
	  	param.add(oper.getCheckreader());
	  	param.add(oper.getGasIgnore());
	  	param.add(oper.getX());
	  	param.add(oper.getY());
		int j=0;  //checkbox
	  	String[] count=oper.getUseantenna();
	  	for(int i=0;i<count.length;i++){
	  	     j=j+Integer.parseInt(count[i]);
	  	  }
	    String usee=String.valueOf(j);
	    param.add(usee);
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
		 * 查询历史记录中是否有该分站记录
		 */
	 public List ifcard(String cid)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select top 1 cardreaderid from v_locatedata where cardreaderid=? ");
		 param.add(cid);
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
          relist=query.getResults(sb.toString(),param.toArray(),CardReader.class);
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
      * 编辑分站
      * delete
      * */
       @SuppressWarnings("unchecked")
       public void delete(String id)throws Exception{
    	 Engine engine=null;
    	 CardReader cardreader=null;
    	 try{
    		 engine=EngineFactory.getEngine("test");
    		 cardreader=(CardReader)engine.load(CardReader.class,id);
    		 engine.delete(cardreader);
    		 engine.commit();
    	 }catch(Exception e){
    		 engine.rollback();
    		 log.error(e);
    		 throw e;
    	 }finally{
    		 engine.closeEngine();
    	 }
      }
/*	public void delete(String id)throws Exception{
     	  Engine engine=null;
     	  List param=new ArrayList();
     	  StringBuffer sb=new StringBuffer();
     	  sb.append(" delete from cardreader where  cardreaderid = ? ");
     	  param.add(id);
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
       }*/
       /**
        * 编辑分站
        * 提取单条数据
        * */
       
       public CardReader load(String id)throws Exception{
     	  CardReader card=null;
     	  Engine engine=null;
     	  try{
     		  engine=EngineFactory.getEngine("test");
     		  card=(CardReader)engine.load(CardReader.class,id);
     	      engine.commit();
     	  }catch(Exception e){
     		  engine.rollback();
     		  log.error(e);
     		  throw e;
     	  }finally{
     		  engine.commit();
     	  }
     	  return card;
       }
       /**
        * 修改分站
        * 
        * */
        @SuppressWarnings("unchecked")
		public void update(Search_OperatorViewReaderForm reader,String cc)throws Exception {
       	 Engine engine=null;
       	 List param=new ArrayList();
       	 StringBuffer sb=new StringBuffer();
       	 sb.append(" update cardreader set  CardReaderId = ? ,CRName= ? , ShortName= ? ,x= ? ,y= ? ,Mapid= ? ,ignoretimes= ? ,  ");
		 sb.append(" ignorelocator= ? ,locatorignoretimes= ? ,state= ? ,modifydate= ? ,ground= ? ,checkreader= ? ,useantenna= ?,ifalarm= ?,ifupdate=?,readercode= ?,z= ? where CardReaderId = ? ");
       	 param.add(reader.getCardreaderid());
    	 param.add(reader.getCrname());
    	 param.add(reader.getShortname());
    	 param.add(reader.getX());
    	 param.add(reader.getY());
    	 param.add(reader.getMapid());
    	 param.add(reader.getIgnoretimes());
    	 param.add(reader.getIgnorelocator());
    	 param.add(reader.getLocatorignoretimes());
    	 param.add(reader.getState());
    	 
    	 Calendar cal=Calendar.getInstance();//获取系统时间
		 SimpleDateFormat formatter=new  SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		 String time=formatter.format(cal.getTime());
    	 param.add(time);//修改时间
    	 param.add(reader.getGround());
    	 param.add(reader.getCheckreader());
    	    int j=0;  //checkbox
    	  	String[] count=reader.getUseantenna();
    	  	for(int i=0;i<count.length;i++){
    	  	     j=j+Integer.parseInt(count[i]); 
    	  	  }
    	  	String usea=String.valueOf(j);
    	  param.add(usea);
    	  param.add(reader.getIfalarm());
    	  param.add(reader.getIfupdate());
    	  param.add(reader.getReadercode());
    	  param.add(reader.getZ());
    	 param.add(cc);
//    	 param.add(reader.getRecordid());
//    	 System.out.println(cc+"............");
//    	 System.out.println(reader.getCrname());
//    	 System.out.println(reader.getShortname());
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
