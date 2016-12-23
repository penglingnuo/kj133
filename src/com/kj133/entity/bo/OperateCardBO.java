package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Recog;
import com.kj133.entity.Search_eidtCard;
import com.kj133.entity.vo.EditCardVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.entity.vo.VdataVO;

public class OperateCardBO {
	
	private final Logger log=Logger.getLogger(this.getClass());
	/**
	 * 保存
	 */
	 public void save(Recog recog)throws Exception{
		 Engine engine=null;
		 String sql=" insert into recog(CardID,CardMode,CardState,RegDate)values (?,?,?,getdate())";
		 String cid=recog.getCardid();  
		 String cardmode=recog.getCardmode();  
		 String cardstate=recog.getCardstate(); 
		 try{
			 engine=EngineFactory.getEngine("test");
			 engine.executeSpecialSQL(sql,new Object[]{cid,cardmode,cardstate});
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
	  * 保存
	  */
	 public void savelx(Recog recog,String id)throws Exception{
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
//		 String sql=" insert into recog(CardID,CardMode,CardState,RegDate)values (?,?,?,getdate())";
		 String cid=recog.getCardid();  
		 String cidmin=recog.getMincardid();  
		 String cidmax=recog.getMaxcardid();  
		 String cardmode=recog.getCardmode();  
		 String cardstate=recog.getCardstate(); 
		 /*int a = Integer.valueOf(cidmin);
		 int b = Integer.valueOf(cidmax);
		 for(int i=a;i<=b;i++){
			 String bb= String.valueOf(i);
			 //第一种方法：
			 if (bb.indexOf("4") == -1) {
//				 System.out.println(bb);
				 sb.append(" insert into recog(CardID,CardMode,CardState,RegDate)values (?,?,?,getdate()) ");
				 param.add(bb);
				 param.add(cardmode);
				 param.add(cardstate);
			 }
			 //第二种方法：
//			 if(bb.contains("4")){
//				 
//			 }else{
//				 System.out.println(bb);
//			 }
			 
		 }*/
		 sb.append(" insert into recog(CardID,CardMode,CardState,RegDate)values (?,?,?,getdate()) ");
		 param.add(id);
		 param.add(cardmode);
		 param.add(cardstate);
		 try{
			 engine=EngineFactory.getEngine("test");
//			 engine.executeSpecialSQL(sql,new Object[]{cid,cardmode,cardstate});
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
		 * 查询历史记录中是否有该卡
		 */
	 public List ifcard(String cid)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select top 1 cardid from v_locatedata where cardid=? ");
		 param.add(cid);
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
             relist=query.getResults(sb.toString(),param.toArray(),EditCardVO.class);
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
		 * 删除指定的id
		 * 
		 */
	 @SuppressWarnings("unchecked")
	public void delete(String id)throws Exception{
		   Engine engine=null;
		   List param=new ArrayList();
		   StringBuffer sb=new StringBuffer();
// sb.append(" select top 1 cardid from v_locatedata where cardid='6' ");
		   sb.append(" delete from recog where 1=1 and cardid= ? ");
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
	 }

	 /**
		 * 提取单条数据
		 */
	 public Recog load(String id)throws Exception{
		 Engine engine=null;
		 Recog recog=null;
		 try{
			 engine=EngineFactory.getEngine("test");
			 recog=(Recog)engine.load(Recog.class,id);
			 engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return recog;
	 }
	 
	 
	 /**
		 * update
		 */
	 public void executeSpecoalSQL(String sql,Object[] obj)throws Exception{
		 Engine engine=null;
		 try{
			 engine=EngineFactory.getEngine("test");
			 engine.executeSpecialSQL(sql,obj);
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
