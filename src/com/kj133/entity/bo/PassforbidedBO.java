package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import com.kj133.entity.Passforbided;
import com.kj133.entity.Search_TransitTrctic;
import com.kj133.entity.vo.OperationNumberVO;


public class PassforbidedBO {
	private Logger log=Logger.getLogger(this.getClass());
	public PassforbidedBO(){
		
	}
	
	
	/**
	 * 
	 * @return 通行策略的标题
	 * @throws Exception
	 */
	public List getCaption() throws Exception {
  	  Engine engine=null;
  	  List relist=null;
  	  List param=new ArrayList();
  	  StringBuffer sb=new StringBuffer();
  	  sb.append(" select distinct  caption,operationNumber from passforbided  ");
  	  try{
  		   engine=EngineFactory.getEngine("test");
  		   Query query=engine.getQuery();
  		   relist=query.getResults(sb.toString(),param.toArray(), Passforbided.class);
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
	
	public List getOperationnumber() throws Exception {
	  	  Engine engine=null;
	  	  List relist=null;
	  	  List param=new ArrayList();
	  	  StringBuffer sb=new StringBuffer();
	  	  sb.append(" select isnull(max(operationnumber),0)+1 as operationnumber  from passforbided with(tablockx) ");
	  	  try{
	  		   engine=EngineFactory.getEngine("test");
	  		   Query query=engine.getQuery();
	  		   relist=query.getResults(sb.toString(),param.toArray(), Passforbided.class);
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
	 *  
	 * @param OperationNumber
	 * @return  根据OperationNumber得到详细信息
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getInfo(String OperationNumber) throws Exception {
	  	  Engine engine=null;
	  	  List relist=null;
	  	  List param=new ArrayList();
	  	  StringBuffer sb=new StringBuffer();
//	  	  sb.append("   select * from (select case CardID when 0 then '所有卡' else convert(varchar,CardID) end as cardid, ");
//	  	  sb.append("   a.CardReaderId as cardreaderid,b.shortname as shortname,c.locatorid as locatorid,c.lname as lname ,case Pass when 0 then '允许' else '禁止' ");
//	  	  sb.append("   end as alloworestop,case PassMode when 0 then '不限时间' when 1 then '仅指定时间' else '指定时间和日期'  ");
//	  	  sb.append("   end as limit,case PassMode when 2 then substring(convert(char,starttime,120),1,10) else ");
//	  	  sb.append("   NULL end as sriqi,case PassMode when 0 then NULL else convert(char(8),starttime,8) end  ");
//	  	  sb.append("   as stime,case PassMode when 2 then substring(convert(char,endtime,120),1,10) else NULL ");
//	  	  sb.append("   end as eriqi,case PassMode when 0 then NULL else convert(char(8),endtime,8) end as  ");
//	  	  sb.append("   etime,OperationNumber  as   operationnumber from passforbided as a,cardreader as b ,locator c where a.cardreaderid=b.cardreaderid ");
//	  	  sb.append("   and a.locatorid=c.locatorid ) as QueryTable  where OperationNumber=? ");
	  	  sb.append(" select * from (select case CardID when 0 then '所有卡' else convert(varchar,CardID) end as cardid,a.CardReaderId as cardreaderid,b.shortname as shortname,case Pass when 0 then '允许' else '禁止' end as alloworestop,case PassMode when 0 then '不限时间' when 1 then '仅指定时间' else '指定时间和日期' end as limit,case PassMode when 2 then substring(convert(char,starttime,120),1,10) else NULL end as sriqi,case PassMode when 0 then NULL else convert(char(8),starttime,8) end as stime,case PassMode when 2 then substring(convert(char,endtime,120),1,10) else NULL end as eriqi,case PassMode when 0 then NULL else convert(char(8),endtime,8) end as etime,OperationNumber from passforbided as a,cardreader as b where a.cardreaderid=b.cardreaderid ");
	  	  sb.append(" ) as QueryTable  where operationnumber=? ");
	  	  param.add(OperationNumber);
	  	  try{
	  		   engine=EngineFactory.getEngine("test");
	  		   Query query=engine.getQuery();
	  		   relist=query.getResults(sb.toString(),param.toArray(), OperationNumberVO.class);
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
	 *  
	 * @param OperationNumber
	 * @return  得到详细信息
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getshow() throws Exception {
		Engine engine=null;
		List relist=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from (select case CardID when 0 then '所有卡' else convert(varchar,CardID) end as cardid,a.CardReaderId as cardreaderid,b.shortname as shortname,case Pass when 0 then '允许' else '禁止' end as alloworestop,case PassMode when 0 then '不限时间' when 1 then '仅指定时间' else '指定时间和日期' end as limit,case PassMode when 2 then substring(convert(char,starttime,120),1,10) else NULL end as sriqi,case PassMode when 0 then NULL else convert(char(8),starttime,8) end as stime,case PassMode when 2 then substring(convert(char,endtime,120),1,10) else NULL end as eriqi,case PassMode when 0 then NULL else convert(char(8),endtime,8) end as etime,OperationNumber from passforbided as a,cardreader as b where a.cardreaderid=b.cardreaderid ");
		sb.append(" ) as QueryTable ");
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(), OperationNumberVO.class);
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
	 * 
	 * @param init
	 * @return  svae到数据库
	 * @throws Exception
	 */
 
		 @SuppressWarnings("unchecked")
		public void save(Search_TransitTrctic init,String number)throws Exception{
	  	    Engine engine=null;
	  	    List param=new ArrayList();
	  	    StringBuffer sb=new StringBuffer();
	  	    String cap=init.getCaption();
	  	    String pass=init.getPass();
	  	    String passmode=init.getPassmode();
	  	    String stime=init.getStime();
	  	    String etime=init.getEtime();

	  	    String[] cx=init.getCardreaderx();//分站号(分站号和员工号必填)
	  	    for(int i=0;i<cx.length;i++){
	  	    	sb.append(" delete from passforbided where    cardreaderid = ? ");//删除原来的分站设置
	  	        param.add(cx[i]);
	  	    	String[] sx=init.getStafferx();//卡号
		  	    for(int k=0;k<sx.length;k++){
		  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber) values (?,?,?,?,?,?,?,?) ");
//		  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
		  	    	 param.add(cap);
		  	    	 param.add(sx[k]);//卡号
		  	    	 param.add(cx[i]);//分站号
		  	    	 param.add(pass);
		  	    	 param.add(passmode);
		  	    	 param.add(stime);
		  	    	 param.add(etime);
		  	    	 param.add(number);
//		  	    	 param.add(0);
		  	    }  
//		  	    String[] lx=init.getLocatorx();//定位器号
//		  	    if(lx.length>0){
//		  	       for(int j=0;j<lx.length;j++){
//		  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
//		  	    	 param.add(cap);
//		  	    	 param.add(sx[i]);//卡号
//		  	    	 param.add(0);//定位器号
//		  	    	 param.add(pass);
//		  	    	 param.add(passmode);
//		  	    	 param.add(stime);
//		  	    	 param.add(etime);
//		  	    	 param.add(number);
//		  	    	 param.add(lx[j]);
//			  	    }
//		  	    } 
	  	    }

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
		  * 
		  * @param init
		  * @return  svae到数据库
		  * @throws Exception
		  */
		 
		 @SuppressWarnings("unchecked")
		 public void save1(Search_TransitTrctic init,String number)throws Exception{
			 Engine engine=null;
			 List param=new ArrayList();
			 StringBuffer sb=new StringBuffer();
			 String cap=init.getCaption();
			 String pass=init.getPass();
			 String passmode=init.getPassmode();
			 String stime=init.getStime();
			 String etime=init.getEtime();
			 
			 String[] cx=init.getCardreaderx();//分站号(分站号和员工号必填)
			 for(int i=0;i<cx.length;i++){
				 sb.append(" delete from passforbided where    cardreaderid = ? ");//删除原来的分站设置
				 param.add(cx[i]);
				 String[] sx=init.getStafferx();//卡号
				 
					 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber) values (?,?,?,?,?,?,?,?) ");
//		  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
					 param.add(cap);
					 param.add(0);//卡号
					 param.add(cx[i]);//分站号
					 param.add(pass);
					 param.add(passmode);
					 param.add(stime);
					 param.add(etime);
					 param.add(number);
//		  	    	 param.add(0);
				 
//		  	    String[] lx=init.getLocatorx();//定位器号
//		  	    if(lx.length>0){
//		  	       for(int j=0;j<lx.length;j++){
//		  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
//		  	    	 param.add(cap);
//		  	    	 param.add(sx[i]);//卡号
//		  	    	 param.add(0);//定位器号
//		  	    	 param.add(pass);
//		  	    	 param.add(passmode);
//		  	    	 param.add(stime);
//		  	    	 param.add(etime);
//		  	    	 param.add(number);
//		  	    	 param.add(lx[j]);
//			  	    }
//		  	    } 
			 }
			 
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
		  * 
		  * @param init
		  * @return  svae到数据库
		  * @throws Exception
		  */
		 
		 @SuppressWarnings("unchecked")
		 public void update(Search_TransitTrctic init,String number,String checkall)throws Exception{
			 Engine engine=null;
			 List param=new ArrayList();
			 StringBuffer sb=new StringBuffer();
			 String cap=init.getCaption();
			 String pass=init.getPass();
			 String passmode=init.getPassmode();
			 String stime=init.getStime();
			 String etime=init.getEtime();
			 
			 
			 
				 String[] cx=init.getCardreaderx();//分站号(分站号和员工号必填)
				 for(int i=0;i<cx.length;i++){
					 sb.append(" delete from passforbided where  cardreaderid = ? ");//删除原来的分站设置
					 param.add(cx[i]);
					 String[] sx=init.getStafferx();//卡号
					 for(int k=0;k<sx.length;k++){
						 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber) values (?,?,?,?,?,?,?,?) ");
//			  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
						 param.add(cap);
						 param.add(sx[k]);//卡号
						 param.add(cx[i]);//分站号
						 param.add(pass);
						 param.add(passmode);
						 param.add(stime);
						 param.add(etime);
						 param.add(number);
//			  	    	 param.add(0);
					 }  
//			  	    String[] lx=init.getLocatorx();//定位器号
//			  	    if(lx.length>0){
//			  	       for(int j=0;j<lx.length;j++){
//			  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
//			  	    	 param.add(cap);
//			  	    	 param.add(sx[i]);//卡号
//			  	    	 param.add(0);//定位器号
//			  	    	 param.add(pass);
//			  	    	 param.add(passmode);
//			  	    	 param.add(stime);
//			  	    	 param.add(etime);
//			  	    	 param.add(number);
//			  	    	 param.add(lx[j]);
//				  	    }
//			  	    } 
				 }
			
			 
			 
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
		  * 
		  * @param init
		  * @return  svae到数据库
		  * @throws Exception
		  */
		 
		 @SuppressWarnings("unchecked")
		 public void update1(Search_TransitTrctic init,String number,String checkall)throws Exception{
			 Engine engine=null;
			 List param=new ArrayList();
			 StringBuffer sb=new StringBuffer();
			 String cap=init.getCaption();
			 String pass=init.getPass();
			 String passmode=init.getPassmode();
			 String stime=init.getStime();
			 String etime=init.getEtime();
			 
			 
			 String[] cx=init.getCardreaderx();//分站号(分站号和员工号必填)
			 for(int i=0;i<cx.length;i++){
				 sb.append(" delete from passforbided where    cardreaderid = ? ");//删除原来的分站设置
				 param.add(cx[i]);
				 String[] sx=init.getStafferx();//卡号
				 
				 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber) values (?,?,?,?,?,?,?,?) ");
//			  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
				 param.add(cap);
				 param.add(0);//卡号
				 param.add(cx[i]);//分站号
				 param.add(pass);
				 param.add(passmode);
				 param.add(stime);
				 param.add(etime);
				 param.add(number);
//			  	    	 param.add(0);
				 
			 }
			 
			 
			 
			 
			 
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
		  * 
		  * @param id
		  * @throws Exception 
		  */
		 @SuppressWarnings("unchecked")
		public void delete(String id)throws Exception{
			   Engine engine=null;
			   List param=new ArrayList();
			   StringBuffer sb=new StringBuffer();
			   sb.append(" delete from passforbided where OperationNumber= ? ");
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
		 
		 
}
