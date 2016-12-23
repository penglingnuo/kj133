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
	 * @return ͨ�в��Եı���
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
	 * @return  ����OperationNumber�õ���ϸ��Ϣ
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getInfo(String OperationNumber) throws Exception {
	  	  Engine engine=null;
	  	  List relist=null;
	  	  List param=new ArrayList();
	  	  StringBuffer sb=new StringBuffer();
//	  	  sb.append("   select * from (select case CardID when 0 then '���п�' else convert(varchar,CardID) end as cardid, ");
//	  	  sb.append("   a.CardReaderId as cardreaderid,b.shortname as shortname,c.locatorid as locatorid,c.lname as lname ,case Pass when 0 then '����' else '��ֹ' ");
//	  	  sb.append("   end as alloworestop,case PassMode when 0 then '����ʱ��' when 1 then '��ָ��ʱ��' else 'ָ��ʱ�������'  ");
//	  	  sb.append("   end as limit,case PassMode when 2 then substring(convert(char,starttime,120),1,10) else ");
//	  	  sb.append("   NULL end as sriqi,case PassMode when 0 then NULL else convert(char(8),starttime,8) end  ");
//	  	  sb.append("   as stime,case PassMode when 2 then substring(convert(char,endtime,120),1,10) else NULL ");
//	  	  sb.append("   end as eriqi,case PassMode when 0 then NULL else convert(char(8),endtime,8) end as  ");
//	  	  sb.append("   etime,OperationNumber  as   operationnumber from passforbided as a,cardreader as b ,locator c where a.cardreaderid=b.cardreaderid ");
//	  	  sb.append("   and a.locatorid=c.locatorid ) as QueryTable  where OperationNumber=? ");
	  	  sb.append(" select * from (select case CardID when 0 then '���п�' else convert(varchar,CardID) end as cardid,a.CardReaderId as cardreaderid,b.shortname as shortname,case Pass when 0 then '����' else '��ֹ' end as alloworestop,case PassMode when 0 then '����ʱ��' when 1 then '��ָ��ʱ��' else 'ָ��ʱ�������' end as limit,case PassMode when 2 then substring(convert(char,starttime,120),1,10) else NULL end as sriqi,case PassMode when 0 then NULL else convert(char(8),starttime,8) end as stime,case PassMode when 2 then substring(convert(char,endtime,120),1,10) else NULL end as eriqi,case PassMode when 0 then NULL else convert(char(8),endtime,8) end as etime,OperationNumber from passforbided as a,cardreader as b where a.cardreaderid=b.cardreaderid ");
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
	 * @return  �õ���ϸ��Ϣ
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getshow() throws Exception {
		Engine engine=null;
		List relist=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from (select case CardID when 0 then '���п�' else convert(varchar,CardID) end as cardid,a.CardReaderId as cardreaderid,b.shortname as shortname,case Pass when 0 then '����' else '��ֹ' end as alloworestop,case PassMode when 0 then '����ʱ��' when 1 then '��ָ��ʱ��' else 'ָ��ʱ�������' end as limit,case PassMode when 2 then substring(convert(char,starttime,120),1,10) else NULL end as sriqi,case PassMode when 0 then NULL else convert(char(8),starttime,8) end as stime,case PassMode when 2 then substring(convert(char,endtime,120),1,10) else NULL end as eriqi,case PassMode when 0 then NULL else convert(char(8),endtime,8) end as etime,OperationNumber from passforbided as a,cardreader as b where a.cardreaderid=b.cardreaderid ");
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
	 * @return  svae�����ݿ�
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

	  	    String[] cx=init.getCardreaderx();//��վ��(��վ�ź�Ա���ű���)
	  	    for(int i=0;i<cx.length;i++){
	  	    	sb.append(" delete from passforbided where    cardreaderid = ? ");//ɾ��ԭ���ķ�վ����
	  	        param.add(cx[i]);
	  	    	String[] sx=init.getStafferx();//����
		  	    for(int k=0;k<sx.length;k++){
		  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber) values (?,?,?,?,?,?,?,?) ");
//		  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
		  	    	 param.add(cap);
		  	    	 param.add(sx[k]);//����
		  	    	 param.add(cx[i]);//��վ��
		  	    	 param.add(pass);
		  	    	 param.add(passmode);
		  	    	 param.add(stime);
		  	    	 param.add(etime);
		  	    	 param.add(number);
//		  	    	 param.add(0);
		  	    }  
//		  	    String[] lx=init.getLocatorx();//��λ����
//		  	    if(lx.length>0){
//		  	       for(int j=0;j<lx.length;j++){
//		  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
//		  	    	 param.add(cap);
//		  	    	 param.add(sx[i]);//����
//		  	    	 param.add(0);//��λ����
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
		  * @return  svae�����ݿ�
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
			 
			 String[] cx=init.getCardreaderx();//��վ��(��վ�ź�Ա���ű���)
			 for(int i=0;i<cx.length;i++){
				 sb.append(" delete from passforbided where    cardreaderid = ? ");//ɾ��ԭ���ķ�վ����
				 param.add(cx[i]);
				 String[] sx=init.getStafferx();//����
				 
					 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber) values (?,?,?,?,?,?,?,?) ");
//		  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
					 param.add(cap);
					 param.add(0);//����
					 param.add(cx[i]);//��վ��
					 param.add(pass);
					 param.add(passmode);
					 param.add(stime);
					 param.add(etime);
					 param.add(number);
//		  	    	 param.add(0);
				 
//		  	    String[] lx=init.getLocatorx();//��λ����
//		  	    if(lx.length>0){
//		  	       for(int j=0;j<lx.length;j++){
//		  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
//		  	    	 param.add(cap);
//		  	    	 param.add(sx[i]);//����
//		  	    	 param.add(0);//��λ����
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
		  * @return  svae�����ݿ�
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
			 
			 
			 
				 String[] cx=init.getCardreaderx();//��վ��(��վ�ź�Ա���ű���)
				 for(int i=0;i<cx.length;i++){
					 sb.append(" delete from passforbided where  cardreaderid = ? ");//ɾ��ԭ���ķ�վ����
					 param.add(cx[i]);
					 String[] sx=init.getStafferx();//����
					 for(int k=0;k<sx.length;k++){
						 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber) values (?,?,?,?,?,?,?,?) ");
//			  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
						 param.add(cap);
						 param.add(sx[k]);//����
						 param.add(cx[i]);//��վ��
						 param.add(pass);
						 param.add(passmode);
						 param.add(stime);
						 param.add(etime);
						 param.add(number);
//			  	    	 param.add(0);
					 }  
//			  	    String[] lx=init.getLocatorx();//��λ����
//			  	    if(lx.length>0){
//			  	       for(int j=0;j<lx.length;j++){
//			  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
//			  	    	 param.add(cap);
//			  	    	 param.add(sx[i]);//����
//			  	    	 param.add(0);//��λ����
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
		  * @return  svae�����ݿ�
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
			 
			 
			 String[] cx=init.getCardreaderx();//��վ��(��վ�ź�Ա���ű���)
			 for(int i=0;i<cx.length;i++){
				 sb.append(" delete from passforbided where    cardreaderid = ? ");//ɾ��ԭ���ķ�վ����
				 param.add(cx[i]);
				 String[] sx=init.getStafferx();//����
				 
				 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber) values (?,?,?,?,?,?,?,?) ");
//			  	    	 sb.append(" insert into passforbided(caption,cardid,cardreaderid,pass,passmode,starttime,endtime,OperationNumber,locatorid) values (?,?,?,?,?,?,?,?,?) ");
				 param.add(cap);
				 param.add(0);//����
				 param.add(cx[i]);//��վ��
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
